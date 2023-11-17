package com.ultra.sample.ultra.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.typi.ultra.call.model.CallModel
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

        val chatIdValue by remember { mutableStateOf(chatId) }
        val userIdValue by remember { mutableStateOf(userId) }
        val userNameValue by remember { mutableStateOf(userName) }
        val onBackClicked: () -> Unit = remember { { navigator.pop() } }
        val onSendContactClicked: () -> Unit = remember { { navigator.push(ContactsScreen(isCreateChat = false)) } }
        val onSendMoneyClicked: () -> Unit = remember { { navigator.push(SendMoneyScreen) } }
        val onUserDetailClicked: (String) -> Unit = remember { { navigator.push(UserDetailScreen(phoneNumber = it)) } }
        val onMediaBrowserClicked: (String) -> Unit =
            remember { { navigator.push(MediaBrowserScreen(messageId = it)) } }
        val onCallClicked: (CallModel) -> Unit = remember {
            {
                with(activity) {
                    val params = CallInputParams.ConnectToRoom(it)
                    startActivity(createCallActivityIntent(params))
                }
            }
        }

        ultraNavigator.ChatDetailScreen(
            chatId = chatIdValue,
            userId = userIdValue,
            name = userNameValue,
            onBackClicked = onBackClicked,
            onSendContactClicked = onSendContactClicked,
            onSendMoneyClicked = onSendMoneyClicked,
            onUserDetailClicked = onUserDetailClicked,
            onMediaBrowserClicked = onMediaBrowserClicked,
            onCallClicked = onCallClicked,
        )
    }
}