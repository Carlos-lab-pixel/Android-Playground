package com.example

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.UserPreferencesRepository
import com.example.model.AppThemeState
import com.example.platform.haptics.HapticsManager
import com.example.ui.navigation.AppNavigation
import com.example.ui.theme.MaterialPlaygroundTheme
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val preferencesRepo = UserPreferencesRepository(applicationContext)
        val hapticsManager = HapticsManager(applicationContext)

        setContent {
            val themeState by preferencesRepo.themeStateFlow.collectAsStateWithLifecycle(initialValue = AppThemeState())
            val recentComponents by preferencesRepo.recentComponentsFlow.collectAsStateWithLifecycle(initialValue = emptyList())

            val localizedContext = remember(themeState.languageCode) {
                val locale = Locale(themeState.languageCode)
                Locale.setDefault(locale)
                val config = Configuration(resources.configuration)
                config.setLocale(locale)
                createConfigurationContext(config)
            }

            CompositionLocalProvider(LocalContext provides localizedContext) {
                MaterialPlaygroundTheme(themeState = themeState) {
                    AppNavigation(
                        themeState = themeState,
                        preferencesRepo = preferencesRepo,
                        hapticsManager = hapticsManager,
                        recentComponents = recentComponents
                    )
                }
            }
        }
    }
}
