package com.ultra.sample

import com.typi.ultra.integration.UltraInitializer
import com.typi.ultra.integration.auth.UltraAuthProvider
import com.typi.ultra.integration.cache.UltraCacheProvider
import com.typi.ultra.integration.logs.UltraFileProvider
import com.typi.ultra.integration.message.UltraMessageProvider
import com.typi.ultra.integration.navigation.UltraNavigator
import com.typi.ultra.integration.push.UltraPushProvider
import com.typi.ultra.integration.support.UltraSupportProvider
import com.typi.ultra.integration.user.UltraUserProvider

interface AppDependencies {

    val authProvider: UltraAuthProvider
    val pushProvider: UltraPushProvider
    val screenStarter: UltraNavigator
    val cacheProvider: UltraCacheProvider
    val messageProvider: UltraMessageProvider
    val initializer: UltraInitializer
    val fileProvider: UltraFileProvider
    val userProvider: UltraUserProvider
    val supportProvider: UltraSupportProvider
}