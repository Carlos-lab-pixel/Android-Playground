package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin

/**
 * Material 3 Expressive Wavy Progress Indicator (Guidelines Section 12).
 * Features animated sinusoidal wavy active track, smooth stop indicators,
 * and high-contrast color roles (Primary active, Secondary container track).
 */
@Composable
fun ExpressiveWavyLinearProgressIndicator(
    progress: Float? = null, // null for indeterminate
    modifier: Modifier = Modifier,
    height: Dp = 16.dp,
    trackThickness: Dp = 4.dp,
    waveAmplitude: Dp = 5.dp,
    waveLength: Dp = 28.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.secondaryContainer
) {
    val infiniteTransition = rememberInfiniteTransition(label = "wavy_progress")
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "wave_offset"
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        val width = size.width
        val centerY = size.height / 2f
        val thicknessPx = trackThickness.toPx()
        val amplitudePx = waveAmplitude.toPx()
        val waveLengthPx = waveLength.toPx()

        // 1. Draw Inactive Track
        drawLine(
            color = trackColor,
            start = Offset(0f, centerY),
            end = Offset(width, centerY),
            strokeWidth = thicknessPx,
            cap = StrokeCap.Round
        )

        // 2. Active Track
        val activeWidth = if (progress != null) (width * progress.coerceIn(0f, 1f)) else width
        val wavePath = Path()
        var x = 0f
        val step = 3f

        wavePath.moveTo(0f, centerY)
        while (x <= activeWidth) {
            val radians = (x / waveLengthPx) * 2f * PI.toFloat() + waveOffset
            val y = centerY + sin(radians) * amplitudePx
            wavePath.lineTo(x, y)
            x += step
        }

        drawPath(
            path = wavePath,
            color = color,
            style = Stroke(width = thicknessPx, cap = StrokeCap.Round)
        )

        // 3. Stop Indicator (4dp circle marking the end for accessibility)
        if (progress != null && progress > 0.05f) {
            val radians = (activeWidth / waveLengthPx) * 2f * PI.toFloat() + waveOffset
            val endY = centerY + sin(radians) * amplitudePx
            drawCircle(
                color = color,
                radius = thicknessPx * 1.2f,
                center = Offset(activeWidth, endY)
            )
        }
    }
}
