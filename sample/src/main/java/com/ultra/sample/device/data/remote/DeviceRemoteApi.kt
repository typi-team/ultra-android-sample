package com.ultra.sample.device.data.remote

import com.ultra.sample.device.data.remote.model.DeviceInfoRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface DeviceRemoteApi {

    @POST("device")
    suspend fun updateDevice(@Body request: DeviceInfoRequest)
}