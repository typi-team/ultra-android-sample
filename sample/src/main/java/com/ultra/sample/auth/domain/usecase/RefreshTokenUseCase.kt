package com.ultra.sample.auth.domain.usecase

import com.ultra.sample.auth.data.AuthRepository
import com.ultra.sample.auth.domain.manager.SessionManager
import com.ultra.sample.core.base.UseCase
import com.ultra.sample.core.settings.SettingsManager

class RefreshTokenUseCase(
    private val sessionManager: SessionManager,
    private val settingsManager: SettingsManager,
    private val authRepository: AuthRepository,
) : UseCase<Unit, String>() {

    override suspend fun execute(parameters: Unit): String {
        val response = authRepository.login(
            nickname = settingsManager.nickname,
            phone = settingsManager.phone,
            firstname = settingsManager.firstName,
            lastname = settingsManager.lastName
        )
        sessionManager.sid = response.sid
        return response.sid
    }
}