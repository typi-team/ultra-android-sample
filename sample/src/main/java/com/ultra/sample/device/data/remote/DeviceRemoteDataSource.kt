package com.ultra.sample.device.data.remote

import com.ultra.sample.device.data.remote.model.DeviceInfoRequest
import com.ultra.sample.device.model.DeviceInfo

interface DeviceRemoteDataSource {

    suspend fun updateDevice(deviceInfo: DeviceInfo)
}

class DeviceRemoteDataSourceImpl(
    private val remoteApi: DeviceRemoteApi
) : DeviceRemoteDataSource {

    override suspend fun updateDevice(deviceInfo: DeviceInfo) {
        remoteApi.updateDevice(deviceInfo.toRequest())
    }

    private fun DeviceInfo.toRequest() = DeviceInfoRequest(
        deviceId = deviceId,
        platform = platform,
        appVersion = appVersion,
        pushToken = pushToken,
    )
}