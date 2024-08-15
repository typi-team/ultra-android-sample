package com.ultra.sample.reception.domain

import com.typi.ultra.integration.auth.UltraAuthProvider
import com.ultra.sample.core.base.UseCase
import com.ultra.sample.core.settings.SettingsManager

class ChangeReceptionUseCase(
    private val settingsManager: SettingsManager,
    private val authProvider: UltraAuthProvider,
) : UseCase<ChangeReceptionUseCase.Param, Unit>() {

    override suspend fun execute(parameters: Param) {
        settingsManager.receptionNumber = parameters.receptionNumber
        authProvider.logout()
    }

    data class Param(
        val receptionNumber: String,
    )
}