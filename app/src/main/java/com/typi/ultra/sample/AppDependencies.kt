package com.typi.ultra.sample

import com.typi.ultra.auth.api.UltraAuthProvider
import com.typi.ultra.push.api.UltraPushProvider

interface AppDependencies {

    val authProvider: UltraAuthProvider
    val pushProvider: UltraPushProvider
}