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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mlmvpn.scanner.R
import com.mlmvpn.scanner.ui.home.Appearance

/** Soft Dusk is the shared visual language for every migrated screen and control. */
object DuskTheme {
    val blush = Color(0xFFF6DCE4)
    val rose = Color(0xFFE9BACB)
    val coral = Color(0xFFE98583)
    val navy = Color(0xFF293451)
    val blueGray = Color(0xFF71819C)
    val cream = Color(0xFFFFF8F1)
    val white = Color(0xFFFFFFFF)
    /** Dedicated neutral backing keeps generated QR modules scannable in either appearance. */
    val qrSurface = Color.White
    /** Cloudflare's brand orange, centralized rather than embedded in individual screens. */
    val cloudflareOrange = Color(0xFFF6821F)
    val lavenderNight = Color(0xFF27263E)
    val navyNight = Color(0xFF191D32)
    val nightCard = Color(0xFF34334D)
    val nightCoral = Color(0xFFFFA39B)
    val nightBlue = Color(0xFF9BB6E3)
    val lightText = Color(0xFF293451)
    val nightText = Color(0xFFF8F2FA)
    val nightMuted = Color(0xFFC1BED0)
    val lightMuted = Color(0xFF72788B)
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

    val background: Color @Composable get() = if (Appearance.isDark()) navyNight else blush
    val surface: Color @Composable get() = if (Appearance.isDark()) nightCard else cream
    val raisedSurface: Color @Composable get() = if (Appearance.isDark()) lavenderNight else white
    val primary: Color @Composable get() = if (Appearance.isDark()) nightBlue else navy
    val accent: Color @Composable get() = if (Appearance.isDark()) nightCoral else coral
    val secondary: Color @Composable get() = if (Appearance.isDark()) Color(0xFFA9B1CE) else blueGray
    val onSurface: Color @Composable get() = if (Appearance.isDark()) nightText else lightText
    val muted: Color @Composable get() = if (Appearance.isDark()) nightMuted else lightMuted
    val onAccent: Color @Composable get() = if (Appearance.isDark()) navyNight else navy
    val onError: Color @Composable get() = if (Appearance.isDark()) navyNight else white
    val success: Color @Composable get() = if (Appearance.isDark()) Color(0xFF9BCFB4) else Color(0xFF43765A)
    val disabled: Color @Composable get() = if (Appearance.isDark()) Color(0xFF9B98AD) else Color(0xFF8E93A0)
    val outline: Color @Composable get() = if (Appearance.isDark()) nightOutline else lightOutline
    val error: Color @Composable get() = if (Appearance.isDark()) nightError else lightError
    val indigo: Color @Composable get() = if (Appearance.isDark()) Color(0xFFB3A9E5) else Color(0xFF6E69A8)
    val purple: Color @Composable get() = if (Appearance.isDark()) Color(0xFFD4B6E6) else Color(0xFF8F659F)

    val space1 = 4.dp
    val space2 = 8.dp
    val space3 = 12.dp
    val space4 = 16.dp
    val space5 = 24.dp
    val space6 = 32.dp

    val cardShape = RoundedCornerShape(28.dp)
    val controlShape = RoundedCornerShape(50)
    val tileShape = RoundedCornerShape(22.dp)
    val glyphShape = RoundedCornerShape(12.dp)
    val circleShape = CircleShape
    val chartShape = RoundedCornerShape(16.dp)
    val navIconSize = 44.dp
    val bottomNavInset = 128.dp
    val iconSize = 22.dp
    val cardElevation = 8.dp
    val focusCornerRadius = 24
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
) {
    val fill = if (selected) DuskTheme.accent else DuskTheme.raisedSurface
    val ink = if (selected) DuskTheme.onAccent else DuskTheme.primary
    Box(
        modifier = modifier
            .defaultMinSize(minHeight = if (compact) 32.dp else DuskTheme.navIconSize)
            .clip(DuskTheme.controlShape)
            .background(fill)
            .border(1.dp, DuskTheme.accent.copy(alpha = if (selected) 0.65f else 0.28f), DuskTheme.controlShape)
            .then(onClick?.let { Modifier.clickable(onClick = it) } ?: Modifier)
            .padding(horizontal = DuskTheme.space4, vertical = if (compact) DuskTheme.space1 else DuskTheme.space2),
        contentAlignment = Alignment.Center,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(DuskTheme.space2), verticalAlignment = Alignment.CenterVertically) {
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
        tertiary = DuskTheme.coral, onTertiary = DuskTheme.white,
        background = DuskTheme.blush, onBackground = DuskTheme.lightText,
        surface = DuskTheme.cream, onSurface = DuskTheme.lightText,
        surfaceVariant = DuskTheme.lightVariant, onSurfaceVariant = DuskTheme.lightVariantText,
        outline = DuskTheme.lightOutline, error = DuskTheme.lightError, onError = DuskTheme.white,
    )
}
