# Build status

## Verification history

| Commit | Run | Result | Errors fixed / notes |
|---|---:|---|---|
| `4cd7bfb8fcfa238edf56b356f1e17ac51abe181d` | [45792715554](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45792715554) | Red | Kotlin compile annotations: `MaePages.kt` still uses `sp` in other helpers, so the import was restored; `HomeConnectionWidget.kt` read composable theme values inside Canvas draw lambdas and passed unsupported `strokeWidth` to `Stroke`, so values were hoisted to composable scope and the constructor now uses `width`. Fixes are staged for the next commit. |
| `4cd7bfb8fcfa238edf56b356f1e17ac51abe181d` | [45792715567](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45792715567) | Red | Parallel release-APK workflow reported the same Kotlin compiler errors. API check annotations were read; full log/artifact download is still blocked by EOF from GitHub’s external Actions log/artifact host in this sandbox. |
| `3e3273535a98e48fe561398d459f13a93f50ffc9` | [45789488926](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45789488926) | Red | `NodesWidgets.kt` had a missing closing brace and theme getters in a non-composable helper; `GstScreens.kt` lacked the `sp` import. Fixed in `4cd7bfb`. |
| `3e3273535a98e48fe561398d459f13a93f50ffc9` | [45789488822](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45789488822) | Red | Parallel release-APK workflow reported the same errors. Check annotations were read; raw log download returned EOF. |
| `20f332916476d4e9a6a086d20c434677a477792c` | [45774824927](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45774824927) | Green | Earlier `Android APK` run; predates the current migration commits and log-artifact addition. Not verification of current UI changes. |

## Next verification

A new push with the `MaePages.kt` and `HomeConnectionWidget.kt` fixes is pending. The debug workflow captures Gradle output in `build.log` and uploads it as an artifact on success or failure. The wrapper launcher, JAR, and properties file are present. Local Gradle compilation is unavailable because Java is not installed in this sandbox.
