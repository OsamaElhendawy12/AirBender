# Airbender — Architecture & File Map (`ARCHITECTURE.md`)

This document maps every planned package and file in plain language so the codebase stays modular and easy to navigate.

---

## 1. Planned Directory Structure (`app/src/main/java/com/airbender/gestures/`)

```text
com/airbender/gestures/
├── MainActivity.kt                  # Single-activity Compose host + permission requests
├── AirbenderApp.kt                  # Application class + dependency/state container
│
├── ui/
│   ├── theme/
│   │   ├── ThemeTokens.kt           # 5 visual themes (Light/Dark colors, radius, blur, fonts)
│   │   └── AirbenderTheme.kt        # Material 3 + custom theme CompositionLocal
│   ├── components/
│   │   ├── FloatingDock.kt          # Phone bottom dock with gliding indicator
│   │   ├── TabletTopBar.kt          # Tablet top pill switcher + quick engine toggle
│   │   ├── SpringSwitch.kt          # Custom spring-animated toggle switch
│   │   └── HandSkeletonOverlay.kt   # Canvas drawing 21 MediaPipe hand landmarks
│   └── screens/
│       ├── ControlScreen.kt         # Hero toggle, permissions, stats, live viewfinder
│       ├── GesturesScreen.kt        # Gesture-to-action mapping list
│       └── TuningScreen.kt          # Sensitivity sliders, power toggles, app whitelist
│
├── vision/
│   ├── CameraPipeline.kt            # CameraX lifecycle, ImageAnalysis (480p), ImageCapture
│   ├── HandTracker.kt               # MediaPipe HandLandmarker + GestureRecognizer wrapper
│   ├── SwipeStateMachine.kt         # Sliding-window velocity detector, Palm Gate, cooldowns
│   └── FrameThrottler.kt            # Dynamic 12 FPS (idle) ↔ 24 FPS (active) rate limiter
│
├── service/
│   ├── AirbenderForegroundService.kt # Keeps camera alive (foregroundServiceType="camera")
│   ├── AirbenderAccessibilityService.kt # Executes dispatchGesture() & reads foreground app
│   ├── AirbenderTileService.kt      # Android Quick Settings tile for 1-tap start/stop
│   └── FloatingHudOverlay.kt        # Draggable Glassmorphism status pill over other apps
│
└── data/
    ├── AirbenderPrefs.kt            # DataStore persistence for theme, tuning, mappings
    ├── AppProfiles.kt               # Built-in presets (Feed, Reader/PDF, Gallery, Media)
    └── InstalledAppsRepository.kt   # Queries PackageManager for app icons & launch intents
```

---

## 2. Data & Control Flow

1. **Camera Frame:** `CameraPipeline` delivers `640×480` `ImageProxy` frames (`STRATEGY_KEEP_ONLY_LATEST`) through `FrameThrottler` to `HandTracker`.
2. **Inference:** `HandTracker` extracts 21 3D landmarks and static pose classifications (with front-camera horizontal mirroring applied).
3. **Decision:** `SwipeStateMachine` checks the Palm Gate (if enabled), tracks wrist/index velocity over `~200 ms`, enforces cooldown, and emits a clean `GestureEvent`.
4. **Execution:**
   * *Scroll / Swipe / Back* → `AirbenderAccessibilityService.dispatchGesture(...)`
   * *Take Photo* → `CameraPipeline.takePicture()` (saves via `MediaStore`)
   * *Open App* → `startActivity(launchIntent)` for the mapped package
   * *Media Control* → `AudioManager.dispatchMediaKeyEvent(...)`
5. **Feedback:** `FloatingHudOverlay` updates its status dot and brief action label in real time.

---

## 3. Required Manifest Permissions

* `android.permission.CAMERA` (runtime)
* `android.permission.FOREGROUND_SERVICE` & `android.permission.FOREGROUND_SERVICE_CAMERA`
* `android.permission.SYSTEM_ALERT_WINDOW` (for the draggable Floating HUD pill)
* `android.permission.QUERY_ALL_PACKAGES` (to list installed apps in the whitelist/shortcut picker)
* `android.permission.POST_NOTIFICATIONS` (for the foreground service notification on Android 13+)
* `BIND_ACCESSIBILITY_SERVICE` (via `accessibility_service_config.xml` with `android:canPerformGestures="true"`)
