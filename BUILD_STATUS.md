# Build status

## Verification history

| Commit | Run | Result | Errors fixed / notes |
|---|---:|---|---|
| `4e7bffab5213d0cfefee639105ecda047e61f0bc` | [45800616606](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45800616606) | Green | `assembleDebug` passed; log and APK artifacts uploaded. |
| `4e7bffab5213d0cfefee639105ecda047e61f0bc` | [45800616627](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45800616627) | Green | Parallel release-APK build passed and uploaded APK artifacts. |
| `8a732f9ab765ff64ae68c9e09f5965ae418064c4` | [45796255514](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45796255514) | Red | Compiler errors: `IosSettings.kt` used invalid `TextStyle(color=..., style=...)` and named `textAlign` arguments at two `BasicTextField`s; changed both to `MaterialTheme.typography.*.copy(...)`. `IosAlerts.kt` lacked the `size` import used by its progress indicator. Fixed in `4e7bffa`. |
| `8a732f9ab765ff64ae68c9e09f5965ae418064c4` | [45796255356](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45796255356) | Red | Parallel release-APK workflow had the same compiler errors. GitHub check annotations were read; full raw log retrieval via GitHub’s artifact host returned EOF. |
| `4cd7bfb8fcfa238edf56b356f1e17ac51abe181d` | [45792715554](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45792715554) | Red | Compiler errors: restored `sp` import in `MaePages.kt`; moved composable theme reads out of `HomeConnectionWidget.kt` Canvas draw lambdas and corrected `Stroke(width=...)`. Fixed in `8a732f9`. |
| `4cd7bfb8fcfa238edf56b356f1e17ac51abe181d` | [45792715567](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45792715567) | Red | Parallel release-APK workflow reported the same errors. |
| `3e3273535a98e48fe561398d459f13a93f50ffc9` | [45789488926](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45789488926) | Red | `NodesWidgets.kt` had a missing closing brace and theme getters in a non-composable helper; `GstScreens.kt` lacked the `sp` import. Fixed in `4cd7bfb`. |
| `3e3273535a98e48fe561398d459f13a93f50ffc9` | [45789488822](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45789488822) | Red | Parallel release-APK workflow reported the same errors. |
| `20f332916476d4e9a6a086d20c434677a477792c` | [45774824927](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45774824927) | Green | Earlier `Android APK` run; predates the current migration commits and log-artifact addition. Not verification of current UI changes. |

## Current state

The required wrapper files are present. `.github/workflows/build.yml` runs `assembleDebug` on pushes and uploads the Gradle log even when the build fails. The latest push has green debug and release workflow results. Local Gradle compilation is unavailable because Java is not installed in this sandbox.
