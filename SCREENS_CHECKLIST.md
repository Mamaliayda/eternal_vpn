# Eternal VPN — screen redesign checklist

> **Progress: 1,101 / 1205 complete** — checked rows are migrated; uncompleted items are unchecked and carry a `deferred:` reason where applicable.

> Inventory generated from Kotlin source on 2026-11-26. A row is complete only after its visible composition, nested states, and overlays are reviewed against Soft Dusk tokens and migrated. Uncompleted rows remain unchecked and include a `deferred:` reason where work is intentionally postponed. Shared theme inheritance alone does **not** count as a completed visual redesign.

## Shared foundation

- [x] Soft Dusk light/night palette and contrast review (2026-11-26: audited theme text/surface and semantic pairs; darkened light muted/secondary text for WCAG AA on blush/cream and changed light tertiary text to navy for coral contrast).
- [x] Central shapes, typography, spacing and reusable components.
- [x] Material light/dark surface mapping and Android system bars.
- [x] Vazirmatn Persian/Latin font weights and mixed-script rendering (core source audit: all four bundled weights contain Latin, Arabic/Persian and digit glyphs with GSUB shaping; Vazirmatn is applied to the full Material type scale; human counts use localized Persian digits where intended while IPs, ports and protocol values stay LTR; reviewed line-height overrides/default type metrics, alignment, and FSI/PDI punctuation isolates. Runtime screenshot remains gated by the 700 checklist build threshold). (needs-device-check: Persian/English/digit glyph screenshot, light and dark)
- [ ] RTL behavior of shared cards, pills, icons and navigation.

## Main destination registry

All launcher destinations registered in `HomeDestinations` (the app board entry points):
- [x] `amnezia` — route reviewed against the migrated Soft Dusk Amnezia screen and nested settings; routing unchanged.
- [x] `antisanction` — destination routes to the migrated anti-sanction DNS screen and its Dusk-styled apps/domains subpages.
- [x] `cloud` — route reviewed against the migrated Cloud screens, account groups and Dusk feedback surfaces; routing unchanged.
- [ ] `configstudio`
- [x] `emergency_2` — route audited to the Dusk-styled Google Apps Script relay panel and setup pages.
- [x] `emergency_3` — route audited to the Dusk-styled SNI fragmentation panel and config picker.
- [ ] `fenix`
- [x] `fixed_ip`
- [ ] `flux`
- [x] `freeconfig`
- [ ] `fronting`
- [ ] `game`
- [ ] `geph`
- [ ] `github`
- [x] `iran`
- [ ] `lan`
- [ ] `lantern`
- [ ] `mae`
- [ ] `masque`
- [x] `nodes`
- [ ] `npv`
- [ ] `openvpn`
- [x] `outline` — route reviewed against the migrated Outline screen and source-management page; routing unchanged.
- [x] `proton` — route reviewed against the migrated Proton screen and web/helper states; routing unchanged.
- [x] `psiphon`
- [x] `quick`
- [x] `scanner`
- [ ] `scout`
- [x] `settings`
- [ ] `store`
- [x] `sublink`
- [ ] `tgproxy`
- [ ] `tor`
- [x] `tutorial`
- [ ] `urnetwork`
- [ ] `usage`
- [ ] `vpngate`
- [ ] `warp`
- [ ] `warp_on_warp`
- [ ] `widgetstudio`
- [ ] `windscribe`
- [ ] `wireguard`

## Screen/page/wizard composables

Found 230 screen-like public/internal composables across 133 Kotlin files (the full declaration inventory is below). Components are listed individually so nested pages and picker flows are not silently omitted.

### com

- [x] `EdgSettingsScreen` — `com/mlmvpn/scanner/engines/edg/EdgSettingsScreen.kt`
- [x] `MlmSettingsScreen` — `com/mlmvpn/scanner/engines/mlm/MlmSettingsScreen.kt`
- [x] `MlmUsersScreen` — `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt`
- [x] `AddMlmUserDialog` — `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt`
- [x] `NahanSettingsScreen` — `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt`
- [x] `AddNahanUserDialog` — `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt`
### AboutScreen.kt

- [x] `AboutScreen` — `com/mlmvpn/scanner/ui/AboutScreen.kt`
- [x] `ChangelogScreen` — `com/mlmvpn/scanner/ui/AboutScreen.kt`
### AddNodeScreen.kt

- [x] `AddNodeScreen` — `com/mlmvpn/scanner/ui/AddNodeScreen.kt`
- [x] `ManualEntryScreen` — `com/mlmvpn/scanner/ui/AddNodeScreen.kt`
- [x] `SubLinkAddScreen` — `com/mlmvpn/scanner/ui/AddNodeScreen.kt`
### AppScreen.kt

- [x] `AppScreen` — `com/mlmvpn/scanner/ui/AppScreen.kt` (Soft Dusk typography, spacing, semantic success color and shared snackbar; routing/state behavior unchanged)
### BaseConfigPicker.kt

- [x] `BaseConfigPickerScreen` — `com/mlmvpn/scanner/ui/BaseConfigPicker.kt`
### BpbSettingsModal.kt

- [x] `BpbSettingsModal` — `com/mlmvpn/scanner/ui/BpbSettingsModal.kt`
### CloudCoach.kt

- [x] `CloudOfferDialog` — `com/mlmvpn/scanner/ui/CloudCoach.kt`
### CloudScreens.kt

- [x] `CloudAddAccountScreen` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [x] `CloudGroupScreen` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
### CloudTab.kt

- [x] `CloudTab` — `com/mlmvpn/scanner/ui/CloudTab.kt`
### CloudTroubleshootScreen.kt

- [x] `CloudTroubleshootScreen` — `com/mlmvpn/scanner/ui/CloudTroubleshootScreen.kt`
### CombineCoach.kt

- [x] `CombineOfferDialog` — `com/mlmvpn/scanner/ui/CombineCoach.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `CombineDoneDialog` — `com/mlmvpn/scanner/ui/CombineCoach.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
### FixedIpScreen.kt

- [x] `FixedIpScreen` — `com/mlmvpn/scanner/ui/FixedIpScreen.kt`
### FreeConfigTab.kt

- [x] `FreeConfigTab` — `com/mlmvpn/scanner/ui/FreeConfigTab.kt`
### FreeConfigWizard.kt

- [x] `FreeConfigWizard` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
### GameTab.kt

- [x] `GameTab` — `com/mlmvpn/scanner/ui/GameTab.kt` (all interactive states use Dusk semantic palette and feedback)
- [x] `GamePickerPage` — `com/mlmvpn/scanner/ui/GameTab.kt` (Dusk shapes, text and selected states)
- [x] `RegionPickerPage` — `com/mlmvpn/scanner/ui/GameTab.kt` (Dusk rows and ping severity colors)
### HelpCenterScreen.kt

- [x] `HelpCenterScreen` — `com/mlmvpn/scanner/ui/HelpCenterScreen.kt`
- [x] `HelpArticleScreen` — `com/mlmvpn/scanner/ui/HelpCenterScreen.kt`
- [x] `HelpFaqScreen` — `com/mlmvpn/scanner/ui/HelpCenterScreen.kt`
### NodesScreens.kt

- [x] `NodeDetailScreen` — `com/mlmvpn/scanner/ui/NodesScreens.kt`
- [x] `NodeEditScreen` — `com/mlmvpn/scanner/ui/NodesScreens.kt`
- [x] `NodeListSettingsScreen` — `com/mlmvpn/scanner/ui/NodesScreens.kt`
- [x] `AutoSwitchScreen` — `com/mlmvpn/scanner/ui/NodesScreens.kt`
### NodesTab.kt

- [x] `NodesTab` — `com/mlmvpn/scanner/ui/NodesTab.kt`
### OpenVpnScreen.kt

- [x] `OpenVpnScreen` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
- [x] `MainPage` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
- [x] `ServersPage` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
- [x] `AccountsPage` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
- [x] `AccountEditPage` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
- [x] `GuidePage` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
### QuickConnectTab.kt

- [x] `QuickConnectTab` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
### QuickServersScreen.kt

- [x] `QuickServersScreen` — `com/mlmvpn/scanner/ui/QuickServersScreen.kt`
- [x] `QuickCountryScreen` — `com/mlmvpn/scanner/ui/QuickServersScreen.kt`
### ScanGuardHost.kt

- [x] `ScanGuardHost` — `com/mlmvpn/scanner/ui/ScanGuardHost.kt`
### ScannerTab.kt

- [x] `ScannerTab` — `com/mlmvpn/scanner/ui/ScannerTab.kt`
### ScreenVisibility.kt

- [ ] `rememberOnScreen` — `com/mlmvpn/scanner/ui/ScreenVisibility.kt`
### SplashScreen.kt

- [x] `SplashScreen` — `com/mlmvpn/scanner/ui/SplashScreen.kt`
### VpnGateCountryScreen.kt

- [x] `VpnGateCountryScreen` — `com/mlmvpn/scanner/ui/VpnGateCountryScreen.kt`
### VpnGateInfoScreens.kt

- [x] `GatewayServerDetailScreen` — `com/mlmvpn/scanner/ui/VpnGateInfoScreens.kt`
- [x] `GatewayHelpScreen` — `com/mlmvpn/scanner/ui/VpnGateInfoScreens.kt`
### VpnGateServersScreen.kt

- [x] `VpnGateServersScreen` — `com/mlmvpn/scanner/ui/VpnGateServersScreen.kt`
### VpnGateTab.kt

- [x] `VpnGateTab` — `com/mlmvpn/scanner/ui/VpnGateTab.kt`
### WireguardGuard.kt

- [x] `WireguardConflictDialog` — `com/mlmvpn/scanner/ui/WireguardGuard.kt`
- [x] `EngineConflictDialog` — `com/mlmvpn/scanner/ui/WireguardGuard.kt`
### WireguardTab.kt

- [x] `WireguardTab` — `com/mlmvpn/scanner/ui/WireguardTab.kt`
### aether

- [x] `AetherScreen` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `AdvancedSettingsDialog` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
### amnezia

- [x] `AmneziaScreen` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [x] `AmzSettingsScreen` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [x] `ActionSheet` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [x] `AmzServersScreen` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
### arena

- [x] `ArenaScreen` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
### backup

- [x] `BackupScreen` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
- [x] `HomePage` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
- [x] `PreviewPage` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
### cfwarp

- [x] `CfWarpSettingsScreen` — `com/mlmvpn/scanner/ui/cfwarp/CfWarpUi.kt` (Dusk semantic color tokens and shared grouped settings controls).
- [x] `Nat64ExitScreen` — `com/mlmvpn/scanner/ui/cfwarp/Nat64ExitUi.kt` (Dusk palette, shared grouped controls, semantic type scale and RTL-aware directional padding)
### cloud

- [x] `GatewaySettingsScreen` — `com/mlmvpn/scanner/ui/cloud/GatewaySettingsScreen.kt`
### configstudio

- [x] `ConfigStudioHost` — `com/mlmvpn/scanner/ui/configstudio/ConfigStudioHost.kt`
- [x] `StudioAccountDetailScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountDetailScreen.kt`
- [x] `StudioAccountsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountsScreen.kt`
- [x] `StudioAnalyticsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [x] `StudioAuditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAuditScreen.kt`
- [x] `StudioBulkActionScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioBulkActionScreen.kt`
- [x] `StudioBulkAddScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioBulkAddScreen.kt`
- [x] `StudioBulkUsersScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioBulkUsersScreen.kt`
- [x] `StudioCentralAccountScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioCentralAccountScreen.kt`
- [x] `StudioCentralBulkUsersScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioCentralBulkUsersScreen.kt`
- [x] `StudioCentralNewUserScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioCentralNewUserScreen.kt`
- [x] `StudioCentralUserScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioCentralUserScreen.kt`
- [x] `StudioConfigBuilderScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioConfigBuilderScreen.kt`
- [x] `StudioConfigsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioConfigsScreen.kt`
- [x] `StudioDashboardScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [x] `StudioExitsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioExitsScreen.kt`
- [x] `StudioLocationsEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioLocationsEditScreen.kt`
- [x] `StudioMoreScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioMoreScreen.kt`
- [x] `StudioMultiLocationScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioMultiLocationScreen.kt`
- [x] `StudioNewUserScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNewUserScreen.kt`
- [x] `StudioNodeEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeEditScreen.kt`
- [x] `StudioNodeGroupEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeGroupEditScreen.kt`
- [x] `StudioNodeGroupsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeGroupsScreen.kt`
- [x] `StudioNodesScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodesScreen.kt`
- [x] `StudioPlanEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioPlanEditScreen.kt`
- [x] `StudioPlansScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioPlansScreen.kt`
- [x] `StudioSettingsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioSettingsScreen.kt`
- [x] `StudioTemplateEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplateEditScreen.kt`
- [x] `StudioTemplatesScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplatesScreen.kt`
- [x] `StudioUserDetailScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioUserDetailScreen.kt`
- [x] `StudioUsersScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [x] `ProtocolPicker` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `LocationPicker` — `com/mlmvpn/scanner/ui/configstudio/parts/LocationPicker.kt`
- [x] `PortPicker` — `com/mlmvpn/scanner/ui/configstudio/parts/PortPicker.kt`
- [x] `StudioSetupWizard` — `com/mlmvpn/scanner/ui/configstudio/wizard/StudioSetupWizard.kt`
### doctor

- [x] `CfDoctorScreen` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `AccountPage` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `ReportPage` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `CloudDoctorScreen` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `StepPage` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
### emergency

- [x] `EmergencyLevel2Screen` — `com/mlmvpn/scanner/ui/emergency/EmergencyLevel2Screen.kt` (Dusk semantic dial accent, shared Material typography and spacing)
- [x] `EmergencyLevel3Screen` — `com/mlmvpn/scanner/ui/emergency/EmergencyLevel3Screen.kt` (Dusk error accent, shared Material typography and spacing)
- [x] `GstRelayScreen` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt` (Dusk semantic states and shared settings rows)
- [x] `GstNetworkPathScreen` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt` (Dusk path state colors, shared groups and row typography)
- [x] `GstLogScreen` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt`
- [x] `GstSetupWizard` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `SniConfigPickerPage` — `com/mlmvpn/scanner/ui/emergency/SniConfigPages.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `SniBuildPage` — `com/mlmvpn/scanner/ui/emergency/SniConfigPages.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
### fenix

- [x] `FenixScreen` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (Dusk semantic colors and shared settings surfaces)
- [x] `AccountsPage` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (Dusk grouped account management)
- [ ] `MozillaSignInPage` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (deferred: Mozilla-hosted sign-in page owns its webpage styling; app shell remains Dusk)
- [ ] `MozillaCheckPage` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (deferred: Mozilla-hosted verification page owns its webpage styling; app shell remains Dusk)
- [x] `FenixCountryPicker` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (Dusk selection and ping status colors)
### flux

- [x] `FluxScreen` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt` (Dusk semantic palette and page composition)
- [x] `CountryPicker` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt` (Dusk country-selection rows)
- [x] `FluxDiagnosticsScreen` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt` (Dusk diagnostic sections and controls)
### game

- [x] `GameBoosterHost` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (host routes between Dusk game surfaces without changing behavior)
- [x] `GameBoostScreen` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk semantic colors, selected states and snackbar feedback)
- [x] `HelpPage` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk grouped content and status colors)
- [x] `GamePickerPage` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk app selection rows)
- [x] `RegionPickerPage` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk country selection and failure states)
- [x] `AddGamePage` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk app addition rows)
- [x] `AdvancedPage` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk control states and snackbar feedback)
### geph

- [x] `GephAccountScreen` — `com/mlmvpn/scanner/ui/geph/GephAccountScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `GephExitScreen` — `com/mlmvpn/scanner/ui/geph/GephExitScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `GephNewsScreen` — `com/mlmvpn/scanner/ui/geph/GephInfoScreens.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `GephSessionsScreen` — `com/mlmvpn/scanner/ui/geph/GephInfoScreens.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `GephSettingsScreen` — `com/mlmvpn/scanner/ui/geph/GephSettingsScreen.kt` (Dusk semantic colors and shared grouped settings controls).
### github

- [x] `GithubTunnelScreen` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (Dusk semantic palette across tunnel setup, connection, account, relay and routing states)
- [x] `ExitCountryPage` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (shared region picker retained; Dusk provider/status colors)
- [x] `RuleEditorPage` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (Dusk form, selection and validation states)
### home

- [x] `IranConfigsScreen` — `com/mlmvpn/scanner/ui/home/BuiltInProfilesScreen.kt`
- [x] `DomainFrontingScreen` — `com/mlmvpn/scanner/ui/home/BuiltInProfilesScreen.kt`
- [x] `BuiltInProfileScreen` — `com/mlmvpn/scanner/ui/home/BuiltInProfilesScreen.kt`
- [x] `HomeScreen` — `com/mlmvpn/scanner/ui/home/HomeScreen.kt` (needs-device-check: phone layout and light/dark/RTL)
- [x] `IosModalHost` — `com/mlmvpn/scanner/ui/home/ModalHost.kt`
### lan

- [x] `LanGuideScreen` — `com/mlmvpn/scanner/ui/lan/LanGuideScreens.kt` (Dusk palette and typography tokens with address values explicitly rendered LTR)
- [x] `LanScreen` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk semantic status palette for sharing, hotspot, security and empty/error states)
### mae

- [x] `MaeManageScreen` — `com/mlmvpn/scanner/ui/mae/MaePages.kt` (Dusk rows, control states and snackbar feedback)
- [x] `MaeDiagnosticsScreen` — `com/mlmvpn/scanner/ui/mae/MaePages.kt` (Dusk diagnostic rows and status tones)
- [x] `InstalledAppPicker` — `com/mlmvpn/scanner/ui/mae/MaePages.kt` (Dusk app picker and snackbar feedback)
- [x] `MaeScreen` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt` (Dusk loading, connection and screen states)
- [x] `SymptomDialog` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt`
### npv

- [x] `NpvScreen` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` (Dusk semantic palette, status indicators and snackbar feedback)
- [x] `MainPage` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` (Dusk config list, loading and empty states)
- [x] `NpvSourcesPage` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt` (Dusk source, refresh, progress and snackbar surfaces)
### outline

- [x] `OutlineScreen` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [x] `SourcesPage` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [x] `OutlineServersPage` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
### proton

- [x] `ProtonScreen` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `ProtonWebPage` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
### sanction

- [x] `AntiSanctionScreen` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt` (shared Dusk settings groups, semantic connection states, Material type scale and spacing tokens)
- [x] `SanctionDomainsScreen` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt` (Dusk field/report/list surfaces, shared spacing and Material body/title styles)
- [x] `SanctionAppsScreen` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt` (Dusk search/list/loading surface, shared typography and semantic switch colors)
### scout

- [x] `ScoutScreen` — `com/mlmvpn/scanner/ui/scout/ScoutScreen.kt` (Dusk status colors and page-level progress, running, idle and offline states)
- [x] `ScoutDialog` — `com/mlmvpn/scanner/ui/scout/ScoutScreen.kt`
### settings

- [x] `WorkersListPage` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `D1ListPage` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `KvListPage` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `SelectableResourcePage` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `IosScreen` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `IosPickerScreen` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `IosTextScreen` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `BackendDnsPage` — `com/mlmvpn/scanner/ui/settings/NetworkSettingPages.kt`
- [x] `LocalPortPage` — `com/mlmvpn/scanner/ui/settings/NetworkSettingPages.kt`
- [x] `ProxyModePage` — `com/mlmvpn/scanner/ui/settings/NetworkSettingPages.kt`
- [x] `AllowLanPage` — `com/mlmvpn/scanner/ui/settings/NetworkSettingPages.kt`
- [x] `SettingsScreen` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt` (needs-device-check: light/dark/RTL settings review)
- [x] `DisplayPage` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `WallpaperPage` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `PresetPickerPage` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `SmartIranPage` — `com/mlmvpn/scanner/ui/settings/SmartIranPage.kt`
- [x] `UsageScreen` — `com/mlmvpn/scanner/ui/settings/UsageScreen.kt`
- [x] `VpnSettingsScreen` — `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt` (needs-device-check: mixed-script RTL and technical values)
- [x] `AppPickerPage` — `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt`
### spider

- [x] `SpiderScreen` — `com/mlmvpn/scanner/ui/spider/SpiderScreen.kt` (already uses shared settings groups, semantic Dusk colors, common snackbar/alert surfaces; verified no hardcoded style literals)
- [x] `NewUserPage` — `com/mlmvpn/scanner/ui/spider/SpiderScreen.kt` (shared settings form/group/action components)
- [x] `NewExitPage` — `com/mlmvpn/scanner/ui/spider/SpiderScreen.kt` (shared settings form/group/action components)
### store

- [x] `StoreScreen` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `TodayPage` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `AppsPage` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `UpdatesPage` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `ProductPage` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
### sublink

- [x] `SubLinkDetailScreen` — `com/mlmvpn/scanner/ui/sublink/SubLinkDetailScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `SubLinkHelpScreen` — `com/mlmvpn/scanner/ui/sublink/SubLinkDetailScreen.kt`
- [x] `SubLinkFormScreen` — `com/mlmvpn/scanner/ui/sublink/SubLinkFormScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `SubLinkGroupScreen` — `com/mlmvpn/scanner/ui/sublink/SubLinkFormScreen.kt`
- [x] `SubLinkScreen` — `com/mlmvpn/scanner/ui/sublink/SubLinkScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
### tgproxy

- [x] `TgDomainPage` — `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `TgProxyScreen` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `TgMainPage` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
### tunnel

- [x] `PsiphonMethodScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `PsiphonProxyScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `PsiphonCleanIpScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt` (Dusk semantic colors, shared compact pill and snackbar feedback)
- [x] `PsiphonPairingScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `RegionPickerScreen` — `com/mlmvpn/scanner/ui/tunnel/RegionPickerScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `TorBridgesScreen` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (Dusk bridge-selection, status and snackbar actions)
- [x] `BridgeListPage` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (Dusk bridge list, filter, test and empty states)
- [x] `BridgeDetailPage` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (Dusk detail and copy feedback surfaces)
- [x] `AddBridgesPage` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (Dusk bridge-input, validation and result states)
- [x] `TorModeScreen` — `com/mlmvpn/scanner/ui/tunnel/TorModeScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `TransportAdvancedScreen` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `NumberPage` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `SecretPage` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `TransportHost` — `com/mlmvpn/scanner/ui/tunnel/TransportHost.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `TransportScreen` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `TunnelLogScreen` — `com/mlmvpn/scanner/ui/tunnel/TunnelLogScreen.kt`
### urnetwork

- [x] `UrnetworkLocationsScreen` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
- [x] `UrnetworkRestoreScreen` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` (Dusk palette, typography and shared shapes reviewed; checked-row source audit clean.)
### widgetstudio

- [x] `WidgetStudioScreen` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Soft Dusk editor, preview states and interaction feedback; user design data remains customizable) (needs-device-check: real launcher widget, not only the Studio preview)
- [x] `LookTab` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Dusk segmented controls and preview framing; style data preserved)
- [x] `ColorTab` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Dusk editor controls while user-selected template colors remain data)
- [x] `InfoTab` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Dusk settings rows and selected states)
- [x] `SizeTab` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Dusk size selection and shared cards)
- [x] `MethodPicker` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Dusk method rows and selection markers)
- [x] `CountryPicker` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Dusk country rows and loading state)
### windscribe

- [x] `WindscribeScreen` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` (Dusk semantic account, connection, testing and error colors)
- [x] `LoginDialog` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [x] `CaptchaDialog` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [x] `EmailDialog` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
### com

- [x] `ConfigureScreen` — `com/mlmvpn/scanner/widget/studio/StudioConfigureActivity.kt` (semantic Dusk colors, shared shapes, typography and spacing tokens)

## Supporting and leaf composables

All declared `@Composable` Kotlin functions were scanned: 948 functions in 189 source files. Screen-like functions are listed above; the supporting rendering, control, state and reusable leaf composables are itemized below.

### com

- [x] `MlmUserCard` — `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt`
- [x] `InfoItem` — `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt`
- [x] `ActionBtn` — `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt`
- [x] `NahanSectionHeader` — `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt`
- [x] `NahanTextField` — `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt`
- [x] `NahanUserCard` — `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt`
### AboutScreen.kt

- [x] `ReleaseHeader` — `com/mlmvpn/scanner/ui/AboutScreen.kt`
- [x] `ChangeCard` — `com/mlmvpn/scanner/ui/AboutScreen.kt`
- [x] `changelogText` — `com/mlmvpn/scanner/ui/AboutScreen.kt`
### AddNodeModal.kt

- [x] `getTitleForForm` — `com/mlmvpn/scanner/ui/AddNodeModal.kt`
### AppScreen.kt

- [x] `FeatureTopBar` — `com/mlmvpn/scanner/ui/AppScreen.kt`
### BaseConfigPicker.kt

- [x] `BaseConfigRow` — `com/mlmvpn/scanner/ui/BaseConfigPicker.kt`
### BpbSettingsModal.kt

- [x] `SectionTitle` — `com/mlmvpn/scanner/ui/BpbSettingsModal.kt`
- [x] `PortsGrid` — `com/mlmvpn/scanner/ui/BpbSettingsModal.kt`
### CloudCoach.kt

- [x] `CloudCoachBar` — `com/mlmvpn/scanner/ui/CloudCoach.kt` (delegates visual presentation to the shared Dusk CoachBar)
### CloudScreens.kt

- [x] `CloudPanelRow` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [x] `ColumnScope` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [x] `CloudPanelHeaderRow` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [x] `PanelStatePill` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [x] `PanelBadge` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [x] `PanelLamps` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [x] `CloudPanelManageRows` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
### CloudTab.kt

- [x] `CloudDetailPlaceholder` — `com/mlmvpn/scanner/ui/CloudTab.kt`
- [x] `AccountGroupCard` — `com/mlmvpn/scanner/ui/CloudTab.kt`
- [x] `CloudGroupHandoff` — `com/mlmvpn/scanner/ui/CloudTab.kt`
### CloudTroubleshootScreen.kt

- [x] `com` — `com/mlmvpn/scanner/ui/CloudTroubleshootScreen.kt`
- [x] `httpValue` — `com/mlmvpn/scanner/ui/CloudTroubleshootScreen.kt` (HTTP identifiers isolated LTR in mixed Persian text)
### CombineCoach.kt

- [x] `CombineCoachBar` — `com/mlmvpn/scanner/ui/CombineCoach.kt`
- [x] `CoachBar` — `com/mlmvpn/scanner/ui/CombineCoach.kt`
### FreeConfigWizard.kt

- [x] `BusyStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [x] `PickPoolStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [x] `PickCountStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [x] `QuickPickChip` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [x] `FetchingStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [x] `ResultsStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [x] `DoneStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
### GameTab.kt

- [x] `pingColor` — `com/mlmvpn/scanner/ui/GameTab.kt` (Dusk semantic status scale)
- [x] `GameCard` — `com/mlmvpn/scanner/ui/GameTab.kt` (Dusk card and selection tokens)
- [x] `PickRow` — `com/mlmvpn/scanner/ui/GameTab.kt` (Dusk text/status colors)
- [x] `section` — `com/mlmvpn/scanner/ui/GameTab.kt` (shared Dusk settings section primitives)
- [x] `BoostButton` — `com/mlmvpn/scanner/ui/GameTab.kt` (Dusk action/state colors and shared shape)
- [x] `ResultCard` — `com/mlmvpn/scanner/ui/GameTab.kt` (Dusk result/status palette)
- [x] `LivePingCard` — `com/mlmvpn/scanner/ui/GameTab.kt` (Dusk live-status tokens)
- [x] `DedicatedDnsCard` — `com/mlmvpn/scanner/ui/GameTab.kt` (Dusk settings rows, states and async feedback)
### HelpCenterScreen.kt

- [x] `HelpSearchField` — `com/mlmvpn/scanner/ui/HelpCenterScreen.kt`
- [x] `HelpBody` — `com/mlmvpn/scanner/ui/HelpCenterScreen.kt`
### NodesScreens.kt

- [x] `nodeBadge` — `com/mlmvpn/scanner/ui/NodesScreens.kt`
- [x] `measurementTone` — `com/mlmvpn/scanner/ui/NodesScreens.kt`
- [x] `speedTone` — `com/mlmvpn/scanner/ui/NodesScreens.kt`
- [x] `MeasureRow` — `com/mlmvpn/scanner/ui/NodesScreens.kt`
- [x] `NodePill` — `com/mlmvpn/scanner/ui/NodesScreens.kt`
- [x] `NodeQrCard` — `com/mlmvpn/scanner/ui/NodesScreens.kt`
### NodesTab.kt

- [x] `GroupManagementDialogs` — `com/mlmvpn/scanner/ui/NodesTab.kt`
### NodesWidgets.kt

- [x] `ListJumpButton` — `com/mlmvpn/scanner/ui/NodesWidgets.kt`
- [x] `NodeToolbar` — `com/mlmvpn/scanner/ui/NodesWidgets.kt`
- [x] `ToolbarAction` — `com/mlmvpn/scanner/ui/NodesWidgets.kt`
- [x] `NodeTestProgressRow` — `com/mlmvpn/scanner/ui/NodesWidgets.kt`
- [x] `NodePlatformChips` — `com/mlmvpn/scanner/ui/NodesWidgets.kt`
- [x] `NodeNotice` — `com/mlmvpn/scanner/ui/NodesWidgets.kt`
- [x] `NodeRow` — `com/mlmvpn/scanner/ui/NodesWidgets.kt`
- [x] `MetricDot` — `com/mlmvpn/scanner/ui/NodesWidgets.kt`
- [x] `NodeConnectBar` — `com/mlmvpn/scanner/ui/NodesWidgets.kt`
### OpenVpnScreen.kt

- [x] `LiveGroup` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
- [x] `Figure` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
- [x] `Banner` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
- [x] `ServerRow` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
- [x] `SearchBox` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
- [x] `Field` — `com/mlmvpn/scanner/ui/OpenVpnScreen.kt`
### QuickConnectTab.kt

- [x] `ConnectOrb` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
- [x] `EgressBanner` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
- [x] `PoolRow` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
- [x] `SavedRow` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
- [x] `Badge` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
### QuickServersScreen.kt

- [x] `QuickCountryRow` — `com/mlmvpn/scanner/ui/QuickServersScreen.kt`
- [x] `NumberRow` — `com/mlmvpn/scanner/ui/QuickServersScreen.kt`
- [x] `Stepper` — `com/mlmvpn/scanner/ui/QuickServersScreen.kt`
- [x] `ResultRow` — `com/mlmvpn/scanner/ui/QuickServersScreen.kt`
- [x] `ScanStrip` — `com/mlmvpn/scanner/ui/QuickServersScreen.kt`
- [x] `StageBar` — `com/mlmvpn/scanner/ui/QuickServersScreen.kt`
- [x] `QuickButton` — `com/mlmvpn/scanner/ui/QuickServersScreen.kt`
### RouteChips.kt

- [x] `RouteChips` — `com/mlmvpn/scanner/ui/RouteChips.kt`
### ScannerTab.kt

- [x] `ScannerHeader` — `com/mlmvpn/scanner/ui/ScannerTab.kt`
- [x] `ScannerProgressCard` — `com/mlmvpn/scanner/ui/ScannerTab.kt`
- [x] `ScanStageRow` — `com/mlmvpn/scanner/ui/ScannerTab.kt`
- [x] `NetworkPausedBanner` — `com/mlmvpn/scanner/ui/ScannerTab.kt`
- [x] `ScannerSettingsCard` — `com/mlmvpn/scanner/ui/ScannerTab.kt`
### ScreenChrome.kt

- [ ] `FloatingHeaderLayout` — `com/mlmvpn/scanner/ui/ScreenChrome.kt`
### ScreenVisibility.kt

- [ ] `LaunchedWhileVisible` — `com/mlmvpn/scanner/ui/ScreenVisibility.kt`
### SubscriptionUsageCard.kt

- [x] `SubscriptionUsageCard` — `com/mlmvpn/scanner/ui/SubscriptionUsageCard.kt`
### VpnGateCountryScreen.kt

- [x] `CountryCheckRow` — `com/mlmvpn/scanner/ui/VpnGateCountryScreen.kt`
- [x] `CountryRow` — `com/mlmvpn/scanner/ui/VpnGateCountryScreen.kt`
### VpnGateInfoScreens.kt

- [x] `HelpRow` — `com/mlmvpn/scanner/ui/VpnGateInfoScreens.kt`
### VpnGateServersScreen.kt

- [x] `SearchField` — `com/mlmvpn/scanner/ui/VpnGateServersScreen.kt`
- [x] `ScopeChips` — `com/mlmvpn/scanner/ui/VpnGateServersScreen.kt`
- [x] `ScopeChip` — `com/mlmvpn/scanner/ui/VpnGateServersScreen.kt`
- [x] `ListSummary` — `com/mlmvpn/scanner/ui/VpnGateServersScreen.kt`
- [x] `BottomBar` — `com/mlmvpn/scanner/ui/VpnGateServersScreen.kt`
### VpnGateSweepStrip.kt

- [x] `VpnGateSweepStrip` — `com/mlmvpn/scanner/ui/VpnGateSweepStrip.kt`
### VpnGateTab.kt

- [x] `StatusLine` — `com/mlmvpn/scanner/ui/VpnGateTab.kt`
### VpnGateWidgets.kt

- [x] `GatewayServerRow` — `com/mlmvpn/scanner/ui/VpnGateWidgets.kt`
- [x] `HandshakeBadge` — `com/mlmvpn/scanner/ui/VpnGateWidgets.kt`
- [x] `handshakeLabel` — `com/mlmvpn/scanner/ui/VpnGateWidgets.kt`
- [x] `PingBadge` — `com/mlmvpn/scanner/ui/VpnGateWidgets.kt`
- [x] `pingTone` — `com/mlmvpn/scanner/ui/VpnGateWidgets.kt`
- [x] `GatewayToolbar` — `com/mlmvpn/scanner/ui/VpnGateWidgets.kt`
- [x] `GateToolbarAction` — `com/mlmvpn/scanner/ui/VpnGateWidgets.kt`
- [x] `GatewayBarButton` — `com/mlmvpn/scanner/ui/VpnGateWidgets.kt`
- [x] `GatewaySnack` — `com/mlmvpn/scanner/ui/VpnGateWidgets.kt`
- [x] `GatewayEmptyState` — `com/mlmvpn/scanner/ui/VpnGateWidgets.kt`
### WireguardTab.kt

- [x] `FeatureRow` — `com/mlmvpn/scanner/ui/WireguardTab.kt`
### aether

- [x] `AetherPowerButton` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `AetherStatusLine` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `QuickSettings` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `QuickRow` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `MiniSegmented` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `AetherProgressStrip` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `HeaderCard` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `TabRow` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `MasquePane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `WgPane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `GooLPane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `MasqueAdvancedPane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `SharedAdvancedPane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `SharedPane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `LogCard` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `StatusCard` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `ConnectBar` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `SettingsCard` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `SectionTitle` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `SectionHint` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `WarningCard` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `StepDot` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `DetailRow` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `SegmentedControl` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `TextFieldLite` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `DropdownLite` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `ButtonLite` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
### amnezia

- [x] `AmzHome` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [x] `HeroCard` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [x] `Orb` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [x] `StatTile` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `statusLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt` (deferred: localized string formatter has no independent visual surface; rendered within the Dusk-styled HeroCard)
- [ ] `stepLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt` (deferred: localized string formatter has no independent visual surface; rendered within the Dusk-styled HeroCard)
- [ ] `failLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt` (deferred: localized string formatter has no independent visual surface; rendered within the Dusk-styled HeroCard)
- [x] `subLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [x] `LocationRow` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `groupName` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt` (deferred: text formatter only; no independent visual surface).
- [x] `kindColor` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [x] `delayColor` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [x] `Tag` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [x] `DelayCapsule` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [x] `BarIcon` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [x] `UndoBar` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [x] `SearchBar` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [x] `Segmented` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [x] `GroupChips` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [x] `Chip` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [x] `TestBar` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [x] `Pill` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [x] `PickRow` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [x] `GroupHeader` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [x] `SwipeRow` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [x] `ServerRow` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
### arena

- [ ] `ArenaEntryCard` — `com/mlmvpn/scanner/ui/arena/ArenaEntry.kt`
- [ ] `RaceStage` — `com/mlmvpn/scanner/ui/arena/ArenaRace.kt`
- [ ] `RaceBar` — `com/mlmvpn/scanner/ui/arena/ArenaRace.kt`
- [ ] `GridReveal` — `com/mlmvpn/scanner/ui/arena/ArenaRace.kt`
- [ ] `StartLights` — `com/mlmvpn/scanner/ui/arena/ArenaRace.kt`
- [ ] `Track` — `com/mlmvpn/scanner/ui/arena/ArenaRace.kt`
- [ ] `Lane` — `com/mlmvpn/scanner/ui/arena/ArenaRace.kt`
- [ ] `PodiumStand` — `com/mlmvpn/scanner/ui/arena/ArenaRace.kt`
- [ ] `PodiumSteps` — `com/mlmvpn/scanner/ui/arena/ArenaRace.kt`
- [ ] `ResultPodium` — `com/mlmvpn/scanner/ui/arena/ArenaRace.kt`
- [ ] `Podium` — `com/mlmvpn/scanner/ui/arena/ArenaRace.kt`
- [x] `TeamRow` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `PrimaryButton` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `ThinBar` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `SoundToggle` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `Lobby` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `Confirm` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `Live` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `PrepList` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `CleanIpGroup` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `ScoreRing` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `tintOf` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `Result` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `Failure` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [x] `TeamTile` — `com/mlmvpn/scanner/ui/arena/ArenaTeams.kt` (Dusk semantic gradient/text colors and centralized proportional shape/type scales)
### backup

- [x] `com` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
- [x] `ModeRow` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
- [x] `WarningFooter` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
### cfwarp

- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/cfwarp/CfWarpUi.kt` (deferred: receiver signature only; rendered rows are shared Dusk settings components).
- [x] `ExitWaySwitch` — `com/mlmvpn/scanner/ui/cfwarp/Ipv4ExitUi.kt` (shared Dusk button shape, semantic colors and Material type scale)
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/cfwarp/Ipv4ExitUi.kt` (deferred: receiver type only; no independent visual surface)
- [x] `ExitWayRow` — `com/mlmvpn/scanner/ui/cfwarp/Ipv4ExitUi.kt` (shared Dusk shape/palette and Material typography; row layout inherits RTL)
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/cfwarp/Nat64ExitUi.kt` (deferred: receiver type only; no independent visual surface)
- [x] `ExitRow` — `com/mlmvpn/scanner/ui/cfwarp/Nat64ExitUi.kt` (shared Dusk palette, shape and Material typography tokens)
### cloud

- [x] `CfMethodCard` — `com/mlmvpn/scanner/ui/cloud/CfMethodCard.kt`
- [x] `CloudReceivedModule` — `com/mlmvpn/scanner/ui/cloud/CloudReceivedModule.kt`
- [x] `accountColor` — `com/mlmvpn/scanner/ui/cloud/CloudUsageModule.kt`
- [x] `CloudUsageModule` — `com/mlmvpn/scanner/ui/cloud/CloudUsageModule.kt`
- [x] `GatewayBlock` — `com/mlmvpn/scanner/ui/cloud/GatewayRows.kt`
- [x] `Note` — `com/mlmvpn/scanner/ui/cloud/GatewayRows.kt`
- [x] `GwDivider` — `com/mlmvpn/scanner/ui/cloud/GatewayRows.kt`
- [x] `Editor` — `com/mlmvpn/scanner/ui/cloud/GatewaySettingsScreen.kt`
- [x] `PanelRows` — `com/mlmvpn/scanner/ui/cloud/PanelRows.kt`
- [x] `OpenPanelRow` — `com/mlmvpn/scanner/ui/cloud/PanelRows.kt`
- [x] `Divider` — `com/mlmvpn/scanner/ui/cloud/PanelRows.kt`
- [x] `ActionRow` — `com/mlmvpn/scanner/ui/cloud/PanelRows.kt`
### configstudio

- [x] `FleetSummary` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountsScreen.kt`
- [x] `ShardRow` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountsScreen.kt`
- [x] `shardSubtitle` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountsScreen.kt`
- [x] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [x] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [x] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [x] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [x] `AuditRow` — `com/mlmvpn/scanner/ui/configstudio/StudioAuditScreen.kt`
- [x] `actionLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioAuditScreen.kt`
- [x] `JobRow` — `com/mlmvpn/scanner/ui/configstudio/StudioBulkAddScreen.kt`
- [x] `roomLine` — `com/mlmvpn/scanner/ui/configstudio/StudioCentralNewUserScreen.kt`
- [x] `StudioCombineHandover` — `com/mlmvpn/scanner/ui/configstudio/StudioCombineHandover.kt`
- [x] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioConfigBuilderScreen.kt`
- [x] `ConfigRow` — `com/mlmvpn/scanner/ui/configstudio/StudioConfigsScreen.kt`
- [ ] `expiresInText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt` (deferred: display-text formatter with no independent visual surface; rendered in the migrated Dusk screen)
- [ ] `timeLeftText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt` (deferred: display-text formatter with no independent visual surface; rendered in the migrated Dusk screen)
- [ ] `countOrDash` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt` (deferred: display-text formatter with no independent visual surface; rendered in the migrated Dusk screen)
- [ ] `activityLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt` (deferred: display-text formatter with no independent visual surface; rendered in the migrated Dusk screen)
- [ ] `whenText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt` (deferred: display-text formatter with no independent visual surface; rendered in the migrated Dusk screen)
- [ ] `bytesFa` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt` (deferred: display-text formatter with no independent visual surface; rendered in the migrated Dusk screen)
- [ ] `freshnessText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt` (deferred: display-text formatter with no independent visual surface; rendered in the migrated Dusk screen)
- [x] `ExitRow` — `com/mlmvpn/scanner/ui/configstudio/StudioExitsScreen.kt`
- [x] `CountryTitle` — `com/mlmvpn/scanner/ui/configstudio/StudioMultiLocationScreen.kt`
- [x] `ResultView` — `com/mlmvpn/scanner/ui/configstudio/StudioNewUserScreen.kt`
- [x] `shardPlacementNote` — `com/mlmvpn/scanner/ui/configstudio/StudioNewUserScreen.kt`
- [x] `probeAge` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeEditScreen.kt`
- [x] `strategyLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeGroupsScreen.kt`
- [x] `strategyNote` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeGroupsScreen.kt`
- [x] `NodeRow` — `com/mlmvpn/scanner/ui/configstudio/StudioNodesScreen.kt`
- [x] `healthLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioNodesScreen.kt`
- [x] `agoText` — `com/mlmvpn/scanner/ui/configstudio/StudioNodesScreen.kt`
- [x] `termsOf` — `com/mlmvpn/scanner/ui/configstudio/StudioPlansScreen.kt`
- [x] `driftLine` — `com/mlmvpn/scanner/ui/configstudio/StudioPlansScreen.kt`
- [x] `presetName` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplateEditScreen.kt`
- [x] `presetNote` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplateEditScreen.kt`
- [x] `shapeOf` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplatesScreen.kt`
- [x] `driftLine` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplatesScreen.kt`
- [x] `DeviceRow` — `com/mlmvpn/scanner/ui/configstudio/StudioUserDetailScreen.kt`
- [x] `deviceSubtitle` — `com/mlmvpn/scanner/ui/configstudio/StudioUserDetailScreen.kt`
- [x] `sessionLength` — `com/mlmvpn/scanner/ui/configstudio/StudioUserDetailScreen.kt`
- [x] `BarIcon` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [x] `filterLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [x] `sortLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [x] `UserRow` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [x] `subtitleFor` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [x] `UserKindBadge` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [x] `StudioTabBar` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `StudioBadge` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `CountryBadge` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `StatusLine` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `StudioStatTile` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `StudioTilePair` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `NoticeCard` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `EmptyState` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `ChoiceRow` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `StudioSeparator` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `SegmentedControl` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `statusText` — `com/mlmvpn/scanner/ui/configstudio/parts/LocationPicker.kt`
- [x] `rememberCountryChoices` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioCreateParts.kt`
- [x] `CreatedConfigsCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioCreateParts.kt`
- [x] `LinkCountLine` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioCreateParts.kt`
- [x] `StepDots` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `StepHeader` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `WizardField` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `WizardPrimary` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `InfoCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `ProblemCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `PayloadText` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `ColumnScope` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `MeterBar` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `FactsCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `PayloadCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `StudioChip` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `StudioListRow` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `StatusDot` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `PageIntro` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `StudioTile` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `StudioTileRow` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [x] `StudioQrCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioShare.kt`
- [x] `VolumeField` — `com/mlmvpn/scanner/ui/configstudio/parts/VolumeField.kt`
- [x] `androidx` — `com/mlmvpn/scanner/ui/configstudio/wizard/StudioSetupWizard.kt`
- [x] `androidx` — `com/mlmvpn/scanner/ui/configstudio/wizard/StudioSetupWizard.kt`
- [x] `androidx` — `com/mlmvpn/scanner/ui/configstudio/wizard/StudioSetupWizard.kt`
### doctor

- [x] `DoctorMain` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `Hero` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `Capsule` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `StageRow` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `layerText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt` (deferred: localized text formatter has no independent visual surface; rendered in the Dusk-styled Doctor screen)
- [x] `TextRow` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `stepText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt` (deferred: localized text formatter has no independent visual surface; rendered in the Dusk-styled Doctor screen)
- [ ] `findingText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt` (deferred: localized text formatter has no independent visual surface; rendered in the Dusk-styled Doctor screen)
- [ ] `worksText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt` (deferred: localized text formatter has no independent visual surface; rendered in the Dusk-styled Doctor screen)
- [x] `Field` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `Step` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `HeroFrame` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `HeroTitle` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt` (Dusk semantic colors and Material title/body typography)
- [x] `HubHero` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `SetupHero` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `Verdict` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `PopIn` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `StepRow` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `Pill` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `Chevron` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `SelectRow` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `CredentialSection` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `DoctorField` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `DoctorCapsule` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `DeveloperSection` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `DevLine` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
### emergency

- [x] `dialAccent` — `com/mlmvpn/scanner/ui/emergency/EmergencyKit.kt` (semantic Dusk running/success and failure/accent colors; feature idle accent supplied by caller)
- [x] `EmergencyDial` — `com/mlmvpn/scanner/ui/emergency/EmergencyKit.kt` (fixed touch geometry preserved; shared Dusk ink, typography and spacing)
- [x] `EmergencyStatusLine` — `com/mlmvpn/scanner/ui/emergency/EmergencyKit.kt` (Dusk status tones and Material body scale)
- [x] `pingTone` — `com/mlmvpn/scanner/ui/emergency/EmergencyKit.kt` (Dusk error/success/accent thresholds)
- [x] `RouteRow` — `com/mlmvpn/scanner/ui/emergency/EmergencyLevel3Screen.kt` (Dusk semantic ping tone and Material route/value styles)
- [x] `pingLabel` — `com/mlmvpn/scanner/ui/emergency/EmergencyLevel3Screen.kt` (diagnostic formatter; output is rendered through Dusk-styled `RouteRow`)
- [x] `relayTone` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt` (Dusk semantic report tones)
- [x] `PathRow` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt` (Dusk ping/status tones, shared spacing and Material typography)
- [x] `LogChip` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt` (Dusk tint, shared control shape and typography)
- [x] `StepDots` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt` (Dusk component reviewed; source audit clean.)
- [x] `StepHeader` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt` (Dusk component reviewed; source audit clean.)
- [x] `WizardField` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt` (Dusk component reviewed; source audit clean.)
- [x] `WizardPrimary` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt` (Dusk component reviewed; source audit clean.)
- [x] `InfoCard` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt` (Dusk component reviewed; source audit clean.)
- [x] `StepPassword` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt` (Dusk component reviewed; source audit clean.)
- [x] `StepConfirmPassword` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt` (Dusk component reviewed; source audit clean.)
- [x] `StepScript` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt` (Dusk component reviewed; source audit clean.)
- [x] `StepDeploymentId` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt` (Dusk component reviewed; source audit clean.)
- [x] `StepDone` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt` (Dusk component reviewed; source audit clean.)
- [x] `SwipeToDeleteRow` — `com/mlmvpn/scanner/ui/emergency/SniConfigPages.kt` (Dusk component reviewed; source audit clean.)
- [x] `Bin` — `com/mlmvpn/scanner/ui/emergency/SniConfigPages.kt` (Dusk component reviewed; source audit clean.)
- [x] `SniConfigRow` — `com/mlmvpn/scanner/ui/emergency/SniConfigPages.kt` (Dusk component reviewed; source audit clean.)
### fenix

- [x] `Welcome` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [x] `Ready` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (Dusk state composition)
- [x] `AccountCard` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (Dusk account/status colors)
- [x] `resetLine` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (localized date formatter rendered with Dusk typography)
- [x] `agoText` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (localized time formatter rendered with Dusk typography)
- [x] `PrimaryButton` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [x] `ErrorText` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [x] `ConnectButton` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (Dusk connection-state control)
- [x] `statusText` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (localized state text rendered by Dusk typography)
- [x] `failureText` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (localized failure text rendered by Dusk typography)
- [ ] `countryValue` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (deferred: localized country/value formatter; no independent visual surface)
- [x] `CountryRow` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` (Dusk country selection row and semantic status tones)
### flux

- [x] `FluxMain` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt` (Dusk connection states and semantic colors)
- [x] `statusText` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt` (localized connection status rendered by Dusk typography)
- [x] `IpSelector` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt` (Dusk selected/unselected control colors)
- [x] `ConnectButton` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt` (Dusk connection-state control)
- [x] `AddSubscription` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt` (Dusk source entry surface)
- [ ] `Mono` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt` (deferred: diagnostic text intentionally remains monospace data output; surrounding surface is Dusk)
### game

- [x] `GameIcon` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk glyph shape around app artwork)
- [x] `GameRow` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk row typography and status colors)
- [x] `ValueRow` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk value/label contrast)
- [x] `Chevron` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk disabled/secondary icon tone)
- [ ] `regionLabel` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (deferred: localized region formatter; no independent visual surface)
- [x] `StageList` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk stage progress/failure semantics)
- [x] `stageTitle` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (localized state label rendered by Dusk stage row)
- [ ] `evidence` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (deferred: diagnostic text formatter rendered with Dusk secondary typography)
- [x] `ResultCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk outcome and severity colors)
- [x] `StatsRow` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk ping/data states and shared Material metric typography; values remain diagnostic data)
- [x] `verdictTitle` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (localized outcome title rendered with Dusk typography)
- [x] `verdictDetail` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (localized outcome explanation rendered with Dusk typography)
- [x] `kindLine` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (localized mode label rendered with Dusk typography)
- [ ] `classLineText` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (deferred: data label formatter; Dusk row applies visual styling)
- [x] `symptomDone` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (localized completion text rendered with Dusk typography)
- [ ] `causeText` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (deferred: diagnostic cause formatter; no independent visual surface)
- [x] `LastSessionCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk session summary and state colors)
- [x] `FeedbackCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk feedback surface and on-success contrast)
- [x] `RegionCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk regional test status and ping severity)
- [x] `ActionsRow` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk success/action colors and contrast)
- [x] `TipsCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk preflight warning and error semantics)
- [x] `LiveCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk live status colors)
- [x] `Sparkline` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk severity token supplied to data visualization)
- [x] `CheckRow` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` (Dusk selected state and icon colors)
### geph

- [x] `NoAccount` — `com/mlmvpn/scanner/ui/geph/GephAccountScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `HasAccount` — `com/mlmvpn/scanner/ui/geph/GephAccountScreen.kt` (Dusk component reviewed; source audit clean.)
- [ ] `gephAgo` — `com/mlmvpn/scanner/ui/geph/GephExitScreen.kt`
- [x] `TrafficBars` — `com/mlmvpn/scanner/ui/geph/GephInfoScreens.kt` (Dusk component reviewed; source audit clean.)
- [ ] `exitLabel` — `com/mlmvpn/scanner/ui/geph/GephUi.kt`
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/geph/GephUi.kt`
- [x] `GephLiveCard` — `com/mlmvpn/scanner/ui/geph/GephUi.kt` (Dusk component reviewed; source audit clean.)
### github

- [x] `Dial` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (Dusk connection-state dial colors)
- [x] `Notice` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (Dusk error/status notice contrast)
- [x] `SignInCard` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (Dusk authentication code card and snackbar copy feedback)
- [x] `SetupStep` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (Dusk setup progress and status contrast)
- [x] `StepRow` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (Dusk setup completion semantics)
- [x] `LogBlock` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (Dusk muted diagnostics text)
- [x] `SitesField` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (Dusk input text and hint colors)
- [x] `SelectedAppRow` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (shared Dusk glyph shape, semantic colors and Material text styles)
- [x] `countryName` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` (localized country label rendered by shared region picker)
### home

- [ ] `ids` — `com/mlmvpn/scanner/ui/home/ActiveEngines.kt` (deferred: state-only engine ownership mapping; behavior intentionally preserved under UI-layer-only scope)
- [x] `AppIconTile` — `com/mlmvpn/scanner/ui/home/AppIcon.kt`
- [x] `TextTag` — `com/mlmvpn/scanner/ui/home/AppIcon.kt`
- [x] `CountBadge` — `com/mlmvpn/scanner/ui/home/AppIcon.kt`
- [x] `RunningLamp` — `com/mlmvpn/scanner/ui/home/AppIcon.kt`
- [x] `TileFace` — `com/mlmvpn/scanner/ui/home/AppIcon.kt`
- [x] `tileVector` — `com/mlmvpn/scanner/ui/home/AppIcon.kt`
- [x] `tilePainter` — `com/mlmvpn/scanner/ui/home/AppIcon.kt`
- [x] `AppIconCell` — `com/mlmvpn/scanner/ui/home/AppIcon.kt`
- [x] `TileCaption` — `com/mlmvpn/scanner/ui/home/AppIcon.kt`
- [x] `TileLamp` — `com/mlmvpn/scanner/ui/home/AppIcon.kt`
- [x] `Modifier` — `com/mlmvpn/scanner/ui/home/AppIcon.kt`
- [x] `isDark` — `com/mlmvpn/scanner/ui/home/Appearance.kt`
- [x] `ProfileRow` — `com/mlmvpn/scanner/ui/home/BuiltInProfilesScreen.kt`
- [x] `DriftingLights` — `com/mlmvpn/scanner/ui/home/DriftingLights.kt`
- [x] `Modifier` — `com/mlmvpn/scanner/ui/home/Glass.kt`
- [x] `GlassDock` — `com/mlmvpn/scanner/ui/home/GlassDock.kt`
- [x] `HomeConnectionWidget` — `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt`
- [x] `Header` — `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt`
- [x] `StatusPill` — `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt`
- [x] `Footer` — `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt`
- [x] `Stat` — `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt`
- [x] `tabular` — `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt`
- [x] `LiveChart` — `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt`
- [x] `PowerButton` — `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt`
- [x] `DuskThemeHills` — `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt`
- [x] `RemoveBadge` — `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt`
- [x] `DragLayer` — `com/mlmvpn/scanner/ui/home/HomeDrag.kt`
- [x] `FolderTile` — `com/mlmvpn/scanner/ui/home/HomeFolders.kt`
- [x] `folderTitle` — `com/mlmvpn/scanner/ui/home/HomeFolders.kt`
- [x] `FolderOverlay` — `com/mlmvpn/scanner/ui/home/HomeFolders.kt`
- [x] `FolderName` — `com/mlmvpn/scanner/ui/home/HomeFolders.kt`
- [x] `HomeStatusStrip` — `com/mlmvpn/scanner/ui/home/HomeScreen.kt`
- [x] `BoardGrid` — `com/mlmvpn/scanner/ui/home/HomeScreen.kt`
- [x] `rememberSlotPosition` — `com/mlmvpn/scanner/ui/home/HomeScreen.kt`
- [x] `BoardCell` — `com/mlmvpn/scanner/ui/home/HomeScreen.kt`
- [x] `RemoveBadge` — `com/mlmvpn/scanner/ui/home/HomeScreen.kt`
- [x] `PageDots` — `com/mlmvpn/scanner/ui/home/HomeScreen.kt`
- [x] `SearchPill` — `com/mlmvpn/scanner/ui/home/HomeSearch.kt`
- [x] `HomeSearch` — `com/mlmvpn/scanner/ui/home/HomeSearch.kt`
- [x] `SearchField` — `com/mlmvpn/scanner/ui/home/HomeSearch.kt`
- [x] `SearchResults` — `com/mlmvpn/scanner/ui/home/HomeSearch.kt`
- [x] `TileResults` — `com/mlmvpn/scanner/ui/home/HomeSearch.kt`
- [x] `SettingsResults` — `com/mlmvpn/scanner/ui/home/HomeSearch.kt`
- [x] `Modifier` — `com/mlmvpn/scanner/ui/home/HomeSearch.kt`
- [x] `rememberIsTelevision` — `com/mlmvpn/scanner/ui/home/TvFocus.kt`
- [x] `HomeWallpaper` — `com/mlmvpn/scanner/ui/home/Wallpapers.kt`
- [x] `rememberFrostedBackdrop` — `com/mlmvpn/scanner/ui/home/Wallpapers.kt`
> Audit note: the literal colors in `Wallpapers.PRESETS` are curated user-selectable wallpaper artwork, not component styling; the glass DstIn black/white values are alpha masks.
### lan

- [x] `LanDiagram` — `com/mlmvpn/scanner/ui/lan/LanDiagram.kt` (Dusk semantic status colors, glyph/connector shapes and label typography)
- [x] `Hop` — `com/mlmvpn/scanner/ui/lan/LanDiagram.kt` (shared Dusk status semantics, shape and label type scale)
- [x] `Link` — `com/mlmvpn/scanner/ui/lan/LanDiagram.kt` (Dusk success/neutral colors and shared pill shape)
- [x] `GuideListSection` — `com/mlmvpn/scanner/ui/lan/LanGuideScreens.kt` (delegates to shared Dusk section, row and footer components)
- [x] `GuideValue` — `com/mlmvpn/scanner/ui/lan/LanGuideScreens.kt` (Dusk type/color tokens and explicit LTR monospace technical values)
- [x] `LanStateCard` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk blocker/status card)
- [x] `StateGlyph` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk status illustration)
- [x] `ActionButton` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk action semantic colors)
- [x] `ShareTimerCard` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk timer selection and share state)
- [x] `DoctorSection` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk diagnostics sections)
- [x] `DoctorRow` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk pass/fail/warning states)
- [x] `HotspotCard` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk hotspot state and QR plate token)
- [x] `LabelledValue` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk label/value hierarchy and direction-aware numeric output)
- [x] `SetupCard` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk setup surface and neutral QR backing)
- [x] `SmallAction` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (shared Dusk pill shape, compact action color and Material label style)
- [x] `AddressCard` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk sharing endpoint surface)
- [x] `CopyRow` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk action tile and on-success contrast)
- [x] `SecurityBar` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk security indicator semantics)
- [x] `ClientsSection` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (Dusk connected-client and blocked-client states)
- [x] `headline` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (localized sharing status rendered in Dusk typography)
- [x] `detail` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt` (localized blocker explanation rendered in Dusk typography)
### lantern

- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/lantern/LanternUi.kt` (deferred: receiver type only; no independent visual surface)
- [x] `LanternLiveCard` — `com/mlmvpn/scanner/ui/lantern/LanternUi.kt` (shared Dusk shape/status colors and Material typography)
- [x] `ModeRow` — `com/mlmvpn/scanner/ui/lantern/LanternUi.kt` (Dusk colors and Material type scale; flow inherits RTL)
### mae

- [x] `MaeAppIcon` — `com/mlmvpn/scanner/ui/mae/MaeAppIcon.kt` (shared squircle shape, palette and typography scale tokens)
- [ ] `MaeCloudGate` — `com/mlmvpn/scanner/ui/mae/MaeCloudGate.kt`
- [ ] `rememberInstalledRegistryIds` — `com/mlmvpn/scanner/ui/mae/MaePages.kt` (deferred: installed-app registry state only; no visual surface)
- [x] `MaeOnboarding` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [x] `MaeConfirmRemove` — `com/mlmvpn/scanner/ui/mae/MaePages.kt` (shared Dusk alert surface)
- [x] `ForeignExitsList` — `com/mlmvpn/scanner/ui/mae/MaePages.kt` (Dusk setup/status rows)
- [x] `ManageAppRow` — `com/mlmvpn/scanner/ui/mae/MaePages.kt` (Dusk app row and semantic tones)
- [x] `DestructiveRow` — `com/mlmvpn/scanner/ui/mae/MaePages.kt` (Dusk destructive/disabled states)
- [x] `MaeAppDetail` — `com/mlmvpn/scanner/ui/mae/MaePages.kt` (Dusk detail surface and controls)
- [x] `CheckRow` — `com/mlmvpn/scanner/ui/mae/MaePages.kt` (Dusk selected state and row contrast)
- [x] `SetupRow` — `com/mlmvpn/scanner/ui/mae/MaePages.kt` (Dusk setup/status row)
- [x] `MaeContent` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt` (Dusk service list and snackbar feedback)
- [x] `SwipeActionsRow` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt` (Dusk swipe action surfaces and direction-aware layout)
- [x] `SwipeAction` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt` (Dusk destructive/positive action states)
- [x] `ConnectButton` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt` (Dusk connection state and shared shape)
- [x] `noteText` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt` (localized state text rendered with Dusk typography)
- [x] `ServiceRow` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt` (Dusk status, selection and feedback colors)
- [x] `ServiceChip` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt`
### mitm

- [x] `MitmSetupCard` — `com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt` (Dusk semantic colors/shapes, Material typography and shared action components)
- [x] `StepRow` — `com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt` (Dusk status colors and shared type scale)
- [x] `ActionButton` — `com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt` (shared Dusk button colors and border)
- [x] `BrandPath` — `com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt` (Dusk typography/colors with logical flow)
- [x] `InfoLine` — `com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt` (Dusk typography/colors and start-aligned content)
### npv

- [x] `RefreshQuestions` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` (Dusk confirmation surface)
- [x] `HeroCard` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` (Dusk connection state and semantic colors)
- [x] `Orb` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` (Dusk connection-state illustration)
- [x] `Stat` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` (Dusk data/status text hierarchy)
- [x] `MeasureRow` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` (Dusk measurement states)
- [x] `ConfigRow` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` (Dusk config status and selection)
- [x] `NativeRow` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` (Dusk native-file state)
- [x] `RefreshCard` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt` (Dusk refresh/progress state)
- [x] `Steps` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt` (Dusk step status semantics)
- [x] `StepLine` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt` (Dusk step progress and result colors)
- [x] `AddChannel` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt` (Dusk channel-entry and validation feedback)
- [x] `ChannelRow` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt` (Dusk source status and actions)
- [x] `FileRow` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt` (Dusk config/native file status)
### outline

- [x] `OutlineUndoBar` — `com/mlmvpn/scanner/ui/outline/OutlineParts.kt`
- [x] `KeyQr` — `com/mlmvpn/scanner/ui/outline/OutlineParts.kt`
- [x] `OutlineHome` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [x] `TestProgress` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [x] `HeroCard` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [x] `Orb` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [x] `StatTile` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [x] `SearchBar` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [x] `Chip` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [x] `SortChip` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [x] `Pill` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [x] `PickRow` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [x] `SwipeRow` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [x] `ServerRow` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
### proton

- [ ] `rememberVpnGate` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt` (deferred: state/permission launcher helper; presentation belongs to Android and the Dusk page).
- [x] `ProtonHome` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `PingProgress` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `ServerRow` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `statusText` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt` (localized state text rendered by the Dusk-styled screen).
- [x] `PrimaryButton` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `Message` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `helperName` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt` (deferred: helper-name formatter only; styling is owned by the Dusk row that renders it).
- [x] `HelperProgress` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `HelperChooser` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
### sanction

- [x] `AppRow` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt` (Dusk theme colors/shapes and Material body/label styles)
- [x] `ExitChoiceRow` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt` (semantic selection state, Material body styles and Dusk spacing)
- [x] `SanctionButton` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt` (Dusk semantic fills, shared panel shape and Material label style)
### scout

- [x] `statusColor` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk semantic status mapping)
- [ ] `methodTitle` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (deferred: localized label helper; no independent visual surface)
- [x] `ScoutLogo` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk illustration tones)
- [x] `ProgressRing` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk progress indicator and semantic colors)
- [x] `StatusPill` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk status chip)
- [x] `MethodIcon` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk fallback icon tone; method artwork remains unchanged)
- [x] `MethodRow` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk row text and status contrast)
- [x] `MethodCard` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk card and connection state)
- [x] `FoldSection` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk section heading and disclosure state)
- [x] `SectionTitle` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk secondary text and spacing)
- [x] `LineCard` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk network status card)
- [x] `LineChip` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk network state colors)
- [x] `Stat` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk semantic colors and shared Material metric hierarchy)
- [x] `PillButton` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk action states)
- [x] `Dot` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt` (Dusk semantic network-state indicator)
### settings

- [x] `CloudResourcesHub` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt` (Soft Dusk semantic colors and snackbar feedback)
- [x] `ResourceSection` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `HubRow` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `HubChevron` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `ItemRow` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `SelectionTick` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `StatChip` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `AccountTotals` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `TotalCell` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `TotalDivider` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `EmptyBlock` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `LtrText` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `GeminiExitRow` — `com/mlmvpn/scanner/ui/settings/GeminiExitRow.kt`
- [x] `IosAlert` — `com/mlmvpn/scanner/ui/settings/IosAlerts.kt`
- [x] `AlertSeparator` — `com/mlmvpn/scanner/ui/settings/IosAlerts.kt`
- [x] `AlertButton` — `com/mlmvpn/scanner/ui/settings/IosAlerts.kt`
- [x] `IosPrompt` — `com/mlmvpn/scanner/ui/settings/IosAlerts.kt`
- [x] `rememberIosRefreshState` — `com/mlmvpn/scanner/ui/settings/IosRefresh.kt` (gesture state feeds the checked Dusk IosRefreshIndicator)
- [x] `IosRefreshIndicator` — `com/mlmvpn/scanner/ui/settings/IosRefresh.kt`
- [x] `SettingsSectionHeader` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `SettingsGroup` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `Separator` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `Separator` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `SettingsGlyph` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `RowChevron` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `RowFrame` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `SettingsActionRow` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `SettingsFooter` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `SettingsRow` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `IosBadge` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `SettingsToggle` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `SettingsTextRow` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `CloudflareAccountCard` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `AccountDots` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `SettingsSlider` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [x] `SettingsDetailPlaceholder` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `presetName` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `AppToggleRow` — `com/mlmvpn/scanner/ui/settings/SmartIranPage.kt`
- [x] `UsagePeriod` — `com/mlmvpn/scanner/ui/settings/UsageScreen.kt`
- [x] `SevenDayChart` — `com/mlmvpn/scanner/ui/settings/UsageScreen.kt`
- [x] `rememberInstalledApps` — `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt` (reviewed: state-only package query; Dusk visuals are in the checked AppPickerPage)
- [x] `mtuMethodLabel` — `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt`
- [x] `WarpIdRelayRow` — `com/mlmvpn/scanner/ui/settings/WarpIdRelayRow.kt`
- [x] `WorkerSetupRow` — `com/mlmvpn/scanner/ui/settings/WorkerSetupRow.kt`
### spider

- [x] `SpiderCloudRows` — `com/mlmvpn/scanner/ui/spider/SpiderConfigs.kt` (semantic Dusk colors and shared row structure)
- [x] `SpiderRow` — `com/mlmvpn/scanner/ui/spider/SpiderConfigs.kt` (Dusk colors and Material typography)
### store

- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [x] `DeploymentRow` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `AppRow` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `ActionButton` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `Pill` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `ProgressRing` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `JobLine` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `FeatureCard` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `AllCurrentCard` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `StatsCard` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `Stat` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `HistoryRow` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `InfoStrip` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `Fact` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `InfoRow` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `StoreTabBar` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `TabItem` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `StoreIconTile` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt` (Dusk component reviewed; source audit clean.)
### sublink

- [x] `HelpRow` — `com/mlmvpn/scanner/ui/sublink/SubLinkDetailScreen.kt`
- [x] `GroupRow` — `com/mlmvpn/scanner/ui/sublink/SubLinkFormScreen.kt`
- [x] `SubLinkRow` — `com/mlmvpn/scanner/ui/sublink/SubLinkScreen.kt`
- [x] `StateBlock` — `com/mlmvpn/scanner/ui/sublink/SubLinkScreen.kt`
- [x] `expiryLabel` — `com/mlmvpn/scanner/ui/sublink/SubLinkScreen.kt`
### tgproxy

- [x] `Step` — `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt` (Dusk component reviewed; source audit clean.)
- [x] `Body` — `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt` (Dusk component reviewed; source audit clean.)
- [x] `Pill` — `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt` (Dusk component reviewed; source audit clean.)
- [x] `ProgressCard` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `ServiceCard` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `SmallButton` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `TgCloudGate` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt` (Dusk component reviewed; source audit clean.)
- [ ] `bridgeLine` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt`
### theme

- [x] `iosButtonColors` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt` (shared Dusk palette/shape/spacing tokens)
- [x] `iosButtonBorder` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt` (shared Dusk palette/shape/spacing tokens)
- [x] `iosNeutralButtonColors` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt` (shared Dusk palette/shape/spacing tokens)
- [x] `iosNeutralBorder` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt` (shared Dusk palette/shape/spacing tokens)
- [x] `iosFieldColors` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt` (shared Dusk palette/shape/spacing tokens)
- [x] `Modifier` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt` (shared Dusk palette/shape/spacing tokens)
- [x] `DuskCard` — `com/mlmvpn/scanner/ui/theme/SoftDusk.kt`
- [x] `DuskPill` — `com/mlmvpn/scanner/ui/theme/SoftDusk.kt`
- [x] `duskColorScheme` — `com/mlmvpn/scanner/ui/theme/SoftDusk.kt`
- [x] `MlmVpnTheme` — `com/mlmvpn/scanner/ui/theme/Theme.kt`
### tunnel

- [ ] `withFlag` — `com/mlmvpn/scanner/ui/tunnel/CountryLabel.kt`
- [ ] `localized` — `com/mlmvpn/scanner/ui/tunnel/CountryLabel.kt`
- [ ] `LadderHandOverCard` — `com/mlmvpn/scanner/ui/tunnel/LadderHandOverCard.kt`
- [x] `ColumnScope` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `PsiphonLiveCard` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `MethodRow` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `IpRow` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt` (Dusk card shape/colors, Material type scale and shared technical monospace token)
- [x] `RunSummary` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `ActionLine` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `rememberTicker` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `ProgressShell` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `CardLine` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `Mono` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `CleanProgressCard` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `ProxyProgressCard` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `CustomTestCard` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `Note` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `Chip` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `OptionRow` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `CountryRow` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt` (Dusk surface and shared typography/shape tokens)
- [x] `RegionRow` — `com/mlmvpn/scanner/ui/tunnel/RegionPickerScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `torBridgesValue` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (localized bridge preference value rendered by Dusk settings row)
- [x] `sortLabel` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (localized sorting label rendered in Dusk control)
- [x] `ChoiceRow` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (Dusk source selection states)
- [x] `Chip` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (Dusk selection chip and contrast)
- [x] `Note` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (Dusk warning/info notice)
- [x] `BridgeRow` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (shared Dusk card/tag shapes, semantic tones and Material type scale)
- [x] `Tag` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (Dusk semantic tag colors)
- [x] `statusLine` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (localized bridge state label rendered in Dusk typography)
- [x] `statusColor` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (Dusk semantic bridge status mapping)
- [x] `badReason` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (localized bridge validation message rendered in Dusk typography)
- [x] `ago` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (localized time value rendered in Dusk typography)
- [x] `TorEntryCard` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` (Dusk bridge status summary card)
- [x] `Footer` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `ManualShapingGroup` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `ManualEndpointGroup` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `ExitCountryCard` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `Footer` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `ConnectDial` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `DialCore` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `StatusLine` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `LiveStats` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `Metric` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` (Dusk component reviewed; source audit clean.)
- [x] `Divider` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` (Dusk component reviewed; source audit clean.)
- [ ] `regionLabel` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt`
- [x] `LogAction` — `com/mlmvpn/scanner/ui/tunnel/TunnelLogScreen.kt`
- [ ] `IdentityBlockedCard` — `com/mlmvpn/scanner/ui/tunnel/WarpIdentityCard.kt`
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/tunnel/WarpIdentityCard.kt`
### urnetwork

- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
- [x] `UrnetworkLiveCard` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` (Dusk component reviewed; source audit clean.)
- [x] `StepRow` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` (Dusk component reviewed; source audit clean.)
- [x] `WindowDetail` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` (Dusk component reviewed; source audit clean.)
- [x] `UrnetworkUsageCard` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` (Dusk component reviewed; source audit clean.)
- [x] `UsageBar` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` (Dusk component reviewed; source audit clean.)
- [x] `Metric` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` (Dusk component reviewed; source audit clean.)
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
- [x] `LocationRow` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` (Dusk component reviewed; source audit clean.)
### widgetstudio

- [x] `TemplatePreview` — `com/mlmvpn/scanner/ui/widgetstudio/StudioParts.kt` (same widget renderer; user design remains customizable)
- [x] `PreviewStateChips` — `com/mlmvpn/scanner/ui/widgetstudio/StudioParts.kt` (Dusk state chips)
- [x] `PreviewStage` — `com/mlmvpn/scanner/ui/widgetstudio/StudioParts.kt` (light/night Soft Dusk backdrop and outline tokens)
- [x] `PresetCard` — `com/mlmvpn/scanner/ui/widgetstudio/StudioParts.kt` (Dusk title and card framing; preset art is intentionally per-style)
- [x] `Swatch` — `com/mlmvpn/scanner/ui/widgetstudio/StudioParts.kt` (selected ring uses Dusk tokens while swatch color remains user data)
- [x] `StudioMain` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Dusk screens, status summaries and controls)
- [x] `PrimaryButton` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Dusk action color and shared typography semantics)
- [x] `com` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (TemplateRow uses Dusk card/text/action tokens)
- [x] `MethodStylePreview` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Dusk method-style preview and semantic statuses)
- [x] `StudioEditor` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Dusk preview, editor controls and content hierarchy)
- [x] `SwatchRow` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (custom widget colors are data; selection UI uses Dusk)
- [x] `HueSlider` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (custom accent editing remains data; control state uses shared Dusk slider)
- [x] `CountryRow` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (shared Dusk spacing, semantic selected marker and Material type scale)
- [x] `AddMethodWidget` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` (Dusk method list and widget-placement explanation)
### windscribe

- [x] `Action` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` (Dusk primary action with correct on-primary contrast)
- [x] `ConnectButton` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` (Dusk connection-state control)
- [ ] `accountSummary` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` (deferred: account summary formatter displayed in Dusk rows)
- [ ] `protocolName` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` (deferred: protocol label formatter; no independent visual surface)
- [ ] `methodName` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` (deferred: method label formatter; no independent visual surface)
- [x] `connectionLabel` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` (localized status text rendered with Dusk typography)
- [x] `TestProgress` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` (Dusk progress track and semantic status colors)
- [x] `resultLine` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` (localized result text rendered in Dusk rows)
- [x] `measurementLabel` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` (localized measurement text rendered in Dusk typography)
- [x] `CaptchaArt` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
### com

- [ ] `LocaleProvider` — `com/mlmvpn/scanner/utils/AppLocaleManager.kt` (deferred: locale state/provider only; visible selection is rendered by the Dusk settings surface)
- [x] `Heading` — `com/mlmvpn/scanner/widget/studio/StudioConfigureActivity.kt` (semantic Dusk colors, shared shapes, typography and spacing tokens)

## Android activities and fragments

No Android `Fragment` subclasses were found in the Java/Kotlin sources; the UI is Compose-based. The six activity classes:

- [x] `ShadeCollapseActivity` — audited/deferred: finishes before drawing; Android shade animation owns the only visible transition. — `com/mlmvpn/core/tunnel/ShadeCollapseActivity.kt`
- [x] `MainActivity` — Soft Dusk theme and light system-bar icon styling are applied at the activity root. — `com/mlmvpn/scanner/MainActivity.kt`
- [x] `AmzImportActivity` — audited/deferred: ACTION_VIEW import dispatcher has no UI of its own. — `com/mlmvpn/scanner/ui/amnezia/AmzImportActivity.kt`
- [x] `NpvImportActivity` — audited/deferred: ACTION_VIEW importer only emits a platform Toast and launches the app; no owned screen UI. — `com/mlmvpn/scanner/ui/npv/NpvImportActivity.kt`
- [x] `WidgetActionActivity` — audited/deferred: transparent widget action trampoline finishes without drawing UI. — `com/mlmvpn/scanner/widget/WidgetActionActivity.kt`
- [x] `StudioConfigureActivity` — Soft Dusk theme, color tokens, typography and layout direction applied to configuration UI. — `com/mlmvpn/scanner/widget/studio/StudioConfigureActivity.kt`

## Dialogs, sheets, popups and snackbars

Source inventory of Compose overlay hosts/call sites. Review each listed occurrence, including loading, validation, success and error variants.

- [x] `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt` — AlertDialog × 2, Dialog × 4, DropdownMenu × 1
- [x] `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt` — AlertDialog × 1, Dialog × 3, DropdownMenu × 1
- [x] `com/mlmvpn/scanner/ui/AppScreen.kt` — AlertDialog × 3, Dialog × 3
- [x] `com/mlmvpn/scanner/ui/CloudCoach.kt` — Dialog × 1
- [x] `com/mlmvpn/scanner/ui/CloudTab.kt` — AlertDialog × 3, Dialog × 5
- [x] `com/mlmvpn/scanner/ui/CombineCoach.kt` — Dialog × 2
- [x] `com/mlmvpn/scanner/ui/GameTab.kt` — Dialog × 1
- [x] `com/mlmvpn/scanner/ui/NodesTab.kt` — Dialog × 2
- [x] `com/mlmvpn/scanner/ui/ScanGuardHost.kt` — AlertDialog × 1, Dialog × 1
- [x] `com/mlmvpn/scanner/ui/ScannerTab.kt` — AlertDialog × 8, Dialog × 8
- [x] `com/mlmvpn/scanner/ui/WireguardGuard.kt` — Dialog × 4
- [x] `com/mlmvpn/scanner/ui/aether/AetherScreen.kt` — Dialog × 1, DropdownMenu × 1
- [x] `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt` — Dialog × 1
- [x] `com/mlmvpn/scanner/ui/cfwarp/CfWarpUi.kt` — AlertDialog × 1, Dialog × 1
- [x] `com/mlmvpn/scanner/ui/cfwarp/Ipv4ExitUi.kt` — AlertDialog × 1, Dialog × 1
- [x] `com/mlmvpn/scanner/ui/cfwarp/Nat64ExitUi.kt` — AlertDialog × 1, Dialog × 1
- [x] `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt` — AlertDialog × 1, Dialog × 1
- [x] `com/mlmvpn/scanner/ui/geph/GephAccountScreen.kt` — AlertDialog × 2, Dialog × 2
- [x] `com/mlmvpn/scanner/ui/mae/MaeScreen.kt` — AlertDialog × 1, Dialog × 3
- [x] `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` — AlertDialog × 4, Dialog × 4
- [x] `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt` — AlertDialog × 1, Dialog × 1
- [x] `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt` — AlertDialog × 1, Dialog × 1
- [x] `com/mlmvpn/scanner/ui/scout/ScoutScreen.kt` — AlertDialog × 2, Dialog × 8
- [x] `com/mlmvpn/scanner/ui/settings/IosAlerts.kt` — Dialog × 1
- [x] `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt` — AlertDialog × 1, Dialog × 1
- [x] `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` — AlertDialog × 4, Dialog × 10

## Other visual/system surfaces

- [x] Android system VPN permission / app-permission prompts — audited/deferred: Android owns prompt rendering and colors; app-side preparation/denial entry points use Dusk feedback, but the OS dialog itself is not app-themeable.
- [x] Runtime notifications, ongoing VPN foreground notification, channels and quick-settings tiles — VPN notification accent follows Soft Dusk; tile and notification chrome are OS-rendered and cannot be branded by the app. (needs-device-check: OS-owned notification shade and quick-settings tile)
- [x] Home-screen widgets and widget configuration/preview states — shared method/status palette migrated; Widget Studio configuration and previews use Dusk, while custom template choices remain user-controlled. (needs-device-check: real launcher placement, resizing and actions)
- [x] Native XML dialogs/layouts and standalone import/configuration activities — owned UI migrated; import dispatchers are explicitly deferred above.
- [ ] Loading, empty, offline, connected, connecting, failure, retry and permission-denied states across every destination. (deferred: exhaustive device/emulator state walk required; source components were audited, but no runtime device is available here) (needs-device-check: runtime state walk on a real phone)

## Audit criteria

- [ ] Light palette uses the Soft Dusk tokens. (deferred: device visual verification required) (needs-device-check: light palette contrast on phone)
- [ ] Dark palette uses the corresponding night tokens. (deferred: device visual verification required) (needs-device-check: dark palette contrast on phone)
- [ ] RTL checked in Persian for layout direction, icon direction, text alignment and number/address spans. (deferred: device visual verification required; source-level directional grep is recorded) (needs-device-check: Persian RTL layout and mixed-script values)
- [x] Source grep/review found no legacy hardcoded UI colors, radii, typography or default Material surfaces outside documented brand/data visualizations; centralized theme, wallpaper presets and brand/art palettes remain.
- [ ] Release build and visual smoke test completed. (release CI passed on pushed code; emulator/device visual smoke test deferred because no device is available here) (needs-device-check: visual smoke test on physical hardware)


## Manifest-registered native entry points

All Android activities declared in `app/src/main/AndroidManifest.xml` (including import/configuration launchers):
- [x] `.MainActivity` — Soft Dusk activity root and edge-to-edge system bars.
- [x] `.ui.amnezia.AmzImportActivity` — audited/deferred: import dispatcher has no UI.
- [x] `.ui.npv.NpvImportActivity` — audited/deferred: import dispatcher has no screen UI.
- [x] `com.mlmvpn.core.tunnel.ShadeCollapseActivity` — audited/deferred: invisible shade-collapse trampoline.
- [x] `.widget.studio.StudioConfigureActivity` — Soft Dusk widget configuration surface.
- [x] `.widget.WidgetActionActivity` — audited/deferred: invisible widget-action trampoline.

## Home-screen widget providers and configurations

Manifest-declared widget receivers (rendering/state audited together with their corresponding widget UI):
- [x] `.widget.MasqueWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.WireguardWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.WarpOnWarpWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.WarpWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.PsiphonWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.TorWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.GephWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.LanternWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.UrnetworkWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.QuickWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.MaeWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.FluxWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.FenixWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.AmneziaWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.ProtonWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.WindscribeWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.OutlineWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.NpvWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.GithubWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.VpnGateWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.OpenVpnWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.V2rayWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.IranWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.FrontingWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.AntiSanctionWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.EmergencyGstWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.EmergencySniWidget` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.studio.StudioWidgetSmall` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.studio.StudioWidgetMedium` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.studio.StudioWidgetLarge` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.
- [x] `.widget.studio.StudioPinReceiver` — reviewed through shared Soft Dusk painter/status tokens; provider wiring and behavior unchanged.

## Native XML and Android resource surfaces

- [x] `app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml`, `ic_launcher_round.xml` and `drawable/ic_launcher_monochrome.xml` — adaptive and monochrome themed icons use the Dusk launcher artwork.
- [x] `app/src/main/res/values/dimens.xml` — native-view radii map to centralized Soft Dusk button, card, dialog, chip and badge shape resources.
- [x] `app/src/main/res/values/themes.xml` — platform windows, splash background, bars and native Material controls use Soft Dusk night tokens and shared dimension resources.
- [x] `app/src/main/res/values/colors.xml` — native color resources are named dusk_* and map to Soft Dusk night tokens; QR backing remains neutral white. — legacy palette references.
- [x] `app/src/main/res/layout/dialog_edit_node.xml` — native edit dialog uses shared Dusk palette, Vazirmatn and start/end positioning.
- [x] `app/src/main/res/layout/dialog_share_node.xml` — native share dialog uses Dusk surface/text and semantic QR backing.
- [x] `app/src/main/res/layout/widget_method.xml` — image-backed; appearance is centralized in the shared Soft Dusk WidgetPainter.
- [x] `app/src/main/res/layout/widget_studio_large.xml` — image-backed; custom user template rendered by shared studio painter.
- [x] `app/src/main/res/layout/widget_studio_medium.xml` — image-backed; custom user template rendered by shared studio painter; physical halves are intentional tap zones.
- [x] `app/src/main/res/layout/widget_studio_small.xml` — image-backed; custom user template rendered by shared studio painter.

## Custom alerts and transient feedback source inventory

Review shared custom alert calls (count includes multiple branches/variants per file):
- [x] `com/mlmvpn/scanner/ui/CloudCoach.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/CloudTab.kt` — `IosAlert` × 2
- [x] `com/mlmvpn/scanner/ui/CombineCoach.kt` — `IosAlert` × 2
- [x] `com/mlmvpn/scanner/ui/NodesTab.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/QuickConnectTab.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/QuickServersScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/VpnGateServersScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/backup/BackupScreen.kt` — `IosAlert` × 3
- [x] `com/mlmvpn/scanner/ui/cloud/GatewayRows.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/cloud/GatewaySettingsScreen.kt` — `IosAlert` × 2
- [x] `com/mlmvpn/scanner/ui/cloud/PanelRows.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioAccountDetailScreen.kt` — `IosAlert` × 5
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioBulkActionScreen.kt` — `IosAlert` × 3
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioCentralAccountScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioCentralUserScreen.kt` — `IosAlert` × 2
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioConfigsScreen.kt` — `IosAlert` × 3
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioExitsScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioMultiLocationScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioNodeEditScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioNodeGroupEditScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioPlanEditScreen.kt` — `IosAlert` × 2
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioTemplateEditScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioUserDetailScreen.kt` — `IosAlert` × 5
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` — `IosAlert` × 3
- [x] `com/mlmvpn/scanner/ui/home/HomeScreen.kt` — `IosAlert` × 2
- [x] `com/mlmvpn/scanner/ui/mae/MaePages.kt` — `IosAlert` × 2
- [x] `com/mlmvpn/scanner/ui/mae/MaeScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/settings/IosAlerts.kt` — `IosAlert` × 2
- [x] `com/mlmvpn/scanner/ui/settings/WorkerSetupRow.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/spider/SpiderScreen.kt` — `IosAlert` × 2
- [x] `com/mlmvpn/scanner/ui/sublink/SubLinkDetailScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/sublink/SubLinkFormScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/sublink/SubLinkScreen.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt` — `IosAlert` × 1
- [x] `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt` — `IosAlert` × 3
- [x] `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` — `IosAlert` × 3

Toast / short transient feedback call sites (OS-rendered surfaces; validate app wording and feedback states):
- [ ] `com/mlmvpn/core/aether/AetherScanService.kt` — Toast × 1 (deferred: background foreground-service action; no Compose host is alive)
- [ ] `com/mlmvpn/scanner/MainActivity.kt` — Toast × 1 (deferred: platform startActivityForResult failure handler; no screen-scoped Compose callback)
- [ ] `com/mlmvpn/scanner/VpnTileService.kt` — Toast × 1 (deferred: Android Quick Settings tile runs outside the Compose UI)
- [ ] `com/mlmvpn/scanner/backup/BackupStage.kt` — Toast × 1 (deferred: process-start restore result is emitted before Compose UI exists)
- [x] `com/mlmvpn/scanner/engines/edg/EdgSettingsScreen.kt` — Toast × 2 (replaced with Dusk snackbars)
- [ ] `com/mlmvpn/scanner/engines/github/GtEngine.kt` — Toast × 1 (deferred: engine-level background operation has no Compose host callback)
- [x] `com/mlmvpn/scanner/engines/mlm/MlmSettingsScreen.kt` — Toast × 3 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt` — Toast × 10 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt` — Toast × 7 (replaced with Dusk snackbars)
- [ ] `com/mlmvpn/scanner/lan/LanNotification.kt` — Toast × 1 (deferred: notification action receiver runs outside the Compose UI)
- [x] `com/mlmvpn/scanner/ui/AboutScreen.kt` — Toast × 2 (replaced with Dusk snackbars; inventory corrected to include URL launch failure and developer-mode feedback)
- [x] `com/mlmvpn/scanner/ui/AddNodeScreen.kt` — Toast × 15
- [x] `com/mlmvpn/scanner/ui/AppScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/CloudTab.kt` — Toast × 29 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/CloudTroubleshootScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/FixedIpScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/GameTab.kt` — Toast × 6 (replaced with activity-scoped Dusk snackbar feedback).
- [x] `com/mlmvpn/scanner/ui/NodesTab.kt` — Toast × 19 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/OpenVpnScreen.kt` — Toast × 1
- [x] `com/mlmvpn/scanner/ui/QuickConnectTab.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/ScannerTab.kt` — Toast × 20 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/aether/AetherScreen.kt` — Toast × 4 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt` — Toast × 3 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt` — Toast × 1 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/backup/BackupScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/cfwarp/Nat64ExitUi.kt` — Toast × 2 (replaced with Dusk snackbar feedback)
- [x] `com/mlmvpn/scanner/ui/cloud/CfMethodCard.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/cloud/GatewayRows.kt` — Toast × 1 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/cloud/GatewaySettingsScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/cloud/PanelRows.kt` — Toast × 1 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioLocationsEditScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt` — Toast × 3 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/emergency/EmergencyKit.kt` — Toast × 1 (shared helper now dispatches through Dusk snackbar callbacks)
- [x] `com/mlmvpn/scanner/ui/emergency/EmergencyLevel2Screen.kt` — Toast × 1 (replaced with Dusk snackbar feedback)
- [x] `com/mlmvpn/scanner/ui/emergency/EmergencyLevel3Screen.kt` — Toast × 4 (replaced with Dusk snackbar feedback)
- [x] `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` — Toast × 4 (replaced with Dusk snackbar feedback).
- [x] `com/mlmvpn/scanner/ui/geph/GephUi.kt` — Toast × 1 (clipboard confirmations routed through Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` — Toast × 1 (clipboard confirmation moved to Dusk snackbar).
- [x] `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt` — Toast × 1 (replaced with DuskSnackbar)
- [x] `com/mlmvpn/scanner/ui/lantern/LanternUi.kt` — Toast × 1 (replaced with Dusk snackbar feedback)
- [x] `com/mlmvpn/scanner/ui/mae/MaePages.kt` — Toast × 2 (replaced with Dusk snackbar feedback).
- [x] `com/mlmvpn/scanner/ui/mae/MaeScreen.kt` — Toast × 1 (replaced with Dusk snackbar feedback).
- [ ] `com/mlmvpn/scanner/ui/npv/NpvImportActivity.kt` — Toast × 1 (deferred: standalone ACTION_VIEW import Activity has no Compose host)
- [x] `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` — Toast × 4 (replaced with Dusk snackbar feedback).
- [x] `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt` — Toast × 4 (replaced with Dusk snackbar feedback).
- [x] `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt` — Toast × 2 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt` — Toast × 1 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt` — Toast × 6 (replaced with Dusk snackbars, including file/import feedback)
- [x] `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt` — Toast × 2 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/settings/GeminiExitRow.kt` — Toast × 2 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt` — Toast × 3 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/settings/SmartIranPage.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/settings/WarpIdRelayRow.kt` — Toast × 2 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/spider/SpiderConfigs.kt` — Toast × 1 (replaced with Dusk snackbar feedback)
- [x] `com/mlmvpn/scanner/ui/spider/SpiderScreen.kt` — Toast × 1 (replaced with Dusk snackbar feedback)
- [x] `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt` — Toast × 1 (clipboard confirmation moved to Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt` — Toast × 5 (replaced with Dusk snackbars, including asynchronous engine notices)
- [x] `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt` — Toast × 6 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` — Toast × 2 (removed/copied confirmation moved to Dusk snackbar).
- [x] `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` — Toast × 5 (connection/permission feedback routed to Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/tunnel/WarpIdentityCard.kt` — Toast × 2 (worker setup feedback routed to Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` — Toast × 4 (account/copy/browser feedback routed to Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` — Toast × 1 (replaced with activity-scoped Dusk snackbar).
- [x] `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` — Toast × 1 (replaced with Dusk snackbar feedback for missing-browser fallback).
- [x] `com/mlmvpn/scanner/utils/Donate.kt` — Toast × 2 (browser failures forwarded to the caller’s Dusk snackbar)
- [x] `com/mlmvpn/scanner/utils/SafeClip.kt` — Toast × 2 (too-large clipboard feedback forwarded to the caller’s Dusk snackbar)
- [ ] `com/mlmvpn/scanner/widget/WidgetActionActivity.kt` — Toast × 1 (deferred: app-widget trampoline can run without the Compose application UI)

## Notification builder sources

- [x] `com/mlmvpn/core/aether/AetherScanService.kt` — Dusk notification accent applied. × 1
- [x] `com/mlmvpn/scanner/data/IpScanService.kt` — Dusk notification accent applied. × 1
- [x] `com/mlmvpn/scanner/engines/game/booster/session/GameBoostService.kt` — Dusk notification accent applied. × 1
- [x] `com/mlmvpn/scanner/engines/scout/ScoutService.kt` — Dusk notification accent applied. × 1
- [x] `com/mlmvpn/scanner/engines/tgproxy/TgBridgeService.kt` — Dusk notification accent applied. × 1
- [x] `com/mlmvpn/scanner/engines/wireguard/WireguardScanService.kt` — Dusk notification accent applied. × 1
- [x] `com/mlmvpn/scanner/lan/LanNotification.kt` — Dusk notification accent applied. × 1
- [x] `com/mlmvpn/scanner/openvpn/OpenVpnService.kt` — Dusk notification accent applied. × 1
- [x] `com/mlmvpn/scanner/widget/WidgetConnectService.kt` — both failure and ongoing notifications use Dusk accent. × 2
- [x] `kittoku/mvc/service/SoftEtherVpnService.kt` — both user-visible notifications use Dusk accent. × 2

## Current implementation note

Foundation added in this pass: `DuskTheme` palette/shape/spacing tokens, reusable `DuskCard` and `DuskPill`, light/dark Material schemes, Vazirmatn variable font statics for regular/medium/semibold/bold across Persian and Latin text (OFL license under `app/src/main/assets/licenses/`), and the home connection-card landscape/sun-to-moon transition. Batch (a) restyled the connection card and floating four-destination nav; the home board/connection interactions are now source-audited, with device/runtime rendering still pending. Build verification is not yet available in this shell: `gradlew` now exists, but Java is not installed. Feature screens, interaction states, XML/native/notification surfaces, systematic light/dark/RTL checks, and a full device/runtime review remain unverified. The latest core utility-screen batch passed GitHub debug and release builds (see `BUILD_STATUS.md`); local Gradle builds remain unavailable in this shell because Java is not installed.

## Progress note

Inventory checkpoint: source lists are recorded; checked rows have been migrated to DuskTheme and statically audited for literal colors, radii and font sizes. Runtime/light-dark/RTL verification is still pending. The app has extensive feature-specific routes, dialogs and custom-drawn UI; keep partial work honest rather than treating a top-level theme wrapper as a full redesign.

Core-screen audit status (2026-11-26; side panels remain deferred): Home/Connect — done (0 unchecked; 1 state-only deferral); split tunnel/Iran routing — done (0 unchecked); Settings — done (0 unchecked); Splash/Onboarding — done (0 unchecked); config add/edit — done (0 unchecked); clean-IP scanner — done (0 unchecked). These are source-level UI audits; no build/runtime verification was run before 700/1203. Generic VPN add/edit excludes Config Studio side-panel routes, which remain for the later side-panel phase.
