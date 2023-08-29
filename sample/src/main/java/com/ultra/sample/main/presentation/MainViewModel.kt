package com.ultra.sample.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typi.ultra.chat.presentation.detail.model.ChatDetailInputParams
import com.typi.ultra.user.model.UltraContact
import com.ultra.sample.core.cache.CacheManager
import com.ultra.sample.core.cache.KEY_CONTACT
import com.ultra.sample.push.PushManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val pushManager: PushManager,
    private val cacheManager: CacheManager
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    private val _effect: Channel<MainEffect> = Channel(Channel.BUFFERED)
    val effect: Flow<MainEffect> = _effect.receiveAsFlow()

    private var cacheJob: Job? = null

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
        cacheJob?.cancel()
        cacheJob = null

        cacheJob = viewModelScope.launch {
            cacheManager.listen(KEY_CONTACT)
                .filterIsInstance<UltraContact?>()
                .onEach(::onContactPicked)
                .collect()
        }
        _effect.trySend(MainEffect.ShowScreen("contacts/$title"))
    }

    fun onSendContactClicked(title: String) {
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

    private suspend fun onContactPicked(contact: UltraContact?) {
        delay(300)
        cacheJob?.cancel()
        cacheJob = null

        contact ?: return
        val chatDetailParams = ChatDetailInputParams.User(
            userId = contact.userId
        )
        onChatClicked(chatDetailParams)
    }
}

data class MainState(
    val isBottomBarVisible: Boolean = true,
)

sealed class MainEffect {

    data class ShowScreen(val route: String) : MainEffect()
    object Logout : MainEffect()
}