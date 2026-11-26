package com.mlmvpn.scanner.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import com.mlmvpn.scanner.ui.theme.DuskCard
import com.mlmvpn.scanner.ui.theme.DuskPill
import com.mlmvpn.scanner.ui.theme.DuskTheme

/**
 * Floating four-destination navigation shared by the home board and feature pages.
 * The route IDs and callbacks stay owned by AppScreen; this composable only presents them.
 */
@Composable
fun GlassDock(
    apps: List<HomeApp>,
    tileSize: Dp,
    onOpen: (HomeApp) -> Unit,
    modifier: Modifier = Modifier,
    /** Off while the home screen's search covers navigation, so remote focus stays in the search. */
    focusable: Boolean = true,
    selectedId: String? = null,
) {
    DuskCard(
        modifier = modifier.widthIn(max = 560.dp).fillMaxWidth(),
        shape = DuskTheme.cardShape,
        contentPadding = PaddingValues(horizontal = DuskTheme.space2, vertical = DuskTheme.space2),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            apps.forEach { app ->
                val label = stringResource(app.labelRes)
                val selected = app.id == selectedId
                Column(
                    modifier = Modifier
                        .tvFocusable(enabled = focusable, cornerRadius = DuskTheme.focusCornerRadius, onPress = { onOpen(app) })
                        .then(if (focusable) Modifier.clickable { onOpen(app) } else Modifier)
                        .semantics(mergeDescendants = true) { contentDescription = label },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(DuskTheme.space1),
                ) {
                    Box(
                        modifier = Modifier
                            .size(tileSize.coerceAtMost(DuskTheme.navIconSize))
                            .clip(DuskTheme.circleShape)
                            .background(if (selected) DuskTheme.accent else DuskTheme.raisedSurface),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = app.icon,
                            contentDescription = null,
                            tint = if (selected) DuskTheme.onAccent else DuskTheme.primary,
                            modifier = Modifier.size(DuskTheme.iconSize),
                        )
                    }
                    DuskPill(text = label, selected = selected)
                }
            }
        }
    }
}
