# Airbender — Changelog (`CHANGELOG.md`)

All notable milestones and changes will be logged here in plain language after each verified vertical slice.

---

## [0.1.0] — Slice 1: Project Skeleton, 5-Theme UI & CI Build (2026-11-16)

### Added
* **Gradle & CI Setup:** Configured Kotlin `2.0.21`, AGP `8.5.2`, Jetpack Compose BOM `2024.09.03`, and `.github/workflows/build.yml` (using `gradle/actions/setup-gradle@v4` with Gradle `8.9`) to build `app-debug.apk` automatically on push.
* **5-Theme Design System (`ThemeTokens.kt`, `AirbenderTheme.kt`):** Implemented Light and Dark modes for *Air Breeze*, *Nothing Dot-Matrix*, *Calm Organic*, *Studio Grid*, and *Aurora Indigo* with theme-specific backgrounds, card borders, corner radii, and typography.
* **3-Tab Animated Shell (`MainActivity.kt`, `FloatingDock.kt`, `TabletTopBar.kt`):** Built `HorizontalPager` sliding navigation with an iOS-style floating dock on phones and a top pill bar + 2-column layout on tablets.
* **Interactive UI Screens (`ControlScreen.kt`, `GesturesScreen.kt`, `TuningScreen.kt`, `SpringSwitch.kt`):** Added spring-animated switches, gesture action cycling, sensitivity sliders, theme/mode picker, and app whitelist chips.
