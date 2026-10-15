package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.model.AppThemeState
import com.example.model.ColorSeed
import com.example.model.ExpressiveShapeStyle
import com.example.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

class UserPreferencesRepository(private val context: Context) {

    private object PreferencesKeys {
        val THEME_MODE = stringPreferencesKey("theme_mode")
        val DYNAMIC_COLOR = booleanPreferencesKey("dynamic_color")
        val COLOR_SEED = stringPreferencesKey("color_seed")
        val SHAPE_STYLE = stringPreferencesKey("shape_style")
        val HAPTICS_ENABLED = booleanPreferencesKey("haptics_enabled")
        val HAPTIC_INTENSITY = floatPreferencesKey("haptic_intensity")
        val REDUCED_MOTION = booleanPreferencesKey("reduced_motion")
        val LANGUAGE_CODE = stringPreferencesKey("language_code")
        val RECENT_COMPONENTS = stringPreferencesKey("recent_components")
    }

    val themeStateFlow: Flow<AppThemeState> = context.dataStore.data.map { preferences ->
        val themeModeStr = preferences[PreferencesKeys.THEME_MODE] ?: ThemeMode.SYSTEM.name
        val isDynamicColor = preferences[PreferencesKeys.DYNAMIC_COLOR] ?: true
        val colorSeedStr = preferences[PreferencesKeys.COLOR_SEED] ?: ColorSeed.EXPRESSIVE_VIBRANT.name
        val shapeStyleStr = preferences[PreferencesKeys.SHAPE_STYLE] ?: ExpressiveShapeStyle.EXPRESSIVE.name
        val isHapticsEnabled = preferences[PreferencesKeys.HAPTICS_ENABLED] ?: true
        val hapticIntensity = preferences[PreferencesKeys.HAPTIC_INTENSITY] ?: 1.0f
        val isReducedMotion = preferences[PreferencesKeys.REDUCED_MOTION] ?: false
        val languageCode = preferences[PreferencesKeys.LANGUAGE_CODE] ?: "en"

        AppThemeState(
            themeMode = try { ThemeMode.valueOf(themeModeStr) } catch (_: Exception) { ThemeMode.SYSTEM },
            isDynamicColor = isDynamicColor,
            colorSeed = try { ColorSeed.valueOf(colorSeedStr) } catch (_: Exception) { ColorSeed.EXPRESSIVE_VIBRANT },
            shapeStyle = try { ExpressiveShapeStyle.valueOf(shapeStyleStr) } catch (_: Exception) { ExpressiveShapeStyle.EXPRESSIVE },
            isHapticsEnabled = isHapticsEnabled,
            hapticIntensity = hapticIntensity,
            isReducedMotion = isReducedMotion,
            languageCode = languageCode
        )
    }

    val recentComponentsFlow: Flow<List<String>> = context.dataStore.data.map { preferences ->
        val raw = preferences[PreferencesKeys.RECENT_COMPONENTS] ?: ""
        if (raw.isBlank()) emptyList() else raw.split(",").filter { it.isNotBlank() }
    }

    suspend fun updateThemeMode(themeMode: ThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME_MODE] = themeMode.name
        }
    }

    suspend fun updateDynamicColor(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.DYNAMIC_COLOR] = enabled
        }
    }

    suspend fun updateColorSeed(colorSeed: ColorSeed) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.COLOR_SEED] = colorSeed.name
        }
    }

    suspend fun updateShapeStyle(shapeStyle: ExpressiveShapeStyle) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SHAPE_STYLE] = shapeStyle.name
        }
    }

    suspend fun updateHapticsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.HAPTICS_ENABLED] = enabled
        }
    }

    suspend fun updateHapticIntensity(intensity: Float) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.HAPTIC_INTENSITY] = intensity
        }
    }

    suspend fun updateReducedMotion(reduced: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.REDUCED_MOTION] = reduced
        }
    }

    suspend fun updateLanguage(code: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LANGUAGE_CODE] = code
        }
    }

    suspend fun recordComponentViewed(componentId: String) {
        context.dataStore.edit { preferences ->
            val raw = preferences[PreferencesKeys.RECENT_COMPONENTS] ?: ""
            val currentList = if (raw.isBlank()) mutableListOf() else raw.split(",").filter { it.isNotBlank() }.toMutableList()
            currentList.remove(componentId)
            currentList.add(0, componentId)
            val trimmed = currentList.take(8)
            preferences[PreferencesKeys.RECENT_COMPONENTS] = trimmed.joinToString(",")
        }
    }
}
