package com.airbender.gestures.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbender.gestures.ui.theme.LocalAirbenderPalette

@Composable
fun TabletTopBar(
    selectedTab: Int,
    onSelectTab: (Int) -> Unit,
    engineActive: Boolean,
    onToggleEngine: () -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalAirbenderPalette.current
    val tabs = listOf("Control", "Gestures", "Tuning")

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(palette.accent),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Air,
                    contentDescription = "Airbender",
                    tint = palette.accentInk,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column {
                Text(
                    text = "Airbender",
                    color = palette.textMain,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = palette.fontFamily
                )
                Text(
                    text = "Touchless Control",
                    color = palette.textMuted,
                    fontSize = 11.sp,
                    fontFamily = palette.fontFamily
                )
            }
        }

        Row(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(0xFF0F172A))
                .border(1.dp, palette.cardBorder, CircleShape)
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                val active = index == selectedTab
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(if (active) palette.accent else Color.Transparent)
                        .clickable { onSelectTab(index) }
                        .padding(horizontal = 16.dp, vertical = 7.dp)
                ) {
                    Text(
                        text = title,
                        color = if (active) palette.accentInk else Color(0xFFCBD5E1),
                        fontSize = 12.sp,
                        fontWeight = if (active) FontWeight.SemiBold else FontWeight.Medium,
                        fontFamily = palette.fontFamily
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(if (engineActive) palette.accent else palette.inset)
                .clickable { onToggleEngine() }
                .padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text(
                text = if (engineActive) "Engine: ON" else "Engine: PAUSED",
                color = if (engineActive) palette.accentInk else palette.textMuted,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = palette.fontFamily
            )
        }
    }
}
