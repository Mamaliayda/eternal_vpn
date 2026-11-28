package com.mlmvpn.scanner.ui.proton

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.net.VpnService
import android.os.Handler
import android.os.Looper
import android.provider.OpenableColumns
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.SslErrorHandler
import android.webkit.ConsoleMessage
import android.webkit.JavascriptInterface
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.mlmvpn.scanner.MyVpnService
import com.mlmvpn.scanner.R
import com.mlmvpn.scanner.engines.amnezia.AmzBrain
import com.mlmvpn.scanner.engines.amnezia.AmzConnect
import com.mlmvpn.scanner.engines.amnezia.AmzEngine
import com.mlmvpn.scanner.engines.amnezia.AmzServer
import com.mlmvpn.scanner.engines.flux.FluxEngine
import com.mlmvpn.scanner.engines.flux.core.model.FluxUiState
import com.mlmvpn.scanner.engines.proton.ProtonAccess
import com.mlmvpn.scanner.engines.proton.ProtonApi
import com.mlmvpn.scanner.engines.proton.ProtonEngine
import com.mlmvpn.scanner.ui.amnezia.DelayCapsule
import com.mlmvpn.scanner.ui.amnezia.fa
import com.mlmvpn.scanner.ui.amnezia.groupFlag
import com.mlmvpn.scanner.ui.amnezia.groupName
import com.mlmvpn.scanner.ui.theme.DuskTheme
import com.mlmvpn.scanner.ui.theme.rememberDuskSnackbarAction
import com.mlmvpn.scanner.ui.settings.IosScreen
import com.mlmvpn.scanner.ui.settings.SettingsActionRow
import com.mlmvpn.scanner.ui.settings.SettingsFooter
import com.mlmvpn.scanner.ui.settings.SettingsGroup
import com.mlmvpn.scanner.ui.settings.SettingsRow
import com.mlmvpn.scanner.ui.settings.SettingsSectionHeader
import com.mlmvpn.scanner.ui.tunnel.TunnelController
import com.mlmvpn.scanner.ui.tunnel.TunnelStage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import java.net.Proxy

private enum class Page { MAIN, WEB }

/**
 * «Proton VPN»: sign in to (or make) a Proton account on Proton's own page inside the app, pick
 * free servers there, and the app takes the WireGuard configs that page makes and connects with
 * them. See [ProtonEngine] for why nothing of the user's password or verification reaches the app,
 * and [ProtonAccess] for how the page is reached when Proton is filtered: any engine that is on
 * carries it, and is turned off again before Proton itself connects.
 */
@Composable
fun ProtonScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val notify = rememberDuskSnackbarAction()
    var ready by remember { mutableStateOf(ProtonEngine.isInitialized()) }
    LaunchedEffect(Unit) {
        if (!ready) { withContext(Dispatchers.IO) { ProtonEngine.init(context) }; ready = true }
    }
    if (!ready) {
        IosScreen(title = stringResource(R.string.proton_title), onBack = onBack, backLabel = stringResource(R.string.home)) {}
        return
    }
    var page by rememberSaveable { mutableStateOf(Page.MAIN) }
    var url by rememberSaveable { mutableStateOf(ProtonEngine.SIGN_IN_URL) }
    BackHandler(enabled = page == Page.WEB) { page = Page.MAIN }

    // A config that was refused: the reason is shown here and the notice consumed, so «آمنزیا»
    // never shows it later.
    val notice by AmzEngine.notice.collectAsState()
    LaunchedEffect(notice) {
        val n = notice ?: return@LaunchedEffect
        val text = when (n) {
            is AmzEngine.Notice.Imported -> context.getString(R.string.proton_added)
            AmzEngine.Notice.ImportNoPeer -> context.getString(R.string.amz_import_no_peer)
            AmzEngine.Notice.ImportFailed, is AmzEngine.Notice.ImportWg -> context.getString(R.string.proton_file_bad)
            else -> null
        }
        if (text != null) {
            notify(text)
            AmzEngine.consumeNotice()
        }
    }

    when (page) {
        Page.WEB -> ProtonWebPage(
            url = url,
            notify = notify,
            onBack = { page = Page.MAIN },
            onDone = { switch -> page = Page.MAIN; if (switch) ProtonEngine.switchToProton(context) },
        )
        Page.MAIN -> ProtonHome(
            notify = notify,
            onBack = onBack,
            onSignIn = { url = ProtonEngine.SIGN_IN_URL; page = Page.WEB },
            onSignUp = { url = ProtonEngine.SIGN_UP_URL; page = Page.WEB },
        )
    }
}

/** The VPN permission, asked once before an engine is turned on. */
@Composable
private fun rememberVpnGate(onDenied: () -> Unit = {}): (() -> Unit) -> Unit {
    val context = LocalContext.current
    var after by remember { mutableStateOf<(() -> Unit)?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { res ->
        if (res.resultCode == Activity.RESULT_OK) after?.invoke() else onDenied()
        after = null
    }
    return { action ->
        com.mlmvpn.scanner.data.ScanGuard.run(com.mlmvpn.scanner.data.ScanGuard.Reason.CONNECT_VPN) {
            val prep = try { VpnService.prepare(context) } catch (e: Exception) { null }
            if (prep != null) { after = action; launcher.launch(prep) } else action()
        }
    }
}

// ── the main page ───────────────────────────────────────────────────────────────────────────────

@Composable
private fun ProtonHome(notify: (String) -> Unit, onBack: () -> Unit, onSignIn: () -> Unit, onSignUp: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val ids by ProtonEngine.ids.collectAsState()
    val switching by ProtonEngine.switching.collectAsState()
    val state by AmzEngine.store.state.collectAsState()
    val net by AmzEngine.net.collectAsState()
    val conn by AmzEngine.connect.collectAsState()
    val pending by AmzEngine.pending.collectAsState()
    val progress by AmzEngine.progress.collectAsState()
    val protonPinging by ProtonEngine.pinging.collectAsState()
    val running by MyVpnService.isRunningFlow.collectAsState()
    val phase by MyVpnService.connectionPhaseFlow.collectAsState()
    val tun by TunnelController.state.collectAsState()
    val fluxUi by FluxEngine.ui.collectAsState()
    val mine = remember(state.servers, ids) { ProtonEngine.servers(state.servers, ids) }
    val stats = remember(state, net) { state.statsFor(net) }
    // A whole account is a long list: the ones that answer on this network first, fastest first,
    // then the untested, then the ones that did not answer.
    val sorted = remember(mine, stats) {
        mine.sortedWith(compareBy<AmzServer>(
            { s -> stats[s.id].let { st -> if (st != null && st.alive) 0 else if (st == null || !st.tested) 1 else 2 } },
            { s -> stats[s.id]?.takeIf { it.alive }?.lastMs ?: Long.MAX_VALUE },
        ))
    }
    // Another engine is still on from the sign-in: Proton cannot run inside it.
    val helperOn = remember(running, phase, tun.stage, fluxUi) { ProtonAccess.current() != null }

    val tried by ProtonEngine.tried.collectAsState()
    val activeId = when (val c = conn) { is AmzConnect.Connecting -> c.serverId; is AmzConnect.Connected -> c.serverId; is AmzConnect.Failed -> c.serverId; else -> null }
    // A failure before any server was picked names none; it is still this screen's when Proton asked.
    val ours = if (activeId != null) activeId in ids else tried && conn is AmzConnect.Failed
    val connected = ours && conn is AmzConnect.Connected
    val busy = (ours && conn is AmzConnect.Connecting) || switching

    val withVpn = rememberVpnGate()
    var askSignOut by remember { mutableStateOf(false) }
    // A session can outlive the servers (all deleted by hand): signing out stays possible then too.
    // Asked off the main thread: the first cookie read of a process starts the whole WebView, and
    // on the main thread that froze the Proton screen as it opened (freeze and ANR reports, 1.2.41).
    var signedIn by remember(mine) { mutableStateOf(false) }
    LaunchedEffect(mine) { signedIn = withContext(Dispatchers.IO) { ProtonApi.signedIn() } }
    val picker = rememberLauncherForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) { uris ->
        scope.launch(Dispatchers.IO) { uris.forEach { importUri(context, it, notify) } }
    }
    val pickFile = {
        try { picker.launch(arrayOf("*/*")) }
        catch (_: android.content.ActivityNotFoundException) { notify(context.getString(R.string.amz_no_file_picker)) }
    }

    IosScreen(onBack = onBack, backLabel = stringResource(R.string.home)) {
        Spacer(Modifier.height(DuskTheme.space2_5))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(painterResource(R.drawable.ic_app_proton), contentDescription = null, modifier = Modifier.size(84.dp))
        }
        Spacer(Modifier.height(DuskTheme.space2))
        Text(
            stringResource(R.string.proton_title), color = DuskTheme.onSurface, fontSize = DuskTheme.textSize32, fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(),
        )
        Text(
            stringResource(R.string.proton_tagline), color = DuskTheme.muted, fontSize = DuskTheme.textSize13,
            textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(horizontal = DuskTheme.space5, vertical = DuskTheme.space1),
        )
        com.mlmvpn.scanner.ui.RouteChips()
        Spacer(Modifier.height(DuskTheme.space3_5))

        if (mine.isEmpty()) {
            PrimaryButton(stringResource(R.string.proton_sign_in), onClick = onSignIn)
            SettingsFooter(stringResource(R.string.proton_sign_in_footer))
            SettingsGroup {
                SettingsActionRow(label = stringResource(R.string.proton_sign_up), icon = Icons.Default.PersonAdd, tint = DuskTheme.purple, onClick = onSignUp)
            }
        } else {
            Spacer(Modifier.height(DuskTheme.space2_5))
            if (helperOn && !switching) {
                SettingsGroup {
                    SettingsRow(
                        title = stringResource(R.string.proton_helper_on), subtitle = stringResource(R.string.proton_helper_on_sub),
                        icon = Icons.Default.Bolt, tint = DuskTheme.accent, showChevron = false,
                    )
                }
                Spacer(Modifier.height(DuskTheme.space2_5))
                PrimaryButton(stringResource(R.string.proton_switch_now), tone = DuskTheme.accent) { ProtonEngine.switchToProton(context) }
                Spacer(Modifier.height(18.dp))
            } else {
                PrimaryButton(
                    stringResource(when { busy -> R.string.proton_cancel; connected -> R.string.proton_disconnect; else -> R.string.proton_connect }),
                    tone = when { connected -> DuskTheme.success; busy -> DuskTheme.accent; else -> DuskTheme.purple },
                ) {
                    if (connected || busy) ProtonEngine.disconnect(context) else withVpn { ProtonEngine.connect(context) }
                }
                Spacer(Modifier.height(DuskTheme.space3))
            }
            val byId = remember(mine) { mine.associateBy { it.id } }
            Text(
                if (switching) stringResource(R.string.proton_switching) else statusText(conn, ours, activeId?.let { byId[it] }),
                color = if (ours && conn is AmzConnect.Failed) DuskTheme.accent else DuskTheme.muted,
                fontSize = DuskTheme.textSize14, textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = DuskTheme.space5),
            )

            SettingsSectionHeader(stringResource(R.string.proton_servers))
            SettingsGroup {
                // At the top, not under 111 rows: the one action that applies to the whole list. While it
                // runs it shows how far it is, how many answered, and which way it is trying.
                if (progress.running && protonPinging) PingProgress(progress) else SettingsActionRow(
                    label = stringResource(R.string.proton_ping_all),
                    icon = Icons.Default.Speed, tint = DuskTheme.success,
                ) { ProtonEngine.pingAll(context) }
                sorted.forEach { s ->
                    Separator()
                    val st = stats[s.id]
                    ServerRow(
                        s, active = ours && s.id == activeId,
                        ms = st?.takeIf { it.alive }?.lastMs,
                        testing = protonPinging && s.id in pending,
                        failed = st != null && st.tested && !st.alive,
                        onClick = { withVpn { ProtonEngine.connect(context, s.id) } },
                        onDelete = { ProtonEngine.remove(context, s.id) },
                    )
                }
                Separator()
                SettingsActionRow(label = stringResource(R.string.proton_add_more), icon = Icons.Default.AddCircle, tint = DuskTheme.purple, onClick = onSignIn)
            }
            SettingsFooter(stringResource(R.string.proton_free_footer))
        }

        SettingsSectionHeader(stringResource(R.string.proton_fallback_title))
        SettingsGroup {
            SettingsActionRow(label = stringResource(R.string.proton_open_browser), icon = Icons.Default.OpenInBrowser, tint = DuskTheme.primary) { openInBrowser(context, notify) }
            Separator()
            SettingsActionRow(label = stringResource(R.string.proton_pick_file), icon = Icons.Default.FileOpen, tint = DuskTheme.secondary, onClick = pickFile)
            if (mine.isNotEmpty() || signedIn) {
                Separator()
                SettingsActionRow(label = stringResource(R.string.proton_sign_out), icon = Icons.Default.Logout, tint = DuskTheme.error) { askSignOut = true }
            }
        }
        // Signing out takes the account's servers out of the list too, so it is asked first.
        if (askSignOut) {
            com.mlmvpn.scanner.ui.settings.IosAlert(
                title = stringResource(R.string.proton_sign_out_title),
                message = stringResource(R.string.proton_sign_out_body),
                onDismiss = { askSignOut = false },
                actions = listOf(
                    com.mlmvpn.scanner.ui.settings.IosAlertAction(
                        stringResource(R.string.cancel), onClick = { askSignOut = false },
                    ),
                    com.mlmvpn.scanner.ui.settings.IosAlertAction(
                        stringResource(R.string.proton_sign_out_action),
                        onClick = {
                            askSignOut = false
                            ProtonEngine.signOut(context)
                            signedIn = false
                            notify(context.getString(R.string.proton_signed_out))
                        },
                        destructive = true,
                        preferred = true,
                    ),
                ),
            )
        }
        SettingsFooter(stringResource(R.string.proton_fallback_footer))
        Spacer(Modifier.height(40.dp))
    }
}

/** «تست ۲۳ از ۱۱۱ · ۸ سالم», a bar, the way being tried, and Stop. */
@Composable
private fun PingProgress(progress: com.mlmvpn.scanner.engines.amnezia.AmzTestProgress) {
    Column(Modifier.fillMaxWidth().padding(horizontal = DuskTheme.space4, vertical = DuskTheme.space3)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                stringResource(R.string.amz_testing, fa(progress.done.toString()), fa(progress.total.toString()), fa(progress.alive.toString())),
                color = DuskTheme.onSurface, fontSize = DuskTheme.textSize15, modifier = Modifier.weight(1f),
            )
            Text(
                stringResource(R.string.amz_stop), color = DuskTheme.error, fontSize = DuskTheme.textSize15, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clip(RoundedCornerShape(DuskTheme.space2)).clickable { AmzEngine.stopTest() }.padding(horizontal = DuskTheme.space2, vertical = DuskTheme.space1),
            )
        }
        Spacer(Modifier.height(DuskTheme.space2_5))
        LinearProgressIndicator(
            progress = if (progress.total > 0) progress.done.toFloat() / progress.total else 0f,
            color = DuskTheme.success, trackColor = DuskTheme.white.copy(alpha = 0.15f),
            modifier = Modifier.fillMaxWidth().height(DuskTheme.space1).clip(RoundedCornerShape(DuskTheme.corner2)),
        )
        Spacer(Modifier.height(DuskTheme.space2))
        Text(
            stringResource(when (progress.via) {
                "u6" -> R.string.proton_ping_via_u6
                "v6" -> R.string.proton_ping_via_v6
                "cf" -> R.string.proton_ping_via_cf
                "u4" -> R.string.proton_ping_via_u4
                "v4" -> R.string.proton_ping_via_v4
                "all" -> R.string.proton_ping_via_all
                else -> R.string.proton_ping_starting
            }),
            color = DuskTheme.muted, fontSize = DuskTheme.textSize13,
        )
    }
}

@Composable
private fun ServerRow(server: AmzServer, active: Boolean, ms: Long?, testing: Boolean, failed: Boolean, onClick: () -> Unit, onDelete: () -> Unit) {
    val key = AmzBrain.group(server)
    Row(
        Modifier.fillMaxWidth().clickable(onClick = onClick).heightIn(min = 60.dp).padding(start = DuskTheme.space4, end = DuskTheme.space1_5, top = DuskTheme.space2, bottom = DuskTheme.space2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(38.dp).clip(CircleShape).background(DuskTheme.white.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
            Text(groupFlag(key), fontSize = DuskTheme.textSize20)
        }
        Spacer(Modifier.width(DuskTheme.space3))
        Column(Modifier.weight(1f)) {
            Text(server.name, color = if (active) DuskTheme.success else DuskTheme.onSurface, fontSize = DuskTheme.textSize16, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(groupName(key), color = DuskTheme.muted, fontSize = DuskTheme.textSize12, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        when {
            testing -> Text("…", color = DuskTheme.muted, fontSize = DuskTheme.textSize16)
            ms != null -> DelayCapsule(ms)
            failed -> Text("—", color = DuskTheme.error, fontSize = DuskTheme.textSize16)
        }
        Spacer(Modifier.width(DuskTheme.space1))
        Box(
            Modifier.size(40.dp).clip(CircleShape).semantics { contentDescription = "delete" }.clickable(role = Role.Button, onClick = onDelete),
            contentAlignment = Alignment.Center,
        ) { Icon(Icons.Default.Close, contentDescription = null, tint = DuskTheme.muted, modifier = Modifier.size(18.dp)) }
    }
}

@Composable
private fun statusText(conn: AmzConnect, ours: Boolean, server: AmzServer?): String {
    if (!ours) return stringResource(R.string.proton_status_idle)
    return when (conn) {
        AmzConnect.Idle -> stringResource(R.string.proton_status_idle)
        is AmzConnect.Connecting -> stringResource(when (conn.step) {
            AmzConnect.Step.PREPARING -> R.string.amz_status_preparing
            AmzConnect.Step.STARTING -> R.string.amz_status_starting
            AmzConnect.Step.HANDSHAKE -> R.string.amz_status_handshake
            AmzConnect.Step.VERIFYING -> R.string.amz_status_verifying
        })
        is AmzConnect.Connected -> listOfNotNull(
            stringResource(R.string.amz_status_connected),
            server?.let { groupName(AmzBrain.group(it)) },
            conn.delayMs?.let { fa("$it ms") },
            // Through Cloudflare, on a line that gives Proton no IPv6 and blackholes its IPv4.
            if (conn.via == "cf") stringResource(R.string.proton_via_cf) else null,
        ).joinToString(" · ")
        is AmzConnect.Failed -> stringResource(when (conn.reason) {
            AmzConnect.Reason.NO_DATA -> if (conn.serverId == null) R.string.proton_fail_none else R.string.amz_fail_no_data
            AmzConnect.Reason.NO_HANDSHAKE -> R.string.proton_fail_no_handshake
            AmzConnect.Reason.VPN_REFUSED -> R.string.amz_fail_vpn
            AmzConnect.Reason.BAD_CONFIG -> R.string.amz_fail_bad_config
            AmzConnect.Reason.OFFLINE -> R.string.amz_fail_offline
        })
    }
}

@Composable
private fun PrimaryButton(label: String, tone: Color = DuskTheme.purple, onClick: () -> Unit) {
    Box(
        Modifier.fillMaxWidth().padding(horizontal = DuskTheme.space4).clip(RoundedCornerShape(DuskTheme.space3))
            .background(tone.copy(alpha = 0.22f))
            .clickable(role = Role.Button, onClick = onClick)
            .padding(vertical = 13.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(label, color = DuskTheme.onSurface, fontSize = DuskTheme.textSize16, fontWeight = FontWeight.SemiBold)
    }
}

// ── the fallbacks: a browser, a file ────────────────────────────────────────────────────────────

/**
 * Proton's page in the phone's own browser. The browser is not the app, so it rides whatever engine
 * is on; the `.conf` it downloads is then picked with «انتخاب فایل».
 */
private fun openInBrowser(context: android.content.Context, notify: (String) -> Unit) {
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(ProtonEngine.SIGN_IN_URL)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    } catch (_: Exception) {
        notify(context.getString(R.string.amz_no_file_picker))
    }
}

/** A `.conf` the user picked: read (a config is a few hundred bytes) and kept like any other. */
private fun importUri(context: android.content.Context, uri: Uri, notify: (String) -> Unit) {
    val name = runCatching {
        context.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)?.use { c ->
            if (c.moveToFirst()) c.getString(0) else null
        }
    }.getOrNull().orEmpty().substringBeforeLast('.')
    val text = runCatching {
        context.contentResolver.openInputStream(uri)?.use { it.readBytes().take(MAX_FILE).toByteArray().toString(Charsets.UTF_8) }
    }.getOrNull()
    if (text.isNullOrBlank()) { notify(context.getString(R.string.proton_file_bad)); return }
    ProtonEngine.onConf(context, text, name)
}

private const val MAX_FILE = 64 * 1024

// ── Proton's own page ───────────────────────────────────────────────────────────────────────────

/** What the page's script hands over; it runs on a WebView thread, so it is posted to the main one. */
private class ConfBridge(private val token: String, private val onConf: (String, String) -> Unit) {
    private val main = Handler(Looper.getMainLooper())
    @JavascriptInterface
    fun conf(token: String?, text: String?, name: String?) {
        if (token != this.token) return
        val t = text ?: return
        if (t.length > 20_000) return
        main.post { onConf(t, name.orEmpty()) }
    }
}

private enum class Check { TESTING, READY, BLOCKED }

/**
 * Proton's real sign-in / sign-up page, then its WireGuard download.
 *
 * Before the page opens, whether Proton answers is checked -- on the network as it is, or through
 * the engine that is on. When it does not, the page does not open: the user is offered an engine to
 * turn on, shown while it comes up (and told if it does not), and the page opens by itself the
 * moment Proton answers through it. The page does the checks it wants (CAPTCHA, emailed code, 2FA)
 * as in any browser; the app only listens for the `.conf` files the dashboard makes, as many as the
 * user takes, and «تمام» ends it. Main-frame navigation is fenced to Proton's own hosts, so no
 * other page can ever reach the one function this page exposes.
 */
@SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
@Composable
private fun ProtonWebPage(url: String, notify: (String) -> Unit, onBack: () -> Unit, onDone: (switch: Boolean) -> Unit) {
    val webViewNoticeColor = DuskTheme.muted
    val context = LocalContext.current
    val running by MyVpnService.isRunningFlow.collectAsState()
    val phase by MyVpnService.connectionPhaseFlow.collectAsState()
    val tun by TunnelController.state.collectAsState()
    val fluxUi by FluxEngine.ui.collectAsState()
    val route = remember(running, phase, tun.stage, fluxUi) { ProtonAccess.current() }

    // The engine the user just asked for, from the tap until Proton opens through it.
    var pending by remember { mutableStateOf<ProtonAccess.Helper?>(null) }
    var timedOut by remember { mutableStateOf(false) }
    LaunchedEffect(pending) {
        timedOut = false
        if (pending != null) { delay(HELPER_WAIT_MS); timedOut = true }
    }
    // An engine's state from BEFORE the tap (a failure of an earlier try) says nothing about this
    // one: a failure counts only after this try has been seen starting.
    var started by remember(pending) { mutableStateOf(false) }
    LaunchedEffect(pending, fluxUi, tun.stage, phase) {
        if (pending != null && (fluxUi is FluxUiState.Searching || phase == MyVpnService.Phase.CONNECTING || tun.stage == TunnelStage.STARTING)) started = true
    }
    val engineFailed = timedOut || (started && when (pending) {
        ProtonAccess.Helper.FLUX -> fluxUi is FluxUiState.Failed
        ProtonAccess.Helper.TOR, ProtonAccess.Helper.WIREGUARD -> tun.stage == TunnelStage.FAILED
        null -> false
    })
    val waiting = route == null && pending != null && !engineFailed
    val starting = waiting || phase == MyVpnService.Phase.CONNECTING || tun.stage == TunnelStage.STARTING ||
        fluxUi is FluxUiState.Searching

    var attempt by remember { mutableStateOf(0) }
    var check by remember { mutableStateOf(Check.TESTING) }
    // The page, once open, stays open: an engine that drops for a moment (FLUX re-racing on a bad
    // line did, on a phone, two minutes in) must not throw away a half-typed sign-in and start the
    // site over. [via] is the listener it opened through; null when Proton answered on the line.
    var opened by remember { mutableStateOf(false) }
    var via by remember { mutableStateOf<ProtonAccess.Route?>(null) }
    // Once the page is open, the engine's wait is over: its timer must not later turn into "the
    // engine failed" while the user is signing in.
    LaunchedEffect(opened) { if (opened) pending = null }
    var proxyUnsupported by remember { mutableStateOf(false) }
    LaunchedEffect(route, attempt, starting) {
        if (opened || starting) return@LaunchedEffect
        check = Check.TESTING
        proxyUnsupported = false
        if (ProtonAccess.reachable(route)) {
            val ok = ProtonAccess.setWebViewProxy(route)
            proxyUnsupported = !ok
            check = if (ok) Check.READY else Check.BLOCKED
            if (ok) { via = route; opened = true }
        } else check = Check.BLOCKED
    }
    // The override is process-wide: whoever sets it puts it back.
    DisposableEffect(Unit) { onDispose { ProtonAccess.clearWebViewProxy() } }

    val ids by ProtonEngine.ids.collectAsState()
    val first = remember { ids.size }
    val got = ids.size - first

    val web = remember { arrayOfNulls<WebView>(1) }
    DisposableEffect(Unit) {
        onDispose { web[0]?.let { it.removeJavascriptInterface("MlmProton"); it.destroy() }; web[0] = null }
    }
    var failed by remember(check) { mutableStateOf<String?>(null) }
    var progress by remember(check) { mutableStateOf(0) }
    var pageError by remember(check) { mutableStateOf<String?>(null) }
    var blank by remember(check) { mutableStateOf(false) }
    // A slow route (FLUX on a busy line) often loses the first load of the page and carries the
    // second: the page is reloaded by itself a couple of times before "did not open" is shown.
    var autoReloads by remember(check) { mutableStateOf(0) }
    // What went wrong inside the page (script errors, requests that failed), newest last: shown when
    // the page stays white, because that is the only way to see why.
    val errs = remember(check) { androidx.compose.runtime.mutableStateListOf<String>() }
    fun note(line: String) { if (line !in errs) errs.add(line); while (errs.size > 4) errs.removeAt(0) }
    // A page that "loaded" and is still white is what a script that cannot run looks like, and it
    // has no error to show: so the page's own text is asked for every few seconds.
    LaunchedEffect(check, attempt) {
        if (check != Check.READY) return@LaunchedEffect
        repeat(8) {
            delay(BLANK_CHECK_MS)
            val w = web[0] ?: return@LaunchedEffect
            val len = suspendCancellableCoroutine<Int> { c ->
                w.evaluateJavascript("(document.body?document.body.innerText.length:-1)") { c.resume(it?.trim()?.toIntOrNull() ?: -1) }
            }
            blank = len < 20
            if (!blank) return@LaunchedEffect
        }
    }

    // An open page follows its engine: when the engine is back (on whatever port it listens now),
    // the page is pointed at it and a load that failed in the meantime is tried again.
    val lost = opened && via != null && route == null
    LaunchedEffect(route) {
        val r = route ?: return@LaunchedEffect
        if (!opened || via == null) return@LaunchedEffect
        if (r != via && ProtonAccess.setWebViewProxy(r)) via = r
        if (failed != null) { failed = null; autoReloads = 0; web[0]?.loadUrl(url) }
    }

    // Signed in: the app takes the account's free servers itself ([ProtonApi]) -- by itself the
    // first time the page shows the signed-in site, and from «تمام» if that has not happened.
    val scope = rememberCoroutineScope()
    var taking by remember { mutableStateOf(false) }
    var taken by remember { mutableStateOf<ProtonApi.Outcome?>(null) }
    fun take(then: (() -> Unit)? = null) {
        if (taking) return
        val w = web[0]
        taking = true
        taken = null
        scope.launch {
            val out = try {
                val version = w?.let { pageVersion(it) }.orEmpty()
                val uid = w?.let { pageUid(it) }
                val ua = w?.settings?.userAgentString.orEmpty()
                // The way the page reached Proton: the engine it opened through (wherever it listens
                // now), or the line.
                val proxy = if (via == null) Proxy.NO_PROXY else (ProtonAccess.current() ?: via)?.javaProxy() ?: Proxy.NO_PROXY
                ProtonEngine.takeFromAccount(context, proxy, version, ua, uid)
            } finally { taking = false }
            taken = out
            if (out is ProtonApi.Outcome.Got) then?.invoke()
        }
    }
    LaunchedEffect(opened) {
        if (!opened) return@LaunchedEffect
        // A take that failed on the way (the route dropped) is tried again by itself, twice.
        var tries = 0
        while (tries < AUTO_TAKES) {
            delay(if (tries == 0) TAKE_POLL_MS else TAKE_RETRY_MS)
            if (taking) continue
            when (taken) { null, is ProtonApi.Outcome.Failed -> Unit; else -> return@LaunchedEffect }
            val w = web[0] ?: continue
            val path = Uri.parse(w.url.orEmpty()).path.orEmpty().lowercase()
            val routeDown = via != null && ProtonAccess.current() == null
            if (progress >= 100 && !routeDown && SIGN_IN_STEPS.none { path.contains(it) } && withContext(Dispatchers.IO) { ProtonApi.signedIn() }) {
                tries++
                take()
            }
        }
    }

    val withVpn = rememberVpnGate(onDenied = { pending = null })
    val pick: (ProtonAccess.Helper) -> Unit = { h ->
        pending = h
        withVpn { ProtonAccess.start(context, h) }
    }

    IosScreen(title = stringResource(R.string.proton_web_title), onBack = onBack, backLabel = stringResource(R.string.proton_title), scrollable = false) {
        Box(Modifier.fillMaxWidth().weight(1f)) {
            // Once open, only the page: none of the states before it may take its place again.
            when {
                !opened && waiting -> HelperProgress(
                    helper = pending!!, fluxUi = fluxUi, phase = phase, tun = tun,
                    onCancel = { ProtonAccess.stop(context); pending = null },
                )
                !opened && starting -> Message(stringResource(R.string.proton_helper_starting))
                !opened && check == Check.TESTING -> Message(stringResource(if (route != null) R.string.proton_checking_via else R.string.proton_checking))
                !opened && check == Check.BLOCKED -> HelperChooser(
                    notify = notify,
                    onPick = pick,
                    onRetry = { pending = null; attempt++ },
                    engineFailed = engineFailed,
                    afterBlockedRoute = route != null,
                    proxyUnsupported = proxyUnsupported,
                )
                else -> {
                    Column(Modifier.fillMaxSize()) {
                        Text(
                            stringResource(R.string.proton_web_hint), color = DuskTheme.muted, fontSize = DuskTheme.textSize13, textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = DuskTheme.space1_5),
                        )
                        if (lost) {
                            Text(
                                stringResource(R.string.proton_route_lost), color = DuskTheme.accent, fontSize = DuskTheme.textSize13, textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = DuskTheme.space1),
                            )
                        }
                        if (progress in 1..99) {
                            LinearProgressIndicator(
                                progress = progress / 100f, color = DuskTheme.purple, trackColor = DuskTheme.white.copy(alpha = 0.12f),
                                modifier = Modifier.fillMaxWidth().height(3.dp),
                            )
                        }
                        Box(Modifier.fillMaxWidth().weight(1f)) {
                            AndroidView(
                                modifier = Modifier.fillMaxSize(),
                                factory = { ctx -> com.mlmvpn.scanner.ui.webViewOrNotice(ctx, webViewNoticeColor) { ctx ->
                                    WebView(ctx).apply {
                                        web[0] = this
                                        // AndroidView leaves a view WRAP_CONTENT, and a WebView that
                                        // is WRAP_CONTENT high forces the page's viewport height to 0:
                                        // CSS `100vh` became 0, Proton's full-height layout centred
                                        // the sign-in form above the top edge, and the page was
                                        // white with all of its text loaded (measured on a phone).
                                        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                                        setBackgroundColor(android.graphics.Color.WHITE)
                                        settings.javaScriptEnabled = true
                                        settings.domStorageEnabled = true
                                        settings.databaseEnabled = true
                                        settings.allowFileAccess = false
                                        settings.allowContentAccess = false
                                        settings.setSupportMultipleWindows(false)
                                        settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_NEVER_ALLOW
                                        // A WebView says so in its user agent ("; wv"), and a site may
                                        // treat that as a lesser browser: this one asks as Chrome does.
                                        settings.userAgentString = android.webkit.WebSettings.getDefaultUserAgent(ctx)
                                            .replace("; wv", "").replace(" Version/4.0", "")
                                        CookieManager.getInstance().setAcceptCookie(true)
                                        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
                                        val token = java.util.UUID.randomUUID().toString()
                                        val js = ProtonEngine.captureJs(token)
                                        addJavascriptInterface(ConfBridge(token) { text, name -> ProtonEngine.onConf(context, text, name) }, "MlmProton")
                                        webChromeClient = object : WebChromeClient() {
                                            override fun onProgressChanged(view: WebView, newProgress: Int) { progress = newProgress }
                                            override fun onConsoleMessage(m: ConsoleMessage): Boolean {
                                                if (m.messageLevel() == ConsoleMessage.MessageLevel.ERROR) {
                                                    pageError = m.message().take(140)
                                                    note("JS: " + m.message().take(90))
                                                    android.util.Log.w("ProtonPage", "${m.message()} (${m.sourceId()}:${m.lineNumber()})")
                                                }
                                                return true
                                            }
                                        }
                                        webViewClient = object : WebViewClient() {
                                            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                                                // Only a whole-page move to somewhere that is not Proton is refused; frames
                                                // inside the page (the CAPTCHA, blank ones) are the page's own business.
                                                val u = request.url
                                                val isWeb = u.scheme == "http" || u.scheme == "https"
                                                return request.isForMainFrame && isWeb && !ProtonEngine.isProtonHost(u.host)
                                            }
                                            override fun onPageStarted(view: WebView, loaded: String?, favicon: android.graphics.Bitmap?) {
                                                failed = null
                                            }
                                            override fun onPageFinished(view: WebView, loaded: String?) {
                                                if (ProtonEngine.isProtonHost(Uri.parse(loaded.orEmpty()).host)) view.evaluateJavascript(js, null)
                                            }
                                            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                                                if (request.isForMainFrame) {
                                                    if (autoReloads < AUTO_RELOADS) {
                                                        autoReloads++
                                                        view.postDelayed({ if (web[0] === view) view.loadUrl(url) }, 1_500)
                                                    } else failed = error.description?.toString() ?: "error"
                                                } else note("${request.url.host}: ${error.description}")
                                            }
                                            override fun onReceivedHttpError(view: WebView, request: WebResourceRequest, response: android.webkit.WebResourceResponse) {
                                                if (request.isForMainFrame) pageError = "HTTP ${response.statusCode}"
                                                else if (response.statusCode >= 400) note("${request.url.host}: HTTP ${response.statusCode}")
                                            }
                                            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                                                // Never carry on past a certificate problem on a sign-in page.
                                                handler.cancel()
                                                failed = "SSL"
                                            }
                                        }
                                        loadUrl(url)
                                    }
                                } },
                            )
                            if (failed != null) {
                                Column(
                                    Modifier.fillMaxSize().background(DuskTheme.background).padding(DuskTheme.space5),
                                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(stringResource(R.string.proton_page_failed), color = DuskTheme.onSurface, fontSize = DuskTheme.textSize16, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                                    Text(failed.orEmpty(), color = DuskTheme.muted, fontSize = DuskTheme.textSize12, textAlign = TextAlign.Center, modifier = Modifier.padding(top = DuskTheme.space1))
                                    Spacer(Modifier.height(DuskTheme.space3_5))
                                    PrimaryButton(stringResource(R.string.proton_retry)) { failed = null; autoReloads = 0; web[0]?.loadUrl(url) }
                                }
                            } else if (blank) {
                                Column(
                                    Modifier.align(Alignment.BottomCenter).fillMaxWidth().background(DuskTheme.background.copy(alpha = 0.94f)).padding(DuskTheme.space3),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(stringResource(R.string.proton_blank), color = DuskTheme.onSurface, fontSize = DuskTheme.textSize14, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                                    errs.forEach { Text(it, color = DuskTheme.muted, fontSize = DuskTheme.textSize11, textAlign = TextAlign.Center, maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 2.dp)) }
                                    Spacer(Modifier.height(DuskTheme.space2))
                                    Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(DuskTheme.space2)) {
                                        Box(Modifier.weight(1f)) { PrimaryButton(stringResource(R.string.proton_reload)) { blank = false; web[0]?.reload() } }
                                        Box(Modifier.weight(1f)) { PrimaryButton(stringResource(R.string.proton_open_browser), tone = DuskTheme.primary) { openInBrowser(context, notify) } }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (check == Check.READY || got > 0) {
            Column(Modifier.fillMaxWidth().background(DuskTheme.background).padding(horizontal = DuskTheme.space4, vertical = DuskTheme.space2)) {
                val outcome = taken
                when {
                    taking -> Row(
                        Modifier.fillMaxWidth().padding(bottom = DuskTheme.space1_5),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CircularProgressIndicator(color = DuskTheme.purple, strokeWidth = 2.dp, modifier = Modifier.size(DuskTheme.space4))
                        Spacer(Modifier.width(DuskTheme.space2))
                        Text(stringResource(R.string.proton_taking), color = DuskTheme.muted, fontSize = DuskTheme.textSize13)
                    }
                    got > 0 -> Text(
                        stringResource(R.string.proton_got_servers, fa(got)), color = DuskTheme.success, fontSize = DuskTheme.textSize13,
                        textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(bottom = DuskTheme.space1_5),
                    )
                    outcome != null && outcome !is ProtonApi.Outcome.Got -> Text(
                        when (outcome) {
                            is ProtonApi.Outcome.NothingNew -> stringResource(R.string.proton_take_none)
                            ProtonApi.Outcome.Expired -> stringResource(R.string.proton_take_expired)
                            ProtonApi.Outcome.NotSignedIn -> stringResource(R.string.proton_sign_in_first)
                            is ProtonApi.Outcome.Failed -> stringResource(R.string.proton_take_failed, outcome.detail.take(120))
                            is ProtonApi.Outcome.Got -> ""
                        },
                        color = DuskTheme.accent, fontSize = DuskTheme.textSize13,
                        textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(bottom = DuskTheme.space1_5),
                    )
                }
                PrimaryButton(
                    stringResource(if (via != null && got > 0) R.string.proton_done_switch else R.string.proton_done),
                    tone = if (got > 0) DuskTheme.purple else DuskTheme.muted,
                ) {
                    when {
                        got > 0 -> onDone(via != null)
                        taking -> Unit
                        // Signed in but nothing taken yet: «تمام» takes them, then finishes.
                        ProtonApi.signedIn() -> take { onDone(via != null) }
                        else -> notify(context.getString(R.string.proton_sign_in_first))
                    }
                }
            }
        }
    }
}

private const val HELPER_WAIT_MS = 120_000L
private const val TAKE_POLL_MS = 2_000L
private const val TAKE_RETRY_MS = 10_000L
private const val AUTO_TAKES = 3
/** Pages of the sign-in or sign-up itself: the session may exist there before the account is ready. */
private val SIGN_IN_STEPS = listOf("login", "signup", "reset", "forgot", "authorize", "verify", "recovery")

private val VERSION = Regex("""^\d+(\.\d+){1,3}$""")

/**
 * The session the page itself is using: Proton keeps it in local storage as `ps-<n>`, the newest one
 * the live one. Only its id is read (it names the AUTH cookie to use), nothing of what it unlocks.
 */
private suspend fun pageUid(w: WebView): String? = withTimeoutOrNull(3_000) {
    suspendCancellableCoroutine<String?> { c ->
        w.evaluateJavascript(PAGE_UID_JS) { raw ->
            val v = raw?.trim()?.trim('"').orEmpty()
            c.resume(v.takeIf { it.isNotEmpty() && it != "null" && it.all { ch -> ch.isLetterOrDigit() || ch == '-' || ch == '_' } })
        }
    }
}

private const val PAGE_UID_JS = """(function(){try{var best=null,at=-1;
for(var i=0;i<localStorage.length;i++){var k=localStorage.key(i);if(!/^ps-\d+$/.test(k))continue;
var o=JSON.parse(localStorage.getItem(k)||'{}');var t=o.persistedAt||0;if(o.UID&&t>=at){best=o.UID;at=t;}}
return best||'';}catch(e){return '';}})()"""

/** The dashboard's version as the page reports it (`SENTRY_RELEASE`); "" when it does not say. */
private suspend fun pageVersion(w: WebView): String = withTimeoutOrNull(3_000) {
    suspendCancellableCoroutine<String> { c ->
        w.evaluateJavascript("(window.SENTRY_RELEASE&&SENTRY_RELEASE.id)||''") { raw ->
            val v = raw?.trim()?.trim('"').orEmpty()
            c.resume(if (VERSION.matches(v)) v else "")
        }
    }
}.orEmpty()
private const val BLANK_CHECK_MS = 6_000L
private const val AUTO_RELOADS = 2

@Composable
private fun Message(text: String) {
    Box(Modifier.fillMaxSize().padding(DuskTheme.space5), contentAlignment = Alignment.Center) {
        Text(text, color = DuskTheme.muted, fontSize = DuskTheme.textSize15, textAlign = TextAlign.Center)
    }
}

@Composable
private fun helperName(h: ProtonAccess.Helper): String = stringResource(when (h) {
    ProtonAccess.Helper.FLUX -> R.string.proton_name_flux
    ProtonAccess.Helper.TOR -> R.string.proton_name_tor
    ProtonAccess.Helper.WIREGUARD -> R.string.proton_name_wg
})

/**
 * The engine the user asked for, coming up: a spinner, what the engine itself says it is doing
 * (asked of the engine, not guessed), and a way to give up. When it is up the page moves on by itself.
 */
@Composable
private fun HelperProgress(
    helper: ProtonAccess.Helper, fluxUi: FluxUiState, phase: MyVpnService.Phase,
    tun: com.mlmvpn.scanner.ui.tunnel.TunnelUiState, onCancel: () -> Unit,
) {
    val name = helperName(helper)
    val line = when {
        helper == ProtonAccess.Helper.FLUX && phase == MyVpnService.Phase.CONNECTING -> stringResource(R.string.proton_wait_starting)
        helper == ProtonAccess.Helper.FLUX && fluxUi is FluxUiState.Searching -> stringResource(R.string.proton_wait_flux_search)
        helper != ProtonAccess.Helper.FLUX && tun.stage == TunnelStage.STARTING && tun.progress >= 0 ->
            stringResource(R.string.proton_wait_starting) + " " + fa(tun.progress) + "٪"
        else -> stringResource(R.string.proton_wait_starting)
    }
    Column(
        Modifier.fillMaxSize().padding(DuskTheme.space5),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(color = DuskTheme.purple, modifier = Modifier.size(44.dp))
        Spacer(Modifier.height(DuskTheme.space4))
        Text(stringResource(R.string.proton_wait_title, name), color = DuskTheme.onSurface, fontSize = DuskTheme.textSize17, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
        Spacer(Modifier.height(DuskTheme.space1_5))
        Text(line, color = DuskTheme.muted, fontSize = DuskTheme.textSize13, textAlign = TextAlign.Center)
        Spacer(Modifier.height(20.dp))
        Box(Modifier.width(220.dp)) { PrimaryButton(stringResource(R.string.proton_cancel), tone = DuskTheme.muted, onClick = onCancel) }
    }
}

/**
 * Proton does not answer: the engines the page can turn on by itself, in one tap each. Whatever
 * else the user turns on (a V2Ray config, FENIX, ...) from its own screen is noticed the same way.
 */
@Composable
private fun HelperChooser(
    notify: (String) -> Unit,
    onPick: (ProtonAccess.Helper) -> Unit, onRetry: () -> Unit,
    engineFailed: Boolean, afterBlockedRoute: Boolean, proxyUnsupported: Boolean,
) {
    val context = LocalContext.current
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(top = DuskTheme.space2)) {
        Text(
            stringResource(R.string.proton_blocked_title), color = DuskTheme.onSurface, fontSize = DuskTheme.textSize17, fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(horizontal = DuskTheme.space5),
        )
        Text(
            stringResource(when {
                proxyUnsupported -> R.string.proton_no_webproxy
                engineFailed -> R.string.proton_helper_failed
                afterBlockedRoute -> R.string.proton_helper_up_failed
                else -> R.string.proton_blocked_body
            }),
            color = if (engineFailed) DuskTheme.accent else DuskTheme.muted, fontSize = DuskTheme.textSize13, textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = DuskTheme.space5, vertical = DuskTheme.space2),
        )
        if (!proxyUnsupported) {
            SettingsGroup {
                SettingsRow(
                    title = stringResource(R.string.proton_helper_flux), subtitle = stringResource(R.string.proton_helper_flux_sub),
                    icon = Icons.Default.Bolt, tint = DuskTheme.indigo,
                    onClick = { onPick(ProtonAccess.Helper.FLUX) })
                Separator()
                SettingsRow(
                    title = stringResource(R.string.proton_helper_tor), subtitle = stringResource(R.string.proton_helper_tor_sub),
                    icon = Icons.Default.Shield, tint = DuskTheme.purple,
                    onClick = { onPick(ProtonAccess.Helper.TOR) })
                Separator()
                SettingsRow(
                    title = stringResource(R.string.proton_helper_wg), subtitle = stringResource(R.string.proton_helper_wg_sub),
                    icon = Icons.Default.VpnKey, tint = DuskTheme.success,
                    onClick = { onPick(ProtonAccess.Helper.WIREGUARD) })
            }
            SettingsFooter(stringResource(R.string.proton_helper_other))
        }
        SettingsGroup {
            SettingsActionRow(label = stringResource(R.string.proton_retry), icon = Icons.Default.Refresh, tint = DuskTheme.purple, onClick = onRetry)
            Separator()
            SettingsActionRow(label = stringResource(R.string.proton_open_browser), icon = Icons.Default.OpenInBrowser, tint = DuskTheme.primary) { openInBrowser(context, notify) }
        }
        Spacer(Modifier.height(DuskTheme.space5))
    }
}
