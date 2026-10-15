package com.example.ui.motion

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Cubic
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.circle
import androidx.graphics.shapes.rectangle
import androidx.graphics.shapes.star

/**
 * Creates curated expressive shapes and handles smooth morphing between them
 * using official AndroidX Graphics Shapes library.
 */
object ExpressiveShapeFactory {

    fun create4PointStar(): RoundedPolygon {
        return RoundedPolygon.star(
            numVerticesPerRadius = 4,
            radius = 1f,
            innerRadius = 0.45f,
            rounding = CornerRounding(0.2f),
            innerRounding = CornerRounding(0.15f)
        )
    }

    fun createCircle(): RoundedPolygon {
        return RoundedPolygon.circle(
            numVertices = 12,
            radius = 1f
        )
    }

    fun createSoftSquare(): RoundedPolygon {
        return RoundedPolygon.rectangle(
            width = 1.8f,
            height = 1.8f,
            rounding = CornerRounding(0.35f)
        )
    }

    fun createPill(): RoundedPolygon {
        return RoundedPolygon.rectangle(
            width = 2.0f,
            height = 1.2f,
            rounding = CornerRounding(0.6f)
        )
    }

    fun createFlowerClover(): RoundedPolygon {
        return RoundedPolygon.star(
            numVerticesPerRadius = 6,
            radius = 1f,
            innerRadius = 0.65f,
            rounding = CornerRounding(0.4f),
            innerRounding = CornerRounding(0.25f)
        )
    }
}

/**
 * Converts a Morph at a given progress (0f..1f) into a Compose Path,
 * scaled and centered to bounds [width, height].
 */
fun Morph.toScaledComposePath(
    progress: Float,
    targetWidth: Float,
    targetHeight: Float,
    scaleFactor: Float = 0.42f
): Path {
    val path = Path()
    var isFirst = true

    val centerX = targetWidth / 2f
    val centerY = targetHeight / 2f
    val baseScale = minOf(targetWidth, targetHeight) * scaleFactor

    val cubics: List<Cubic> = this.asCubics(progress)
    for (cubic in cubics) {
        val x0 = centerX + cubic.anchor0X * baseScale
        val y0 = centerY + cubic.anchor0Y * baseScale
        val cx0 = centerX + cubic.control0X * baseScale
        val cy0 = centerY + cubic.control0Y * baseScale
        val cx1 = centerX + cubic.control1X * baseScale
        val cy1 = centerY + cubic.control1Y * baseScale
        val x1 = centerX + cubic.anchor1X * baseScale
        val y1 = centerY + cubic.anchor1Y * baseScale

        if (isFirst) {
            path.moveTo(x0, y0)
            isFirst = false
        }
        path.cubicTo(cx0, cy0, cx1, cy1, x1, y1)
    }
    path.close()
    return path
}

@Composable
fun MorphingShapeCanvas(
    morph: Morph,
    progress: Float,
    color: Color,
    strokeColor: Color? = null,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val path = morph.toScaledComposePath(progress, size.width, size.height)
        drawPath(path, color = color, style = Fill)
        if (strokeColor != null) {
            drawPath(path, color = strokeColor, style = Stroke(width = 3f))
        }
    }
}
