package com.example.platform.haptics

import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationAttributes
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import com.example.model.CustomWaveformConfig
import com.example.model.HapticCapabilityInfo

interface HapticsService {
    fun getCapabilities(): HapticCapabilityInfo
    fun playPredefined(effectId: Int, fallbackDurationMs: Long = 50): Boolean
    fun playPrimitive(primitiveId: Int, fallbackDurationMs: Long = 40): Boolean
    fun playCustomWaveform(config: CustomWaveformConfig, intensityMultiplier: Float): Boolean
    fun cancel()
}

class HapticsManager(private val context: Context) : HapticsService {

    private val vibrator: Vibrator? by lazy {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                manager?.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            }
        } catch (_: Exception) {
            null
        }
    }

    override fun getCapabilities(): HapticCapabilityInfo {
        val vib = vibrator
        val hasVib = vib?.hasVibrator() == true
        val hasAmp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vib?.hasAmplitudeControl() == true
        } else false

        val hasPrimitives = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && vib != null) {
            try {
                vib.areAllPrimitivesSupported(
                    VibrationEffect.Composition.PRIMITIVE_CLICK,
                    VibrationEffect.Composition.PRIMITIVE_TICK
                )
            } catch (_: Exception) {
                false
            }
        } else false

        return HapticCapabilityInfo(
            hasVibrator = hasVib,
            hasAmplitudeControl = hasAmp,
            hasPrimitivesSupport = hasPrimitives,
            sdkInt = Build.VERSION.SDK_INT
        )
    }

    override fun playPredefined(effectId: Int, fallbackDurationMs: Long): Boolean {
        val vib = vibrator ?: return false
        if (!vib.hasVibrator()) return false

        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val effect = VibrationEffect.createPredefined(effectId)
                vib.vibrate(effect)
                true
            } else {
                @Suppress("DEPRECATION")
                vib.vibrate(fallbackDurationMs.coerceIn(10L, 5000L))
                true
            }
        } catch (_: Exception) {
            false
        }
    }

    override fun playPrimitive(primitiveId: Int, fallbackDurationMs: Long): Boolean {
        val vib = vibrator ?: return false
        if (!vib.hasVibrator()) return false

        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val composition = VibrationEffect.startComposition()
                    .addPrimitive(primitiveId, 1.0f)
                    .compose()
                vib.vibrate(composition)
                true
            } else {
                playPredefined(VibrationEffect.EFFECT_CLICK, fallbackDurationMs)
            }
        } catch (_: Exception) {
            false
        }
    }

    override fun playCustomWaveform(config: CustomWaveformConfig, intensityMultiplier: Float): Boolean {
        val vib = vibrator ?: return false
        if (!vib.hasVibrator()) return false

        val pulses = config.pulses.coerceAtLeast(1)
        val duration = config.durationMs.coerceAtLeast(10).toLong()
        val gap = config.gapMs.coerceAtLeast(0).toLong()
        val baseAmp = (config.amplitude * intensityMultiplier).toInt().coerceIn(1, 255)

        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val timings = mutableListOf<Long>()
                val amplitudes = mutableListOf<Int>()

                // Initial delay
                timings.add(0L)
                amplitudes.add(0)

                for (i in 0 until pulses) {
                    timings.add(duration)
                    amplitudes.add(baseAmp)
                    if (i < pulses - 1) {
                        timings.add(gap)
                        amplitudes.add(0)
                    }
                }

                val effect = VibrationEffect.createWaveform(
                    timings.toLongArray(),
                    amplitudes.toIntArray(),
                    -1 // No repeat
                )
                vib.vibrate(effect)
                true
            } else {
                @Suppress("DEPRECATION")
                vib.vibrate(duration * pulses)
                true
            }
        } catch (_: Exception) {
            false
        }
    }

    override fun cancel() {
        try {
            vibrator?.cancel()
        } catch (_: Exception) {
            // Ignore
        }
    }
}
