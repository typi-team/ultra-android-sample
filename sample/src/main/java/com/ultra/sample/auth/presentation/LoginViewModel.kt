package com.ultra.sample.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ultra.sample.auth.domain.usecase.LoginUseCase
import com.ultra.sample.core.settings.SettingsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel(
    private val settingsManager: SettingsManager,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow<LoginUiState>(LoginUiState.UnAuth())
    val viewState: StateFlow<LoginUiState> = _viewState.asStateFlow()

    init {
        _viewState.value = if (settingsManager.isAuthorized) {
            LoginUiState.Auth()
        } else {
            LoginUiState.UnAuth()
        }
    }

    fun onAuthClicked(
        onAuthSuccess: () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _viewState.value = LoginUiState.Auth(isLoading = true)

                loginUseCase(
                    LoginUseCase.Param(
                        nickname = settingsManager.nickname,
                        phone = settingsManager.phone,
                        firstname = settingsManager.firstName,
                        lastname = settingsManager.lastName,
                    )
                )

                onAuthSuccess()
            } catch (throwable: Throwable) {
                Timber.e(throwable, "Couldn't login")
            } finally {
                _viewState.value = LoginUiState.Auth(isLoading = false)
            }
        }
    }

    fun onLoginClicked(
        nickname: String,
        phone: String,
        firstname: String,
        lastname: String?,
        onLoginSuccess: () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _viewState.value = LoginUiState.UnAuth(isLoading = true)

                loginUseCase(
                    LoginUseCase.Param(
                        nickname = nickname,
                        phone = phone,
                        firstname = firstname,
                        lastname = lastname
                    )
                )

                onLoginSuccess()
            } catch (throwable: Throwable) {
                Timber.e(throwable, "Couldn't login")
            } finally {
                _viewState.value = LoginUiState.UnAuth(isLoading = false)
            }
        }
    }
}

sealed class LoginUiState {

    data class Auth(
        val isLoading: Boolean = false,
    ) : LoginUiState()

    data class UnAuth(
        val isLoading: Boolean = false,
    ) : LoginUiState()
}