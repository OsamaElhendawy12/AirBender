package com.airbender.gestures.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class AirbenderThemeStyle(val displayName: String) {
    AIR_BREEZE("Air Breeze"),
    NOTHING_DOT("Nothing Dot-Matrix"),
    CALM_ORGANIC("Calm Organic"),
    STUDIO_GRID("Studio Grid"),
    AURORA_INDIGO("Aurora Indigo")
}

data class AirbenderPalette(
    val background: Color,
    val backgroundGlow: Color,
    val card: Color,
    val cardBorder: Color,
    val inset: Color,
    val textMain: Color,
    val textMuted: Color,
    val accent: Color,
    val accentInk: Color,
    val accentSoft: Color,
    val accentSoftInk: Color,
    val toggleOff: Color,
    val dockBackground: Color,
    val cardRadius: Dp,
    val fontFamily: FontFamily,
    val useDotMatrix: Boolean = false,
    val useStudioGrid: Boolean = false
)

fun paletteFor(style: AirbenderThemeStyle, isDark: Boolean): AirbenderPalette {
    return when (style) {
        AirbenderThemeStyle.AIR_BREEZE -> if (isDark) {
            AirbenderPalette(
                background = Color(0xFF0B1120),
                backgroundGlow = Color(0x3838BDF8),
                card = Color(0xB8161F33),
                cardBorder = Color(0x2E94A3B8),
                inset = Color(0xBF0F172A),
                textMain = Color(0xFFF1F5F9),
                textMuted = Color(0xFF94A3B8),
                accent = Color(0xFF38BDF8),
                accentInk = Color(0xFF082F49),
                accentSoft = Color(0x2E38BDF8),
                accentSoftInk = Color(0xFF7DD3FC),
                toggleOff = Color(0xFF334155),
                dockBackground = Color(0xDB14171F),
                cardRadius = 24.dp,
                fontFamily = FontFamily.SansSerif
            )
        } else {
            AirbenderPalette(
                background = Color(0xFFEEF4FF),
                backgroundGlow = Color(0x4038BDF8),
                card = Color(0xCCFFFFFF),
                cardBorder = Color(0xE6FFFFFF),
                inset = Color(0xB3E2EEFF),
                textMain = Color(0xFF0F172A),
                textMuted = Color(0xFF64748B),
                accent = Color(0xFF2563EB),
                accentInk = Color(0xFFFFFFFF),
                accentSoft = Color(0x1F2563EB),
                accentSoftInk = Color(0xFF1D4ED8),
                toggleOff = Color(0xFFCBD5E1),
                dockBackground = Color(0xDBFFFFFF),
                cardRadius = 24.dp,
                fontFamily = FontFamily.SansSerif
            )
        }

        AirbenderThemeStyle.NOTHING_DOT -> if (isDark) {
            AirbenderPalette(
                background = Color(0xFF09090B),
                backgroundGlow = Color(0xFF27272A),
                card = Color(0xFF141417),
                cardBorder = Color(0xFF27272A),
                inset = Color(0xFF0C0C0E),
                textMain = Color(0xFFF4F4F5),
                textMuted = Color(0xFFA1A1AA),
                accent = Color(0xFFFF3B30),
                accentInk = Color(0xFFFFFFFF),
                accentSoft = Color(0x2EFF3B30),
                accentSoftInk = Color(0xFFFF6961),
                toggleOff = Color(0xFF3F3F46),
                dockBackground = Color(0xE6141417),
                cardRadius = 20.dp,
                fontFamily = FontFamily.Monospace,
                useDotMatrix = true
            )
        } else {
            AirbenderPalette(
                background = Color(0xFFF4F4F5),
                backgroundGlow = Color(0xFFD4D4D8),
                card = Color(0xFFFFFFFF),
                cardBorder = Color(0xFFD4D4D8),
                inset = Color(0xFFF4F4F5),
                textMain = Color(0xFF18181B),
                textMuted = Color(0xFF71717A),
                accent = Color(0xFFDC2626),
                accentInk = Color(0xFFFFFFFF),
                accentSoft = Color(0x1ADC2626),
                accentSoftInk = Color(0xFFB91C1C),
                toggleOff = Color(0xFFD4D4D8),
                dockBackground = Color(0xE6FFFFFF),
                cardRadius = 20.dp,
                fontFamily = FontFamily.Monospace,
                useDotMatrix = true
            )
        }

        AirbenderThemeStyle.CALM_ORGANIC -> if (isDark) {
            AirbenderPalette(
                background = Color(0xFF0B1410),
                backgroundGlow = Color(0x2634D399),
                card = Color(0xFF13211B),
                cardBorder = Color(0x2634D399),
                inset = Color(0xFF0D1713),
                textMain = Color(0xFFECFDF5),
                textMuted = Color(0xFF86EFAC),
                accent = Color(0xFF34D399),
                accentInk = Color(0xFF022C22),
                accentSoft = Color(0x2934D399),
                accentSoftInk = Color(0xFF6EE7B7),
                toggleOff = Color(0xFF284237),
                dockBackground = Color(0xE013211B),
                cardRadius = 28.dp,
                fontFamily = FontFamily.SansSerif
            )
        } else {
            AirbenderPalette(
                background = Color(0xFFF1F7F3),
                backgroundGlow = Color(0x33A7F3D0),
                card = Color(0xFFFFFFFF),
                cardBorder = Color(0x99A7F3D0),
                inset = Color(0xFFECF5EF),
                textMain = Color(0xFF064E3B),
                textMuted = Color(0xFF4B7C6B),
                accent = Color(0xFF059669),
                accentInk = Color(0xFFFFFFFF),
                accentSoft = Color(0x1F059669),
                accentSoftInk = Color(0xFF047857),
                toggleOff = Color(0xFFCBD5E1),
                dockBackground = Color(0xE6FFFFFF),
                cardRadius = 28.dp,
                fontFamily = FontFamily.SansSerif
            )
        }

        AirbenderThemeStyle.STUDIO_GRID -> if (isDark) {
            AirbenderPalette(
                background = Color(0xFF121418),
                backgroundGlow = Color(0x14FFFFFF),
                card = Color(0xFF1A1D23),
                cardBorder = Color(0xFF2A2F3A),
                inset = Color(0xFF13161B),
                textMain = Color(0xFFF3F4F6),
                textMuted = Color(0xFF9CA3AF),
                accent = Color(0xFFF59E0B),
                accentInk = Color(0xFF271702),
                accentSoft = Color(0x2EF59E0B),
                accentSoftInk = Color(0xFFFCD34D),
                toggleOff = Color(0xFF374151),
                dockBackground = Color(0xE61A1D23),
                cardRadius = 14.dp,
                fontFamily = FontFamily.SansSerif,
                useStudioGrid = true
            )
        } else {
            AirbenderPalette(
                background = Color(0xFFF5F2EB),
                backgroundGlow = Color(0x29B4A58C),
                card = Color(0xFFFBF9F5),
                cardBorder = Color(0xFFDFD8CA),
                inset = Color(0xFFEFEAE0),
                textMain = Color(0xFF1F2937),
                textMuted = Color(0xFF6B7280),
                accent = Color(0xFFD97706),
                accentInk = Color(0xFFFFFFFF),
                accentSoft = Color(0x21D97706),
                accentSoftInk = Color(0xFFB45309),
                toggleOff = Color(0xFFD6CEBE),
                dockBackground = Color(0xE6FBF9F5),
                cardRadius = 14.dp,
                fontFamily = FontFamily.SansSerif,
                useStudioGrid = true
            )
        }

        AirbenderThemeStyle.AURORA_INDIGO -> if (isDark) {
            AirbenderPalette(
                background = Color(0xFF0E0F1C),
                backgroundGlow = Color(0x386366F1),
                card = Color(0xC7181A2C),
                cardBorder = Color(0x33818CF8),
                inset = Color(0xFF121424),
                textMain = Color(0xFFEEF2FF),
                textMuted = Color(0xFFA5B4FC),
                accent = Color(0xFF818CF8),
                accentInk = Color(0xFF1E1B4B),
                accentSoft = Color(0x2E818CF8),
                accentSoftInk = Color(0xFFC7D2FE),
                toggleOff = Color(0xFF312E81),
                dockBackground = Color(0xDB181A2C),
                cardRadius = 22.dp,
                fontFamily = FontFamily.SansSerif
            )
        } else {
            AirbenderPalette(
                background = Color(0xFFF4F5FC),
                backgroundGlow = Color(0x40818CF8),
                card = Color(0xD9FFFFFF),
                cardBorder = Color(0xCCC7D2FE),
                inset = Color(0xFFEEF0FA),
                textMain = Color(0xFF1E1B4B),
                textMuted = Color(0xFF6366F1),
                accent = Color(0xFF4F46E5),
                accentInk = Color(0xFFFFFFFF),
                accentSoft = Color(0x1C4F46E5),
                accentSoftInk = Color(0xFF4338CA),
                toggleOff = Color(0xFFC7D2FE),
                dockBackground = Color(0xDBFFFFFF),
                cardRadius = 22.dp,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}
