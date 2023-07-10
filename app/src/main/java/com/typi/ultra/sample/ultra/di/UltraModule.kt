package com.typi.ultra.sample.ultra.di

import com.typi.ultra.UltraScreenDelegate
import com.typi.ultra.auth.api.UltraAuthDelegate
import com.typi.ultra.network.UltraNetworkDelegate
import com.typi.ultra.push.api.UltraPushDelegate
import com.typi.ultra.sample.ultra.UltraAuthDelegateImpl
import com.typi.ultra.sample.ultra.UltraNetworkDelegateImpl
import com.typi.ultra.sample.ultra.UltraPushDelegateImpl
import com.typi.ultra.sample.ultra.UltraScreenDelegateImpl
import com.typi.ultra.sample.ultra.UltraThemeDelegateImpl
import com.typi.ultra.uikit.UltraThemeDelegate
import org.koin.dsl.module

object UltraModule {

    fun create() = module {
        single<UltraAuthDelegate> { UltraAuthDelegateImpl(get()) }
        single<UltraPushDelegate> { UltraPushDelegateImpl() }
        single<UltraThemeDelegate> { UltraThemeDelegateImpl(get()) }
        single<UltraScreenDelegate> { UltraScreenDelegateImpl() }
        single<UltraNetworkDelegate> { UltraNetworkDelegateImpl() }
    }
}