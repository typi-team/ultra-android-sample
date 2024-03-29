package com.ultra.sample.device.data.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DeviceInfoRequest(
    @SerializedName("device_id")
    val deviceId: String,
    @SerializedName("platform")
    val platform: String,
    @SerializedName("app_version")
    val appVersion: String,
    @SerializedName("token")
    val pushToken: String,
)
