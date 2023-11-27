package com.ultra.sample.device.model

data class DeviceInfo(
    val deviceId: String,
    val platform: String,
    val appVersion: String,
    val pushToken: String,
)
