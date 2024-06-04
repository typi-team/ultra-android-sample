package com.ultra.sample.ultra.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.typi.ultra.integration.chat.UltraChatType
import com.typi.ultra.integration.navigation.UltraNavigator
import com.ultra.sample.R
import com.ultra.sample.contacts.ui.ContactsScreen
import com.ultra.sample.money.presentation.MoneyInfoScreen
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
            emptyContent = { type ->
                EmptyState(type = type)
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
            onMoneyMessageClicked = { transactionId ->
                navigator.push(MoneyInfoScreen(transactionId = transactionId))
            }
        )
    }

    @Composable
    private fun EmptyState(
        type: UltraChatType,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.img_chats_empty),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = when (type) {
                    UltraChatType.P2P -> stringResource(R.string.chat_chats_no_messages_p2p)
                    UltraChatType.PersonalManager -> stringResource(R.string.chat_chats_no_messages_personal_manager)
                    UltraChatType.Support -> stringResource(R.string.chat_chats_no_messages_support)
                    UltraChatType.Assistant -> stringResource(R.string.chat_chats_no_messages_assistant)
                },
                textAlign = TextAlign.Center,
            )
        }
    }
}