package com.ultra.sample.auth.domain.manager

import com.ultra.sample.core.settings.SettingsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SessionManagerImpl(
    private val settingManager: SettingsManager
) : SessionManager {

    override var sid: String? = settingManager.sid
        set(value) {
            field = value
            _isAuthorized.value = getAuthorized()
            settingManager.sid = value
        }

    private val _isAuthorized = MutableStateFlow(getAuthorized())
    override val isAuthorized: StateFlow<Boolean>
        get() = _isAuthorized.asStateFlow()

    override fun clear() {
        sid = null
        _isAuthorized.value = getAuthorized()
    }

    private fun getAuthorized(): Boolean = !sid.isNullOrEmpty()
}