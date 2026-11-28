package com.mlmvpn.scanner.ui.urnetwork

import com.mlmvpn.scanner.ui.theme.DuskTheme
import com.mlmvpn.scanner.ui.theme.rememberDuskSnackbarAction

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mlmvpn.core.tunnel.IpFormatter
import com.mlmvpn.core.urnetwork.UrnetworkEngine
import com.mlmvpn.core.urnetwork.UrnetworkEngine.Phase
import com.mlmvpn.core.urnetwork.UrnetworkSettings
import com.mlmvpn.core.urnetwork.UrnetworkUsage
import com.mlmvpn.scanner.R
import com.mlmvpn.scanner.store.tr
import com.mlmvpn.scanner.ui.LaunchedWhileVisible
import com.mlmvpn.scanner.ui.emergency.faDigits
import com.mlmvpn.scanner.ui.home.frostedGlass
import com.mlmvpn.scanner.ui.settings.IosScreen
import com.mlmvpn.scanner.ui.settings.SettingsActionRow
import com.mlmvpn.scanner.ui.settings.SettingsFooter
import com.mlmvpn.scanner.ui.settings.SettingsGroup
import com.mlmvpn.scanner.ui.settings.SettingsRow
import com.mlmvpn.scanner.ui.settings.SettingsSectionHeader
import com.mlmvpn.scanner.ui.settings.SettingsToggle
import com.mlmvpn.scanner.ui.tunnel.Transport
import com.mlmvpn.scanner.ui.tunnel.TunnelController
import com.mlmvpn.scanner.ui.tunnel.TunnelStage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

/** «URnetwork»'s own pages behind its screen (see TransportHost). */
object UrnetworkPages {
    const val LOCATIONS = "urnetwork_locations"
    const val RESTORE = "urnetwork_restore"
}

private const val TERMS_URL = "https://ur.io/terms"
private const val PRIVACY_URL = "https://ur.io/privacy"

// ── the first visit: URnetwork's terms, and one button ──────────────────────────────────────

/**
 * What a new user sees, and nothing else: URnetwork's terms in short, the links to them in full,
 * the acceptance, and «ساخت حساب کاربری». The account is made only when the user taps it with the
 * terms accepted -- an instant account, no email and no password. Then the connect screen.
 */
@Composable
fun ColumnScope.UrnetworkOnboarding(onCreated: () -> Unit) {
    val showNotice = rememberDuskSnackbarAction()
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()
    var accepted by rememberSaveable { mutableStateOf(false) }
    var busy by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    Spacer(Modifier.height(18.dp))
    Image(
        painter = painterResource(R.drawable.ic_app_urnetwork),
        contentDescription = null,
        modifier = Modifier.size(84.dp).align(Alignment.CenterHorizontally),
    )
    Spacer(Modifier.height(10.dp))
    Text("URnetwork", color = DuskTheme.onSurface, fontSize = DuskTheme.textSize22, fontWeight = FontWeight.SemiBold, modifier = Modifier.align(Alignment.CenterHorizontally))
    Spacer(Modifier.height(4.dp))
    Text(
        tr("پذیرش قوانین URnetwork", "URnetwork's terms"),
        color = DuskTheme.muted, fontSize = DuskTheme.textSize14, modifier = Modifier.align(Alignment.CenterHorizontally),
    )

    SettingsSectionHeader(tr("قوانین استفاده از URnetwork", "URnetwork's terms of use"))
    Column(
        Modifier.fillMaxWidth().padding(horizontal = 16.dp).frostedGlass(RoundedCornerShape(DuskTheme.corner16)).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        val points = listOf(
            tr("URnetwork شبکه‌ای از دستگاه‌های «ارائه‌دهنده» در کشورهای مختلف است؛ ترافیک شما از دستگاه همین ارائه‌دهندگان بیرون می‌رود.",
                "URnetwork is a network of «provider» devices in many countries; your traffic leaves from these providers' devices."),
            tr("استفاده فقط شخصی و قانونی مجاز است و هر کاربر مسئول کارهایی است که با این شبکه انجام می‌دهد.",
                "Use is for personal, lawful purposes only, and each user is responsible for what they do on the network."),
            tr("ارائه‌دهندگان ترافیک رمزنشده را می‌توانند ببینند؛ برای کارهای حساس فقط از سایت‌ها و برنامه‌های رمزدار (HTTPS) استفاده کنید.",
                "Providers can see unencrypted traffic; for anything sensitive use only encrypted (HTTPS) sites and apps."),
            tr("حساب رایگان سهمیهٔ دادهٔ روزانه دارد که هر روز ساعت ۰۰:۰۰ به وقت جهانی دوباره پر می‌شود؛ مصرف، باقی‌مانده و زمان پر شدن بعدی در صفحهٔ اتصال دیده می‌شود.",
                "A free account has a daily data allowance, granted again every day at 00:00 UTC; the usage, what is left and the time of the next grant are on the connect screen."),
            tr("حساب بی‌ایمیل و بی‌گذرواژه ساخته می‌شود و یک «عبارت بازیابی» دارد که تنها راه برگرداندن آن است.",
                "The account is made with no email or password, and comes with a «recovery phrase», the only way back to it."),
            tr("Eternal VPN نمایندهٔ رسمی URnetwork نیست؛ از SDK متن‌باز و رسمی خود URnetwork استفاده می‌کند.",
                "Eternal VPN is not affiliated with URnetwork; it uses URnetwork's own official open-source SDK."),
        )
        points.forEach { p ->
            Row(verticalAlignment = Alignment.Top) {
                Text("•", color = DuskTheme.primary, fontSize = DuskTheme.textSize15, modifier = Modifier.padding(end = 8.dp))
                Text(p, color = DuskTheme.onSurface, fontSize = DuskTheme.textSize14, lineHeight = DuskTheme.textSize22)
            }
        }
    }
    SettingsGroup(modifier = Modifier.padding(top = 10.dp)) {
        SettingsActionRow(
            label = tr("متن کامل قوانین استفاده (ur.io/terms)", "The full terms of use (ur.io/terms)"),
            icon = Icons.Default.Description, tint = DuskTheme.primary, labelColor = DuskTheme.onSurface,
            onClick = { openUrl(ctx, TERMS_URL, showNotice) },
        )
        Separator()
        SettingsActionRow(
            label = tr("سیاست حریم خصوصی (ur.io/privacy)", "Privacy policy (ur.io/privacy)"),
            icon = Icons.Default.Policy, tint = DuskTheme.primary, labelColor = DuskTheme.onSurface,
            onClick = { openUrl(ctx, PRIVACY_URL, showNotice) },
        )
    }

    // The acceptance: the whole row toggles it, as a checkbox does.
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .clip(RoundedCornerShape(DuskTheme.corner12))
            .clickable(enabled = !busy) { accepted = !accepted; error = null }
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            if (accepted) Icons.Default.CheckBox else Icons.Default.CheckBoxOutlineBlank,
            contentDescription = null,
            tint = if (accepted) DuskTheme.primary else DuskTheme.muted,
            modifier = Modifier.size(26.dp),
        )
        Spacer(Modifier.width(10.dp))
        Text(
            tr("قوانین استفاده و حریم خصوصی URnetwork را خواندم و می‌پذیرم", "I have read and accept URnetwork's terms of use and privacy policy"),
            color = DuskTheme.onSurface, fontSize = DuskTheme.textSize15, lineHeight = DuskTheme.textSize22,
        )
    }

    val enabled = accepted && !busy
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .heightIn(min = 54.dp)
            .clip(RoundedCornerShape(DuskTheme.corner14))
            .background(if (enabled || busy) DuskTheme.primary else DuskTheme.disabled.copy(alpha = 0.35f))
            .clickable(enabled = enabled) {
                busy = true
                error = null
                scope.launch {
                    val err = withContext(Dispatchers.IO) { UrnetworkEngine.createAccount(ctx) }
                    busy = false
                    if (err == null) {
                        showNotice(tr("حساب URnetwork ساخته شد", "The URnetwork account is ready"))
                        onCreated()
                    } else error = err
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        if (busy) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(10.dp))
                Text(tr("در حال ساختن حساب…", "Making the account…"), color = Color.White, fontSize = DuskTheme.textSize17, fontWeight = FontWeight.SemiBold)
            }
        } else {
            Text(tr("ساخت حساب کاربری", "Create account"), color = Color.White, fontSize = DuskTheme.textSize17, fontWeight = FontWeight.SemiBold)
        }
    }
    error?.let {
        Text(it, color = DuskTheme.error, fontSize = DuskTheme.textSize13, lineHeight = DuskTheme.textSize20, modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 10.dp))
    }
    Spacer(Modifier.height(28.dp))
}

// ── while it connects and while it is up: the steps, the providers ──────────────────────────

/**
 * What the core is doing, step by step, the way URnetwork's own app shows its window: the
 * providers being checked, the ones that joined, out of how many it wants -- then where the
 * connected ones are. Shown while the tile owns the tunnel, and after a failed try with its reason.
 */
@Composable
fun UrnetworkLiveCard(ownsTunnel: Boolean) {
    val live by UrnetworkEngine.live.collectAsState()
    val busy = ownsTunnel && live.phase != Phase.IDLE
    if (!busy && live.error == null) return
    var all by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp)
            .frostedGlass(RoundedCornerShape(DuskTheme.corner16)).padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        val failed = live.phase == Phase.FAILED
        val connected = live.phase == Phase.CONNECTED
        val n = maxOf(live.providers, live.window.added)
        Text(
            when {
                failed -> tr("وصل نشد", "Did not connect")
                connected -> tr("وصل — از راه ${faDigits(n.toString())} ارائه‌دهنده", "Connected -- through $n providers")
                live.phase == Phase.RECOVERING -> tr("گرفتن دوبارهٔ ارائه‌دهنده‌ها", "Getting providers again")
                else -> tr("در حال اتصال به URnetwork", "Connecting to URnetwork")
            },
            color = when {
                failed -> DuskTheme.error
                connected -> DuskTheme.success
                else -> DuskTheme.onSurface
            },
            fontSize = DuskTheme.textSize15, fontWeight = FontWeight.SemiBold,
        )

        // The steps, as far as this session got.
        val steps = buildList {
            add(Phase.STARTING to tr("راه‌اندازی هستهٔ URnetwork", "Starting URnetwork's core"))
            if (live.registering) add(Phase.REGISTERING to tr("ثبت این گوشی در حساب", "Registering this phone in the account"))
            add(Phase.FINDING to tr("یافتن ارائه‌دهنده‌ها", "Finding providers"))
            add(Phase.TESTING to tr("آزمایش عبور داده از ارائه‌دهنده‌ها", "Testing data through the providers"))
            add(Phase.TUNNEL to tr("ساخت تونل گوشی", "Building the phone's tunnel"))
        }
        val current = when (live.phase) {
            Phase.CONNECTED -> Phase.CONNECTED
            Phase.RECOVERING -> Phase.FINDING
            Phase.FAILED -> live.reached
            else -> live.phase
        }
        steps.forEach { (phase, label) ->
            val state = when {
                current == Phase.CONNECTED || phase.ordinal < current.ordinal -> StepState.DONE
                phase == current -> if (failed) StepState.FAILED else StepState.ACTIVE
                else -> StepState.WAITING
            }
            StepRow(label, state) {
                if (phase == Phase.FINDING && (state == StepState.ACTIVE || state == StepState.DONE || state == StepState.FAILED)) {
                    WindowDetail(live.window)
                }
            }
        }

        if (connected && live.countries.isNotEmpty()) {
            Spacer(Modifier.height(2.dp))
            Text(
                live.countries.take(8).joinToString("   ") { c ->
                    val flag = if (c.code.length == 2 && IpFormatter.isRealCountry(c.code)) IpFormatter.flag(c.code.uppercase()) else "🌐"
                    "$flag ${faDigits(c.count.toString())}"
                },
                color = DuskTheme.onSurface, fontSize = DuskTheme.textSize14,
            )
            // The SDK reports no place for a provider it kept from an earlier connection (a restored
            // exit skips the search that carries the place).
            if (live.countries.any { it.code.length != 2 || !IpFormatter.isRealCountry(it.code) }) {
                Text(
                    tr("🌐 یعنی کشور آن ارائه‌دهنده گزارش نشده است (مثلاً ارائه‌دهنده‌ای که از اتصال قبلی نگه داشته شده).",
                        "🌐 means the provider's country was not reported (for example one kept from the previous connection)."),
                    color = DuskTheme.muted, fontSize = DuskTheme.textSize12, lineHeight = DuskTheme.textSize18,
                )
            }
            Text(
                tr("خروج هم‌زمان از چند ارائه‌دهنده است؛ هر اتصال ممکن است از یکی از این کشورها بیرون برود. ارائه‌دهنده‌ها دستگاه کاربران دیگرند و می‌آیند و می‌روند؛ URnetwork خاموش‌ها و کندها را مدام با تازه‌ها عوض می‌کند، پس کم و زیاد شدن این عدد عادی است.",
                    "Traffic leaves through several providers at once; each connection may exit in any of these countries. Providers are other users' devices and come and go; URnetwork keeps replacing the ones that went quiet or slow, so this number rising and falling is normal."),
                color = DuskTheme.muted, fontSize = DuskTheme.textSize12, lineHeight = DuskTheme.textSize18,
            )
        }
        // The SDK's own count, as the official app shows it: packets stopped, not names (a name on
        // the list is answered empty in DNS and sends nothing to count). Nothing shown at zero.
        if (live.blocker && live.blocked > 0) {
            Text(
                tr("بسته‌های مسدودشدهٔ تبلیغات و ردیاب‌ها: ${faDigits(live.blocked.toString())}", "Ad and tracker packets blocked: ${live.blocked}"),
                color = DuskTheme.muted, fontSize = DuskTheme.textSize12,
            )
        }
        if (busy && connected && live.stalled) {
            Text(
                tr("مسیر داده لحظه‌ای جواب نداد؛ اتصال به سرور URnetwork دوباره برقرار می‌شود و ارائه‌دهنده‌ها نگه داشته شده‌اند.",
                    "The data path stopped answering for a moment; URnetwork's server connection is being redialed and the providers are held."),
                color = DuskTheme.accent, fontSize = DuskTheme.textSize12, lineHeight = DuskTheme.textSize18,
            )
        }
        if (busy && live.saving.isNotEmpty()) {
            Text(
                tr("حالت کم‌مصرف (${live.saving.joinToString("، ")}): در بیکاری، ارائه‌دهنده‌ها سه برابر کمتر بررسی می‌شوند تا رادیوی گوشی کمتر بیدار شود.",
                    "Power saving (${live.saving.joinToString(", ")}): while idle, the providers are checked three times less often so the phone's radio wakes less."),
                color = DuskTheme.muted, fontSize = DuskTheme.textSize12, lineHeight = DuskTheme.textSize18,
            )
        }
        if (failed) live.error?.let { Text(it, color = DuskTheme.muted, fontSize = DuskTheme.textSize13, lineHeight = DuskTheme.textSize20) }

        val lines = if (all) live.log else live.log.takeLast(5)
        if (lines.isNotEmpty()) {
            Spacer(Modifier.height(2.dp))
            lines.forEach { Text("· ${faDigits(it)}", color = DuskTheme.muted, fontSize = DuskTheme.textSize11, lineHeight = DuskTheme.textSize16) }
        }
        if (live.log.size > 5) {
            Text(
                if (all) tr("کوتاه‌تر", "Less") else tr("همهٔ گزارش (${faDigits(live.log.size.toString())} خط)", "The whole log (${live.log.size} lines)"),
                color = DuskTheme.primary, fontSize = DuskTheme.textSize13, modifier = Modifier.clickable { all = !all }.padding(top = 2.dp),
            )
        }
    }
}

private enum class StepState { DONE, ACTIVE, WAITING, FAILED }

@Composable
private fun StepRow(label: String, state: StepState, extra: @Composable () -> Unit) {
    Row(verticalAlignment = Alignment.Top) {
        Box(Modifier.size(22.dp), contentAlignment = Alignment.Center) {
            when (state) {
                StepState.DONE -> Icon(Icons.Default.Check, contentDescription = null, tint = DuskTheme.success, modifier = Modifier.size(18.dp))
                StepState.ACTIVE -> CircularProgressIndicator(color = DuskTheme.primary, strokeWidth = 2.dp, modifier = Modifier.size(14.dp))
                StepState.FAILED -> Text("✕", color = DuskTheme.error, fontSize = DuskTheme.textSize14, fontWeight = FontWeight.Bold)
                StepState.WAITING -> Box(Modifier.size(7.dp).clip(CircleShape).background(DuskTheme.disabled.copy(alpha = 0.45f)))
            }
        }
        Spacer(Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(
                label,
                color = when (state) {
                    StepState.WAITING -> DuskTheme.muted
                    StepState.FAILED -> DuskTheme.error
                    else -> DuskTheme.onSurface
                },
                fontSize = DuskTheme.textSize14, lineHeight = DuskTheme.textSize21,
                fontWeight = if (state == StepState.ACTIVE) FontWeight.Medium else FontWeight.Normal,
            )
            extra()
        }
    }
}

/** The window as numbers and as dots: joined (green), being checked (amber), failed (red), free. */
@Composable
private fun WindowDetail(w: UrnetworkEngine.Window) {
    val numbers = if (w.target == 0 && w.evaluating == 0 && w.added == 0) tr("در انتظار فهرست ارائه‌دهنده‌ها…", "Waiting for the provider list…")
    else tr(
        "${faDigits(w.evaluating.toString())} در حال بررسی · ${faDigits(w.added.toString())} وصل از ${faDigits(w.target.toString())}" +
            (if (w.evalFailed > 0) " · ${faDigits(w.evalFailed.toString())} رد شد" else ""),
        "${w.evaluating} being checked · ${w.added} of ${w.target} connected" + (if (w.evalFailed > 0) " · ${w.evalFailed} failed" else ""),
    )
    Text(numbers, color = DuskTheme.muted, fontSize = DuskTheme.textSize12, lineHeight = DuskTheme.textSize18)
    // The SDK's diagnosis is for a window still forming: once a provider joined, the providers
    // coming and going are its normal life, not a failure.
    if (w.added == 0) UrnetworkEngine.stallText(w.stall)?.let { Text(it, color = DuskTheme.accent, fontSize = DuskTheme.textSize12, lineHeight = DuskTheme.textSize18) }
    val slots = maxOf(w.target, w.added + w.evaluating).coerceIn(0, 32)
    if (slots == 0) return
    val pulse by rememberInfiniteTransition(label = "urn-dots").animateFloat(
        initialValue = 0.35f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(700), RepeatMode.Reverse), label = "urn-dot",
    )
    val perRow = 16
    val rows = (slots + perRow - 1) / perRow
    val failedShown = w.evalFailed.coerceAtMost((slots - w.added - w.evaluating).coerceAtLeast(0))
    val rtl = LocalConfiguration.current.layoutDirection == android.view.View.LAYOUT_DIRECTION_RTL
    // The theme's colours are read here: the draw scope below is not composable.
    val joined = DuskTheme.success
    val checking = DuskTheme.accent.copy(alpha = pulse)
    val refused = DuskTheme.error.copy(alpha = 0.7f)
    val free = DuskTheme.disabled.copy(alpha = 0.3f)
    Canvas(Modifier.fillMaxWidth().padding(top = 6.dp).height((rows * 14).dp)) {
        val d = 9.dp.toPx()
        val gap = 5.dp.toPx()
        for (i in 0 until slots) {
            val color = when {
                i < w.added -> joined
                i < w.added + w.evaluating -> checking
                i < w.added + w.evaluating + failedShown -> refused
                else -> free
            }
            val col = i % perRow
            val row = i / perRow
            val x = if (rtl) size.width - (col * (d + gap)) - d else col * (d + gap)
            drawCircle(color, radius = d / 2, center = Offset(x + d / 2, row * (d + gap) + d / 2))
        }
    }
}

// ── the account's data ─────────────────────────────────────────────────────────────────────

/**
 * The bar and the numbers of the account's data, straight from URnetwork's own `/subscription/balance`,
 * as the official app's usage bar and «About your data» show them: on the free plan today's
 * allowance -- granted every day at 00:00 UTC -- with what is used, left and settling, and the time
 * to the next grant; on Pro the month's. Fetched when the screen shows and every minute and a half
 * while it does; the last answer stays while offline.
 */
@Composable
fun UrnetworkUsageCard(onRestore: () -> Unit) {
    val ctx = LocalContext.current
    val live by UrnetworkEngine.live.collectAsState()
    var usage by remember { mutableStateOf(UrnetworkEngine.cachedUsage(ctx)) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var signedOut by remember { mutableStateOf(false) }
    var tick by remember { mutableIntStateOf(0) }
    var now by remember { mutableLongStateOf(System.currentTimeMillis()) }
    val scope = rememberCoroutineScope()

    suspend fun refresh() {
        loading = true
        when (val r = withContext(Dispatchers.IO) { UrnetworkEngine.api(ctx, "balance") }) {
            is UrnetworkEngine.Api.Ok -> {
                UrnetworkEngine.saveUsage(ctx, r.json)
                usage = UrnetworkUsage.parse(r.json, System.currentTimeMillis())
                error = null
                signedOut = false
            }
            UrnetworkEngine.Api.SignedOut -> signedOut = true
            is UrnetworkEngine.Api.Failed -> error = r.reason
        }
        loading = false
    }
    LaunchedWhileVisible(tick) {
        while (true) {
            refresh()
            delay(90_000)
        }
    }
    // The countdown moves on each whole minute; past the grant, the new day's numbers are fetched.
    LaunchedWhileVisible(Unit) {
        while (true) {
            val before = now
            now = System.currentTimeMillis()
            if (UrnetworkUsage.nextFreeRefresh(before) != UrnetworkUsage.nextFreeRefresh(now)) tick++
            delay(60_000L - now % 60_000L + 200L)
        }
    }

    val u = usage
    val daily = u?.pro != true
    SettingsSectionHeader(if (daily) tr("حجم روزانهٔ حساب", "The account's daily data") else tr("حجم ماهانهٔ حساب", "The account's monthly data"))
    Column(
        Modifier.fillMaxWidth().padding(horizontal = 16.dp).frostedGlass(RoundedCornerShape(DuskTheme.corner16)).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val plan = when {
                u == null -> tr("حساب URnetwork", "URnetwork account")
                u.pro -> tr("طرح Pro", "Pro plan")
                else -> tr("طرح رایگان", "Free plan")
            }
            Text(plan, color = DuskTheme.onSurface, fontSize = DuskTheme.textSize15, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
            if (loading) CircularProgressIndicator(color = DuskTheme.primary, strokeWidth = 2.dp, modifier = Modifier.size(16.dp))
            else Icon(
                Icons.Default.Refresh, contentDescription = tr("به‌روزرسانی", "Refresh"), tint = DuskTheme.primary,
                modifier = Modifier.size(22.dp).clip(CircleShape).clickable { scope.launch { refresh() } },
            )
        }
        if (u == null) {
            Text(
                if (error != null) tr("هنوز اطلاعات حجم گرفته نشده: ", "No data figures yet: ") + error
                else tr("در حال گرفتن حجم حساب از URnetwork…", "Getting the account's data from URnetwork…"),
                color = DuskTheme.muted, fontSize = DuskTheme.textSize13, lineHeight = DuskTheme.textSize20,
            )
        } else {
            val exhausted = live.insufficient || (u.total > 0 && u.remaining <= 0L)
            val refreshAt = u.refreshAt(now)
            Text(
                if (daily) tr("${bytes(u.used)} از ${bytes(u.total)} سهمیهٔ امروز مصرف شده", "${bytes(u.used)} of today's ${bytes(u.total)} used")
                else tr("${bytes(u.used)} از ${bytes(u.total)} مصرف شده", "${bytes(u.used)} of ${bytes(u.total)} used"),
                color = DuskTheme.onSurface, fontSize = DuskTheme.textSize17, fontWeight = FontWeight.Medium,
            )
            UsageBar(u.fraction, exhausted)
            Row(Modifier.fillMaxWidth()) {
                Metric(tr("مصرف‌شده", "Used"), bytes(u.used), Modifier.weight(1f))
                Metric(tr("باقی‌مانده", "Left"), bytes(u.remaining), Modifier.weight(1f), if (exhausted) DuskTheme.error else DuskTheme.success)
                Metric(
                    tr("تا شارژ بعدی", "To the next grant"),
                    when {
                        refreshAt == null -> "—"
                        daily -> UrnetworkUsage.countdown(now, refreshAt).let { (h, m) ->
                            if (h > 0) tr("${faDigits(h.toString())} ساعت", "$h h") else tr("${faDigits(m.toString())} دقیقه", "$m min")
                        }
                        else -> u.daysLeft(now)?.let { d -> if (d <= 0) tr("امروز", "today") else tr("${faDigits(d.toString())} روز", "$d days") } ?: "—"
                    },
                    Modifier.weight(1f),
                )
            }
            if (refreshAt != null) {
                Text(
                    if (daily) {
                        val (h, m) = UrnetworkUsage.countdown(now, refreshAt)
                        tr("حجم رایگان هر روز ساعت ${faDigits(clockLabel(refreshAt))} (به وقت گوشی) دوباره پر می‌شود — " +
                            (if (h > 0) "${faDigits(h.toString())} ساعت و " else "") + "${faDigits(m.toString())} دقیقهٔ دیگر.",
                            "The free data is granted again every day at ${clockLabel(refreshAt)} (phone time) -- in " +
                                (if (h > 0) "$h h " else "") + "$m min.")
                    } else {
                        tr("ریست بعدی: ", "Next reset: ") + dateLabel(refreshAt) +
                            (u.daysLeft(now)?.let { d -> if (d > 0) tr(" (${faDigits(d.toString())} روز دیگر)", " (in $d days)") else "" } ?: "")
                    },
                    color = DuskTheme.muted, fontSize = DuskTheme.textSize12, lineHeight = DuskTheme.textSize18,
                )
            }
            if (u.pending > 0) {
                Text(
                    tr("در حال تسویه: ${bytes(u.pending)} — حجمی که اتصال‌های باز گرفته‌اند و پس از بسته شدن آن‌ها حساب می‌شود.",
                        "Settling: ${bytes(u.pending)} -- held by open connections, counted once they close."),
                    color = DuskTheme.muted, fontSize = DuskTheme.textSize12, lineHeight = DuskTheme.textSize18,
                )
            }
            if (exhausted) {
                Text(
                    if (daily && refreshAt != null) tr("حجم امروز تمام شده است؛ URnetwork تا ساعت ${faDigits(clockLabel(refreshAt))} داده‌ای رد نمی‌کند.",
                        "Today's data is used up; URnetwork carries nothing until ${clockLabel(refreshAt)}.")
                    else tr("حجم این دوره تمام شده است؛ URnetwork تا ریست بعدی داده‌ای رد نمی‌کند.", "This period's data is used up; URnetwork carries nothing until the next reset."),
                    color = DuskTheme.error, fontSize = DuskTheme.textSize13, lineHeight = DuskTheme.textSize20,
                )
            }
            error?.let {
                Text(tr("آخرین به‌روزرسانی نشد: ", "The last refresh failed: ") + it, color = DuskTheme.accent, fontSize = DuskTheme.textSize12, lineHeight = DuskTheme.textSize18)
            }
            Text(tr("به‌روز شده ", "Updated ") + ago(u.fetchedAt), color = DuskTheme.muted, fontSize = DuskTheme.textSize11)
        }
        if (signedOut) {
            Text(
                tr("URnetwork ورود این حساب را دیگر نمی‌پذیرد. با عبارت بازیابی دوباره وارد شوید.", "URnetwork no longer accepts this account's sign-in. Sign in again with the recovery phrase."),
                color = DuskTheme.error, fontSize = DuskTheme.textSize13, lineHeight = DuskTheme.textSize20,
            )
            Text(tr("ورود با عبارت بازیابی", "Sign in with the recovery phrase"), color = DuskTheme.primary, fontSize = DuskTheme.textSize14,
                modifier = Modifier.clickable { onRestore() })
        }
    }
}

@Composable
private fun UsageBar(fraction: Float, exhausted: Boolean) {
    val color = when {
        exhausted || fraction >= 0.9f -> DuskTheme.error
        fraction >= 0.75f -> DuskTheme.accent
        else -> DuskTheme.success
    }
    val rtl = LocalConfiguration.current.layoutDirection == android.view.View.LAYOUT_DIRECTION_RTL
    val track = DuskTheme.disabled.copy(alpha = 0.25f)
    Canvas(Modifier.fillMaxWidth().height(10.dp)) {
        val r = CornerRadius(size.height / 2, size.height / 2)
        drawRoundRect(track, cornerRadius = r)
        val w = size.width * (if (exhausted) 1f else fraction)
        if (w > 0f) drawRoundRect(color, topLeft = Offset(if (rtl) size.width - w else 0f, 0f), size = Size(w, size.height), cornerRadius = r)
    }
}

@Composable
private fun Metric(label: String, value: String, modifier: Modifier, color: Color = DuskTheme.onSurface) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = color, fontSize = DuskTheme.textSize15, fontWeight = FontWeight.SemiBold, maxLines = 1)
        Text(label, color = DuskTheme.muted, fontSize = DuskTheme.textSize12)
    }
}

// ── the choices below the dial ─────────────────────────────────────────────────────────────

/**
 * «URnetwork»'s settings on its screen: the exit location, URnetwork's own ad and tracker blocker
 * (off by default), its use in MAE, and the account with its recovery phrase.
 */
@Composable
fun ColumnScope.UrnetworkPanel(onOpenPage: (String) -> Unit) {
    val showNotice = rememberDuskSnackbarAction()
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()
    val live by UrnetworkEngine.live.collectAsState()
    val tunnel by TunnelController.state.collectAsState()
    val ours = tunnel.active == Transport.URNETWORK && (tunnel.stage == TunnelStage.STARTING || tunnel.stage == TunnelStage.RUNNING)

    if (!UrnetworkEngine.available(ctx)) {
        SettingsGroup {
            SettingsRow(
                title = tr("روی این گوشی در دسترس نیست", "Not available on this phone"),
                subtitle = UrnetworkEngine.unavailableReason(ctx),
                icon = Icons.Default.Info, tint = DuskTheme.accent, showChevron = false,
            )
        }
        return
    }

    // ── where it comes out ──
    SettingsSectionHeader(tr("مکان خروج", "Exit location"))
    SettingsGroup {
        val code = UrnetworkSettings.locationCode(ctx)
        val name = UrnetworkSettings.locationName(ctx)
        SettingsRow(
            title = tr("کشور", "Country"),
            icon = Icons.Default.Language, tint = DuskTheme.indigo,
            value = if (code == null && name == null) tr("بهترین مکان در دسترس", "Best available")
                else listOfNotNull(code?.takeIf { IpFormatter.isRealCountry(it) }?.let { IpFormatter.flag(it.uppercase()) }, name ?: code?.uppercase()).joinToString(" "),
            onClick = { onOpenPage(UrnetworkPages.LOCATIONS) },
        )
    }
    SettingsFooter(tr(
        "«بهترین مکان در دسترس» ارائه‌دهنده‌های پاسخ‌گو را از هر کشوری برمی‌دارد. با انتخاب یک کشور، همهٔ ارائه‌دهنده‌ها از همان کشور می‌آیند. اگر وصل باشید، مکان تازه بی‌قطع شدن تونل اعمال می‌شود.",
        "«Best available» takes responsive providers from any country. Choose a country and every provider comes from it. While connected, a new location applies without dropping the tunnel.",
    ))

    // ── ads and trackers ──
    var blocker by remember { mutableStateOf(UrnetworkSettings.blocker(ctx)) }
    SettingsSectionHeader(tr("تبلیغات و ردیاب‌ها", "Ads and trackers"))
    SettingsGroup {
        SettingsToggle(
            title = tr("مسدودسازی تبلیغات و ردیاب‌ها", "Block ads and trackers"),
            checked = blocker,
            onCheckedChange = {
                blocker = it
                UrnetworkSettings.setBlocker(ctx, it)
                UrnetworkEngine.blockerChanged(it)
            },
            icon = Icons.Default.Block, tint = DuskTheme.error,
            subtitle = if (blocker && ours && live.blocked > 0) tr("${faDigits(live.blocked.toString())} بستهٔ مسدودشده", "${live.blocked} packets blocked") else null,
        )
    }
    SettingsFooter(tr(
        "مسدودکنندهٔ خود URnetwork (همان که در برنامهٔ رسمی است): فهرستی از حدود ۱۶۰ هزار دامنهٔ تبلیغاتی و ردیاب که درون تونل جلوی آن‌ها گرفته می‌شود. پیش‌فرض خاموش است و روشن و خاموش کردنش بی‌قطع اتصال اعمال می‌شود. اگر سایت یا برنامه‌ای درست کار نکرد، خاموشش کنید. عدد کنار کلید، مثل برنامهٔ رسمی، بسته‌های مسدودشده را می‌شمارد؛ نام‌هایی که همان اول در DNS بسته می‌شوند در آن نمی‌آیند.",
        "URnetwork's own blocker (the one in its official app): a list of about 160,000 ad and tracker domains stopped inside the tunnel. Off by default; turning it on or off applies without reconnecting. If a site or app misbehaves, turn it off. The number by the switch counts blocked packets, as the official app does; names stopped right away in DNS are not in it.",
    ))

    // ── MAE ──
    var forMae by remember { mutableStateOf(UrnetworkSettings.forMae(ctx)) }
    SettingsSectionHeader("MAE")
    SettingsGroup {
        SettingsToggle(
            title = tr("استفاده در MAE", "Use in MAE"),
            checked = forMae,
            onCheckedChange = {
                forMae = it
                UrnetworkSettings.setForMae(ctx, it)
                com.mlmvpn.scanner.engines.mae.MaeEngine.urnetworkForMaeChanged()
            },
            icon = Icons.Default.AutoAwesome, tint = DuskTheme.primary,
        )
    }
    SettingsFooter(tr(
        "وقتی MAE وصل است، URnetwork یکی از خروجی‌های خارجی آن می‌شود: یک هستهٔ جدا (یک دستگاه جدا در همین حساب) کنار MAE بالا می‌آید و MAE برای هر برنامه خودش می‌سنجد که از URnetwork باز می‌شود یا نه. مصرف آن هم از همین حجم حساب کم می‌شود. به URnetwork این کاشی دست نمی‌زند.",
        "While MAE is connected, URnetwork becomes one of its exits abroad: a separate core (a device of its own in this account) runs beside MAE, and MAE checks for each app by itself whether it opens through URnetwork. Its traffic counts against the same account data. The URnetwork of this tile is not touched.",
    ))

    // ── the account ──
    var showSeed by remember { mutableStateOf(false) }
    var seed by remember { mutableStateOf<String?>(null) }
    val name = remember(tunnel.stage) { UrnetworkEngine.accountName(ctx) }
    SettingsSectionHeader(tr("حساب", "Account"))
    SettingsGroup {
        if (name != null) {
            SettingsRow(title = tr("نام حساب", "Account name"), icon = Icons.Default.Person, tint = DuskTheme.disabled, value = name, showChevron = false)
            Separator()
        }
        SettingsActionRow(
            label = if (showSeed) tr("پنهان کردن عبارت بازیابی", "Hide the recovery phrase") else tr("نمایش عبارت بازیابی", "Show the recovery phrase"),
            icon = Icons.Default.Key, tint = DuskTheme.accent, labelColor = DuskTheme.onSurface,
            onClick = {
                if (showSeed) showSeed = false
                else scope.launch {
                    seed = withContext(Dispatchers.IO) { UrnetworkEngine.seed(ctx) }
                    showSeed = true
                }
            },
        )
        if (showSeed) {
            Separator()
            val s = seed
            if (s == null) {
                SettingsRow(
                    title = tr("عبارت بازیابی روی این گوشی نیست", "There is no recovery phrase on this phone"),
                    subtitle = tr("URnetwork هنگام ساختن این حساب عبارتی نداد.", "URnetwork gave no phrase when this account was made."),
                    showChevron = false,
                )
            } else {
                SettingsRow(title = s, subtitle = tr("این را جای امنی نگه دارید و به کسی ندهید؛ تنها راه برگرداندن حساب است.", "Keep this somewhere safe and give it to nobody; it is the only way back to the account."), showChevron = false)
                Separator()
                SettingsActionRow(
                    label = tr("کپی عبارت بازیابی", "Copy the recovery phrase"),
                    icon = Icons.Default.ContentCopy, tint = DuskTheme.primary, labelColor = DuskTheme.onSurface,
                    onClick = {
                        ctx.getSystemService(ClipboardManager::class.java)?.setPrimaryClip(ClipData.newPlainText("URnetwork", s))
                        showNotice(tr("کپی شد", "Copied"))
                    },
                )
            }
        }
        Separator()
        SettingsActionRow(
            label = tr("ورود با عبارت بازیابی حسابی دیگر", "Sign in with another account's recovery phrase"),
            icon = Icons.Default.Login, tint = DuskTheme.primary, labelColor = DuskTheme.onSurface,
            onClick = { onOpenPage(UrnetworkPages.RESTORE) },
        )
    }
    SettingsFooter(tr(
        "عبارت بازیابی تنها راه برگرداندن این حساب روی گوشی دیگر یا پس از پاک شدن برنامه است. در پشتیبان‌گیری برنامه فقط رمزشده نگه داشته می‌شود.",
        "The recovery phrase is the only way back to this account on another phone or after the app is removed. The app's backup keeps it encrypted only.",
    ))
}

// ── the location picker ────────────────────────────────────────────────────────────────────

private data class UrLocation(val id: String, val name: String, val code: String, val providers: Int)

/**
 * The countries URnetwork has providers in right now, from its own `/network/provider-locations`,
 * each with its provider count; «best available» first. A choice applies at once to a running core.
 */
@Composable
fun UrnetworkLocationsScreen(onBack: () -> Unit, backLabel: String) {
    val ctx = LocalContext.current
    var list by remember { mutableStateOf<List<UrLocation>?>(cachedLocations(ctx)) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var query by rememberSaveable { mutableStateOf("") }
    var selected by remember { mutableStateOf(UrnetworkSettings.locationCode(ctx)) }
    val locale = LocalConfiguration.current.locales[0]

    LaunchedEffect(Unit) {
        loading = true
        when (val r = withContext(Dispatchers.IO) { UrnetworkEngine.api(ctx, "locations") }) {
            is UrnetworkEngine.Api.Ok -> {
                ctx.getSharedPreferences("urnetwork", Context.MODE_PRIVATE).edit()
                    .putString("locations", r.json.toString()).putLong("locations_at", System.currentTimeMillis()).apply()
                list = parseLocations(r.json)
                error = null
            }
            UrnetworkEngine.Api.SignedOut -> error = UrnetworkEngine.readable("auth")
            is UrnetworkEngine.Api.Failed -> error = r.reason
        }
        loading = false
    }

    fun choose(loc: UrLocation?) {
        if (loc == null) UrnetworkSettings.setBest(ctx)
        else {
            val json = JSONObject()
                .put("connect_location_id", JSONObject().put("location_id", loc.id))
                .put("name", loc.name)
                .put("location_type", "country")
                .put("country_code", loc.code)
                .put("country_location_id", loc.id)
                .put("provider_count", loc.providers)
            UrnetworkSettings.setLocation(ctx, json.toString(), loc.name, loc.code)
        }
        selected = loc?.code
        UrnetworkEngine.locationChanged(ctx)
        onBack()
    }

    IosScreen(title = tr("مکان خروج", "Exit location"), onBack = onBack, backLabel = backLabel) {
        Spacer(Modifier.height(10.dp))
        SettingsGroup {
            Row(Modifier.fillMaxWidth().heightIn(min = 46.dp).padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    singleLine = true,
                    textStyle = TextStyle(color = DuskTheme.onSurface, fontSize = DuskTheme.textSize16),
                    cursorBrush = SolidColor(DuskTheme.primary),
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = { inner ->
                        Box {
                            if (query.isEmpty()) Text(tr("جستجوی کشور", "Search countries"), color = DuskTheme.muted.copy(alpha = 0.6f), fontSize = DuskTheme.textSize16)
                            inner()
                        }
                    },
                )
            }
        }
        SettingsSectionHeader(tr("انتخاب خودکار", "Automatic"))
        SettingsGroup {
            LocationRow(
                title = tr("بهترین مکان در دسترس", "Best available"),
                detail = tr("پیشنهادی — پاسخ‌گوترین ارائه‌دهنده‌ها از هر کشور", "Recommended -- the most responsive providers, anywhere"),
                flag = "🌐",
                selected = selected == null,
            ) { choose(null) }
        }
        SettingsSectionHeader(tr("کشورها", "Countries"))
        val all = list
        when {
            all == null && loading -> Text(tr("در حال گرفتن فهرست کشورها از URnetwork…", "Getting the country list from URnetwork…"),
                color = DuskTheme.muted, fontSize = DuskTheme.textSize13, modifier = Modifier.padding(horizontal = 32.dp))
            all == null -> Text(tr("فهرست کشورها گرفته نشد: ", "The country list did not come: ") + (error ?: ""),
                color = DuskTheme.accent, fontSize = DuskTheme.textSize13, lineHeight = DuskTheme.textSize20, modifier = Modifier.padding(horizontal = 32.dp))
            else -> {
                val q = query.trim().lowercase()
                val shown = all.filter { l ->
                    q.isEmpty() || l.name.lowercase().contains(q) || l.code.lowercase() == q ||
                        java.util.Locale("", l.code.uppercase()).getDisplayCountry(locale).lowercase().contains(q)
                }
                SettingsGroup {
                    shown.forEachIndexed { i, l ->
                        if (i > 0) Separator()
                        val localName = java.util.Locale("", l.code.uppercase()).getDisplayCountry(locale).ifBlank { l.name }
                        LocationRow(
                            title = localName,
                            detail = tr("${faDigits(l.providers.toString())} ارائه‌دهنده", "${l.providers} providers"),
                            flag = if (IpFormatter.isRealCountry(l.code)) IpFormatter.flag(l.code.uppercase()) else "🌐",
                            selected = selected?.equals(l.code, ignoreCase = true) == true,
                        ) { choose(l) }
                    }
                }
                if (shown.isEmpty()) Text(tr("کشوری با این نام نیست.", "No country by that name."), color = DuskTheme.muted, fontSize = DuskTheme.textSize13,
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp))
                error?.let { Text(tr("فهرست تازه گرفته نشد؛ فهرست قبلی نشان داده می‌شود: ", "No fresh list; showing the last one: ") + it,
                    color = DuskTheme.accent, fontSize = DuskTheme.textSize12, lineHeight = DuskTheme.textSize18, modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)) }
            }
        }
        SettingsFooter(tr(
            "تعداد ارائه‌دهنده‌های هر کشور همین حالا از خود URnetwork گرفته می‌شود و هر لحظه تغییر می‌کند. کشورهای کم‌ارائه‌دهنده ممکن است کندتر باشند.",
            "Each country's provider count comes from URnetwork itself right now and changes all the time. Countries with few providers may be slower.",
        ))
        Spacer(Modifier.height(28.dp))
    }
}

@Composable
private fun LocationRow(title: String, detail: String, flag: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().clickable(onClick = onClick).heightIn(min = 52.dp).padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(flag, fontSize = DuskTheme.textSize20)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = DuskTheme.onSurface, fontSize = DuskTheme.textSize16)
            Text(detail, color = DuskTheme.muted, fontSize = DuskTheme.textSize12)
        }
        if (selected) Icon(Icons.Default.Check, contentDescription = null, tint = DuskTheme.primary, modifier = Modifier.size(20.dp))
    }
}

private fun cachedLocations(ctx: Context): List<UrLocation>? =
    ctx.getSharedPreferences("urnetwork", Context.MODE_PRIVATE).getString("locations", null)
        ?.let { runCatching { parseLocations(JSONObject(it)) }.getOrNull() }

/** The countries of `/network/provider-locations`, most providers first. */
private fun parseLocations(json: JSONObject): List<UrLocation> {
    val arr = json.optJSONArray("locations") ?: JSONArray()
    return (0 until arr.length()).mapNotNull { i ->
        val o = arr.optJSONObject(i) ?: return@mapNotNull null
        if (o.optString("location_type") != "country") return@mapNotNull null
        val id = o.optString("location_id").ifBlank { return@mapNotNull null }
        val code = o.optString("country_code").ifBlank { return@mapNotNull null }
        UrLocation(id, o.optString("name").ifBlank { code.uppercase() }, code.lowercase(), o.optInt("provider_count"))
    }.filter { it.providers > 0 }.sortedByDescending { it.providers }
}

// ── signing in with a recovery phrase ──────────────────────────────────────────────────────

@Composable
fun UrnetworkRestoreScreen(onBack: () -> Unit, backLabel: String) {
    val showNotice = rememberDuskSnackbarAction()
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()
    var phrase by rememberSaveable { mutableStateOf("") }
    var busy by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    val tunnel by TunnelController.state.collectAsState()
    val ours = tunnel.active == Transport.URNETWORK && tunnel.isBusy

    IosScreen(title = tr("ورود با عبارت بازیابی", "Sign in with a recovery phrase"), onBack = onBack, backLabel = backLabel) {
        Spacer(Modifier.height(14.dp))
        SettingsGroup {
            Box(Modifier.fillMaxWidth().heightIn(min = 120.dp).padding(16.dp)) {
                BasicTextField(
                    value = phrase,
                    onValueChange = { phrase = it; error = null },
                    textStyle = TextStyle(color = DuskTheme.onSurface, fontSize = DuskTheme.textSize16, lineHeight = DuskTheme.textSize24),
                    cursorBrush = SolidColor(DuskTheme.primary),
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = { inner ->
                        Box {
                            if (phrase.isEmpty()) Text(tr("عبارت بازیابی حساب URnetwork (کلمه‌های انگلیسی با فاصله)", "The URnetwork account's recovery phrase (English words, space separated)"),
                                color = DuskTheme.muted.copy(alpha = 0.6f), fontSize = DuskTheme.textSize15)
                            inner()
                        }
                    },
                )
            }
        }
        SettingsFooter(
            if (ours) tr("اول URnetwork را قطع کنید؛ حساب روی اتصال روشن عوض نمی‌شود.", "Disconnect URnetwork first; the account does not change under a live connection.")
            else tr("حساب فعلی این گوشی کنار گذاشته می‌شود. اگر عبارت بازیابی آن را ندارید و هنوز لازمش دارید، اول از صفحهٔ قبل کپی‌اش کنید.",
                "This phone's current account is set aside. If you do not have its recovery phrase and still need it, copy it from the previous page first.")
        )
        SettingsGroup(modifier = Modifier.padding(top = 14.dp)) {
            SettingsActionRow(
                label = if (busy) tr("در حال ورود…", "Signing in…") else tr("ورود", "Sign in"),
                icon = Icons.Default.Login, tint = DuskTheme.primary, busy = busy,
                enabled = !busy && !ours && phrase.trim().split(Regex("\\s+")).size >= 6,
                onClick = {
                    busy = true
                    scope.launch {
                        val err = withContext(Dispatchers.IO) { UrnetworkEngine.restoreAccount(ctx, phrase) }
                        busy = false
                        if (err == null) {
                            showNotice(tr("وارد حساب شدید", "Signed in"))
                            onBack()
                        } else error = err
                    }
                },
            )
        }
        error?.let { Text(it, color = DuskTheme.error, fontSize = DuskTheme.textSize13, lineHeight = DuskTheme.textSize20, modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 8.dp)) }
        Spacer(Modifier.height(28.dp))
    }
}

// ── small pieces ───────────────────────────────────────────────────────────────────────────

private fun openUrl(ctx: Context, url: String, showNotice: (String) -> Unit) {
    runCatching { ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) }
        .onFailure { showNotice(url) }
}

/** Bytes as people read them, on 1024: "۲۹٫۸ گیگابایت" / "29.8 GB". */
internal fun bytes(n: Long): String {
    val v = n.coerceAtLeast(0L).toDouble()
    val (value, fa, en) = when {
        v >= 1024.0 * 1024 * 1024 -> Triple(v / (1024.0 * 1024 * 1024), "گیگابایت", "GB")
        v >= 1024.0 * 1024 -> Triple(v / (1024.0 * 1024), "مگابایت", "MB")
        v >= 1024.0 -> Triple(v / 1024.0, "کیلوبایت", "KB")
        else -> Triple(v, "بایت", "B")
    }
    val num = if (value >= 100 || value == Math.floor(value)) String.format(java.util.Locale.US, "%.0f", value)
        else String.format(java.util.Locale.US, "%.1f", value)
    return tr("${faDigits(num).replace('.', '٫')} $fa", "$num $en")
}

/** A day in the user's calendar: Solar Hijri in Persian ("۱۵ آبان ۱۴۰۵"), Gregorian in English. */
private fun dateLabel(ms: Long): String = runCatching {
    tr(
        android.icu.text.DateFormat.getDateInstance(android.icu.text.DateFormat.LONG, android.icu.util.ULocale("fa_IR@calendar=persian")).format(java.util.Date(ms)),
        java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, java.util.Locale.US).format(java.util.Date(ms)),
    )
}.getOrDefault("")

/** "03:30" on the phone's own clock. */
private fun clockLabel(ms: Long): String = java.text.SimpleDateFormat("HH:mm", java.util.Locale.US).format(java.util.Date(ms))

/** "۳ دقیقه پیش", "۲ روز پیش". */
private fun ago(at: Long): String {
    val s = ((System.currentTimeMillis() - at) / 1000).coerceAtLeast(0)
    return when {
        s < 60 -> tr("همین حالا", "just now")
        s < 3600 -> tr("${faDigits((s / 60).toString())} دقیقه پیش", "${s / 60} min ago")
        s < 86_400 -> tr("${faDigits((s / 3600).toString())} ساعت پیش", "${s / 3600} h ago")
        else -> tr("${faDigits((s / 86_400).toString())} روز پیش", "${s / 86_400} days ago")
    }
}
