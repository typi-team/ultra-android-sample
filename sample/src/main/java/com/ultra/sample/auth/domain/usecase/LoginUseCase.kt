package com.ultra.sample.auth.domain.usecase

import com.typi.ultra.auth.api.UltraAuthProvider
import com.ultra.sample.auth.data.AuthRepository
import com.ultra.sample.auth.domain.manager.SessionManager
import com.ultra.sample.core.base.UseCase
import com.ultra.sample.core.settings.SettingsManager

class LoginUseCase(
    private val sessionManager: SessionManager,
    private val settingsManager: SettingsManager,
    private val authProvider: UltraAuthProvider,
    private val authRepository: AuthRepository,
) : UseCase<LoginUseCase.Param, Unit>() {

    override suspend fun execute(parameters: Param) {
        val response = authRepository.login(
            nickname = parameters.nickname,
            phone = parameters.phone,
            firstname = parameters.firstname,
            lastname = parameters.lastname
        )
        settingsManager.nickname = response.nickname
        settingsManager.phone = response.phone
        settingsManager.firstName = response.firstName
        settingsManager.lastName = response.lastName.orEmpty()
        sessionManager.sid = response.sid
        authProvider.login(sid = response.sid)
    }

    data class Param(
        val nickname: String,
        val phone: String,
        val firstname: String,
        val lastname: String?,
    )
}