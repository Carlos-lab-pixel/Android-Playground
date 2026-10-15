package com.example.ui.playground

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.ComponentItem
import com.example.model.PlaygroundConfig
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaygroundScreen(
    item: ComponentItem,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var config by remember(item.id) {
        mutableStateOf(
            PlaygroundConfig(
                label = item.defaultLabel,
                textValue = item.defaultTextValue.ifBlank { "Editable sample text" },
                selectedVariant = item.variants.firstOrNull() ?: "Default"
            )
        )
    }

    val generatedCode = remember(item, config) {
        PlaygroundCodeGenerator.generateCode(item, config)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(item.name) },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("playground_back_button")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            config = PlaygroundConfig(
                                label = item.defaultLabel,
                                textValue = item.defaultTextValue.ifBlank { "Sample text" },
                                selectedVariant = item.variants.firstOrNull() ?: "Default"
                            )
                            scope.launch { snackbarHostState.showSnackbar("Configuration reset to defaults") }
                        },
                        modifier = Modifier.testTag("playground_reset_button")
                    ) {
                        Icon(Icons.Filled.Refresh, contentDescription = stringResource(R.string.playground_reset))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 1. Live Preview Section
            Text(
                text = stringResource(R.string.playground_preview),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            ComponentPreviewContent(
                item = item,
                config = config,
                onConfigChange = { config = it }
            )

            // 2. Interactive Controls
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = stringResource(R.string.playground_controls),
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Label edit
                    OutlinedTextField(
                        value = config.label,
                        onValueChange = { config = config.copy(label = it) },
                        label = { Text(stringResource(R.string.playground_label_text)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Enabled toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(stringResource(R.string.playground_enabled))
                        Switch(
                            checked = config.enabled,
                            onCheckedChange = { config = config.copy(enabled = it) }
                        )
                    }

                    // Icon toggle if supported
                    if (item.supportsIcon) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(stringResource(R.string.playground_show_icon))
                            Switch(
                                checked = config.showIcon,
                                onCheckedChange = { config = config.copy(showIcon = it) }
                            )
                        }
                    }

                    // Variants selector
                    if (item.variants.isNotEmpty()) {
                        Column {
                            Text(
                                text = stringResource(R.string.playground_variant),
                                style = MaterialTheme.typography.labelLarge
                            )
                            Spacer(Modifier.height(8.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                item.variants.forEach { variant ->
                                    FilterChip(
                                        selected = config.selectedVariant == variant,
                                        onClick = { config = config.copy(selectedVariant = variant) },
                                        label = { Text(variant) }
                                    )
                                }
                            }
                        }
                    }

                    // Corner Shape slider if supported
                    if (item.supportsShape) {
                        Column {
                            Text(
                                text = "${stringResource(R.string.playground_corner_shape)}: ${config.cornerRadiusDp.toInt()}dp",
                                style = MaterialTheme.typography.labelLarge
                            )
                            Slider(
                                value = config.cornerRadiusDp,
                                onValueChange = { config = config.copy(cornerRadiusDp = it) },
                                valueRange = 0f..32f,
                                steps = 7
                            )
                        }
                    }

                    // Value slider if supported
                    if (item.supportsSlider) {
                        Column {
                            Text(
                                text = "Value: ${(config.sliderValue * 100).toInt()}%",
                                style = MaterialTheme.typography.labelLarge
                            )
                            Slider(
                                value = config.sliderValue,
                                onValueChange = { config = config.copy(sliderValue = it) },
                                valueRange = 0f..1f
                            )
                        }
                    }
                }
            }

            // 3. State Inspector
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = stringResource(R.string.playground_state_inspector),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("• enabled: ${config.enabled}", style = MaterialTheme.typography.bodyMedium)
                    Text("• label: \"${config.label}\"", style = MaterialTheme.typography.bodyMedium)
                    Text("• variant: ${config.selectedVariant}", style = MaterialTheme.typography.bodyMedium)
                    Text("• cornerRadius: ${config.cornerRadiusDp.toInt()}.dp", style = MaterialTheme.typography.bodyMedium)
                    Text("• clickCount: ${config.clickCount}", style = MaterialTheme.typography.bodyMedium)
                    Text("• isChecked / toggled: ${config.isChecked}", style = MaterialTheme.typography.bodyMedium)
                    Text("• sliderProgress: ${String.format("%.2f", config.sliderValue)}", style = MaterialTheme.typography.bodyMedium)
                }
            }

            // 4. Accessibility & Platform Spec
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.playground_accessibility),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Text("• API: ${item.m3Api}", style = MaterialTheme.typography.bodySmall)
                    Text("• Touch Target: Minimum 48dp x 48dp enforced via Material 3 specifications.", style = MaterialTheme.typography.bodySmall)
                    Text("• Semantics: Full TalkBack screen reader support with accessibility roles.", style = MaterialTheme.typography.bodySmall)
                    Text("• Contrast: Meets WCAG AAA compliance across dynamic light and dark palettes.", style = MaterialTheme.typography.bodySmall)
                }
            }

            // 5. Kotlin Implementation Example
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.playground_code_example),
                            style = MaterialTheme.typography.titleSmall
                        )
                        IconButton(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("Jetpack Compose Code", generatedCode)
                                clipboard.setPrimaryClip(clip)
                                scope.launch {
                                    snackbarHostState.showSnackbar("Kotlin code copied to clipboard!")
                                }
                            },
                            modifier = Modifier.testTag("copy_code_button")
                        ) {
                            Icon(Icons.Filled.ContentCopy, contentDescription = stringResource(R.string.playground_copy_code))
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = generatedCode,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(12.dp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}
