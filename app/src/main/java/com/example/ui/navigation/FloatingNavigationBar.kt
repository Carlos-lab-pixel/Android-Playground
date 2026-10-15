package com.example.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.MaterialComponentShapes
import com.example.ui.theme.MaterialCornerRadius
import com.example.ui.theme.MaterialMotion
import com.example.ui.theme.MaterialSpacing

@Composable
fun FloatingNavigationBar(
    currentRoute: String?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current

    Box(
        modifier = modifier
            .navigationBarsPadding()
            .padding(horizontal = MaterialSpacing.space300, vertical = MaterialSpacing.space150)
            .wrapContentWidth(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(MaterialCornerRadius.ExtraExtraLarge),
            color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.95f),
            tonalElevation = 6.dp,
            shadowElevation = 8.dp,
            modifier = Modifier
                .widthIn(min = 280.dp, max = 420.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(MaterialCornerRadius.ExtraExtraLarge)
                )
                .testTag("floating_nav_bar")
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = MaterialSpacing.space150, vertical = MaterialSpacing.space100),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopLevelDestination.values().forEach { destination ->
                    val selected = currentRoute == destination.route
                    FloatingNavItem(
                        destination = destination,
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                onNavigateToDestination(destination)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun FloatingNavItem(
    destination: TopLevelDestination,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    val iconColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.onSecondaryContainer
        else MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = androidx.compose.animation.core.tween(250),
        label = "nav_icon_color"
    )

    val containerColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.secondaryContainer
        else Color.Transparent,
        animationSpec = androidx.compose.animation.core.tween(250),
        label = "nav_pill_color"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(MaterialCornerRadius.ExtraLarge))
            .background(containerColor)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(bounded = true, radius = 32.dp),
                role = Role.Tab,
                onClick = onClick
            )
            .padding(horizontal = MaterialSpacing.space200, vertical = MaterialSpacing.space125)
            .testTag("nav_${destination.route}"),
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
                modifier = Modifier.size(24.dp)
            )

            AnimatedVisibility(
                visible = selected,
                enter = fadeIn(MaterialMotion.EffectsFast) + expandHorizontally(
                    spring(dampingRatio = 0.8f, stiffness = 400f)
                ),
                exit = fadeOut(MaterialMotion.EffectsFast) + shrinkHorizontally(
                    spring(dampingRatio = 0.9f, stiffness = 600f)
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
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        modifier = modifier.testTag("adaptive_nav_rail")
    ) {
        Spacer(Modifier.height(MaterialSpacing.space200))
        TopLevelDestination.values().forEach { destination ->
            val selected = currentRoute == destination.route
            NavigationRailItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = stringResource(destination.labelResId)
                    )
                },
                label = { Text(stringResource(destination.labelResId)) },
                modifier = Modifier.testTag("rail_${destination.route}")
            )
        }
    }
}
