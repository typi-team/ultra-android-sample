package com.ultra.sample.ultra.delegates

import android.content.Context
import android.net.Uri
import com.typi.ultra.integration.settings.UltraSettingsDelegate
import com.typi.ultra.integration.settings.model.UltraNetworkSettings
import com.typi.ultra.integration.settings.model.UltraPushSettings
import com.ultra.sample.R

class UltraSettingsDelegateImpl(
    private val context: Context
) : UltraSettingsDelegate {

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
            notificationColor = R.color.ic_launcher_background,
            callRingtoneUri = Uri.parse("android.resource://" + context.packageName + "/" + R.raw.sample_ringtone),
            callVibrationPattern = longArrayOf(1480, 1000),
        )
    }
}