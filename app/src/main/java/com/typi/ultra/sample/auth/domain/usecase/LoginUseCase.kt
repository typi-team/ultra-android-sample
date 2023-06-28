package com.typi.ultra.sample.auth.domain.usecase

import com.typi.ultra.auth.api.UltraAuthProvider
import com.typi.ultra.sample.auth.data.AuthRepository
import com.typi.ultra.sample.auth.domain.manager.SessionManager
import com.typi.ultra.sample.core.base.UseCase
import com.typi.ultra.sample.core.settings.SettingsManager

class LoginUseCase(
    private val sessionManager: SessionManager,
    private val settingsManager: SettingsManager,
    private val authProvider: UltraAuthProvider,
    private val authRepository: AuthRepository,
) : UseCase<LoginUseCase.Param, Unit>() {

    override suspend fun execute(parameters: Param) {
        val response = authRepository.login(
            phone = parameters.phone,
            firstname = parameters.firstname,
            lastname = parameters.lastname
        )
        authProvider.login(sid = response.sid)
        settingsManager.phone = response.phone
        settingsManager.firstName = response.firstName
        settingsManager.lastName = response.lastName.orEmpty()
        sessionManager.sid = response.sid
    }

    data class Param(
        val phone: String,
        val firstname: String,
        val lastname: String?,
    )
}