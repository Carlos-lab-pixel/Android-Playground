package com.example.model

enum class HapticFeedbackKind {
    PREDEFINED_EFFECT,
    PRIMITIVE_COMPOSITION,
    CUSTOM_WAVEFORM,
    COMPOSE_UI
}

data class HapticEffectItem(
    val id: String,
    val name: String,
    val description: String,
    val kind: HapticFeedbackKind,
    val apiRequirement: String,
    val durationMs: Long,
    val fallbackExplanation: String
)

data class HapticCapabilityInfo(
    val hasVibrator: Boolean,
    val hasAmplitudeControl: Boolean,
    val hasPrimitivesSupport: Boolean,
    val sdkInt: Int,
    val isSystemHapticsEnabled: Boolean = true
)

data class CustomWaveformConfig(
    val pulses: Int = 2,
    val durationMs: Int = 80,
    val gapMs: Int = 60,
    val amplitude: Int = 180
) {
    val totalDurationMs: Int
        get() = (pulses * durationMs) + ((pulses - 1).coerceAtLeast(0) * gapMs)
}
