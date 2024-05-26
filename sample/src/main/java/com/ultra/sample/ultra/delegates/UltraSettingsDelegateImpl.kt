package com.ultra.sample.ultra.delegates

import android.content.Context
import android.net.Uri
import com.typi.ultra.integration.settings.UltraSettingsDelegate
import com.typi.ultra.integration.settings.model.UltraNetworkSettings
import com.typi.ultra.integration.settings.model.UltraPushSettings
import com.ultra.sample.BuildConfig
import com.ultra.sample.R
import com.ultra.sample.core.settings.SettingsManager
import java.util.UUID

class UltraSettingsDelegateImpl(
    private val context: Context,
    private val settingsManager: SettingsManager,
) : UltraSettingsDelegate {

    override val networkSettings: UltraNetworkSettings = createNetworkSettings()

    override val pushSettings: UltraPushSettings = createPushSettings()

    override fun getEncryptionKey(): String? =
        if (BuildConfig.DEBUG) {
            null
        } else {
            settingsManager.applicationId ?: generateApplicationId()
        }

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

    private fun generateApplicationId(): String {
        val id = UUID.randomUUID().toString()
        settingsManager.applicationId = id
        return id
    }
}