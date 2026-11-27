package com.mlmvpn.scanner.ui.theme

import com.mlmvpn.scanner.ui.theme.DuskTheme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mlmvpn.scanner.ui.home.Appearance

// Compatibility aliases keep older screen call sites source-compatible while resolving every
// presentation color through the central Soft Dusk semantic palette.
val BgDark: Color @Composable get() = DuskTheme.background
val SurfaceDark: Color @Composable get() = DuskTheme.surface
val DialogSurface: Color @Composable get() = DuskTheme.raisedSurface
val BorderDark: Color @Composable get() = DuskTheme.outline
val Primary: Color @Composable get() = DuskTheme.primary
val PrimaryLight: Color @Composable get() = DuskTheme.primary
val TextPrimary: Color @Composable get() = DuskTheme.onSurface
val TextMuted: Color @Composable get() = DuskTheme.muted
val TextDim: Color @Composable get() = DuskTheme.muted
val GreenOk: Color @Composable get() = DuskTheme.success
val RedError: Color @Composable get() = DuskTheme.error
val YellowWarn: Color @Composable get() = DuskTheme.accent
val ConnectedRed: Color @Composable get() = DuskTheme.accent

@Composable
fun MlmVpnTheme(content: @Composable () -> Unit) {
    val dark = Appearance.isDark()
    val snackbarHostState = remember { SnackbarHostState() }
    MaterialTheme(
        colorScheme = duskColorScheme(dark),
        typography = iranSansTypography(MaterialTheme.typography),
    ) {
        CompositionLocalProvider(
            LocalDuskSnackbarHostState provides snackbarHostState,
            LocalDuskSnackbarScope provides rememberCoroutineScope(),
        ) {
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
