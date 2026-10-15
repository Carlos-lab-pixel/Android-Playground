# Material Playground: Material 3 Expressive Laboratory

An interactive native Android laboratory for exploring Material 3 Expressive components, motion physics, tactile haptic feedback, and dynamic theming in real time.

## User Review & Critical Decisions

> [!IMPORTANT]
> The following product and design decisions were confirmed during the interactive interview and will guide the implementation:
> - **Showcase Theme**: Expressive vibrant palette with dynamic seed color support (tonal color harmonization and high-contrast accessibility).
> - **Home Screen Focus**: Featured interactive showcase highlighting key expressive components with quick 1-tap jump points to all dedicated labs (Components, Haptics, Motion, Theme Studio).
> - **Haptic Safety & Testing**: Interactive test-only mode with explicit visual wave previews, bounded duration limits (maximum 1000ms), and automated hardware capability detection with graceful fallbacks.

---

### 1. Overview & Core Concept

- **What It Does**: Material Playground is a native Android application built with Jetpack Compose that acts as an interactive design and engineering workshop. It lets developers and designers inspect and manipulate Material 3 Expressive UI components, test hardware haptic primitives, explore spring physics and animation curves, customize app-wide color schemes and shapes, and inspect copy-ready Kotlin Compose code.
- **Target Audience / Persona**: Android engineers, UX/UI designers, and mobile enthusiasts who want to understand, test, and adapt Material 3 Expressive patterns on real Android hardware.
- **Key Value**: Replaces static documentation with an interactive sandbox where every component attribute, motion spec, and haptic vibration can be felt, adjusted, tested, and copied directly into production apps.

---

### 2. User Experience & Visual Design

#### Key User Flows
1. **Home Experience**: Users land on an expressive dashboard featuring an interactive Hero Playground (e.g. an expressive expandable action sheet or dynamic morphing card), quick-launch chips for the 4 core labs, recently inspected components, and a real-time hardware capability badge (Vibrator, Amplitude control, API level).
2. **Component Catalog & Interactive Playground**:
   - Browse components organized into intuitive categories (Actions, Selection, Text Input, Navigation, Communication, Containment & Layout, Pickers, Expressive).
   - Instant search and category filtering with pill chips.
   - Reusable Playground Screen: Top interactive live preview responding to touches, followed by interactive control sections (Appearance, Behavior, State Inspector, Accessibility info, and a copyable Kotlin Code Snippet).
   - Reset button restoring defaults in 1 tap.
3. **Haptics Lab**:
   - Hardware capability inspector (Vibrator presence, amplitude control, predefined effects support, primitive composition support).
   - Interaction feedback explorer (button clicks, toggle switches, slider ticks, confirm, error, drag-drop).
   - Named effect collection (Soft Click, Crisp Tick, Double Click, Firm Confirmation, Success Pattern, Rejection Pulse).
   - Custom Haptic Designer: Visual timeline preview, frequency/amplitude controls, duration sliders with strict safety caps, and test playback.
4. **Motion Lab**:
   - Spring physics playground: Interactive controls for Damping Ratio (High, Medium, Low, No Bouncy) and Stiffness (High, Medium, Low, Very Low).
   - Transition & Easing curves visualizer (FastOutSlowIn, LinearOutSlowIn, Emphasized, Linear).
   - AnimatedVisibility and animateContentSize interactive demos.
5. **Theme Studio**:
   - Live theme toggle (System, Light, Dark).
   - Dynamic colors toggle (Material You on supported devices).
   - Seed color picker with instant app-wide palette generation.
   - Corner shape scale selector (None, Small, Medium, Large, Extra Large / Expressive).
6. **Settings & Localization**:
   - Language selector: English & German (`res/values` and `res/values-de`).
   - App haptic master toggle & motion intensity reduction toggle.

#### Visual Identity & Theme
- **Aesthetic**: Expressive, playful, and tactile. Utilizes large confident headlines, varied corner treatments, generous spacing, and smooth spring-based micro-interactions.
- **Color System**: Material 3 M3 semantic color scheme (`primary`, `onPrimary`, `surfaceContainer`, `secondaryContainer`, `outlineVariant`) dynamically adapting to seed color choices.
- **Layout & Density**: Adaptive layout using WindowSizeClass awareness (Bottom NavigationBar for compact phones; NavigationRail ready for tablets/foldables). Proper edge-to-edge support with safe drawing insets.

---

### 3. Key Product Decisions & Trade-Offs

- **Architecture (MVVM + Unidirectional Data Flow)**:
  - *Chosen Approach*: Clean architecture with ViewModels managing immutable UI state via `StateFlow`, single activity, Compose Navigation with type-safe route keys.
  - *Why*: Ensures state persistence across screen rotations, clear separation of platform APIs from UI, and easy unit testing.
- **Persistence (DataStore Preferences)**:
  - *Chosen Approach*: AndroidX DataStore Preferences for storing theme choices, dynamic color toggles, haptic settings, language, and recently viewed components.
  - *Why*: Lightweight, modern, coroutine-backed, and zero-overhead compared to Room or cloud databases for local user preferences.
- **Haptic Hardware Abstraction (`HapticsManager`)**:
  - *Chosen Approach*: An abstraction layer over `android.os.Vibrator` / `VibratorManager` and Compose's `LocalHapticFeedback`. Checks API level (API 29+, 30+ for Primitives, 31+ for VibratorManager) and feature support before triggering vibrations.
  - *Why*: Guarantees zero crashes on emulators or devices without haptic motors, while unleashing rich compositions on modern Pixel and flagship devices.
- **Dynamic Code Inspector**:
  - *Chosen Approach*: Pure Kotlin code generation engine that reflects real-time playground parameter changes into syntax-highlighted, clean Jetpack Compose snippets with a one-tap copy button.

---

### 4. Technical Architecture & Data Strategy

```
┌────────────────────────────────────────────────────────────────────────┐
│                          MainActivity                                  │
│                 (Edge-to-Edge, Theme, Localization)                    │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
                  ┌─────────────────┴─────────────────┐
                  ▼                                   ▼
       ┌──────────────────────┐             ┌─────────────────────┐
       │   App Navigation     │             │    Theme Studio     │
       │ (Compose Navigation) │             │ (Dynamic M3 Theme)  │
       └──────────┬───────────┘             └──────────┬──────────┘
                  │                                    │
  ┌───────────────┼───────────────┬────────────────────┼──────────────┐
  ▼               ▼               ▼                    ▼              ▼
┌───────────┐ ┌───────────┐ ┌───────────┐        ┌───────────┐  ┌───────────┐
│   Home    │ │Component  │ │  Haptics  │        │  Motion   │  │ Settings  │
│ Dashboard │ │ Catalog & │ │    Lab    │        │    Lab    │  │& App Info │
│           │ │Playground │ │           │        │           │  │           │
└─────┬─────┘ └─────┬─────┘ └─────┬─────┘        └─────┬─────┘  └─────┬─────┘
      │             │             │                    │              │
      ▼             ▼             ▼                    ▼              ▼
┌──────────────────────────────────────────────────────────────────────────┐
│                             ViewModels                                   │
│  (CatalogViewModel, HapticsViewModel, MotionViewModel, ThemeViewModel)   │
└───────────────────┬──────────────────────────────────┬───────────────────┘
                    │                                  │
                    ▼                                  ▼
      ┌───────────────────────────┐      ┌───────────────────────────┐
      │     DataStore Storage     │      │      HapticsManager       │
      │  (User Preferences, Seed) │      │  (Vibrator / Primitives)  │
      └───────────────────────────┘      └───────────────────────────┘
```

#### Interactive Component & State Mapping
- `CatalogViewModel`: Holds active filters, search query, categorized component definitions, current component playground state (appearance, behavior toggles, values), and produces live preview states and formatted Kotlin code.
- `HapticsViewModel`: Holds detected device hardware capabilities, active custom waveform points, test playback state, and interfaces safely with `HapticsManager`.
- `MotionViewModel`: Holds spring physics parameters (stiffness, damping), easing curve selection, and animation state triggers.
- `ThemeViewModel`: Observes user theme preferences from DataStore (dark/light, seed color, expressive shape roundness) and provides real-time `ColorScheme` and `Shapes` to `MaterialPlaygroundTheme`.
- `Robolectric Test Suite`: JVM unit and Compose UI tests validating catalog filtering, state restoration, haptic capability fallbacks, and theme switching.
