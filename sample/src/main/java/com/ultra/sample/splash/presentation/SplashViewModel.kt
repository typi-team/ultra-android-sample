package com.ultra.sample.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ultra.sample.auth.domain.manager.SessionManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _actions = Channel<SplashAction>(Channel.BUFFERED)
    val actions: Flow<SplashAction> = _actions.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(DELAY)
            _actions.trySend(
                if (sessionManager.isAuthorized.value) {
                    SplashAction.ShowMainScreen
                } else {
                    SplashAction.ShowAuthScreen
                }
            )
        }
    }

    companion object {

        private const val DELAY = 1_000L
    }
}

sealed class SplashAction {
    object ShowAuthScreen : SplashAction()
    object ShowMainScreen : SplashAction()
}