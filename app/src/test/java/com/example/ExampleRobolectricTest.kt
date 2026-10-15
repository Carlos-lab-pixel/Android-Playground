package com.example

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.example.model.ComponentCategory
import com.example.model.CustomWaveformConfig
import com.example.model.HapticCapabilityInfo
import com.example.model.PlaygroundConfig
import com.example.platform.haptics.HapticsService
import com.example.ui.catalog.ComponentCatalogData
import com.example.ui.playground.PlaygroundCodeGenerator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

class FakeHapticsService(
    private val fakeCaps: HapticCapabilityInfo = HapticCapabilityInfo(
        hasVibrator = false,
        hasAmplitudeControl = false,
        hasPrimitivesSupport = false,
        sdkInt = 34
    )
) : HapticsService {
    var lastPredefinedEffect: Int? = null
    var lastWaveformConfig: CustomWaveformConfig? = null
    var isCancelled: Boolean = false

    override fun getCapabilities(): HapticCapabilityInfo = fakeCaps

    override fun playPredefined(effectId: Int, fallbackDurationMs: Long): Boolean {
        lastPredefinedEffect = effectId
        return fakeCaps.hasVibrator
    }

    override fun playPrimitive(primitiveId: Int, fallbackDurationMs: Long): Boolean {
        return fakeCaps.hasPrimitivesSupport
    }

    override fun playCustomWaveform(config: CustomWaveformConfig, intensityMultiplier: Float): Boolean {
        lastWaveformConfig = config
        return fakeCaps.hasVibrator
    }

    override fun cancel() {
        isCancelled = true
    }
}

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun app_name_is_material_playground() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Material Playground", appName)
    }

    @Test
    fun catalog_contains_required_components() {
        val items = ComponentCatalogData.allComponents
        assertTrue("Catalog should contain at least 15 components", items.size >= 15)

        val button = ComponentCatalogData.getById("button")
        assertNotNull(button)
        assertEquals(ComponentCategory.ACTIONS, button?.category)

        val switchItem = ComponentCatalogData.getById("switch")
        assertNotNull(switchItem)
        assertEquals(ComponentCategory.SELECTION, switchItem?.category)

        val textField = ComponentCatalogData.getById("text_field")
        assertNotNull(textField)
        assertEquals(ComponentCategory.TEXT_INPUT, textField?.category)
    }

    @Test
    fun code_generator_reflects_configuration_changes() {
        val buttonItem = ComponentCatalogData.getById("button")!!
        val configDefault = PlaygroundConfig(label = "Primary Action", enabled = true)
        val codeDefault = PlaygroundCodeGenerator.generateCode(buttonItem, configDefault)
        assertTrue(codeDefault.contains("Button"))
        assertTrue(codeDefault.contains("Primary Action"))
        assertFalse(codeDefault.contains("enabled = false"))

        val configDisabled = configDefault.copy(enabled = false, selectedVariant = "Tonal")
        val codeTonal = PlaygroundCodeGenerator.generateCode(buttonItem, configDisabled)
        assertTrue(codeTonal.contains("FilledTonalButton"))
        assertTrue(codeTonal.contains("enabled = false"))
    }

    @Test
    fun waveform_config_calculates_total_duration_correctly() {
        val waveform = CustomWaveformConfig(pulses = 3, durationMs = 50, gapMs = 40, amplitude = 200)
        // 3 * 50 + 2 * 40 = 150 + 80 = 230
        assertEquals(230, waveform.totalDurationMs)
    }

    @Test
    fun fake_haptics_service_handles_missing_hardware_gracefully() {
        val service = FakeHapticsService(
            HapticCapabilityInfo(hasVibrator = false, hasAmplitudeControl = false, hasPrimitivesSupport = false, sdkInt = 30)
        )
        val played = service.playPredefined(0, 50)
        assertFalse("Should return false when vibrator is missing", played)
        assertEquals(0, service.lastPredefinedEffect)

        service.cancel()
        assertTrue(service.isCancelled)
    }
}
