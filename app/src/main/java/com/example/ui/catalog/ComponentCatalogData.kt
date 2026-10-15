package com.example.ui.catalog

import com.example.model.ComponentCategory
import com.example.model.ComponentItem

object ComponentCatalogData {

    val allComponents: List<ComponentItem> = listOf(
        // ACTIONS
        ComponentItem(
            id = "button",
            name = "Button (Filled)",
            category = ComponentCategory.ACTIONS,
            description = "Standard high-emphasis primary button for principal user actions.",
            m3Api = "androidx.compose.material3.Button",
            defaultLabel = "Filled Button",
            variants = listOf("Filled", "Tonal", "Outlined", "Elevated", "Text"),
            supportsIcon = true,
            supportsShape = true,
            documentationSnippet = """
                Button(
                    onClick = { /* handle action */ },
                    shape = RoundedCornerShape(cornerDp.dp)
                ) {
                    Icon(Icons.Filled.ThumbUp, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Button")
                }
            """.trimIndent()
        ),
        ComponentItem(
            id = "filled_tonal_button",
            name = "Filled Tonal Button",
            category = ComponentCategory.ACTIONS,
            description = "Medium-emphasis alternative to filled buttons for secondary important actions.",
            m3Api = "androidx.compose.material3.FilledTonalButton",
            defaultLabel = "Tonal Action",
            variants = listOf("Standard", "With Icon"),
            supportsIcon = true,
            documentationSnippet = """
                FilledTonalButton(onClick = { /* ... */ }) {
                    Text("Filled Tonal Button")
                }
            """.trimIndent()
        ),
        ComponentItem(
            id = "outlined_button",
            name = "Outlined Button",
            category = ComponentCategory.ACTIONS,
            description = "Medium-emphasis button featuring a distinct border stroke.",
            m3Api = "androidx.compose.material3.OutlinedButton",
            defaultLabel = "Outlined Button",
            variants = listOf("Standard", "With Icon"),
            supportsIcon = true,
            documentationSnippet = """
                OutlinedButton(onClick = { /* ... */ }) {
                    Text("Outlined Button")
                }
            """.trimIndent()
        ),
        ComponentItem(
            id = "elevated_button",
            name = "Elevated Button",
            category = ComponentCategory.ACTIONS,
            description = "Button with a shadow elevation for distinguishing from flat surfaces.",
            m3Api = "androidx.compose.material3.ElevatedButton",
            defaultLabel = "Elevated Action",
            supportsIcon = true,
            documentationSnippet = """
                ElevatedButton(onClick = { /* ... */ }) {
                    Text("Elevated Button")
                }
            """.trimIndent()
        ),
        ComponentItem(
            id = "text_button",
            name = "Text Button",
            category = ComponentCategory.ACTIONS,
            description = "Low-emphasis button with no outline or container fill.",
            m3Api = "androidx.compose.material3.TextButton",
            defaultLabel = "Text Action",
            supportsIcon = true,
            documentationSnippet = """
                TextButton(onClick = { /* ... */ }) {
                    Text("Text Button")
                }
            """.trimIndent()
        ),
        ComponentItem(
            id = "icon_button",
            name = "Icon Button",
            category = ComponentCategory.ACTIONS,
            description = "Compact icon-only action button with standard, filled, and tonal styles.",
            m3Api = "androidx.compose.material3.IconButton",
            defaultLabel = "Favorite",
            variants = listOf("Standard", "Filled", "FilledTonal", "Outlined"),
            supportsIcon = true,
            documentationSnippet = """
                IconButton(onClick = { /* ... */ }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                }
            """.trimIndent()
        ),
        ComponentItem(
            id = "floating_action_button",
            name = "Floating Action Button (FAB)",
            category = ComponentCategory.ACTIONS,
            description = "Primary screen action button in standard, small, large, and extended sizes.",
            m3Api = "androidx.compose.material3.FloatingActionButton",
            defaultLabel = "Create",
            variants = listOf("Standard", "Small", "Large", "Extended"),
            supportsIcon = true,
            supportsShape = true,
            documentationSnippet = """
                FloatingActionButton(
                    onClick = { /* ... */ },
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            """.trimIndent()
        ),
        ComponentItem(
            id = "segmented_button",
            name = "Segmented Button",
            category = ComponentCategory.ACTIONS,
            description = "Compact group of mutually exclusive or multi-select option buttons.",
            m3Api = "androidx.compose.material3.SingleChoiceSegmentedButtonRow",
            defaultLabel = "Daily, Weekly, Monthly",
            variants = listOf("Day", "Week", "Month"),
            documentationSnippet = """
                SingleChoiceSegmentedButtonRow {
                    SegmentedButton(
                        selected = selectedIndex == 0,
                        onClick = { selectedIndex = 0 },
                        shape = SegmentedButtonDefaults.itemShape(index = 0, count = 3)
                    ) { Text("Day") }
                }
            """.trimIndent()
        ),

        // SELECTION
        ComponentItem(
            id = "checkbox",
            name = "Checkbox",
            category = ComponentCategory.SELECTION,
            description = "Allows users to select one or multiple items from a list.",
            m3Api = "androidx.compose.material3.Checkbox",
            defaultLabel = "Accept Material 3 Expressive guidelines",
            documentationSnippet = """
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it }
                )
            """.trimIndent()
        ),
        ComponentItem(
            id = "tri_state_checkbox",
            name = "Tri-State Checkbox",
            category = ComponentCategory.SELECTION,
            description = "Checkbox supporting On, Off, and Indeterminate states.",
            m3Api = "androidx.compose.material3.TriStateCheckbox",
            defaultLabel = "Indeterminate Selection",
            variants = listOf("On", "Off", "Indeterminate"),
            documentationSnippet = """
                TriStateCheckbox(
                    state = ToggleableState.Indeterminate,
                    onClick = { /* advance state */ }
                )
            """.trimIndent()
        ),
        ComponentItem(
            id = "switch",
            name = "Switch",
            category = ComponentCategory.SELECTION,
            description = "Toggle switch for binary on/off options with expressive thumb icons.",
            m3Api = "androidx.compose.material3.Switch",
            defaultLabel = "Enable Expressive Motion",
            supportsIcon = true,
            documentationSnippet = """
                Switch(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    thumbContent = { Icon(Icons.Filled.Check, null) }
                )
            """.trimIndent()
        ),
        ComponentItem(
            id = "radio_button",
            name = "Radio Button",
            category = ComponentCategory.SELECTION,
            description = "Single-choice selection control within a mutually exclusive set.",
            m3Api = "androidx.compose.material3.RadioButton",
            defaultLabel = "Option A",
            variants = listOf("Option 1", "Option 2", "Option 3"),
            documentationSnippet = """
                RadioButton(
                    selected = selected,
                    onClick = { selected = true }
                )
            """.trimIndent()
        ),
        ComponentItem(
            id = "slider",
            name = "Slider",
            category = ComponentCategory.SELECTION,
            description = "Continuous or stepped track control for selecting a numeric value.",
            m3Api = "androidx.compose.material3.Slider",
            defaultLabel = "Volume Level",
            supportsSlider = true,
            variants = listOf("Continuous", "Discrete (5 steps)"),
            documentationSnippet = """
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = 0f..1f,
                    steps = 4
                )
            """.trimIndent()
        ),
        ComponentItem(
            id = "chips",
            name = "Chips (Filter, Assist, Input)",
            category = ComponentCategory.SELECTION,
            description = "Compact interactive elements representing attributes, filters, or inputs.",
            m3Api = "androidx.compose.material3.FilterChip",
            defaultLabel = "Expressive Tag",
            variants = listOf("FilterChip", "AssistChip", "InputChip", "SuggestionChip"),
            supportsIcon = true,
            documentationSnippet = """
                FilterChip(
                    selected = isSelected,
                    onClick = { isSelected = !isSelected },
                    label = { Text("Filter Tag") },
                    leadingIcon = { Icon(Icons.Filled.Done, null) }
                )
            """.trimIndent()
        ),

        // TEXT INPUT
        ComponentItem(
            id = "text_field",
            name = "TextField (Filled)",
            category = ComponentCategory.TEXT_INPUT,
            description = "Filled background text entry field with animated floating label.",
            m3Api = "androidx.compose.material3.TextField",
            defaultLabel = "Full Name",
            defaultTextValue = "Alex Johnson",
            supportsIcon = true,
            documentationSnippet = """
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Label") },
                    leadingIcon = { Icon(Icons.Filled.Person, null) },
                    supportingText = { Text("Helper text") }
                )
            """.trimIndent()
        ),
        ComponentItem(
            id = "outlined_text_field",
            name = "Outlined TextField",
            category = ComponentCategory.TEXT_INPUT,
            description = "Text entry field featuring a clean bounding border line.",
            m3Api = "androidx.compose.material3.OutlinedTextField",
            defaultLabel = "Email Address",
            defaultTextValue = "alex@example.com",
            supportsIcon = true,
            documentationSnippet = """
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Email") },
                    isError = isError,
                    leadingIcon = { Icon(Icons.Filled.Email, null) }
                )
            """.trimIndent()
        ),

        // NAVIGATION
        ComponentItem(
            id = "navigation_bar",
            name = "Navigation Bar",
            category = ComponentCategory.NAVIGATION,
            description = "Bottom navigation container with active indicator pills and labels.",
            m3Api = "androidx.compose.material3.NavigationBar",
            defaultLabel = "Bottom Navigation",
            documentationSnippet = """
                NavigationBar {
                    NavigationBarItem(
                        selected = selected,
                        onClick = { /* navigate */ },
                        icon = { Icon(Icons.Filled.Home, null) },
                        label = { Text("Home") }
                    )
                }
            """.trimIndent()
        ),
        ComponentItem(
            id = "tabs",
            name = "Primary & Secondary Tab Row",
            category = ComponentCategory.NAVIGATION,
            description = "Horizontal tab row organizing content across parallel screens.",
            m3Api = "androidx.compose.material3.PrimaryTabRow",
            defaultLabel = "Tab Navigation",
            variants = listOf("Primary Tab Row", "Secondary Tab Row"),
            documentationSnippet = """
                PrimaryTabRow(selectedTabIndex = tabIndex) {
                    Tab(
                        selected = tabIndex == 0,
                        onClick = { tabIndex = 0 },
                        text = { Text("Overview") }
                    )
                }
            """.trimIndent()
        ),

        // COMMUNICATION
        ComponentItem(
            id = "badge",
            name = "Badge & BadgedBox",
            category = ComponentCategory.COMMUNICATION,
            description = "Dynamic notification badge attached to navigation or action icons.",
            m3Api = "androidx.compose.material3.BadgedBox",
            defaultLabel = "99+",
            documentationSnippet = """
                BadgedBox(
                    badge = { Badge { Text("5") } }
                ) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Alerts")
                }
            """.trimIndent()
        ),
        ComponentItem(
            id = "alert_dialog",
            name = "Alert Dialog",
            category = ComponentCategory.COMMUNICATION,
            description = "Modal prompt interrupting the user to confirm a critical action.",
            m3Api = "androidx.compose.material3.AlertDialog",
            defaultLabel = "Confirm Action",
            documentationSnippet = """
                AlertDialog(
                    onDismissRequest = { /* dismiss */ },
                    confirmButton = { TextButton(onClick = {}) { Text("Confirm") } },
                    dismissButton = { TextButton(onClick = {}) { Text("Cancel") } },
                    title = { Text("Dialog Title") },
                    text = { Text("Material 3 Expressive dialog message.") }
                )
            """.trimIndent()
        ),
        ComponentItem(
            id = "progress_indicators",
            name = "Progress Indicators",
            category = ComponentCategory.PROGRESS,
            description = "Circular and linear animated progress indicators in determinate or indeterminate mode.",
            m3Api = "androidx.compose.material3.LinearProgressIndicator",
            defaultLabel = "Loading Progress",
            variants = listOf("Circular", "Linear", "Indeterminate"),
            supportsSlider = true,
            documentationSnippet = """
                LinearProgressIndicator(
                    progress = { progressFloat },
                    modifier = Modifier.fillMaxWidth()
                )
            """.trimIndent()
        ),

        // CONTAINMENT & LAYOUT
        ComponentItem(
            id = "card",
            name = "Card (Elevated, Filled, Outlined)",
            category = ComponentCategory.CONTAINMENT,
            description = "Surface container holding related content with distinct M3 elevation and borders.",
            m3Api = "androidx.compose.material3.Card",
            defaultLabel = "Interactive M3 Card",
            variants = listOf("Filled", "Elevated", "Outlined"),
            supportsShape = true,
            documentationSnippet = """
                ElevatedCard(
                    shape = RoundedCornerShape(cornerDp.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Card Title", style = MaterialTheme.typography.titleMedium)
                        Text("Expressive card surface.")
                    }
                }
            """.trimIndent()
        ),
        ComponentItem(
            id = "list_item",
            name = "ListItem",
            category = ComponentCategory.CONTAINMENT,
            description = "Standardized row with leading icon/avatar, headlines, and trailing control.",
            m3Api = "androidx.compose.material3.ListItem",
            defaultLabel = "Material 3 Item",
            documentationSnippet = """
                ListItem(
                    headlineContent = { Text("List Item Headline") },
                    supportingContent = { Text("Supporting detail line") },
                    leadingContent = { Icon(Icons.Filled.Star, null) },
                    trailingContent = { Switch(checked = true, onCheckedChange = {}) }
                )
            """.trimIndent()
        ),
        ComponentItem(
            id = "bottom_sheet",
            name = "Modal Bottom Sheet",
            category = ComponentCategory.CONTAINMENT,
            description = "Sheet sliding up from the bottom edge containing contextual tasks.",
            m3Api = "androidx.compose.material3.ModalBottomSheet",
            defaultLabel = "Open Sheet",
            documentationSnippet = """
                ModalBottomSheet(onDismissRequest = { showSheet = false }) {
                    Text("Bottom Sheet Content", Modifier.padding(24.dp))
                }
            """.trimIndent()
        ),

        // EXPRESSIVE
        ComponentItem(
            id = "expressive_morphing",
            name = "Expressive Morphing Container",
            category = ComponentCategory.EXPRESSIVE,
            description = "Dynamic container smoothly animating size, corner geometry, and color using spring specs.",
            m3Api = "androidx.compose.animation.animateContentSize",
            defaultLabel = "Tap to Morph",
            supportsShape = true,
            documentationSnippet = """
                Box(
                    modifier = Modifier
                        .animateContentSize(spring(dampingRatio = 0.6f, stiffness = 400f))
                        .clip(RoundedCornerShape(cornerSize))
                        .background(color)
                )
            """.trimIndent()
        )
    )

    fun getById(id: String): ComponentItem? = allComponents.find { it.id == id }
}
