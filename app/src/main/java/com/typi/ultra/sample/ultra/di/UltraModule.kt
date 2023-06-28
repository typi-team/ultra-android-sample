package com.typi.ultra.sample.ultra.di

import com.typi.ultra.UltraScreenDelegate
import com.typi.ultra.auth.api.UltraAuthDelegate
import com.typi.ultra.push.api.UltraPushDelegate
import com.typi.ultra.uikit.UltraThemeDelegate
import com.typi.ultra.sample.ultra.UltraAuthDelegateImpl
import com.typi.ultra.sample.ultra.UltraPushDelegateImpl
import com.typi.ultra.sample.ultra.UltraScreenDelegateImpl
import com.typi.ultra.sample.ultra.UltraThemeDelegateImpl
import org.koin.dsl.module

object UltraModule {

    fun create() = module {
        single<UltraAuthDelegate> { UltraAuthDelegateImpl(get()) }
        single<UltraPushDelegate> { UltraPushDelegateImpl() }
        single<UltraThemeDelegate> { UltraThemeDelegateImpl(get()) }
        single<UltraScreenDelegate> { UltraScreenDelegateImpl() }
    }
}