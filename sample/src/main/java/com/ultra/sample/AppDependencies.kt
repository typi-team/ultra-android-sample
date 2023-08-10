package com.ultra.sample

import com.typi.ultra.auth.api.UltraAuthProvider
import com.typi.ultra.cache.api.UltraCacheProvider
import com.typi.ultra.navigation.UltraNavigator
import com.typi.ultra.push.api.UltraPushProvider

interface AppDependencies {

    val authProvider: UltraAuthProvider
    val pushProvider: UltraPushProvider
    val screenStarter: UltraNavigator
    val cacheProvider: UltraCacheProvider
}