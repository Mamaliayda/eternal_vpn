# Build status

## Verification history

| Commit | Run | Result | Errors fixed / notes |
|---|---:|---|---|
| `3e3273535a98e48fe561398d459f13a93f50ffc9` | [45789488926](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45789488926) | Red | `assembleDebug` reported `NodesWidgets.kt` syntax/bracing errors that cascaded into local-function, missing `CONNECT_BAR_LIGHT_HEIGHT`, and composable-context errors; `GstScreens.kt:380` had unresolved `sp`. Fixes are applied in the next commit: close `NodeConnectBar`, mark its theme-color helper `@Composable`, and restore the `sp` unit import. |
| `3e3273535a98e48fe561398d459f13a93f50ffc9` | [45789488822](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45789488822) | Red | The parallel release-APK workflow reported the same Kotlin compile errors. GitHub check annotations were retrieved and read; `gh run view --log-failed` and artifact download both returned EOF in this sandbox, so full raw logs could not be downloaded. |
| `20f332916476d4e9a6a086d20c434677a477792c` | [45774824927](https://github.com/Mamaliayda/eternal_vpn/actions/runs/45774824927) | Green | Earlier `Android APK` run; predates the current migration commits and log-artifact addition. Not verification of current UI changes. |

## Next verification

The brace/composable and missing `sp` import fixes are ready to push; Actions verification is pending. The workflow captures Gradle output in `build.log` and uploads it as an artifact even when the build fails. The wrapper launcher, JAR, and properties file are present. Local Gradle compilation is unavailable because Java is not installed in this sandbox.
