package com.ultra.sample.ultra

import com.typi.ultra.settings.UltraNetworkSettings
import com.typi.ultra.settings.UltraPushSettings
import com.typi.ultra.settings.UltraSettingsDelegate
import com.ultra.sample.R

class UltraSettingsDelegateImpl : UltraSettingsDelegate {

    override val networkSettings: UltraNetworkSettings = createNetworkSettings()

    override val pushSettings: UltraPushSettings = createPushSettings()

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