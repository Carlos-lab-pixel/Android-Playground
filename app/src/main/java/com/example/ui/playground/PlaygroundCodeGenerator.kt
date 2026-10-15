package com.example.ui.playground

import com.example.model.ComponentItem
import com.example.model.PlaygroundConfig

object PlaygroundCodeGenerator {

    fun generateCode(item: ComponentItem, config: PlaygroundConfig): String {
        val enabledParam = if (!config.enabled) "\n    enabled = false," else ""
        val shapeParam = if (item.supportsShape) "\n    shape = RoundedCornerShape(${config.cornerRadiusDp.toInt()}.dp)," else ""

        return when (item.id) {
            "button" -> {
                val composableName = when (config.selectedVariant) {
                    "Tonal" -> "FilledTonalButton"
                    "Outlined" -> "OutlinedButton"
                    "Elevated" -> "ElevatedButton"
                    "Text" -> "TextButton"
                    else -> "Button"
                }
                """
                // Import: androidx.compose.material3.$composableName
                $composableName(
                    onClick = { /* Handle action */ },$enabledParam$shapeParam
                    modifier = Modifier.padding(8.dp)
                ) {
                    ${if (config.showIcon) "Icon(Icons.Filled.ThumbUp, contentDescription = null)\n    Spacer(Modifier.width(8.dp))\n    " else ""}Text("${config.label}")
                }
                """.trimIndent()
            }
            "floating_action_button" -> {
                val composableName = when (config.selectedVariant) {
                    "Small" -> "SmallFloatingActionButton"
                    "Large" -> "LargeFloatingActionButton"
                    "Extended" -> "ExtendedFloatingActionButton"
                    else -> "FloatingActionButton"
                }
                if (config.selectedVariant == "Extended") {
                    """
                    ExtendedFloatingActionButton(
                        onClick = { /* Action */ },$shapeParam
                        icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                        text = { Text("${config.label}") }
                    )
                    """.trimIndent()
                } else {
                    """
                    $composableName(
                        onClick = { /* Action */ },$shapeParam
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "${config.label}")
                    }
                    """.trimIndent()
                }
            }
            "switch" -> {
                """
                Switch(
                    checked = ${config.isChecked},
                    onCheckedChange = { /* update state */ },$enabledParam
                    ${if (config.showIcon) "thumbContent = if (${config.isChecked}) {\n        { Icon(Icons.Filled.Check, null, Modifier.size(SwitchDefaults.IconSize)) }\n    } else null" else ""}
                )
                """.trimIndent()
            }
            "checkbox" -> {
                """
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = ${config.isChecked},
                        onCheckedChange = { /* update state */ },$enabledParam
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("${config.label}")
                }
                """.trimIndent()
            }
            "slider" -> {
                """
                Column {
                    Text("Value: ${(config.sliderValue * 100).toInt()}%")
                    Slider(
                        value = ${String.format("%.2f", config.sliderValue)}f,
                        onValueChange = { /* update */ },$enabledParam
                        valueRange = 0f..1f${if (config.selectedVariant.contains("Discrete")) ",\n    steps = 4" else ""}
                    )
                }
                """.trimIndent()
            }
            "text_field", "outlined_text_field" -> {
                val composableName = if (item.id == "text_field") "TextField" else "OutlinedTextField"
                """
                $composableName(
                    value = "${config.textValue}",
                    onValueChange = { /* update text */ },$enabledParam
                    label = { Text("${config.label}") },
                    isError = ${config.isError},
                    ${if (config.showIcon) "leadingIcon = { Icon(Icons.Filled.Search, null) }," else ""}
                    ${if (config.showSupportingText) "supportingText = { Text(${if (config.isError) "\"Invalid entry\"" else "\"Helper message\""}) }," else ""}
                    modifier = Modifier.fillMaxWidth()
                )
                """.trimIndent()
            }
            "card" -> {
                val composableName = when (config.selectedVariant) {
                    "Elevated" -> "ElevatedCard"
                    "Outlined" -> "OutlinedCard"
                    else -> "Card"
                }
                """
                $composableName($shapeParam
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("${config.label}", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(4.dp))
                        Text("Material 3 Expressive Container Surface")
                    }
                }
                """.trimIndent()
            }
            "progress_indicators" -> {
                if (config.selectedVariant == "Circular") {
                    """
                    CircularProgressIndicator(
                        progress = { ${String.format("%.2f", config.sliderValue)}f },
                        modifier = Modifier.size(48.dp)
                    )
                    """.trimIndent()
                } else {
                    """
                    LinearProgressIndicator(
                        progress = { ${String.format("%.2f", config.sliderValue)}f },
                        modifier = Modifier.fillMaxWidth()
                    )
                    """.trimIndent()
                }
            }
            else -> item.documentationSnippet
        }
    }
}
