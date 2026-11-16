# Airbender — Product Specification & Vertical-Slice Roadmap (`SPEC.md`)

**App Name:** Airbender  
**Package ID:** `com.airbender.gestures`  
**Distribution:** Personal sideloaded APK (built via GitHub Actions)  
**Core Promise:** 100% on-device, ad-free, zero-paywall air-gesture control for Android phones and tablets.

---

## 1. Core Capabilities

1. **Dynamic Air Swipes (`AccessibilityService.dispatchGesture`):**
   * **Swipe Up / Down:** Vertical scroll or short-form video skip.
   * **Swipe Left / Right:** Horizontal swipe, page flip, or system back.
2. **Static Pose Triggers (`MediaPipe GestureRecognizer`):**
   * **Peace Sign (✌️):** Capture a photo directly via CameraX `ImageCapture` (avoids camera hardware conflicts with the stock camera app) and save to `MediaStore`.
   * **Thumbs Up (👍) / Point Up (☝️):** Launch a user-assigned app via `PackageManager` launch `Intent`.
   * **Closed Fist (✊):** Global Media Play/Pause or Mute.
3. **Anti-Accidental Trigger & Power Engine:**
   * **Arm-to-Swipe Palm Gate:** Optional brief open-palm hold (`~350 ms`) before swipes register.
   * **Smart FPS Throttling:** `ImageAnalysis` runs at `640×480` (`~12 FPS` idle, ramping to `~24 FPS` when a hand is tracked).
   * **Cooldown Timer:** Configurable debounce (`150–1000 ms`) between consecutive swipes.
4. **Smart App Profiles & Whitelist:**
   * Detects the active foreground app via `AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED`.
   * **5 Reusable Behavior Profiles:**
     * *Vertical Feed:* YouTube, TikTok, Instagram, Instagram Lite, Facebook, Facebook Lite, Reddit.
     * *Reader / PDF (Strict Palm Gate):* NoteIn, Google Drive (PDF), Brave, Chrome, Honor Docs, Word, PowerPoint.
     * *Gallery:* Honor Gallery, Google Photos.
     * *Media:* Spotify, Netflix, VLC.
     * *Quick-Launch Targets:* Gemini, ChatGPT, Claude (plus any installed app via `QUERY_ALL_PACKAGES`).

---

## 2. Vertical-Slice Roadmap (Build Order)

Each slice is built, compiled via GitHub Actions, tested on-device (Honor 90 / Honor Pad 10), and logged in `CHANGELOG.md` before starting the next.

| Slice | Goal | Verification Criteria |
| :--- | :--- | :--- |
| **Slice 0** | **Living Docs & Guardrails** *(Current)* | `DESIGN.md`, `SPEC.md`, `ARCHITECTURE.md`, `CHANGELOG.md` locked. |
| **Slice 1** | **Project Skeleton + CI Build** | Kotlin + Compose project, 3-tab shell (`Control`, `Gestures`, `Tuning`), theme engine, GitHub Actions debug APK builds cleanly. |
| **Slice 2** | **CameraX + MediaPipe Hand Tracking** | Front camera `480p` `ImageAnalysis` + 21-point hand skeleton overlay in Control tab + FPS throttling. |
| **Slice 3** | **Swipe State Machine + Accessibility** | Wrist/index velocity tracker, Palm Gate, cooldowns, and real `AccessibilityService.dispatchGesture()` scrolling/swiping in other apps. |
| **Slice 4** | **Static Poses (Photo + App Launch)** | Peace Sign → `ImageCapture` photo to Gallery; Thumbs Up → launch assigned app; Fist → Media Play/Pause. |
| **Slice 5** | **Foreground Service, HUD & QS Tile** | `foregroundServiceType="camera"`, Quick Settings Tile, draggable Glassmorphism Floating Pill, battery-exemption guide. |
| **Slice 6** | **Smart App Profiles & Whitelist** | Installed app picker (`PackageManager`), foreground package detection, and auto-switching between Feed / Reader / Gallery / Media profiles. |
