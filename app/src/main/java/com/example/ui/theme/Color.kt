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

// Cyber Violet
private val CyberLight = lightColorScheme(
    primary = Color(0xFF6D28D9),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFEDE9FE),
    onPrimaryContainer = Color(0xFF2E1065),
    secondary = Color(0xFF0891B2),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFCFFAFE),
    onSecondaryContainer = Color(0xFF164E63),
    tertiary = Color(0xFFD946EF),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFAE8FF),
    onTertiaryContainer = Color(0xFF701A75),
    background = Color(0xFFFAF5FF),
    onBackground = Color(0xFF1E1B4B),
    surface = Color(0xFFFAF5FF),
    onSurface = Color(0xFF1E1B4B)
)

private val CyberDark = darkColorScheme(
    primary = Color(0xFFA78BFA),
    onPrimary = Color(0xFF3B0764),
    primaryContainer = Color(0xFF5B21B6),
    onPrimaryContainer = Color(0xFFEDE9FE),
    secondary = Color(0xFF22D3EE),
    onSecondary = Color(0xFF083344),
    secondaryContainer = Color(0xFF155E75),
    onSecondaryContainer = Color(0xFFCFFAFE),
    tertiary = Color(0xFFF0ABFC),
    onTertiary = Color(0xFF4A044E),
    tertiaryContainer = Color(0xFF86198F),
    onTertiaryContainer = Color(0xFFFAE8FF),
    background = Color(0xFF0F0728),
    onBackground = Color(0xFFF5F3FF),
    surface = Color(0xFF0F0728),
    onSurface = Color(0xFFF5F3FF)
)

// Coral Flamingo
private val CoralLight = lightColorScheme(
    primary = Color(0xFFE11D48),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFE4E6),
    onPrimaryContainer = Color(0xFF881337),
    secondary = Color(0xFFEA580C),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFEDD5),
    onSecondaryContainer = Color(0xFF7C2D12),
    tertiary = Color(0xFFD97706),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFEF3C7),
    onTertiaryContainer = Color(0xFF78350F),
    background = Color(0xFFFFF1F2),
    onBackground = Color(0xFF2C0A11),
    surface = Color(0xFFFFF1F2),
    onSurface = Color(0xFF2C0A11)
)

private val CoralDark = darkColorScheme(
    primary = Color(0xFFFB7185),
    onPrimary = Color(0xFF4C0519),
    primaryContainer = Color(0xFF9F1239),
    onPrimaryContainer = Color(0xFFFFE4E6),
    secondary = Color(0xFFFB923C),
    onSecondary = Color(0xFF431407),
    secondaryContainer = Color(0xFF9A3412),
    onSecondaryContainer = Color(0xFFFFEDD5),
    tertiary = Color(0xFFFCD34D),
    onTertiary = Color(0xFF451A03),
    tertiaryContainer = Color(0xFF92400E),
    onTertiaryContainer = Color(0xFFFEF3C7),
    background = Color(0xFF1A080C),
    onBackground = Color(0xFFFFE4E6),
    surface = Color(0xFF1A080C),
    onSurface = Color(0xFFFFE4E6)
)

// Electric Mint
private val MintLight = lightColorScheme(
    primary = Color(0xFF059669),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD1FAE5),
    onPrimaryContainer = Color(0xFF064E3B),
    secondary = Color(0xFF65A30D),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFECFCCB),
    onSecondaryContainer = Color(0xFF365314),
    tertiary = Color(0xFF0D9488),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFCCFBF1),
    onTertiaryContainer = Color(0xFF134E4A),
    background = Color(0xFFF0FDF4),
    onBackground = Color(0xFF052E16),
    surface = Color(0xFFF0FDF4),
    onSurface = Color(0xFF052E16)
)

private val MintDark = darkColorScheme(
    primary = Color(0xFF34D399),
    onPrimary = Color(0xFF022C22),
    primaryContainer = Color(0xFF065F46),
    onPrimaryContainer = Color(0xFFD1FAE5),
    secondary = Color(0xFFA3E635),
    onSecondary = Color(0xFF1A2E05),
    secondaryContainer = Color(0xFF3F6212),
    onSecondaryContainer = Color(0xFFECFCCB),
    tertiary = Color(0xFF2DD4BF),
    onTertiary = Color(0xFF042F2E),
    tertiaryContainer = Color(0xFF115E59),
    onTertiaryContainer = Color(0xFFCCFBF1),
    background = Color(0xFF051C12),
    onBackground = Color(0xFFD1FAE5),
    surface = Color(0xFF051C12),
    onSurface = Color(0xFFD1FAE5)
)

// Cobalt Electric
private val CobaltLight = lightColorScheme(
    primary = Color(0xFF1D4ED8),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFDBEAFE),
    onPrimaryContainer = Color(0xFF1E3A8A),
    secondary = Color(0xFF0284C7),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE0F2FE),
    onSecondaryContainer = Color(0xFF075985),
    tertiary = Color(0xFF4F46E5),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFE0E7FF),
    onTertiaryContainer = Color(0xFF312E81),
    background = Color(0xFFEFF6FF),
    onBackground = Color(0xFF172554),
    surface = Color(0xFFEFF6FF),
    onSurface = Color(0xFF172554)
)

private val CobaltDark = darkColorScheme(
    primary = Color(0xFF60A5FA),
    onPrimary = Color(0xFF172554),
    primaryContainer = Color(0xFF1E40AF),
    onPrimaryContainer = Color(0xFFDBEAFE),
    secondary = Color(0xFF38BDF8),
    onSecondary = Color(0xFF082F49),
    secondaryContainer = Color(0xFF0369A1),
    onSecondaryContainer = Color(0xFFE0F2FE),
    tertiary = Color(0xFF818CF8),
    onTertiary = Color(0xFF1E1B4B),
    tertiaryContainer = Color(0xFF3730A3),
    onTertiaryContainer = Color(0xFFE0E7FF),
    background = Color(0xFF0B132B),
    onBackground = Color(0xFFDBEAFE),
    surface = Color(0xFF0B132B),
    onSurface = Color(0xFFDBEAFE)
)

// Midnight Chrome
private val MidnightLight = lightColorScheme(
    primary = Color(0xFF1E293B),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE2E8F0),
    onPrimaryContainer = Color(0xFF0F172A),
    secondary = Color(0xFF475569),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFF1F5F9),
    onSecondaryContainer = Color(0xFF1E293B),
    tertiary = Color(0xFF64748B),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFF8FAFC),
    onTertiaryContainer = Color(0xFF334155),
    background = Color(0xFFF8FAFC),
    onBackground = Color(0xFF0F172A),
    surface = Color(0xFFF8FAFC),
    onSurface = Color(0xFF0F172A)
)

private val MidnightDark = darkColorScheme(
    primary = Color(0xFF94A3B8),
    onPrimary = Color(0xFF0F172A),
    primaryContainer = Color(0xFF334155),
    onPrimaryContainer = Color(0xFFF1F5F9),
    secondary = Color(0xFFCBD5E1),
    onSecondary = Color(0xFF1E293B),
    secondaryContainer = Color(0xFF475569),
    onSecondaryContainer = Color(0xFFF8FAFC),
    tertiary = Color(0xFFE2E8F0),
    onTertiary = Color(0xFF0F172A),
    tertiaryContainer = Color(0xFF64748B),
    onTertiaryContainer = Color(0xFFFFFFFF),
    background = Color(0xFF090D16),
    onBackground = Color(0xFFE2E8F0),
    surface = Color(0xFF090D16),
    onSurface = Color(0xFFE2E8F0)
)

// Electric Sunset
private val ElectricSunsetLight = lightColorScheme(
    primary = Color(0xFFD84315),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFCCBC),
    onPrimaryContainer = Color(0xFF3E2723),
    secondary = Color(0xFFC2185B),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFF8BBD0),
    onSecondaryContainer = Color(0xFF3E1124),
    tertiary = Color(0xFFFF6F00),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFECB3),
    onTertiaryContainer = Color(0xFF3E2000),
    background = Color(0xFFFFFBF9),
    onBackground = Color(0xFF261914),
    surface = Color(0xFFFFFBF9),
    onSurface = Color(0xFF261914)
)

private val ElectricSunsetDark = darkColorScheme(
    primary = Color(0xFFFF8A65),
    onPrimary = Color(0xFF5D1000),
    primaryContainer = Color(0xFF8C2200),
    onPrimaryContainer = Color(0xFFFFCCBC),
    secondary = Color(0xFFF48FB1),
    onSecondary = Color(0xFF560027),
    secondaryContainer = Color(0xFF7B003A),
    onSecondaryContainer = Color(0xFFF8BBD0),
    tertiary = Color(0xFFFFD54F),
    onTertiary = Color(0xFF4A3400),
    tertiaryContainer = Color(0xFF704E00),
    onTertiaryContainer = Color(0xFFFFECB3),
    background = Color(0xFF19110E),
    onBackground = Color(0xFFF5EBE6),
    surface = Color(0xFF19110E),
    onSurface = Color(0xFFF5EBE6)
)

// Aurora Cyber
private val AuroraCyberLight = lightColorScheme(
    primary = Color(0xFF007799),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFB3E5FC),
    onPrimaryContainer = Color(0xFF002233),
    secondary = Color(0xFF007E33),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFB9F6CA),
    onSecondaryContainer = Color(0xFF003314),
    tertiary = Color(0xFF00838F),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF80DEEA),
    onTertiaryContainer = Color(0xFF00272B),
    background = Color(0xFFF4FAF9),
    onBackground = Color(0xFF101B18),
    surface = Color(0xFFF4FAF9),
    onSurface = Color(0xFF101B18)
)

private val AuroraCyberDark = darkColorScheme(
    primary = Color(0xFF4DD0E1),
    onPrimary = Color(0xFF00363D),
    primaryContainer = Color(0xFF004D56),
    onPrimaryContainer = Color(0xFFB2EBF2),
    secondary = Color(0xFF69F0AE),
    onSecondary = Color(0xFF003816),
    secondaryContainer = Color(0xFF005A25),
    onSecondaryContainer = Color(0xFFB9F6CA),
    tertiary = Color(0xFF80CBC4),
    onTertiary = Color(0xFF00332E),
    tertiaryContainer = Color(0xFF004D40),
    onTertiaryContainer = Color(0xFFE0F2F1),
    background = Color(0xFF0B1413),
    onBackground = Color(0xFFE0ECE9),
    surface = Color(0xFF0B1413),
    onSurface = Color(0xFFE0ECE9)
)

// Cherry Blossom
private val CherryBloomLight = lightColorScheme(
    primary = Color(0xFFAD1457),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFF8BBD0),
    onPrimaryContainer = Color(0xFF3E001D),
    secondary = Color(0xFFE65100),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFE0B2),
    onSecondaryContainer = Color(0xFF3E1500),
    tertiary = Color(0xFF880E4F),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFD1DC),
    onTertiaryContainer = Color(0xFF33001C),
    background = Color(0xFFFFF9FA),
    onBackground = Color(0xFF22161B),
    surface = Color(0xFFFFF9FA),
    onSurface = Color(0xFF22161B)
)

private val CherryBloomDark = darkColorScheme(
    primary = Color(0xFFF48FB1),
    onPrimary = Color(0xFF560027),
    primaryContainer = Color(0xFF7B003A),
    onPrimaryContainer = Color(0xFFF8BBD0),
    secondary = Color(0xFFFFB74D),
    onSecondary = Color(0xFF502400),
    secondaryContainer = Color(0xFF723600),
    onSecondaryContainer = Color(0xFFFFE0B2),
    tertiary = Color(0xFFFF80AB),
    onTertiary = Color(0xFF5B002B),
    tertiaryContainer = Color(0xFF82003F),
    onTertiaryContainer = Color(0xFFFFD1DC),
    background = Color(0xFF160D11),
    onBackground = Color(0xFFF2E4E9),
    surface = Color(0xFF160D11),
    onSurface = Color(0xFFF2E4E9)
)

// Royal Amethyst
private val RoyalAmethystLight = lightColorScheme(
    primary = Color(0xFF6A1B9A),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE1BEE7),
    onPrimaryContainer = Color(0xFF27003B),
    secondary = Color(0xFF8E24AA),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFF3E5F5),
    onSecondaryContainer = Color(0xFF31003D),
    tertiary = Color(0xFF4A148C),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFD1C4E9),
    onTertiaryContainer = Color(0xFF1B003B),
    background = Color(0xFFFCF8FF),
    onBackground = Color(0xFF1E1724),
    surface = Color(0xFFFCF8FF),
    onSurface = Color(0xFF1E1724)
)

private val RoyalAmethystDark = darkColorScheme(
    primary = Color(0xFFCE93D8),
    onPrimary = Color(0xFF3B0054),
    primaryContainer = Color(0xFF550077),
    onPrimaryContainer = Color(0xFFE1BEE7),
    secondary = Color(0xFFBA68C8),
    onSecondary = Color(0xFF420054),
    secondaryContainer = Color(0xFF63007C),
    onSecondaryContainer = Color(0xFFF3E5F5),
    tertiary = Color(0xFFB39DDB),
    onTertiary = Color(0xFF260057),
    tertiaryContainer = Color(0xFF38007E),
    onTertiaryContainer = Color(0xFFD1C4E9),
    background = Color(0xFF140D18),
    onBackground = Color(0xFFEDE4F1),
    surface = Color(0xFF140D18),
    onSurface = Color(0xFFEDE4F1)
)

// Cosmic Turquoise
private val CosmicTurquoiseLight = lightColorScheme(
    primary = Color(0xFF007A99),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFB2EBF2),
    onPrimaryContainer = Color(0xFF002731),
    secondary = Color(0xFFE64A19),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFCCBC),
    onSecondaryContainer = Color(0xFF3E1200),
    tertiary = Color(0xFF00897B),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFB2DFDB),
    onTertiaryContainer = Color(0xFF002824),
    background = Color(0xFFF3FAFB),
    onBackground = Color(0xFF101C1E),
    surface = Color(0xFFF3FAFB),
    onSurface = Color(0xFF101C1E)
)

private val CosmicTurquoiseDark = darkColorScheme(
    primary = Color(0xFF4DD0E1),
    onPrimary = Color(0xFF003643),
    primaryContainer = Color(0xFF004E60),
    onPrimaryContainer = Color(0xFFB2EBF2),
    secondary = Color(0xFFFF8A65),
    onSecondary = Color(0xFF561500),
    secondaryContainer = Color(0xFF7A2100),
    onSecondaryContainer = Color(0xFFFFCCBC),
    tertiary = Color(0xFF4DB6AC),
    onTertiary = Color(0xFF003731),
    tertiaryContainer = Color(0xFF004F47),
    onTertiaryContainer = Color(0xFFB2DFDB),
    background = Color(0xFF0C1416),
    onBackground = Color(0xFFDEEBEE),
    surface = Color(0xFF0C1416),
    onSurface = Color(0xFFDEEBEE)
)

fun getCustomColorScheme(seed: ColorSeed, isDark: Boolean): ColorScheme {
    return when (seed) {
        ColorSeed.EXPRESSIVE_VIBRANT -> if (isDark) VibrantDark else VibrantLight
        ColorSeed.OCEANIC_INDIGO -> if (isDark) OceanicDark else OceanicLight
        ColorSeed.FOREST_JADE -> if (isDark) ForestDark else ForestLight
        ColorSeed.SUNSET_AMBER -> if (isDark) SunsetDark else SunsetLight
        ColorSeed.TERRACOTTA -> if (isDark) TerracottaDark else TerracottaLight
        ColorSeed.NEON_CYBER -> if (isDark) CyberDark else CyberLight
        ColorSeed.CORAL_BLUSH -> if (isDark) CoralDark else CoralLight
        ColorSeed.MINT_LIME -> if (isDark) MintDark else MintLight
        ColorSeed.COBALT_AURA -> if (isDark) CobaltDark else CobaltLight
        ColorSeed.MIDNIGHT_OBSIDIAN -> if (isDark) MidnightDark else MidnightLight
        ColorSeed.ELECTRIC_SUNSET -> if (isDark) ElectricSunsetDark else ElectricSunsetLight
        ColorSeed.AURORA_CYBER -> if (isDark) AuroraCyberDark else AuroraCyberLight
        ColorSeed.CHERRY_BLOSSOM -> if (isDark) CherryBloomDark else CherryBloomLight
        ColorSeed.ROYAL_AMETHYST -> if (isDark) RoyalAmethystDark else RoyalAmethystLight
        ColorSeed.COSMIC_TURQUOISE -> if (isDark) CosmicTurquoiseDark else CosmicTurquoiseLight
    }
}


