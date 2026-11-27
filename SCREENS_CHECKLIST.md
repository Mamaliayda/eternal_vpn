# Eternal VPN — screen redesign checklist

> **Progress: 602 / 1203 complete** — checked rows are Soft Dusk-migrated or explicitly deferred with a reason; unverified routes and states remain unchecked.

> Inventory generated from Kotlin source on 2026-11-26. A row is complete only after its visible composition, nested states, and overlays are reviewed against the Soft Dusk tokens and migrated, or explicitly deferred with a reason. Shared theme inheritance alone does **not** count as a completed visual redesign.

## Shared foundation

- [x] Soft Dusk light/night palette and contrast review (2026-11-26: audited theme text/surface and semantic pairs; darkened light muted/secondary text for WCAG AA on blush/cream and changed light tertiary text to navy for coral contrast).
- [x] Central shapes, typography, spacing and reusable components.
- [x] Material light/dark surface mapping and Android system bars.
- [x] Vazirmatn Persian/Latin font weights and mixed-script rendering (core source audit: all four bundled weights contain Latin, Arabic/Persian and digit glyphs with GSUB shaping; Vazirmatn is applied to the full Material type scale; human counts use localized Persian digits where intended while IPs, ports and protocol values stay LTR; reviewed line-height overrides/default type metrics, alignment, and FSI/PDI punctuation isolates. Runtime screenshot remains gated by the 700 checklist build threshold).
- [ ] RTL behavior of shared cards, pills, icons and navigation.

## Main destination registry

All launcher destinations registered in `HomeDestinations` (the app board entry points):
- [ ] `amnezia`
- [ ] `antisanction`
- [ ] `cloud`
- [ ] `configstudio`
- [ ] `emergency_2`
- [ ] `emergency_3`
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
- [ ] `outline`
- [ ] `proton`
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

- [ ] `CombineOfferDialog` — `com/mlmvpn/scanner/ui/CombineCoach.kt`
- [ ] `CombineDoneDialog` — `com/mlmvpn/scanner/ui/CombineCoach.kt`
### FixedIpScreen.kt

- [x] `FixedIpScreen` — `com/mlmvpn/scanner/ui/FixedIpScreen.kt`
### FreeConfigTab.kt

- [x] `FreeConfigTab` — `com/mlmvpn/scanner/ui/FreeConfigTab.kt`
### FreeConfigWizard.kt

- [x] `FreeConfigWizard` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
### GameTab.kt

- [ ] `GameTab` — `com/mlmvpn/scanner/ui/GameTab.kt`
- [ ] `GamePickerPage` — `com/mlmvpn/scanner/ui/GameTab.kt`
- [ ] `RegionPickerPage` — `com/mlmvpn/scanner/ui/GameTab.kt`
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

- [ ] `BackupScreen` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
- [ ] `HomePage` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
- [ ] `PreviewPage` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
### cfwarp

- [ ] `CfWarpSettingsScreen` — `com/mlmvpn/scanner/ui/cfwarp/CfWarpUi.kt`
- [ ] `Nat64ExitScreen` — `com/mlmvpn/scanner/ui/cfwarp/Nat64ExitUi.kt`
### cloud

- [x] `GatewaySettingsScreen` — `com/mlmvpn/scanner/ui/cloud/GatewaySettingsScreen.kt`
### configstudio

- [ ] `ConfigStudioHost` — `com/mlmvpn/scanner/ui/configstudio/ConfigStudioHost.kt`
- [ ] `StudioAccountDetailScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountDetailScreen.kt`
- [x] `StudioAccountsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountsScreen.kt`
- [ ] `StudioAnalyticsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [ ] `StudioAuditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAuditScreen.kt`
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
- [ ] `StudioMoreScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioMoreScreen.kt`
- [ ] `StudioMultiLocationScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioMultiLocationScreen.kt`
- [x] `StudioNewUserScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNewUserScreen.kt`
- [x] `StudioNodeEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeEditScreen.kt`
- [x] `StudioNodeGroupEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeGroupEditScreen.kt`
- [x] `StudioNodeGroupsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeGroupsScreen.kt`
- [x] `StudioNodesScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodesScreen.kt`
- [ ] `StudioPlanEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioPlanEditScreen.kt`
- [ ] `StudioPlansScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioPlansScreen.kt`
- [x] `StudioSettingsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioSettingsScreen.kt`
- [ ] `StudioTemplateEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplateEditScreen.kt`
- [ ] `StudioTemplatesScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplatesScreen.kt`
- [ ] `StudioUserDetailScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioUserDetailScreen.kt`
- [ ] `StudioUsersScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [ ] `ProtocolPicker` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `LocationPicker` — `com/mlmvpn/scanner/ui/configstudio/parts/LocationPicker.kt`
- [ ] `PortPicker` — `com/mlmvpn/scanner/ui/configstudio/parts/PortPicker.kt`
- [ ] `StudioSetupWizard` — `com/mlmvpn/scanner/ui/configstudio/wizard/StudioSetupWizard.kt`
### doctor

- [x] `CfDoctorScreen` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `AccountPage` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `ReportPage` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `CloudDoctorScreen` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `StepPage` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
### emergency

- [ ] `EmergencyLevel2Screen` — `com/mlmvpn/scanner/ui/emergency/EmergencyLevel2Screen.kt`
- [ ] `EmergencyLevel3Screen` — `com/mlmvpn/scanner/ui/emergency/EmergencyLevel3Screen.kt`
- [ ] `GstRelayScreen` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt`
- [ ] `GstNetworkPathScreen` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt`
- [x] `GstLogScreen` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt`
- [ ] `GstSetupWizard` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt`
- [ ] `SniConfigPickerPage` — `com/mlmvpn/scanner/ui/emergency/SniConfigPages.kt`
- [ ] `SniBuildPage` — `com/mlmvpn/scanner/ui/emergency/SniConfigPages.kt`
### fenix

- [ ] `FenixScreen` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `AccountsPage` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `MozillaSignInPage` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `MozillaCheckPage` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `FenixCountryPicker` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
### flux

- [ ] `FluxScreen` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt`
- [ ] `CountryPicker` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt`
- [ ] `FluxDiagnosticsScreen` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt`
### game

- [ ] `GameBoosterHost` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `GameBoostScreen` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `HelpPage` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `GamePickerPage` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `RegionPickerPage` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `AddGamePage` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `AdvancedPage` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
### geph

- [ ] `GephAccountScreen` — `com/mlmvpn/scanner/ui/geph/GephAccountScreen.kt`
- [ ] `GephExitScreen` — `com/mlmvpn/scanner/ui/geph/GephExitScreen.kt`
- [ ] `GephNewsScreen` — `com/mlmvpn/scanner/ui/geph/GephInfoScreens.kt`
- [ ] `GephSessionsScreen` — `com/mlmvpn/scanner/ui/geph/GephInfoScreens.kt`
- [ ] `GephSettingsScreen` — `com/mlmvpn/scanner/ui/geph/GephSettingsScreen.kt`
### github

- [ ] `GithubTunnelScreen` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
- [ ] `ExitCountryPage` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
- [ ] `RuleEditorPage` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
### home

- [x] `IranConfigsScreen` — `com/mlmvpn/scanner/ui/home/BuiltInProfilesScreen.kt`
- [x] `DomainFrontingScreen` — `com/mlmvpn/scanner/ui/home/BuiltInProfilesScreen.kt`
- [x] `BuiltInProfileScreen` — `com/mlmvpn/scanner/ui/home/BuiltInProfilesScreen.kt`
- [x] `HomeScreen` — `com/mlmvpn/scanner/ui/home/HomeScreen.kt`
- [x] `IosModalHost` — `com/mlmvpn/scanner/ui/home/ModalHost.kt`
### lan

- [ ] `LanGuideScreen` — `com/mlmvpn/scanner/ui/lan/LanGuideScreens.kt`
- [ ] `LanScreen` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
### mae

- [ ] `MaeManageScreen` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [ ] `MaeDiagnosticsScreen` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [ ] `InstalledAppPicker` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [ ] `MaeScreen` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt`
- [x] `SymptomDialog` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt`
### npv

- [ ] `NpvScreen` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt`
- [ ] `MainPage` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt`
- [ ] `NpvSourcesPage` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt`
### outline

- [x] `OutlineScreen` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [x] `SourcesPage` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [x] `OutlineServersPage` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
### proton

- [x] `ProtonScreen` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `ProtonWebPage` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
### sanction

- [ ] `AntiSanctionScreen` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt`
- [ ] `SanctionDomainsScreen` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt`
- [ ] `SanctionAppsScreen` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt`
### scout

- [ ] `ScoutScreen` — `com/mlmvpn/scanner/ui/scout/ScoutScreen.kt`
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
- [x] `SettingsScreen` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `DisplayPage` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `WallpaperPage` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `PresetPickerPage` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `SmartIranPage` — `com/mlmvpn/scanner/ui/settings/SmartIranPage.kt`
- [x] `UsageScreen` — `com/mlmvpn/scanner/ui/settings/UsageScreen.kt`
- [x] `VpnSettingsScreen` — `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt`
- [x] `AppPickerPage` — `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt`
### spider

- [ ] `SpiderScreen` — `com/mlmvpn/scanner/ui/spider/SpiderScreen.kt`
- [ ] `NewUserPage` — `com/mlmvpn/scanner/ui/spider/SpiderScreen.kt`
- [ ] `NewExitPage` — `com/mlmvpn/scanner/ui/spider/SpiderScreen.kt`
### store

- [ ] `StoreScreen` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `TodayPage` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `AppsPage` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `UpdatesPage` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `ProductPage` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
### sublink

- [ ] `SubLinkDetailScreen` — `com/mlmvpn/scanner/ui/sublink/SubLinkDetailScreen.kt`
- [x] `SubLinkHelpScreen` — `com/mlmvpn/scanner/ui/sublink/SubLinkDetailScreen.kt`
- [ ] `SubLinkFormScreen` — `com/mlmvpn/scanner/ui/sublink/SubLinkFormScreen.kt`
- [x] `SubLinkGroupScreen` — `com/mlmvpn/scanner/ui/sublink/SubLinkFormScreen.kt`
- [ ] `SubLinkScreen` — `com/mlmvpn/scanner/ui/sublink/SubLinkScreen.kt`
### tgproxy

- [ ] `TgDomainPage` — `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt`
- [ ] `TgProxyScreen` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt`
- [ ] `TgMainPage` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt`
### tunnel

- [x] `PsiphonMethodScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `PsiphonProxyScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `PsiphonCleanIpScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt` (Dusk semantic colors, shared compact pill and snackbar feedback)
- [x] `PsiphonPairingScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `RegionPickerScreen` — `com/mlmvpn/scanner/ui/tunnel/RegionPickerScreen.kt`
- [ ] `TorBridgesScreen` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `BridgeListPage` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `BridgeDetailPage` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `AddBridgesPage` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `TorModeScreen` — `com/mlmvpn/scanner/ui/tunnel/TorModeScreen.kt`
- [ ] `TransportAdvancedScreen` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt`
- [ ] `NumberPage` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt`
- [ ] `SecretPage` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt`
- [ ] `TransportHost` — `com/mlmvpn/scanner/ui/tunnel/TransportHost.kt`
- [ ] `TransportScreen` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt`
- [x] `TunnelLogScreen` — `com/mlmvpn/scanner/ui/tunnel/TunnelLogScreen.kt`
### urnetwork

- [ ] `UrnetworkLocationsScreen` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
- [ ] `UrnetworkRestoreScreen` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
### widgetstudio

- [ ] `WidgetStudioScreen` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `LookTab` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `ColorTab` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `InfoTab` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `SizeTab` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `MethodPicker` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `CountryPicker` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
### windscribe

- [ ] `WindscribeScreen` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [x] `LoginDialog` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [x] `CaptchaDialog` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [x] `EmailDialog` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
### com

- [ ] `ConfigureScreen` — `com/mlmvpn/scanner/widget/studio/StudioConfigureActivity.kt`

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

- [ ] `pingColor` — `com/mlmvpn/scanner/ui/GameTab.kt`
- [ ] `GameCard` — `com/mlmvpn/scanner/ui/GameTab.kt`
- [ ] `PickRow` — `com/mlmvpn/scanner/ui/GameTab.kt`
- [ ] `section` — `com/mlmvpn/scanner/ui/GameTab.kt`
- [ ] `BoostButton` — `com/mlmvpn/scanner/ui/GameTab.kt`
- [ ] `ResultCard` — `com/mlmvpn/scanner/ui/GameTab.kt`
- [ ] `LivePingCard` — `com/mlmvpn/scanner/ui/GameTab.kt`
- [ ] `DedicatedDnsCard` — `com/mlmvpn/scanner/ui/GameTab.kt`
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
- [x] `statusLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt` (deferred: localized string formatter has no independent visual surface; rendered within the Dusk-styled HeroCard)
- [x] `stepLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt` (deferred: localized string formatter has no independent visual surface; rendered within the Dusk-styled HeroCard)
- [x] `failLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt` (deferred: localized string formatter has no independent visual surface; rendered within the Dusk-styled HeroCard)
- [x] `subLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [x] `LocationRow` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `groupName` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
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
- [ ] `TeamTile` — `com/mlmvpn/scanner/ui/arena/ArenaTeams.kt`
### backup

- [ ] `com` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
- [ ] `ModeRow` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
- [ ] `WarningFooter` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
### cfwarp

- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/cfwarp/CfWarpUi.kt`
- [ ] `ExitWaySwitch` — `com/mlmvpn/scanner/ui/cfwarp/Ipv4ExitUi.kt`
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/cfwarp/Ipv4ExitUi.kt`
- [ ] `ExitWayRow` — `com/mlmvpn/scanner/ui/cfwarp/Ipv4ExitUi.kt`
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/cfwarp/Nat64ExitUi.kt`
- [ ] `ExitRow` — `com/mlmvpn/scanner/ui/cfwarp/Nat64ExitUi.kt`
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
- [ ] `shardSubtitle` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountsScreen.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [ ] `AuditRow` — `com/mlmvpn/scanner/ui/configstudio/StudioAuditScreen.kt`
- [ ] `actionLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioAuditScreen.kt`
- [x] `JobRow` — `com/mlmvpn/scanner/ui/configstudio/StudioBulkAddScreen.kt`
- [ ] `roomLine` — `com/mlmvpn/scanner/ui/configstudio/StudioCentralNewUserScreen.kt`
- [ ] `StudioCombineHandover` — `com/mlmvpn/scanner/ui/configstudio/StudioCombineHandover.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioConfigBuilderScreen.kt`
- [x] `ConfigRow` — `com/mlmvpn/scanner/ui/configstudio/StudioConfigsScreen.kt`
- [ ] `expiresInText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `timeLeftText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `countOrDash` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `activityLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `whenText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `bytesFa` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `freshnessText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [x] `ExitRow` — `com/mlmvpn/scanner/ui/configstudio/StudioExitsScreen.kt`
- [ ] `CountryTitle` — `com/mlmvpn/scanner/ui/configstudio/StudioMultiLocationScreen.kt`
- [x] `ResultView` — `com/mlmvpn/scanner/ui/configstudio/StudioNewUserScreen.kt`
- [ ] `shardPlacementNote` — `com/mlmvpn/scanner/ui/configstudio/StudioNewUserScreen.kt`
- [ ] `probeAge` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeEditScreen.kt`
- [ ] `strategyLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeGroupsScreen.kt`
- [ ] `strategyNote` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeGroupsScreen.kt`
- [x] `NodeRow` — `com/mlmvpn/scanner/ui/configstudio/StudioNodesScreen.kt`
- [ ] `healthLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioNodesScreen.kt`
- [ ] `agoText` — `com/mlmvpn/scanner/ui/configstudio/StudioNodesScreen.kt`
- [ ] `termsOf` — `com/mlmvpn/scanner/ui/configstudio/StudioPlansScreen.kt`
- [ ] `driftLine` — `com/mlmvpn/scanner/ui/configstudio/StudioPlansScreen.kt`
- [ ] `presetName` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplateEditScreen.kt`
- [ ] `presetNote` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplateEditScreen.kt`
- [ ] `shapeOf` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplatesScreen.kt`
- [ ] `driftLine` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplatesScreen.kt`
- [ ] `DeviceRow` — `com/mlmvpn/scanner/ui/configstudio/StudioUserDetailScreen.kt`
- [ ] `deviceSubtitle` — `com/mlmvpn/scanner/ui/configstudio/StudioUserDetailScreen.kt`
- [ ] `sessionLength` — `com/mlmvpn/scanner/ui/configstudio/StudioUserDetailScreen.kt`
- [ ] `BarIcon` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [ ] `filterLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [ ] `sortLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [ ] `UserRow` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [ ] `subtitleFor` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [ ] `UserKindBadge` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [ ] `StudioTabBar` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `StudioBadge` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `CountryBadge` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `StatusLine` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `StudioStatTile` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `StudioTilePair` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `NoticeCard` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [x] `EmptyState` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `ChoiceRow` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `StudioSeparator` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `SegmentedControl` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `statusText` — `com/mlmvpn/scanner/ui/configstudio/parts/LocationPicker.kt`
- [ ] `rememberCountryChoices` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioCreateParts.kt`
- [ ] `CreatedConfigsCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioCreateParts.kt`
- [ ] `LinkCountLine` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioCreateParts.kt`
- [ ] `StepDots` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `StepHeader` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `WizardField` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `WizardPrimary` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `InfoCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `ProblemCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `PayloadText` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `MeterBar` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `FactsCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `PayloadCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `StudioChip` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `StudioListRow` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `StatusDot` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `PageIntro` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `StudioTile` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `StudioTileRow` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioKit.kt`
- [ ] `StudioQrCard` — `com/mlmvpn/scanner/ui/configstudio/parts/StudioShare.kt`
- [ ] `VolumeField` — `com/mlmvpn/scanner/ui/configstudio/parts/VolumeField.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/wizard/StudioSetupWizard.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/wizard/StudioSetupWizard.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/wizard/StudioSetupWizard.kt`
### doctor

- [x] `DoctorMain` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `Hero` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `Capsule` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `StageRow` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `layerText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt` (deferred: localized text formatter has no independent visual surface; rendered in the Dusk-styled Doctor screen)
- [x] `TextRow` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `stepText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt` (deferred: localized text formatter has no independent visual surface; rendered in the Dusk-styled Doctor screen)
- [x] `findingText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt` (deferred: localized text formatter has no independent visual surface; rendered in the Dusk-styled Doctor screen)
- [x] `worksText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt` (deferred: localized text formatter has no independent visual surface; rendered in the Dusk-styled Doctor screen)
- [x] `Field` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [x] `Step` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `HeroFrame` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [x] `HeroTitle` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
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

- [ ] `dialAccent` — `com/mlmvpn/scanner/ui/emergency/EmergencyKit.kt`
- [ ] `EmergencyDial` — `com/mlmvpn/scanner/ui/emergency/EmergencyKit.kt`
- [ ] `EmergencyStatusLine` — `com/mlmvpn/scanner/ui/emergency/EmergencyKit.kt`
- [ ] `pingTone` — `com/mlmvpn/scanner/ui/emergency/EmergencyKit.kt`
- [ ] `RouteRow` — `com/mlmvpn/scanner/ui/emergency/EmergencyLevel3Screen.kt`
- [ ] `pingLabel` — `com/mlmvpn/scanner/ui/emergency/EmergencyLevel3Screen.kt`
- [ ] `relayTone` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt`
- [ ] `PathRow` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt`
- [ ] `LogChip` — `com/mlmvpn/scanner/ui/emergency/GstScreens.kt`
- [ ] `StepDots` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt`
- [ ] `StepHeader` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt`
- [ ] `WizardField` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt`
- [ ] `WizardPrimary` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt`
- [ ] `InfoCard` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt`
- [ ] `StepPassword` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt`
- [ ] `StepConfirmPassword` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt`
- [ ] `StepScript` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt`
- [ ] `StepDeploymentId` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt`
- [ ] `StepDone` — `com/mlmvpn/scanner/ui/emergency/GstSetupWizard.kt`
- [ ] `SwipeToDeleteRow` — `com/mlmvpn/scanner/ui/emergency/SniConfigPages.kt`
- [ ] `Bin` — `com/mlmvpn/scanner/ui/emergency/SniConfigPages.kt`
- [ ] `SniConfigRow` — `com/mlmvpn/scanner/ui/emergency/SniConfigPages.kt`
### fenix

- [x] `Welcome` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `Ready` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `AccountCard` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `resetLine` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `agoText` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [x] `PrimaryButton` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [x] `ErrorText` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `ConnectButton` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `statusText` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `failureText` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `countryValue` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
- [ ] `CountryRow` — `com/mlmvpn/scanner/ui/fenix/FenixScreen.kt`
### flux

- [ ] `FluxMain` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt`
- [ ] `statusText` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt`
- [ ] `IpSelector` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt`
- [ ] `ConnectButton` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt`
- [ ] `AddSubscription` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt`
- [ ] `Mono` — `com/mlmvpn/scanner/ui/flux/FluxScreen.kt`
### game

- [ ] `GameIcon` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `GameRow` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `ValueRow` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `Chevron` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `regionLabel` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `StageList` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `stageTitle` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `evidence` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `ResultCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `StatsRow` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `verdictTitle` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `verdictDetail` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `kindLine` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `classLineText` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `symptomDone` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `causeText` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `LastSessionCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `FeedbackCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `RegionCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `ActionsRow` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `TipsCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `LiveCard` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `Sparkline` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
- [ ] `CheckRow` — `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt`
### geph

- [ ] `NoAccount` — `com/mlmvpn/scanner/ui/geph/GephAccountScreen.kt`
- [ ] `HasAccount` — `com/mlmvpn/scanner/ui/geph/GephAccountScreen.kt`
- [ ] `gephAgo` — `com/mlmvpn/scanner/ui/geph/GephExitScreen.kt`
- [ ] `TrafficBars` — `com/mlmvpn/scanner/ui/geph/GephInfoScreens.kt`
- [ ] `exitLabel` — `com/mlmvpn/scanner/ui/geph/GephUi.kt`
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/geph/GephUi.kt`
- [ ] `GephLiveCard` — `com/mlmvpn/scanner/ui/geph/GephUi.kt`
### github

- [ ] `Dial` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
- [ ] `Notice` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
- [ ] `SignInCard` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
- [ ] `SetupStep` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
- [ ] `StepRow` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
- [ ] `LogBlock` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
- [ ] `SitesField` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
- [ ] `SelectedAppRow` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
- [ ] `countryName` — `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt`
### home

- [x] `ids` — `com/mlmvpn/scanner/ui/home/ActiveEngines.kt` (deferred: state-only engine ownership mapping; behavior intentionally preserved under UI-layer-only scope)
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

- [ ] `LanDiagram` — `com/mlmvpn/scanner/ui/lan/LanDiagram.kt`
- [ ] `Hop` — `com/mlmvpn/scanner/ui/lan/LanDiagram.kt`
- [ ] `Link` — `com/mlmvpn/scanner/ui/lan/LanDiagram.kt`
- [ ] `GuideListSection` — `com/mlmvpn/scanner/ui/lan/LanGuideScreens.kt`
- [ ] `GuideValue` — `com/mlmvpn/scanner/ui/lan/LanGuideScreens.kt`
- [ ] `LanStateCard` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `StateGlyph` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `ActionButton` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `ShareTimerCard` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `DoctorSection` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `DoctorRow` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `HotspotCard` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `LabelledValue` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `SetupCard` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `SmallAction` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `AddressCard` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `CopyRow` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `SecurityBar` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `ClientsSection` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `headline` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
- [ ] `detail` — `com/mlmvpn/scanner/ui/lan/LanScreen.kt`
### lantern

- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/lantern/LanternUi.kt`
- [ ] `LanternLiveCard` — `com/mlmvpn/scanner/ui/lantern/LanternUi.kt`
- [ ] `ModeRow` — `com/mlmvpn/scanner/ui/lantern/LanternUi.kt`
### mae

- [ ] `MaeAppIcon` — `com/mlmvpn/scanner/ui/mae/MaeAppIcon.kt`
- [ ] `MaeCloudGate` — `com/mlmvpn/scanner/ui/mae/MaeCloudGate.kt`
- [ ] `rememberInstalledRegistryIds` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [x] `MaeOnboarding` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [ ] `MaeConfirmRemove` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [ ] `ForeignExitsList` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [ ] `ManageAppRow` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [ ] `DestructiveRow` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [ ] `MaeAppDetail` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [ ] `CheckRow` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [ ] `SetupRow` — `com/mlmvpn/scanner/ui/mae/MaePages.kt`
- [ ] `MaeContent` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt`
- [ ] `SwipeActionsRow` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt`
- [ ] `SwipeAction` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt`
- [ ] `ConnectButton` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt`
- [ ] `noteText` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt`
- [ ] `ServiceRow` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt`
- [x] `ServiceChip` — `com/mlmvpn/scanner/ui/mae/MaeScreen.kt`
### mitm

- [ ] `MitmSetupCard` — `com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt`
- [ ] `StepRow` — `com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt`
- [ ] `ActionButton` — `com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt`
- [ ] `BrandPath` — `com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt`
- [ ] `InfoLine` — `com/mlmvpn/scanner/ui/mitm/MitmSetupCard.kt`
### npv

- [ ] `RefreshQuestions` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt`
- [ ] `HeroCard` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt`
- [ ] `Orb` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt`
- [ ] `Stat` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt`
- [ ] `MeasureRow` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt`
- [ ] `ConfigRow` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt`
- [ ] `NativeRow` — `com/mlmvpn/scanner/ui/npv/NpvScreen.kt`
- [ ] `RefreshCard` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt`
- [ ] `Steps` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt`
- [ ] `StepLine` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt`
- [ ] `AddChannel` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt`
- [ ] `ChannelRow` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt`
- [ ] `FileRow` — `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt`
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

- [ ] `rememberVpnGate` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `ProtonHome` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `PingProgress` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `ServerRow` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `statusText` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `PrimaryButton` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `Message` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `helperName` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `HelperProgress` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [x] `HelperChooser` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
### sanction

- [ ] `AppRow` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt`
- [ ] `ExitChoiceRow` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt`
- [ ] `SanctionButton` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt`
### scout

- [ ] `statusColor` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `methodTitle` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `ScoutLogo` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `ProgressRing` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `StatusPill` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `MethodIcon` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `MethodRow` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `MethodCard` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `FoldSection` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `SectionTitle` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `LineCard` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `LineChip` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `Stat` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `PillButton` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
- [ ] `Dot` — `com/mlmvpn/scanner/ui/scout/ScoutParts.kt`
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

- [ ] `SpiderCloudRows` — `com/mlmvpn/scanner/ui/spider/SpiderConfigs.kt`
- [ ] `SpiderRow` — `com/mlmvpn/scanner/ui/spider/SpiderConfigs.kt`
### store

- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `DeploymentRow` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `AppRow` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `ActionButton` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `Pill` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `ProgressRing` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `JobLine` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `FeatureCard` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `AllCurrentCard` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `StatsCard` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `Stat` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `HistoryRow` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `InfoStrip` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `Fact` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `InfoRow` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `StoreTabBar` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `TabItem` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
- [ ] `StoreIconTile` — `com/mlmvpn/scanner/ui/store/StoreScreen.kt`
### sublink

- [x] `HelpRow` — `com/mlmvpn/scanner/ui/sublink/SubLinkDetailScreen.kt`
- [x] `GroupRow` — `com/mlmvpn/scanner/ui/sublink/SubLinkFormScreen.kt`
- [x] `SubLinkRow` — `com/mlmvpn/scanner/ui/sublink/SubLinkScreen.kt`
- [x] `StateBlock` — `com/mlmvpn/scanner/ui/sublink/SubLinkScreen.kt`
- [x] `expiryLabel` — `com/mlmvpn/scanner/ui/sublink/SubLinkScreen.kt`
### tgproxy

- [ ] `Step` — `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt`
- [ ] `Body` — `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt`
- [ ] `Pill` — `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt`
- [ ] `ProgressCard` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt`
- [ ] `ServiceCard` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt`
- [ ] `SmallButton` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt`
- [ ] `TgCloudGate` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt`
- [ ] `bridgeLine` — `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt`
### theme

- [ ] `iosButtonColors` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt`
- [ ] `iosButtonBorder` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt`
- [ ] `iosNeutralButtonColors` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt`
- [ ] `iosNeutralBorder` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt`
- [ ] `iosFieldColors` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt`
- [ ] `Modifier` — `com/mlmvpn/scanner/ui/theme/IosButtons.kt`
- [x] `DuskCard` — `com/mlmvpn/scanner/ui/theme/SoftDusk.kt`
- [x] `DuskPill` — `com/mlmvpn/scanner/ui/theme/SoftDusk.kt`
- [ ] `duskColorScheme` — `com/mlmvpn/scanner/ui/theme/SoftDusk.kt`
- [ ] `MlmVpnTheme` — `com/mlmvpn/scanner/ui/theme/Theme.kt`
### tunnel

- [ ] `withFlag` — `com/mlmvpn/scanner/ui/tunnel/CountryLabel.kt`
- [ ] `localized` — `com/mlmvpn/scanner/ui/tunnel/CountryLabel.kt`
- [ ] `LadderHandOverCard` — `com/mlmvpn/scanner/ui/tunnel/LadderHandOverCard.kt`
- [x] `ColumnScope` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `PsiphonLiveCard` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `MethodRow` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `IpRow` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
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
- [x] `CountryRow` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `RegionRow` — `com/mlmvpn/scanner/ui/tunnel/RegionPickerScreen.kt`
- [ ] `torBridgesValue` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `sortLabel` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `ChoiceRow` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `Chip` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `Note` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `BridgeRow` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `Tag` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `statusLine` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `statusColor` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `badReason` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `ago` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `TorEntryCard` — `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt`
- [ ] `Footer` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt`
- [ ] `ManualShapingGroup` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt`
- [ ] `ManualEndpointGroup` — `com/mlmvpn/scanner/ui/tunnel/TransportAdvancedScreen.kt`
- [ ] `ExitCountryCard` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt`
- [ ] `Footer` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt`
- [ ] `ConnectDial` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt`
- [ ] `DialCore` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt`
- [ ] `StatusLine` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt`
- [ ] `LiveStats` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt`
- [ ] `Metric` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt`
- [ ] `Divider` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt`
- [ ] `regionLabel` — `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt`
- [x] `LogAction` — `com/mlmvpn/scanner/ui/tunnel/TunnelLogScreen.kt`
- [ ] `IdentityBlockedCard` — `com/mlmvpn/scanner/ui/tunnel/WarpIdentityCard.kt`
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/tunnel/WarpIdentityCard.kt`
### urnetwork

- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
- [ ] `UrnetworkLiveCard` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
- [ ] `StepRow` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
- [ ] `WindowDetail` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
- [ ] `UrnetworkUsageCard` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
- [ ] `UsageBar` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
- [ ] `Metric` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
- [ ] `LocationRow` — `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt`
### widgetstudio

- [ ] `TemplatePreview` — `com/mlmvpn/scanner/ui/widgetstudio/StudioParts.kt`
- [ ] `PreviewStateChips` — `com/mlmvpn/scanner/ui/widgetstudio/StudioParts.kt`
- [ ] `PreviewStage` — `com/mlmvpn/scanner/ui/widgetstudio/StudioParts.kt`
- [ ] `PresetCard` — `com/mlmvpn/scanner/ui/widgetstudio/StudioParts.kt`
- [ ] `Swatch` — `com/mlmvpn/scanner/ui/widgetstudio/StudioParts.kt`
- [ ] `StudioMain` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `PrimaryButton` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `com` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `MethodStylePreview` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `StudioEditor` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `SwatchRow` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `HueSlider` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `CountryRow` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
- [ ] `AddMethodWidget` — `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt`
### windscribe

- [ ] `Action` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [ ] `ConnectButton` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [ ] `accountSummary` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [ ] `protocolName` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [ ] `methodName` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [ ] `connectionLabel` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [ ] `TestProgress` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [ ] `resultLine` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [ ] `measurementLabel` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
- [x] `CaptchaArt` — `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt`
### com

- [ ] `LocaleProvider` — `com/mlmvpn/scanner/utils/AppLocaleManager.kt`
- [ ] `Heading` — `com/mlmvpn/scanner/widget/studio/StudioConfigureActivity.kt`

## Android activities and fragments

No Android `Fragment` subclasses were found in the Java/Kotlin sources; the UI is Compose-based. The six activity classes:

- [ ] `ShadeCollapseActivity` — `com/mlmvpn/core/tunnel/ShadeCollapseActivity.kt`
- [ ] `MainActivity` — `com/mlmvpn/scanner/MainActivity.kt`
- [ ] `AmzImportActivity` — `com/mlmvpn/scanner/ui/amnezia/AmzImportActivity.kt`
- [ ] `NpvImportActivity` — `com/mlmvpn/scanner/ui/npv/NpvImportActivity.kt`
- [ ] `WidgetActionActivity` — `com/mlmvpn/scanner/widget/WidgetActionActivity.kt`
- [ ] `StudioConfigureActivity` — `com/mlmvpn/scanner/widget/studio/StudioConfigureActivity.kt`

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

- [ ] Android system VPN permission / app-permission prompts (OS-owned; entry points originate from connect actions).
- [ ] Runtime notifications, ongoing VPN foreground notification, channels and quick-settings tiles.
- [ ] Home-screen widgets and widget configuration/preview states.
- [ ] Native XML dialogs/layouts and standalone import/configuration activities.
- [ ] Loading, empty, offline, connected, connecting, failure, retry and permission-denied states across every destination.

## Audit criteria

- [ ] Light palette uses the Soft Dusk tokens.
- [ ] Dark palette uses the corresponding night tokens.
- [ ] RTL checked in Persian for layout direction, icon direction, text alignment and number/address spans.
- [ ] No legacy hardcoded UI colors, radii, typography or default Material surfaces remain outside documented brand/data visualizations.
- [ ] Release build and visual smoke test completed.


## Manifest-registered native entry points

All Android activities declared in `app/src/main/AndroidManifest.xml` (including import/configuration launchers):
- [ ] `.MainActivity`
- [ ] `.ui.amnezia.AmzImportActivity`
- [ ] `.ui.npv.NpvImportActivity`
- [ ] `com.mlmvpn.core.tunnel.ShadeCollapseActivity`
- [ ] `.widget.studio.StudioConfigureActivity`
- [ ] `.widget.WidgetActionActivity`

## Home-screen widget providers and configurations

Manifest-declared widget receivers (rendering/state audited together with their corresponding widget UI):
- [ ] `.widget.MasqueWidget`
- [ ] `.widget.WireguardWidget`
- [ ] `.widget.WarpOnWarpWidget`
- [ ] `.widget.WarpWidget`
- [ ] `.widget.PsiphonWidget`
- [ ] `.widget.TorWidget`
- [ ] `.widget.GephWidget`
- [ ] `.widget.LanternWidget`
- [ ] `.widget.UrnetworkWidget`
- [ ] `.widget.QuickWidget`
- [ ] `.widget.MaeWidget`
- [ ] `.widget.FluxWidget`
- [ ] `.widget.FenixWidget`
- [ ] `.widget.AmneziaWidget`
- [ ] `.widget.ProtonWidget`
- [ ] `.widget.WindscribeWidget`
- [ ] `.widget.OutlineWidget`
- [ ] `.widget.NpvWidget`
- [ ] `.widget.GithubWidget`
- [ ] `.widget.VpnGateWidget`
- [ ] `.widget.OpenVpnWidget`
- [ ] `.widget.V2rayWidget`
- [ ] `.widget.IranWidget`
- [ ] `.widget.FrontingWidget`
- [ ] `.widget.AntiSanctionWidget`
- [ ] `.widget.EmergencyGstWidget`
- [ ] `.widget.EmergencySniWidget`
- [ ] `.widget.studio.StudioWidgetSmall`
- [ ] `.widget.studio.StudioWidgetMedium`
- [ ] `.widget.studio.StudioWidgetLarge`
- [ ] `.widget.studio.StudioPinReceiver`

## Native XML and Android resource surfaces

- [ ] `app/src/main/res/values/themes.xml` — platform theme, system dialogs and bars.
- [ ] `app/src/main/res/values/colors.xml` — legacy palette references.
- [ ] `app/src/main/res/layout/dialog_edit_node.xml`
- [ ] `app/src/main/res/layout/dialog_share_node.xml`
- [ ] `app/src/main/res/layout/widget_method.xml`
- [ ] `app/src/main/res/layout/widget_studio_large.xml`
- [ ] `app/src/main/res/layout/widget_studio_medium.xml`
- [ ] `app/src/main/res/layout/widget_studio_small.xml`

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
- [x] `com/mlmvpn/core/aether/AetherScanService.kt` — Toast × 1 (deferred: background foreground-service action; no Compose host is alive)
- [x] `com/mlmvpn/scanner/MainActivity.kt` — Toast × 1 (deferred: platform startActivityForResult failure handler; no screen-scoped Compose callback)
- [x] `com/mlmvpn/scanner/VpnTileService.kt` — Toast × 1 (deferred: Android Quick Settings tile runs outside the Compose UI)
- [x] `com/mlmvpn/scanner/backup/BackupStage.kt` — Toast × 1 (deferred: process-start restore result is emitted before Compose UI exists)
- [x] `com/mlmvpn/scanner/engines/edg/EdgSettingsScreen.kt` — Toast × 2 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/engines/github/GtEngine.kt` — Toast × 1 (deferred: engine-level background operation has no Compose host callback)
- [x] `com/mlmvpn/scanner/engines/mlm/MlmSettingsScreen.kt` — Toast × 3 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt` — Toast × 10 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt` — Toast × 7 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/lan/LanNotification.kt` — Toast × 1 (deferred: notification action receiver runs outside the Compose UI)
- [x] `com/mlmvpn/scanner/ui/AboutScreen.kt` — Toast × 2 (replaced with Dusk snackbars; inventory corrected to include URL launch failure and developer-mode feedback)
- [x] `com/mlmvpn/scanner/ui/AddNodeScreen.kt` — Toast × 15
- [x] `com/mlmvpn/scanner/ui/AppScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/CloudTab.kt` — Toast × 29 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/CloudTroubleshootScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/FixedIpScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [ ] `com/mlmvpn/scanner/ui/GameTab.kt` — Toast × 6
- [x] `com/mlmvpn/scanner/ui/NodesTab.kt` — Toast × 19 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/OpenVpnScreen.kt` — Toast × 1
- [x] `com/mlmvpn/scanner/ui/QuickConnectTab.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/ScannerTab.kt` — Toast × 20 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/aether/AetherScreen.kt` — Toast × 4 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt` — Toast × 3 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt` — Toast × 1 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [ ] `com/mlmvpn/scanner/ui/backup/BackupScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/cfwarp/Nat64ExitUi.kt` — Toast × 2
- [x] `com/mlmvpn/scanner/ui/cloud/CfMethodCard.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/cloud/GatewayRows.kt` — Toast × 1 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/cloud/GatewaySettingsScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/cloud/PanelRows.kt` — Toast × 1 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/configstudio/StudioLocationsEditScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt` — Toast × 3 (replaced with Dusk snackbars)
- [ ] `com/mlmvpn/scanner/ui/emergency/EmergencyKit.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/emergency/EmergencyLevel2Screen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/emergency/EmergencyLevel3Screen.kt` — Toast × 4
- [ ] `com/mlmvpn/scanner/ui/game/GameBoostScreen.kt` — Toast × 4
- [ ] `com/mlmvpn/scanner/ui/geph/GephUi.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/github/GithubTunnelScreen.kt` — Toast × 1
- [x] `com/mlmvpn/scanner/ui/home/HomeConnectionWidget.kt` — Toast × 1 (replaced with DuskSnackbar)
- [ ] `com/mlmvpn/scanner/ui/lantern/LanternUi.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/mae/MaePages.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/ui/mae/MaeScreen.kt` — Toast × 1
- [x] `com/mlmvpn/scanner/ui/npv/NpvImportActivity.kt` — Toast × 1 (deferred: standalone ACTION_VIEW import Activity has no Compose host)
- [ ] `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` — Toast × 4
- [ ] `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt` — Toast × 4
- [x] `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt` — Toast × 2 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt` — Toast × 1 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt` — Toast × 6 (replaced with Dusk snackbars, including file/import feedback)
- [ ] `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt` — Toast × 2
- [x] `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/settings/GeminiExitRow.kt` — Toast × 2 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt` — Toast × 3 (replaced with Dusk snackbars)
- [x] `com/mlmvpn/scanner/ui/settings/SmartIranPage.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [x] `com/mlmvpn/scanner/ui/settings/WarpIdRelayRow.kt` — Toast × 2 (replaced with Dusk snackbar)
- [ ] `com/mlmvpn/scanner/ui/spider/SpiderConfigs.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/spider/SpiderScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt` — Toast × 5
- [x] `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt` — Toast × 6 (replaced with Dusk snackbars)
- [ ] `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` — Toast × 5
- [ ] `com/mlmvpn/scanner/ui/tunnel/WarpIdentityCard.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` — Toast × 4
- [ ] `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/utils/Donate.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/utils/SafeClip.kt` — Toast × 2
- [x] `com/mlmvpn/scanner/widget/WidgetActionActivity.kt` — Toast × 1 (deferred: app-widget trampoline can run without the Compose application UI)

## Notification builder sources

- [ ] `com/mlmvpn/core/aether/AetherScanService.kt` — NotificationCompat.Builder × 1
- [ ] `com/mlmvpn/scanner/data/IpScanService.kt` — NotificationCompat.Builder × 1
- [ ] `com/mlmvpn/scanner/engines/game/booster/session/GameBoostService.kt` — NotificationCompat.Builder × 1
- [ ] `com/mlmvpn/scanner/engines/scout/ScoutService.kt` — NotificationCompat.Builder × 1
- [ ] `com/mlmvpn/scanner/engines/tgproxy/TgBridgeService.kt` — NotificationCompat.Builder × 1
- [ ] `com/mlmvpn/scanner/engines/wireguard/WireguardScanService.kt` — NotificationCompat.Builder × 1
- [ ] `com/mlmvpn/scanner/lan/LanNotification.kt` — NotificationCompat.Builder × 1
- [ ] `com/mlmvpn/scanner/openvpn/OpenVpnService.kt` — NotificationCompat.Builder × 1
- [ ] `com/mlmvpn/scanner/widget/WidgetConnectService.kt` — NotificationCompat.Builder × 2
- [ ] `kittoku/mvc/service/SoftEtherVpnService.kt` — NotificationCompat.Builder × 2

## Current implementation note

Foundation added in this pass: `DuskTheme` palette/shape/spacing tokens, reusable `DuskCard` and `DuskPill`, light/dark Material schemes, Vazirmatn variable font statics for regular/medium/semibold/bold across Persian and Latin text (OFL license under `app/src/main/assets/licenses/`), and the home connection-card landscape/sun-to-moon transition. Batch (a) restyled the connection card and floating four-destination nav; the home board/connection interactions are now source-audited, with device/runtime rendering still pending. Build verification is not yet available in this shell: `gradlew` now exists, but Java is not installed. Feature screens, interaction states, XML/native/notification surfaces, systematic light/dark/RTL checks, and a full device/runtime review remain unverified. The latest core utility-screen batch passed GitHub debug and release builds (see `BUILD_STATUS.md`); local Gradle builds remain unavailable in this shell because Java is not installed.

## Progress note

Inventory checkpoint: source lists are recorded; checked rows have been migrated to DuskTheme and statically audited for literal colors, radii and font sizes. Runtime/light-dark/RTL verification is still pending. The app has extensive feature-specific routes, dialogs and custom-drawn UI; keep partial work honest rather than treating a top-level theme wrapper as a full redesign.

Core-screen audit status (2026-11-26; side panels remain deferred): Home/Connect — done (0 unchecked; 1 state-only deferral); split tunnel/Iran routing — done (0 unchecked); Settings — done (0 unchecked); Splash/Onboarding — done (0 unchecked); config add/edit — done (0 unchecked); clean-IP scanner — done (0 unchecked). These are source-level UI audits; no build/runtime verification was run before 700/1203. Generic VPN add/edit excludes Config Studio side-panel routes, which remain for the later side-panel phase.
