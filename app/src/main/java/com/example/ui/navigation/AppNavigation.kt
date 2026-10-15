package com.example.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.UserPreferencesRepository
import com.example.model.AppThemeState
import com.example.platform.haptics.HapticsService
import com.example.ui.catalog.CatalogScreen
import com.example.ui.catalog.ComponentCatalogData
import com.example.ui.haptics.HapticsScreen
import com.example.ui.home.HomeScreen
import com.example.ui.motion.MotionScreen
import com.example.ui.playground.PlaygroundScreen
import com.example.ui.settings.SettingsScreen
import com.example.ui.themestudio.ThemeStudioScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(
    themeState: AppThemeState,
    preferencesRepo: UserPreferencesRepository,
    hapticsManager: HapticsService,
    recentComponents: List<String>,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isTopLevelDestination = NavDestination.values().any { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            if (isTopLevelDestination) {
                NavigationBar(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .testTag("bottom_nav_bar")
                ) {
                    NavDestination.values().forEach { destination ->
                        val selected = currentRoute == destination.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (currentRoute != destination.route) {
                                    navController.navigate(destination.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                                    contentDescription = stringResource(destination.labelResId)
                                )
                            },
                            label = { Text(stringResource(destination.labelResId)) },
                            modifier = Modifier.testTag("nav_${destination.route}")
                        )
                    }
                }
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavDestination.HOME.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavDestination.HOME.route) {
                HomeScreen(
                    caps = hapticsManager.getCapabilities(),
                    recentComponentIds = recentComponents,
                    onNavigateToComponents = { navController.navigate(NavDestination.COMPONENTS.route) },
                    onNavigateToHaptics = { navController.navigate(NavDestination.HAPTICS.route) },
                    onNavigateToMotion = { navController.navigate(NavDestination.MOTION.route) },
                    onNavigateToTheme = { navController.navigate(NavDestination.THEME.route) },
                    onSelectComponent = { item ->
                        scope.launch { preferencesRepo.recordComponentViewed(item.id) }
                        navController.navigate("playground/${item.id}")
                    }
                )
            }

            composable(NavDestination.COMPONENTS.route) {
                CatalogScreen(
                    onSelectComponent = { item ->
                        scope.launch { preferencesRepo.recordComponentViewed(item.id) }
                        navController.navigate("playground/${item.id}")
                    }
                )
            }

            composable(NavDestination.HAPTICS.route) {
                HapticsScreen(hapticsManager = hapticsManager)
            }

            composable(NavDestination.MOTION.route) {
                MotionScreen()
            }

            composable(NavDestination.THEME.route) {
                ThemeStudioScreen(
                    themeState = themeState,
                    preferencesRepo = preferencesRepo
                )
            }

            composable(NavDestination.SETTINGS.route) {
                SettingsScreen(
                    themeState = themeState,
                    preferencesRepo = preferencesRepo
                )
            }

            composable(
                route = "playground/{componentId}",
                arguments = listOf(navArgument("componentId") { type = NavType.StringType })
            ) { backStackEntry ->
                val componentId = backStackEntry.arguments?.getString("componentId")
                val item = componentId?.let { ComponentCatalogData.getById(it) }
                    ?: ComponentCatalogData.allComponents.first()

                PlaygroundScreen(
                    item = item,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
