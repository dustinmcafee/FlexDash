<div align="center">

# 📱 FlexDash

**A sleek Android dashboard for your self-hosted media server**

[![CI](https://github.com/dustinmcafee/FlexDash/actions/workflows/ci.yml/badge.svg)](https://github.com/dustinmcafee/FlexDash/actions/workflows/ci.yml)
[![Release](https://github.com/dustinmcafee/FlexDash/actions/workflows/release.yml/badge.svg)](https://github.com/dustinmcafee/FlexDash/actions/workflows/release.yml)
[![Latest Release](https://img.shields.io/github/v/release/dustinmcafee/FlexDash?style=flat-square&color=blue)](https://github.com/dustinmcafee/FlexDash/releases/latest)
[![License](https://img.shields.io/github/license/dustinmcafee/FlexDash?style=flat-square)](LICENSE)
[![Android](https://img.shields.io/badge/Android-8.0%2B-green?style=flat-square&logo=android)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-purple?style=flat-square&logo=kotlin)](https://kotlinlang.org)

---

*One tap to any service. Automatically routes through VPN when connected.*

</div>

---

## ✨ Features

| Feature | Details |
|---|---|
| 🔒 **VPN-aware routing** | Detects `10.8.0.x` network interface — uses `10.8.0.6` on VPN, `192.168.1.175` on LAN |
| 🎨 **Chrome Custom Tabs** | Native browser experience, no WebView jank |
| 🌑 **Dark theme** | Easy on the eyes at 3am when you're adding torrents |
| ⚡ **Instant launch** | Zero loading screens, zero login prompts |

---

## 🚀 Services

| App | Port | Purpose |
|---|---|---|
| 🎬 Jellyfin | `8096` | Stream your media library |
| 🎥 Radarr | `7878` | Automated movie downloads |
| 📺 Sonarr | `8989` | Automated TV show downloads |
| ⬇️ qBittorrent | `8080` | Download client (VPN kill-switch enforced) |
| 🔍 Prowlarr | `9696` | Indexer aggregator |

---

## 📥 Install

### Phone / Tablet
Download the latest APK from [Releases](https://github.com/dustinmcafee/FlexDash/releases/latest), enable **Install unknown apps** for your browser, and sideload.

### Android TV / Google TV / Fire TV
Runs on any Android-based TV device on **Android 8.0+** (Nvidia Shield, Chromecast with Google TV, Fire TV, Android smart TVs). Non-Android TVs (Samsung Tizen, LG webOS, Roku) are not supported.

1. Enable **Developer options → USB/network debugging** on the TV.
2. Install over ADB: `adb connect <tv-ip>` then `adb install app-debug.apk` — or sideload with an app like *Downloader* / *Send Files to TV*.
3. FlexDash appears in the TV home-screen app row and is fully D-pad navigable. The focused service card shows a green border; press the center button to launch.

> A Custom Tabs–capable browser (e.g. Chrome) must be installed on the TV to open services.

---

## 🔧 Build

```bash
git clone git@github.com:dustinmcafee/FlexDash.git
cd FlexDash
./gradlew assembleDebug
# APK → app/build/outputs/apk/debug/app-debug.apk
```

**Requirements:** JDK 17, Android SDK 34

---

## 🏷️ Releasing

Push a version tag and GitHub Actions builds and publishes the release automatically:

```bash
git tag v1.1
git push origin v1.1
```

---

## 🧪 Tests

```bash
./gradlew test
```
