package com.example.ui.playground

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.example.model.ComponentItem
import com.example.model.PlaygroundConfig

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun ComponentPreviewContent(
    item: ComponentItem,
    config: PlaygroundConfig,
    onConfigChange: (PlaygroundConfig) -> Unit,
    modifier: Modifier = Modifier
) {
    val cornerShape = RoundedCornerShape(config.cornerRadiusDp.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        when (item.id) {
            "button" -> {
                when (config.selectedVariant) {
                    "Tonal" -> FilledTonalButton(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        enabled = config.enabled,
                        shape = cornerShape
                    ) {
                        if (config.showIcon) {
                            Icon(Icons.Filled.ThumbUp, contentDescription = null, Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                        }
                        Text("${config.label} (${config.clickCount})")
                    }
                    "Outlined" -> OutlinedButton(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        enabled = config.enabled,
                        shape = cornerShape
                    ) {
                        if (config.showIcon) {
                            Icon(Icons.Filled.ThumbUp, contentDescription = null, Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                        }
                        Text("${config.label} (${config.clickCount})")
                    }
                    "Elevated" -> ElevatedButton(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        enabled = config.enabled,
                        shape = cornerShape
                    ) {
                        if (config.showIcon) {
                            Icon(Icons.Filled.ThumbUp, contentDescription = null, Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                        }
                        Text("${config.label} (${config.clickCount})")
                    }
                    "Text" -> TextButton(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        enabled = config.enabled
                    ) {
                        if (config.showIcon) {
                            Icon(Icons.Filled.ThumbUp, contentDescription = null, Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                        }
                        Text("${config.label} (${config.clickCount})")
                    }
                    else -> Button(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        enabled = config.enabled,
                        shape = cornerShape
                    ) {
                        if (config.showIcon) {
                            Icon(Icons.Filled.ThumbUp, contentDescription = null, Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                        }
                        Text("${config.label} (${config.clickCount})")
                    }
                }
            }

            "filled_tonal_button" -> {
                FilledTonalButton(
                    onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                    enabled = config.enabled,
                    shape = cornerShape
                ) {
                    if (config.showIcon) {
                        Icon(Icons.Filled.Star, contentDescription = null, Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                    }
                    Text("${config.label} (${config.clickCount})")
                }
            }

            "outlined_button" -> {
                OutlinedButton(
                    onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                    enabled = config.enabled,
                    shape = cornerShape
                ) {
                    if (config.showIcon) {
                        Icon(Icons.Filled.Star, contentDescription = null, Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                    }
                    Text("${config.label} (${config.clickCount})")
                }
            }

            "elevated_button" -> {
                ElevatedButton(
                    onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                    enabled = config.enabled,
                    shape = cornerShape
                ) {
                    if (config.showIcon) {
                        Icon(Icons.Filled.ThumbUp, contentDescription = null, Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                    }
                    Text("${config.label} (${config.clickCount})")
                }
            }

            "text_button" -> {
                TextButton(
                    onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                    enabled = config.enabled
                ) {
                    Text("${config.label} (${config.clickCount})")
                }
            }

            "icon_button" -> {
                when (config.selectedVariant) {
                    "Filled" -> FilledIconButton(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        enabled = config.enabled
                    ) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                    }
                    "FilledTonal" -> FilledTonalIconButton(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        enabled = config.enabled
                    ) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                    }
                    "Outlined" -> OutlinedIconButton(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        enabled = config.enabled
                    ) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                    }
                    else -> IconButton(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        enabled = config.enabled
                    ) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                    }
                }
            }

            "floating_action_button" -> {
                when (config.selectedVariant) {
                    "Small" -> SmallFloatingActionButton(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        shape = cornerShape
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                    "Extended" -> ExtendedFloatingActionButton(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        shape = cornerShape,
                        icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                        text = { Text("${config.label} (${config.clickCount})") }
                    )
                    else -> FloatingActionButton(
                        onClick = { onConfigChange(config.copy(clickCount = config.clickCount + 1)) },
                        shape = cornerShape
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                }
            }

            "segmented_button" -> {
                var selectedIndex by remember { mutableIntStateOf(0) }
                val options = listOf("Day", "Week", "Month")
                SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                    options.forEachIndexed { index, label ->
                        SegmentedButton(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
                        ) {
                            Text(label)
                        }
                    }
                }
            }

            "checkbox" -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onConfigChange(config.copy(isChecked = !config.isChecked)) }
                ) {
                    Checkbox(
                        checked = config.isChecked,
                        onCheckedChange = { onConfigChange(config.copy(isChecked = it)) },
                        enabled = config.enabled
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(config.label, style = MaterialTheme.typography.bodyLarge)
                }
            }

            "tri_state_checkbox" -> {
                var toggleState by remember { mutableStateOf(ToggleableState.Indeterminate) }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        toggleState = when (toggleState) {
                            ToggleableState.Off -> ToggleableState.Indeterminate
                            ToggleableState.Indeterminate -> ToggleableState.On
                            ToggleableState.On -> ToggleableState.Off
                        }
                    }
                ) {
                    TriStateCheckbox(
                        state = toggleState,
                        onClick = {
                            toggleState = when (toggleState) {
                                ToggleableState.Off -> ToggleableState.Indeterminate
                                ToggleableState.Indeterminate -> ToggleableState.On
                                ToggleableState.On -> ToggleableState.Off
                            }
                        },
                        enabled = config.enabled
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("State: ${toggleState.name}")
                }
            }

            "switch" -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(config.label, style = MaterialTheme.typography.bodyLarge)
                    com.example.ui.components.ExpressiveSwitch(
                        checked = config.isChecked,
                        onCheckedChange = { onConfigChange(config.copy(isChecked = it)) },
                        enabled = config.enabled
                    )
                }
            }

            "radio_button" -> {
                var selectedRadio by remember { mutableIntStateOf(0) }
                Column(modifier = Modifier.fillMaxWidth()) {
                    listOf("Expressive Option A", "Expressive Option B", "Expressive Option C").forEachIndexed { index, title ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedRadio = index }
                                .padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = selectedRadio == index,
                                onClick = { selectedRadio = index },
                                enabled = config.enabled
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(title)
                        }
                    }
                }
            }

            "slider" -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Value: ${(config.sliderValue * 100).toInt()}%",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Slider(
                        value = config.sliderValue,
                        onValueChange = { onConfigChange(config.copy(sliderValue = it)) },
                        enabled = config.enabled,
                        steps = if (config.selectedVariant.contains("Discrete")) 4 else 0
                    )
                }
            }

            "chips" -> {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = config.isChecked,
                        onClick = { onConfigChange(config.copy(isChecked = !config.isChecked)) },
                        label = { Text("Filter") },
                        leadingIcon = if (config.isChecked) {
                            { Icon(Icons.Filled.Check, null, Modifier.size(16.dp)) }
                        } else null
                    )
                    AssistChip(
                        onClick = {},
                        label = { Text("Assist") },
                        leadingIcon = { Icon(Icons.Filled.Star, null, Modifier.size(16.dp)) }
                    )
                    InputChip(
                        selected = false,
                        onClick = {},
                        label = { Text("Input") },
                        trailingIcon = { Icon(Icons.Filled.Close, null, Modifier.size(16.dp)) }
                    )
                }
            }

            "text_field" -> {
                TextField(
                    value = config.textValue,
                    onValueChange = { onConfigChange(config.copy(textValue = it)) },
                    label = { Text(config.label) },
                    enabled = config.enabled,
                    isError = config.isError,
                    leadingIcon = if (config.showIcon) {
                        { Icon(Icons.Filled.Person, contentDescription = null) }
                    } else null,
                    supportingText = if (config.showSupportingText) {
                        { Text(if (config.isError) "Input error detected" else "Supporting information") }
                    } else null,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            "outlined_text_field" -> {
                OutlinedTextField(
                    value = config.textValue,
                    onValueChange = { onConfigChange(config.copy(textValue = it)) },
                    label = { Text(config.label) },
                    enabled = config.enabled,
                    isError = config.isError,
                    leadingIcon = if (config.showIcon) {
                        { Icon(Icons.Filled.Search, contentDescription = null) }
                    } else null,
                    supportingText = if (config.showSupportingText) {
                        { Text(if (config.isError) "Input error detected" else "Supporting information") }
                    } else null,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            "navigation_bar" -> {
                var selectedNav by remember { mutableIntStateOf(0) }
                NavigationBar(modifier = Modifier.clip(RoundedCornerShape(16.dp))) {
                    NavigationBarItem(
                        selected = selectedNav == 0,
                        onClick = { selectedNav = 0 },
                        icon = { Icon(Icons.Filled.Favorite, null) },
                        label = { Text("Favorites") }
                    )
                    NavigationBarItem(
                        selected = selectedNav == 1,
                        onClick = { selectedNav = 1 },
                        icon = { Icon(Icons.Filled.Star, null) },
                        label = { Text("Popular") }
                    )
                    NavigationBarItem(
                        selected = selectedNav == 2,
                        onClick = { selectedNav = 2 },
                        icon = { Icon(Icons.Filled.Person, null) },
                        label = { Text("Profile") }
                    )
                }
            }

            "tabs" -> {
                var tabIndex by remember { mutableIntStateOf(0) }
                PrimaryTabRow(selectedTabIndex = tabIndex) {
                    Tab(
                        selected = tabIndex == 0,
                        onClick = { tabIndex = 0 },
                        text = { Text("Overview") }
                    )
                    Tab(
                        selected = tabIndex == 1,
                        onClick = { tabIndex = 1 },
                        text = { Text("Details") }
                    )
                    Tab(
                        selected = tabIndex == 2,
                        onClick = { tabIndex = 2 },
                        text = { Text("Metrics") }
                    )
                }
            }

            "badge" -> {
                BadgedBox(
                    badge = { Badge { Text("${(config.sliderValue * 99).toInt() + 1}") } }
                ) {
                    Icon(
                        Icons.Filled.Notifications,
                        contentDescription = "Alerts",
                        modifier = Modifier.size(36.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            "alert_dialog" -> {
                var showDialog by remember { mutableStateOf(false) }
                Button(onClick = { showDialog = true }, shape = cornerShape) {
                    Text("Launch Alert Dialog")
                }
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Material 3 Expressive") },
                        text = { Text("This is a live interactive AlertDialog using M3 design tokens.") },
                        confirmButton = {
                            Button(onClick = { showDialog = false }) { Text("Acknowledge") }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) { Text("Dismiss") }
                        }
                    )
                }
            }

            "progress_indicators" -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (config.selectedVariant == "Circular") {
                        CircularProgressIndicator(
                            progress = { config.sliderValue },
                            modifier = Modifier.size(56.dp)
                        )
                    } else {
                        LinearProgressIndicator(
                            progress = { config.sliderValue },
                            modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp))
                        )
                    }
                    Text(
                        text = "${(config.sliderValue * 100).toInt()}% Complete",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            "card" -> {
                when (config.selectedVariant) {
                    "Elevated" -> ElevatedCard(shape = cornerShape, modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp)) {
                            Text(config.label, style = MaterialTheme.typography.titleMedium)
                            Text("Elevated Material 3 surface container.")
                        }
                    }
                    "Outlined" -> OutlinedCard(shape = cornerShape, modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp)) {
                            Text(config.label, style = MaterialTheme.typography.titleMedium)
                            Text("Outlined Material 3 surface container.")
                        }
                    }
                    else -> Card(shape = cornerShape, modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp)) {
                            Text(config.label, style = MaterialTheme.typography.titleMedium)
                            Text("Filled Material 3 surface container.")
                        }
                    }
                }
            }

            "list_item" -> {
                ListItem(
                    headlineContent = { Text(config.label) },
                    supportingContent = { Text("Supporting line item information") },
                    leadingContent = {
                        Icon(Icons.Filled.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    },
                    trailingContent = {
                        com.example.ui.components.ExpressiveSwitch(
                            checked = config.isChecked,
                            onCheckedChange = { onConfigChange(config.copy(isChecked = it)) }
                        )
                    },
                    modifier = Modifier.clip(cornerShape)
                )
            }

            "expressive_morphing" -> {
                val shapeA = remember { com.example.ui.motion.ExpressiveShapeFactory.create4PointStar() }
                val shapeB = remember { com.example.ui.motion.ExpressiveShapeFactory.createFlowerClover(6) }
                val shapeC = remember { com.example.ui.motion.ExpressiveShapeFactory.createPill() }
                val shapeD = remember { com.example.ui.motion.ExpressiveShapeFactory.createCircle(16) }

                val morphAB = remember { androidx.graphics.shapes.Morph(shapeA, shapeB) }
                val morphBC = remember { androidx.graphics.shapes.Morph(shapeB, shapeC) }
                val morphCD = remember { androidx.graphics.shapes.Morph(shapeC, shapeD) }

                val progress by animateFloatAsState(
                    targetValue = if (config.isChecked) 1f else 0f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    label = "morphProgress"
                )

                val activeMorph = if (config.selectedVariant.contains("Pill")) morphBC else morphAB

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(160.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                            .clickable {
                                onConfigChange(config.copy(isChecked = !config.isChecked))
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        com.example.ui.motion.MorphingShapeCanvas(
                            morph = activeMorph,
                            progress = progress,
                            color = MaterialTheme.colorScheme.primary,
                            strokeColor = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(130.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { onConfigChange(config.copy(isChecked = !config.isChecked)) }
                        ) {
                            Text(if (config.isChecked) "Morph Backward (0%)" else "Morph Forward (100%)")
                        }
                    }

                    Text(
                        text = "Official AndroidX Graphics Shapes Morphing • Progress: ${(progress * 100).toInt()}%",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            "expressive_loading_indicator" -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val isContained = config.selectedVariant.contains("Contained")
                    com.example.ui.components.ExpressiveLoadingIndicator(
                        isContained = isContained,
                        size = if (isContained) 64.dp else 52.dp
                    )
                    Text(
                        text = if (isContained) "Contained Loading Indicator (7-shape morph loop)" else "Uncontained Loading Indicator",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            "connected_button_group" -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val options = if (config.selectedVariant.contains("4")) {
                        listOf("Daily", "Weekly", "Monthly", "Yearly")
                    } else {
                        listOf("Day", "Week", "Month")
                    }
                    var sel by remember { mutableIntStateOf(0) }
                    com.example.ui.components.ExpressiveConnectedButtonGroup(
                        options = options,
                        selectedIndex = sel,
                        onSelectIndex = { sel = it }
                    )
                    Text(
                        text = "Selected: ${options.getOrElse(sel) { "" }} (2dp inner padding, flips round ↔ square)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            "wavy_progress_indicator" -> {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    val isIndeterminate = config.selectedVariant.contains("Indeterminate")
                    com.example.ui.components.ExpressiveWavyLinearProgressIndicator(
                        progress = if (isIndeterminate) null else config.sliderValue.coerceIn(0.1f, 1f),
                        height = 20.dp,
                        waveAmplitude = 6.dp
                    )
                    Text(
                        text = if (isIndeterminate) "Indeterminate Sinusoidal Wavy Track" else "Determinate Wavy Track (${(config.sliderValue * 100).toInt()}%) with Stop Indicator",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            "expressive_slider" -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    val sliderSize = when {
                        config.selectedVariant.contains("XL") -> com.example.ui.components.ExpressiveSliderSize.XL
                        config.selectedVariant.contains("L") -> com.example.ui.components.ExpressiveSliderSize.L
                        else -> com.example.ui.components.ExpressiveSliderSize.M
                    }
                    val isCentered = config.selectedVariant.contains("Centered")

                    var sVal by remember { mutableFloatStateOf(config.sliderValue) }
                    com.example.ui.components.ExpressiveSlider(
                        value = sVal,
                        onValueChange = {
                            sVal = it
                            onConfigChange(config.copy(sliderValue = it))
                        },
                        size = sliderSize,
                        isCentered = isCentered,
                        showInsetIcon = true,
                        numStops = if (isCentered) 5 else 0
                    )
                    Text(
                        text = "${sliderSize.label} Track • Value: ${(sVal * 100).toInt()}% • Handle shrinks on press",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            "floating_toolbar" -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    com.example.ui.navigation.FloatingToolbarNavigation(
                        currentRoute = "home",
                        onNavigateToDestination = { },
                        onOpenSettings = { }
                    )
                    Text(
                        text = "Material 3 Expressive Floating Toolbar with Paired Companion Action",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            else -> {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}
