package com.ultra.sample.ultra.presentation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.typi.ultra.integration.navigation.UltraNavigator
import com.ultra.sample.contacts.ui.ContactsScreen
import com.ultra.sample.navigation.BaseScreen
import org.koin.compose.koinInject

object ChatsScreen : BaseScreen(shouldShowBottomBar = true) {

    @Composable
    override fun ScreenContent() {
        val navigator = LocalNavigator.currentOrThrow
        val ultraNavigator: UltraNavigator = koinInject()

        ultraNavigator.ChatsScreen(
            onChatClicked = { chatId ->
                navigator.push(ChatDetailScreen(chatId = chatId))
            },
            onContactsClicked = {
                navigator.push(ContactsScreen())
            },
            isToolbarVisible = true,
        )
    }
}