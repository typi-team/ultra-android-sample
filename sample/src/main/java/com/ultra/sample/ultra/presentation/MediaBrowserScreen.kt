package com.ultra.sample.ultra.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.typi.ultra.integration.navigation.UltraNavigator
import com.ultra.sample.navigation.BaseScreen
import org.koin.compose.koinInject

class MediaBrowserScreen(
    private val messageId: String,
) : BaseScreen() {

    @Composable
    override fun ScreenContent() {
        val navigator = LocalNavigator.currentOrThrow
        val ultraNavigator: UltraNavigator = koinInject()

        val messageIdValue by remember { mutableStateOf(messageId) }
        val onBackClicked: () -> Unit = remember { { navigator.pop() } }

        ultraNavigator.MediaBrowserScreen(
            messageId = messageIdValue,
            onBackClicked = onBackClicked,
        )
    }
}