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
    TERRACOTTA("Terracotta Rust", Color(0xFF984061), Color(0xFF735760)),
    NEON_CYBER("Cyber Violet", Color(0xFF7C3AED), Color(0xFF06B6D4)),
    CORAL_BLUSH("Coral Flamingo", Color(0xFFF43F5E), Color(0xFFFB923C)),
    MINT_LIME("Electric Mint", Color(0xFF059669), Color(0xFF65A30D)),
    COBALT_AURA("Cobalt Electric", Color(0xFF2563EB), Color(0xFF0284C7)),
    MIDNIGHT_OBSIDIAN("Midnight Chrome", Color(0xFF334155), Color(0xFF64748B)),
    // New expressive additions
    ELECTRIC_SUNSET("Electric Sunset", Color(0xFFFF5722), Color(0xFFE91E63)),
    AURORA_CYBER("Aurora Emerald", Color(0xFF00B0FF), Color(0xFF00E676)),
    CHERRY_BLOSSOM("Cherry Bloom", Color(0xFFD81B60), Color(0xFFFFB300)),
    ROYAL_AMETHYST("Royal Amethyst", Color(0xFF7B1FA2), Color(0xFF9C27B0)),
    COSMIC_TURQUOISE("Cosmic Turquoise", Color(0xFF00B4D8), Color(0xFFFF7043))
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
