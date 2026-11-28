"""Regression checks for the checked-row UI audit."""
import unittest
from pathlib import Path
from tempfile import TemporaryDirectory
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
        self.assertNotIn("material_default_colors_unreviewed", categories)
        self.assertIn("app/src/main/java/com/mlmvpn/scanner/lan/LanNotification.kt", ui_audit.TOAST_DEFERRALS)
        self.assertIn("app/src/main/java/com/mlmvpn/scanner/backup/BackupStage.kt", ui_audit.TOAST_DEFERRALS)
        self.assertIn("app/src/main/java/com/mlmvpn/scanner/engines/github/GtEngine.kt", ui_audit.TOAST_DEFERRALS)
        self.assertNotIn("app/src/main/java/com/mlmvpn/scanner/engines/github/UnreviewedUi.kt", ui_audit.TOAST_DEFERRALS)

    def test_canary_flags_all_eight_requested_violation_classes(self):
        cases = {
            "color.kt": ("val bad = Color(0xFF123456)", "color_0x"),
            "hex.xml": ('<View android:background="#A1B2C3" />', "hardcoded_hex"),
            "font-size.kt": ("TextStyle(fontSize = 13.sp)", "hardcoded_font_size"),
            "radius.kt": ("RoundedCornerShape(17.dp)", "hardcoded_dp_radius"),
            "physical.kt": ("Modifier.padding(left = 4.dp)", "physical_left_right"),
            "toast.kt": ("Toast.makeText(ctx, text, Toast.LENGTH_SHORT)", "toast_call"),
            "material.kt": ("Button(onClick = {}) { Text(\"canary\") }", "material_default_colors_unreviewed"),
            "font-name.kt": ('FontFamily("Canary Sans")', "literal_font_name"),
        }
        with TemporaryDirectory(dir=ui_audit.ROOT / "tools", prefix="audit-canary-") as temp:
            base = Path(temp)
            found = set()
            for name, (source, expected) in cases.items():
                path = base / name
                path.write_text(source + "\n")
                categories = {item["category"] for item in ui_audit.audit_file(path, [(1, "canary")])}
                self.assertIn(expected, categories, f"{name} should trigger {expected}")
                found.add(expected)
            self.assertEqual({expected for _, expected in cases.values()}, found)

            marked = base / "marked.kt"
            marked.write_text("Button( // dusk-ok: test rationale\n onClick = {}) { Text(\"ok\") }\n")
            marked_categories = {item["category"] for item in ui_audit.audit_file(marked, [(1, "canary")])}
            self.assertIn("material_default_colors_review", marked_categories)
            self.assertNotIn("material_default_colors_unreviewed", marked_categories)

            nearby = base / "nearby.kt"
            nearby.write_text(
                "Button(onClick = {}) {}\n"
                "Button(colors = duskColors, onClick = {}) {}\n"
            )
            nearby_categories = [item["category"] for item in ui_audit.audit_file(nearby, [(1, "canary")])]
            self.assertEqual(["material_default_colors_unreviewed"], nearby_categories)


if __name__ == "__main__":
    unittest.main()
