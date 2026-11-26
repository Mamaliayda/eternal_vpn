package com.mlmvpn.scanner.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mlmvpn.scanner.engines.vpngate.VpnGateSweep
import com.mlmvpn.scanner.ui.theme.DuskTheme
import com.mlmvpn.scanner.R
import com.mlmvpn.scanner.utils.S

/**
 * Progress strip for the VPN Gate bulk tests: label + count + percentage, a filling bar, and a ✕
 * that stops the sweep.
 *
 * Modelled on [NodeTestProgressRow], the same control on the V2Ray screen -- it used to be a
 * frosted-glass pill of its own, which made the one bar in the app that reports a long-running
 * batch look different depending on which screen you started it from.
 *
 * Place it OUTSIDE the scrolling container: these sweeps take minutes over hundreds of servers and
 * the whole point is that the user can scroll the results while still seeing how far along it is.
 * Draws nothing when no sweep is running.
 */
@Composable
fun VpnGateSweepStrip(modifier: Modifier = Modifier) {
    val sweep by VpnGateSweep.stateFlow.collectAsState()

    AnimatedVisibility(visible = sweep != null) {
        // Held so the row keeps rendering its last values through the exit animation instead
        // of snapping to a blank bar the instant the sweep clears.
        val s = sweep ?: return@AnimatedVisibility
        Row(
            modifier = modifier.fillMaxWidth().padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "${s.kind.labelFa} ${faCount(s.done)}/${faCount(s.total)}",
                color = DuskTheme.muted,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(end = 10.dp),
            )
            LinearProgressIndicator(
                progress = s.fraction,
                modifier = Modifier.weight(1f).height(3.dp).clip(DuskTheme.circleShape),
                color = DuskTheme.primary,
                trackColor = DuskTheme.white.copy(alpha = 0.14f),
            )
            Text(
                text = faCount(s.percent) + S(R.string.str_4),
                color = DuskTheme.muted,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 10.dp),
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = S(R.string.stop_testing_2),
                tint = DuskTheme.muted,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(16.dp)
                    .clip(DuskTheme.circleShape)
                    .clickable { VpnGateSweep.cancel() },
            )
        }
    }
}
