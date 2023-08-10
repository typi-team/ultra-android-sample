package com.ultra.sample.auth.domain.manager

import kotlinx.coroutines.flow.StateFlow

interface SessionManager {

    val isAuthorized: StateFlow<Boolean>
    var sid: String?

    fun clear()
}