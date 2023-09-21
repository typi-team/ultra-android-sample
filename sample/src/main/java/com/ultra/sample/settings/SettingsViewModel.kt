package com.ultra.sample.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ultra.sample.R
import com.ultra.sample.auth.domain.usecase.LogoutUseCase
import com.ultra.sample.core.settings.SettingsManager
import com.ultra.sample.core.ui.alert.ConfirmAlertDialogState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsManager: SettingsManager,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<SettingsState> = MutableStateFlow(getDefaultState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    private val _effect: Channel<SettingsEffect> = Channel(Channel.BUFFERED)
    val effect: Flow<SettingsEffect> = _effect.receiveAsFlow()

    fun onLogoutClicked() {
        setState {
            copy(
                logoutDialogState = ConfirmAlertDialogState(
                    title = R.string.logout,
                    description = R.string.logout_confirmation
                )
            )
        }
    }

    fun onLogoutDismiss() {
        setState {
            copy(
                logoutDialogState = null
            )
        }
    }

    fun onLogoutConfirm() {
        onLogoutDismiss()
        viewModelScope.launch {
            runCatching {
                logoutUseCase(Unit)
                _effect.send(SettingsEffect.Logout)
            }
        }
    }

    private fun setState(reducer: SettingsState.() -> SettingsState) {
        val newState = state.value.reducer()
        _state.value = newState
    }

    private fun getDefaultState(): SettingsState {
        return SettingsState(
            fullName = settingsManager.firstName + " " + settingsManager.lastName,
            phoneNumber = settingsManager.phone
        )
    }
}

data class SettingsState(
    val fullName: String,
    val phoneNumber: String,
    val logoutDialogState: ConfirmAlertDialogState? = null,
)

sealed class SettingsEffect {
    object Logout : SettingsEffect()
}