package com.airbender.gestures.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val LocalAirbenderPalette = staticCompositionLocalOf {
    paletteFor(AirbenderThemeStyle.AIR_BREEZE, isDark = false)
}

@Composable
fun AirbenderTheme(
    style: AirbenderThemeStyle,
    isDark: Boolean,
    content: @Composable () -> Unit
) {
    val palette = paletteFor(style, isDark)
    val colorScheme = if (isDark) {
        darkColorScheme(
            primary = palette.accent,
            onPrimary = palette.accentInk,
            background = palette.background,
            surface = palette.card,
            onBackground = palette.textMain,
            onSurface = palette.textMain
        )
    } else {
        lightColorScheme(
            primary = palette.accent,
            onPrimary = palette.accentInk,
            background = palette.background,
            surface = palette.card,
            onBackground = palette.textMain,
            onSurface = palette.textMain
        )
    }

    CompositionLocalProvider(LocalAirbenderPalette provides palette) {
        MaterialTheme(colorScheme = colorScheme) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(palette.background)
            ) {
                ThemeBackgroundCanvas(palette = palette)
                content()
            }
        }
    }
}

@Composable
private fun ThemeBackgroundCanvas(palette: AirbenderPalette) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        if (palette.useDotMatrix) {
            val step = 18.dp.toPx()
            var x = step
            while (x < size.width) {
                var y = step
                while (y < size.height) {
                    drawCircle(
                        color = palette.backgroundGlow,
                        radius = 1.2.dp.toPx(),
                        center = Offset(x, y)
                    )
                    y += step
                }
                x += step
            }
        } else if (palette.useStudioGrid) {
            val step = 24.dp.toPx()
            var x = 0f
            while (x < size.width) {
                drawLine(
                    color = palette.backgroundGlow,
                    start = Offset(x, 0f),
                    end = Offset(x, size.height),
                    strokeWidth = 1f
                )
                x += step
            }
            var y = 0f
            while (y < size.height) {
                drawLine(
                    color = palette.backgroundGlow,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = 1f
                )
                y += step
            }
        } else {
            drawRect(
                brush = Brush.radialGradient(
                    colors = listOf(palette.backgroundGlow, Color.Transparent),
                    center = Offset(size.width * 0.2f, size.height * 0.12f),
                    radius = size.maxDimension * 0.7f
                )
            )
        }
    }
}
