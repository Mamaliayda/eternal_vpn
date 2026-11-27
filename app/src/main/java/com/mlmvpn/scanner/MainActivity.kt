package com.mlmvpn.scanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.appcompat.app.AppCompatActivity

import android.util.Log

import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.asPaddingValues
import androidx.activity.enableEdgeToEdge
import androidx.activity.SystemBarStyle

class MainActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: android.content.Context) {
        com.mlmvpn.scanner.utils.AppLocaleManager.init(newBase)
        super.attachBaseContext(com.mlmvpn.scanner.utils.AppLocaleManager.wrapContext(newBase))
    }

    /**
     * A remote's key press that Compose cannot place. On Android TV boxes a D-pad key arriving while
     * the focused node is being removed (a page closing under the remote) throws "Event can't be
     * processed because we do not have an active focus target" from inside Compose and closed the
     * app; dropped instead, the next press finds the new page's focus.
     */
    override fun dispatchKeyEvent(event: android.view.KeyEvent): Boolean = try {
        super.dispatchKeyEvent(event)
    } catch (e: IllegalStateException) {
        if (e.message?.contains("focus target") == true) false else throw e
    }

    /**
     * A screen the device does not have. Some Android TV boxes, projectors and custom ROMs ship
     * without the system's VPN permission dialog (com.android.vpndialogs), so the intent of
     * VpnService.prepare() -- launched from here by every «connect» -- closed the app (1.2.42
     * reports #2371, #2373). It says so instead.
     */
    @Deprecated("Deprecated in Java")
    override fun startActivityForResult(intent: android.content.Intent, requestCode: Int, options: Bundle?) {
        try {
            @Suppress("DEPRECATION")
            super.startActivityForResult(intent, requestCode, options)
        } catch (e: android.content.ActivityNotFoundException) {
            Log.w("MainActivity", "no activity for ${intent.component ?: intent.action}")
            android.widget.Toast.makeText(this, getString(R.string.screen_missing_on_device), android.widget.Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        com.mlmvpn.scanner.utils.AppLocaleManager.init(this)
        com.mlmvpn.scanner.ui.home.Appearance.init(this)
        // Both bars stay transparent over the full-bleed Dusk backdrop. Their icon contrast is
        // updated from the app's selected appearance below, not the phone's unrelated system mode.
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
        )
        Log.d("LanguageSwitch", "MainActivity.onCreate called")
        // Seeds the wallpaper choice before the first frame, so the backdrop the user picked is
        // the one they see on launch rather than a flash of the default.
        com.mlmvpn.scanner.ui.home.Wallpapers.init(this)

        com.mlmvpn.scanner.engines.subgenerator.SubGenManager(this).startAutoSync()

        // A home-screen widget asked for one tile's page (see WidgetOpen). Only on a real start: a
        // rotation recreates the activity with the same intent, and the request was taken off it.
        if (savedInstanceState == null) com.mlmvpn.scanner.widget.WidgetOpen.offer(intent)
        // Widgets speak the app's language, which may have just changed: redraw them once.
        com.mlmvpn.scanner.widget.WidgetUpdater.refreshAllAsync(this)

        setContent {
            // Appearance is resolved once and drives the Material surfaces as well as Soft Dusk
            // tokens. Individual feature layouts are being migrated route by route; this root
            // scheme is the foundation, not a claim that every legacy screen is finished.
            com.mlmvpn.scanner.ui.theme.MlmVpnTheme {
                val darkAppearance = com.mlmvpn.scanner.ui.home.Appearance.isDark()
                androidx.compose.runtime.SideEffect {
                    val bars = androidx.core.view.WindowCompat.getInsetsController(window, window.decorView)
                    bars.isAppearanceLightStatusBars = !darkAppearance
                    bars.isAppearanceLightNavigationBars = !darkAppearance
                }
                val systemBars = androidx.compose.foundation.layout.WindowInsets.systemBars.asPaddingValues()
                // The app-wide text size rides on the DENSITY's fontScale rather than on the
                // Typography. It has to: nearly every Text in this app passes an explicit
                // `fontSize = 14.sp`, and an explicit size overrides the type scale completely.
                // fontScale multiplies every sp in the tree, so hardcoded sizes scale as well.
                val baseDensity = androidx.compose.ui.platform.LocalDensity.current
                androidx.compose.runtime.CompositionLocalProvider(
                    com.mlmvpn.scanner.ui.LocalSystemBottomPadding provides systemBars.calculateBottomPadding(),
                    com.mlmvpn.scanner.ui.LocalSystemTopPadding provides systemBars.calculateTopPadding(),
                    androidx.compose.ui.platform.LocalDensity provides androidx.compose.ui.unit.Density(
                        density = baseDensity.density,
                        // Following the system means leaving its fontScale exactly alone,
                        // not multiplying it by 100%.
                        fontScale = if (com.mlmvpn.scanner.ui.home.Appearance.textScaleAuto) {
                            baseDensity.fontScale
                        } else {
                            baseDensity.fontScale *
                                (com.mlmvpn.scanner.ui.home.Appearance.textScale / 100f)
                        },
                    ),
                ) {
                    com.mlmvpn.scanner.utils.LocaleProvider {
                        // No systemBarsPadding() here on purpose. The home screen's backdrop has
                        // to run edge to edge -- under the clock and signal icons at the top and
                        // under the navigation bar at the bottom -- so that the app opens with a
                        // full-bleed wallpaper and no cut line across it. Insetting at the root
                        // would letterbox the wallpaper into a black-topped strip.
                        //
                        // The inset is applied one level down instead, by AppScreen's feature
                        // host. Full-screen overlays (the emergency screens, About, Help, the
                        // anti-sanction screen) already apply LocalSystemTopPadding themselves,
                        // so they keep working -- and stop being padded twice, which is what was
                        // quietly happening while the root inset was also in play.
                        var windowSizePx by androidx.compose.runtime.remember {
                            androidx.compose.runtime.mutableStateOf(androidx.compose.ui.unit.IntSize.Zero)
                        }
                        androidx.compose.foundation.layout.Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .onSizeChanged { windowSizePx = it }
                                .background(com.mlmvpn.scanner.ui.theme.DuskTheme.background)
                        ) {
                            androidx.compose.runtime.CompositionLocalProvider(
                                com.mlmvpn.scanner.ui.LocalWindowSizePx provides windowSizePx
                            ) {
                            com.mlmvpn.scanner.ui.home.HomeWallpaper(
                                wallpaperId = com.mlmvpn.scanner.ui.home.Wallpapers.current,
                                blurPercent = com.mlmvpn.scanner.ui.home.Wallpapers.blur,
                            )
                            // AppScreen is composed underneath from the start, so the tab is
                            // already warm and its first fetch already in flight by the time
                            // the intro clears — the animation costs no perceived delay.
                            val splashDone = androidx.compose.runtime.remember {
                                androidx.compose.runtime.mutableStateOf(
                                    com.mlmvpn.scanner.ui.IntroState.played
                                )
                            }
                            com.mlmvpn.scanner.ui.AppScreen()
                            if (!splashDone.value) {
                                com.mlmvpn.scanner.ui.SplashScreen(
                                    onFinished = {
                                        splashDone.value = true
                                        com.mlmvpn.scanner.ui.IntroState.played = true
                                    }
                                )
                            }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * A widget opening a tile while the app is already up: the request is handed to the screen
     * that is there instead of rebuilding the activity (WidgetOpen asks for single-top).
     */
    override fun onNewIntent(intent: android.content.Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        com.mlmvpn.scanner.widget.WidgetOpen.offer(intent)
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("LanguageSwitch", "MainActivity.onConfigurationChanged called. New Locale: ${newConfig.locales}")
    }
}
