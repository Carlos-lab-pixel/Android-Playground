package com.example.ui.motion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.R
import kotlinx.coroutines.launch

@Composable
fun MotionScreen(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()

    // Spring spec controls
    var dampingRatio by remember { mutableFloatStateOf(Spring.DampingRatioMediumBouncy) }
    var stiffness by remember { mutableFloatStateOf(Spring.StiffnessMedium) }
    var springTriggered by remember { mutableStateOf(false) }

    // Easing curve controls
    var selectedEasingName by remember { mutableStateOf("FastOutSlowIn") }
    var easingProgress by remember { mutableFloatStateOf(0f) }

    // Visibility demo
    var isVisible by remember { mutableStateOf(true) }

    // Content size demo
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Header
        Column {
            Text(
                text = stringResource(R.string.motion_title),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.motion_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // 1. Spring Physics Playground
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text(
                    text = stringResource(R.string.motion_spring_title),
                    style = MaterialTheme.typography.titleMedium
                )

                // Spring Visual Stage
                val targetOffsetX = if (springTriggered) 200.dp else 0.dp
                val animatedOffsetX by animateDpAsState(
                    targetValue = targetOffsetX,
                    animationSpec = spring(dampingRatio = dampingRatio, stiffness = stiffness),
                    label = "springOffset"
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                        .padding(16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .offset(x = animatedOffsetX)
                            .size(64.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.Speed, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                    }
                }

                // Damping presets
                Column {
                    Text(
                        text = "Damping Ratio: ${String.format("%.2f", dampingRatio)} (${
                            when {
                                dampingRatio <= 0.25f -> "High Bouncy"
                                dampingRatio <= 0.6f -> "Medium Bouncy"
                                dampingRatio <= 0.85f -> "Low Bouncy"
                                else -> "No Bouncy"
                            }
                        })",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 4.dp)) {
                        FilterChip(
                            selected = dampingRatio == Spring.DampingRatioHighBouncy,
                            onClick = { dampingRatio = Spring.DampingRatioHighBouncy },
                            label = { Text("High") }
                        )
                        FilterChip(
                            selected = dampingRatio == Spring.DampingRatioMediumBouncy,
                            onClick = { dampingRatio = Spring.DampingRatioMediumBouncy },
                            label = { Text("Medium") }
                        )
                        FilterChip(
                            selected = dampingRatio == Spring.DampingRatioLowBouncy,
                            onClick = { dampingRatio = Spring.DampingRatioLowBouncy },
                            label = { Text("Low") }
                        )
                        FilterChip(
                            selected = dampingRatio == Spring.DampingRatioNoBouncy,
                            onClick = { dampingRatio = Spring.DampingRatioNoBouncy },
                            label = { Text("No Bouncy") }
                        )
                    }
                }

                // Stiffness slider
                Column {
                    Text(
                        text = "Stiffness: ${stiffness.toInt()} (${
                            when {
                                stiffness >= 1200f -> "High"
                                stiffness >= 600f -> "Medium"
                                stiffness >= 200f -> "Low"
                                else -> "Very Low"
                            }
                        })",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Slider(
                        value = stiffness,
                        onValueChange = { stiffness = it },
                        valueRange = 50f..1500f
                    )
                }

                Button(
                    onClick = { springTriggered = !springTriggered },
                    modifier = Modifier.fillMaxWidth().testTag("trigger_spring_btn")
                ) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(if (springTriggered) "Spring Return" else "Trigger Spring Motion")
                }
            }
        }

        // 2. AnimatedVisibility
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = stringResource(R.string.motion_visibility_title),
                    style = MaterialTheme.typography.titleMedium
                )

                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn(animationSpec = tween(400)) + expandVertically() + slideInVertically { -it / 2 },
                    exit = fadeOut(animationSpec = tween(300)) + shrinkVertically() + slideOutVertically { -it / 2 }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(MaterialTheme.colorScheme.tertiaryContainer)
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Expressive Entrance & Exit Animation",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }

                OutlinedButton(
                    onClick = { isVisible = !isVisible },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isVisible) "Hide Component" else "Reveal Component")
                }
            }
        }

        // 3. animateContentSize Container
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(spring(dampingRatio = 0.7f, stiffness = 500f))
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.motion_size_title),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Tap this card to smoothly expand and collapse surface bounds without layout glitches.",
                    style = MaterialTheme.typography.bodyMedium
                )

                if (isExpanded) {
                    Spacer(Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Material 3 Expressive spring physics ensure container expansions feel fluid and organic rather than rigid linear jumps.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isExpanded) "Collapse Card" else "Expand Card")
                }
            }
        }

        // 4. Official AndroidX Graphics Shapes Morphing Laboratory
        val localHaptic = androidx.compose.ui.platform.LocalHapticFeedback.current
        var shapePairIndex by remember { mutableIntStateOf(0) }
        var manualMorphProgress by remember { mutableFloatStateOf(0f) }
        var isAutoMorphing by remember { mutableStateOf(false) }

        val starShape = remember { ExpressiveShapeFactory.create4PointStar() }
        val star5Shape = remember { ExpressiveShapeFactory.create5PointStar() }
        val cloverShape = remember { ExpressiveShapeFactory.createFlowerClover(6) }
        val pillShape = remember { ExpressiveShapeFactory.createPill() }
        val circleShape = remember { ExpressiveShapeFactory.createCircle(16) }
        val scallopShape = remember { ExpressiveShapeFactory.createScallop(12) }

        val shapeOptions = listOf(
            "Star (4pt)" to starShape,
            "Star (5pt)" to star5Shape,
            "Clover (6pt)" to cloverShape,
            "Pill" to pillShape,
            "Circle" to circleShape,
            "Scallop" to scallopShape
        )

        var startShapeIdx by remember { mutableIntStateOf(0) }
        var targetShapeIdx by remember { mutableIntStateOf(2) }

        val officialMorph = remember(startShapeIdx, targetShapeIdx) {
            androidx.graphics.shapes.Morph(shapeOptions[startShapeIdx].second, shapeOptions[targetShapeIdx].second)
        }

        val animatedProgress by animateFloatAsState(
            targetValue = if (isAutoMorphing) 1f else manualMorphProgress,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "official_morph_spring"
        )

        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Official AndroidX Shape Morphing",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                        Text(
                            text = "Powered by androidx.graphics.shapes (Morph & RoundedPolygon)",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Interactive Canvas Stage
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                        .clickable {
                            localHaptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                            isAutoMorphing = !isAutoMorphing
                            if (!isAutoMorphing) {
                                manualMorphProgress = if (manualMorphProgress > 0.5f) 0f else 1f
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    MorphingShapeCanvas(
                        morph = officialMorph,
                        progress = animatedProgress,
                        color = MaterialTheme.colorScheme.primary,
                        strokeColor = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(140.dp)
                    )

                    Text(
                        text = "Tap canvas to toggle spring morph",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp)
                    )
                }

                // Start shape selector
                Column {
                    Text(
                        text = "Start Shape: ${shapeOptions[startShapeIdx].first}",
                        style = MaterialTheme.typography.labelMedium
                    )
                    androidx.compose.foundation.lazy.LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        items(shapeOptions.size) { idx ->
                            FilterChip(
                                selected = startShapeIdx == idx,
                                onClick = {
                                    startShapeIdx = idx
                                    localHaptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                                },
                                label = { Text(shapeOptions[idx].first) }
                            )
                        }
                    }
                }

                // Target shape selector
                Column {
                    Text(
                        text = "Target Shape: ${shapeOptions[targetShapeIdx].first}",
                        style = MaterialTheme.typography.labelMedium
                    )
                    androidx.compose.foundation.lazy.LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        items(shapeOptions.size) { idx ->
                            FilterChip(
                                selected = targetShapeIdx == idx,
                                onClick = {
                                    targetShapeIdx = idx
                                    localHaptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                                },
                                label = { Text(shapeOptions[idx].first) }
                            )
                        }
                    }
                }

                // Scrub slider
                Column {
                    Text(
                        text = "Morph Progress: ${(animatedProgress * 100).toInt()}%",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Slider(
                        value = if (isAutoMorphing) animatedProgress else manualMorphProgress,
                        onValueChange = {
                            isAutoMorphing = false
                            manualMorphProgress = it
                        },
                        valueRange = 0f..1f
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = {
                            localHaptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                            isAutoMorphing = !isAutoMorphing
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (isAutoMorphing) "Stop Animation" else "Play Spring Morph")
                    }

                    OutlinedButton(
                        onClick = {
                            localHaptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                            // Swap start and target shapes
                            val temp = startShapeIdx
                            startShapeIdx = targetShapeIdx
                            targetShapeIdx = temp
                        }
                    ) {
                        Text("Swap")
                    }
                }
            }
        }

        Spacer(Modifier.height(32.dp))
    }
}
