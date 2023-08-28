package com.ultra.sample.ultra

import com.typi.ultra.settings.UltraNetworkSettings
import com.typi.ultra.settings.UltraPushSettings
import com.typi.ultra.settings.UltraSettingsDelegate
import com.ultra.sample.R
import com.ultra.sample.core.network.AvatarInterceptor
import okhttp3.Interceptor

class UltraSettingsDelegateImpl(
    interceptor: AvatarInterceptor
) : UltraSettingsDelegate {

    override val networkSettings: UltraNetworkSettings = createNetworkSettings()

    override val pushSettings: UltraPushSettings = createPushSettings()

    override val avatarInterceptor: Interceptor = interceptor

    override fun getAvatarUrl(phoneNumber: String): String =
        "http://ultra-dev.typi.team:8086/v1/profile/get-avatar?phone=$phoneNumber"

    private fun createNetworkSettings(): UltraNetworkSettings {
        return UltraNetworkSettings(
            url = "ultra-dev.typi.team",
            port = 443
        )
    }

    private fun createPushSettings(): UltraPushSettings {
        return UltraPushSettings(
            notificationSmallIcon = R.drawable.ic_push_notification,
            notificationColor = R.color.ic_launcher_background
        )
    }
}