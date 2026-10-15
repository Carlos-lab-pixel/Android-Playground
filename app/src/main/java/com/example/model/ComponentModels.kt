package com.example.model

enum class ComponentCategory(val title: String) {
    ACTIONS("Actions"),
    SELECTION("Selection"),
    TEXT_INPUT("Text Input"),
    NAVIGATION("Navigation"),
    COMMUNICATION("Communication"),
    CONTAINMENT("Containment"),
    PROGRESS("Progress"),
    PICKERS("Pickers"),
    EXPRESSIVE("Expressive")
}

data class ComponentItem(
    val id: String,
    val name: String,
    val category: ComponentCategory,
    val description: String,
    val m3Api: String,
    val isExperimental: Boolean = false,
    val minSdk: Int = 24,
    val defaultLabel: String = "Interactive Component",
    val variants: List<String> = emptyList(),
    val supportsIcon: Boolean = true,
    val supportsShape: Boolean = true,
    val supportsSlider: Boolean = false,
    val defaultTextValue: String = "",
    val documentationSnippet: String = ""
)

data class PlaygroundConfig(
    val label: String = "Click Me",
    val enabled: Boolean = true,
    val showIcon: Boolean = true,
    val selectedVariant: String = "Default",
    val cornerRadiusDp: Float = 16f,
    val isChecked: Boolean = true,
    val sliderValue: Float = 0.5f,
    val textValue: String = "Editable text",
    val isError: Boolean = false,
    val showSupportingText: Boolean = true,
    val clickCount: Int = 0,
    val isExpanded: Boolean = false
)
