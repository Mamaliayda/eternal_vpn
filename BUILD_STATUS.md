# Build status

## Verification history

| Commit | Run | Result | Errors fixed / notes |
|---|---:|---|---|
| `8a732f9ab765ff64ae68c9e09f5965ae418064c4` | [45796255514](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45796255514) | Red | Compiler errors: `IosSettings.kt` used invalid `TextStyle(color=..., style=...)` and named `textAlign` arguments at two `BasicTextField`s; changed both to `MaterialTheme.typography.*.copy(...)`. `IosAlerts.kt` lacked the `size` import used by its progress indicator; added it. |
| `8a732f9ab765ff64ae68c9e09f5965ae418064c4` | [45796255356](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45796255356) | Red | Parallel release-APK workflow had the same compiler errors. GitHub check annotations were read; full raw log retrieval via GitHub’s artifact host returned EOF. |
| `4cd7bfb8fcfa238edf56b356f1e17ac51abe181d` | [45792715554](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45792715554) | Red | Compiler errors: restored `sp` import in `MaePages.kt`; moved composable theme reads out of `HomeConnectionWidget.kt` Canvas draw lambdas and corrected `Stroke(width=...)`. |
| `4cd7bfb8fcfa238edf56b356f1e17ac51abe181d` | [45792715567](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45792715567) | Red | Parallel release-APK workflow reported the same errors. |
| `3e3273535a98e48fe561398d459f13a93f50ffc9` | [45789488926](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45789488926) | Red | `NodesWidgets.kt` had a missing closing brace and theme getters in a non-composable helper; `GstScreens.kt` lacked the `sp` import. Fixed in `4cd7bfb`. |
| `3e3273535a98e48fe561398d459f13a93f50ffc9` | [45789488822](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45789488822) | Red | Parallel release-APK workflow reported the same errors. |
| `20f332916476d4e9a6a086d20c434677a477792c` | [45774824927](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45774824927) | Green | Earlier `Android APK` run; predates the current migration commits and log-artifact addition. Not verification of current UI changes. |

## Next verification

Fixes for `IosSettings.kt` and `IosAlerts.kt` are prepared for the next push. The debug workflow captures Gradle output in `build.log` and uploads it as an artifact on success or failure. The wrapper launcher, JAR, and properties file are present. Local Gradle compilation is unavailable because Java is not installed in this sandbox.
