package com.ultra.sample.ultra.delegates

import com.typi.ultra.integration.settings.UltraSettingsDelegate
import com.typi.ultra.integration.settings.model.UltraNetworkSettings
import com.typi.ultra.integration.settings.model.UltraPushSettings
import com.ultra.sample.BuildConfig
import com.ultra.sample.R

class UltraSettingsDelegateImpl : UltraSettingsDelegate {

    override val networkSettings: UltraNetworkSettings = createNetworkSettings()

    override val pushSettings: UltraPushSettings = createPushSettings()

    private fun createNetworkSettings(): UltraNetworkSettings {
        return UltraNetworkSettings(
            url = BuildConfig.ULTRA_URL,
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