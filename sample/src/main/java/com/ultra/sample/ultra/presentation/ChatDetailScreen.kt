package com.ultra.sample.ultra.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.typi.ultra.integration.navigation.UltraNavigator
import com.ultra.sample.R
import com.ultra.sample.contacts.ui.ContactsScreen
import com.ultra.sample.money.presentation.SendMoneyScreen
import com.ultra.sample.navigation.BaseScreen
import org.koin.compose.koinInject

class ChatDetailScreen(
    private val chatId: String? = null,
    private val userId: String? = null,
    private val userName: String? = null,
) : BaseScreen() {

    @Composable
    override fun ScreenContent() {
        val navigator = LocalNavigator.currentOrThrow
        val ultraNavigator: UltraNavigator = koinInject()

        ultraNavigator.ChatDetailScreen(
            chatId = chatId,
            userId = userId,
            name = userName,
            emptyContent = {
                Text(
                    text = stringResource(R.string.empty_messages),
                    modifier = Modifier.align(Alignment.Center),
                )
            },
            onBackClicked = navigator::pop,
            onSendContactClicked = {
                navigator.push(ContactsScreen(isCreateChat = false))
            },
            onSendMoneyClicked = { _ ->
                navigator.push(SendMoneyScreen)
            },
            onUserDetailClicked = { phoneNumber ->
                navigator.push(UserDetailScreen(phoneNumber = phoneNumber))
            },
            onMediaBrowserClicked = { messageId ->
                navigator.push(MediaBrowserScreen(messageId = messageId))
            },
        )
    }
}