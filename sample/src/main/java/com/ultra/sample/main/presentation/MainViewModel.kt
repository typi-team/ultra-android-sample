package com.ultra.sample.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typi.ultra.chat.presentation.detail.model.ChatDetailInputParams
import com.ultra.sample.push.PushManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val pushManager: PushManager,
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    private val _effect: Channel<MainEffect> = Channel(Channel.BUFFERED)
    val effect: Flow<MainEffect> = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            runCatching {
                pushManager.updateToken()
            }.onFailure {
                Timber.d(it, "Couldn't update token")
            }
        }
    }

    fun onBottomBarStateChanged(shouldShow: Boolean) {
        _state.value = _state.value.copy(isBottomBarVisible = shouldShow)
    }

    fun onChatClicked(params: ChatDetailInputParams) {
        val route = when (params) {
            is ChatDetailInputParams.Chat -> "chat_detail?chat_id=${params.chatId}"
            is ChatDetailInputParams.User -> "chat_detail?user_id=${params.userId}"
        }
        _effect.trySend(MainEffect.ShowScreen(route))
    }

    fun onContactsClicked(title: String) {
        _effect.trySend(MainEffect.ShowScreen("contacts/$title"))
    }

    fun onSendMoneyClicked() {
        _effect.trySend(MainEffect.ShowScreen("send_money"))
    }

    fun onUserDetailClicked(phoneNumber: String) {
        _effect.trySend(MainEffect.ShowScreen("user_detail/$phoneNumber"))
    }

    fun onVideoPlayerClicked(messageId: String) {
        _effect.trySend(MainEffect.ShowScreen("video_player/$messageId"))
    }

    fun onLoggedOut() {
        _effect.trySend(MainEffect.Logout)
    }
}

data class MainState(
    val isBottomBarVisible: Boolean = true,
)

sealed class MainEffect {

    data class ShowScreen(val route: String) : MainEffect()
    object Logout : MainEffect()
}