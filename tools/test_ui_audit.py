"""Regression checks for the checked-row UI audit."""
import unittest
from pathlib import Path
from tools import ui_audit


class UiAuditTests(unittest.TestCase):
    def test_every_checked_row_is_referenced_or_explicitly_audit_metadata(self):
        rows = ui_audit.checked_rows()
        refs, missing = ui_audit.references(rows)
        self.assertEqual([], missing)
        # One checked row (line 1335) is the audit-control statement itself, not a UI source.
        self.assertEqual(len(rows) - 1, len({n for linked in refs.values() for n, _ in linked}))
        self.assertGreaterEqual(len(refs), 200)

    def test_style_literal_detectors_catch_regressions(self):
        self.assertRegex("Text(label, fontSize = 13.sp)", ui_audit.FONT_SIZE_RE)
        self.assertRegex("RoundedCornerShape(12.dp)", ui_audit.RADIUS_RE)
        self.assertRegex("TextAlign.Right", ui_audit.PHYSICAL_KT_RE)
        self.assertRegex("Toast.makeText(ctx, text, Toast.LENGTH_SHORT)", ui_audit.TOAST_RE)

    def test_material_defaults_and_toast_deferrals_are_contextual(self):
        categories = {item["category"] for item in ui_audit.audit_file(
            Path(ui_audit.ROOT / "app/src/main/java/com/mlmvpn/scanner/engines/edg/EdgSettingsScreen.kt"),
            [(1, "test")])}
        self.assertIn("material_default_colors_review", categories)
        self.assertIn("app/src/main/java/com/mlmvpn/scanner/lan/LanNotification.kt", ui_audit.TOAST_DEFERRALS)
        self.assertIn("app/src/main/java/com/mlmvpn/scanner/backup/BackupStage.kt", ui_audit.TOAST_DEFERRALS)
        self.assertIn("app/src/main/java/com/mlmvpn/scanner/engines/github/GtEngine.kt", ui_audit.TOAST_DEFERRALS)
        self.assertNotIn("app/src/main/java/com/mlmvpn/scanner/engines/github/UnreviewedUi.kt", ui_audit.TOAST_DEFERRALS)


if __name__ == "__main__":
    unittest.main()
