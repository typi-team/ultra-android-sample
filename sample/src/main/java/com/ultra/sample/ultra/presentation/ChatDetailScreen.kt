package com.ultra.sample.ultra.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.typi.ultra.call.presentation.ongoing.CallActivity.Companion.createCallActivityIntent
import com.typi.ultra.call.presentation.ongoing.model.CallInputParams
import com.typi.ultra.integration.navigation.UltraNavigator
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
        val activity = LocalContext.current as Activity
        val navigator = LocalNavigator.currentOrThrow
        val ultraNavigator: UltraNavigator = koinInject()

        ultraNavigator.ChatDetailScreen(
            chatId = chatId,
            userId = userId,
            name = userName,
            onBackClicked = navigator::pop,
            onSendContactClicked = {
                navigator.push(ContactsScreen(isCreateChat = false))
            },
            onSendMoneyClicked = {
                navigator.push(SendMoneyScreen)
            },
            onUserDetailClicked = { phoneNumber ->
                navigator.push(UserDetailScreen(phoneNumber = phoneNumber))
            },
            onMediaBrowserClicked = { messageId ->
                navigator.push(MediaBrowserScreen(messageId = messageId))
            },
            onCallClicked = { callModel ->
                with(activity) {
                    val params = CallInputParams.ConnectToRoom(callModel)
                    startActivity(createCallActivityIntent(params))
                }
            }
        )
    }
}