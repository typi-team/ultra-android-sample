package com.typi.ultra.sample.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typi.ultra.sample.push.PushManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val pushManager: PushManager,
) : ViewModel() {

    private val _actions = Channel<MainAction>(Channel.BUFFERED)
    val actions: Flow<MainAction> = _actions.receiveAsFlow()

    fun onCreate() {
        viewModelScope.launch {
            runCatching {
                pushManager.updateToken()
                _actions.send(MainAction.StartServices)
            }.onFailure {
                Timber.d(it, "Couldn't update token")
            }
        }
    }
}

sealed class MainAction {
    object StartServices : MainAction()
}