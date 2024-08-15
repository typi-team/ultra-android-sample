package com.ultra.sample.ultra.delegates

import com.typi.ultra.integration.message.UltraMessageDelegate
import com.ultra.sample.core.settings.SettingsManager

class UltraMessageDelegateImpl(
    private val settingsManager: SettingsManager
) : UltraMessageDelegate {

    override fun getMessageProperties(): Map<String, String> {
        return buildMap {
            val reception = settingsManager.receptionNumber
            if (reception.isNotEmpty()) {
                put(RECEPTION, reception)
            }
        }
    }

    private companion object {

        const val RECEPTION = "reception"
    }
}