package com.example.ui.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
import com.example.ui.home.HomeScreen
import com.example.ui.labs.LabsHubScreen
import com.example.ui.playground.PlaygroundScreen
import com.example.ui.settings.SettingsScreen
import com.example.ui.theme.MaterialMotion
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppNavigation(
    themeState: AppThemeState,
    preferencesRepo: UserPreferencesRepository,
    hapticsManager: HapticsService,
    recentComponents: List<String>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val windowSizeClass = activity?.let { calculateWindowSizeClass(it) }
    val isExpanded = windowSizeClass?.widthSizeClass == WindowWidthSizeClass.Expanded

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isTopLevelDestination = TopLevelDestination.values().any { it.route == currentRoute }

    fun navigateToTopLevel(destination: TopLevelDestination) {
        if (currentRoute != destination.route) {
            navController.navigate(destination.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Adaptive Navigation Rail for Expanded (Tablets / Foldables)
            if (isExpanded && isTopLevelDestination) {
                AdaptiveNavigationRail(
                    currentRoute = currentRoute,
                    onNavigateToDestination = ::navigateToTopLevel,
                    onOpenSettings = { navController.navigate(SubDestinations.SETTINGS) }
                )
            }

            Box(modifier = Modifier.weight(1f).fillMaxSize()) {
                NavHost(
                    navController = navController,
                    startDestination = TopLevelDestination.HOME.route,
                    // 30% shorter snappier transitions per Material 3 Expressive guidelines
                    enterTransition = {
                        fadeIn(tween(150, easing = MaterialMotion.EmphasizedDecelerateEasing)) +
                                slideInHorizontally(tween(175, easing = MaterialMotion.EmphasizedDecelerateEasing)) { (it * 0.08f).toInt() }
                    },
                    exitTransition = {
                        fadeOut(tween(100, easing = MaterialMotion.EmphasizedAccelerateEasing)) +
                                slideOutHorizontally(tween(120, easing = MaterialMotion.EmphasizedAccelerateEasing)) { (-it * 0.08f).toInt() }
                    },
                    popEnterTransition = {
                        fadeIn(tween(150, easing = MaterialMotion.EmphasizedDecelerateEasing)) +
                                slideInHorizontally(tween(175, easing = MaterialMotion.EmphasizedDecelerateEasing)) { (-it * 0.08f).toInt() }
                    },
                    popExitTransition = {
                        fadeOut(tween(100, easing = MaterialMotion.EmphasizedAccelerateEasing)) +
                                slideOutHorizontally(tween(120, easing = MaterialMotion.EmphasizedAccelerateEasing)) { (it * 0.08f).toInt() }
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(TopLevelDestination.HOME.route) {
                        HomeScreen(
                            caps = hapticsManager.getCapabilities(),
                            recentComponentIds = recentComponents,
                            onNavigateToComponents = { navigateToTopLevel(TopLevelDestination.EXPLORE) },
                            onNavigateToHaptics = {
                                navController.navigate("labs_tab/0")
                            },
                            onNavigateToMotion = {
                                navController.navigate("labs_tab/1")
                            },
                            onNavigateToTheme = {
                                navController.navigate("labs_tab/2")
                            },
                            onOpenSettings = { navController.navigate(SubDestinations.SETTINGS) },
                            onSelectComponent = { item ->
                                scope.launch { preferencesRepo.recordComponentViewed(item.id) }
                                navController.navigate(SubDestinations.playgroundRoute(item.id))
                            }
                        )
                    }

                    composable(TopLevelDestination.EXPLORE.route) {
                        CatalogScreen(
                            onSelectComponent = { item ->
                                scope.launch { preferencesRepo.recordComponentViewed(item.id) }
                                navController.navigate(SubDestinations.playgroundRoute(item.id))
                            },
                            onOpenSettings = { navController.navigate(SubDestinations.SETTINGS) }
                        )
                    }

                    composable(TopLevelDestination.LABS.route) {
                        LabsHubScreen(
                            themeState = themeState,
                            preferencesRepo = preferencesRepo,
                            hapticsManager = hapticsManager,
                            onOpenSettings = { navController.navigate(SubDestinations.SETTINGS) },
                            initialTab = 0
                        )
                    }

                    composable(
                        route = "labs_tab/{tabIndex}",
                        arguments = listOf(navArgument("tabIndex") { type = NavType.IntType; defaultValue = 0 })
                    ) { backStackEntry ->
                        val tabIndex = backStackEntry.arguments?.getInt("tabIndex") ?: 0
                        LabsHubScreen(
                            themeState = themeState,
                            preferencesRepo = preferencesRepo,
                            hapticsManager = hapticsManager,
                            onOpenSettings = { navController.navigate(SubDestinations.SETTINGS) },
                            initialTab = tabIndex
                        )
                    }

                    composable(SubDestinations.SETTINGS) {
                        SettingsScreen(
                            themeState = themeState,
                            preferencesRepo = preferencesRepo,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }

                    composable(
                        route = SubDestinations.PLAYGROUND,
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

                // Floating Toolbar Navigation Bar + Paired Settings Companion Button
                if (!isExpanded && isTopLevelDestination) {
                    Box(
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        FloatingToolbarNavigation(
                            currentRoute = currentRoute,
                            onNavigateToDestination = ::navigateToTopLevel,
                            onOpenSettings = { navController.navigate(SubDestinations.SETTINGS) }
                        )
                    }
                }
            }
        }
    }
}
