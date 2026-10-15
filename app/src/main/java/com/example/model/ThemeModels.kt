package com.example.model

import androidx.compose.ui.graphics.Color

enum class ThemeMode {
    SYSTEM,
    LIGHT,
    DARK
}

enum class ColorSeed(val displayName: String, val primaryColor: Color, val secondaryColor: Color) {
    EXPRESSIVE_VIBRANT("Expressive Violet", Color(0xFF6750A4), Color(0xFF625B71)),
    OCEANIC_INDIGO("Oceanic Indigo", Color(0xFF006399), Color(0xFF4F6070)),
    FOREST_JADE("Forest Jade", Color(0xFF006D44), Color(0xFF4D6353)),
    SUNSET_AMBER("Sunset Amber", Color(0xFF8B5000), Color(0xFF715B41)),
    TERRACOTTA("Terracotta Rust", Color(0xFF984061), Color(0xFF735760))
}

enum class ExpressiveShapeStyle(val displayName: String, val smallCorner: Float, val mediumCorner: Float, val largeCorner: Float) {
    SUBTLE("Subtle (8dp)", 4f, 8f, 12f),
    STANDARD("Standard M3 (16dp)", 8f, 16f, 24f),
    EXPRESSIVE("Expressive M3 (28dp)", 12f, 24f, 32f)
}

data class AppThemeState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val isDynamicColor: Boolean = true,
    val colorSeed: ColorSeed = ColorSeed.EXPRESSIVE_VIBRANT,
    val shapeStyle: ExpressiveShapeStyle = ExpressiveShapeStyle.EXPRESSIVE,
    val isHapticsEnabled: Boolean = true,
    val hapticIntensity: Float = 1.0f,
    val isReducedMotion: Boolean = false,
    val languageCode: String = "en"
)
