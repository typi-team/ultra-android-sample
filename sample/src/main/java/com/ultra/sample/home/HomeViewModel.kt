package com.ultra.sample.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ultra.sample.R
import com.ultra.sample.auth.domain.usecase.LogoutUseCase
import com.ultra.sample.core.settings.SettingsManager
import com.ultra.sample.core.ui.alert.ConfirmAlertDialogState
import java.util.Locale
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val settingsManager: SettingsManager,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(getDefaultState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _effect: Channel<HomeEffect> = Channel(Channel.BUFFERED)
    val effect: Flow<HomeEffect> = _effect.receiveAsFlow()

    fun onLanguageClicked() {
        viewModelScope.launch {
            when (state.value.language) {
                Language.English -> {
                    _effect.send(HomeEffect.ChangeLanguage(Locale(RUSSIAN)))
                    setState {
                        copy(
                            language = Language.Russian
                        )
                    }
                }
                Language.Russian -> {
                    _effect.send(HomeEffect.ChangeLanguage(Locale.ENGLISH))
                    setState {
                        copy(
                            language = Language.English
                        )
                    }
                }
            }
        }
    }

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
                _effect.send(HomeEffect.Logout)
            }
        }
    }

    private fun setState(reducer: HomeState.() -> HomeState) {
        val newState = state.value.reducer()
        _state.value = newState
    }

    private fun getDefaultState(): HomeState {
        return HomeState(
            fullName = settingsManager.firstName + " " + settingsManager.lastName,
            phoneNumber = settingsManager.phone,
            language = when (Locale.getDefault().language) {
                RUSSIAN -> Language.Russian
                else -> Language.English
            },
        )
    }

    companion object {

        private const val RUSSIAN = "ru"
    }
}

data class HomeState(
    val fullName: String,
    val phoneNumber: String,
    val language: Language,
    val logoutDialogState: ConfirmAlertDialogState? = null,
)

sealed class Language(
    val code: String
) {
    data object English : Language(code = "EN")

    data object Russian : Language(code = "RU")
}

sealed class HomeEffect {
    data class ChangeLanguage(
        val locale: Locale
    ) : HomeEffect()

    data object Logout : HomeEffect()
}
