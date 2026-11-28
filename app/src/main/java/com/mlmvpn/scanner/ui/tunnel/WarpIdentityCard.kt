package com.mlmvpn.scanner.ui.tunnel

import com.mlmvpn.scanner.ui.theme.DuskTheme
import com.mlmvpn.scanner.ui.theme.rememberDuskSnackbarAction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mlmvpn.core.warp.WarpIdRelay
import com.mlmvpn.scanner.data.CloudManager
import com.mlmvpn.scanner.store.tr
import com.mlmvpn.scanner.ui.settings.SettingsFooter
import com.mlmvpn.scanner.ui.settings.SettingsGroup
import com.mlmvpn.scanner.ui.settings.SettingsRow
import com.mlmvpn.scanner.ui.settings.SettingsSectionHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/** The three transports that start from a WARP identity. */
internal val Transport.usesWarpIdentity: Boolean
    get() = this == Transport.MASQUE || this == Transport.WIREGUARD || this == Transport.GOOL

/**
 * Deploy the identity relay on the user's first Cloudflare account, then [onReady].
 * Shared by the failure card and the identity row, so both behave the same.
 */
private fun setUpRelay(
    context: android.content.Context,
    scope: kotlinx.coroutines.CoroutineScope,
    onStep: (String?) -> Unit,
    onReady: () -> Unit,
    showNotice: (String) -> Unit,
) {
    scope.launch {
        val account = withContext(Dispatchers.IO) {
            val cloud = CloudManager(context).apply { loadAccounts() }
            cloud.accounts.firstOrNull { it.accountId == WarpIdRelay.accountId(context) } ?: cloud.accounts.firstOrNull()
        }
        if (account == null) {
            showNotice(tr("اول در بخش «ابری» یک حساب کلادفلر اضافه کنید؛ هویت از راه همان ساخته می‌شود.",
                "Add a Cloudflare account in «Cloud» first; the identity is made through it."))
            onStep(null)
            return@launch
        }
        onStep(tr("راه‌اندازی ورکر…", "Setting up the worker…"))
        val (ok, message) = withContext(Dispatchers.IO) {
            WarpIdRelay.deploy(context, account) { step -> scope.launch { onStep(step) } }
        }
        onStep(null)
        if (ok) onReady() else showNotice(message)
    }
}

/**
 * Under the dial, when a connect failed because no identity could be made: the one thing to do
 * about it, doable right here. One tap puts the relay on the user's account and connects again.
 */
@Composable
internal fun IdentityBlockedCard(onRetry: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val showNotice = rememberDuskSnackbarAction()
    val scope = rememberCoroutineScope()
    var step by remember { mutableStateOf<String?>(null) }
    val ready = remember(step) { WarpIdRelay.isReady(context) }

    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(DuskTheme.corner20))
            .background(DuskTheme.cloudflareOrange.copy(alpha = 0.16f))
            .padding(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Warning, null, tint = DuskTheme.accent, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(8.dp))
            Text(
                tr("هویت وارپ ساخته نشد", "No WARP identity could be made"),
                color = DuskTheme.onSurface, fontSize = DuskTheme.textSize16, fontWeight = FontWeight.Bold,
            )
        }
        Spacer(Modifier.height(6.dp))
        Text(
            if (ready) {
                tr("ورکر هویت روی حسابتان هست ولی این بار جواب نداد. دوباره امتحان کنید.",
                    "Your identity worker is set up but did not answer this time. Try again.")
            } else {
                tr("سرور صدور هویت وارپ روی این اینترنت فیلتر است. با یک لمس، هویت از راه یک ورکر روی حساب کلادفلر خودتان ساخته می‌شود — مثل ترفند جمنای — و دوباره وصل می‌شویم.",
                    "The WARP identity server is filtered on this network. One tap makes the identity through a worker on your own Cloudflare account — like the Gemini trick — and connects again.")
            },
            color = DuskTheme.muted, fontSize = DuskTheme.textSize13, lineHeight = DuskTheme.textSize20,
        )
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier
                .clip(RoundedCornerShape(50))
                .background(DuskTheme.primary)
                .clickable(enabled = step == null) {
                    if (ready) onRetry() else setUpRelay(context, scope, { step = it }, onRetry, showNotice)
                }
                .padding(horizontal = 18.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (step != null) {
                CircularProgressIndicator(Modifier.size(16.dp), color = Color.White, strokeWidth = 2.dp)
                Spacer(Modifier.width(8.dp))
            }
            Text(
                step ?: if (ready) tr("دوباره وصل شو", "Connect again") else tr("ساخت هویت از راه ورکر و اتصال", "Make it through a worker and connect"),
                color = Color.White, fontSize = DuskTheme.textSize14, fontWeight = FontWeight.Bold,
            )
        }
    }
}

/**
 * On the transport's own page: whether its identity exists, how it was made, and the switch for
 * the worker route. [refresh] changes whenever the tunnel stage does, so the row follows a connect.
 */
@Composable
internal fun ColumnScope.WarpIdentitySection(transport: Transport, refresh: Any?) {
    val context = LocalContext.current
    val showNotice = rememberDuskSnackbarAction()
    val scope = rememberCoroutineScope()
    var step by remember { mutableStateOf<String?>(null) }
    var bump by remember { mutableIntStateOf(0) }
    // A cheap file check, repeated: the identity is made mid-connect, before any stage changes.
    com.mlmvpn.scanner.ui.LaunchedWhileVisible(Unit) {
        while (true) { kotlinx.coroutines.delay(2000); bump++ }
    }
    val id = remember(refresh, bump) { WarpIdRelay.identityState(context, transport.value) }
    val relay = remember(refresh, bump, step) { WarpIdRelay.isReady(context) }

    SettingsSectionHeader(tr("هویت وارپ", "WARP identity"))
    SettingsGroup {
        SettingsRow(
            title = tr("هویت", "Identity"),
            icon = Icons.Default.Badge,
            tint = if (id.made) DuskTheme.success else DuskTheme.disabled,
            value = when {
                !id.made -> tr("هنوز ساخته نشده", "Not made yet")
                id.via == WarpIdRelay.VIA_WORKER -> tr("ساخته شد · از راه ورکر", "Made · through the worker")
                id.via == WarpIdRelay.VIA_DIRECT -> tr("ساخته شد · مستقیم", "Made · directly")
                id.via == WarpIdRelay.VIA_ECH -> tr("ساخته شد · با نام رمزگذاری‌شده (ECH)", "Made · with its name encrypted (ECH)")
                else -> tr("ساخته شد", "Made")
            },
            showChevron = false,
            onClick = null,
        )
        Separator()
        // Knows its build: a relay already on this app's build is not uploaded again on a tap.
        com.mlmvpn.scanner.ui.settings.WorkerSetupRow(
            title = tr("ساخت هویت از راه ورکر", "Make identity through a worker"),
            icon = Icons.Default.VpnKey,
            tint = DuskTheme.accent,
            deployed = remember(refresh, bump, step) { WarpIdRelay.deployedVersion(context) },
            latest = WarpIdRelay.VERSION,
            progress = step,
            subtitleOff = tr("خاموش", "Off"),
            subtitleOn = tr("فعال", "On"),
            onDeploy = { if (step == null) setUpRelay(context, scope, { step = it }, { bump++ }, showNotice) },
        )
    }
    SettingsFooter(
        if (relay) {
            tr("وقتی هویت نیست، از راه ورکر روی حساب کلادفلر خودتان ساخته می‌شود؛ اگر آن نشد، مستقیم.",
                "When there is no identity, it is made through the worker on your own Cloudflare account; failing that, directly.")
        } else {
            tr("اگر ساخت هویت روی اینترنت شما فیلتر باشد، این را یک بار روشن کنید.",
                "If making an identity is filtered on your network, turn this on once.")
        }
    )
}
