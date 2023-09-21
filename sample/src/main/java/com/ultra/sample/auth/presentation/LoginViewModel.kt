package com.ultra.sample.auth.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ultra.sample.R
import com.ultra.sample.auth.domain.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val viewState: StateFlow<LoginUiState> = _viewState.asStateFlow()

    fun onLoginClicked(
        nickname: String,
        phone: String,
        firstname: String,
        lastname: String?,
        onLoginSuccess: () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _viewState.value = LoginUiState.Loading

                loginUseCase(
                    LoginUseCase.Param(
                        nickname = nickname,
                        phone = phone,
                        firstname = firstname,
                        lastname = lastname
                    )
                )

                _viewState.value = LoginUiState.Idle
                onLoginSuccess()
            } catch (throwable: Throwable) {
                _viewState.value = LoginUiState.Error(R.string.ultra_something_went_wrong)
            }
        }
    }
}

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Error(@StringRes val messageResId: Int) : LoginUiState()
}