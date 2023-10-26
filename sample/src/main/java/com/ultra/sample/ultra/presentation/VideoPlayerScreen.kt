package com.ultra.sample.ultra.presentation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.typi.ultra.integration.navigation.UltraNavigator
import org.koin.compose.koinInject

class VideoPlayerScreen(
    private val messageId: String,
) : Screen {

    @Composable
    override fun Content() {
        val ultraNavigator: UltraNavigator = koinInject()
        ultraNavigator.VideoPlayerScreen(messageId = messageId)
    }
}