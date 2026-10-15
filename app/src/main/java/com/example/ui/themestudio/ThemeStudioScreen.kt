package com.example.ui.themestudio

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.UserPreferencesRepository
import com.example.model.AppThemeState
import com.example.model.ColorSeed
import com.example.model.ExpressiveShapeStyle
import com.example.model.ThemeMode
import kotlinx.coroutines.launch

@Composable
fun ThemeStudioScreen(
    themeState: AppThemeState,
    preferencesRepo: UserPreferencesRepository,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var previewSliderValue by remember { mutableFloatStateOf(0.6f) }
    var previewSwitchChecked by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Header
        Column {
            Text(
                text = stringResource(R.string.theme_title),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.theme_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // 1. Color Mode (System, Light, Dark)
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = stringResource(R.string.theme_mode_title),
                    style = MaterialTheme.typography.titleMedium
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FilterChip(
                        selected = themeState.themeMode == ThemeMode.SYSTEM,
                        onClick = { scope.launch { preferencesRepo.updateThemeMode(ThemeMode.SYSTEM) } },
                        label = { Text(stringResource(R.string.theme_mode_system)) },
                        modifier = Modifier.testTag("theme_mode_system")
                    )
                    FilterChip(
                        selected = themeState.themeMode == ThemeMode.LIGHT,
                        onClick = { scope.launch { preferencesRepo.updateThemeMode(ThemeMode.LIGHT) } },
                        label = { Text(stringResource(R.string.theme_mode_light)) },
                        modifier = Modifier.testTag("theme_mode_light")
                    )
                    FilterChip(
                        selected = themeState.themeMode == ThemeMode.DARK,
                        onClick = { scope.launch { preferencesRepo.updateThemeMode(ThemeMode.DARK) } },
                        label = { Text(stringResource(R.string.theme_mode_dark)) },
                        modifier = Modifier.testTag("theme_mode_dark")
                    )
                }
            }
        }

        // 2. Dynamic Wallpaper Theming (Material You)
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Filled.Wallpaper,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(
                                text = stringResource(R.string.theme_dynamic_color),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = stringResource(R.string.theme_dynamic_color_desc),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    Switch(
                        checked = themeState.isDynamicColor,
                        onCheckedChange = { isChecked ->
                            scope.launch { preferencesRepo.updateDynamicColor(isChecked) }
                        },
                        modifier = Modifier.testTag("dynamic_color_switch")
                    )
                }

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                    Text(
                        text = "Note: Wallpaper dynamic colors require Android 12+ (API 31). Fallback custom seed palettes are active below.",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }

        // 3. Custom Color Scheme Seeds
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Palette, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.theme_seed_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Text(
                    text = if (themeState.isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        "Currently using wallpaper colors. Turn off Dynamic Color above to activate custom seed palettes."
                    } else {
                        "Select a vibrant seed palette for M3 tonal elevation:"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ColorSeed.values().forEach { seed ->
                        val isSelected = !themeState.isDynamicColor && themeState.colorSeed == seed
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    scope.launch {
                                        preferencesRepo.updateDynamicColor(false)
                                        preferencesRepo.updateColorSeed(seed)
                                    }
                                }
                                .padding(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(seed.primaryColor)
                                    .then(
                                        if (isSelected) {
                                            Modifier.border(3.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                                        } else Modifier
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isSelected) {
                                    Icon(
                                        Icons.Filled.Check,
                                        contentDescription = "Selected",
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = seed.displayName.split(" ").first(),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }

        // 4. Expressive Corner Shapes
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = stringResource(R.string.theme_shape_title),
                    style = MaterialTheme.typography.titleMedium
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ExpressiveShapeStyle.values().forEach { style ->
                        FilterChip(
                            selected = themeState.shapeStyle == style,
                            onClick = { scope.launch { preferencesRepo.updateShapeStyle(style) } },
                            label = { Text(style.displayName) },
                            modifier = Modifier.testTag("shape_style_${style.name}")
                        )
                    }
                }
            }
        }

        // 5. Live Harmonization Preview
        ElevatedCard(
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.theme_preview_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Filled Primary")
                    }
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Outlined Secondary")
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Harmonized Switch Component")
                    Switch(
                        checked = previewSwitchChecked,
                        onCheckedChange = { previewSwitchChecked = it }
                    )
                }

                Column {
                    Text(
                        text = "Harmonized Slider: ${(previewSliderValue * 100).toInt()}%",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Slider(
                        value = previewSliderValue,
                        onValueChange = { previewSliderValue = it }
                    )
                }
            }
        }

        Spacer(Modifier.height(32.dp))
    }
}
