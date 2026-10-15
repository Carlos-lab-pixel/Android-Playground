package com.example.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.R

enum class TopLevelDestination(
    val route: String,
    val labelResId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME(
        route = "home",
        labelResId = R.string.nav_home,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    EXPLORE(
        route = "explore",
        labelResId = R.string.nav_explore,
        selectedIcon = Icons.Filled.Widgets,
        unselectedIcon = Icons.Outlined.Widgets
    ),
    LABS(
        route = "labs",
        labelResId = R.string.nav_labs,
        selectedIcon = Icons.Filled.Science,
        unselectedIcon = Icons.Outlined.Science
    )
}

object SubDestinations {
    const val SETTINGS = "settings"
    const val HAPTICS = "haptics"
    const val MOTION = "motion"
    const val THEME = "theme"
    const val PLAYGROUND = "playground/{componentId}"

    fun playgroundRoute(componentId: String) = "playground/$componentId"
}
