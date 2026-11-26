package com.mlmvpn.scanner.ui.theme

import androidx.compose.material3.Typography

/** Backwards-compatible name for the single Soft Dusk font family. */
val IranSans = DuskThemeFont

/** Material's complete type scale, consistently using Vazirmatn across Persian and Latin scripts. */
fun iranSansTypography(base: Typography): Typography = Typography(
    displayLarge = base.displayLarge.copy(fontFamily = IranSans),
    displayMedium = base.displayMedium.copy(fontFamily = IranSans),
    displaySmall = base.displaySmall.copy(fontFamily = IranSans),
    headlineLarge = base.headlineLarge.copy(fontFamily = IranSans),
    headlineMedium = base.headlineMedium.copy(fontFamily = IranSans),
    headlineSmall = base.headlineSmall.copy(fontFamily = IranSans),
    titleLarge = base.titleLarge.copy(fontFamily = IranSans),
    titleMedium = base.titleMedium.copy(fontFamily = IranSans),
    titleSmall = base.titleSmall.copy(fontFamily = IranSans),
    bodyLarge = base.bodyLarge.copy(fontFamily = IranSans),
    bodyMedium = base.bodyMedium.copy(fontFamily = IranSans),
    bodySmall = base.bodySmall.copy(fontFamily = IranSans),
    labelLarge = base.labelLarge.copy(fontFamily = IranSans),
    labelMedium = base.labelMedium.copy(fontFamily = IranSans),
    labelSmall = base.labelSmall.copy(fontFamily = IranSans),
)
