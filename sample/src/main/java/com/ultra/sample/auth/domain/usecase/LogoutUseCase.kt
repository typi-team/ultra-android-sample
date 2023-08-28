package com.ultra.sample.auth.domain.usecase

import com.typi.ultra.auth.api.UltraAuthProvider
import com.ultra.sample.auth.domain.manager.SessionManager
import com.ultra.sample.core.base.UseCase
import com.ultra.sample.core.settings.SettingsManager

class LogoutUseCase(
    private val sessionManager: SessionManager,
    private val settingsManager: SettingsManager,
    private val authProvider: UltraAuthProvider,
) : UseCase<Unit, Unit>() {

    override suspend fun execute(parameters: Unit) {
        settingsManager.clear()
        sessionManager.clear()
        authProvider.logout()
    }
}