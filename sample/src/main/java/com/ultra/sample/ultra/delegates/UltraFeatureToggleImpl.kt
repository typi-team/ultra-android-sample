package com.ultra.sample.ultra.delegates

import com.typi.ultra.integration.toggle.UltraFeatureToggle

class UltraFeatureToggleImpl : UltraFeatureToggle {

    override val isMoneyTransferEnabled: Boolean = true
    override val isCallsEnabled: Boolean = false
    override val isComplaintEnabled: Boolean = false
}