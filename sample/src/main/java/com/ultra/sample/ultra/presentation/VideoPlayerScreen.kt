package com.ultra.sample.ultra.presentation

import androidx.compose.runtime.Composable
import com.typi.ultra.integration.navigation.UltraNavigator
import com.ultra.sample.navigation.BaseScreen
import org.koin.compose.koinInject

class VideoPlayerScreen(
    private val messageId: String,
) : BaseScreen() {

    @Composable
    override fun ScreenContent() {
        val ultraNavigator: UltraNavigator = koinInject()
        ultraNavigator.VideoPlayerScreen(messageId = messageId)
    }
}