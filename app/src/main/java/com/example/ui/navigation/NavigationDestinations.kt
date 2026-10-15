package com.example.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.material.icons.outlined.Vibration
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavDestination(
    val route: String,
    val labelResId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME(
        route = "home",
        labelResId = com.example.R.string.nav_home,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    COMPONENTS(
        route = "components",
        labelResId = com.example.R.string.nav_components,
        selectedIcon = Icons.Filled.Widgets,
        unselectedIcon = Icons.Outlined.Widgets
    ),
    HAPTICS(
        route = "haptics",
        labelResId = com.example.R.string.nav_haptics,
        selectedIcon = Icons.Filled.Vibration,
        unselectedIcon = Icons.Outlined.Vibration
    ),
    MOTION(
        route = "motion",
        labelResId = com.example.R.string.nav_motion,
        selectedIcon = Icons.Filled.Speed,
        unselectedIcon = Icons.Outlined.Speed
    ),
    THEME(
        route = "theme",
        labelResId = com.example.R.string.nav_theme,
        selectedIcon = Icons.Filled.Palette,
        unselectedIcon = Icons.Outlined.Palette
    ),
    SETTINGS(
        route = "settings",
        labelResId = com.example.R.string.nav_settings,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
}
