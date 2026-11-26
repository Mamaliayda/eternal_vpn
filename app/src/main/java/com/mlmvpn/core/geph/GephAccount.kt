package com.mlmvpn.core.geph

import android.content.Context
import com.mlmvpn.core.tunnel.SecureStore
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

/**
 * The Geph credentials used by this phone's VPN engine. Account creation and sign-in stay because
 * Geph requires them to establish a tunnel; this client does not inspect subscription tiers or
 * offer voucher redemption.
 */

object GephAccount {

    private const val KEY_SECRET = "geph_secret"
    private const val KEY_USER = "geph_legacy_user"
    private const val KEY_PASS = "geph_legacy_pass"

    sealed class SecretStatus {
        data class Current(val userId: Long) : SecretStatus()
        /** Replaced by a newer code -- the new one is what logs in now. */
        object Retired : SecretStatus()
        object Invalid : SecretStatus()
    }

    data class News(val title: String, val dateUnix: Long, val contents: String, val important: Boolean)

    // ── the credential ─────────────────────────────────────────────────────────────────────────

    @Synchronized fun credential(ctx: Context): GephCredential? {
        val secret = SecureStore.getSecret(ctx, KEY_SECRET)
        if (secret.isNotBlank()) return GephCredential.Secret(secret)
        val user = SecureStore.getSecret(ctx, KEY_USER)
        val pass = SecureStore.getSecret(ctx, KEY_PASS)
        if (user.isNotBlank() && pass.isNotBlank()) return GephCredential.Legacy(user, pass)
        return null
    }

    /**
     * Whether an account is stored: no decrypting and no lock, so a screen can ask from the main
     * thread. It used to decrypt through the Keystore under this object's lock, and the Geph screen
     * froze whenever the Keystore was slow or a sign-in held the lock (ANR and freeze reports, 1.2.41).
     */
    fun hasAccount(ctx: Context): Boolean =
        SecureStore.hasSecret(ctx, KEY_SECRET) || (SecureStore.hasSecret(ctx, KEY_USER) && SecureStore.hasSecret(ctx, KEY_PASS))

    /** Digits only: people paste codes with spaces or dashes between the groups. */
    fun normalizeSecret(input: String): String? =
        input.filter { it.isDigit() || it in "۰۱۲۳۴۵۶۷۸۹" }
            .map { c -> if (c in '۰'..'۹') '0' + (c - '۰') else c }
            .joinToString("")
            .takeIf { it.length in 16..40 }

    /** «1234 5678 …» -- the way the official app shows it, so a user can read it aloud. */
    fun formatSecret(secret: String): String = secret.chunked(4).joinToString(" ")

    @Synchronized fun setSecret(ctx: Context, secret: String) {
        forgetCredentialOnly(ctx)
        SecureStore.putSecret(ctx, KEY_SECRET, secret)
    }

    @Synchronized fun setLegacy(ctx: Context, username: String, password: String) {
        forgetCredentialOnly(ctx)
        SecureStore.putSecret(ctx, KEY_USER, username)
        SecureStore.putSecret(ctx, KEY_PASS, password)
    }

    /** Removes the account from this phone. The code still works anywhere else it is entered. */
    @Synchronized fun forget(ctx: Context) {
        credential(ctx)?.let { deleteCache(ctx, it) }
        forgetCredentialOnly(ctx)
    }

    private fun forgetCredentialOnly(ctx: Context) {
        SecureStore.removeSecret(ctx, KEY_SECRET)
        SecureStore.removeSecret(ctx, KEY_USER)
        SecureStore.removeSecret(ctx, KEY_PASS)
    }

    /** An automatic registration must never replace an account entered while it was running. */
    @Synchronized fun keepAutomaticSecret(ctx: Context, secret: String): GephCredential {
        credential(ctx)?.let { return it }
        setSecret(ctx, requireNotNull(normalizeSecret(secret)))
        return requireNotNull(credential(ctx))
    }

    private fun deleteCache(ctx: Context, cred: GephCredential) {
        val db = GephEngine.cacheFile(ctx, cred)
        listOf(db, File(db.path + "-wal"), File(db.path + "-shm")).forEach { it.delete() }
    }

    /** Whether [secret] is a live account code, one that has been replaced, or nothing at all. */
    fun secretStatus(ctx: Context, secret: String): Result<SecretStatus> = runCatching {
        val answer = GephEngine.withControl(ctx) { c ->
            c.brokerRpc("get_account_secret_status", JSONArray().put(secret))
        }
        when {
            answer is String && answer.equals("retired", true) -> SecretStatus.Retired
            answer is String -> SecretStatus.Invalid
            answer is JSONObject && answer.has("current") ->
                SecretStatus.Current(answer.optJSONObject("current")?.optLong("user_id") ?: 0L)
            else -> SecretStatus.Invalid
        }
    }

    /** Only codes of the first generation (starting with 9) can be replaced; new ones start with 8. */
    fun canRotate(ctx: Context): Boolean =
        (credential(ctx) as? GephCredential.Secret)?.secret?.startsWith("9") == true

    /**
     * Replaces the account code. The old one stops working everywhere and every device has to
     * log in again with the new one -- which is why the new code is saved here immediately.
     */
    fun rotate(ctx: Context): Result<String> = runCatching {
        val old = (credential(ctx) as? GephCredential.Secret)?.secret
            ?: throw IllegalStateException("no account code")
        val answer = GephEngine.withControl(ctx) { c ->
            c.brokerRpc("rotate_account_secret", JSONArray().put(old), 30_000)
        }
        val fresh = (answer as? String)?.let(::normalizeSecret) ?: throw IllegalStateException("no new code in the answer")
        credential(ctx)?.let { deleteCache(ctx, it) }
        SecureStore.putSecret(ctx, KEY_SECRET, fresh)
        fresh
    }

    /**
     * Makes a free account: the network's proof-of-work puzzle, solved on the phone. Takes
     * roughly half a minute to a few minutes depending on the processor. [onProgress] gets 0..1.
     */
    fun register(ctx: Context, onProgress: (Double) -> Unit, cancelled: () -> Boolean): Result<String> = runCatching {
        GephEngine.withControl(ctx) { c ->
            val index = c.startRegistration()
            val deadline = System.currentTimeMillis() + 20 * 60_000L
            while (System.currentTimeMillis() < deadline) {
                if (cancelled()) throw InterruptedException("cancelled")
                val (progress, secret) = c.pollRegistration(index)
                onProgress(progress.coerceIn(0.0, 1.0))
                if (secret != null) {
                    val code = normalizeSecret(secret) ?: throw IllegalStateException("bad account code")
                    setSecret(ctx, code)
                    return@withControl code
                }
                Thread.sleep(1_200)
            }
            throw IllegalStateException("the puzzle did not finish in 20 minutes")
        }
    }

    /** Geph's own announcements, in the app's language when the network has it (fa included). */
    fun news(ctx: Context, lang: String): Result<List<News>> = runCatching {
        val arr = GephEngine.withControl(ctx) { c ->
            runCatching { c.brokerRpc("get_news", JSONArray().put(lang)) as? JSONArray }.getOrNull()
                ?: c.latestNews(lang)
        } ?: JSONArray()
        (0 until arr.length()).mapNotNull { i ->
            val o = arr.optJSONObject(i) ?: return@mapNotNull null
            News(o.optString("title"), o.optLong("date_unix"), o.optString("contents"), o.optBoolean("important"))
        }.sortedByDescending { it.dateUnix }
    }
}
