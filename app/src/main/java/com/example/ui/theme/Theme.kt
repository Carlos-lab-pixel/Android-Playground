package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.model.AppThemeState
import com.example.model.ThemeMode

@Composable
fun MaterialPlaygroundTheme(
    themeState: AppThemeState = AppThemeState(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val isDark = when (themeState.themeMode) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
    }

    val colorScheme = if (themeState.isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        try {
            if (isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        } catch (_: Exception) {
            getCustomColorScheme(themeState.colorSeed, isDark)
        }
    } else {
        getCustomColorScheme(themeState.colorSeed, isDark)
    }

    val shapes = getExpressiveShapes(themeState.shapeStyle)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = shapes,
        content = content
    )
}
