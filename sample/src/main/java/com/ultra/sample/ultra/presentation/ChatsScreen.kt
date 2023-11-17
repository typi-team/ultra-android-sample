package com.ultra.sample.ultra.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

        val onChatClicked: (String) -> Unit = remember { { navigator.push(ChatDetailScreen(chatId = it)) } }
        val onContactsClicked: () -> Unit = remember { { navigator.push(ContactsScreen()) } }

        ultraNavigator.ChatsScreen(
            onChatClicked = onChatClicked,
            onContactsClicked = onContactsClicked,
            isToolbarVisible = true,
        )
    }
}