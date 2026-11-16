package com.airbender.gestures.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbender.gestures.ui.components.SpringSwitch
import com.airbender.gestures.ui.theme.AirbenderThemeStyle
import com.airbender.gestures.ui.theme.LocalAirbenderPalette

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TuningScreen(
    themeStyle: AirbenderThemeStyle,
    onSelectThemeStyle: (AirbenderThemeStyle) -> Unit,
    isDark: Boolean,
    onToggleDark: (Boolean) -> Unit,
    velocityPct: Float,
    onVelocityChange: (Float) -> Unit,
    cooldownMs: Int,
    onCooldownChange: (Int) -> Unit,
    palmGateEnabled: Boolean,
    onPalmGateChange: (Boolean) -> Unit,
    mirrorFront: Boolean,
    onMirrorFrontChange: (Boolean) -> Unit,
    smartFps: Boolean,
    onSmartFpsChange: (Boolean) -> Unit,
    whitelist: Map<String, Boolean>,
    onToggleWhitelist: (String) -> Unit
) {
    val palette = LocalAirbenderPalette.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Appearance & 5 Themes Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(palette.cardRadius))
                .background(palette.card)
                .border(1.dp, palette.cardBorder, RoundedCornerShape(palette.cardRadius))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "APPEARANCE & THEME",
                color = palette.textMuted,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = palette.fontFamily
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dark Mode",
                    color = palette.textMain,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = palette.fontFamily
                )
                SpringSwitch(checked = isDark, onCheckedChange = onToggleDark)
            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AirbenderThemeStyle.entries.forEach { style ->
                    val selected = style == themeStyle
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (selected) palette.accent else palette.inset)
                            .clickable { onSelectThemeStyle(style) }
                            .padding(horizontal = 12.dp, vertical = 7.dp)
                    ) {
                        Text(
                            text = style.displayName,
                            color = if (selected) palette.accentInk else palette.textMuted,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = palette.fontFamily
                        )
                    }
                }
            }
        }

        // Sensitivity & Timing Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(palette.cardRadius))
                .background(palette.card)
                .border(1.dp, palette.cardBorder, RoundedCornerShape(palette.cardRadius))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "SENSITIVITY & TIMING",
                color = palette.textMuted,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = palette.fontFamily
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Swipe Velocity Threshold", color = palette.textMain, fontSize = 13.sp, fontFamily = palette.fontFamily)
                Text("${velocityPct.toInt()}%", color = palette.accent, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, fontFamily = palette.fontFamily)
            }
            Slider(
                value = velocityPct,
                onValueChange = onVelocityChange,
                valueRange = 20f..100f,
                colors = SliderDefaults.colors(thumbColor = palette.accent, activeTrackColor = palette.accent)
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Cooldown Between Swipes", color = palette.textMain, fontSize = 13.sp, fontFamily = palette.fontFamily)
                Text("${cooldownMs} ms", color = palette.accent, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, fontFamily = palette.fontFamily)
            }
            Slider(
                value = cooldownMs.toFloat(),
                onValueChange = { onCooldownChange(it.toInt()) },
                valueRange = 150f..1000f,
                colors = SliderDefaults.colors(thumbColor = palette.accent, activeTrackColor = palette.accent)
            )
        }

        // Behavior Toggles Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(palette.cardRadius))
                .background(palette.card)
                .border(1.dp, palette.cardBorder, RoundedCornerShape(palette.cardRadius))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "BEHAVIOR & POWER",
                color = palette.textMuted,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = palette.fontFamily
            )
            BehaviorRow("Arm-to-Swipe Palm Gate", "Prevents accidental scrolls while reading", palmGateEnabled, onPalmGateChange)
            BehaviorRow("Mirror Front Camera", "Flips left/right swipe direction naturally", mirrorFront, onMirrorFrontChange)
            BehaviorRow("Smart FPS Throttling", "12 FPS idle → 24 FPS when hand tracked", smartFps, onSmartFpsChange)
        }

        // App Whitelist Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(palette.cardRadius))
                .background(palette.card)
                .border(1.dp, palette.cardBorder, RoundedCornerShape(palette.cardRadius))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "ACTIVE APP PRESETS",
                color = palette.textMuted,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = palette.fontFamily
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                whitelist.forEach { (app, enabled) ->
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (enabled) palette.accent else palette.inset)
                            .clickable { onToggleWhitelist(app) }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (enabled) "$app ✓" else app,
                            color = if (enabled) palette.accentInk else palette.textMuted,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = palette.fontFamily
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(84.dp))
    }
}

@Composable
private fun BehaviorRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val palette = LocalAirbenderPalette.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f).padding(end = 12.dp)) {
            Text(
                text = title,
                color = palette.textMain,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = palette.fontFamily
            )
            Text(
                text = subtitle,
                color = palette.textMuted,
                fontSize = 11.sp,
                fontFamily = palette.fontFamily
            )
        }
        SpringSwitch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
