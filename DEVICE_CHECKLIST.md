# Eternal VPN — real-phone visual checks

**Status: not run.** These checks require a physical Android phone; source inspection and CI builds are not a substitute. Do not tick any item until observed on-device. Record the phone model, Android version, build/version, and date at the bottom.

## Setup

1. Install the same debug APK under test; grant VPN permission only when a check asks for it.
2. In **Settings → Display**, switch between the app's light and dark appearance. In Android/app language settings, choose Persian (فارسی) and enable RTL. Reopen the app after each change if needed.
3. Keep real endpoints private: redact addresses, account names, QR codes, and notification contents in any screenshots shared outside your device.

## Checks

| Done | Screen / action | Expected result | Required modes |
|---|---|---|---|
| [ ] | Home board and connection card | No clipped tiles or status labels; cards, navigation, system bars and connection states remain legible; scroll/drag interactions still target the intended tile. | Light + dark; Persian RTL; mixed text `سرور DE-Frankfurt 12` |
| [ ] | Settings → Display / wallpaper picker | Selected appearance and wallpaper are obvious; switching light/dark updates surfaces and system bars without low-contrast text or a stale preview. | Light + dark; Persian RTL |
| [ ] | Settings → VPN settings → app picker | Search, checkboxes, row labels and empty state remain aligned and usable; package names are not cut off unexpectedly. | Light + dark; Persian RTL; mixed Latin package name + Persian label |
| [ ] | Region/server picker and connection details | Country names, flags, IP/port and latency fit; technical values stay LTR and digits are readable inside Persian text. | Light + dark; Persian RTL; `فرانکفورت DE · 192.0.2.10:443 · ۱۲ ms` |
| [ ] | About screen | App title reads **Eternal VPN**; version, links and support rows are legible and do not expose legacy MLM branding. | Light + dark; Persian RTL |
| [ ] | Widget Studio, then add one widget to the launcher | Editor and preview match; widget title reads **Eternal VPN**; resize/configure and tap actions work. Launcher rendering is checked separately from the in-app preview. | Light + dark in editor; Persian RTL; widget host in both available system themes |
| [ ] | Notification shade and quick-settings tile | App notification title is **Eternal VPN**; channel/action text is clear; tile state and label remain understandable in the system-owned shade. | Light + dark system theme; Persian system language if available |
| [ ] | One form/dialog and one error/snackbar state | Text, actions and validation messages fit without overlap; dismiss/back behavior is predictable and no old branding appears. | Light + dark; Persian RTL; mixed `VPN / اتصال 12` |
| [ ] | Scroll a long help/tutorial page | Persian glyphs shape correctly; Latin acronyms, file names, addresses and digits retain their intended direction; no line is clipped. | Light + dark; Persian RTL; mixed Persian/English/digits |

## Device record

- Phone/model: ______________________________
- Android version: ___________________________
- Build/version: ______________________________
- Date: ______________________________________
- Notes / failed checks: _______________________
