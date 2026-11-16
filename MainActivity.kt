package com.airbender.gestures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbender.gestures.ui.components.FloatingDock
import com.airbender.gestures.ui.components.TabletTopBar
import com.airbender.gestures.ui.screens.ControlScreen
import com.airbender.gestures.ui.screens.GestureMappingItem
import com.airbender.gestures.ui.screens.GesturesScreen
import com.airbender.gestures.ui.screens.TuningScreen
import com.airbender.gestures.ui.theme.AirbenderTheme
import com.airbender.gestures.ui.theme.AirbenderThemeStyle
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AirbenderRoot()
        }
    }
}

@Composable
fun AirbenderRoot() {
    var themeStyle by remember { mutableStateOf(AirbenderThemeStyle.AIR_BREEZE) }
    var isDark by remember { mutableStateOf(false) }
    var engineActive by remember { mutableStateOf(true) }
    var velocityPct by remember { mutableFloatStateOf(65f) }
    var cooldownMs by remember { mutableIntStateOf(450) }
    var palmGateEnabled by remember { mutableStateOf(true) }
    var mirrorFront by remember { mutableStateOf(true) }
    var smartFps by remember { mutableStateOf(true) }

    val gestures = remember {
        mutableStateListOf(
            GestureMappingItem("up", "⬆️", "Air Swipe Up", "Scroll Down", listOf("Scroll Down", "Next Short / Reel", "Volume Up"), true),
            GestureMappingItem("down", "⬇️", "Air Swipe Down", "Scroll Up", listOf("Scroll Up", "Prev Short / Reel", "Volume Down"), true),
            GestureMappingItem("left", "⬅️", "Air Swipe Left", "Next Page / Tab", listOf("Next Page / Tab", "Swipe Left", "Forward"), true),
            GestureMappingItem("right", "➡️", "Air Swipe Right", "Previous Page / Back", listOf("Previous Page / Back", "Swipe Right", "System Back"), true),
            GestureMappingItem("peace", "✌️", "Peace Sign", "Take Photo (ImageCapture)", listOf("Take Photo (ImageCapture)", "Take Screenshot"), true),
            GestureMappingItem("thumb", "👍", "Thumbs Up", "Open NoteIn", listOf("Open NoteIn", "Open Gemini", "Open Brave", "Open YouTube"), true),
            GestureMappingItem("fist", "✊", "Closed Fist", "Play / Pause Media", listOf("Play / Pause Media", "Mute Audio", "Pause Airbender"), false)
        )
    }

    val whitelist = remember {
        mutableStateMapOf(
            "YouTube" to true,
            "TikTok" to true,
            "Instagram" to true,
            "Instagram Lite" to true,
            "Facebook" to true,
            "Facebook Lite" to true,
            "NoteIn" to true,
            "Google Drive" to true,
            "Honor Gallery" to true,
            "Gemini" to true,
            "ChatGPT" to false,
            "Claude" to false
        )
    }

    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    AirbenderTheme(style = themeStyle, isDark = isDark) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
        ) {
            val isTablet = maxWidth >= 700.dp

            Column(modifier = Modifier.fillMaxSize()) {
                if (isTablet) {
                    TabletTopBar(
                        selectedTab = pagerState.currentPage,
                        onSelectTab = { idx -> scope.launch { pagerState.animateScrollToPage(idx) } },
                        engineActive = engineActive,
                        onToggleEngine = { engineActive = !engineActive }
                    )
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f)
                ) { page ->
                    when (page) {
                        0 -> ControlScreen(
                            isTablet = isTablet,
                            engineActive = engineActive,
                            onToggleEngine = { engineActive = !engineActive },
                            activeGestureCount = gestures.count { it.enabled },
                            cooldownMs = cooldownMs,
                            palmGateEnabled = palmGateEnabled,
                            themeName = themeStyle.displayName
                        )
                        1 -> GesturesScreen(
                            gestures = gestures,
                            onToggleGesture = { id ->
                                val idx = gestures.indexOfFirst { it.id == id }
                                if (idx >= 0) {
                                    val item = gestures[idx]
                                    gestures[idx] = item.copy(enabled = !item.enabled)
                                }
                            },
                            onCycleAction = { id ->
                                val idx = gestures.indexOfFirst { it.id == id }
                                if (idx >= 0) {
                                    val item = gestures[idx]
                                    val nextIdx = (item.options.indexOf(item.action) + 1) % item.options.size
                                    gestures[idx] = item.copy(action = item.options[nextIdx])
                                }
                            }
                        )
                        2 -> TuningScreen(
                            themeStyle = themeStyle,
                            onSelectThemeStyle = { themeStyle = it },
                            isDark = isDark,
                            onToggleDark = { isDark = it },
                            velocityPct = velocityPct,
                            onVelocityChange = { velocityPct = it },
                            cooldownMs = cooldownMs,
                            onCooldownChange = { cooldownMs = it },
                            palmGateEnabled = palmGateEnabled,
                            onPalmGateChange = { palmGateEnabled = it },
                            mirrorFront = mirrorFront,
                            onMirrorFrontChange = { mirrorFront = it },
                            smartFps = smartFps,
                            onSmartFpsChange = { smartFps = it },
                            whitelist = whitelist,
                            onToggleWhitelist = { app ->
                                whitelist[app] = !(whitelist[app] ?: false)
                            }
                        )
                    }
                }
            }

            if (!isTablet) {
                FloatingDock(
                    selectedTab = pagerState.currentPage,
                    onSelectTab = { idx -> scope.launch { pagerState.animateScrollToPage(idx) } },
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}
