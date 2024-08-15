package com.ultra.sample.auth.domain.usecase

import com.ultra.sample.auth.data.AuthRepository
import com.ultra.sample.core.base.UseCase
import com.ultra.sample.core.settings.SettingsManager
import com.ultra.sample.device.manager.DeviceManager

class RefreshTokenUseCase(
    private val deviceManager: DeviceManager,
    private val settingsManager: SettingsManager,
    private val authRepository: AuthRepository,
) : UseCase<Unit, String>() {

    override suspend fun execute(parameters: Unit): String {
        val response = authRepository.login(
            nickname = settingsManager.nickname,
            phone = settingsManager.phone,
            firstname = settingsManager.firstName,
            lastname = settingsManager.lastName,
            receptionNumber = settingsManager.receptionNumber,
            deviceId = deviceManager.getDeviceId(),
        )
        settingsManager.sid = response.sid
        return response.sid
    }
}