package com.ultra.sample.ultra.delegates

import com.typi.ultra.integration.toggle.UltraFeatureToggle

class UltraFeatureToggleImpl : UltraFeatureToggle {

    override val isMoneyTransferEnabled: Boolean = true
    override val isCallsEnabled: Boolean = true
    override val isVoiceMessageEnabled: Boolean = true
    override val isComplaintEnabled: Boolean = true
    override val isLocationEnabled: Boolean = false
    override val isUserBlockingEnabled: Boolean = true
}