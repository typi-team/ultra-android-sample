package com.ultra.sample.device.domain

import com.ultra.sample.BuildConfig
import com.ultra.sample.core.base.UseCase
import com.ultra.sample.device.data.DeviceRepository
import com.ultra.sample.device.manager.DeviceManager
import com.ultra.sample.device.model.DeviceInfo

class UpdateDeviceUseCase(
    private val deviceManager: DeviceManager,
    private val deviceRepository: DeviceRepository
) : UseCase<UpdateDeviceUseCase.Param, Unit>() {

    override suspend fun execute(parameters: Param) {
        val deviceInfo = DeviceInfo(
            deviceId = deviceManager.getDeviceId(),
            platform = PLATFORM,
            appVersion = BuildConfig.VERSION_NAME,
            pushToken = parameters.pushToken
        )
        deviceRepository.updateDevice(deviceInfo)
    }

    data class Param(
        val pushToken: String
    )

    companion object {

        private const val PLATFORM = "ANDROID"
    }
}