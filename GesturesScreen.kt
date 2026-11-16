package com.airbender.gestures.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbender.gestures.ui.components.SpringSwitch
import com.airbender.gestures.ui.theme.LocalAirbenderPalette

data class GestureMappingItem(
    val id: String,
    val emoji: String,
    val name: String,
    val action: String,
    val options: List<String>,
    val enabled: Boolean
)

@Composable
fun GesturesScreen(
    gestures: List<GestureMappingItem>,
    onToggleGesture: (String) -> Unit,
    onCycleAction: (String) -> Unit
) {
    val palette = LocalAirbenderPalette.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Gesture Mappings",
            color = palette.textMain,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = palette.fontFamily
        )
        Text(
            text = "Tap any action pill to cycle its target",
            color = palette.textMuted,
            fontSize = 12.sp,
            fontFamily = palette.fontFamily
        )

        gestures.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(palette.cardRadius))
                    .background(palette.card)
                    .border(1.dp, palette.cardBorder, RoundedCornerShape(palette.cardRadius))
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(palette.inset),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = item.emoji, fontSize = 18.sp)
                    }
                    Column(modifier = Modifier.padding(end = 8.dp)) {
                        Text(
                            text = item.name,
                            color = palette.textMain,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = palette.fontFamily
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(palette.accentSoft)
                                .clickable { onCycleAction(item.id) }
                                .padding(horizontal = 9.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "→ ${item.action}",
                                color = palette.accentSoftInk,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = palette.fontFamily
                            )
                        }
                    }
                }
                SpringSwitch(
                    checked = item.enabled,
                    onCheckedChange = { onToggleGesture(item.id) }
                )
            }
        }
        Spacer(modifier = Modifier.height(84.dp))
    }
}
