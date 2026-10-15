package com.example.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.example.model.ColorSeed

// Expressive Vibrant (Default)
private val VibrantLight = lightColorScheme(
    primary = Color(0xFF6750A4),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFEADDFF),
    onPrimaryContainer = Color(0xFF21005D),
    secondary = Color(0xFF625B71),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE8DEF8),
    onSecondaryContainer = Color(0xFF1D192B),
    tertiary = Color(0xFF7D5260),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFD8E4),
    onTertiaryContainer = Color(0xFF31111D),
    background = Color(0xFFFEF7FF),
    onBackground = Color(0xFF1D1B20),
    surface = Color(0xFFFEF7FF),
    onSurface = Color(0xFF1D1B20),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0)
)

private val VibrantDark = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    onPrimary = Color(0xFF381E72),
    primaryContainer = Color(0xFF4F378B),
    onPrimaryContainer = Color(0xFFEADDFF),
    secondary = Color(0xFFCCC2DC),
    onSecondary = Color(0xFF332D41),
    secondaryContainer = Color(0xFF4A4458),
    onSecondaryContainer = Color(0xFFE8DEF8),
    tertiary = Color(0xFFEFB8C8),
    onTertiary = Color(0xFF492532),
    tertiaryContainer = Color(0xFF633B48),
    onTertiaryContainer = Color(0xFFFFD8E4),
    background = Color(0xFF141218),
    onBackground = Color(0xFFE6E0E9),
    surface = Color(0xFF141218),
    onSurface = Color(0xFFE6E0E9),
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),
    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F)
)

// Oceanic Indigo
private val OceanicLight = lightColorScheme(
    primary = Color(0xFF006399),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFCEE5FF),
    onPrimaryContainer = Color(0xFF001D32),
    secondary = Color(0xFF51606F),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFD5E4F7),
    onSecondaryContainer = Color(0xFF0E1D2A),
    tertiary = Color(0xFF67587A),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFEEDCFF),
    onTertiaryContainer = Color(0xFF221533),
    background = Color(0xFFF8F9FF),
    onBackground = Color(0xFF191C20),
    surface = Color(0xFFF8F9FF),
    onSurface = Color(0xFF191C20)
)

private val OceanicDark = darkColorScheme(
    primary = Color(0xFF97CBFF),
    onPrimary = Color(0xFF003353),
    primaryContainer = Color(0xFF004B75),
    onPrimaryContainer = Color(0xFFCEE5FF),
    secondary = Color(0xFFB8C8DA),
    onSecondary = Color(0xFF233240),
    secondaryContainer = Color(0xFF3A4857),
    onSecondaryContainer = Color(0xFFD5E4F7),
    tertiary = Color(0xFFD2BFE7),
    onTertiary = Color(0xFF382A4A),
    tertiaryContainer = Color(0xFF4F4061),
    onTertiaryContainer = Color(0xFFEEDCFF),
    background = Color(0xFF111418),
    onBackground = Color(0xFFE1E2E8),
    surface = Color(0xFF111418),
    onSurface = Color(0xFFE1E2E8)
)

// Forest Jade
private val ForestLight = lightColorScheme(
    primary = Color(0xFF006D44),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF90F7BE),
    onPrimaryContainer = Color(0xFF002111),
    secondary = Color(0xFF4F6353),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFD2E8D4),
    onSecondaryContainer = Color(0xFF0D1F13),
    tertiary = Color(0xFF3A646E),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFBEEAF6),
    onTertiaryContainer = Color(0xFF001F25),
    background = Color(0xFFF6FBF3),
    onBackground = Color(0xFF181D18),
    surface = Color(0xFFF6FBF3),
    onSurface = Color(0xFF181D18)
)

private val ForestDark = darkColorScheme(
    primary = Color(0xFF73DAA4),
    onPrimary = Color(0xFF003921),
    primaryContainer = Color(0xFF005232),
    onPrimaryContainer = Color(0xFF90F7BE),
    secondary = Color(0xFFB6CCB9),
    onSecondary = Color(0xFF223527),
    secondaryContainer = Color(0xFF384C3C),
    onSecondaryContainer = Color(0xFFD2E8D4),
    tertiary = Color(0xFFA2CED9),
    onTertiary = Color(0xFF01363F),
    tertiaryContainer = Color(0xFF204C56),
    onTertiaryContainer = Color(0xFFBEEAF6),
    background = Color(0xFF101511),
    onBackground = Color(0xFFE0E4DE),
    surface = Color(0xFF101511),
    onSurface = Color(0xFFE0E4DE)
)

// Sunset Amber
private val SunsetLight = lightColorScheme(
    primary = Color(0xFF885200),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFDDB6),
    onPrimaryContainer = Color(0xFF2B1700),
    secondary = Color(0xFF705B41),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFCDDBE),
    onSecondaryContainer = Color(0xFF271805),
    tertiary = Color(0xFF55643B),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFD8EAA5),
    onTertiaryContainer = Color(0xFF141F02),
    background = Color(0xFFFFF8F4),
    onBackground = Color(0xFF211A13),
    surface = Color(0xFFFFF8F4),
    onSurface = Color(0xFF211A13)
)

private val SunsetDark = darkColorScheme(
    primary = Color(0xFFFFB86C),
    onPrimary = Color(0xFF482900),
    primaryContainer = Color(0xFF673D00),
    onPrimaryContainer = Color(0xFFFFDDB6),
    secondary = Color(0xFFDEC2A2),
    onSecondary = Color(0xFF3E2D17),
    secondaryContainer = Color(0xFF56432B),
    onSecondaryContainer = Color(0xFFFCDDBE),
    tertiary = Color(0xFFBCCE8B),
    onTertiary = Color(0xFF283511),
    tertiaryContainer = Color(0xFF3E4C25),
    onTertiaryContainer = Color(0xFFD8EAA5),
    background = Color(0xFF18120C),
    onBackground = Color(0xFFEBE0D7),
    surface = Color(0xFF18120C),
    onSurface = Color(0xFFEBE0D7)
)

// Terracotta Rust
private val TerracottaLight = lightColorScheme(
    primary = Color(0xFF904A46),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFDAD7),
    onPrimaryContainer = Color(0xFF3B0909),
    secondary = Color(0xFF775653),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFDAD6),
    onSecondaryContainer = Color(0xFF2C1513),
    tertiary = Color(0xFF715B2E),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFEDFA6),
    onTertiaryContainer = Color(0xFF261A00),
    background = Color(0xFFFFF8F7),
    onBackground = Color(0xFF231919),
    surface = Color(0xFFFFF8F7),
    onSurface = Color(0xFF231919)
)

private val TerracottaDark = darkColorScheme(
    primary = Color(0xFFFFB3AE),
    onPrimary = Color(0xFF571D1C),
    primaryContainer = Color(0xFF733330),
    onPrimaryContainer = Color(0xFFFFDAD7),
    secondary = Color(0xFFE7BDB8),
    onSecondary = Color(0xFF442927),
    secondaryContainer = Color(0xFF5D3F3C),
    onSecondaryContainer = Color(0xFFFFDAD6),
    tertiary = Color(0xFFE1C38C),
    onTertiary = Color(0xFF3F2E05),
    tertiaryContainer = Color(0xFF574419),
    onTertiaryContainer = Color(0xFFFEDFA6),
    background = Color(0xFF1A1111),
    onBackground = Color(0xFFF1DFDD),
    surface = Color(0xFF1A1111),
    onSurface = Color(0xFFF1DFDD)
)

fun getCustomColorScheme(seed: ColorSeed, isDark: Boolean): ColorScheme {
    return when (seed) {
        ColorSeed.EXPRESSIVE_VIBRANT -> if (isDark) VibrantDark else VibrantLight
        ColorSeed.OCEANIC_INDIGO -> if (isDark) OceanicDark else OceanicLight
        ColorSeed.FOREST_JADE -> if (isDark) ForestDark else ForestLight
        ColorSeed.SUNSET_AMBER -> if (isDark) SunsetDark else SunsetLight
        ColorSeed.TERRACOTTA -> if (isDark) TerracottaDark else TerracottaLight
    }
}
