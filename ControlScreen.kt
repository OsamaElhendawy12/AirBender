package com.airbender.gestures.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbender.gestures.ui.theme.LocalAirbenderPalette

@Composable
fun ControlScreen(
    isTablet: Boolean,
    engineActive: Boolean,
    onToggleEngine: () -> Unit,
    activeGestureCount: Int,
    cooldownMs: Int,
    palmGateEnabled: Boolean,
    themeName: String
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        if (isTablet) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    HeroEngineCard(engineActive, onToggleEngine)
                    ProfileStatsCard(themeName, activeGestureCount, cooldownMs, palmGateEnabled)
                }
                Box(modifier = Modifier.weight(1f)) {
                    LiveViewfinderCard(engineActive)
                }
            }
        } else {
            HeroEngineCard(engineActive, onToggleEngine)
            ProfileStatsCard(themeName, activeGestureCount, cooldownMs, palmGateEnabled)
            LiveViewfinderCard(engineActive)
        }
        Spacer(modifier = Modifier.height(84.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun HeroEngineCard(
    engineActive: Boolean,
    onToggleEngine: () -> Unit
) {
    val palette = LocalAirbenderPalette.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(palette.cardRadius))
            .background(palette.card)
            .border(1.dp, palette.cardBorder, RoundedCornerShape(palette.cardRadius))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Gesture Service",
                    color = palette.textMuted,
                    fontSize = 12.sp,
                    fontFamily = palette.fontFamily
                )
                Text(
                    text = if (engineActive) "Airbender Active" else "Paused",
                    color = palette.textMain,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = palette.fontFamily
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (engineActive) palette.accent else palette.inset)
                    .clickable { onToggleEngine() }
                    .padding(horizontal = 16.dp, vertical = 9.dp)
            ) {
                Text(
                    text = if (engineActive) "Running" else "Start",
                    color = if (engineActive) palette.accentInk else palette.textMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = palette.fontFamily
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Camera (Slice 2)", "Accessibility (Slice 3)", "Battery Exempt").forEach { label ->
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(palette.accentSoft)
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = label,
                        color = palette.accentSoftInk,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = palette.fontFamily
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileStatsCard(
    themeName: String,
    activeGestureCount: Int,
    cooldownMs: Int,
    palmGateEnabled: Boolean
) {
    val palette = LocalAirbenderPalette.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(palette.cardRadius))
            .background(palette.card)
            .border(1.dp, palette.cardBorder, RoundedCornerShape(palette.cardRadius))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ACTIVE PROFILE",
                color = palette.textMuted,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = palette.fontFamily
            )
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(palette.accentSoft)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = themeName,
                    color = palette.accentSoftInk,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = palette.fontFamily
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatTile("Active", activeGestureCount.toString(), Modifier.weight(1f))
            StatTile("Cooldown", "${cooldownMs}ms", Modifier.weight(1f))
            StatTile("Palm Gate", if (palmGateEnabled) "On" else "Off", Modifier.weight(1f))
        }
    }
}

@Composable
private fun StatTile(label: String, value: String, modifier: Modifier = Modifier) {
    val palette = LocalAirbenderPalette.current
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(palette.inset)
            .padding(vertical = 10.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = palette.textMain,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = palette.fontFamily
        )
        Text(
            text = label,
            color = palette.textMuted,
            fontSize = 11.sp,
            fontFamily = palette.fontFamily
        )
    }
}

@Composable
private fun LiveViewfinderCard(engineActive: Boolean) {
    val palette = LocalAirbenderPalette.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(palette.cardRadius))
            .background(Color(0xFF101318))
            .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(palette.cardRadius))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Front Camera • 480p",
                color = Color(0xFFCBD5E1),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = palette.fontFamily
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(if (engineActive) Color(0xFF34D399) else Color(0xFF64748B))
                )
                Text(
                    text = if (engineActive) "Preview Ready (Slice 2)" else "Standby",
                    color = if (engineActive) Color(0xFF34D399) else Color(0xFF94A3B8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = palette.fontFamily
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFF070A0F))
                .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(18.dp)),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(140.dp)) {
                val w = size.width
                val h = size.height
                val wrist = Offset(w * 0.5f, h * 0.85f)
                val palm = Offset(w * 0.5f, h * 0.60f)
                val tips = listOf(
                    Offset(w * 0.24f, h * 0.30f),
                    Offset(w * 0.40f, h * 0.18f),
                    Offset(w * 0.55f, h * 0.16f),
                    Offset(w * 0.72f, h * 0.24f),
                    Offset(w * 0.82f, h * 0.52f)
                )
                drawLine(palette.accent, wrist, palm, strokeWidth = 4f, cap = StrokeCap.Round)
                tips.forEach { tip ->
                    drawLine(palette.accent, palm, tip, strokeWidth = 4f, cap = StrokeCap.Round)
                    drawCircle(Color.White, radius = 5f, center = tip)
                }
                drawCircle(palette.accent, radius = 6f, center = wrist)
                drawCircle(palette.accent, radius = 6f, center = palm)
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(10.dp)
                    .clip(CircleShape)
                    .background(Color(0x99000000))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = if (engineActive) "Hand Skeleton Preview" else "Camera Paused",
                    color = Color(0xFFE2E8F0),
                    fontSize = 11.sp,
                    fontFamily = palette.fontFamily
                )
            }
        }
    }
}
