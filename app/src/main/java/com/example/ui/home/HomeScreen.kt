package com.example.ui.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.ComponentItem
import com.example.model.HapticCapabilityInfo
import com.example.ui.catalog.ComponentCatalogData

@Composable
fun HomeScreen(
    caps: HapticCapabilityInfo,
    recentComponentIds: List<String>,
    onNavigateToComponents: () -> Unit,
    onNavigateToHaptics: () -> Unit,
    onNavigateToMotion: () -> Unit,
    onNavigateToTheme: () -> Unit,
    onSelectComponent: (ComponentItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var heroClickCount by remember { mutableIntStateOf(0) }
    var isHeroExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        // Hero Header
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = stringResource(R.string.home_hero_title),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.home_hero_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // 1. Featured Interactive Component Showcase (Hero Card)
        ElevatedCard(
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(spring(dampingRatio = 0.65f, stiffness = 500f))
                .clickable { isHeroExpanded = !isHeroExpanded }
                .testTag("home_hero_card"),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.home_featured_component),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = "Expressive Spring Morphing Card",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.TouchApp,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(Modifier.height(12.dp))
                Text(
                    text = if (isHeroExpanded) {
                        "Tap the button below to test reactive M3 state transitions with live counters, spring damping, and haptic feedback."
                    } else {
                        "Tap this card to expand and interact with expressive morphing."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                if (isHeroExpanded) {
                    Spacer(Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = { heroClickCount++ },
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("Taps: $heroClickCount")
                        }
                        FilledTonalButton(
                            onClick = {
                                ComponentCatalogData.getById("expressive_morphing")?.let(onSelectComponent)
                            },
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("Inspect Code")
                        }
                    }
                }
            }
        }

        // 2. Interactive Labs Quick Launch Grid
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = stringResource(R.string.home_quick_labs),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                LabCard(
                    title = "Components",
                    subtitle = "30+ UI items",
                    icon = Icons.Filled.Widgets,
                    onClick = onNavigateToComponents,
                    modifier = Modifier.weight(1f).testTag("lab_components")
                )
                LabCard(
                    title = "Haptics Lab",
                    subtitle = "Tactile motor",
                    icon = Icons.Filled.Vibration,
                    onClick = onNavigateToHaptics,
                    modifier = Modifier.weight(1f).testTag("lab_haptics")
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                LabCard(
                    title = "Motion Lab",
                    subtitle = "Spring physics",
                    icon = Icons.Filled.Speed,
                    onClick = onNavigateToMotion,
                    modifier = Modifier.weight(1f).testTag("lab_motion")
                )
                LabCard(
                    title = "Theme Studio",
                    subtitle = "Wallpaper & M3",
                    icon = Icons.Filled.Palette,
                    onClick = onNavigateToTheme,
                    modifier = Modifier.weight(1f).testTag("lab_theme")
                )
            }
        }

        // 3. Compact Device Capability Summary
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = stringResource(R.string.home_device_caps),
                    style = MaterialTheme.typography.titleMedium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Vibrator: ${if (caps.hasVibrator) "Available" else "Not Present"} (API ${caps.sdkInt})",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Amplitude Control: ${if (caps.hasAmplitudeControl) "Supported" else "Unsupported"}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // 4. Recently Explored
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = stringResource(R.string.home_recents_title),
                style = MaterialTheme.typography.titleLarge
            )

            val recentItems = recentComponentIds.mapNotNull { ComponentCatalogData.getById(it) }
            if (recentItems.isEmpty()) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.home_recents_empty),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(recentItems, key = { it.id }) { item ->
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                            modifier = Modifier
                                .width(180.dp)
                                .clickable { onSelectComponent(item) }
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = item.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    maxLines = 1
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = item.category.title,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(32.dp))
    }
}

@Composable
fun LabCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
