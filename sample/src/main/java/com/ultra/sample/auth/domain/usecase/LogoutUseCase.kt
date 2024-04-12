package com.ultra.sample.auth.domain.usecase

import com.typi.ultra.integration.auth.UltraAuthProvider
import com.ultra.sample.core.base.UseCase
import com.ultra.sample.core.settings.SettingsManager

class LogoutUseCase(
    private val settingsManager: SettingsManager,
    private val authProvider: UltraAuthProvider,
) : UseCase<Unit, Unit>() {

    override suspend fun execute(parameters: Unit) {
        settingsManager.clear()
        authProvider.logout()
    }
}