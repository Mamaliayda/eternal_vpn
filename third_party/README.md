# Upstream Android cores

Both VPN engines are kept in the APK. Psiphon's gomobile runtime is relocated to avoid
colliding with Xray's runtime; Xray's AAR is built from pinned public source during CI so the
binary itself does not need to be committed.

## Xray Android library

The app uses the `libv2ray` API from AndroidLibXrayLite v26.5.9, including the process-finder
hook implemented by `VlessXrayInjector.kt`. The Android APK workflow checks out the source at
commit `1314c9af79518a03300803019c8264c737bb3596`, builds its AAR with gomobile and the
Android NDK, and places it in `app/libs/libv2ray.aar` before Gradle runs. The upstream source
already includes its geoip/geosite data assets.

- Repository: <https://github.com/2dust/AndroidLibXrayLite>
- Release: <https://github.com/2dust/AndroidLibXrayLite/releases/tag/v26.5.9>
- Source commit: `1314c9af79518a03300803019c8264c737bb3596`
- License: LGPL-3.0 (see the upstream repository)

To build this dependency locally, check out that commit, install Go 1.26, JDK 17, Android
SDK/NDK 28.2.13676358, then run `gomobile init` and
`gomobile bind -v -androidapi 24 -trimpath -ldflags='-s -w -buildid=' ./` from the source
checkout. Copy its generated `libv2ray.aar` to `app/libs/` before building the app.

## Psiphon Android library 2.0.39

The APK consumes the relocated AAR at `app/libs/psiphontunnel-2.0.39-ns.aar`. Psiphon and
Xray are both gomobile builds: each normally bundles the same Java runtime classes (`go.Seq`,
`go.Universe`, `go.error`) and native library name (`libgojni.so`). Including both pristine
AARs causes duplicate classes and duplicate native-library paths. Picking one at packaging
time is not safe because the libraries contain different Go programs; the missing one would
fail when its core is used.

The included Psiphon AAR keeps both VPN engines available. Its gomobile runtime is relocated
from `go` to `pg`, and its native library is renamed from `libgojni.so` to `libpgjni.so`.
The Xray runtime remains unchanged. The transformation is performed by
`scripts/repackage-psiphon-aar.py` and verified against the packaged classes and native
symbols for all four ABIs.

The upstream artifact is published in Psiphon's Android Maven repository:

- Repository: <https://github.com/Psiphon-Labs/psiphon-tunnel-core-Android-library>
- Source path: `ca/psiphon/psiphontunnel/2.0.39/psiphontunnel-2.0.39.aar`
- Pinned source commit: `7ba832f9d693b69889e6c1582dac2d3e5b51e6ad`
- Upstream AAR SHA-256: `7bcbb09ed53ac44f858298253eeb77c1baf7fefd105a8d1ef4eee3a148dd7ac1`
- Relocated AAR SHA-256: `92abbb3e884decd194d5abd52cee4bb7d057264d5bd060af6b852cf5b20ee2a9`

To reproduce the bundled artifact, retrieve the upstream AAR from the pinned repository
commit and run:

```bash
python3 scripts/repackage-psiphon-aar.py /path/to/psiphontunnel-2.0.39.aar
```

The script writes a `-ns.aar` sibling. The upstream project is GPL-3.0; its source and
license are available at the pinned repository above. The relocation script is included in
this repository as the corresponding modification to the upstream artifact.
