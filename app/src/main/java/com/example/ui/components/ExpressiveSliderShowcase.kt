package com.example.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ExpressiveSliderSize(val label: String, val thickness: Dp) {
    XS("XS (16dp)", 16.dp),
    S("S (24dp)", 24.dp),
    M("M (40dp)", 40.dp),
    L("L (56dp)", 56.dp),
    XL("XL (96dp)", 96.dp)
}

/**
 * Material 3 Expressive Slider (Guidelines Section 7).
 * Features standard, stops, and centered configurations, thickness scale (XS-XL),
 * and dynamic inset icon support on M, L, XL tracks.
 */
@Composable
fun ExpressiveSlider(
    value: Float, // 0f..1f
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    size: ExpressiveSliderSize = ExpressiveSliderSize.M,
    showInsetIcon: Boolean = true,
    numStops: Int = 0, // 0 for continuous standard
    isCentered: Boolean = false,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.secondaryContainer
) {
    val haptic = LocalHapticFeedback.current
    var isPressed by remember { mutableStateOf(false) }

    // Handle width shrinks when pressed/dragged (Section 7.3)
    val handleWidth by animateDpAsState(
        targetValue = if (isPressed) 3.dp else 5.dp,
        label = "slider_handle_width"
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(size.thickness)
            .clip(RoundedCornerShape(size.thickness / 2f))
            .background(inactiveColor)
    ) {
        val totalWidthPx = constraints.maxWidth.toFloat()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(numStops, isCentered, totalWidthPx) {
                    detectTapGestures(
                        onPress = { offset ->
                            isPressed = true
                            val fraction = if (totalWidthPx > 0f) (offset.x / totalWidthPx).coerceIn(0f, 1f) else 0f
                            val finalValue = if (numStops > 1) {
                                val stepFraction = 1f / (numStops - 1)
                                kotlin.math.round(fraction / stepFraction) * stepFraction
                            } else fraction

                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onValueChange(finalValue)
                            tryAwaitRelease()
                            isPressed = false
                        }
                    )
                }
        )
        val activeWidthDp = maxWidth * value

        // 1. Active Track
        if (isCentered) {
            val centerOffset = maxWidth / 2f
            val factor = value - 0.5f
            val distance = maxWidth * kotlin.math.abs(factor)
            val start = if (factor >= 0f) centerOffset else centerOffset - distance

            Box(
                modifier = Modifier
                    .offset(x = start)
                    .width(distance)
                    .fillMaxHeight()
                    .background(activeColor)
            )
        } else {
            Box(
                modifier = Modifier
                    .width(activeWidthDp)
                    .fillMaxHeight()
                    .background(activeColor)
            )
        }

        // 2. Stops Indicators (Section 7.4)
        if (numStops > 1) {
            val stepWidth = maxWidth / (numStops - 1)
            for (i in 0 until numStops) {
                Box(
                    modifier = Modifier
                        .offset(x = stepWidth * i - 2.dp)
                        .size(4.dp)
                        .clip(CircleShape)
                        .background(
                            if (i.toFloat() / (numStops - 1) <= value) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
                            else MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.4f)
                        )
                        .align(Alignment.CenterStart)
                )
            }
        }

        // 3. Inset Icon on M, L, XL sizes (Section 7.4)
        if (showInsetIcon && size.thickness >= 40.dp && !isCentered) {
            val iconVector = when {
                value <= 0.05f -> Icons.Filled.VolumeMute
                value <= 0.5f -> Icons.Filled.VolumeDown
                else -> Icons.Filled.VolumeUp
            }

            val iconColor = if (value > 0.15f) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondaryContainer

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = iconVector,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(if (size == ExpressiveSliderSize.XL) 32.dp else 22.dp)
                )
            }
        }

        // 4. Handle Indicator
        Box(
            modifier = Modifier
                .offset(x = (maxWidth * value) - (handleWidth / 2f))
                .width(handleWidth)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface)
        )
    }
}
