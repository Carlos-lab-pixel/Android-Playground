package com.example.ui.settings

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.UserPreferencesRepository
import com.example.model.AppThemeState
import com.example.model.ThemeMode
import com.example.ui.theme.MaterialCornerRadius
import com.example.ui.theme.MaterialSpacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    themeState: AppThemeState,
    preferencesRepo: UserPreferencesRepository,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.settings_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.testTag("settings_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = MaterialSpacing.space200, vertical = MaterialSpacing.space150)
                .padding(bottom = MaterialSpacing.space500),
            verticalArrangement = Arrangement.spacedBy(MaterialSpacing.space250)
        ) {
            // 1. Theme Mode
            SettingsGroup(title = "Appearance & Theme", icon = Icons.Filled.Palette) {
                Text(
                    text = "Theme Display Mode",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(MaterialSpacing.space100))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(MaterialSpacing.space100)
                ) {
                    FilterChip(
                        selected = themeState.themeMode == ThemeMode.SYSTEM,
                        onClick = { scope.launch { preferencesRepo.updateThemeMode(ThemeMode.SYSTEM) } },
                        label = { Text("System") },
                        modifier = Modifier.testTag("theme_mode_system")
                    )
                    FilterChip(
                        selected = themeState.themeMode == ThemeMode.LIGHT,
                        onClick = { scope.launch { preferencesRepo.updateThemeMode(ThemeMode.LIGHT) } },
                        label = { Text("Light") },
                        modifier = Modifier.testTag("theme_mode_light")
                    )
                    FilterChip(
                        selected = themeState.themeMode == ThemeMode.DARK,
                        onClick = { scope.launch { preferencesRepo.updateThemeMode(ThemeMode.DARK) } },
                        label = { Text("Dark") },
                        modifier = Modifier.testTag("theme_mode_dark")
                    )
                }
            }

            // 2. Language Selection
            SettingsGroup(title = stringResource(R.string.settings_language), icon = Icons.Filled.Language) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(MaterialSpacing.space100)
                ) {
                    FilterChip(
                        selected = themeState.languageCode == "en",
                        onClick = { scope.launch { preferencesRepo.updateLanguage("en") } },
                        label = { Text(stringResource(R.string.settings_language_en)) },
                        modifier = Modifier.testTag("lang_en")
                    )
                    FilterChip(
                        selected = themeState.languageCode == "de",
                        onClick = { scope.launch { preferencesRepo.updateLanguage("de") } },
                        label = { Text(stringResource(R.string.settings_language_de)) },
                        modifier = Modifier.testTag("lang_de")
                    )
                }
            }

            // 3. Tactile Haptics Preferences
            SettingsGroup(title = "Tactile Hardware Feedback", icon = Icons.Filled.Vibration) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.haptics_master_toggle),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Play vibrations on interactions across the playground",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = themeState.isHapticsEnabled,
                        onCheckedChange = { scope.launch { preferencesRepo.updateHapticsEnabled(it) } },
                        modifier = Modifier.testTag("settings_haptics_toggle")
                    )
                }

                if (themeState.isHapticsEnabled) {
                    Spacer(Modifier.height(MaterialSpacing.space100))
                    Text(
                        text = "Global Intensity (${(themeState.hapticIntensity * 100).toInt()}%)",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Slider(
                        value = themeState.hapticIntensity,
                        onValueChange = { scope.launch { preferencesRepo.updateHapticIntensity(it) } },
                        valueRange = 0.1f..1f,
                        modifier = Modifier.testTag("settings_haptic_slider")
                    )
                }
            }

            // 4. Motion Physics Preferences
            SettingsGroup(title = "Motion & Animation", icon = Icons.Filled.Speed) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Reduce Motion",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Minimize bouncy springs and large transitions for accessibility",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = themeState.isReducedMotion,
                        onCheckedChange = { scope.launch { preferencesRepo.updateReducedMotion(it) } },
                        modifier = Modifier.testTag("settings_motion_toggle")
                    )
                }
            }

            // 5. About & Specification Information
            SettingsGroup(title = stringResource(R.string.settings_about_title), icon = Icons.Filled.Info) {
                Text(
                    text = stringResource(R.string.settings_about_desc),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(MaterialSpacing.space100))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(MaterialCornerRadius.Small),
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text(
                            text = "Google Sans Flex Typeface",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(horizontal = MaterialSpacing.space100, vertical = MaterialSpacing.space50)
                        )
                    }

                    Text(
                        text = stringResource(R.string.settings_version),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsGroup(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(MaterialCornerRadius.LargeIncreased),
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(MaterialSpacing.space200),
            verticalArrangement = Arrangement.spacedBy(MaterialSpacing.space100)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialSpacing.space100)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f),
                modifier = Modifier.padding(vertical = MaterialSpacing.space50)
            )
            content()
        }
    }
}
