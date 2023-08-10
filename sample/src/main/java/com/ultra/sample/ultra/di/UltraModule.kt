package com.ultra.sample.ultra.di

import com.typi.ultra.auth.api.UltraAuthDelegate
import com.typi.ultra.settings.UltraSettingsDelegate
import com.typi.ultra.uikit.UltraThemeDelegate
import com.ultra.sample.AppDependencies
import com.ultra.sample.ultra.UltraAuthDelegateImpl
import com.ultra.sample.ultra.UltraSettingsDelegateImpl
import com.ultra.sample.ultra.UltraThemeDelegateImpl
import org.koin.dsl.module

object UltraModule {

    fun create(appDependencies: AppDependencies) = module {
        single { appDependencies.authProvider }
        single { appDependencies.pushProvider }
        single { appDependencies.screenStarter }
        single { appDependencies.cacheProvider }

        single<UltraSettingsDelegate> { UltraSettingsDelegateImpl() }
        single<UltraAuthDelegate> { UltraAuthDelegateImpl(get()) }
        single<UltraThemeDelegate> { UltraThemeDelegateImpl(get()) }
    }
}