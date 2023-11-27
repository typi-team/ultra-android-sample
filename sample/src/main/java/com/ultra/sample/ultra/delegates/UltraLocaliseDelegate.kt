package com.ultra.sample.ultra.delegates

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.typi.ultra.integration.localise.DefaultUltraLocalise
import com.typi.ultra.integration.localise.UltraLocalise
import com.typi.ultra.integration.localise.UltraLocaliseDelegate

class UltraLocaliseDelegateImpl : UltraLocaliseDelegate {

    private val _localise = mutableStateOf(DefaultUltraLocalise())
    override val localise: State<UltraLocalise> = _localise
}