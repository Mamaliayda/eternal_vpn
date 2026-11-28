# Soft Dusk — Audit findings / pending fixes

Baseline: commit `fa771880edb5be297c3b7b85a24b98ebcc3d3af5` (CI has now completed green for both required runs; see links below). This file records the read-only honesty audits and final-grep hits before remediation. Hits are intentionally preserved verbatim with file/line so the follow-up can be checked off without losing provenance.

## 25-row checked-item honesty audit

Random sample chosen from the 1,205 checklist rows: 10 checked rows at ordinal ≤700 and 15 checked rows at ordinal >700. The 15 latter items satisfy the required ≥10 post-700 sample.

| Checklist ordinal | Row | Result | Finding |
|---:|---|:---:|---|
| 48 | `EdgSettingsScreen` | PASS | Dusk semantic colors, shared spacing/shapes and Material typography in the screen. |
| 191 | `HomeScreen` | PASS | Dusk surface/status tokens and shared controls; offsets are motion/overlay geometry, separately listed for RTL review. |
| 222 | `ProxyModePage` | PASS | Shared `IosScreen`, `SettingsGroup`, `SettingsToggle`, Dusk spacing and footer. |
| 323 | `HelpBody` | PASS | Dusk callout shape/color/spacing and Material body typography. |
| 354 | `ResultRow` | PASS | Dusk semantic text/chips and Material typography; uses logical start/end padding. |
| 540 | `EmptyState` | PASS | Shared `DuskCard`, Dusk glyph/color/spacing and Material typography. |
| 582 | `HeroTitle` | FAIL | `CloudDoctorScreen.kt:537-538` still uses literal `fontSize` and `lineHeight`; move to Material type scale. |
| 636 | `GameIcon` | PASS | Dusk glyph shape used for app artwork; dynamic fallback glyph sizing is intentionally content-sized. |
| 645 | `StatsRow` | FAIL | `GameBoostScreen.kt:668-671` still hardcodes 15sp on the metric hierarchy. |
| 674 | `SelectedAppRow` | FAIL | `GithubTunnelScreen.kt:1036-1044` still has literal rounded shapes and 15sp/11sp text. |
| 738 | `SmallAction` | FAIL | `LanScreen.kt:880-887` still has literal 12dp shape and 14sp type. |
| 751 | `MaeOnboarding` | PASS | Shared Dusk screen/groups/actions and Material typography. |
| 823 | `Stat` | FAIL | `ScoutParts.kt:374-375` still hardcodes 18sp/11sp. |
| 853 | `SettingsFooter` | PASS | Dusk muted token, Material body style and shared spacing. |
| 854 | `SettingsRow` | PASS | Dusk colors and Material text styles through shared row frame. |
| 871 | `SpiderRow` | PASS | Dusk colors and Material body style. |
| 909 | `DuskCard` | PASS | Defines shared Dusk card defaults for shape, color, elevation and padding. |
| 939 | `BridgeRow` | FAIL | `TorBridgesScreen.kt:376,380,385,392,399-400` retains literal radii and font sizes. |
| 1013 | Aether `Dialog` + `DropdownMenu` | PASS | Dialog delegates to `DuskCard`; dropdown uses shared `.iosMenu()` and Material typography. |
| 1059 | `.widget.WindscribeWidget` | PASS | Provider delegates appearance to shared `WidgetUpdater` painter/tokens; provider itself has no independent visuals. |
| 1072 | `.widget.studio.StudioWidgetMedium` | PASS | Provider delegates appearance to shared `StudioPainter`; no independent visual surface. |
| 1093 | `GatewayRows` `IosAlert` | PASS | Uses shared themed alert component. |
| 1163 | Geph clipboard Toast migration | PASS | Clipboard helper receives `showNotice` and routes to Dusk snackbar. |
| 1176 | Cloud resources Toast migration | PASS | Clipboard feedback uses activity-scoped Dusk snackbar callback. |
| 1191 | Widget Studio Toast migration | PASS | `WidgetStudioScreen` uses `rememberDuskSnackbarAction`. |

**25-row result:** 19 PASS, 6 FAIL. The six findings were recorded before remediation; see re-audit below.

## 10-row follow-up audit (same >700 area)

| Checklist ordinal | Row | Result | Finding |
|---:|---|:---:|---|
| 702 | `RemoveBadge` | PASS | Dusk colors/shapes; absolute offset is intentional badge anchoring and is listed for directional review. |
| 753 | `ForeignExitsList` | PASS | Delegates to shared Dusk settings section/group/row components. |
| 778 | `RefreshCard` | PASS | Shared Dusk settings group, actions, separators and footer. |
| 890 | `HelpRow` | PASS | Thin delegation to the shared themed `SettingsRow`. |
| 919 | Psiphon `IpRow` | FAIL | Delegates to `CountryRow`, which still uses literal rounded radius and sp sizes (`PsiphonPages.kt:1302-1310`). |
| 928 | `CustomTestCard` | PASS | Uses shared progress shell and Dusk semantic content colors. |
| 982 | Widget Studio `CountryRow` | FAIL | `WidgetStudioScreen.kt:862,865-868` still uses literal 20sp/16sp. |
| 1056 | `.widget.FenixWidget` | PASS | Uses shared method-widget painter; provider has no independent visuals. |
| 1094 | `GatewaySettingsScreen` alerts | PASS | Destructive/confirm dialogs use shared `IosAlert`. |
| 1115 | `OutlineScreen` alert | PASS | Uses shared `IosAlert`. |

**Follow-up result:** 8 PASS, 2 FAIL. The two findings were recorded before remediation; see re-audit below.


## Re-audit after green CI and pending-fix application

All eight sampled failures were corrected after both `fa771880` runs passed, then re-read in source:

| Finding | Retest | Resolution |
|---|:---:|---|
| `HeroTitle` | PASS | Replaced literal 19sp/13sp/line-height with Material title/body styles. |
| `StatsRow` | PASS | Migrated all four metric text nodes to shared Material body style. |
| `SelectedAppRow` | PASS | Replaced literal glyph corner radii with `DuskTheme.glyphShape`; applied body/label styles. |
| `SmallAction` | PASS | Uses shared pill shape and Material label typography. |
| Scout `Stat` | PASS | Uses Material title and label styles. |
| Tor `BridgeRow` | PASS | Uses shared Dusk card/tag shapes and Material title/body/label scale. |
| Psiphon `IpRow` / `CountryRow` | PASS | Delegated row now uses shared Dusk card shape, typography and technical mono token. |
| Widget Studio `CountryRow` | PASS | Uses shared Dusk spacing and Material title/body styles. |

**Retest result:** 8/8 PASS; the corresponding checklist rows remain checked with the migration described. Additional grep fixes applied: the Aether UI now uses semantic Dusk colors rather than `MaterialTheme.colorScheme` defaults; `SafeWebView` and app background colors use Dusk tokens; the search field uses logical text alignment; widget preview colors reference named resources; and the home edit badge now uses a layout-direction-aware offset.

## Final-grep audit

Scope: application source/resources (`app/src`), excluding generated build output. Every raw match from each requested pattern is recorded below. Some hits are intentional Dusk token definitions or fixed brand/system artwork; those are noted, not silently discarded. “Fix / review” means the line needs an explicit resolution before the final clean-grep statement.


### Hardcoded #hex literals — 281 hit(s)

```text
app/src/main/res/values/colors.xml:5:    <color name="white">#FFFFFF</color>
app/src/main/res/values/colors.xml:6:    <color name="black">#000000</color>
app/src/main/res/values/colors.xml:7:    <color name="dusk_background">#191D32</color>
app/src/main/res/values/colors.xml:8:    <color name="dusk_surface">#34334D</color>
app/src/main/res/values/colors.xml:9:    <color name="dusk_surface_raised">#27263E</color>
app/src/main/res/values/colors.xml:10:    <color name="dusk_panel">#34334D</color>
app/src/main/res/values/colors.xml:11:    <color name="dusk_card">#34334D</color>
app/src/main/res/values/colors.xml:12:    <color name="dusk_outline">#77758E</color>
app/src/main/res/values/colors.xml:13:    <color name="dusk_outline_variant">#4A4962</color>
app/src/main/res/values/colors.xml:14:    <color name="dusk_primary">#9BB6E3</color>
app/src/main/res/values/colors.xml:15:    <color name="dusk_primary_dim">#728EBB</color>
app/src/main/res/values/colors.xml:16:    <color name="dusk_primary_container">#27263E</color>
app/src/main/res/values/colors.xml:17:    <color name="dusk_secondary">#C1BED0</color>
app/src/main/res/values/colors.xml:18:    <color name="dusk_secondary_container">#34334D</color>
app/src/main/res/values/colors.xml:19:    <color name="dusk_error">#FFB4AB</color>
app/src/main/res/values/colors.xml:20:    <color name="dusk_error_container">#690005</color>
app/src/main/res/values/colors.xml:21:    <color name="dusk_warning">#E9BACB</color>
app/src/main/res/values/colors.xml:22:    <color name="dusk_on_surface">#F8F2FA</color>
app/src/main/res/values/colors.xml:23:    <color name="dusk_on_surface_variant">#D0CDE0</color>
app/src/main/res/values/colors.xml:24:    <color name="dusk_on_surface_dim">#C1BED0</color>
app/src/main/res/values/colors.xml:26:    <color name="dusk_qr_surface">#FFFFFF</color>
app/src/main/res/values/colors.xml:28:    <color name="dusk_widget_warp_start">#FFFFA82E</color>
app/src/main/res/values/colors.xml:29:    <color name="dusk_widget_warp_end">#FFDB8000</color>
app/src/main/res/values/colors.xml:30:    <color name="dusk_widget_fronting_start">#FFFFD52E</color>
app/src/main/res/values/colors.xml:31:    <color name="dusk_widget_fronting_end">#FFDBAF00</color>
app/src/main/res/values/colors.xml:32:    <color name="dusk_widget_emergency_start">#FFFF5E55</color>
app/src/main/res/values/colors.xml:33:    <color name="dusk_widget_emergency_end">#FFDB3329</color>
app/src/main/res/values/colors.xml:34:    <color name="dusk_widget_antisanction_start">#FF59D177</color>
app/src/main/res/values/colors.xml:35:    <color name="dusk_widget_antisanction_end">#FF2DAB4D</color>
app/src/main/res/values/colors.xml:36:    <color name="dusk_widget_quick_start">#FF2E92FF</color>
app/src/main/res/values/colors.xml:37:    <color name="dusk_widget_quick_end">#FF0069DB</color>
app/src/main/res/values/colors.xml:38:    <color name="dusk_nav_pressed_overlay">#288AB4F8</color>
app/src/main/res/values/colors.xml:39:    <color name="dusk_chip_green_overlay">#1A81C995</color>
app/src/main/res/values/colors.xml:40:    <color name="dusk_chip_blue_overlay">#1A8AB4F8</color>
app/src/main/res/values/colors.xml:41:    <color name="dusk_badge_active_fill">#1A0DFF9E</color>
app/src/main/res/values/colors.xml:42:    <color name="dusk_badge_active_stroke">#4D0DFF9E</color>
app/src/main/res/drawable/widget_preview_studio_small.xml:9:    <path android:pathData="M26,0 H134 A26,26 0 0 1 160,26 V134 A26,26 0 0 1 134,160 H26 A26,26 0 0 1 0,134 V26 A26,26 0 0 1 26,0 Z"><aapt:attr name="android:fillColor"><gradient android:type="linear" android:startX="0" android:startY="0" android:endX="160" android:endY="160" android:startColor="#E6223A57" android:endColor="#F00A1626" /></aapt:attr></path>
app/src/main/res/drawable/widget_preview_studio_small.xml:10:    <path android:pathData="M26,0 H134 A26,26 0 0 1 160,26 V134 A26,26 0 0 1 134,160 H26 A26,26 0 0 1 0,134 V26 A26,26 0 0 1 26,0 Z" android:strokeColor="#40FFFFFF" android:strokeWidth="1.5" />
app/src/main/res/drawable/widget_preview_studio_small.xml:11:    <path android:fillColor="#2A39E58C" android:pathData="M46,102 A34,34 0 1 0 114,102 A34,34 0 1 0 46,102 Z" />
app/src/main/res/drawable/widget_preview_studio_small.xml:12:    <path android:strokeColor="#FF39E58C" android:strokeWidth="3.7" android:pathData="M48.040000000000006,102 A31.959999999999997,31.959999999999997 0 1 0 111.96,102 A31.959999999999997,31.959999999999997 0 1 0 48.040000000000006,102 Z" />
app/src/main/res/drawable/widget_preview_studio_small.xml:13:    <path android:strokeColor="#FF39E58C" android:strokeWidth="3.4" android:strokeLineCap="round" android:pathData="M72.3,91.4 A13.0696,13.0696 0 1 0 87.7,91.4 M80,85.6 V99.5" />
app/src/main/res/drawable/widget_preview_studio_small.xml:14:    <path android:fillColor="#FF34C759" android:pathData="M21.28,14 H34.72 A7.28,7.28 0 0 1 42,21.28 V34.72 A7.28,7.28 0 0 1 34.72,42 H21.28 A7.28,7.28 0 0 1 14,34.72 V21.28 A7.28,7.28 0 0 1 21.28,14 Z" />
app/src/main/res/drawable/widget_preview_studio_small.xml:15:    <path android:fillColor="#E6FFFFFF" android:pathData="M54.5,18 H115.5 A4.5,4.5 0 0 1 120,22.5 V22.5 A4.5,4.5 0 0 1 115.5,27 H54.5 A4.5,4.5 0 0 1 50,22.5 V22.5 A4.5,4.5 0 0 1 54.5,18 Z" />
app/src/main/res/drawable/widget_preview_studio_small.xml:16:    <path android:fillColor="#99FFFFFF" android:pathData="M53.5,31 H92.5 A3.5,3.5 0 0 1 96,34.5 V34.5 A3.5,3.5 0 0 1 92.5,38 H53.5 A3.5,3.5 0 0 1 50,34.5 V34.5 A3.5,3.5 0 0 1 53.5,31 Z" />
app/src/main/res/drawable/widget_preview_studio_medium.xml:9:    <path android:pathData="M26,0 H304 A26,26 0 0 1 330,26 V124 A26,26 0 0 1 304,150 H26 A26,26 0 0 1 0,124 V26 A26,26 0 0 1 26,0 Z"><aapt:attr name="android:fillColor"><gradient android:type="linear" android:startX="0" android:startY="0" android:endX="330" android:endY="150" android:startColor="#E6223A57" android:endColor="#F00A1626" /></aapt:attr></path>
app/src/main/res/drawable/widget_preview_studio_medium.xml:10:    <path android:pathData="M26,0 H304 A26,26 0 0 1 330,26 V124 A26,26 0 0 1 304,150 H26 A26,26 0 0 1 0,124 V26 A26,26 0 0 1 26,0 Z" android:strokeColor="#40FFFFFF" android:strokeWidth="1.5" />
app/src/main/res/drawable/widget_preview_studio_medium.xml:11:    <path android:fillColor="#2A39E58C" android:pathData="M38,75 A44,44 0 1 0 126,75 A44,44 0 1 0 38,75 Z" />
app/src/main/res/drawable/widget_preview_studio_medium.xml:12:    <path android:strokeColor="#FF39E58C" android:strokeWidth="4.8" android:pathData="M40.64,75 A41.36,41.36 0 1 0 123.36,75 A41.36,41.36 0 1 0 40.64,75 Z" />
app/src/main/res/drawable/widget_preview_studio_medium.xml:13:    <path android:strokeColor="#FF39E58C" android:strokeWidth="4.4" android:strokeLineCap="round" android:pathData="M72.1,61.3 A16.9136,16.9136 0 1 0 91.9,61.3 M82,53.7 V71.7" />
app/src/main/res/drawable/widget_preview_studio_medium.xml:14:    <path android:fillColor="#FF34C759" android:pathData="M278.84,16 H295.16 A8.84,8.84 0 0 1 304,24.84 V41.16 A8.84,8.84 0 0 1 295.16,50 H278.84 A8.84,8.84 0 0 1 270,41.16 V24.84 A8.84,8.84 0 0 1 278.84,16 Z" />
app/src/main/res/drawable/widget_preview_studio_medium.xml:15:    <path android:fillColor="#E6FFFFFF" android:pathData="M175,20 H255 A5,5 0 0 1 260,25 V25 A5,5 0 0 1 255,30 H175 A5,5 0 0 1 170,25 V25 A5,5 0 0 1 175,20 Z" />
app/src/main/res/drawable/widget_preview_studio_medium.xml:16:    <path android:fillColor="#99FFFFFF" android:pathData="M197.5,36 H256.5 A3.5,3.5 0 0 1 260,39.5 V39.5 A3.5,3.5 0 0 1 256.5,43 H197.5 A3.5,3.5 0 0 1 194,39.5 V39.5 A3.5,3.5 0 0 1 197.5,36 Z" />
app/src/main/res/drawable/widget_preview_studio_medium.xml:17:    <path android:fillColor="#99FFFFFF" android:pathData="M218,112 H300 A4,4 0 0 1 304,116 V116 A4,4 0 0 1 300,120 H218 A4,4 0 0 1 214,116 V116 A4,4 0 0 1 218,112 Z" />
app/src/main/res/drawable/widget_preview_studio_medium.xml:18:    <path android:fillColor="#26FFFFFF" android:pathData="M225,78 H293 A11,11 0 0 1 304,89 V89 A11,11 0 0 1 293,100 H225 A11,11 0 0 1 214,89 V89 A11,11 0 0 1 225,78 Z" />
app/src/main/res/drawable/widget_preview_studio_large.xml:9:    <path android:pathData="M26,0 H304 A26,26 0 0 1 330,26 V304 A26,26 0 0 1 304,330 H26 A26,26 0 0 1 0,304 V26 A26,26 0 0 1 26,0 Z"><aapt:attr name="android:fillColor"><gradient android:type="linear" android:startX="0" android:startY="0" android:endX="330" android:endY="330" android:startColor="#E6223A57" android:endColor="#F00A1626" /></aapt:attr></path>
app/src/main/res/drawable/widget_preview_studio_large.xml:10:    <path android:pathData="M26,0 H304 A26,26 0 0 1 330,26 V304 A26,26 0 0 1 304,330 H26 A26,26 0 0 1 0,304 V26 A26,26 0 0 1 26,0 Z" android:strokeColor="#40FFFFFF" android:strokeWidth="1.5" />
app/src/main/res/drawable/widget_preview_studio_large.xml:11:    <path android:fillColor="#2A39E58C" android:pathData="M103,165 A62,62 0 1 0 227,165 A62,62 0 1 0 103,165 Z" />
app/src/main/res/drawable/widget_preview_studio_large.xml:12:    <path android:strokeColor="#FF39E58C" android:strokeWidth="6.8" android:pathData="M106.72,165 A58.279999999999994,58.279999999999994 0 1 0 223.28,165 A58.279999999999994,58.279999999999994 0 1 0 106.72,165 Z" />
app/src/main/res/drawable/widget_preview_studio_large.xml:13:    <path android:strokeColor="#FF39E58C" android:strokeWidth="6.2" android:strokeLineCap="round" android:pathData="M151.0,145.7 A23.8328,23.8328 0 1 0 179.0,145.7 M165,135.0 V160.4" />
app/src/main/res/drawable/widget_preview_studio_large.xml:14:    <path android:fillColor="#FF34C759" android:pathData="M284.84,18 H301.16 A8.84,8.84 0 0 1 310,26.84 V43.16 A8.84,8.84 0 0 1 301.16,52 H284.84 A8.84,8.84 0 0 1 276,43.16 V26.84 A8.84,8.84 0 0 1 284.84,18 Z" />
app/src/main/res/drawable/widget_preview_studio_large.xml:15:    <path android:fillColor="#E6FFFFFF" android:pathData="M175.5,22 H260.5 A5.5,5.5 0 0 1 266,27.5 V27.5 A5.5,5.5 0 0 1 260.5,33 H175.5 A5.5,5.5 0 0 1 170,27.5 V27.5 A5.5,5.5 0 0 1 175.5,22 Z" />
app/src/main/res/drawable/widget_preview_studio_large.xml:16:    <path android:fillColor="#99FFFFFF" android:pathData="M28.5,280 H99.5 A4.5,4.5 0 0 1 104,284.5 V284.5 A4.5,4.5 0 0 1 99.5,289 H28.5 A4.5,4.5 0 0 1 24,284.5 V284.5 A4.5,4.5 0 0 1 28.5,280 Z" />
app/src/main/res/drawable/widget_preview_studio_large.xml:17:    <path android:fillColor="#99FFFFFF" android:pathData="M129.5,280 H200.5 A4.5,4.5 0 0 1 205,284.5 V284.5 A4.5,4.5 0 0 1 200.5,289 H129.5 A4.5,4.5 0 0 1 125,284.5 V284.5 A4.5,4.5 0 0 1 129.5,280 Z" />
app/src/main/res/drawable/widget_preview_studio_large.xml:18:    <path android:fillColor="#99FFFFFF" android:pathData="M230.5,280 H301.5 A4.5,4.5 0 0 1 306,284.5 V284.5 A4.5,4.5 0 0 1 301.5,289 H230.5 A4.5,4.5 0 0 1 226,284.5 V284.5 A4.5,4.5 0 0 1 230.5,280 Z" />
app/src/main/res/drawable/widget_preview_studio_large.xml:19:    <path android:fillColor="#26FFFFFF" android:pathData="M37,66 H121 A13,13 0 0 1 134,79 V79 A13,13 0 0 1 121,92 H37 A13,13 0 0 1 24,79 V79 A13,13 0 0 1 37,66 Z" />
app/src/main/res/drawable/ic_launcher_monochrome.xml:4:    <path android:fillColor="#FFFFFFFF"
app/src/main/res/drawable/widget_glyph_vpn_lock.xml:9:        android:fillColor="#FFFFFFFF"
app/src/main/res/drawable/widget_glyph_rocket.xml:9:        android:fillColor="#FFFFFFFF"
app/src/main/res/drawable/node_icon_bg.xml:4:    <solid android:color="#1A3050"/>
app/src/main/res/drawable/ic_notification.xml:7:        android:fillColor="#FFFFFF"
app/src/main/res/drawable/ic_glyph_swap.xml:15:        android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_shield_check.xml:15:        android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_shield_check.xml:21:        android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_radar.xml:15:        android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_radar.xml:20:        android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_radar.xml:26:        android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_radar.xml:31:        android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_glyph_radar.xml:34:        android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_glyph_pin.xml:15:        android:fillColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_pad.xml:15:        android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_pad.xml:21:        android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_pad.xml:27:        android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_glyph_pad.xml:30:        android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_glyph_layers.xml:15:        android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_glyph_layers.xml:18:        android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_book.xml:15:        android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_book.xml:20:        android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_glyph_bars.xml:15:        android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_glyph_bars.xml:18:        android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_glyph_bars.xml:21:        android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_cloudflare_round.xml:18:        android:fillColor="#F38020" />
app/src/main/res/drawable/ic_cloudflare_round.xml:23:        android:fillColor="#FFFFFF" />
app/src/main/res/drawable/ic_baseline_close_24.xml:1:<vector xmlns:android="http://schemas.android.com/apk/res/android" android:height="24dp" android:tint="#000000" android:viewportHeight="24" android:viewportWidth="24" android:width="24dp">
app/src/main/res/drawable/ic_app_wireguard.xml:3:    Home tile «wireguard» — desktop artwork g-wireguard-art, cover tile on #88171a at 84%.
app/src/main/res/drawable/ic_app_wireguard.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FF88171A" />
app/src/main/res/drawable/ic_app_wireguard.xml:19:                android:fillColor="#FF88171A" />
app/src/main/res/drawable/ic_app_wireguard.xml:22:                android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_wireguard.xml:25:                android:fillColor="#FF231F20" />
app/src/main/res/drawable/ic_app_windscribe.xml:1:<vector xmlns:android="http://schemas.android.com/apk/res/android" android:width="108dp" android:height="108dp" android:viewportWidth="1024" android:viewportHeight="1024"><path android:fillColor="#020d1c" android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z"/><path android:fillColor="#FFFFFF" android:pathData="M724.09 376.15c-7.45-18-14.75-36.05-22.29-54C638.56 296 575.23 270.09 512 243.94c-63.23 26.15-126.56 52.06-189.79 78.21-7.54 17.95-14.84 36-22.29 54Q272 444.07 244 512q39.11 95 78.23 190 94.9 39 189.77 78.08 94.92-39 189.77-78.08 39.17-95 78.23-190-27.94-67.93-55.91-135.85zM512 742.84q-81.87-33.41-163.7-66.92-33.58-82-67-163.94 33.51-81.9 67-163.8 81.6-33.3 163.16-66.7a1.12 1.12 0 0 1 1.2 0q81.59 33.33 163.16 66.7 33.53 81.89 67 163.8-33.49 82-67 163.94C621.14 698.2 512 742.84 512 742.84m.12-186.31a.23.23 0 0 1-.22 0q-26.15 26.32-52.29 52.64c-10.3 10.17-25 14.61-39.25 14.82h-42.61V400.43h52.56v163.56q25.8-26 51.66-51.92 14.92-15 29.91-30l.1-.1.1.1q15 15 29.91 30Q567.88 538 593.67 564q.09-81.78 0-163.56h52.56V624h-42.61c-14.22-.21-28.95-4.65-39.25-14.82q-26.19-26.27-52.29-52.64m200.75-44.85.05.13h-3.38c-4 .09-8.06 0-12.09 0h-43.28V392.6h-63.69l-2.29 2.3L576 407.06c-5.79 5.79-11.78 11.77-17.66 17.68L556 427v76.95h-11c-9.21-9.28-18.42-18.51-27.35-27.47L512.1 471l-.12-.12-.1.1-5.54 5.56c-8.93 9-18.14 18.19-27.35 27.47H468V427l-2.29-2.3c-5.88-5.9-11.87-11.89-17.66-17.68l-12.15-12.15-2.29-2.3H369.9v119.22h-43.28c-4 0-8.07.09-12.09 0h-3.41l.05-.13h-.05q29.82-70.51 59.46-141.12Q440.76 340.92 511 311.4a1.84 1.84 0 0 1 2 0q70.21 29.6 140.46 59.16 29.72 70.57 59.46 141.12z"/></vector>
app/src/main/res/drawable/ic_app_warp.xml:3:    Home tile «warp» — desktop artwork g-warp-art, cover tile on #f06d6c.
app/src/main/res/drawable/ic_app_warp.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FFF06D6C" />
app/src/main/res/drawable/ic_app_warp.xml:19:                android:fillColor="#FFF06D6C"
app/src/main/res/drawable/ic_app_warp.xml:21:                android:strokeColor="#FFF06D6C"
app/src/main/res/drawable/ic_app_warp.xml:26:                android:fillColor="#FF09A9D6"
app/src/main/res/drawable/ic_app_warp.xml:28:                android:strokeColor="#FF09A9D6"
app/src/main/res/drawable/ic_app_warp.xml:33:                android:fillColor="#FFF1A830"
app/src/main/res/drawable/ic_app_warp.xml:35:                android:strokeColor="#FFF1A830"
app/src/main/res/drawable/ic_app_warp.xml:40:                android:fillColor="#FF28429E"
app/src/main/res/drawable/ic_app_warp.xml:42:                android:strokeColor="#FF28429E"
app/src/main/res/drawable/ic_app_warp.xml:47:                android:fillColor="#FFFDFEFE"
app/src/main/res/drawable/ic_app_warp.xml:49:                android:strokeColor="#FFFDFEFE"
app/src/main/res/drawable/ic_app_warp.xml:54:                android:fillColor="#FF0984B8"
app/src/main/res/drawable/ic_app_warp.xml:56:                android:strokeColor="#FF0984B8"
app/src/main/res/drawable/ic_app_warp.xml:61:                android:fillColor="#FF1FB293"
app/src/main/res/drawable/ic_app_warp.xml:63:                android:strokeColor="#FF1FB293"
app/src/main/res/drawable/ic_app_warp.xml:68:                android:fillColor="#FFCE409F"
app/src/main/res/drawable/ic_app_warp.xml:70:                android:strokeColor="#FFCE409F"
app/src/main/res/drawable/ic_app_warp.xml:75:                android:fillColor="#FF47B8CE"
app/src/main/res/drawable/ic_app_warp.xml:77:                android:strokeColor="#FF47B8CE"
app/src/main/res/drawable/ic_app_v2ray.xml:20:        android:fillColor="#409ED7" />
app/src/main/res/drawable/ic_app_v2ray.xml:34:            android:fillColor="#FFFFFF" />
app/src/main/res/drawable/ic_app_urnetwork.xml:16:                android:fillColor="#101010"
app/src/main/res/drawable/ic_app_urnetwork.xml:19:                android:strokeColor="#101010"
app/src/main/res/drawable/ic_app_urnetwork.xml:23:                android:fillColor="#d6e5f3"
app/src/main/res/drawable/ic_app_urnetwork.xml:26:                android:strokeColor="#d6e5f3"
app/src/main/res/drawable/ic_app_urnetwork.xml:30:                android:fillColor="#ed91fe"
app/src/main/res/drawable/ic_app_urnetwork.xml:33:                android:strokeColor="#ed91fe"
app/src/main/res/drawable/ic_app_urnetwork.xml:37:                android:fillColor="#88f968"
app/src/main/res/drawable/ic_app_urnetwork.xml:40:                android:strokeColor="#88f968"
app/src/main/res/drawable/ic_app_urnetwork.xml:44:                android:fillColor="#eef6bb"
app/src/main/res/drawable/ic_app_urnetwork.xml:47:                android:strokeColor="#eef6bb"
app/src/main/res/drawable/ic_app_urnetwork.xml:51:                android:fillColor="#fd6d59"
app/src/main/res/drawable/ic_app_urnetwork.xml:54:                android:strokeColor="#fd6d59"
app/src/main/res/drawable/ic_app_tor.xml:17:                <item android:offset="0" android:color="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_tor.xml:18:                <item android:offset="1" android:color="#FFECECF0" />
app/src/main/res/drawable/ic_app_tor.xml:27:                android:fillColor="#FF68B044" />
app/src/main/res/drawable/ic_app_tor.xml:30:                android:fillColor="#FFF5F8DE" />
app/src/main/res/drawable/ic_app_tor.xml:33:                android:fillColor="#FF7E4798" />
app/src/main/res/drawable/ic_app_tor.xml:36:                android:fillColor="#FF010101" />
app/src/main/res/drawable/ic_app_telegram.xml:2:<!-- Telegram: the user's artwork (telegram-svgrepo-com.svg, plane in white on #61A8DE), seated on the app's squircle. -->
app/src/main/res/drawable/ic_app_telegram.xml:4:    <path android:fillColor="#61A8DE" android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z"/>
app/src/main/res/drawable/ic_app_telegram.xml:6:        <path android:fillColor="#FFFFFF" android:pathData="M358.844,100.6L54.091,219.359c-9.871,3.847-9.273,18.012,0.888,21.012l77.441,22.868l28.901,91.706 c3.019,9.579,15.158,12.483,22.185,5.308l40.039-40.882l78.56,57.665c9.614,7.057,23.306,1.814,25.747-9.859l52.031-248.76 C382.431,106.232,370.443,96.08,358.844,100.6z M320.636,155.806L179.08,280.984c-1.411,1.248-2.309,2.975-2.519,4.847 l-5.45,48.448c-0.178,1.58-2.389,1.789-2.861,0.271l-22.423-72.253c-1.027-3.308,0.312-6.892,3.255-8.717l167.163-103.676 C320.089,147.518,324.025,152.81,320.636,155.806z"/>
app/src/main/res/drawable/ic_app_store.xml:26:                    <item android:offset="0" android:color="#18BFFB" />
app/src/main/res/drawable/ic_app_store.xml:27:                    <item android:offset="1" android:color="#2072F3" />
app/src/main/res/drawable/ic_app_store.xml:32:            android:fillColor="#FFFFFF"
app/src/main/res/drawable/ic_app_proton.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FF1C1B33" />
app/src/main/res/drawable/ic_app_proton.xml:22:                    <item android:offset="0" android:color="#FF8EFFEE" />
app/src/main/res/drawable/ic_app_proton.xml:23:                    <item android:offset="0.07" android:color="#FF8EFFEE" />
app/src/main/res/drawable/ic_app_proton.xml:24:                    <item android:offset="0.45" android:color="#FFC9C7FF" />
app/src/main/res/drawable/ic_app_proton.xml:25:                    <item android:offset="1" android:color="#FF7341FF" />
app/src/main/res/drawable/ic_app_proton.xml:36:                    <item android:offset="0" android:color="#FF6D4AFF" />
app/src/main/res/drawable/ic_app_proton.xml:37:                    <item android:offset="0.4" android:color="#FF6D4AFF" />
app/src/main/res/drawable/ic_app_proton.xml:38:                    <item android:offset="1" android:color="#FF00F0C3" />
app/src/main/res/drawable/ic_app_outline.xml:4:    <path android:fillColor="#093828" android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z"/>
app/src/main/res/drawable/ic_app_outline.xml:6:        <path android:fillColor="#2a9d7d" android:pathData="M232.37 105.91C233.8 109.98 232.68 121.57 232.68 126.48C232.68 142.07 232.68 157.65 232.68 173.24C232.68 225.77 232.65 278.29 232.62 330.82C232.61 348.59 232.64 366.35 232.54 384.12C232.51 388.55 233.85 401.41 231.92 404.69C216.4 404.91 189.79 393.78 176.16 386.09C101.76 344.14 77.55 245.53 123.78 173.59C142.01 145.22 169.43 123.68 201.26 112.63C208.49 110.12 215.92 108.13 223.47 106.83C226.25 106.35 229.63 105.24 232.37 105.91Z"/>
app/src/main/res/drawable/ic_app_outline.xml:7:        <path android:fillColor="#ffffff" android:pathData="M279.66 404.69C279.66 384.43 279.66 364.17 279.66 343.91C285.92 341.56 292.55 340.53 298.72 337.77C315.7 330.17 330.02 317.52 339.74 301.65C344.64 293.65 348.11 284.76 350.21 275.64C361.93 224.84 329.83 178.21 280.26 166.69C278.52 163.35 279.65 153.96 279.65 149.86C279.65 140.04 279.65 130.22 279.65 120.4C279.65 115.8 279.05 110.69 279.85 106.17C282.23 105.23 285.43 106.48 287.93 106.86C295.67 108.05 303.32 110.16 310.69 112.76C342.68 124.01 369.54 145.24 388.25 173.57C436.61 246.8 409.01 347.52 332.37 388.27C319.52 395.1 294.08 405.06 279.66 404.69Z"/>
app/src/main/res/drawable/ic_app_openvpn.xml:17:                <item android:offset="0" android:color="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_openvpn.xml:18:                <item android:offset="1" android:color="#FFECECF0" />
app/src/main/res/drawable/ic_app_openvpn.xml:27:                android:fillColor="#FF1A3967"
app/src/main/res/drawable/ic_app_openvpn.xml:31:                android:fillColor="#FFED7F22"
app/src/main/res/drawable/ic_app_npv.xml:16:                android:fillColor="#0a100e"
app/src/main/res/drawable/ic_app_npv.xml:19:                android:strokeColor="#0a100e"
app/src/main/res/drawable/ic_app_npv.xml:23:                android:fillColor="#54e08c"
app/src/main/res/drawable/ic_app_npv.xml:26:                android:strokeColor="#54e08c"
app/src/main/res/drawable/ic_app_settings.xml:24:                <item android:offset="0" android:color="#D3D7DD" />
app/src/main/res/drawable/ic_app_settings.xml:25:                <item android:offset="1" android:color="#888C90" />
app/src/main/res/drawable/ic_app_settings.xml:32:        android:fillColor="#2E2E30" />
app/src/main/res/drawable/ic_app_settings.xml:36:        android:fillColor="#000000" />
app/src/main/res/drawable/ic_app_settings.xml:40:        android:fillColor="#7F7F7F" />
app/src/main/res/drawable/ic_app_settings.xml:44:        android:fillColor="#777777" />
app/src/main/res/drawable/ic_app_settings.xml:54:                <item android:offset="0" android:color="#D9DADB" />
app/src/main/res/drawable/ic_app_settings.xml:55:                <item android:offset="1" android:color="#7D8082" />
app/src/main/res/drawable/ic_app_lantern.xml:17:                android:fillColor="#FF00BCD4" />
app/src/main/res/drawable/ic_app_lantern.xml:23:                            android:fillColor="#FFFEE600" />
app/src/main/res/drawable/ic_app_lantern.xml:26:                            android:fillColor="#FF111111" />
app/src/main/res/drawable/ic_app_iran.xml:31:        android:fillColor="#FFFFFF" />
app/src/main/res/drawable/ic_app_iran.xml:41:            android:fillColor="#121111" />
app/src/main/res/drawable/ic_app_gscript.xml:19:        android:fillColor="#FFFFFF" />
app/src/main/res/drawable/ic_app_gscript.xml:30:            android:fillColor="#4285F4" />
app/src/main/res/drawable/ic_app_gscript.xml:33:            android:fillColor="#34A853" />
app/src/main/res/drawable/ic_app_gscript.xml:36:            android:fillColor="#FBBC05" />
app/src/main/res/drawable/ic_app_gscript.xml:39:            android:fillColor="#EA4335" />
app/src/main/res/drawable/ic_app_masque.xml:3:    Home tile «masque» — desktop artwork g-masque-art, cover tile on #FFFFFF.
app/src/main/res/drawable/ic_app_masque.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_masque.xml:19:                android:fillColor="#FFFFFFFF"
app/src/main/res/drawable/ic_app_masque.xml:21:                android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_app_masque.xml:26:                android:fillColor="#FF3E91FF"
app/src/main/res/drawable/ic_app_masque.xml:28:                android:strokeColor="#FF020202"
app/src/main/res/drawable/ic_app_github.xml:15:                <item android:offset="0" android:color="#FF4B4D55" />
app/src/main/res/drawable/ic_app_github.xml:16:                <item android:offset="1" android:color="#FF25262B" />
app/src/main/res/drawable/ic_app_github.xml:21:        <path android:pathData="M12 .5C5.65.5.5 5.65.5 12c0 5.08 3.29 9.39 7.86 10.91.58.1.79-.25.79-.56 0-.27-.01-1.17-.02-2.12-3.2.7-3.88-1.36-3.88-1.36-.52-1.34-1.28-1.7-1.28-1.7-1.05-.72.08-.7.08-.7 1.16.08 1.77 1.19 1.77 1.19 1.03 1.76 2.7 1.26 3.36.96.1-.75.4-1.26.73-1.55-2.55-.29-5.24-1.28-5.24-5.69 0-1.26.45-2.29 1.19-3.09-.12-.29-.52-1.47.11-3.06 0 0 .97-.31 3.18 1.18a11 11 0 0 1 5.79 0c2.2-1.49 3.18-1.18 3.18-1.18.63 1.59.23 2.77.12 3.06.74.8 1.18 1.83 1.18 3.09 0 4.42-2.69 5.39-5.25 5.68.41.36.78 1.06.78 2.13 0 1.54-.01 2.79-.01 3.17 0 .31.21.67.8.56A10.52 10.52 0 0 0 23.5 12C23.5 5.65 18.35.5 12 .5z" android:fillColor="#FFFFFFFF" android:fillType="evenOdd" />
app/src/main/res/drawable/ic_app_geph.xml:3:    Home tile «geph» — desktop artwork g-geph-art, cover tile on #FFFFFF.
app/src/main/res/drawable/ic_app_geph.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_geph.xml:19:                android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_geph.xml:22:                android:fillColor="#FF384445" />
app/src/main/res/drawable/ic_app_geph.xml:25:                android:fillColor="#FF6BC8C7" />
app/src/main/res/drawable/ic_app_geph.xml:28:                android:fillColor="#FFBEEBEB" />
app/src/main/res/drawable/ic_app_gateway.xml:3:    Home tile «gateway» — desktop artwork g-gateway-art, cover tile on #FFFFFF.
app/src/main/res/drawable/ic_app_gateway.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_gateway.xml:19:                android:fillColor="#FFFFFFFF"
app/src/main/res/drawable/ic_app_gateway.xml:21:                android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_app_gateway.xml:26:                android:fillColor="#FF673BB5"
app/src/main/res/drawable/ic_app_gateway.xml:28:                android:strokeColor="#FF673BB5"
app/src/main/res/drawable/ic_app_gateway.xml:33:                android:fillColor="#FFCCCCCC"
app/src/main/res/drawable/ic_app_gateway.xml:35:                android:strokeColor="#FFCCCCCC"
app/src/main/res/drawable/ic_app_gateway.xml:40:                android:fillColor="#FF3F51B4"
app/src/main/res/drawable/ic_app_gateway.xml:42:                android:strokeColor="#FF3F51B4"
app/src/main/res/drawable/ic_app_gateway.xml:47:                android:fillColor="#FF4CB050"
app/src/main/res/drawable/ic_app_gateway.xml:49:                android:strokeColor="#FF4CB050"
app/src/main/res/drawable/ic_app_gateway.xml:54:                android:fillColor="#FFFE9701"
app/src/main/res/drawable/ic_app_gateway.xml:56:                android:strokeColor="#FFFE9701"
app/src/main/res/drawable/ic_app_gateway.xml:61:                android:fillColor="#FFF34236"
app/src/main/res/drawable/ic_app_gateway.xml:63:                android:strokeColor="#FFF34236"
app/src/main/res/drawable/ic_app_dns.xml:19:        android:fillColor="#34C759" />
app/src/main/res/drawable/ic_app_dns.xml:29:            android:strokeColor="#FFFFFF"
app/src/main/res/drawable/ic_app_dns.xml:32:            android:fillColor="#00000000" />
app/src/main/res/drawable/ic_app_dns.xml:35:            android:strokeColor="#FFFFFF"
app/src/main/res/drawable/ic_app_dns.xml:38:            android:fillColor="#00000000" />
app/src/main/res/drawable/ic_app_cloudflare.xml:3:    Home tile «cloud» — desktop artwork g-cloudflare-art, cover tile on #FFFFFF.
app/src/main/res/drawable/ic_app_cloudflare.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_cloudflare.xml:19:                android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_cloudflare.xml:22:                android:fillColor="#FFF38020" />
app/src/main/res/drawable/ic_app_cloudflare.xml:25:                android:fillColor="#FFFAAE40" />
app/src/main/java/com/mlmvpn/scanner/engines/tgproxy/TgProbe.kt:157:         * An unencrypted `req_pq_multi#be7e8ef1 nonce:int128` in the intermediate transport:
app/src/main/java/com/mlmvpn/scanner/engines/tgproxy/TgProbe.kt:173:        /** True when [plain] is a `res_pq#05162463` that repeats [nonce]. */
app/src/main/java/com/mlmvpn/scanner/engines/windscribe/WindscribeModels.kt:152:    // build with "Integer cannot be cast to Long" (crash #962, R8-merged selectors).
app/src/main/java/com/mlmvpn/scanner/engines/proton/ProtonAccess.kt:134:     * from this thread (1.2.42 #2276).
app/src/main/java/com/mlmvpn/scanner/engines/proton/ProtonStealth.kt:852:        // runs on the main thread (ANR #844, #848). The lease goes as soon as that dial lets go.
app/src/main/java/com/mlmvpn/scanner/engines/gst/GstTunEngine.kt:103:     * MhrvVpnService.teardown() (which fixed a SIGSEGV — see their #700):
app/src/main/java/com/mlmvpn/scanner/engines/gst/GstEngine.kt:33:        // service and closed the app (1.2.42 #2215): the connect fails instead.
app/src/main/java/com/mlmvpn/scanner/engines/game/booster/session/GameBoostService.kt:354:     * was stopped (1.2.42 #2256).
app/src/main/java/com/mlmvpn/scanner/engines/fenix/FenixEngine.kt:455:                // copy taken while another answer is written throws (crash #471).
app/src/main/java/com/mlmvpn/scanner/engines/amnezia/AmzTester.kt:496:     * cores have just closed theirs (EADDRINUSE, crash #582): a few tries, then a random high port.
app/src/main/java/com/mlmvpn/scanner/utils/SafeClip.kt:11: * #2211). A text that long is refused with a message instead.
app/src/main/java/com/mlmvpn/scanner/utils/SafeStart.kt:46:        // DeadSystemRuntimeException: the system server is restarting (crash #730). Nothing can
app/src/main/java/com/mlmvpn/scanner/tun/Tun2proxyHostService.kt:113:        // mlmvpn/crashes #1120: every one of its 57 reports of 1.2.43 and 1.2.44). Put in place
app/src/main/java/com/mlmvpn/scanner/tun/Tun2proxyHostService.kt:163:            // close (mlmvpn/crashes #150, #28: 61 reports of 1.2.43 and 1.2.44), and a next
app/src/main/java/com/mlmvpn/scanner/ui/tunnel/TunnelController.kt:247:        // busy the disconnect button froze the app for seconds (1.2.42 #2109, #2391).
app/src/main/java/com/mlmvpn/scanner/ui/scout/ScoutScreen.kt:45:        // on the main thread for Android to call the app frozen (1.2.42 #2301): loaded here first.
app/src/main/java/com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt:725:        // (width or height 0) threw «width and height must be > 0» (1.2.42 #2344, #2410).
app/src/main/java/com/mlmvpn/scanner/ui/npv/NpvScreen.kt:246:    // (1.2.42 #2408).
app/src/main/java/com/mlmvpn/scanner/ui/mae/MaeAppIcon.kt:66: * enough starting threads for Android to call the app frozen (1.2.42 #2021).
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt:105:    // it took seconds on slow phones, and the home screen froze while it drew (1.2.42 #2110, #2327).
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt:128:        // home screen frozen behind the tap (1.2.42 #2374): asked off the main thread.
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:469:            // home screen (ANR #719).
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:518:            // (1.2.42 #2302).
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:537:            // Rendered on a worker thread (ANR #659); the sharp preset shows until it is ready.
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:704:    // screen for seconds on slower phones (ANR #489, #659, #719). The glass is plain until it is ready.
app/src/main/java/com/mlmvpn/scanner/ui/home/AppIcon.kt:294:    // slow phones for nine seconds (1.2.42 #2033, #2206). Blank for the first frame or two.
app/src/main/java/com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt:404: * the OPAQUE #1C1C1E the palette keeps only as the no-wallpaper fallback — so a row of users sat as
app/src/main/java/com/mlmvpn/scanner/ui/OpenVpnScreen.kt:186:            onCreate = { runCatching { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tunnelbear.com/account/signup"))) } /* no browser installed: crash #576 */ },
app/src/main/java/com/mlmvpn/scanner/ui/OpenVpnScreen.kt:200:            onSite = { runCatching { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tunnelbear.com/account"))) } /* no browser installed: crash #576 */ },
app/src/main/java/com/mlmvpn/scanner/ui/NodesTab.kt:864:                    // a character): thousands of configs crashed the app (crash #677). As many
app/src/main/java/com/mlmvpn/scanner/ui/AddNodeScreen.kt:142:                // out of memory (crashes #697, #702).
app/src/main/java/com/mlmvpn/scanner/ui/AddNodeModal.kt:99:        // (#473, seen again on 1.2.42): it is shown as written instead.
app/src/main/java/com/mlmvpn/scanner/ui/AddNodeModal.kt:469:    "trojan://humanity@127.0.0.1:40443?security=tls&sni=www.calmloud.com&fp=chrome&alpn=http%2F1.1&type=ws&host=www.calmloud.com&path=%2Fassignment#DADA%20%40TheVPNMethod",
app/src/main/java/com/mlmvpn/scanner/lan/LanSetupServer.kt:512:        :root{--bg:#0f1513;--card:#18211f;--ink:#e8efed;--dim:#9aa8a5;--accent:#4ecdc4;
app/src/main/java/com/mlmvpn/scanner/lan/LanSetupServer.kt:513:        --ok:#63c795;--warn:#d6a544;--line:#26332f}
app/src/main/java/com/mlmvpn/scanner/lan/LanSetupServer.kt:514:        @media(prefers-color-scheme:light){:root{--bg:#f3f6f6;--card:#fff;--ink:#16211f;
app/src/main/java/com/mlmvpn/scanner/lan/LanSetupServer.kt:515:        --dim:#5b6a68;--accent:#0e7c7b;--ok:#2e7d5b;--warn:#8a5a06;--line:#dde5e4}}
app/src/main/java/com/mlmvpn/scanner/lan/LanSetupServer.kt:534:        button{flex:none;border:0;border-radius:8px;background:var(--accent);color:#03211f;
app/src/main/java/com/mlmvpn/scanner/data/NodeManager.kt:361:        // the list long enough for the screen, waiting to read its size, to freeze (ANR #721).
app/src/main/java/com/mlmvpn/scanner/data/GroupManager.kt:82:     * and the app ran out of memory (crashes #509-#741, 1.2.40). Read and written a node at a time.
app/src/main/java/com/mlmvpn/scanner/MlmApp.kt:21:        // Started before the first unlock after a reboot (a Xiaomi, mlmvpn/crashes #3220): every
app/src/main/java/com/mlmvpn/scanner/MlmApp.kt:38:        // service after a reboot), and the app stood frozen at launch (ANR #542, #543).
app/src/main/java/com/mlmvpn/scanner/MainActivity.kt:44:     * reports #2371, #2373). It says so instead.
app/src/main/java/com/mlmvpn/core/tunnel/TunnelVpnService.kt:173: * `checkDNSAnswerIP#1767: IP is bogon`. The resolver got an *answer*, and the
app/src/main/java/com/mlmvpn/core/tunnel/TunnelVpnService.kt:395:                // wrapped in an Error, and that closed the app (1.2.42 #2006, #2011, #2013, #2014).
app/src/main/java/com/mlmvpn/core/tunnel/TunnelVpnService.kt:722:     *    "connect: connection timed out" / "i/o timeout" from tcpDial#308. Not
app/src/main/java/com/mlmvpn/core/tunnel/TunnelVpnService.kt:1086:         * Sampled from the launcher artwork's neon ring (#70E0B0 region), so the
app/src/main/java/com/mlmvpn/core/tunnel/TunnelVpnService.kt:2691:                // without its config went up too, or the same exception followed (1.2.42 #2460).
app/src/main/java/com/mlmvpn/core/tunnel/TunnelVpnService.kt:2909:        // every connect (1.2.42 #2335).
app/src/main/java/com/mlmvpn/core/tunnel/TunnelVpnService.kt:6599:     * (1.2.42 #2283). A connect that starts the foreground again in the meantime wins.
app/src/main/java/com/mlmvpn/core/tunnel/TunnelVpnService.kt:6628:        // Some ROMs refuse it even with the permission declared (crash #581). A VPN service runs
app/src/main/java/com/mlmvpn/core/warp/XrayHosts.kt:53:                // core was removed at the same moment -- NoSuchElementException (1.2.42 #2018).
app/src/main/java/com/mlmvpn/core/warp/WarpIdRelay.kt:70:    /** Sealed off the caller's thread: a stop pressed on the screen waited on the Keystore (ANR #662). */
app/src/main/java/com/mlmvpn/core/warp/WarpIdRelay.kt:118:     * draw, and opening the sealed key on the main thread froze the WireGuard tab (ANR #870).
app/src/main/java/com/mlmvpn/core/warp/Nat64Probe.kt:123:     * and copying the plain file that is not there crashed every ping (1.2.40, crash #453).
app/src/main/java/com/mlmvpn/core/warp/AwgHostClient.kt:119:     * worker sat in here holding it (freeze report #1026, 2026-10-11).
app/src/main/java/com/mlmvpn/core/warp/AwgHostClient.kt:147:                    // (Outline's WARP probe on a tester's phone, 2026-10-11, freeze report #1026).
app/src/main/java/com/mlmvpn/core/lantern/LanternEngine.kt:391:                // the tile is stopping: no answer, not a crash (1.2.42 #2006, #2013)
```


### Color(0x...) literals — 116 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:45:    val blush = Color(0xFFF6DCE4)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:46:    val rose = Color(0xFFE9BACB)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:47:    val coral = Color(0xFFE98583)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:48:    val navy = Color(0xFF293451)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:49:    val blueGray = Color(0xFF58657A)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:50:    val cream = Color(0xFFFFF8F1)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:51:    val white = Color(0xFFFFFFFF)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:58:    val cloudflareOrange = Color(0xFFF6821F)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:59:    val cloudflareOrangeLight = Color(0xFFFFA94D)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:60:    val telegramBlue = Color(0xFF61A8DE)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:61:    val brandCyan = Color(0xFF18BFFB)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:62:    val brandBlue = Color(0xFF2072F3)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:64:        Color(0xFF0A84FF), Color(0xFF30D158), Color(0xFFFF9F0A), Color(0xFFFF375F),
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:65:        Color(0xFFBF5AF2), Color(0xFF64D2FF), Color(0xFFFF6482), Color(0xFF5E5CE6),
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:67:    val lavenderNight = Color(0xFF27263E)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:68:    val navyNight = Color(0xFF191D32)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:69:    val nightCard = Color(0xFF34334D)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:70:    val nightCoral = Color(0xFFFFA39B)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:71:    val nightBlue = Color(0xFF9BB6E3)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:72:    val lightText = Color(0xFF293451)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:73:    val nightText = Color(0xFFF8F2FA)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:74:    val nightMuted = Color(0xFFC1BED0)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:75:    val lightMuted = Color(0xFF595F72)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:76:    val nightVariantText = Color(0xFFD0CDE0)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:77:    val lightVariant = Color(0xFFF0E5E8)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:78:    val lightVariantText = Color(0xFF515B70)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:79:    val nightOutline = Color(0xFF77758E)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:80:    val lightOutline = Color(0xFF9BA5B6)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:81:    val nightError = Color(0xFFFFB4AB)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:82:    val lightError = Color(0xFFB3261E)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:83:    val nightErrorText = Color(0xFF690005)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:84:    val nightTertiary = Color(0xFFD8B5E2)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:85:    val nightOnPrimaryContainer = Color(0xFFFFE0DB)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:92:    val secondary: Color @Composable get() = if (Appearance.isDark()) Color(0xFFA9B1CE) else blueGray
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:99:    val success: Color @Composable get() = if (Appearance.isDark()) Color(0xFF9BCFB4) else Color(0xFF43765A)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:100:    val disabled: Color @Composable get() = if (Appearance.isDark()) Color(0xFF9B98AD) else Color(0xFF8E93A0)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:103:    val indigo: Color @Composable get() = if (Appearance.isDark()) Color(0xFFB3A9E5) else Color(0xFF6E69A8)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:104:    val purple: Color @Composable get() = if (Appearance.isDark()) Color(0xFFD4B6E6) else Color(0xFF8F659F)
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:83:            base = Color(0xFF241A16),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:85:                Blob(0.20f, 0.16f, 0.85f, Color(0xFF5C4033)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:86:                Blob(0.86f, 0.34f, 0.62f, Color(0xFF4A2F26)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:87:                Blob(0.50f, 0.94f, 0.75f, Color(0xFF1A1210)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:92:            base = Color(0xFF2A1E12),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:94:                Blob(0.78f, 0.14f, 0.80f, Color(0xFF7A5320)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:95:                Blob(0.14f, 0.52f, 0.66f, Color(0xFF4B331A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:96:                Blob(0.55f, 0.98f, 0.70f, Color(0xFF1C1409)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:101:            base = Color(0xFF161A2E),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:103:                Blob(0.24f, 0.20f, 0.82f, Color(0xFF33407A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:104:                Blob(0.84f, 0.60f, 0.68f, Color(0xFF283155)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:105:                Blob(0.50f, 0.96f, 0.72f, Color(0xFF0E1120)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:110:            base = Color(0xFF1A1A1C),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:112:                Blob(0.30f, 0.18f, 0.86f, Color(0xFF3A3A3F)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:113:                Blob(0.82f, 0.70f, 0.60f, Color(0xFF2A2A2E)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:114:                Blob(0.50f, 1.00f, 0.70f, Color(0xFF101012)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:119:            base = Color(0xFF1E2626),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:121:                Blob(0.22f, 0.24f, 0.84f, Color(0xFF44585A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:122:                Blob(0.80f, 0.22f, 0.58f, Color(0xFF35494B)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:123:                Blob(0.55f, 0.95f, 0.72f, Color(0xFF141A1A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:128:            base = Color(0xFF221831),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:130:                Blob(0.76f, 0.18f, 0.80f, Color(0xFF56317A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:131:                Blob(0.18f, 0.58f, 0.66f, Color(0xFF3A2352)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:132:                Blob(0.50f, 0.98f, 0.70f, Color(0xFF150F1F)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:137:            base = Color(0xFF10222B),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:139:                Blob(0.26f, 0.22f, 0.84f, Color(0xFF1F4E63)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:140:                Blob(0.84f, 0.56f, 0.64f, Color(0xFF17394A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:141:                Blob(0.50f, 0.97f, 0.72f, Color(0xFF0A161C)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:146:            base = Color(0xFF1C2024),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:148:                Blob(0.20f, 0.28f, 0.82f, Color(0xFF3B444E)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:149:                Blob(0.86f, 0.24f, 0.58f, Color(0xFF2C333B)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:150:                Blob(0.50f, 0.98f, 0.70f, Color(0xFF111417)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:155:            base = Color(0xFF2B1720),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:157:                Blob(0.72f, 0.20f, 0.82f, Color(0xFF7B3446)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:158:                Blob(0.16f, 0.44f, 0.64f, Color(0xFF4C2130)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:159:                Blob(0.50f, 0.98f, 0.72f, Color(0xFF1A0E14)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:164:            base = Color(0xFF15231A),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:166:                Blob(0.24f, 0.20f, 0.84f, Color(0xFF2C4E36)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:167:                Blob(0.82f, 0.62f, 0.62f, Color(0xFF203A28)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:168:                Blob(0.50f, 0.98f, 0.70f, Color(0xFF0C150F)),
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:81:    val Blue   = Color(0xFF007AFF)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:82:    val Green  = Color(0xFF34C759)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:83:    val Indigo = Color(0xFF5856D6)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:84:    val Orange = Color(0xFFFF9500)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:85:    val Pink   = Color(0xFFFF2D55)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:86:    val Purple = Color(0xFFAF52DE)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:87:    val Red    = Color(0xFFFF3B30)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:88:    val Teal   = Color(0xFF5AC8FA)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:89:    val Yellow = Color(0xFFFFCC00)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:90:    val Gray   = Color(0xFF8E8E93)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:92:    val Top = Color(0xFF1C1C1E)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:93:    val Bottom = Color(0xFF000000)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:94:    val Lane = Color(0xFF2C2C2E)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:95:    val LaneLine = Color(0x26FFFFFF)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:97:    val Secondary = Color(0x99EBEBF5)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:98:    val LightHousing = Color(0xFF0A0A0A)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:99:    val LightOff = Color(0xFF3A1414)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:100:    val LightOn = Color(0xFFFF3B30)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:101:    val Go = Color(0xFF30D158)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:102:    val Out = Color(0xFFFF453A)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:103:    val Progress = Color(0xFF0A84FF)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:104:    val Gold = Color(0xFFFFD60A)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:105:    val Silver = Color(0xFFC7C7CC)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:106:    val Bronze = Color(0xFFC08B5C)
app/src/main/java/com/mlmvpn/scanner/ui/SafeWebView.kt:27:        setTextColor(0xFFB0B0B5.toInt())
app/src/main/java/com/mlmvpn/scanner/ui/AppScreen.kt:272:                            Modifier.background(if (darkTheme) Color(0xFF000000) else Color(0xFF1C1C1E))
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:37:    private val Blue = Color(0xFF0A84FF)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:38:    private val Indigo = Color(0xFF5E5CE6)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:39:    private val Green = Color(0xFF32D74B)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:40:    private val Orange = Color(0xFFFF9F0A)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:41:    private val Purple = Color(0xFFBF5AF2)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:42:    private val Pink = Color(0xFFFF375F)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:43:    private val Teal = Color(0xFF40C8E0)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:44:    private val Gray = Color(0xFF8E8E93)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:45:    private val Red = Color(0xFFFF453A)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:46:    private val CfOrange = Color(0xFFF6821F)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:362:            icon = StoreIcon(Icons.Default.AutoAwesome, Color(0xFF5E5CE6), tint2 = CfOrange),
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:574:            icon = StoreIcon(Icons.Default.SwapHoriz, Orange, tint2 = Color(0xFFFFD60A)),
```


### Legacy gs_* references — 0 hit(s)

No matches.


### Legacy Ios.* references — 0 hit(s)

No matches.


### Physical left/right APIs — 5 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/ui/mae/MaeScreen.kt:427:                modifier = Modifier.matchParentSize().wrapContentWidth(AbsoluteAlignment.Right),
app/src/main/java/com/mlmvpn/scanner/ui/mae/MaeScreen.kt:437:                modifier = Modifier.matchParentSize().wrapContentWidth(AbsoluteAlignment.Left),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:428:        layoutDirection = LayoutDirection.Ltr,
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:675:        layoutDirection = LayoutDirection.Ltr,
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeSearch.kt:423:                    textAlign = if (rtl) TextAlign.Right else TextAlign.Left,
```


### Absolute layout offsets (manual RTL review) — 14 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/ui/settings/IosSettings.kt:623: * rather than `absoluteOffset` on purpose -- in Persian the stack should build from the right,
app/src/main/java/com/mlmvpn/scanner/ui/mae/MaeScreen.kt:14:import androidx.compose.foundation.layout.absoluteOffset
app/src/main/java/com/mlmvpn/scanner/ui/mae/MaeScreen.kt:441:        Box(Modifier.fillMaxWidth().absoluteOffset { IntOffset(offset.value.roundToInt(), 0) }) {
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeScreen.kt:26:import androidx.compose.foundation.layout.absoluteOffset
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeScreen.kt:808:                                    .absoluteOffset { position.value.toIntOffset() }
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeScreen.kt:976:                .absoluteOffset(x = -(d * 0.32f), y = -(d * 0.32f))
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeFolders.kt:19:import androidx.compose.foundation.layout.absoluteOffset
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeFolders.kt:409:                                            .absoluteOffset { position.value.toIntOffset() }
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt:19:import androidx.compose.foundation.layout.absoluteOffset
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt:507:            .absoluteOffset(x = (-6).dp, y = (-6).dp)
app/src/main/java/com/mlmvpn/scanner/ui/home/AppIcon.kt:13:import androidx.compose.foundation.layout.absoluteOffset
app/src/main/java/com/mlmvpn/scanner/ui/home/AppIcon.kt:124:            .absoluteOffset(y = -(h * 0.45f))
app/src/main/java/com/mlmvpn/scanner/ui/home/AppIcon.kt:151:            .absoluteOffset(x = d * 0.34f, y = -(d * 0.34f))
app/src/main/java/com/mlmvpn/scanner/ui/home/AppIcon.kt:189:            .absoluteOffset(x = -(dot * 0.22f), y = -(dot * 0.22f))
```


### Default Material colorScheme references — 11 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:359:                color = MaterialTheme.colorScheme.primary,
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:365:                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.10f))
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:430:        AetherStage.STOPPED, AetherStage.IDLE -> MaterialTheme.colorScheme.primary
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:612:                    .background(if (on) MaterialTheme.colorScheme.primary else Color.Transparent)
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:657:                                activeIdx == idx -> MaterialTheme.colorScheme.primary
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:784:                Icon(Icons.Default.Bolt, null, tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:815:                    containerColor = if (selected) MaterialTheme.colorScheme.primary else DuskTheme.navyNight,
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:842:                colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary))
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:929:                colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary))
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:1180:                    else -> MaterialTheme.colorScheme.onSurface
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:1238:                    else if (active) MaterialTheme.colorScheme.primary.copy(alpha = 0.7f) else DuskTheme.muted)
```


### Material component color-default calls (review explicit arguments) — 18 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/ui/theme/IosButtons.kt:20:fun iosButtonColors(accent: Color = DuskTheme.primary): ButtonColors = ButtonDefaults.buttonColors(
app/src/main/java/com/mlmvpn/scanner/ui/theme/IosButtons.kt:34:fun iosNeutralButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
app/src/main/java/com/mlmvpn/scanner/ui/theme/IosButtons.kt:48:fun iosFieldColors(): TextFieldColors = TextFieldDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/ui/settings/SmartIranPage.kt:210:            colors = SwitchDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/ui/settings/IosSettings.kt:399:                colors = SwitchDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt:751:            colors = androidx.compose.material3.SwitchDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:780:        colors = CardDefaults.cardColors(containerColor = DuskTheme.surface)
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:842:                colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary))
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:929:                colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary))
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:1276:        colors = CardDefaults.cardColors(containerColor = DuskTheme.surface)
app/src/main/java/com/mlmvpn/scanner/ui/GameTab.kt:606:                    colors = CardDefaults.cardColors(containerColor = GameColors.SurfaceDark),
app/src/main/java/com/mlmvpn/scanner/ui/CloudTab.kt:2476:            colors = androidx.compose.material3.ButtonDefaults.textButtonColors(contentColor = DuskTheme.primary),
app/src/main/java/com/mlmvpn/scanner/ui/BpbSettingsModal.kt:62:    val switchColors = SwitchDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:195:                                colors = ButtonDefaults.buttonColors(
app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:396:                                colors = ButtonDefaults.buttonColors(
app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:557:                            colors = ButtonDefaults.buttonColors(
app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:850:                        colors = androidx.compose.material3.SwitchDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:878:                    colors = ButtonDefaults.buttonColors(
```


### Remaining Toast calls — 8 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/widget/WidgetActionActivity.kt:152:        runCatching { Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show() }
app/src/main/java/com/mlmvpn/scanner/ui/npv/NpvImportActivity.kt:33:        Toast.makeText(this, when {
app/src/main/java/com/mlmvpn/scanner/engines/github/GtEngine.kt:1040:            try { android.widget.Toast.makeText(app, msg, android.widget.Toast.LENGTH_LONG).show() } catch (_: Exception) {}
app/src/main/java/com/mlmvpn/scanner/lan/LanNotification.kt:162:                    Toast.makeText(context, S(R.string.lanweb_copied), Toast.LENGTH_SHORT).show()
app/src/main/java/com/mlmvpn/scanner/backup/BackupStage.kt:51:                runCatching { Toast.makeText(app, resultMessage(done), Toast.LENGTH_LONG).show() }
app/src/main/java/com/mlmvpn/scanner/VpnTileService.kt:175:            Toast.makeText(this@VpnTileService, msg, Toast.LENGTH_SHORT).show()
app/src/main/java/com/mlmvpn/scanner/MainActivity.kt:53:            android.widget.Toast.makeText(this, getString(R.string.screen_missing_on_device), android.widget.Toast.LENGTH_LONG).show()
app/src/main/java/com/mlmvpn/core/aether/AetherScanService.kt:246:                android.widget.Toast.makeText(context,
```


## CI results for the audited baseline

- [Android debug build — run 46064743749](https://github.com/Mamaliayda/eternal_vpn/actions/runs/46064743749): **success**, commit `fa771880`.
- [Android APK/release — run 46064743731](https://github.com/Mamaliayda/eternal_vpn/actions/runs/46064743731): **success**, commit `fa771880`.

## Checklist total change

Checklist row count increased from 1,203 to 1,205 (**net +2**). The two net-new checkbox items are the adaptive/monochrome launcher icon row and the `values/dimens.xml` centralized native radii row. The pre-existing `values/themes.xml` row was replaced with a more explicit description of its scope; that wording change does not add a checklist item.

## Post-remediation final-grep pass (current working tree; pre-push)

The first hex query intentionally used a broad `3–8` digit expression and caught issue IDs/protocol comments as well as colors. The final pass uses a color-sized 6/8-digit expression. All output below is verbatim and classified after inspection. The post-fix UI scan finds no per-component ad-hoc color literals; remaining source/resource hits are centralized palettes, fixed branded artwork, or protocol IDs.

### Strict #hex (6 or 8 digits) — 176 hit(s)

```text
app/src/main/res/values/colors.xml:5:    <color name="white">#FFFFFF</color>
app/src/main/res/values/colors.xml:6:    <color name="black">#000000</color>
app/src/main/res/values/colors.xml:7:    <color name="dusk_background">#191D32</color>
app/src/main/res/values/colors.xml:8:    <color name="dusk_surface">#34334D</color>
app/src/main/res/values/colors.xml:9:    <color name="dusk_surface_raised">#27263E</color>
app/src/main/res/values/colors.xml:10:    <color name="dusk_panel">#34334D</color>
app/src/main/res/values/colors.xml:11:    <color name="dusk_card">#34334D</color>
app/src/main/res/values/colors.xml:12:    <color name="dusk_outline">#77758E</color>
app/src/main/res/values/colors.xml:13:    <color name="dusk_outline_variant">#4A4962</color>
app/src/main/res/values/colors.xml:14:    <color name="dusk_primary">#9BB6E3</color>
app/src/main/res/values/colors.xml:15:    <color name="dusk_primary_dim">#728EBB</color>
app/src/main/res/values/colors.xml:16:    <color name="dusk_primary_container">#27263E</color>
app/src/main/res/values/colors.xml:17:    <color name="dusk_secondary">#C1BED0</color>
app/src/main/res/values/colors.xml:18:    <color name="dusk_secondary_container">#34334D</color>
app/src/main/res/values/colors.xml:19:    <color name="dusk_error">#FFB4AB</color>
app/src/main/res/values/colors.xml:20:    <color name="dusk_error_container">#690005</color>
app/src/main/res/values/colors.xml:21:    <color name="dusk_warning">#E9BACB</color>
app/src/main/res/values/colors.xml:22:    <color name="dusk_on_surface">#F8F2FA</color>
app/src/main/res/values/colors.xml:23:    <color name="dusk_on_surface_variant">#D0CDE0</color>
app/src/main/res/values/colors.xml:24:    <color name="dusk_on_surface_dim">#C1BED0</color>
app/src/main/res/values/colors.xml:26:    <color name="dusk_qr_surface">#FFFFFF</color>
app/src/main/res/values/colors.xml:28:    <color name="dusk_studio_preview_gradient_start">#E6223A57</color>
app/src/main/res/values/colors.xml:29:    <color name="dusk_studio_preview_gradient_end">#F00A1626</color>
app/src/main/res/values/colors.xml:30:    <color name="dusk_studio_preview_frame">#40FFFFFF</color>
app/src/main/res/values/colors.xml:31:    <color name="dusk_studio_preview_glow">#2A39E58C</color>
app/src/main/res/values/colors.xml:32:    <color name="dusk_studio_preview_accent">#FF39E58C</color>
app/src/main/res/values/colors.xml:33:    <color name="dusk_studio_preview_status">#FF34C759</color>
app/src/main/res/values/colors.xml:34:    <color name="dusk_studio_preview_title">#E6FFFFFF</color>
app/src/main/res/values/colors.xml:35:    <color name="dusk_studio_preview_subtitle">#99FFFFFF</color>
app/src/main/res/values/colors.xml:36:    <color name="dusk_studio_preview_value">#99FFFFFF</color>
app/src/main/res/values/colors.xml:37:    <color name="dusk_studio_preview_hint">#26FFFFFF</color>
app/src/main/res/values/colors.xml:38:    <color name="cloudflare_brand_orange">#F38020</color>
app/src/main/res/values/colors.xml:40:    <color name="dusk_widget_warp_start">#FFFFA82E</color>
app/src/main/res/values/colors.xml:41:    <color name="dusk_widget_warp_end">#FFDB8000</color>
app/src/main/res/values/colors.xml:42:    <color name="dusk_widget_fronting_start">#FFFFD52E</color>
app/src/main/res/values/colors.xml:43:    <color name="dusk_widget_fronting_end">#FFDBAF00</color>
app/src/main/res/values/colors.xml:44:    <color name="dusk_widget_emergency_start">#FFFF5E55</color>
app/src/main/res/values/colors.xml:45:    <color name="dusk_widget_emergency_end">#FFDB3329</color>
app/src/main/res/values/colors.xml:46:    <color name="dusk_widget_antisanction_start">#FF59D177</color>
app/src/main/res/values/colors.xml:47:    <color name="dusk_widget_antisanction_end">#FF2DAB4D</color>
app/src/main/res/values/colors.xml:48:    <color name="dusk_widget_quick_start">#FF2E92FF</color>
app/src/main/res/values/colors.xml:49:    <color name="dusk_widget_quick_end">#FF0069DB</color>
app/src/main/res/values/colors.xml:50:    <color name="dusk_nav_pressed_overlay">#288AB4F8</color>
app/src/main/res/values/colors.xml:51:    <color name="dusk_chip_green_overlay">#1A81C995</color>
app/src/main/res/values/colors.xml:52:    <color name="dusk_chip_blue_overlay">#1A8AB4F8</color>
app/src/main/res/values/colors.xml:53:    <color name="dusk_badge_active_fill">#1A0DFF9E</color>
app/src/main/res/values/colors.xml:54:    <color name="dusk_badge_active_stroke">#4D0DFF9E</color>
app/src/main/res/drawable/ic_app_wireguard.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FF88171A" />
app/src/main/res/drawable/ic_app_wireguard.xml:19:                android:fillColor="#FF88171A" />
app/src/main/res/drawable/ic_app_wireguard.xml:22:                android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_wireguard.xml:25:                android:fillColor="#FF231F20" />
app/src/main/res/drawable/ic_app_windscribe.xml:1:<vector xmlns:android="http://schemas.android.com/apk/res/android" android:width="108dp" android:height="108dp" android:viewportWidth="1024" android:viewportHeight="1024"><path android:fillColor="#020d1c" android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z"/><path android:fillColor="#FFFFFF" android:pathData="M724.09 376.15c-7.45-18-14.75-36.05-22.29-54C638.56 296 575.23 270.09 512 243.94c-63.23 26.15-126.56 52.06-189.79 78.21-7.54 17.95-14.84 36-22.29 54Q272 444.07 244 512q39.11 95 78.23 190 94.9 39 189.77 78.08 94.92-39 189.77-78.08 39.17-95 78.23-190-27.94-67.93-55.91-135.85zM512 742.84q-81.87-33.41-163.7-66.92-33.58-82-67-163.94 33.51-81.9 67-163.8 81.6-33.3 163.16-66.7a1.12 1.12 0 0 1 1.2 0q81.59 33.33 163.16 66.7 33.53 81.89 67 163.8-33.49 82-67 163.94C621.14 698.2 512 742.84 512 742.84m.12-186.31a.23.23 0 0 1-.22 0q-26.15 26.32-52.29 52.64c-10.3 10.17-25 14.61-39.25 14.82h-42.61V400.43h52.56v163.56q25.8-26 51.66-51.92 14.92-15 29.91-30l.1-.1.1.1q15 15 29.91 30Q567.88 538 593.67 564q.09-81.78 0-163.56h52.56V624h-42.61c-14.22-.21-28.95-4.65-39.25-14.82q-26.19-26.27-52.29-52.64m200.75-44.85.05.13h-3.38c-4 .09-8.06 0-12.09 0h-43.28V392.6h-63.69l-2.29 2.3L576 407.06c-5.79 5.79-11.78 11.77-17.66 17.68L556 427v76.95h-11c-9.21-9.28-18.42-18.51-27.35-27.47L512.1 471l-.12-.12-.1.1-5.54 5.56c-8.93 9-18.14 18.19-27.35 27.47H468V427l-2.29-2.3c-5.88-5.9-11.87-11.89-17.66-17.68l-12.15-12.15-2.29-2.3H369.9v119.22h-43.28c-4 0-8.07.09-12.09 0h-3.41l.05-.13h-.05q29.82-70.51 59.46-141.12Q440.76 340.92 511 311.4a1.84 1.84 0 0 1 2 0q70.21 29.6 140.46 59.16 29.72 70.57 59.46 141.12z"/></vector>
app/src/main/res/drawable/ic_app_warp.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FFF06D6C" />
app/src/main/res/drawable/ic_app_warp.xml:19:                android:fillColor="#FFF06D6C"
app/src/main/res/drawable/ic_app_warp.xml:21:                android:strokeColor="#FFF06D6C"
app/src/main/res/drawable/ic_app_warp.xml:26:                android:fillColor="#FF09A9D6"
app/src/main/res/drawable/ic_app_warp.xml:28:                android:strokeColor="#FF09A9D6"
app/src/main/res/drawable/ic_app_warp.xml:33:                android:fillColor="#FFF1A830"
app/src/main/res/drawable/ic_app_warp.xml:35:                android:strokeColor="#FFF1A830"
app/src/main/res/drawable/ic_app_warp.xml:40:                android:fillColor="#FF28429E"
app/src/main/res/drawable/ic_app_warp.xml:42:                android:strokeColor="#FF28429E"
app/src/main/res/drawable/ic_app_warp.xml:47:                android:fillColor="#FFFDFEFE"
app/src/main/res/drawable/ic_app_warp.xml:49:                android:strokeColor="#FFFDFEFE"
app/src/main/res/drawable/ic_app_warp.xml:54:                android:fillColor="#FF0984B8"
app/src/main/res/drawable/ic_app_warp.xml:56:                android:strokeColor="#FF0984B8"
app/src/main/res/drawable/ic_app_warp.xml:61:                android:fillColor="#FF1FB293"
app/src/main/res/drawable/ic_app_warp.xml:63:                android:strokeColor="#FF1FB293"
app/src/main/res/drawable/ic_app_warp.xml:68:                android:fillColor="#FFCE409F"
app/src/main/res/drawable/ic_app_warp.xml:70:                android:strokeColor="#FFCE409F"
app/src/main/res/drawable/ic_app_warp.xml:75:                android:fillColor="#FF47B8CE"
app/src/main/res/drawable/ic_app_warp.xml:77:                android:strokeColor="#FF47B8CE"
app/src/main/res/drawable/ic_app_urnetwork.xml:16:                android:fillColor="#101010"
app/src/main/res/drawable/ic_app_urnetwork.xml:19:                android:strokeColor="#101010"
app/src/main/res/drawable/ic_app_urnetwork.xml:23:                android:fillColor="#d6e5f3"
app/src/main/res/drawable/ic_app_urnetwork.xml:26:                android:strokeColor="#d6e5f3"
app/src/main/res/drawable/ic_app_urnetwork.xml:30:                android:fillColor="#ed91fe"
app/src/main/res/drawable/ic_app_urnetwork.xml:33:                android:strokeColor="#ed91fe"
app/src/main/res/drawable/ic_app_urnetwork.xml:37:                android:fillColor="#88f968"
app/src/main/res/drawable/ic_app_urnetwork.xml:40:                android:strokeColor="#88f968"
app/src/main/res/drawable/ic_app_urnetwork.xml:44:                android:fillColor="#eef6bb"
app/src/main/res/drawable/ic_app_urnetwork.xml:47:                android:strokeColor="#eef6bb"
app/src/main/res/drawable/ic_app_urnetwork.xml:51:                android:fillColor="#fd6d59"
app/src/main/res/drawable/ic_app_urnetwork.xml:54:                android:strokeColor="#fd6d59"
app/src/main/res/drawable/ic_app_tor.xml:17:                <item android:offset="0" android:color="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_tor.xml:18:                <item android:offset="1" android:color="#FFECECF0" />
app/src/main/res/drawable/ic_app_tor.xml:27:                android:fillColor="#FF68B044" />
app/src/main/res/drawable/ic_app_tor.xml:30:                android:fillColor="#FFF5F8DE" />
app/src/main/res/drawable/ic_app_tor.xml:33:                android:fillColor="#FF7E4798" />
app/src/main/res/drawable/ic_app_tor.xml:36:                android:fillColor="#FF010101" />
app/src/main/res/drawable/ic_app_telegram.xml:4:    <path android:fillColor="#61A8DE" android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z"/>
app/src/main/res/drawable/ic_app_telegram.xml:6:        <path android:fillColor="#FFFFFF" android:pathData="M358.844,100.6L54.091,219.359c-9.871,3.847-9.273,18.012,0.888,21.012l77.441,22.868l28.901,91.706 c3.019,9.579,15.158,12.483,22.185,5.308l40.039-40.882l78.56,57.665c9.614,7.057,23.306,1.814,25.747-9.859l52.031-248.76 C382.431,106.232,370.443,96.08,358.844,100.6z M320.636,155.806L179.08,280.984c-1.411,1.248-2.309,2.975-2.519,4.847 l-5.45,48.448c-0.178,1.58-2.389,1.789-2.861,0.271l-22.423-72.253c-1.027-3.308,0.312-6.892,3.255-8.717l167.163-103.676 C320.089,147.518,324.025,152.81,320.636,155.806z"/>
app/src/main/res/drawable/ic_app_store.xml:26:                    <item android:offset="0" android:color="#18BFFB" />
app/src/main/res/drawable/ic_app_store.xml:27:                    <item android:offset="1" android:color="#2072F3" />
app/src/main/res/drawable/ic_app_store.xml:32:            android:fillColor="#FFFFFF"
app/src/main/res/drawable/ic_app_settings.xml:24:                <item android:offset="0" android:color="#D3D7DD" />
app/src/main/res/drawable/ic_app_settings.xml:25:                <item android:offset="1" android:color="#888C90" />
app/src/main/res/drawable/ic_app_settings.xml:32:        android:fillColor="#2E2E30" />
app/src/main/res/drawable/ic_app_settings.xml:36:        android:fillColor="#000000" />
app/src/main/res/drawable/ic_app_settings.xml:40:        android:fillColor="#7F7F7F" />
app/src/main/res/drawable/ic_app_settings.xml:44:        android:fillColor="#777777" />
app/src/main/res/drawable/ic_app_settings.xml:54:                <item android:offset="0" android:color="#D9DADB" />
app/src/main/res/drawable/ic_app_settings.xml:55:                <item android:offset="1" android:color="#7D8082" />
app/src/main/res/drawable/ic_app_proton.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FF1C1B33" />
app/src/main/res/drawable/ic_app_proton.xml:22:                    <item android:offset="0" android:color="#FF8EFFEE" />
app/src/main/res/drawable/ic_app_proton.xml:23:                    <item android:offset="0.07" android:color="#FF8EFFEE" />
app/src/main/res/drawable/ic_app_proton.xml:24:                    <item android:offset="0.45" android:color="#FFC9C7FF" />
app/src/main/res/drawable/ic_app_proton.xml:25:                    <item android:offset="1" android:color="#FF7341FF" />
app/src/main/res/drawable/ic_app_proton.xml:36:                    <item android:offset="0" android:color="#FF6D4AFF" />
app/src/main/res/drawable/ic_app_proton.xml:37:                    <item android:offset="0.4" android:color="#FF6D4AFF" />
app/src/main/res/drawable/ic_app_proton.xml:38:                    <item android:offset="1" android:color="#FF00F0C3" />
app/src/main/res/drawable/ic_app_outline.xml:4:    <path android:fillColor="#093828" android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z"/>
app/src/main/res/drawable/ic_app_outline.xml:6:        <path android:fillColor="#2a9d7d" android:pathData="M232.37 105.91C233.8 109.98 232.68 121.57 232.68 126.48C232.68 142.07 232.68 157.65 232.68 173.24C232.68 225.77 232.65 278.29 232.62 330.82C232.61 348.59 232.64 366.35 232.54 384.12C232.51 388.55 233.85 401.41 231.92 404.69C216.4 404.91 189.79 393.78 176.16 386.09C101.76 344.14 77.55 245.53 123.78 173.59C142.01 145.22 169.43 123.68 201.26 112.63C208.49 110.12 215.92 108.13 223.47 106.83C226.25 106.35 229.63 105.24 232.37 105.91Z"/>
app/src/main/res/drawable/ic_app_outline.xml:7:        <path android:fillColor="#ffffff" android:pathData="M279.66 404.69C279.66 384.43 279.66 364.17 279.66 343.91C285.92 341.56 292.55 340.53 298.72 337.77C315.7 330.17 330.02 317.52 339.74 301.65C344.64 293.65 348.11 284.76 350.21 275.64C361.93 224.84 329.83 178.21 280.26 166.69C278.52 163.35 279.65 153.96 279.65 149.86C279.65 140.04 279.65 130.22 279.65 120.4C279.65 115.8 279.05 110.69 279.85 106.17C282.23 105.23 285.43 106.48 287.93 106.86C295.67 108.05 303.32 110.16 310.69 112.76C342.68 124.01 369.54 145.24 388.25 173.57C436.61 246.8 409.01 347.52 332.37 388.27C319.52 395.1 294.08 405.06 279.66 404.69Z"/>
app/src/main/res/drawable/ic_app_v2ray.xml:20:        android:fillColor="#409ED7" />
app/src/main/res/drawable/ic_app_v2ray.xml:34:            android:fillColor="#FFFFFF" />
app/src/main/res/drawable/ic_app_openvpn.xml:17:                <item android:offset="0" android:color="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_openvpn.xml:18:                <item android:offset="1" android:color="#FFECECF0" />
app/src/main/res/drawable/ic_app_openvpn.xml:27:                android:fillColor="#FF1A3967"
app/src/main/res/drawable/ic_app_openvpn.xml:31:                android:fillColor="#FFED7F22"
app/src/main/res/drawable/ic_app_npv.xml:16:                android:fillColor="#0a100e"
app/src/main/res/drawable/ic_app_npv.xml:19:                android:strokeColor="#0a100e"
app/src/main/res/drawable/ic_app_npv.xml:23:                android:fillColor="#54e08c"
app/src/main/res/drawable/ic_app_npv.xml:26:                android:strokeColor="#54e08c"
app/src/main/res/drawable/ic_app_masque.xml:3:    Home tile «masque» — desktop artwork g-masque-art, cover tile on #FFFFFF.
app/src/main/res/drawable/ic_app_masque.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_masque.xml:19:                android:fillColor="#FFFFFFFF"
app/src/main/res/drawable/ic_app_masque.xml:21:                android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_app_masque.xml:26:                android:fillColor="#FF3E91FF"
app/src/main/res/drawable/ic_app_masque.xml:28:                android:strokeColor="#FF020202"
app/src/main/res/drawable/ic_app_iran.xml:31:        android:fillColor="#FFFFFF" />
app/src/main/res/drawable/ic_app_iran.xml:41:            android:fillColor="#121111" />
app/src/main/res/drawable/ic_app_gscript.xml:19:        android:fillColor="#FFFFFF" />
app/src/main/res/drawable/ic_app_gscript.xml:30:            android:fillColor="#4285F4" />
app/src/main/res/drawable/ic_app_gscript.xml:33:            android:fillColor="#34A853" />
app/src/main/res/drawable/ic_app_gscript.xml:36:            android:fillColor="#FBBC05" />
app/src/main/res/drawable/ic_app_gscript.xml:39:            android:fillColor="#EA4335" />
app/src/main/res/drawable/ic_app_lantern.xml:17:                android:fillColor="#FF00BCD4" />
app/src/main/res/drawable/ic_app_lantern.xml:23:                            android:fillColor="#FFFEE600" />
app/src/main/res/drawable/ic_app_lantern.xml:26:                            android:fillColor="#FF111111" />
app/src/main/res/drawable/ic_app_github.xml:15:                <item android:offset="0" android:color="#FF4B4D55" />
app/src/main/res/drawable/ic_app_github.xml:16:                <item android:offset="1" android:color="#FF25262B" />
app/src/main/res/drawable/ic_app_github.xml:21:        <path android:pathData="M12 .5C5.65.5.5 5.65.5 12c0 5.08 3.29 9.39 7.86 10.91.58.1.79-.25.79-.56 0-.27-.01-1.17-.02-2.12-3.2.7-3.88-1.36-3.88-1.36-.52-1.34-1.28-1.7-1.28-1.7-1.05-.72.08-.7.08-.7 1.16.08 1.77 1.19 1.77 1.19 1.03 1.76 2.7 1.26 3.36.96.1-.75.4-1.26.73-1.55-2.55-.29-5.24-1.28-5.24-5.69 0-1.26.45-2.29 1.19-3.09-.12-.29-.52-1.47.11-3.06 0 0 .97-.31 3.18 1.18a11 11 0 0 1 5.79 0c2.2-1.49 3.18-1.18 3.18-1.18.63 1.59.23 2.77.12 3.06.74.8 1.18 1.83 1.18 3.09 0 4.42-2.69 5.39-5.25 5.68.41.36.78 1.06.78 2.13 0 1.54-.01 2.79-.01 3.17 0 .31.21.67.8.56A10.52 10.52 0 0 0 23.5 12C23.5 5.65 18.35.5 12 .5z" android:fillColor="#FFFFFFFF" android:fillType="evenOdd" />
app/src/main/res/drawable/ic_app_gateway.xml:3:    Home tile «gateway» — desktop artwork g-gateway-art, cover tile on #FFFFFF.
app/src/main/res/drawable/ic_app_gateway.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_gateway.xml:19:                android:fillColor="#FFFFFFFF"
app/src/main/res/drawable/ic_app_gateway.xml:21:                android:strokeColor="#FFFFFFFF"
app/src/main/res/drawable/ic_app_gateway.xml:26:                android:fillColor="#FF673BB5"
app/src/main/res/drawable/ic_app_gateway.xml:28:                android:strokeColor="#FF673BB5"
app/src/main/res/drawable/ic_app_gateway.xml:33:                android:fillColor="#FFCCCCCC"
app/src/main/res/drawable/ic_app_gateway.xml:35:                android:strokeColor="#FFCCCCCC"
app/src/main/res/drawable/ic_app_gateway.xml:40:                android:fillColor="#FF3F51B4"
app/src/main/res/drawable/ic_app_gateway.xml:42:                android:strokeColor="#FF3F51B4"
app/src/main/res/drawable/ic_app_gateway.xml:47:                android:fillColor="#FF4CB050"
app/src/main/res/drawable/ic_app_gateway.xml:49:                android:strokeColor="#FF4CB050"
app/src/main/res/drawable/ic_app_gateway.xml:54:                android:fillColor="#FFFE9701"
app/src/main/res/drawable/ic_app_gateway.xml:56:                android:strokeColor="#FFFE9701"
app/src/main/res/drawable/ic_app_gateway.xml:61:                android:fillColor="#FFF34236"
app/src/main/res/drawable/ic_app_gateway.xml:63:                android:strokeColor="#FFF34236"
app/src/main/res/drawable/ic_app_dns.xml:19:        android:fillColor="#34C759" />
app/src/main/res/drawable/ic_app_dns.xml:29:            android:strokeColor="#FFFFFF"
app/src/main/res/drawable/ic_app_dns.xml:32:            android:fillColor="#00000000" />
app/src/main/res/drawable/ic_app_dns.xml:35:            android:strokeColor="#FFFFFF"
app/src/main/res/drawable/ic_app_dns.xml:38:            android:fillColor="#00000000" />
app/src/main/res/drawable/ic_app_cloudflare.xml:3:    Home tile «cloud» — desktop artwork g-cloudflare-art, cover tile on #FFFFFF.
app/src/main/res/drawable/ic_app_cloudflare.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_cloudflare.xml:19:                android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_cloudflare.xml:22:                android:fillColor="#FFF38020" />
app/src/main/res/drawable/ic_app_cloudflare.xml:25:                android:fillColor="#FFFAAE40" />
app/src/main/res/drawable/ic_app_geph.xml:3:    Home tile «geph» — desktop artwork g-geph-art, cover tile on #FFFFFF.
app/src/main/res/drawable/ic_app_geph.xml:13:    <path android:pathData="M651.264 51.2c92.16 0 138.24 0 188.416 15.36 54.272 19.456 97.28 62.464 116.736 116.736C972.8 233.472 972.8 280.576 972.8 372.736v278.528c0 92.16 0 138.24-15.36 188.416-19.456 54.272-62.464 97.28-116.736 116.736-51.2 16.384-97.28 16.384-189.44 16.384H372.736c-92.16 0-138.24 0-188.416-15.36-55.296-20.48-97.28-62.464-117.76-117.76C51.2 790.528 51.2 744.448 51.2 651.264V372.736c0-92.16 0-138.24 15.36-188.416 20.48-54.272 62.464-97.28 117.76-116.736C233.472 51.2 279.552 51.2 372.736 51.2h278.528z" android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_geph.xml:19:                android:fillColor="#FFFFFFFF" />
app/src/main/res/drawable/ic_app_geph.xml:22:                android:fillColor="#FF384445" />
app/src/main/res/drawable/ic_app_geph.xml:25:                android:fillColor="#FF6BC8C7" />
app/src/main/res/drawable/ic_app_geph.xml:28:                android:fillColor="#FFBEEBEB" />
app/src/main/java/com/mlmvpn/scanner/engines/tgproxy/TgProbe.kt:157:         * An unencrypted `req_pq_multi#be7e8ef1 nonce:int128` in the intermediate transport:
app/src/main/java/com/mlmvpn/scanner/engines/tgproxy/TgProbe.kt:173:        /** True when [plain] is a `res_pq#05162463` that repeats [nonce]. */
```

### Color(0x...) — 114 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:45:    val blush = Color(0xFFF6DCE4)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:46:    val rose = Color(0xFFE9BACB)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:47:    val coral = Color(0xFFE98583)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:48:    val navy = Color(0xFF293451)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:49:    val blueGray = Color(0xFF58657A)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:50:    val cream = Color(0xFFFFF8F1)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:51:    val white = Color(0xFFFFFFFF)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:58:    val cloudflareOrange = Color(0xFFF6821F)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:59:    val cloudflareOrangeLight = Color(0xFFFFA94D)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:60:    val telegramBlue = Color(0xFF61A8DE)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:61:    val brandCyan = Color(0xFF18BFFB)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:62:    val brandBlue = Color(0xFF2072F3)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:64:        Color(0xFF0A84FF), Color(0xFF30D158), Color(0xFFFF9F0A), Color(0xFFFF375F),
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:65:        Color(0xFFBF5AF2), Color(0xFF64D2FF), Color(0xFFFF6482), Color(0xFF5E5CE6),
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:67:    val lavenderNight = Color(0xFF27263E)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:68:    val navyNight = Color(0xFF191D32)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:69:    val nightCard = Color(0xFF34334D)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:70:    val nightCoral = Color(0xFFFFA39B)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:71:    val nightBlue = Color(0xFF9BB6E3)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:72:    val lightText = Color(0xFF293451)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:73:    val nightText = Color(0xFFF8F2FA)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:74:    val nightMuted = Color(0xFFC1BED0)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:75:    val lightMuted = Color(0xFF595F72)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:76:    val nightVariantText = Color(0xFFD0CDE0)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:77:    val lightVariant = Color(0xFFF0E5E8)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:78:    val lightVariantText = Color(0xFF515B70)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:79:    val nightOutline = Color(0xFF77758E)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:80:    val lightOutline = Color(0xFF9BA5B6)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:81:    val nightError = Color(0xFFFFB4AB)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:82:    val lightError = Color(0xFFB3261E)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:83:    val nightErrorText = Color(0xFF690005)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:84:    val nightTertiary = Color(0xFFD8B5E2)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:85:    val nightOnPrimaryContainer = Color(0xFFFFE0DB)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:92:    val secondary: Color @Composable get() = if (Appearance.isDark()) Color(0xFFA9B1CE) else blueGray
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:99:    val success: Color @Composable get() = if (Appearance.isDark()) Color(0xFF9BCFB4) else Color(0xFF43765A)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:100:    val disabled: Color @Composable get() = if (Appearance.isDark()) Color(0xFF9B98AD) else Color(0xFF8E93A0)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:103:    val indigo: Color @Composable get() = if (Appearance.isDark()) Color(0xFFB3A9E5) else Color(0xFF6E69A8)
app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt:104:    val purple: Color @Composable get() = if (Appearance.isDark()) Color(0xFFD4B6E6) else Color(0xFF8F659F)
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:83:            base = Color(0xFF241A16),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:85:                Blob(0.20f, 0.16f, 0.85f, Color(0xFF5C4033)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:86:                Blob(0.86f, 0.34f, 0.62f, Color(0xFF4A2F26)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:87:                Blob(0.50f, 0.94f, 0.75f, Color(0xFF1A1210)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:92:            base = Color(0xFF2A1E12),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:94:                Blob(0.78f, 0.14f, 0.80f, Color(0xFF7A5320)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:95:                Blob(0.14f, 0.52f, 0.66f, Color(0xFF4B331A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:96:                Blob(0.55f, 0.98f, 0.70f, Color(0xFF1C1409)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:101:            base = Color(0xFF161A2E),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:103:                Blob(0.24f, 0.20f, 0.82f, Color(0xFF33407A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:104:                Blob(0.84f, 0.60f, 0.68f, Color(0xFF283155)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:105:                Blob(0.50f, 0.96f, 0.72f, Color(0xFF0E1120)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:110:            base = Color(0xFF1A1A1C),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:112:                Blob(0.30f, 0.18f, 0.86f, Color(0xFF3A3A3F)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:113:                Blob(0.82f, 0.70f, 0.60f, Color(0xFF2A2A2E)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:114:                Blob(0.50f, 1.00f, 0.70f, Color(0xFF101012)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:119:            base = Color(0xFF1E2626),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:121:                Blob(0.22f, 0.24f, 0.84f, Color(0xFF44585A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:122:                Blob(0.80f, 0.22f, 0.58f, Color(0xFF35494B)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:123:                Blob(0.55f, 0.95f, 0.72f, Color(0xFF141A1A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:128:            base = Color(0xFF221831),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:130:                Blob(0.76f, 0.18f, 0.80f, Color(0xFF56317A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:131:                Blob(0.18f, 0.58f, 0.66f, Color(0xFF3A2352)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:132:                Blob(0.50f, 0.98f, 0.70f, Color(0xFF150F1F)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:137:            base = Color(0xFF10222B),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:139:                Blob(0.26f, 0.22f, 0.84f, Color(0xFF1F4E63)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:140:                Blob(0.84f, 0.56f, 0.64f, Color(0xFF17394A)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:141:                Blob(0.50f, 0.97f, 0.72f, Color(0xFF0A161C)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:146:            base = Color(0xFF1C2024),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:148:                Blob(0.20f, 0.28f, 0.82f, Color(0xFF3B444E)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:149:                Blob(0.86f, 0.24f, 0.58f, Color(0xFF2C333B)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:150:                Blob(0.50f, 0.98f, 0.70f, Color(0xFF111417)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:155:            base = Color(0xFF2B1720),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:157:                Blob(0.72f, 0.20f, 0.82f, Color(0xFF7B3446)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:158:                Blob(0.16f, 0.44f, 0.64f, Color(0xFF4C2130)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:159:                Blob(0.50f, 0.98f, 0.72f, Color(0xFF1A0E14)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:164:            base = Color(0xFF15231A),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:166:                Blob(0.24f, 0.20f, 0.84f, Color(0xFF2C4E36)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:167:                Blob(0.82f, 0.62f, 0.62f, Color(0xFF203A28)),
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:168:                Blob(0.50f, 0.98f, 0.70f, Color(0xFF0C150F)),
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:81:    val Blue   = Color(0xFF007AFF)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:82:    val Green  = Color(0xFF34C759)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:83:    val Indigo = Color(0xFF5856D6)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:84:    val Orange = Color(0xFFFF9500)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:85:    val Pink   = Color(0xFFFF2D55)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:86:    val Purple = Color(0xFFAF52DE)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:87:    val Red    = Color(0xFFFF3B30)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:88:    val Teal   = Color(0xFF5AC8FA)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:89:    val Yellow = Color(0xFFFFCC00)
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt:90:    val Gray   = Color(0xFF8E8E93)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:92:    val Top = Color(0xFF1C1C1E)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:93:    val Bottom = Color(0xFF000000)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:94:    val Lane = Color(0xFF2C2C2E)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:95:    val LaneLine = Color(0x26FFFFFF)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:97:    val Secondary = Color(0x99EBEBF5)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:98:    val LightHousing = Color(0xFF0A0A0A)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:99:    val LightOff = Color(0xFF3A1414)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:100:    val LightOn = Color(0xFFFF3B30)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:101:    val Go = Color(0xFF30D158)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:102:    val Out = Color(0xFFFF453A)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:103:    val Progress = Color(0xFF0A84FF)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:104:    val Gold = Color(0xFFFFD60A)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:105:    val Silver = Color(0xFFC7C7CC)
app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt:106:    val Bronze = Color(0xFFC08B5C)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:37:    private val Blue = Color(0xFF0A84FF)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:38:    private val Indigo = Color(0xFF5E5CE6)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:39:    private val Green = Color(0xFF32D74B)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:40:    private val Orange = Color(0xFFFF9F0A)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:41:    private val Purple = Color(0xFFBF5AF2)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:42:    private val Pink = Color(0xFFFF375F)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:43:    private val Teal = Color(0xFF40C8E0)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:44:    private val Gray = Color(0xFF8E8E93)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:45:    private val Red = Color(0xFFFF453A)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:46:    private val CfOrange = Color(0xFFF6821F)
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:362:            icon = StoreIcon(Icons.Default.AutoAwesome, Color(0xFF5E5CE6), tint2 = CfOrange),
app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt:574:            icon = StoreIcon(Icons.Default.SwapHoriz, Orange, tint2 = Color(0xFFFFD60A)),
```

### gs_* — 0 hit(s)

No matches.

### Ios.* — 0 hit(s)

No matches.

### Physical-side Kotlin APIs — 2 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/ui/mae/MaeScreen.kt:427:                modifier = Modifier.matchParentSize().wrapContentWidth(AbsoluteAlignment.Right),
app/src/main/java/com/mlmvpn/scanner/ui/mae/MaeScreen.kt:437:                modifier = Modifier.matchParentSize().wrapContentWidth(AbsoluteAlignment.Left),
```

### Physical-side XML APIs — 0 hit(s)

No matches.

### Explicit LTR layout direction — 2 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:428:        layoutDirection = LayoutDirection.Ltr,
app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt:675:        layoutDirection = LayoutDirection.Ltr,
```

### Absolute offsets/padding — 12 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/ui/settings/IosSettings.kt:623: * rather than `absoluteOffset` on purpose -- in Persian the stack should build from the right,
app/src/main/java/com/mlmvpn/scanner/ui/mae/MaeScreen.kt:14:import androidx.compose.foundation.layout.absoluteOffset
app/src/main/java/com/mlmvpn/scanner/ui/mae/MaeScreen.kt:441:        Box(Modifier.fillMaxWidth().absoluteOffset { IntOffset(offset.value.roundToInt(), 0) }) {
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeScreen.kt:26:import androidx.compose.foundation.layout.absoluteOffset
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeScreen.kt:808:                                    .absoluteOffset { position.value.toIntOffset() }
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeScreen.kt:976:                .absoluteOffset(x = -(d * 0.32f), y = -(d * 0.32f))
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeFolders.kt:19:import androidx.compose.foundation.layout.absoluteOffset
app/src/main/java/com/mlmvpn/scanner/ui/home/HomeFolders.kt:409:                                            .absoluteOffset { position.value.toIntOffset() }
app/src/main/java/com/mlmvpn/scanner/ui/home/AppIcon.kt:13:import androidx.compose.foundation.layout.absoluteOffset
app/src/main/java/com/mlmvpn/scanner/ui/home/AppIcon.kt:124:            .absoluteOffset(y = -(h * 0.45f))
app/src/main/java/com/mlmvpn/scanner/ui/home/AppIcon.kt:151:            .absoluteOffset(x = d * 0.34f, y = -(d * 0.34f))
app/src/main/java/com/mlmvpn/scanner/ui/home/AppIcon.kt:189:            .absoluteOffset(x = -(dot * 0.22f), y = -(dot * 0.22f))
```

### MaterialTheme.colorScheme — 0 hit(s)

No matches.

### Material component color defaults requiring review — 18 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/ui/theme/IosButtons.kt:20:fun iosButtonColors(accent: Color = DuskTheme.primary): ButtonColors = ButtonDefaults.buttonColors(
app/src/main/java/com/mlmvpn/scanner/ui/theme/IosButtons.kt:34:fun iosNeutralButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
app/src/main/java/com/mlmvpn/scanner/ui/theme/IosButtons.kt:48:fun iosFieldColors(): TextFieldColors = TextFieldDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/ui/settings/SmartIranPage.kt:210:            colors = SwitchDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/ui/settings/IosSettings.kt:399:                colors = SwitchDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt:751:            colors = androidx.compose.material3.SwitchDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:780:        colors = CardDefaults.cardColors(containerColor = DuskTheme.surface)
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:842:                colors = SwitchDefaults.colors(checkedThumbColor = DuskTheme.primary))
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:929:                colors = SwitchDefaults.colors(checkedThumbColor = DuskTheme.primary))
app/src/main/java/com/mlmvpn/scanner/ui/aether/AetherScreen.kt:1276:        colors = CardDefaults.cardColors(containerColor = DuskTheme.surface)
app/src/main/java/com/mlmvpn/scanner/ui/GameTab.kt:606:                    colors = CardDefaults.cardColors(containerColor = GameColors.SurfaceDark),
app/src/main/java/com/mlmvpn/scanner/ui/CloudTab.kt:2476:            colors = androidx.compose.material3.ButtonDefaults.textButtonColors(contentColor = DuskTheme.primary),
app/src/main/java/com/mlmvpn/scanner/ui/BpbSettingsModal.kt:62:    val switchColors = SwitchDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:195:                                colors = ButtonDefaults.buttonColors(
app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:396:                                colors = ButtonDefaults.buttonColors(
app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:557:                            colors = ButtonDefaults.buttonColors(
app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:850:                        colors = androidx.compose.material3.SwitchDefaults.colors(
app/src/main/java/com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt:878:                    colors = ButtonDefaults.buttonColors(
```

### Remaining Toast calls — 8 hit(s)

```text
app/src/main/java/com/mlmvpn/scanner/widget/WidgetActionActivity.kt:152:        runCatching { Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show() }
app/src/main/java/com/mlmvpn/scanner/ui/npv/NpvImportActivity.kt:33:        Toast.makeText(this, when {
app/src/main/java/com/mlmvpn/scanner/backup/BackupStage.kt:51:                runCatching { Toast.makeText(app, resultMessage(done), Toast.LENGTH_LONG).show() }
app/src/main/java/com/mlmvpn/scanner/engines/github/GtEngine.kt:1040:            try { android.widget.Toast.makeText(app, msg, android.widget.Toast.LENGTH_LONG).show() } catch (_: Exception) {}
app/src/main/java/com/mlmvpn/scanner/lan/LanNotification.kt:162:                    Toast.makeText(context, S(R.string.lanweb_copied), Toast.LENGTH_SHORT).show()
app/src/main/java/com/mlmvpn/scanner/VpnTileService.kt:175:            Toast.makeText(this@VpnTileService, msg, Toast.LENGTH_SHORT).show()
app/src/main/java/com/mlmvpn/scanner/MainActivity.kt:53:            android.widget.Toast.makeText(this, getString(R.string.screen_missing_on_device), android.widget.Toast.LENGTH_LONG).show()
app/src/main/java/com/mlmvpn/core/aether/AetherScanService.kt:246:                android.widget.Toast.makeText(context,
```

### Disposition of remaining hits

- 176 strict `#hex` matches: 47 are named `values/colors.xml` palette/asset resource definitions; 127 are fixed service/app icon vector artwork; 2 are Telegram transport protocol constructor IDs, not colors. The widget preview, glyph/notification icons, node background and LAN web page no longer contain ad-hoc CSS/XML colors.
- 114 `Color(0x...)` matches are limited to five named palette/data scopes: `SoftDusk.kt` semantic token definitions, `Wallpapers.kt` user-selectable background preset art, `HomeDestinations.kt` fixed iOS-like app identity palette, `ArenaRace.kt` race illustration palette and `StoreCatalog.kt` product icon palette. No component body uses inline `Color(0x...)`.
- The 2 left/right Compose matches are MAE swipe actions deliberately positioned on the physical side revealed by a left/right swipe. `TextAlign.Left/Right` was replaced by `TextAlign.Start`; XML left/right APIs are absent. The two explicit LTR layouts are wallpaper drawing coordinates. Twelve `absoluteOffset` matches are drag/motion/overlay geometry; the home edit badge was changed to logical `offset` for RTL.
- All 18 Material component color-default calls were inspected: shared Dusk helper wrappers or explicit Dusk/theme color arguments. There are zero `MaterialTheme.colorScheme` references after migration.
- All 8 remaining Toast calls are the already-documented non-Compose/platform callback deferrals in `SCREENS_CHECKLIST.md` (two import/widget trampolines, plus background engine/restore/notification/tile/platform error callbacks); none is an unchecked ordinary Compose-screen Toast.


## Post-push CI validation

- Initial audit-fix commit `1f2da999094da1ec632a3c50104f1915eabc2249`: debug run [46105391645](https://github.com/Mamaliayda/eternal_vpn/actions/runs/46105391645) and APK run [46105391694](https://github.com/Mamaliayda/eternal_vpn/actions/runs/46105391694) both failed compiling `SafeWebView.kt`: its non-composable AndroidView factory helper attempted to read `DuskTheme`'s composable color token. The APK workflow exposed compiler diagnostics `SafeWebView.kt:21:14` and `:29:65`.
- Corrected by resolving `DuskTheme.muted` at the composable call sites and passing the plain Compose `Color` to `webViewOrNotice`; helper no longer reads theme state outside composition. This fix is commit `cc5d35a`.
- New CI for `cc5d35a` is pending at the time this note was written; do not treat the release build or artifacts as verified until both workflows pass.

### Additional CI iterations for AndroidView theme capture

- On `986d8e2`, Android release CI run [46107630913](https://github.com/Mamaliayda/eternal_vpn/actions/runs/46107630913) (and matching debug run [46107630916](https://github.com/Mamaliayda/eternal_vpn/actions/runs/46107630916)) reported three factory-lambda composable access errors at ProtonScreen.kt:714 and FenixScreen.kt:375/459. The Compose `DuskTheme.muted` reads were moved into the enclosing composable scopes and passed as captured plain color values.
- On `b74695d`, release CI run [46109737181](https://github.com/Mamaliayda/eternal_vpn/actions/runs/46109737181) reported `FenixScreen.kt:460:79 Unresolved reference 'webViewNoticeColor'`: the second WebView factory belongs to the separate `MozillaCheckPage` composable, not `MozillaSignInPage`. Added a separately scoped theme color in `MozillaCheckPage` (`3c02631`).
- Validation for `3c02631` is running: debug [46111603138](https://github.com/Mamaliayda/eternal_vpn/actions/runs/46111603138); APK/release [46111603135](https://github.com/Mamaliayda/eternal_vpn/actions/runs/46111603135). Build/release status and artifact names are not yet verified.
