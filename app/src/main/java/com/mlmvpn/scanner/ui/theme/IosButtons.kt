package com.mlmvpn.scanner.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * The shared filled-glass action button. Pass the semantic Dusk accent that describes the action;
 * the restrained tint and rim identify the button without allowing it to outshine its surface.
 */
@Composable
fun iosButtonColors(accent: Color = DuskTheme.primary): ButtonColors = ButtonDefaults.buttonColors(
    containerColor = accent.copy(alpha = 0.22f),
    contentColor = DuskTheme.onSurface,
    disabledContainerColor = accent.copy(alpha = 0.08f),
    disabledContentColor = DuskTheme.disabled,
)

/** The rim paired with [iosButtonColors], softened when the action is disabled. */
@Composable
fun iosButtonBorder(accent: Color = DuskTheme.primary, enabled: Boolean = true): BorderStroke =
    BorderStroke(DuskTheme.actionBorderStroke, if (enabled) accent else DuskTheme.outline)

/** A neutral secondary action, leaving semantic accent for the primary choice. */
@Composable
fun iosNeutralButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
    containerColor = DuskTheme.surface.copy(alpha = 0.76f),
    contentColor = DuskTheme.onSurface,
    disabledContainerColor = DuskTheme.surface.copy(alpha = 0.42f),
    disabledContentColor = DuskTheme.disabled,
)

/** The neutral rim paired with [iosNeutralButtonColors]. */
@Composable
fun iosNeutralBorder(): BorderStroke = BorderStroke(DuskTheme.neutralBorderStroke, DuskTheme.outline)

/** A single appearance-aware treatment for filled and outlined text fields. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun iosFieldColors(): TextFieldColors = TextFieldDefaults.colors(
    focusedContainerColor = DuskTheme.raisedSurface,
    unfocusedContainerColor = DuskTheme.surface,
    disabledContainerColor = DuskTheme.surface.copy(alpha = 0.55f),
    focusedTextColor = DuskTheme.onSurface,
    unfocusedTextColor = DuskTheme.onSurface,
    disabledTextColor = DuskTheme.disabled,
    cursorColor = DuskTheme.primary,
    focusedIndicatorColor = DuskTheme.primary,
    unfocusedIndicatorColor = DuskTheme.outline,
    disabledIndicatorColor = DuskTheme.outline,
    focusedLabelColor = DuskTheme.primary,
    unfocusedLabelColor = DuskTheme.muted,
    disabledLabelColor = DuskTheme.disabled,
    focusedPlaceholderColor = DuskTheme.disabled,
    unfocusedPlaceholderColor = DuskTheme.disabled,
)

/** Rounded Dusk menu fill prevents square corners showing beneath Material's popup surface. */
@Composable
fun Modifier.iosMenu(): Modifier = this
    .background(DuskTheme.raisedSurface, DuskTheme.chartShape)
    .padding(vertical = DuskTheme.menuInset)
