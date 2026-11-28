package com.mlmvpn.scanner.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.mlmvpn.scanner.R
import com.mlmvpn.scanner.data.ScanGuard

/**
 * The one dialog that says "your scan is using the connection right now".
 *
 * Mounted once, at the app's root, rather than on each screen that can collide with a scan. The
 * collision is not a property of any one screen -- the connection list, Quick Connect, the game
 * booster, the five transports and the tile can all raise it -- and a copy per screen would be
 * six chances to word the same question differently and one guaranteed omission.
 *
 * The two answers are the only two that exist. There is no third option where both things happen:
 * a tunnel takes the default route out from under every socket the scan has open, and a delay test
 * measured while a scan is saturating the link is a number about the scan, not about the server.
 */
@Composable
fun ScanGuardHost() {
    val pending by ScanGuard.pending.collectAsState()
    val request = pending ?: return
    val scout = ScanGuard.isScoutBusy() && !com.mlmvpn.scanner.data.ScannerManager.isScanning.value

    com.mlmvpn.scanner.ui.settings.IosAlert(
        onDismiss = { ScanGuard.dismiss() },
        title = if (scout) com.mlmvpn.scanner.store.tr("Eternal VPN Scout در حال بررسی است", "Eternal VPN Scout is running")
        else stringResource(R.string.scan_busy_title),
        message = when {
            scout -> com.mlmvpn.scanner.store.tr(
                "Scout الان روش‌ها را روی خودِ اینترنت شما می‌سنجد؛ کار دیگری در این لحظه عددها را خراب می‌کند. صبر می‌کنید یا Scout را متوقف کنم؟ نتایج تا همین لحظه می‌ماند.",
                "Scout is measuring the methods on your own line right now; doing this now would spoil its numbers. Wait, or stop Scout? The results so far are kept.",
            )
            request.reason == ScanGuard.Reason.CONNECT_VPN -> stringResource(R.string.scan_busy_connect_body)
            else -> stringResource(R.string.scan_busy_delay_body)
        },
        actions = listOf(
            com.mlmvpn.scanner.ui.settings.IosAlertAction(
                stringResource(R.string.scan_busy_wait),
                onClick = { ScanGuard.dismiss() },
            ),
            com.mlmvpn.scanner.ui.settings.IosAlertAction(
                if (scout) com.mlmvpn.scanner.store.tr("توقف Scout و ادامه", "Stop Scout and go on")
                else stringResource(R.string.scan_busy_stop_scan),
                onClick = { ScanGuard.stopScanAndProceed() },
                destructive = true,
                preferred = true,
            ),
        ),
    )
}
