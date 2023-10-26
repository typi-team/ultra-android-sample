package com.ultra.sample.ultra.presentation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.typi.ultra.integration.navigation.UltraNavigator
import com.ultra.sample.contacts.ui.ContactsScreen
import org.koin.compose.koinInject

object ChatsScreen : Screen {

    @Composable
    override fun Content() {
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