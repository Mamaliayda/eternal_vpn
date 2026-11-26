package com.mlmvpn.scanner.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mlmvpn.scanner.ui.home.Appearance
import com.mlmvpn.scanner.ui.settings.Ios

// Soft Dusk is the intended design system. These legacy names are compatibility adapters for
// older feature screens; they deliberately remain unchanged until each consumer migrates, since
// blindly swapping a token while its layout still assumes white-on-glass text can break contrast.
// New and migrated UI should consume DuskTheme, DuskCard and DuskPill directly.

/** Page and inner-panel backgrounds. Deeper than a card, so nested boxes still read as nested. */
val BgDark: Color
    @Composable get() = if (Appearance.isDark()) {
        Color.Black.copy(alpha = 0.26f)
    } else {
        Color.Black.copy(alpha = 0.13f)
    }

/** Cards and raised panels. The same glass value the Settings groups use. */
val SurfaceDark: Color
    @Composable get() = Ios.CardOverWallpaper

/** Dialogs and modal sheets. See [Ios.DialogSurface] for why this is not [SurfaceDark]. */
val DialogSurface: Color
    @Composable get() = Ios.DialogSurface

/** Hairlines and outlines. Light on glass rather than dark on a sheet. */
val BorderDark: Color
    @Composable get() = if (Appearance.isDark()) {
        Color.White.copy(alpha = 0.14f)
    } else {
        Color.White.copy(alpha = 0.24f)
    }

val Primary: Color
    @Composable get() = Ios.Blue

val PrimaryLight: Color
    @Composable get() = Color(0xFFAECBFA)

val TextPrimary: Color
    @Composable get() = Ios.Label

val TextMuted: Color
    @Composable get() = Ios.SecondaryLabel

/** The faintest readable tier: timestamps, hints, disabled captions. */
val TextDim: Color
    @Composable get() = Color.White.copy(alpha = 0.45f)

val GreenOk: Color
    @Composable get() = Ios.Green

val RedError: Color
    @Composable get() = Ios.Red

val YellowWarn: Color
    @Composable get() = Ios.Yellow

val ConnectedRed: Color
    @Composable get() = Ios.Pink

@Composable
fun MlmVpnTheme(content: @Composable () -> Unit) {
    val dark = Appearance.isDark()
    val snackbarHostState = remember { SnackbarHostState() }
    MaterialTheme(
        colorScheme = duskColorScheme(dark),
        typography = iranSansTypography(MaterialTheme.typography),
    ) {
        CompositionLocalProvider(LocalDuskSnackbarHostState provides snackbarHostState) {
            Box(Modifier.fillMaxSize()) {
                content()
                DuskSnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .navigationBarsPadding()
                        .padding(horizontal = DuskTheme.space4, vertical = DuskTheme.space3),
                )
            }
        }
    }
}
