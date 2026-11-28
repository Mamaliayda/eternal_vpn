package com.mlmvpn.scanner.ui.home

import android.app.Activity
import android.net.VpnService
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.mlmvpn.scanner.ui.theme.DuskCard
import com.mlmvpn.scanner.ui.theme.DuskPill
import com.mlmvpn.scanner.ui.theme.DuskTheme
import com.mlmvpn.scanner.ui.theme.LocalDuskSnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mlmvpn.scanner.store.tr
import com.mlmvpn.scanner.widget.WidgetConnect
import com.mlmvpn.scanner.widget.WidgetConnectService
import com.mlmvpn.scanner.widget.WidgetFormat
import com.mlmvpn.scanner.widget.WidgetLive
import com.mlmvpn.scanner.widget.WidgetMethods
import com.mlmvpn.scanner.widget.WidgetPainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.max
import kotlin.math.pow

/**
 * The connection widget at the top of the home board, iOS-style: which method is carrying the phone,
 * where it comes out, the traffic moving through it as a live chart, this session's usage, and the
 * button that ends it. Idle, it offers the last method used.
 *
 * It takes the top two rows of the first page (see BoardGrid), the way an iOS widget takes a block
 * of icon cells. Removing it -- the «−» badge while the board is being rearranged -- turns it off;
 * Settings > Display and Widget Studio turn it back on.
 */
@Composable
internal fun HomeConnectionWidget(
    editMode: Boolean,
    onOpenApp: (HomeApp) -> Unit,
    onEnterEditMode: () -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val conn by WidgetLive.conn.collectAsState()
    // Collected only while it is on screen and the app is in front: collecting is what keeps the
    // one-second sampler going, and a plain collect went on all night behind a stopped activity.
    val traffic by WidgetLive.traffic.collectAsStateWithLifecycle()
    DisposableEffect(Unit) {
        WidgetLive.wantCountry("home", true)
        onDispose { WidgetLive.wantCountry("home", false) }
    }
    val method = conn.method?.takeIf { WidgetMethods.isMethod(it) }
    // Read off the main thread: the record's preferences file and, for a node, the node list behind
    // it took seconds on slow phones, and the home screen froze while it drew (1.2.42 #2110, #2327).
    var last by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf<String?>(null) }
    LaunchedEffect(conn.method) {
        last = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) { lastMethod(context) }
    }
    val shown = method ?: last
    val app = WidgetMethods.app(shown)

    val consent = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { res ->
        val m = last
        if (res.resultCode == Activity.RESULT_OK && m != null) WidgetConnectService.start(context, m, null)
    }
    val scope = androidx.compose.runtime.rememberCoroutineScope()
    val snackbarHostState = LocalDuskSnackbarHostState.current
    fun connectLast() {
        val m = last ?: return
        if (com.mlmvpn.scanner.data.ScanGuard.isScanBusy()) {
            scope.launch {
                snackbarHostState.showSnackbar(tr("یک اسکن در حال اجراست؛ اول آن را متوقف کنید", "A scan is running; stop it first"))
            }
            return
        }
        // VpnService.prepare is a call into the system that took seconds on a busy phone, with the
        // home screen frozen behind the tap (1.2.42 #2374): asked off the main thread.
        scope.launch {
            val prep = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) { runCatching { VpnService.prepare(context) }.getOrNull() }
            if (prep != null) runCatching { consent.launch(prep) } else WidgetConnectService.start(context, m, null)
        }
    }

    val connectedAccent = com.mlmvpn.scanner.ui.theme.DuskTheme.accent
    val connectingAccent = com.mlmvpn.scanner.ui.theme.DuskTheme.secondary
    val accent = when {
        conn.up -> connectedAccent
        conn.busy -> connectingAccent
        else -> com.mlmvpn.scanner.ui.theme.DuskTheme.primary
    }
    val shape = DuskTheme.cardShape

    DuskCard(modifier = modifier, shape = shape, contentPadding = PaddingValues(0.dp)) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    // The accent's glow, from the button's corner: the widget's state at a glance.
                    val rtl = layoutDirection == LayoutDirection.Rtl
                    val cx = if (rtl) size.width * 0.16f else size.width * 0.84f
                    drawCircle(
                        brush = Brush.radialGradient(
                            listOf(accent.copy(alpha = if (conn.up) 0.16f else 0.07f), Color.Transparent),
                            center = Offset(cx, size.height * 0.55f),
                            radius = max(size.width, size.height) * 0.62f,
                        ),
                        radius = max(size.width, size.height),
                        center = Offset(cx, size.height * 0.55f),
                    )
                }
                .pointerInput(editMode, shown) {
                    detectTapGestures(
                        onTap = { if (!editMode) app?.let(onOpenApp) },
                        onLongPress = { onEnterEditMode() },
                    )
                }
                .padding(horizontal = 16.dp, vertical = 14.dp),
        ) {
            val compact = maxHeight < 150.dp
            DuskThemeHills(Modifier.fillMaxSize())
            Column(modifier = Modifier.fillMaxSize()) {
                Header(conn, app, compact)
                Spacer(Modifier.height(if (compact) 6.dp else 10.dp))
                Row(modifier = Modifier.fillMaxWidth().weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.weight(1f).fillMaxSize()) {
                        when {
                            conn.up && !compact -> LiveChart(traffic, connectedAccent, com.mlmvpn.scanner.ui.theme.DuskTheme.blueGray, Modifier.fillMaxSize())
                            conn.up -> Unit
                            conn.busy -> Text(
                                tr("در حال برقراری اتصال…", "Connecting…"),
                                color = connectingAccent, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                                modifier = Modifier.align(Alignment.CenterStart),
                            )
                            else -> Text(
                                if (last != null) tr("آخرین روش شما آماده است؛ با یک لمس وصل شوید.", "Your last method is ready; one tap connects it.")
                                else tr("یکی از روش‌های اتصال پایین را انتخاب کنید.", "Pick one of the connection methods below."),
                                color = DuskTheme.muted, style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.align(Alignment.CenterStart),
                            )
                        }
                    }
                    Spacer(Modifier.width(12.dp))
                    PowerButton(
                        state = when {
                            conn.up -> 2
                            conn.busy -> 1
                            else -> 0
                        },
                        enabled = !editMode && (conn.busy || last != null),
                        size = if (compact) 52.dp else 64.dp,
                        onClick = {
                            if (conn.busy) WidgetConnect.disconnect(context) else connectLast()
                        },
                    )
                }
                if (conn.up) {
                    Spacer(Modifier.height(if (compact) 6.dp else 10.dp))
                    Footer(traffic)
                }
            }
        }
        // Physical top-left in both languages, where iOS puts it: TopEnd is the left in Persian.
        val rtl = LocalLayoutDirection.current == LayoutDirection.Rtl
        if (editMode) RemoveBadge(onRemove, Modifier.align(if (rtl) Alignment.TopEnd else Alignment.TopStart))
    }
}

/** The method last connected, from the app's own record of it -- what «اتصال» brings back. */
private fun lastMethod(context: android.content.Context): String? = runCatching {
    when (val r = com.mlmvpn.scanner.data.LastEngine.read(context)) {
        is com.mlmvpn.scanner.data.LastEngine.Record.Tunnel -> r.transportId
        is com.mlmvpn.scanner.data.LastEngine.Record.OpenVpn -> "openvpn"
        is com.mlmvpn.scanner.data.LastEngine.Record.Xray -> ActiveEngines.homeOfNode(context, r.nodeId)
        null -> null
    }
}.getOrNull()?.takeIf { WidgetMethods.isMethod(it) }

@Composable
private fun Header(conn: WidgetLive.Conn, app: HomeApp?, compact: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (app != null) AppIconTile(app = app, size = if (compact) 34.dp else 40.dp) else Box(Modifier.size(40.dp))
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                app?.let { WidgetPainter.labelOf(it) } ?: "MLMVPN",
                color = DuskTheme.onSurface,
                style = (if (compact) MaterialTheme.typography.titleMedium else MaterialTheme.typography.titleLarge)
                    .copy(fontWeight = FontWeight.Bold),
                maxLines = 1, overflow = TextOverflow.Ellipsis,
            )
            val sub = when {
                conn.up -> conn.country?.let { WidgetFormat.flag(it) + " " + WidgetFormat.countryName(it) }
                    ?: tr("در حال یافتن کشور خروج…", "Finding the exit country…")
                conn.busy -> tr("در حال اتصال", "Connecting")
                app != null -> tr("آخرین روش استفاده‌شده", "Last method used")
                else -> tr("هنوز اتصالی برقرار نشده", "Not connected yet")
            }
            Text(sub, color = DuskTheme.muted, style = MaterialTheme.typography.bodySmall, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        StatusPill(conn)
    }
}

@Composable
private fun StatusPill(conn: WidgetLive.Conn) {
    // The session clock's tick, kept down here: once a second it recomposes this pill, not the
    // whole widget around the chart.
    val now = remember { mutableLongStateOf(System.currentTimeMillis()) }
    LaunchedEffect(conn.up, conn.since) {
        while (conn.up) {
            now.longValue = System.currentTimeMillis()
            delay(1_000)
        }
    }
    val (color, text) = when {
        conn.up -> DuskTheme.success to (tr("متصل", "Connected") + " " + WidgetFormat.clock(now.longValue - conn.since))
        conn.busy -> DuskTheme.secondary to tr("در حال اتصال", "Connecting")
        else -> DuskTheme.muted to tr("قطع", "Off")
    }
    DuskPill(text = text, indicator = color)
}

@Composable
private fun Footer(traffic: WidgetLive.Traffic) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Stat("↓", WidgetFormat.rate(traffic.rxRate), com.mlmvpn.scanner.ui.theme.DuskTheme.accent)
        Stat("↑", WidgetFormat.rate(traffic.txRate), com.mlmvpn.scanner.ui.theme.DuskTheme.blueGray)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(tr("مصرف نشست ", "Session "), color = DuskTheme.muted, style = MaterialTheme.typography.bodySmall)
            Text(WidgetFormat.bytes(traffic.rx + traffic.tx), color = DuskTheme.onSurface, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold).merge(tabular()))
        }
    }
}

@Composable
private fun Stat(arrow: String, value: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(arrow, color = color, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
        Spacer(Modifier.width(4.dp))
        Text(value, color = DuskTheme.onSurface, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold).merge(tabular()), maxLines = 1)
    }
}

/** Tabular figures: numbers that change every second keep their width, so nothing beside them shuffles sideways. */
@Composable
private fun tabular() = LocalTextStyle.current.copy(fontFeatureSettings = "tnum")

/**
 * The live chart: a minute of download (filled) and upload (a thinner line), newest at the right.
 *
 * It scrolls on the display's own clock. Every frame works out how far into its second the newest
 * sample is ([WidgetLive.Traffic.atNanos] against the frame time, one time base), so the line
 * glides one step left per sample, the newest point slides in from past the right edge, and the
 * oldest slides out past the left one. Read in the draw phase: a redraw, never a recomposition.
 *
 * It used to restart an animation from a LaunchedEffect when a sample arrived. That effect runs
 * only after the frame that first draws the new sample, so that one frame showed the line a whole
 * step too far left -- a twitch once a second. The scale eases between levels for the same reason
 * instead of snapping to each new peak.
 */
@Composable
private fun LiveChart(traffic: WidgetLive.Traffic, down: Color, up: Color, modifier: Modifier) {
    // A flat line at zero looks the same at every offset: no frames are spent on an idle link.
    val moving = traffic.down.any { it > 0L } || traffic.up.any { it > 0L }
    val frameNanos = remember { mutableLongStateOf(0L) }
    LaunchedEffect(moving) {
        while (moving) withFrameNanos { frameNanos.longValue = it }
    }
    val ceiling by animateFloatAsState(
        targetValue = chartCeiling(traffic),
        animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing),
        label = "chart scale",
    )
    val gridLineColor = DuskTheme.primary.copy(alpha = 0.12f)
    Canvas(modifier = modifier.clip(DuskTheme.chartShape)) {
        val w = size.width
        val h = size.height
        // A faint baseline grid.
        for (i in 1..2) {
            val y = h * i / 3f
            drawLine(gridLineColor, Offset(0f, y), Offset(w, y), strokeWidth = 1f)
        }
        val n = WidgetLive.HISTORY
        val top = ceiling
        val step = w / (n - 1)
        // One step right of its place as the sample lands, at its place a beat later. A late beat
        // carries the glide on a little rather than stopping it dead.
        val progress = if (traffic.atNanos == 0L || frameNanos.longValue == 0L) 1f
            else ((frameNanos.longValue - traffic.atNanos) / 1_000_000f / WidgetLive.TICK_MS).coerceIn(0f, 1.1f)
        val shift = (1f - progress) * step
        // Charts read left to right in any language: time runs that way on every meter.
        fun path(values: LongArray, close: Boolean): Path {
            val p = Path()
            if (values.size < 2) return p
            val startX = w - step * (values.size - 1) + shift
            var px = 0f
            var py = 0f
            values.forEachIndexed { i, v ->
                val x = startX + step * i
                val y = h - (v / top) * h
                if (i == 0) p.moveTo(x, y) else {
                    val mx = (px + x) / 2f
                    p.cubicTo(mx, py, mx, y, x, y)
                }
                px = x; py = y
            }
            if (close) {
                p.lineTo(px, h)
                p.lineTo(startX, h)
                p.close()
            }
            return p
        }
        if (traffic.down.size >= 2) {
            drawPath(path(traffic.down, true), Brush.verticalGradient(listOf(down.copy(alpha = 0.38f), Color.Transparent)))
            drawPath(path(traffic.down, false), down, style = Stroke(width = 2.4.dp.toPx(), cap = StrokeCap.Round))
        } else {
            drawLine(down.copy(alpha = 0.35f), Offset(0f, h - 2f), Offset(w, h - 2f), strokeWidth = 2f)
        }
        if (traffic.up.size >= 2) {
            drawPath(path(traffic.up, false), up.copy(alpha = 0.9f), style = Stroke(width = 1.6.dp.toPx(), cap = StrokeCap.Round))
        }
    }
}

/**
 * The chart's top: the peak with some headroom, rounded up to the next of a few round steps, so
 * the scale moves in clear jumps -- eased by the caller -- instead of every time the peak shifts.
 * A floor of 1 KB/s keeps a keepalive from filling the chart as if it were a download.
 */
private fun chartCeiling(traffic: WidgetLive.Traffic): Float {
    var peak = 0L
    traffic.down.forEach { peak = max(peak, it) }
    traffic.up.forEach { peak = max(peak, it) }
    val want = max(peak * 1.15f, 1024f)
    val magnitude = 10.0.pow(floor(log10(want.toDouble()))).toFloat()
    for (m in CEILING_STEPS) if (m * magnitude >= want) return m * magnitude
    return 10f * magnitude
}

private val CEILING_STEPS = floatArrayOf(1f, 1.5f, 2f, 3f, 4f, 5f, 6f, 8f, 10f)

/** 0 idle (connect), 1 connecting (cancel), 2 connected (disconnect). */
@Composable
private fun PowerButton(state: Int, enabled: Boolean, size: Dp, onClick: () -> Unit) {
    val dusk by androidx.compose.animation.animateColorAsState(
        targetValue = when (state) {
            2 -> com.mlmvpn.scanner.ui.theme.DuskTheme.accent
            1 -> com.mlmvpn.scanner.ui.theme.DuskTheme.secondary
            else -> com.mlmvpn.scanner.ui.theme.DuskTheme.primary
        },
        animationSpec = tween(600, easing = FastOutSlowInEasing), label = "dusk connect color",
    )
    val progress by animateFloatAsState(
        targetValue = when (state) { 2 -> 1f; 1 -> 0.5f; else -> 0f },
        animationSpec = tween(600, easing = FastOutSlowInEasing), label = "sun moon transition",
    )
    val glyphColor = if (state == 0 && !Appearance.isDark()) DuskTheme.white else DuskTheme.navyNight
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(size)
                .shadow(if (state == 2) 14.dp else 5.dp, DuskTheme.circleShape, clip = false, ambientColor = dusk, spotColor = dusk)
                .clip(DuskTheme.circleShape)
                .background(Brush.verticalGradient(listOf(dusk.copy(alpha = if (enabled) 0.96f else 0.48f), dusk.copy(alpha = 0.78f))))
                .pointerInput(enabled) { detectTapGestures { if (enabled) onClick() } },
            contentAlignment = Alignment.Center,
        ) {
            Canvas(modifier = Modifier.size(size * 0.62f)) {
                val glyph = glyphColor
                val r = this.size.minDimension * 0.31f
                val stroke = (r * 0.105f).coerceAtLeast(1.dp.toPx())
                // A small sun becomes a crescent as the connection settles, over a gentle 600 ms.
                if (progress < 0.99f) {
                    val sunAlpha = (1f - progress).coerceIn(0f, 1f)
                    val rayInner = r * 1.42f
                    val rayOuter = r * (1.65f - 0.18f * progress)
                    repeat(8) { i ->
                        val angle = Math.toRadians(i * 45.0)
                        drawLine(
                            glyph.copy(alpha = sunAlpha),
                            Offset(center.x + kotlin.math.cos(angle).toFloat() * rayInner, center.y + kotlin.math.sin(angle).toFloat() * rayInner),
                            Offset(center.x + kotlin.math.cos(angle).toFloat() * rayOuter, center.y + kotlin.math.sin(angle).toFloat() * rayOuter),
                            strokeWidth = stroke, cap = StrokeCap.Round,
                        )
                    }
                    drawCircle(glyph.copy(alpha = sunAlpha), radius = r * (1f - 0.12f * progress), center = center)
                }
                if (progress > 0f) {
                    val moon = Path().apply {
                        moveTo(center.x, center.y - r)
                        cubicTo(center.x + r * 1.12f, center.y - r * 0.35f, center.x + r * 0.88f, center.y + r * 0.72f, center.x, center.y + r)
                        cubicTo(center.x + r * 0.42f, center.y + r * 0.18f, center.x + r * 0.42f, center.y - r * 0.36f, center.x, center.y - r)
                        close()
                    }
                    drawPath(moon, glyph.copy(alpha = progress))
                }
                if (state == 1) {
                    drawCircle(glyph.copy(alpha = 0.7f), radius = r * 1.85f, center = center, style = Stroke(width = stroke * 0.7f))
                }
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(
            when (state) {
                2 -> tr("قطع", "Disconnect")
                1 -> tr("لغو", "Cancel")
                else -> tr("اتصال", "Connect")
            },
            color = DuskTheme.onSurface.copy(alpha = if (enabled) 0.9f else 0.45f),
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
        )
    }
}

/** A quiet, non-raster dusk landscape tucked into the home connection card. */
@Composable
private fun DuskThemeHills(modifier: Modifier = Modifier) {
    val night = Appearance.isDark()
    val sky by androidx.compose.animation.animateColorAsState(
        if (night) com.mlmvpn.scanner.ui.theme.DuskTheme.nightBlue.copy(alpha = 0.24f)
        else com.mlmvpn.scanner.ui.theme.DuskTheme.rose.copy(alpha = 0.30f),
        tween(600, easing = FastOutSlowInEasing), label = "dusk landscape sky",
    )
    val hill by androidx.compose.animation.animateColorAsState(
        if (night) DuskTheme.secondary.copy(alpha = 0.23f)
        else com.mlmvpn.scanner.ui.theme.DuskTheme.coral.copy(alpha = 0.22f),
        tween(600, easing = FastOutSlowInEasing), label = "dusk landscape hills",
    )
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        drawCircle(sky, radius = h * 0.27f, center = Offset(w * 0.82f, h * 0.28f))
        val far = Path().apply {
            moveTo(0f, h * 0.68f)
            cubicTo(w * 0.20f, h * 0.48f, w * 0.34f, h * 0.82f, w * 0.55f, h * 0.62f)
            cubicTo(w * 0.72f, h * 0.48f, w * 0.82f, h * 0.74f, w, h * 0.56f)
            lineTo(w, h); lineTo(0f, h); close()
        }
        drawPath(far, hill)
        val near = Path().apply {
            moveTo(0f, h * 0.82f)
            cubicTo(w * 0.26f, h * 0.63f, w * 0.48f, h * 0.98f, w * 0.70f, h * 0.77f)
            cubicTo(w * 0.83f, h * 0.67f, w * 0.90f, h * 0.83f, w, h * 0.72f)
            lineTo(w, h); lineTo(0f, h); close()
        }
        drawPath(near, hill.copy(alpha = hill.alpha * 0.72f))
    }
}

/** iOS's «−» on a widget while the board is being rearranged: removes it from the board. */
@Composable
private fun RemoveBadge(onRemove: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .offset(x = (-6).dp, y = (-6).dp)
            .size(24.dp)
            .shadow(3.dp, DuskTheme.circleShape)
            .clip(DuskTheme.circleShape)
            .background(DuskTheme.disabled)
            .pointerInput(Unit) { detectTapGestures { onRemove() } },
        contentAlignment = Alignment.Center,
    ) {
        Box(Modifier.width(11.dp).height(2.5.dp).clip(DuskTheme.controlShape).background(DuskTheme.white))
    }
}
