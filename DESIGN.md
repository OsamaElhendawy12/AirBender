# Airbender — UI/UX & Theme Design Guide (`DESIGN.md`)

**Package ID:** `com.airbender.gestures`  
**Target Devices:** Honor 90 (Phone, portrait) & Honor Pad 10 (Tablet, landscape/portrait)  
**Design System:** Material 3 + Jetpack Compose with custom theme tokens

---

## 1. Visual Identity & Theme System

Airbender supports **Light** and **Dark** modes across **5 selectable visual themes** (defaulting to **Air Breeze**):

| Theme | Light BG / Card / Accent | Dark BG / Card / Accent | Surface & Typography Feel |
| :--- | :--- | :--- | :--- |
| **Air Breeze** *(Default)* | `#EEF4FF` / `rgba(255,255,255,0.78)` / `#2563EB` | `#0B1120` / `rgba(22,31,51,0.72)` / `#38BDF8` | Frosted glass cards (`14dp` blur), `24dp` radius, soft radial sky glow |
| **Nothing Dot-Matrix** | `#F4F4F5` / `#FFFFFF` / `#DC2626` | `#09090B` / `#141417` / `#FF3B30` | Subtle dot-matrix canvas, `1dp` hairline borders, `20dp` radius, monospace labels |
| **Calm Organic** | `#F1F7F3` / `#FFFFFF` / `#059669` | `#0B1410` / `#13211B` / `#34D399` | Soft sage/mint gradient wash, shadowless tonal cards, `28dp` pill-like radius |
| **Studio Grid** | `#F5F2EB` / `#FBF9F5` / `#D97706` | `#121418` / `#1A1D23` / `#F59E0B` | Subtle `24dp` grid canvas, crisp `14dp` corners, tactile inset panels |
| **Aurora Indigo** | `#F4F5FC` / `rgba(255,255,255,0.85)` / `#4F46E5` | `#0E0F1C` / `rgba(24,26,44,0.78)` / `#818CF8` | Ambient indigo-violet glow, elevated glossy cards, `22dp` radius |

---

## 2. Navigation & Responsive Layout

* **Phone (Portrait):**
  * **iOS-Inspired Floating Bottom Dock** (`Control`, `Gestures`, `Tuning`) with a translucent blurred pill background and a gliding active-tab indicator.
  * **Horizontal ViewPager (`HorizontalPager`):** Smoothly slides between the 3 screens (`380ms` cubic-bezier / spring curve).
* **Tablet (Landscape / Honor Pad 10):**
  * **Top Pill Switcher Bar** (`Control | Gestures | Tuning`) paired with a quick **Engine: ON / PAUSED** pill on the right.
  * **2-Column Split Layouts:** Control puts status + stats on the left and the dark live camera viewfinder on the right; Gestures and Tuning use balanced 2-column grids.

---

## 3. Core Screens

1. **Control (Home):**
   * **Hero Service Card:** One-tap Start/Pause toggle + 3 compact permission status pills (`Camera`, `Accessibility`, `Battery Exempt`).
   * **Active Profile Summary:** Shows active gesture count, cooldown (`ms`), and Palm Gate status.
   * **Live Viewfinder Card:** High-contrast dark card showing the `480p` front-camera feed, 21-point MediaPipe hand-landmark skeleton overlay, and live detected pose label.
2. **Gestures:**
   * Clean rounded rows with gesture icon, gesture name, tappable target-action pill (e.g., `→ Scroll Down`, `→ Open OmniNote`), and spring-animated toggle switch.
3. **Tuning:**
   * **Sensitivity & Timing:** Sliders for Swipe Velocity Threshold (`%`), Cooldown (`ms`), and Palm Arming Hold (`ms`).
   * **Behavior & Power:** Toggles for Arm-to-Swipe Palm Gate, Front-Camera Mirroring, Smart FPS Throttling (`12 → 24 FPS`), and Glassmorphism Floating Pill.
   * **Active App Whitelist & Profiles:** Quick-toggle chips + installed app picker.

---

## 4. Floating HUD Overlay (`Glassmorphism Pill`)

* Draggable translucent pill (`TYPE_ACCESSIBILITY_OVERLAY` or `SYSTEM_ALERT_WINDOW`) that stays visible over other apps while Airbender runs:
  * **Gray Dot:** Watching (Idle)
  * **Theme Accent Dot:** Armed (Open Palm detected)
  * **Emerald Dot:** Gesture Fired (shows action name briefly, e.g., `Scroll Down`)
  * **Tap:** Quick Pause / Resume without leaving the current app.
