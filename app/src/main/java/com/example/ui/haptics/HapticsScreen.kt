package com.example.ui.haptics

import android.os.Build
import android.os.VibrationEffect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.CustomWaveformConfig
import com.example.model.HapticCapabilityInfo
import com.example.platform.haptics.HapticsManager
import com.example.platform.haptics.HapticsService

@Composable
fun HapticsScreen(
    hapticsManager: HapticsService,
    modifier: Modifier = Modifier
) {
    val hapticFeedback = LocalHapticFeedback.current
    val caps: HapticCapabilityInfo = remember { hapticsManager.getCapabilities() }

    var isMasterEnabled by remember { mutableStateOf(true) }
    var intensity by remember { mutableFloatStateOf(1.0f) }

    // Custom Waveform Designer state
    var pulses by remember { mutableIntStateOf(3) }
    var pulseDurationMs by remember { mutableIntStateOf(60) }
    var pulseGapMs by remember { mutableIntStateOf(50) }
    var amplitude by remember { mutableIntStateOf(200) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Header
        Column {
            Text(
                text = stringResource(R.string.haptics_title),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.haptics_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // 1. Master Control Card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Vibration, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.haptics_master_toggle),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    com.example.ui.components.ExpressiveSwitch(
                        checked = isMasterEnabled,
                        onCheckedChange = { isMasterEnabled = it },
                        modifier = Modifier.testTag("haptics_master_switch")
                    )
                }

                Column {
                    Text(
                        text = "${stringResource(R.string.haptics_intensity)}: ${(intensity * 100).toInt()}%",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Slider(
                        value = intensity,
                        onValueChange = { intensity = it },
                        enabled = isMasterEnabled && caps.hasAmplitudeControl,
                        valueRange = 0.1f..1.0f
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = {
                            if (isMasterEnabled) {
                                hapticsManager.playPredefined(VibrationEffect.EFFECT_CLICK, 50)
                            }
                        },
                        enabled = isMasterEnabled,
                        modifier = Modifier.weight(1f).testTag("haptics_test_btn")
                    ) {
                        Icon(Icons.Filled.PlayArrow, contentDescription = null)
                        Spacer(Modifier.width(4.dp))
                        Text(stringResource(R.string.haptics_test_button))
                    }
                    OutlinedButton(
                        onClick = { hapticsManager.cancel() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Filled.Stop, contentDescription = null)
                        Spacer(Modifier.width(4.dp))
                        Text(stringResource(R.string.haptics_stop_button))
                    }
                }
            }
        }

        // 2. Hardware Capability Inspector
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Info, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.haptics_hardware_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(Modifier.height(4.dp))
                CapabilityRow(
                    label = if (caps.hasVibrator) stringResource(R.string.haptics_vibrator_present) else stringResource(R.string.haptics_vibrator_missing),
                    isSupported = caps.hasVibrator
                )
                CapabilityRow(
                    label = if (caps.hasAmplitudeControl) stringResource(R.string.haptics_amplitude_supported) else stringResource(R.string.haptics_amplitude_unsupported),
                    isSupported = caps.hasAmplitudeControl
                )
                CapabilityRow(
                    label = if (caps.hasPrimitivesSupport) stringResource(R.string.haptics_primitives_supported) else stringResource(R.string.haptics_primitives_unsupported),
                    isSupported = caps.hasPrimitivesSupport
                )
                Text(
                    text = "• Android Platform API Level: ${caps.sdkInt}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // 3. Predefined System Effects
        Text(
            text = stringResource(R.string.haptics_effects_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val effects = listOf(
                Triple("Click (EFFECT_CLICK)", "Crisp primary touch feedback", VibrationEffect.EFFECT_CLICK),
                Triple("Tick (EFFECT_TICK)", "Subtle light slider or selection tick", VibrationEffect.EFFECT_TICK),
                Triple("Double Click", "Rapid double confirmation pulse", VibrationEffect.EFFECT_DOUBLE_CLICK),
                Triple("Heavy Click", "Emphasized action or trigger click", VibrationEffect.EFFECT_HEAVY_CLICK)
            )

            effects.forEach { (name, desc, effectId) ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(name, style = MaterialTheme.typography.titleSmall)
                            Text(desc, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        FilledTonalButton(
                            onClick = {
                                if (isMasterEnabled) {
                                    hapticsManager.playPredefined(effectId)
                                }
                            },
                            enabled = isMasterEnabled
                        ) {
                            Text("Play")
                        }
                    }
                }
            }
        }

        // 4. Custom Waveform Designer
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = stringResource(R.string.haptics_designer_title),
                    style = MaterialTheme.typography.titleMedium
                )

                // Visual Waveform Canvas Preview
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(8.dp)
                ) {
                    val primaryColor = MaterialTheme.colorScheme.primary
                    val totalDuration = (pulses * pulseDurationMs) + ((pulses - 1) * pulseGapMs)
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val canvasWidth = size.width
                        val canvasHeight = size.height
                        val barMaxHeight = canvasHeight * 0.85f

                        val slotWidth = canvasWidth / pulses
                        for (i in 0 until pulses) {
                            val barHeight = (amplitude / 255f) * barMaxHeight
                            val x = i * slotWidth + (slotWidth * 0.2f)
                            val w = slotWidth * 0.6f
                            val y = canvasHeight - barHeight

                            drawRoundRect(
                                color = primaryColor,
                                topLeft = Offset(x, y),
                                size = Size(w, barHeight),
                                cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
                            )
                        }
                    }
                }

                // Controls
                Column {
                    Text("${stringResource(R.string.haptics_designer_pulses)}: $pulses", style = MaterialTheme.typography.labelMedium)
                    Slider(
                        value = pulses.toFloat(),
                        onValueChange = { pulses = it.toInt() },
                        valueRange = 1f..10f,
                        steps = 8
                    )
                }

                Column {
                    Text("${stringResource(R.string.haptics_designer_duration)}: ${pulseDurationMs}ms", style = MaterialTheme.typography.labelMedium)
                    Slider(
                        value = pulseDurationMs.toFloat(),
                        onValueChange = { pulseDurationMs = it.toInt() },
                        valueRange = 20f..3000f
                    )
                }

                Column {
                    Text("${stringResource(R.string.haptics_designer_gap)}: ${pulseGapMs}ms", style = MaterialTheme.typography.labelMedium)
                    Slider(
                        value = pulseGapMs.toFloat(),
                        onValueChange = { pulseGapMs = it.toInt() },
                        valueRange = 10f..500f
                    )
                }

                Column {
                    Text("${stringResource(R.string.haptics_designer_amplitude)}: $amplitude", style = MaterialTheme.typography.labelMedium)
                    Slider(
                        value = amplitude.toFloat(),
                        onValueChange = { amplitude = it.toInt() },
                        enabled = caps.hasAmplitudeControl,
                        valueRange = 20f..255f
                    )
                }

                Button(
                    onClick = {
                        if (isMasterEnabled) {
                            val config = CustomWaveformConfig(
                                pulses = pulses,
                                durationMs = pulseDurationMs,
                                gapMs = pulseGapMs,
                                amplitude = amplitude
                            )
                            hapticsManager.playCustomWaveform(config, intensity)
                        }
                    },
                    enabled = isMasterEnabled,
                    modifier = Modifier.fillMaxWidth().testTag("play_custom_haptic_btn")
                ) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(stringResource(R.string.haptics_designer_play))
                }
            }
        }

        Spacer(Modifier.height(32.dp))
    }
}

@Composable
fun CapabilityRow(label: String, isSupported: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = if (isSupported) Icons.Filled.CheckCircle else Icons.Filled.Warning,
            contentDescription = null,
            tint = if (isSupported) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
