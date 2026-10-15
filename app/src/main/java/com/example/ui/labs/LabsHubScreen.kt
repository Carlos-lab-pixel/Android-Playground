package com.example.ui.labs

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.UserPreferencesRepository
import com.example.model.AppThemeState
import com.example.platform.haptics.HapticsService
import com.example.ui.haptics.HapticsScreen
import com.example.ui.motion.MotionScreen
import com.example.ui.theme.MaterialCornerRadius
import com.example.ui.theme.MaterialMotion
import com.example.ui.theme.MaterialSpacing
import com.example.ui.themestudio.ThemeStudioScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabsHubScreen(
    themeState: AppThemeState,
    preferencesRepo: UserPreferencesRepository,
    hapticsManager: HapticsService,
    onOpenSettings: () -> Unit,
    initialTab: Int = 0,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember(initialTab) { mutableIntStateOf(initialTab) }

    val tabs = listOf(
        Triple(stringResource(R.string.labs_tab_haptics), Icons.Filled.Vibration, "tab_haptics"),
        Triple(stringResource(R.string.labs_tab_motion), Icons.Filled.Speed, "tab_motion"),
        Triple(stringResource(R.string.labs_tab_theme), Icons.Filled.Palette, "tab_theme")
    )

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = stringResource(R.string.labs_title),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = stringResource(R.string.labs_subtitle),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = onOpenSettings,
                    modifier = Modifier.testTag("labs_settings_button")
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(R.string.settings_action)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        // Expressive Tab Navigation Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialSpacing.space200, vertical = MaterialSpacing.space100)
        ) {
            SecondaryTabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clip(RoundedCornerShape(MaterialCornerRadius.Large))
                    .testTag("labs_tab_row")
            ) {
                tabs.forEachIndexed { index, (title, icon, tag) ->
                    val isSelected = selectedTab == index
                    Tab(
                        selected = isSelected,
                        onClick = { selectedTab = index },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(MaterialSpacing.space100)
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                                )
                            }
                        },
                        modifier = Modifier.testTag(tag)
                    )
                }
            }
        }

        // Active Lab Content with smooth cross-fade animation
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            AnimatedContent(
                targetState = selectedTab,
                transitionSpec = {
                    fadeIn(MaterialMotion.EffectsFast) togetherWith fadeOut(MaterialMotion.EffectsFast)
                },
                label = "lab_content_transition"
            ) { targetIndex ->
                when (targetIndex) {
                    0 -> HapticsScreen(hapticsManager = hapticsManager)
                    1 -> MotionScreen()
                    2 -> ThemeStudioScreen(
                        themeState = themeState,
                        preferencesRepo = preferencesRepo
                    )
                }
            }
        }
    }
}
