package com.ultra.sample.device.manager

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

interface DeviceManager {

    fun getDeviceId(): String
}

class DeviceManagerImpl(
    private val context: Context,
) : DeviceManager {

    @SuppressLint("HardwareIds")
    override fun getDeviceId(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}