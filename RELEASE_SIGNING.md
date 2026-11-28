# Android APK signing

## Current workflow configuration

`.github/workflows/android-apk.yml` invokes `:app:assembleRelease -PtestSign`. In `app/build.gradle`, that property assigns `signingConfigs.debug` to the Release build type. There is no checked-in release keystore, custom `storeFile`, or release credential configuration. These CI artifacts are therefore **test builds signed with the runner's Android debug key**, not production-release APKs. Do not distribute them as a stable signed upgrade channel.

## Creating a real release key

Generate the keystore locally on a trusted machine; answer the password prompts interactively and keep a separate secure backup:

```sh
keytool -genkeypair -v \
  -keystore eternal-vpn-release.jks \
  -alias eternal-vpn \
  -keyalg RSA -keysize 4096 -validity 10000
```

Never add the `.jks` file, its passwords, or base64 encoding to Git, an issue, or chat. For a GitHub Actions-only flow, encode the file locally and store the resulting value as a repository **Actions secret**:

```sh
base64 -w 0 eternal-vpn-release.jks
```

Add these secrets under **GitHub → repository Settings → Secrets and variables → Actions**:

- `ANDROID_KEYSTORE_BASE64` — base64 output of the keystore
- `ANDROID_KEYSTORE_PASSWORD` — keystore password
- `ANDROID_KEY_ALIAS` — `eternal-vpn` (or the alias chosen during generation)
- `ANDROID_KEY_PASSWORD` — private-key password

A future production-signing workflow should decode the keystore only into the ephemeral runner's temporary directory, wire those secrets into the Release signing config, and clean the temporary file in an `always()` step. Until those credentials are intentionally configured, keep artifacts explicitly labelled as test-signed and do not create/commit a keystore here.
