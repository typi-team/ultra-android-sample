package com.ultra.sample.ultra.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.typi.ultra.call.presentation.ongoing.CallActivity.Companion.createCallActivityIntent
import com.typi.ultra.call.presentation.ongoing.model.CallInputParams
import com.typi.ultra.integration.navigation.UltraNavigator
import com.ultra.sample.contacts.ui.ContactsScreen
import com.ultra.sample.money.presentation.SendMoneyScreen
import org.koin.compose.koinInject

class ChatDetailScreen(
    private val chatId: String? = null,
    private val userId: String? = null,
    private val userName: String? = null,
) : Screen {

    @Composable
    override fun Content() {
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
            onVideoPlayerClicked = { messageId ->
                navigator.push(VideoPlayerScreen(messageId = messageId))
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