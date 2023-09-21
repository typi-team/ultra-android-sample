package com.ultra.sample.ultra

import com.typi.ultra.UltraFeatureToggle

class UltraFeatureToggleImpl : UltraFeatureToggle {

    override val isMoneyTransferEnabled: Boolean = true
    override val isCallsEnabled: Boolean = false
}