# Eternal VPN — screen redesign checklist

> **Progress: 264 / 1203 complete** — each checked row is fully migrated; unverified routes and states remain unchecked.

> Inventory generated from Kotlin source on 2026-11-26. A row is considered migrated only after its visible composition, nested states, and overlays are reviewed against the Soft Dusk tokens. Shared theme inheritance alone does **not** count as a completed visual redesign.

## Shared foundation

- [x] Soft Dusk light/night palette and contrast review (2026-11-26: audited theme text/surface and semantic pairs; darkened light muted/secondary text for WCAG AA on blush/cream and changed light tertiary text to navy for coral contrast).
- [ ] Central shapes, typography, spacing and reusable components.
- [ ] Material light/dark surface mapping and Android system bars.
- [ ] Vazirmatn Persian/Latin font weights and mixed-script rendering.
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
- [ ] `fixed_ip`
- [ ] `flux`
- [ ] `freeconfig`
- [ ] `fronting`
- [ ] `game`
- [ ] `geph`
- [ ] `github`
- [ ] `iran`
- [ ] `lan`
- [ ] `lantern`
- [ ] `mae`
- [ ] `masque`
- [ ] `nodes`
- [ ] `npv`
- [ ] `openvpn`
- [ ] `outline`
- [ ] `proton`
- [ ] `psiphon`
- [ ] `quick`
- [ ] `scanner`
- [ ] `scout`
- [ ] `settings`
- [ ] `store`
- [ ] `sublink`
- [ ] `tgproxy`
- [ ] `tor`
- [ ] `tutorial`
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

- [ ] `EdgSettingsScreen` — `com/mlmvpn/scanner/engines/edg/EdgSettingsScreen.kt`
- [ ] `MlmSettingsScreen` — `com/mlmvpn/scanner/engines/mlm/MlmSettingsScreen.kt`
- [ ] `MlmUsersScreen` — `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt`
- [x] `AddMlmUserDialog` — `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt`
- [ ] `NahanSettingsScreen` — `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt`
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

- [ ] `BpbSettingsModal` — `com/mlmvpn/scanner/ui/BpbSettingsModal.kt`
### CloudCoach.kt

- [x] `CloudOfferDialog` — `com/mlmvpn/scanner/ui/CloudCoach.kt`
### CloudScreens.kt

- [ ] `CloudAddAccountScreen` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [ ] `CloudGroupScreen` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
### CloudTab.kt

- [ ] `CloudTab` — `com/mlmvpn/scanner/ui/CloudTab.kt`
### CloudTroubleshootScreen.kt

- [ ] `CloudTroubleshootScreen` — `com/mlmvpn/scanner/ui/CloudTroubleshootScreen.kt`
### CombineCoach.kt

- [ ] `CombineOfferDialog` — `com/mlmvpn/scanner/ui/CombineCoach.kt`
- [ ] `CombineDoneDialog` — `com/mlmvpn/scanner/ui/CombineCoach.kt`
### FixedIpScreen.kt

- [ ] `FixedIpScreen` — `com/mlmvpn/scanner/ui/FixedIpScreen.kt`
### FreeConfigTab.kt

- [ ] `FreeConfigTab` — `com/mlmvpn/scanner/ui/FreeConfigTab.kt`
### FreeConfigWizard.kt

- [ ] `FreeConfigWizard` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
### GameTab.kt

- [ ] `GameTab` — `com/mlmvpn/scanner/ui/GameTab.kt`
- [ ] `GamePickerPage` — `com/mlmvpn/scanner/ui/GameTab.kt`
- [ ] `RegionPickerPage` — `com/mlmvpn/scanner/ui/GameTab.kt`
### HelpCenterScreen.kt

- [ ] `HelpCenterScreen` — `com/mlmvpn/scanner/ui/HelpCenterScreen.kt`
- [ ] `HelpArticleScreen` — `com/mlmvpn/scanner/ui/HelpCenterScreen.kt`
- [ ] `HelpFaqScreen` — `com/mlmvpn/scanner/ui/HelpCenterScreen.kt`
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

- [ ] `QuickConnectTab` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
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

- [ ] `GatewayServerDetailScreen` — `com/mlmvpn/scanner/ui/VpnGateInfoScreens.kt`
- [ ] `GatewayHelpScreen` — `com/mlmvpn/scanner/ui/VpnGateInfoScreens.kt`
### VpnGateServersScreen.kt

- [x] `VpnGateServersScreen` — `com/mlmvpn/scanner/ui/VpnGateServersScreen.kt`
### VpnGateTab.kt

- [x] `VpnGateTab` — `com/mlmvpn/scanner/ui/VpnGateTab.kt`
### WireguardGuard.kt

- [x] `WireguardConflictDialog` — `com/mlmvpn/scanner/ui/WireguardGuard.kt`
- [x] `EngineConflictDialog` — `com/mlmvpn/scanner/ui/WireguardGuard.kt`
### WireguardTab.kt

- [ ] `WireguardTab` — `com/mlmvpn/scanner/ui/WireguardTab.kt`
### aether

- [ ] `AetherScreen` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `AdvancedSettingsDialog` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
### amnezia

- [ ] `AmneziaScreen` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `AmzSettingsScreen` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [x] `ActionSheet` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [ ] `AmzServersScreen` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
### arena

- [ ] `ArenaScreen` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
### backup

- [ ] `BackupScreen` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
- [ ] `HomePage` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
- [ ] `PreviewPage` — `com/mlmvpn/scanner/ui/backup/BackupScreen.kt`
### cfwarp

- [ ] `CfWarpSettingsScreen` — `com/mlmvpn/scanner/ui/cfwarp/CfWarpUi.kt`
- [ ] `Nat64ExitScreen` — `com/mlmvpn/scanner/ui/cfwarp/Nat64ExitUi.kt`
### cloud

- [ ] `GatewaySettingsScreen` — `com/mlmvpn/scanner/ui/cloud/GatewaySettingsScreen.kt`
### configstudio

- [ ] `ConfigStudioHost` — `com/mlmvpn/scanner/ui/configstudio/ConfigStudioHost.kt`
- [ ] `StudioAccountDetailScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountDetailScreen.kt`
- [ ] `StudioAccountsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountsScreen.kt`
- [ ] `StudioAnalyticsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [ ] `StudioAuditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioAuditScreen.kt`
- [ ] `StudioBulkActionScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioBulkActionScreen.kt`
- [ ] `StudioBulkAddScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioBulkAddScreen.kt`
- [ ] `StudioBulkUsersScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioBulkUsersScreen.kt`
- [ ] `StudioCentralAccountScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioCentralAccountScreen.kt`
- [ ] `StudioCentralBulkUsersScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioCentralBulkUsersScreen.kt`
- [ ] `StudioCentralNewUserScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioCentralNewUserScreen.kt`
- [ ] `StudioCentralUserScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioCentralUserScreen.kt`
- [ ] `StudioConfigBuilderScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioConfigBuilderScreen.kt`
- [ ] `StudioConfigsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioConfigsScreen.kt`
- [ ] `StudioDashboardScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `StudioExitsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioExitsScreen.kt`
- [ ] `StudioLocationsEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioLocationsEditScreen.kt`
- [ ] `StudioMoreScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioMoreScreen.kt`
- [ ] `StudioMultiLocationScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioMultiLocationScreen.kt`
- [ ] `StudioNewUserScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNewUserScreen.kt`
- [x] `StudioNodeEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeEditScreen.kt`
- [ ] `StudioNodeGroupEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeGroupEditScreen.kt`
- [ ] `StudioNodeGroupsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodeGroupsScreen.kt`
- [x] `StudioNodesScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioNodesScreen.kt`
- [ ] `StudioPlanEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioPlanEditScreen.kt`
- [ ] `StudioPlansScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioPlansScreen.kt`
- [ ] `StudioSettingsScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioSettingsScreen.kt`
- [ ] `StudioTemplateEditScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplateEditScreen.kt`
- [ ] `StudioTemplatesScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioTemplatesScreen.kt`
- [ ] `StudioUserDetailScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioUserDetailScreen.kt`
- [ ] `StudioUsersScreen` — `com/mlmvpn/scanner/ui/configstudio/StudioUsersScreen.kt`
- [ ] `ProtocolPicker` — `com/mlmvpn/scanner/ui/configstudio/design/StudioDesign.kt`
- [ ] `LocationPicker` — `com/mlmvpn/scanner/ui/configstudio/parts/LocationPicker.kt`
- [ ] `PortPicker` — `com/mlmvpn/scanner/ui/configstudio/parts/PortPicker.kt`
- [ ] `StudioSetupWizard` — `com/mlmvpn/scanner/ui/configstudio/wizard/StudioSetupWizard.kt`
### doctor

- [ ] `CfDoctorScreen` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `AccountPage` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `ReportPage` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `CloudDoctorScreen` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `StepPage` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
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
- [ ] `IosModalHost` — `com/mlmvpn/scanner/ui/home/ModalHost.kt`
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

- [ ] `OutlineScreen` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [ ] `SourcesPage` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [ ] `OutlineServersPage` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
### proton

- [ ] `ProtonScreen` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `ProtonWebPage` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
### sanction

- [ ] `AntiSanctionScreen` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt`
- [ ] `SanctionDomainsScreen` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt`
- [ ] `SanctionAppsScreen` — `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt`
### scout

- [ ] `ScoutScreen` — `com/mlmvpn/scanner/ui/scout/ScoutScreen.kt`
- [x] `ScoutDialog` — `com/mlmvpn/scanner/ui/scout/ScoutScreen.kt`
### settings

- [ ] `WorkersListPage` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `D1ListPage` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `KvListPage` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `SelectableResourcePage` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `IosScreen` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `IosPickerScreen` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `IosTextScreen` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `BackendDnsPage` — `com/mlmvpn/scanner/ui/settings/NetworkSettingPages.kt`
- [ ] `LocalPortPage` — `com/mlmvpn/scanner/ui/settings/NetworkSettingPages.kt`
- [ ] `ProxyModePage` — `com/mlmvpn/scanner/ui/settings/NetworkSettingPages.kt`
- [ ] `AllowLanPage` — `com/mlmvpn/scanner/ui/settings/NetworkSettingPages.kt`
- [x] `SettingsScreen` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `DisplayPage` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `WallpaperPage` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `PresetPickerPage` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [ ] `SmartIranPage` — `com/mlmvpn/scanner/ui/settings/SmartIranPage.kt`
- [ ] `UsageScreen` — `com/mlmvpn/scanner/ui/settings/UsageScreen.kt`
- [ ] `VpnSettingsScreen` — `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt`
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

- [ ] `PsiphonMethodScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `PsiphonProxyScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `PsiphonCleanIpScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `PsiphonPairingScreen` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
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

- [ ] `MlmUserCard` — `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt`
- [ ] `InfoItem` — `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt`
- [ ] `ActionBtn` — `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt`
- [ ] `NahanSectionHeader` — `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt`
- [ ] `NahanTextField` — `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt`
- [ ] `NahanUserCard` — `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt`
### AboutScreen.kt

- [x] `ReleaseHeader` — `com/mlmvpn/scanner/ui/AboutScreen.kt`
- [x] `ChangeCard` — `com/mlmvpn/scanner/ui/AboutScreen.kt`
- [ ] `changelogText` — `com/mlmvpn/scanner/ui/AboutScreen.kt`
### AddNodeModal.kt

- [ ] `getTitleForForm` — `com/mlmvpn/scanner/ui/AddNodeModal.kt`
### AppScreen.kt

- [x] `FeatureTopBar` — `com/mlmvpn/scanner/ui/AppScreen.kt`
### BaseConfigPicker.kt

- [x] `BaseConfigRow` — `com/mlmvpn/scanner/ui/BaseConfigPicker.kt`
### BpbSettingsModal.kt

- [ ] `SectionTitle` — `com/mlmvpn/scanner/ui/BpbSettingsModal.kt`
- [ ] `PortsGrid` — `com/mlmvpn/scanner/ui/BpbSettingsModal.kt`
### CloudCoach.kt

- [ ] `CloudCoachBar` — `com/mlmvpn/scanner/ui/CloudCoach.kt`
### CloudScreens.kt

- [ ] `CloudPanelRow` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [ ] `CloudPanelHeaderRow` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [ ] `PanelStatePill` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [ ] `PanelBadge` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [ ] `PanelLamps` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
- [ ] `CloudPanelManageRows` — `com/mlmvpn/scanner/ui/CloudScreens.kt`
### CloudTab.kt

- [ ] `CloudDetailPlaceholder` — `com/mlmvpn/scanner/ui/CloudTab.kt`
- [ ] `AccountGroupCard` — `com/mlmvpn/scanner/ui/CloudTab.kt`
- [ ] `CloudGroupHandoff` — `com/mlmvpn/scanner/ui/CloudTab.kt`
### CloudTroubleshootScreen.kt

- [ ] `com` — `com/mlmvpn/scanner/ui/CloudTroubleshootScreen.kt`
- [ ] `httpValue` — `com/mlmvpn/scanner/ui/CloudTroubleshootScreen.kt`
### CombineCoach.kt

- [ ] `CombineCoachBar` — `com/mlmvpn/scanner/ui/CombineCoach.kt`
- [ ] `CoachBar` — `com/mlmvpn/scanner/ui/CombineCoach.kt`
### FreeConfigWizard.kt

- [ ] `BusyStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [ ] `PickPoolStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [ ] `PickCountStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [ ] `QuickPickChip` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [ ] `FetchingStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [ ] `ResultsStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
- [ ] `DoneStep` — `com/mlmvpn/scanner/ui/FreeConfigWizard.kt`
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

- [ ] `HelpSearchField` — `com/mlmvpn/scanner/ui/HelpCenterScreen.kt`
- [ ] `HelpBody` — `com/mlmvpn/scanner/ui/HelpCenterScreen.kt`
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

- [ ] `ConnectOrb` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
- [ ] `EgressBanner` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
- [ ] `PoolRow` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
- [ ] `SavedRow` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
- [ ] `Badge` — `com/mlmvpn/scanner/ui/QuickConnectTab.kt`
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

- [ ] `HelpRow` — `com/mlmvpn/scanner/ui/VpnGateInfoScreens.kt`
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

- [ ] `FeatureRow` — `com/mlmvpn/scanner/ui/WireguardTab.kt`
### aether

- [ ] `AetherPowerButton` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `AetherStatusLine` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `QuickSettings` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `QuickRow` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `MiniSegmented` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `AetherProgressStrip` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `HeaderCard` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `TabRow` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `MasquePane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `WgPane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `GooLPane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `MasqueAdvancedPane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `SharedAdvancedPane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `SharedPane` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [x] `LogCard` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `StatusCard` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
- [ ] `ConnectBar` — `com/mlmvpn/scanner/ui/aether/AetherScreen.kt`
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

- [ ] `AmzHome` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `HeroCard` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `Orb` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `StatTile` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `statusLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `stepLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `failLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `subLine` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `LocationRow` — `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt`
- [ ] `groupName` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [ ] `kindColor` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [ ] `delayColor` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [ ] `Tag` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [ ] `DelayCapsule` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [ ] `BarIcon` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [ ] `UndoBar` — `com/mlmvpn/scanner/ui/amnezia/AmzParts.kt`
- [ ] `SearchBar` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [ ] `Segmented` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [ ] `GroupChips` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [ ] `Chip` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [ ] `TestBar` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [ ] `Pill` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [ ] `PickRow` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [ ] `GroupHeader` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [ ] `SwipeRow` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
- [ ] `ServerRow` — `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt`
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
- [ ] `TeamRow` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `PrimaryButton` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `ThinBar` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `SoundToggle` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `Lobby` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `Confirm` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `Live` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `PrepList` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `CleanIpGroup` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `ScoreRing` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `tintOf` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `Result` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
- [ ] `Failure` — `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt`
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

- [ ] `CfMethodCard` — `com/mlmvpn/scanner/ui/cloud/CfMethodCard.kt`
- [ ] `CloudReceivedModule` — `com/mlmvpn/scanner/ui/cloud/CloudReceivedModule.kt`
- [ ] `accountColor` — `com/mlmvpn/scanner/ui/cloud/CloudUsageModule.kt`
- [ ] `CloudUsageModule` — `com/mlmvpn/scanner/ui/cloud/CloudUsageModule.kt`
- [ ] `GatewayBlock` — `com/mlmvpn/scanner/ui/cloud/GatewayRows.kt`
- [ ] `Note` — `com/mlmvpn/scanner/ui/cloud/GatewayRows.kt`
- [ ] `GwDivider` — `com/mlmvpn/scanner/ui/cloud/GatewayRows.kt`
- [ ] `Editor` — `com/mlmvpn/scanner/ui/cloud/GatewaySettingsScreen.kt`
- [ ] `PanelRows` — `com/mlmvpn/scanner/ui/cloud/PanelRows.kt`
- [ ] `OpenPanelRow` — `com/mlmvpn/scanner/ui/cloud/PanelRows.kt`
- [ ] `Divider` — `com/mlmvpn/scanner/ui/cloud/PanelRows.kt`
- [ ] `ActionRow` — `com/mlmvpn/scanner/ui/cloud/PanelRows.kt`
### configstudio

- [ ] `FleetSummary` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountsScreen.kt`
- [ ] `ShardRow` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountsScreen.kt`
- [ ] `shardSubtitle` — `com/mlmvpn/scanner/ui/configstudio/StudioAccountsScreen.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioAnalyticsScreen.kt`
- [ ] `AuditRow` — `com/mlmvpn/scanner/ui/configstudio/StudioAuditScreen.kt`
- [ ] `actionLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioAuditScreen.kt`
- [ ] `JobRow` — `com/mlmvpn/scanner/ui/configstudio/StudioBulkAddScreen.kt`
- [ ] `roomLine` — `com/mlmvpn/scanner/ui/configstudio/StudioCentralNewUserScreen.kt`
- [ ] `StudioCombineHandover` — `com/mlmvpn/scanner/ui/configstudio/StudioCombineHandover.kt`
- [ ] `androidx` — `com/mlmvpn/scanner/ui/configstudio/StudioConfigBuilderScreen.kt`
- [ ] `ConfigRow` — `com/mlmvpn/scanner/ui/configstudio/StudioConfigsScreen.kt`
- [ ] `expiresInText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `timeLeftText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `countOrDash` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `activityLabel` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `whenText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `bytesFa` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `freshnessText` — `com/mlmvpn/scanner/ui/configstudio/StudioDashboardScreen.kt`
- [ ] `ExitRow` — `com/mlmvpn/scanner/ui/configstudio/StudioExitsScreen.kt`
- [ ] `CountryTitle` — `com/mlmvpn/scanner/ui/configstudio/StudioMultiLocationScreen.kt`
- [ ] `ResultView` — `com/mlmvpn/scanner/ui/configstudio/StudioNewUserScreen.kt`
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

- [ ] `DoctorMain` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `Hero` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `Capsule` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `StageRow` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `layerText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `TextRow` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `stepText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `findingText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `worksText` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `Field` — `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt`
- [ ] `Step` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `HeroFrame` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `HeroTitle` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `HubHero` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `SetupHero` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `Verdict` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `PopIn` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `StepRow` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `Pill` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `Chevron` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `SelectRow` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `CredentialSection` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `DoctorField` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `DoctorCapsule` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `DeveloperSection` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
- [ ] `DevLine` — `com/mlmvpn/scanner/ui/doctor/CloudDoctorScreen.kt`
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

- [ ] `ids` — `com/mlmvpn/scanner/ui/home/ActiveEngines.kt`
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
- [ ] `isDark` — `com/mlmvpn/scanner/ui/home/Appearance.kt`
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
- [ ] `rememberSlotPosition` — `com/mlmvpn/scanner/ui/home/HomeScreen.kt`
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

- [ ] `OutlineUndoBar` — `com/mlmvpn/scanner/ui/outline/OutlineParts.kt`
- [ ] `KeyQr` — `com/mlmvpn/scanner/ui/outline/OutlineParts.kt`
- [ ] `OutlineHome` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [ ] `TestProgress` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [ ] `HeroCard` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [ ] `Orb` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [ ] `StatTile` — `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt`
- [ ] `SearchBar` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [ ] `Chip` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [ ] `SortChip` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [ ] `Pill` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [ ] `PickRow` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [ ] `SwipeRow` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
- [ ] `ServerRow` — `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt`
### proton

- [ ] `rememberVpnGate` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `ProtonHome` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `PingProgress` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `ServerRow` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `statusText` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `PrimaryButton` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `Message` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `helperName` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `HelperProgress` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
- [ ] `HelperChooser` — `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt`
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

- [ ] `CloudResourcesHub` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `ResourceSection` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `HubRow` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `HubChevron` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `ItemRow` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `SelectionTick` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `StatChip` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `AccountTotals` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `TotalCell` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `TotalDivider` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [x] `EmptyBlock` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `LtrText` — `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt`
- [ ] `GeminiExitRow` — `com/mlmvpn/scanner/ui/settings/GeminiExitRow.kt`
- [x] `IosAlert` — `com/mlmvpn/scanner/ui/settings/IosAlerts.kt`
- [x] `AlertSeparator` — `com/mlmvpn/scanner/ui/settings/IosAlerts.kt`
- [x] `AlertButton` — `com/mlmvpn/scanner/ui/settings/IosAlerts.kt`
- [x] `IosPrompt` — `com/mlmvpn/scanner/ui/settings/IosAlerts.kt`
- [ ] `rememberIosRefreshState` — `com/mlmvpn/scanner/ui/settings/IosRefresh.kt`
- [ ] `IosRefreshIndicator` — `com/mlmvpn/scanner/ui/settings/IosRefresh.kt`
- [ ] `SettingsSectionHeader` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `SettingsGroup` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `Separator` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `Separator` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `SettingsGlyph` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `RowChevron` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `RowFrame` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `SettingsActionRow` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `SettingsFooter` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `SettingsRow` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `IosBadge` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `SettingsToggle` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `SettingsTextRow` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `CloudflareAccountCard` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `AccountDots` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `SettingsSlider` — `com/mlmvpn/scanner/ui/settings/IosSettings.kt`
- [ ] `SettingsDetailPlaceholder` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [ ] `presetName` — `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt`
- [x] `AppToggleRow` — `com/mlmvpn/scanner/ui/settings/SmartIranPage.kt`
- [ ] `UsagePeriod` — `com/mlmvpn/scanner/ui/settings/UsageScreen.kt`
- [ ] `SevenDayChart` — `com/mlmvpn/scanner/ui/settings/UsageScreen.kt`
- [ ] `rememberInstalledApps` — `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt`
- [ ] `mtuMethodLabel` — `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt`
- [ ] `WarpIdRelayRow` — `com/mlmvpn/scanner/ui/settings/WarpIdRelayRow.kt`
- [ ] `WorkerSetupRow` — `com/mlmvpn/scanner/ui/settings/WorkerSetupRow.kt`
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
- [ ] `ColumnScope` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `PsiphonLiveCard` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `MethodRow` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `IpRow` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `RunSummary` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [x] `ActionLine` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `rememberTicker` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `ProgressShell` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `CardLine` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `Mono` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `CleanProgressCard` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `ProxyProgressCard` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `CustomTestCard` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `Note` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `Chip` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `OptionRow` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
- [ ] `CountryRow` — `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt`
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
- [ ] `com/mlmvpn/core/aether/AetherScanService.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/MainActivity.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/VpnTileService.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/backup/BackupStage.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/engines/edg/EdgSettingsScreen.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/engines/github/GtEngine.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/engines/mlm/MlmSettingsScreen.kt` — Toast × 3
- [ ] `com/mlmvpn/scanner/engines/mlm/MlmUsersScreen.kt` — Toast × 10
- [ ] `com/mlmvpn/scanner/engines/nahan/NahanSettingsScreen.kt` — Toast × 7
- [ ] `com/mlmvpn/scanner/lan/LanNotification.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/AboutScreen.kt` — Toast × 1
- [x] `com/mlmvpn/scanner/ui/AddNodeScreen.kt` — Toast × 15
- [x] `com/mlmvpn/scanner/ui/AppScreen.kt` — Toast × 1 (replaced with Dusk snackbar)
- [ ] `com/mlmvpn/scanner/ui/CloudTab.kt` — Toast × 29
- [ ] `com/mlmvpn/scanner/ui/CloudTroubleshootScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/FixedIpScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/GameTab.kt` — Toast × 6
- [ ] `com/mlmvpn/scanner/ui/NodesTab.kt` — Toast × 19
- [x] `com/mlmvpn/scanner/ui/OpenVpnScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/QuickConnectTab.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/ScannerTab.kt` — Toast × 20
- [ ] `com/mlmvpn/scanner/ui/aether/AetherScreen.kt` — Toast × 4
- [ ] `com/mlmvpn/scanner/ui/amnezia/AmneziaScreen.kt` — Toast × 3
- [ ] `com/mlmvpn/scanner/ui/amnezia/AmzServersScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/arena/ArenaScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/backup/BackupScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/cfwarp/Nat64ExitUi.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/ui/cloud/CfMethodCard.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/cloud/GatewayRows.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/cloud/GatewaySettingsScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/cloud/PanelRows.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/configstudio/StudioLocationsEditScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/doctor/CfDoctorScreen.kt` — Toast × 3
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
- [ ] `com/mlmvpn/scanner/ui/npv/NpvImportActivity.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/npv/NpvScreen.kt` — Toast × 4
- [ ] `com/mlmvpn/scanner/ui/npv/NpvSourcesPage.kt` — Toast × 4
- [ ] `com/mlmvpn/scanner/ui/outline/OutlineScreen.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/ui/outline/OutlineServersPage.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/proton/ProtonScreen.kt` — Toast × 6
- [ ] `com/mlmvpn/scanner/ui/sanction/AntiSanctionScreen.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/ui/settings/CloudResourcesScreens.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/settings/GeminiExitRow.kt` — Toast × 2
- [x] `com/mlmvpn/scanner/ui/settings/SettingsScreen.kt` — Toast × 3 (replaced with Dusk snackbars)
- [ ] `com/mlmvpn/scanner/ui/settings/SmartIranPage.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/settings/VpnSettingsScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/settings/WarpIdRelayRow.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/ui/spider/SpiderConfigs.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/spider/SpiderScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/tgproxy/TgDomainPage.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/tgproxy/TgProxyScreen.kt` — Toast × 5
- [ ] `com/mlmvpn/scanner/ui/tunnel/PsiphonPages.kt` — Toast × 6
- [ ] `com/mlmvpn/scanner/ui/tunnel/TorBridgesScreen.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/ui/tunnel/TransportScreen.kt` — Toast × 5
- [ ] `com/mlmvpn/scanner/ui/tunnel/WarpIdentityCard.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/ui/urnetwork/UrnetworkUi.kt` — Toast × 4
- [ ] `com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/ui/windscribe/WindscribeScreen.kt` — Toast × 1
- [ ] `com/mlmvpn/scanner/utils/Donate.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/utils/SafeClip.kt` — Toast × 2
- [ ] `com/mlmvpn/scanner/widget/WidgetActionActivity.kt` — Toast × 1

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

Foundation added in this pass: `DuskTheme` palette/shape/spacing tokens, reusable `DuskCard` and `DuskPill`, light/dark Material schemes, Vazirmatn variable font statics for regular/medium/semibold/bold across Persian and Latin text (OFL license under `app/src/main/assets/licenses/`), and the home connection-card landscape/sun-to-moon transition. Batch (a) restyled the connection card and floating four-destination nav; the home icon board and other home states remain open. Build verification is not yet available in this shell: `gradlew` now exists, but Java is not installed. Feature screens, interaction states, XML/native/notification surfaces, systematic light/dark/RTL checks, and a project build remain unverified.

## Progress note

Inventory checkpoint: source lists are recorded; checked rows have been migrated to DuskTheme and statically audited for literal colors, radii and font sizes. Runtime/light-dark/RTL verification is still pending. The app has extensive feature-specific routes, dialogs and custom-drawn UI; keep partial work honest rather than treating a top-level theme wrapper as a full redesign.
