package com.ultra.sample.device.data

import com.ultra.sample.device.data.remote.DeviceRemoteDataSource
import com.ultra.sample.device.model.DeviceInfo

interface DeviceRepository {

    suspend fun updateDevice(deviceInfo: DeviceInfo)
}

class DeviceRepositoryImpl(
    private val remoteDataSource: DeviceRemoteDataSource
) : DeviceRepository {

    override suspend fun updateDevice(deviceInfo: DeviceInfo) {
        remoteDataSource.updateDevice(deviceInfo)
    }
}