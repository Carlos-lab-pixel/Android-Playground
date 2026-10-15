package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.circle
import androidx.graphics.shapes.rectangle
import androidx.graphics.shapes.star
import com.example.ui.motion.MorphingShapeCanvas

/**
 * Material 3 Expressive Loading Indicator (Guidelines Section 10).
 * Loops through a shape-morph sequence of 7 unique M3 shapes for short waits (200ms - 5s).
 * Replaces the conventional circular progress indicator for indeterminate operations.
 */
@Composable
fun ExpressiveLoadingIndicator(
    modifier: Modifier = Modifier,
    isContained: Boolean = false,
    size: Dp = 48.dp,
    color: Color = if (isContained) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    // 7 unique Material 3 shapes
    val shapes = remember {
        listOf(
            RoundedPolygon.star(numVerticesPerRadius = 4, radius = 1f, innerRadius = 0.45f, rounding = CornerRounding(0.25f)),
            RoundedPolygon.star(numVerticesPerRadius = 6, radius = 1f, innerRadius = 0.65f, rounding = CornerRounding(0.35f)),
            RoundedPolygon.rectangle(width = 1.9f, height = 1.1f, rounding = CornerRounding(0.55f)),
            RoundedPolygon.circle(numVertices = 16, radius = 1f),
            RoundedPolygon.star(numVerticesPerRadius = 8, radius = 1f, innerRadius = 0.7f, rounding = CornerRounding(0.2f)),
            RoundedPolygon.rectangle(width = 1.7f, height = 1.7f, rounding = CornerRounding(0.35f)),
            RoundedPolygon.star(numVerticesPerRadius = 5, radius = 1f, innerRadius = 0.5f, rounding = CornerRounding(0.3f))
        )
    }

    val morphs = remember {
        List(shapes.size) { i ->
            Morph(shapes[i], shapes[(i + 1) % shapes.size])
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "expressive_loading")
    val totalProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = morphs.size.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 650 * morphs.size, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "morph_step_progress"
    )

    val currentMorphIndex = (totalProgress.toInt()) % morphs.size
    val currentMorphFraction = totalProgress - totalProgress.toInt()
    val activeMorph = morphs[currentMorphIndex]

    if (isContained) {
        Box(
            modifier = modifier
                .size(size)
                .clip(CircleShape)
                .background(containerColor),
            contentAlignment = Alignment.Center
        ) {
            MorphingShapeCanvas(
                morph = activeMorph,
                progress = currentMorphFraction,
                color = color,
                modifier = Modifier.size(size * 0.72f)
            )
        }
    } else {
        Box(
            modifier = modifier.size(size),
            contentAlignment = Alignment.Center
        ) {
            MorphingShapeCanvas(
                morph = activeMorph,
                progress = currentMorphFraction,
                color = color,
                modifier = Modifier.size(size)
            )
        }
    }
}
