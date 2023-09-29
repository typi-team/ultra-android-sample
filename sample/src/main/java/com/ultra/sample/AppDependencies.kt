package com.ultra.sample

import com.typi.ultra.integration.auth.UltraAuthProvider
import com.typi.ultra.integration.cache.UltraCacheProvider
import com.typi.ultra.integration.navigation.UltraNavigator
import com.typi.ultra.integration.push.UltraPushProvider

interface AppDependencies {

    val authProvider: UltraAuthProvider
    val pushProvider: UltraPushProvider
    val screenStarter: UltraNavigator
    val cacheProvider: UltraCacheProvider
}