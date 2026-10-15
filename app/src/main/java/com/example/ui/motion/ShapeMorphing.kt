package com.example.ui.motion

import android.graphics.Matrix
import android.graphics.Path as AndroidPath
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.circle
import androidx.graphics.shapes.pill
import androidx.graphics.shapes.rectangle
import androidx.graphics.shapes.star
import androidx.graphics.shapes.toPath

/**
 * Creates curated Material 3 Expressive shapes using official AndroidX Graphics Shapes library.
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

    fun create5PointStar(): RoundedPolygon {
        return RoundedPolygon.star(
            numVerticesPerRadius = 5,
            radius = 1f,
            innerRadius = 0.5f,
            rounding = CornerRounding(0.25f),
            innerRounding = CornerRounding(0.15f)
        )
    }

    fun createCircle(numVertices: Int = 16): RoundedPolygon {
        return RoundedPolygon.circle(
            numVertices = numVertices,
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

    fun createFlowerClover(petals: Int = 6): RoundedPolygon {
        return RoundedPolygon.star(
            numVerticesPerRadius = petals,
            radius = 1f,
            innerRadius = 0.65f,
            rounding = CornerRounding(0.4f),
            innerRounding = CornerRounding(0.25f)
        )
    }

    fun createScallop(scallops: Int = 12): RoundedPolygon {
        return RoundedPolygon.star(
            numVerticesPerRadius = scallops,
            radius = 1f,
            innerRadius = 0.85f,
            rounding = CornerRounding(0.35f),
            innerRounding = CornerRounding(0.25f)
        )
    }
}

/**
 * Converts a Morph at a given progress (0f..1f) into a Compose Path,
 * using the official AndroidX Graphics Shapes [toPath] extension and native Matrix transform.
 */
fun Morph.toScaledComposePath(
    progress: Float,
    targetWidth: Float,
    targetHeight: Float,
    scaleFactor: Float = 0.45f
): Path {
    val androidPath = AndroidPath()
    // Official AndroidX Graphics Shapes toPath invocation
    this.toPath(progress, androidPath)

    val matrix = Matrix()
    val baseScale = minOf(targetWidth, targetHeight) * scaleFactor
    matrix.setScale(baseScale, baseScale)
    matrix.postTranslate(targetWidth / 2f, targetHeight / 2f)
    androidPath.transform(matrix)

    return androidPath.asComposePath()
}

/**
 * Converts a static [RoundedPolygon] to a scaled Compose Path using official [toPath].
 */
fun RoundedPolygon.toScaledComposePath(
    targetWidth: Float,
    targetHeight: Float,
    scaleFactor: Float = 0.45f
): Path {
    val androidPath = AndroidPath()
    // Official AndroidX Graphics Shapes toPath invocation
    this.toPath(androidPath)

    val matrix = Matrix()
    val baseScale = minOf(targetWidth, targetHeight) * scaleFactor
    matrix.setScale(baseScale, baseScale)
    matrix.postTranslate(targetWidth / 2f, targetHeight / 2f)
    androidPath.transform(matrix)

    return androidPath.asComposePath()
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

/**
 * Official Shape implementation allowing any Composable surface or card to be clipped
 * using the official morphing polygon path.
 */
class MorphPolygonShape(
    private val morph: Morph,
    private val progress: Float,
    private val scaleFactor: Float = 0.45f
) : androidx.compose.ui.graphics.Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: androidx.compose.ui.unit.Density
    ): androidx.compose.ui.graphics.Outline {
        val path = morph.toScaledComposePath(progress, size.width, size.height, scaleFactor)
        return androidx.compose.ui.graphics.Outline.Generic(path)
    }
}
