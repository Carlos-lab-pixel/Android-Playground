package com.example.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.ui.theme.MaterialSpacing

/**
 * Material 3 Expressive Floating Toolbar Navigation Bar.
 * - Solid opaque container (non-transparent surfaceContainerHighest).
 * - Optical corner radius formula (outer radius 32dp - 6dp equal padding = 26dp inner container radius).
 * - Equal spacing / padding (6dp) between outer boundary and selection indicator.
 * - Matching companion settings button paired on the right with identical height, corner radius, elevation, and border.
 * - Bounded Material ripples and spring micro-interactions.
 */
@Composable
fun FloatingToolbarNavigation(
    currentRoute: String?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current
    val outerRadius = 32.dp
    val containerPadding = 6.dp
    val innerRadius = outerRadius - containerPadding // 26.dp optical concentric curvature

    Box(
        modifier = modifier
            .navigationBarsPadding()
            .padding(horizontal = MaterialSpacing.space200, vertical = MaterialSpacing.space150)
            .wrapContentWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Main Floating Toolbar (Solid Opaque Surface)
            Surface(
                shape = RoundedCornerShape(outerRadius),
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                tonalElevation = 6.dp,
                shadowElevation = 8.dp,
                modifier = Modifier
                    .height(64.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(outerRadius)
                    )
                    .testTag("floating_toolbar_nav")
            ) {
                Row(
                    modifier = Modifier
                        .padding(containerPadding),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TopLevelDestination.values().forEach { destination ->
                        val selected = currentRoute == destination.route
                        FloatingToolbarItem(
                            destination = destination,
                            selected = selected,
                            innerRadius = innerRadius,
                            onClick = {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                onNavigateToDestination(destination)
                            }
                        )
                    }
                }
            }

            // Matching Companion Settings Component (Paired Floating Action Component)
            Surface(
                shape = RoundedCornerShape(outerRadius),
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                tonalElevation = 6.dp,
                shadowElevation = 8.dp,
                modifier = Modifier
                    .size(64.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(outerRadius)
                    )
                    .clip(RoundedCornerShape(outerRadius))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(bounded = true, radius = outerRadius),
                        role = Role.Button,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            onOpenSettings()
                        }
                    )
                    .testTag("toolbar_settings_companion_button")
            ) {
                Box(
                    modifier = Modifier.size(64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(R.string.settings_action),
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FloatingToolbarItem(
    destination: TopLevelDestination,
    selected: Boolean,
    innerRadius: androidx.compose.ui.unit.Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    val iconColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.onSecondaryContainer
        else MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = tween(180),
        label = "nav_icon_color"
    )

    val containerColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.secondaryContainer
        else Color.Transparent,
        animationSpec = tween(180),
        label = "nav_pill_color"
    )

    val itemShape = RoundedCornerShape(innerRadius)

    Box(
        modifier = modifier
            .height(52.dp)
            .clip(itemShape)
            .background(containerColor)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(bounded = true, color = MaterialTheme.colorScheme.primary),
                role = Role.Tab,
                onClick = onClick
            )
            .padding(horizontal = MaterialSpacing.space150),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                contentDescription = stringResource(destination.labelResId),
                tint = iconColor,
                modifier = Modifier.size(22.dp)
            )

            AnimatedVisibility(
                visible = selected,
                enter = fadeIn(tween(140)) + expandHorizontally(
                    spring(dampingRatio = 0.8f, stiffness = 550f)
                ),
                exit = fadeOut(tween(100)) + shrinkHorizontally(
                    spring(dampingRatio = 0.9f, stiffness = 700f)
                )
            ) {
                Row {
                    Spacer(Modifier.width(MaterialSpacing.space100))
                    Text(
                        text = stringResource(destination.labelResId),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun AdaptiveNavigationRail(
    currentRoute: String?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current

    NavigationRail(
        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        modifier = modifier.testTag("adaptive_nav_rail")
    ) {
        Spacer(Modifier.height(MaterialSpacing.space200))
        TopLevelDestination.values().forEach { destination ->
            val selected = currentRoute == destination.route
            NavigationRailItem(
                selected = selected,
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onNavigateToDestination(destination)
                },
                icon = {
                    Icon(
                        imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = stringResource(destination.labelResId)
                    )
                },
                label = {
                    Text(
                        text = stringResource(destination.labelResId),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            )
        }
        Spacer(Modifier.weight(1f))
        NavigationRailItem(
            selected = false,
            onClick = {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onOpenSettings()
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.settings_action)
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.settings_action),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        )
        Spacer(Modifier.height(MaterialSpacing.space200))
    }
}
