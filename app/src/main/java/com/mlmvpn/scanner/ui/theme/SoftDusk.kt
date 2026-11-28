package com.mlmvpn.scanner.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mlmvpn.scanner.R
import com.mlmvpn.scanner.ui.home.Appearance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/** Soft Dusk is the shared visual language for every migrated screen and control. */
object DuskTheme {
    val blush = Color(0xFFF6DCE4)
    val rose = Color(0xFFE9BACB)
    val coral = Color(0xFFE98583)
    val navy = Color(0xFF293451)
    val blueGray = Color(0xFF58657A)
    val cream = Color(0xFFFFF8F1)
    val white = Color(0xFFFFFFFF)
    val transparent = Color.Transparent
    /** Fixed-width family reserved for addresses, ports, and other exact technical values. */
    val monoFontFamily = FontFamily.Monospace
    /** Dedicated neutral backing keeps generated QR modules scannable in either appearance. */
    val qrSurface = Color.White
    /** Brand colors used only inside product identity and branded link illustrations. */
    val cloudflareOrange = Color(0xFFF6821F)
    val cloudflareOrangeLight = Color(0xFFFFA94D)
    val telegramBlue = Color(0xFF61A8DE)
    val brandCyan = Color(0xFF18BFFB)
    val brandBlue = Color(0xFF2072F3)
    val appIconPalette = listOf(
        Color(0xFF0A84FF), Color(0xFF30D158), Color(0xFFFF9F0A), Color(0xFFFF375F),
        Color(0xFFBF5AF2), Color(0xFF64D2FF), Color(0xFFFF6482), Color(0xFF5E5CE6),
    )
    val lavenderNight = Color(0xFF27263E)
    val navyNight = Color(0xFF191D32)
    val nightCard = Color(0xFF34334D)
    val nightCoral = Color(0xFFFFA39B)
    val nightBlue = Color(0xFF9BB6E3)
    val lightText = Color(0xFF293451)
    val nightText = Color(0xFFF8F2FA)
    val nightMuted = Color(0xFFC1BED0)
    val lightMuted = Color(0xFF595F72)
    val nightVariantText = Color(0xFFD0CDE0)
    val lightVariant = Color(0xFFF0E5E8)
    val lightVariantText = Color(0xFF515B70)
    val nightOutline = Color(0xFF77758E)
    val lightOutline = Color(0xFF9BA5B6)
    val nightError = Color(0xFFFFB4AB)
    val lightError = Color(0xFFB3261E)
    val nightErrorText = Color(0xFF690005)
    val nightTertiary = Color(0xFFD8B5E2)
    val nightOnPrimaryContainer = Color(0xFFFFE0DB)
    val nightMedalGold = Color(0xFFFFD166)
    val lightMedalGold = Color(0xFF8A5A00)
    val nightMedalSilver = Color(0xFFD6D7E0)
    val lightMedalSilver = Color(0xFF5A6170)
    val nightMedalBronze = Color(0xFFE8B08A)
    val lightMedalBronze = Color(0xFF8E4F2C)

    val background: Color @Composable get() = if (Appearance.isDark()) navyNight else blush
    val surface: Color @Composable get() = if (Appearance.isDark()) nightCard else cream
    val raisedSurface: Color @Composable get() = if (Appearance.isDark()) lavenderNight else white
    val primary: Color @Composable get() = if (Appearance.isDark()) nightBlue else navy
    val accent: Color @Composable get() = if (Appearance.isDark()) nightCoral else coral
    val secondary: Color @Composable get() = if (Appearance.isDark()) Color(0xFFA9B1CE) else blueGray
    val onSurface: Color @Composable get() = if (Appearance.isDark()) nightText else lightText
    val muted: Color @Composable get() = if (Appearance.isDark()) nightMuted else lightMuted
    val onAccent: Color @Composable get() = if (Appearance.isDark()) navyNight else navy
    val onPrimary: Color @Composable get() = if (Appearance.isDark()) navyNight else white
    val onSuccess: Color @Composable get() = if (Appearance.isDark()) navyNight else white
    val onError: Color @Composable get() = if (Appearance.isDark()) navyNight else white
    val success: Color @Composable get() = if (Appearance.isDark()) Color(0xFF9BCFB4) else Color(0xFF43765A)
    val disabled: Color @Composable get() = if (Appearance.isDark()) Color(0xFF9B98AD) else Color(0xFF8E93A0)
    val outline: Color @Composable get() = if (Appearance.isDark()) nightOutline else lightOutline
    val error: Color @Composable get() = if (Appearance.isDark()) nightError else lightError
    val indigo: Color @Composable get() = if (Appearance.isDark()) Color(0xFFB3A9E5) else Color(0xFF6E69A8)
    val purple: Color @Composable get() = if (Appearance.isDark()) Color(0xFFD4B6E6) else Color(0xFF8F659F)
    val medalGold: Color @Composable get() = if (Appearance.isDark()) nightMedalGold else lightMedalGold
    val medalSilver: Color @Composable get() = if (Appearance.isDark()) nightMedalSilver else lightMedalSilver
    val medalBronze: Color @Composable get() = if (Appearance.isDark()) nightMedalBronze else lightMedalBronze

    val space1 = 4.dp
    val space2 = 8.dp
    val space3 = 12.dp
    val space4 = 16.dp
    val space5 = 24.dp
    val space6 = 32.dp
    val space1_5 = 6.dp
    val space2_5 = 10.dp
    val space3_5 = 14.dp
    val microSpace = 2.dp
    val hairlineSpace = 1.dp
    val separatorWidth = 0.5.dp
    val inlineIconSize = 18.dp
    val actionHeight = 52.dp
    val floatingActionClearance = 96.dp
    val progressStroke = 2.dp
    val actionBorderStroke = 1.5.dp
    val neutralBorderStroke = 1.dp
    val menuInset = 4.dp

    // Central typography dimensions used by legacy custom-rendered rows while they migrate to
    // semantic Material styles. Keeping exact size values here prevents per-screen style drift.
    val textSize9 = 9.sp
    val textSize10 = 10.sp
    val textSize11 = 11.sp
    val textSize11_5 = 11.5.sp
    val textSize12 = 12.sp
    val textSize12_5 = 12.5.sp
    val textSize13 = 13.sp
    val textSize13_5 = 13.5.sp
    val textSize14 = 14.sp
    val textSize15 = 15.sp
    val textSize16 = 16.sp
    val textSize17 = 17.sp
    val textSize18 = 18.sp
    val textSize19 = 19.sp
    val textSize20 = 20.sp
    val textSize21 = 21.sp
    val textSize22 = 22.sp
    val textSize23 = 23.sp
    val textSize24 = 24.sp
    val textSize25 = 25.sp
    val textSize26 = 26.sp
    val textSize28 = 28.sp
    val textSize30 = 30.sp
    val textSize32 = 32.sp
    val textSize34 = 34.sp
    val textSize36 = 36.sp
    val textSize68 = 68.sp
    val textSize38 = 38.sp
    val textSize40 = 40.sp
    val textSize42 = 42.sp

    // Corner-size tokens for the few visualizations that need exact geometry.
    val corner0 = 0.dp
    val corner1 = 1.dp
    val corner2 = 2.dp
    val corner3 = 3.dp
    val corner5 = 5.dp
    val corner6 = 6.dp
    val corner7 = 7.dp
    val corner8 = 8.dp
    val corner9 = 9.dp
    val corner10 = 10.dp
    val corner11 = 11.dp
    val corner12 = 12.dp
    val corner14 = 14.dp
    val corner15 = 15.dp
    val corner16 = 16.dp
    val corner18 = 18.dp
    val corner20 = 20.dp
    val corner22 = 22.dp
    val corner24 = 24.dp
    val corner28 = 28.dp
    val corner30 = 30.dp

    val cardShape = RoundedCornerShape(28.dp)
    val controlShape = RoundedCornerShape(50)
    val tileShape = RoundedCornerShape(22.dp)
    val glyphShape = RoundedCornerShape(12.dp)
    val squircleShape = RoundedCornerShape(percent = 26)
    val circleShape = CircleShape
    val chartShape = RoundedCornerShape(16.dp)
    val navIconSize = 44.dp
    val bottomNavInset = 128.dp
    val iconSize = 22.dp
    val cardElevation = 8.dp
    val tileElevation = 6.dp
    val badgeElevation = 2.dp
    val indicatorElevation = 3.dp
    val focusCornerRadius = 24
    /** Proportional geometry for the compact team-mark tile. */
    const val teamTileCornerFraction = 0.27f
    const val teamMarkScale = 0.4f
    const val appIconLetterScale = 0.45f
}

val LocalDuskSnackbarHostState = staticCompositionLocalOf<SnackbarHostState> {
    error("DuskSnackbarHostState is only available inside MlmVpnTheme")
}

/** Activity-theme scope keeps a queued notice alive when the screen that emitted it navigates away. */
val LocalDuskSnackbarScope = staticCompositionLocalOf<CoroutineScope> {
    error("DuskSnackbarScope is only available inside MlmVpnTheme")
}

/** A lifecycle-safe callback for click handlers, effects, and asynchronous UI work. */
@Composable
fun rememberDuskSnackbarAction(): (String) -> Unit {
    val hostState = LocalDuskSnackbarHostState.current
    val scope = LocalDuskSnackbarScope.current
    return remember(hostState, scope) {
        { message -> scope.launch { hostState.showSnackbar(message) } }
    }
}

/** Vazirmatn is used as a single bilingual family so mixed Persian, Latin and IP text remains consistent. */
val DuskThemeFont = FontFamily(
    Font(R.font.softdusk_vazirmatn_regular, FontWeight.Normal),
    Font(R.font.softdusk_vazirmatn_medium, FontWeight.Medium),
    Font(R.font.softdusk_vazirmatn_semibold, FontWeight.SemiBold),
    Font(R.font.softdusk_vazirmatn_bold, FontWeight.Bold),
)

@Composable
fun DuskCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = DuskTheme.cardShape,
    contentPadding: PaddingValues = PaddingValues(DuskTheme.space4),
    containerColor: Color = DuskTheme.surface,
    contentColor: Color = DuskTheme.onSurface,
    content: @Composable BoxScope.() -> Unit,
) {
    Surface(
        modifier = modifier.shadow(DuskTheme.cardElevation, shape, clip = false),
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = 0.dp,
    ) {
        Box(Modifier.padding(contentPadding), content = content)
    }
}

@Composable
fun DuskPill(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: (() -> Unit)? = null,
    indicator: Color? = null,
    busy: Boolean = false,
    compact: Boolean = false,
    enabled: Boolean = true,
    leadingContent: (@Composable () -> Unit)? = null,
) {
    val fill = when {
        !enabled -> DuskTheme.surface
        selected -> DuskTheme.accent
        else -> DuskTheme.raisedSurface
    }
    val ink = when {
        !enabled -> DuskTheme.muted
        selected -> DuskTheme.onAccent
        else -> DuskTheme.primary
    }
    val outline = when {
        !enabled -> DuskTheme.outline.copy(alpha = 0.30f)
        selected -> DuskTheme.accent.copy(alpha = 0.65f)
        else -> DuskTheme.accent.copy(alpha = 0.28f)
    }
    Box(
        modifier = modifier
            .defaultMinSize(minHeight = if (compact) 32.dp else DuskTheme.navIconSize)
            .clip(DuskTheme.controlShape)
            .background(fill)
            .border(1.dp, outline, DuskTheme.controlShape)
            .then(onClick?.let { Modifier.clickable(enabled = enabled, onClick = it) } ?: Modifier)
            .padding(horizontal = DuskTheme.space4, vertical = if (compact) DuskTheme.space1 else DuskTheme.space2),
        contentAlignment = Alignment.Center,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(DuskTheme.space2), verticalAlignment = Alignment.CenterVertically) {
            leadingContent?.invoke()
            if (busy) {
                CircularProgressIndicator(
                    modifier = Modifier.size(DuskTheme.space3),
                    color = ink,
                    strokeWidth = 2.dp,
                )
            } else {
                indicator?.let { dot ->
                    Box(Modifier.size(DuskTheme.space2).clip(DuskTheme.circleShape).background(dot))
                }
            }
            Text(
                text = text,
                color = ink,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
            )
        }
    }
}

@Composable
fun DuskSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
    ) { data ->
        Snackbar(
            snackbarData = data,
            shape = DuskTheme.cardShape,
            containerColor = DuskTheme.raisedSurface,
            contentColor = DuskTheme.onSurface,
            actionColor = DuskTheme.primary,
            dismissActionContentColor = DuskTheme.muted,
        )
    }
}

@Composable
fun duskColorScheme(dark: Boolean): ColorScheme = if (dark) {
    darkColorScheme(
        primary = DuskTheme.nightCoral, onPrimary = DuskTheme.navyNight,
        primaryContainer = DuskTheme.lavenderNight, onPrimaryContainer = DuskTheme.nightOnPrimaryContainer,
        secondary = DuskTheme.nightBlue, onSecondary = DuskTheme.navyNight,
        tertiary = DuskTheme.nightTertiary, onTertiary = DuskTheme.navyNight,
        background = DuskTheme.navyNight, onBackground = DuskTheme.nightText,
        surface = DuskTheme.nightCard, onSurface = DuskTheme.nightText,
        surfaceVariant = DuskTheme.lavenderNight, onSurfaceVariant = DuskTheme.nightVariantText,
        outline = DuskTheme.nightOutline, error = DuskTheme.nightError, onError = DuskTheme.nightErrorText,
    )
} else {
    lightColorScheme(
        primary = DuskTheme.navy, onPrimary = DuskTheme.white,
        primaryContainer = DuskTheme.rose, onPrimaryContainer = DuskTheme.navy,
        secondary = DuskTheme.blueGray, onSecondary = DuskTheme.white,
        tertiary = DuskTheme.coral, onTertiary = DuskTheme.navy,
        background = DuskTheme.blush, onBackground = DuskTheme.lightText,
        surface = DuskTheme.cream, onSurface = DuskTheme.lightText,
        surfaceVariant = DuskTheme.lightVariant, onSurfaceVariant = DuskTheme.lightVariantText,
        outline = DuskTheme.lightOutline, error = DuskTheme.lightError, onError = DuskTheme.white,
    )
}
