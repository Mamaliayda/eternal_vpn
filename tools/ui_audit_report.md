# Checked-row UI source audit

- Checked rows: 1101
- Rows with source references: 1100
- Unique files scanned: 213
- Unresolved findings: 0
- Material component defaults reviewed under the root Soft Dusk ColorScheme: 41
- Explicitly documented platform/background Toast deferrals: 6

| Category | Unresolved hits |
|---|---:|

| File:line | Category | Finding | Checked rows |
|---|---|---|---|
| `app/src/main/java/com/mlmvpn/core/aether/AetherScanService.kt:246` | `reviewed_toast_deferral` | `android.widget.Toast.makeText(context,` | 1516 |
| `app/src/main/java/com/mlmvpn/scanner/MainActivity.kt:53` | `reviewed_toast_deferral` | `android.widget.Toast.makeText(this, getString(R.string.screen_missing_on_device), android.widget.Toast.LENGTH_LONG).show()` | 1285, 1324, 1342 |
| `app/src/main/java/com/mlmvpn/scanner/VpnTileService.kt:175` | `reviewed_toast_deferral` | `Toast.makeText(this@VpnTileService, msg, Toast.LENGTH_SHORT).show()` | 1325 |
| `app/src/main/java/com/mlmvpn/scanner/engines/edg/EdgSettingsScreen.kt:114` | `material_default_colors_review` | `Surface(` | 67, 1447 |
| `app/src/main/java/com/mlmvpn/scanner/engines/edg/EdgSettingsScreen.kt:209` | `material_default_colors_review` | `Button(` | 67, 1447 |
| `app/src/main/java/com/mlmvpn/scanner/engines/mlm/MlmSettingsScreen.kt:52` | `material_default_colors_review` | `Surface(` | 68, 1449 |
| `app/src/main/java/com/mlmvpn/scanner/engines/mlm/MlmSettingsScreen.kt:124` | `material_default_colors_review` | `TextButton(onClick = onDismiss, enabled = !isSaving) {` | 68, 1449 |
| `app/src/main/java/com/mlmvpn/scanner/engines/mlm/MlmSettingsScreen.kt:128` | `material_default_colors_review` | `Button(` | 68, 1449 |
| `app/src/main/java/com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt:85` | `material_default_colors_review` | `Surface(` | 69, 70, 428, 429, 430, 1295, 1450 |
| `app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:105` | `material_default_colors_review` | `Surface(` | 71, 72, 431, 432, 433, 1296, 1451 |
| `app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:510` | `material_default_colors_review` | `Button(` | 71, 72, 431, 432, 433, 1296, 1451 |
| `app/src/main/java/com/mlmvpn/scanner/lan/LanNotification.kt:162` | `reviewed_toast_deferral` | `Toast.makeText(context, S(R.string.lanweb_copied), Toast.LENGTH_SHORT).show()` | 1325, 1522 |
| `app/src/main/java/com/mlmvpn/scanner/ui/BpbSettingsModal.kt:72` | `material_default_colors_review` | `Surface(` | 90, 450, 451 |
| `app/src/main/java/com/mlmvpn/scanner/ui/BpbSettingsModal.kt:162` | `material_default_colors_review` | `Button(` | 90, 450, 451 |
| `app/src/main/java/com/mlmvpn/scanner/ui/CloudTab.kt:2066` | `material_default_colors_review` | `TextButton(onClick = onTroubleshoot) {` | 100, 466, 467, 468, 1299, 1401, 1456 |
| `app/src/main/java/com/mlmvpn/scanner/ui/GameTab.kt:825` | `material_default_colors_review` | `Surface(` | 119, 120, 121, 488, 489, 490, 491, 492, 493, 494, 495, 1301, 1459 |
| `app/src/main/java/com/mlmvpn/scanner/ui/GameTab.kt:874` | `material_default_colors_review` | `Surface(` | 119, 120, 121, 488, 489, 490, 491, 492, 493, 494, 495, 1301, 1459 |
| `app/src/main/java/com/mlmvpn/scanner/ui/GameTab.kt:942` | `material_default_colors_review` | `Surface(` | 119, 120, 121, 488, 489, 490, 491, 492, 493, 494, 495, 1301, 1459 |
| `app/src/main/java/com/mlmvpn/scanner/ui/GameTab.kt:1210` | `material_default_colors_review` | `Surface(` | 119, 120, 121, 488, 489, 490, 491, 492, 493, 494, 495, 1301, 1459 |
| `app/src/main/java/com/mlmvpn/scanner/ui/GameTab.kt:1289` | `material_default_colors_review` | `Surface(` | 119, 120, 121, 488, 489, 490, 491, 492, 493, 494, 495, 1301, 1459 |
| `app/src/main/java/com/mlmvpn/scanner/ui/ScannerTab.kt:1118` | `material_default_colors_review` | `TextButton(onClick = { com.mlmvpn.scanner.data.ScannerManager.reset() }, modifier = Modifier.padding(top = 8.dp)) {` | 156, 551, 552, 553, 554, 555, 1304, 1463 |
| `app/src/main/java/com/mlmvpn/scanner/ui/ScannerTab.kt:1235` | `material_default_colors_review` | `Button(` | 156, 551, 552, 553, 554, 555, 1304, 1463 |
| `app/src/main/java/com/mlmvpn/scanner/ui/ScannerTab.kt:1289` | `material_default_colors_review` | `Button(` | 156, 551, 552, 553, 554, 555, 1304, 1463 |
| `app/src/main/java/com/mlmvpn/scanner/ui/ScannerTab.kt:1595` | `material_default_colors_review` | `Button(` | 156, 551, 552, 553, 554, 555, 1304, 1463 |
| `app/src/main/java/com/mlmvpn/scanner/ui/ScannerTab.kt:1919` | `material_default_colors_review` | `is ScanPreflight.Verdict.OwnVpn -> TextButton(onClick = {` | 156, 551, 552, 553, 554, 555, 1304, 1463 |
| `app/src/main/java/com/mlmvpn/scanner/ui/ScannerTab.kt:1928` | `material_default_colors_review` | `is ScanPreflight.Verdict.ForeignVpn -> TextButton(onClick = {` | 156, 551, 552, 553, 554, 555, 1304, 1463 |
| `app/src/main/java/com/mlmvpn/scanner/ui/ScannerTab.kt:1936` | `material_default_colors_review` | `else -> TextButton(onClick = { preflight = null }) {` | 156, 551, 552, 553, 554, 555, 1304, 1463 |
| `app/src/main/java/com/mlmvpn/scanner/ui/ScannerTab.kt:2136` | `material_default_colors_review` | `TextButton(onClick = { com.mlmvpn.scanner.data.ScannerManager.dismissHealthTestCleanup() }) {` | 156, 551, 552, 553, 554, 555, 1304, 1463 |
| `app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:882` | `material_default_colors_review` | `Switch(checked = retry, onCheckedChange = onRetry)` | 185, 186, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 1306, 1464 |
| `app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:968` | `material_default_colors_review` | `Switch(checked = quick, onCheckedChange = onQuick)` | 185, 186, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 1306, 1464 |
| `app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:976` | `material_default_colors_review` | `Switch(checked = noDataCheck, onCheckedChange = onNoDataCheck)` | 185, 186, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 1306, 1464 |
| `app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:990` | `material_default_colors_review` | `Switch(checked = verbose, onCheckedChange = onVerbose)` | 185, 186, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 1306, 1464 |
| `app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:1020` | `material_default_colors_review` | `Switch(checked = quick, onCheckedChange = onQuick)` | 185, 186, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 1306, 1464 |
| `app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:1028` | `material_default_colors_review` | `Switch(checked = noDataCheck, onCheckedChange = onNoDataCheck)` | 185, 186, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 1306, 1464 |
| `app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:1042` | `material_default_colors_review` | `Switch(checked = verbose, onCheckedChange = onVerbose)` | 185, 186, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 1306, 1464 |
| `app/src/main/java/com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt:559` | `material_default_colors_review` | `OutlinedButton(` | 1017, 1018, 1019, 1020, 1021 |
| `app/src/main/java/com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt:583` | `material_default_colors_review` | `OutlinedButton(` | 1017, 1018, 1019, 1020, 1021 |
| `app/src/main/java/com/mlmvpn/scanner/ui/npv/NpvImportActivity.kt:33` | `reviewed_toast_deferral` | `Toast.makeText(this, when {` | 1287, 1327, 1344 |
| `app/src/main/java/com/mlmvpn/scanner/ui/npv/NpvScreen.kt:576` | `material_default_colors_review` | `TextButton(onClick = { NpvEngine.stopMeasure() }) { Text(tr("توقف", "Stop"), color = DuskTheme.accent) }` | 315, 316, 1024, 1025, 1026, 1027, 1028, 1029, 1030, 1314, 1487 |
| `app/src/main/java/com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt:410` | `material_default_colors_review` | `TextButton(onClick = { NpvEngine.cancelRefresh() }) { Text(tr("توقف", "Stop"), color = DuskTheme.accent) }` | 317, 1031, 1032, 1033, 1034, 1035, 1036, 1315, 1488 |
| `app/src/main/java/com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt:476` | `material_default_colors_review` | `TextButton(onClick = { if (onAdd(text)) text = "" }, enabled = text.isNotBlank()) { Text(tr("افزودن", "Add")) }` | 317, 1031, 1032, 1033, 1034, 1035, 1036, 1315, 1488 |
| `app/src/main/java/com/mlmvpn/scanner/ui/scout/ScoutScreen.kt:80` | `material_default_colors_review` | `TextButton(onClick = { coverage = true }) { Text(tr("پوشش", "Coverage"), color = DuskTheme.primary) }` | 334, 335, 1317 |
| `app/src/main/java/com/mlmvpn/scanner/ui/scout/ScoutScreen.kt:127` | `material_default_colors_review` | `if (history.isNotEmpty()) TextButton(onClick = { showHistory = true }) { Text(tr("سابقهٔ بررسی‌ها", "History"), color = DuskTheme.primary) }` | 334, 335, 1317 |
| `app/src/main/java/com/mlmvpn/scanner/ui/scout/ScoutScreen.kt:196` | `material_default_colors_review` | `history.forEach { previous -> TextButton(enabled = !ui.running, onClick = { ScoutEngine.show(previous); showHistory = false }) {` | 334, 335, 1317 |
| `app/src/main/java/com/mlmvpn/scanner/ui/scout/ScoutScreen.kt:256` | `material_default_colors_review` | `if (adapter != null) TextButton(onClick = { details = null; onOpen(if (method.status == ScoutStatus.NOT_INSTALLED) "store" else adapter.homeId) }) {` | 334, 335, 1317 |
| `app/src/main/java/com/mlmvpn/scanner/ui/scout/ScoutScreen.kt:259` | `material_default_colors_review` | `if (!ui.running && adapter?.built == true) TextButton(onClick = { details = null; checked { ScoutEngine.retest(context, id, pauseApproved = it) } }) {` | 334, 335, 1317 |
| `app/src/main/java/com/mlmvpn/scanner/widget/WidgetActionActivity.kt:152` | `reviewed_toast_deferral` | `runCatching { Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show() }` | 1288, 1347 |

## Unresolved references

