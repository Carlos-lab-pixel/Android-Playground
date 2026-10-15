package com.example.ui.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.Morph
import com.example.R
import com.example.model.ComponentItem
import com.example.model.HapticCapabilityInfo
import com.example.ui.catalog.ComponentCatalogData
import com.example.ui.motion.ExpressiveShapeFactory
import com.example.ui.motion.MorphingShapeCanvas
import com.example.ui.theme.MaterialComponentShapes
import com.example.ui.theme.MaterialCornerRadius
import com.example.ui.theme.MaterialMotion
import com.example.ui.theme.MaterialSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    caps: HapticCapabilityInfo,
    recentComponentIds: List<String>,
    onNavigateToComponents: () -> Unit,
    onNavigateToHaptics: () -> Unit,
    onNavigateToMotion: () -> Unit,
    onNavigateToTheme: () -> Unit,
    onOpenSettings: () -> Unit,
    onSelectComponent: (ComponentItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current

    // Morph shapes setup
    val starShape = remember { ExpressiveShapeFactory.create4PointStar() }
    val flowerShape = remember { ExpressiveShapeFactory.createFlowerClover() }
    val pillShape = remember { ExpressiveShapeFactory.createPill() }
    val circleShape = remember { ExpressiveShapeFactory.createCircle() }

    val morphStarToFlower = remember { Morph(starShape, flowerShape) }
    val morphFlowerToPill = remember { Morph(flowerShape, pillShape) }
    val morphPillToCircle = remember { Morph(pillShape, circleShape) }

    var featuredShapeIndex by remember { mutableIntStateOf(0) }
    val morphProgress = remember { Animatable(0f) }

    LaunchedEffect(featuredShapeIndex) {
        morphProgress.snapTo(0f)
        morphProgress.animateTo(
            targetValue = 1f,
            animationSpec = spring(dampingRatio = 0.68f, stiffness = 450f)
        )
    }

    val currentMorph = when (featuredShapeIndex % 3) {
        0 -> morphStarToFlower
        1 -> morphFlowerToPill
        else -> morphPillToCircle
    }

    val shapeName = when (featuredShapeIndex % 3) {
        0 -> "4-Point Star → Expressive Clover"
        1 -> "Expressive Clover → Tactile Pill"
        else -> "Tactile Pill → Pure Geometry"
    }

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = onOpenSettings,
                    modifier = Modifier.testTag("home_settings_button")
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(R.string.settings_action)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = MaterialSpacing.space200)
                .padding(bottom = 96.dp),
            verticalArrangement = Arrangement.spacedBy(MaterialSpacing.space250)
        ) {
            // 1. Hero Section
            Surface(
                shape = RoundedCornerShape(MaterialCornerRadius.ExtraExtraLarge),
                color = MaterialTheme.colorScheme.surfaceContainer,
                tonalElevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("home_hero_surface")
            ) {
                Column(
                    modifier = Modifier.padding(MaterialSpacing.space300),
                    verticalArrangement = Arrangement.spacedBy(MaterialSpacing.space150)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Filled.AutoAwesome,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(MaterialCornerRadius.Small),
                            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f),
                            modifier = Modifier.padding(horizontal = MaterialSpacing.space100, vertical = MaterialSpacing.space50)
                        ) {
                            Text(
                                text = "M3 Expressive 2025",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = MaterialSpacing.space100, vertical = MaterialSpacing.space25)
                            )
                        }
                    }

                    Text(
                        text = stringResource(R.string.home_hero_title),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = stringResource(R.string.home_hero_subtitle),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(Modifier.height(MaterialSpacing.space100))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(MaterialSpacing.space150),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = onNavigateToComponents,
                            shape = RoundedCornerShape(MaterialCornerRadius.Large),
                            modifier = Modifier.testTag("home_hero_explore_button")
                        ) {
                            Icon(Icons.Filled.Widgets, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(MaterialSpacing.space100))
                            Text("Explore 30+ Items")
                        }

                        OutlinedButton(
                            onClick = onNavigateToMotion,
                            shape = RoundedCornerShape(MaterialCornerRadius.Large)
                        ) {
                            Text("Motion Physics")
                        }
                    }
                }
            }

            // 2. Featured Interactive Experiment: Tactile Shape Morphing
            ElevatedCard(
                shape = RoundedCornerShape(MaterialCornerRadius.ExtraLargeIncreased),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("featured_morph_experiment")
            ) {
                Column(
                    modifier = Modifier.padding(MaterialSpacing.space250),
                    verticalArrangement = Arrangement.spacedBy(MaterialSpacing.space150)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "FEATURED EXPERIMENT",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.75f)
                            )
                            Text(
                                text = "Tactile Shape Morphing",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }

                        Icon(
                            imageVector = Icons.Filled.TouchApp,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // Interactive Morph Canvas
                    Surface(
                        shape = RoundedCornerShape(MaterialCornerRadius.LargeIncreased),
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clickable {
                                featuredShapeIndex++
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            MorphingShapeCanvas(
                                morph = currentMorph,
                                progress = morphProgress.value,
                                color = MaterialTheme.colorScheme.primary,
                                strokeColor = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.size(140.dp)
                            )

                            Text(
                                text = "Tap to Morph",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = MaterialSpacing.space100)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = shapeName,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        FilledTonalButton(
                            onClick = { featuredShapeIndex++ },
                            shape = RoundedCornerShape(MaterialCornerRadius.Medium),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Text("Next Shape")
                        }
                    }
                }
            }

            // 3. Discovery Section: Interactive Labs
            Column(verticalArrangement = Arrangement.spacedBy(MaterialSpacing.space150)) {
                Text(
                    text = stringResource(R.string.home_quick_labs),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(MaterialSpacing.space150)
                ) {
                    DiscoveryLabTile(
                        title = "Components",
                        subtitle = "Catalog & Code",
                        icon = Icons.Filled.Widgets,
                        accentColor = MaterialTheme.colorScheme.primary,
                        onClick = onNavigateToComponents,
                        modifier = Modifier.weight(1f).testTag("lab_components")
                    )
                    DiscoveryLabTile(
                        title = "Haptics Lab",
                        subtitle = "Waveforms & Ticks",
                        icon = Icons.Filled.Vibration,
                        accentColor = MaterialTheme.colorScheme.tertiary,
                        onClick = onNavigateToHaptics,
                        modifier = Modifier.weight(1f).testTag("lab_haptics")
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(MaterialSpacing.space150)
                ) {
                    DiscoveryLabTile(
                        title = "Motion Lab",
                        subtitle = "Spring Physics",
                        icon = Icons.Filled.Speed,
                        accentColor = MaterialTheme.colorScheme.secondary,
                        onClick = onNavigateToMotion,
                        modifier = Modifier.weight(1f).testTag("lab_motion")
                    )
                    DiscoveryLabTile(
                        title = "Theme Studio",
                        subtitle = "Palettes & Shapes",
                        icon = Icons.Filled.Palette,
                        accentColor = MaterialTheme.colorScheme.primary,
                        onClick = onNavigateToTheme,
                        modifier = Modifier.weight(1f).testTag("lab_theme")
                    )
                }
            }

            // 4. Hardware Capability Telemetry
            Surface(
                shape = RoundedCornerShape(MaterialCornerRadius.Large),
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = MaterialSpacing.space200, vertical = MaterialSpacing.space150)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(MaterialSpacing.space100)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Vibrator: ${if (caps.hasVibrator) "Ready" else "Virtual"}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(MaterialSpacing.space100)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Amplitude: ${if (caps.hasAmplitudeControl) "Dynamic" else "Standard"}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(MaterialCornerRadius.Small),
                        color = MaterialTheme.colorScheme.surfaceContainerHighest,
                        modifier = Modifier.padding(horizontal = MaterialSpacing.space50)
                    ) {
                        Text(
                            text = "API ${caps.sdkInt}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = MaterialSpacing.space100, vertical = MaterialSpacing.space25)
                        )
                    }
                }
            }

            // 5. Recently Explored
            Column(verticalArrangement = Arrangement.spacedBy(MaterialSpacing.space100)) {
                Text(
                    text = stringResource(R.string.home_recents_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                val recentItems = recentComponentIds.mapNotNull { ComponentCatalogData.getById(it) }
                if (recentItems.isEmpty()) {
                    OutlinedCard(
                        shape = RoundedCornerShape(MaterialCornerRadius.Large),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.home_recents_empty),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(MaterialSpacing.space200)
                        )
                    }
                } else {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(MaterialSpacing.space150)) {
                        items(recentItems, key = { it.id }) { item ->
                            Card(
                                shape = RoundedCornerShape(MaterialCornerRadius.Large),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                                modifier = Modifier
                                    .width(170.dp)
                                    .clickable { onSelectComponent(item) }
                                    .testTag("recent_item_${item.id}")
                            ) {
                                Column(modifier = Modifier.padding(MaterialSpacing.space150)) {
                                    Surface(
                                        shape = RoundedCornerShape(MaterialCornerRadius.Small),
                                        color = MaterialTheme.colorScheme.primaryContainer,
                                        modifier = Modifier.padding(bottom = MaterialSpacing.space75)
                                    ) {
                                        Text(
                                            text = item.category.title,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                                            modifier = Modifier.padding(horizontal = MaterialSpacing.space75, vertical = MaterialSpacing.space25)
                                        )
                                    }
                                    Text(
                                        text = item.name,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.SemiBold,
                                        maxLines = 1
                                    )
                                    Spacer(Modifier.height(MaterialSpacing.space50))
                                    Text(
                                        text = item.description,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 2
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DiscoveryLabTile(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(MaterialCornerRadius.ExtraLarge),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = modifier
            .clip(RoundedCornerShape(MaterialCornerRadius.ExtraLarge))
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(MaterialSpacing.space200),
            verticalArrangement = Arrangement.spacedBy(MaterialSpacing.space100)
        ) {
            Surface(
                shape = RoundedCornerShape(MaterialCornerRadius.Medium),
                color = accentColor.copy(alpha = 0.15f),
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
