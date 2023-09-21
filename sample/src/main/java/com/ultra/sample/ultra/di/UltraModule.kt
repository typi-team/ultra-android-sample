package com.ultra.sample.ultra.di

import com.typi.ultra.UltraFeatureToggle
import com.typi.ultra.auth.api.UltraAuthDelegate
import com.typi.ultra.settings.UltraSettingsDelegate
import com.typi.ultra.uikit.UltraThemeDelegate
import com.typi.ultra.user.UltraUserDelegate
import com.ultra.sample.AppDependencies
import com.ultra.sample.ultra.UltraAuthDelegateImpl
import com.ultra.sample.ultra.UltraFeatureToggleImpl
import com.ultra.sample.ultra.UltraSettingsDelegateImpl
import com.ultra.sample.ultra.UltraThemeDelegateImpl
import com.ultra.sample.ultra.UltraUserDelegateImpl
import org.koin.dsl.module

object UltraModule {

    fun create(appDependencies: AppDependencies) = module {
        single { appDependencies.authProvider }
        single { appDependencies.pushProvider }
        single { appDependencies.screenStarter }
        single { appDependencies.cacheProvider }

        single<UltraSettingsDelegate> { UltraSettingsDelegateImpl(get()) }
        single<UltraAuthDelegate> { UltraAuthDelegateImpl(get()) }
        single<UltraThemeDelegate> { UltraThemeDelegateImpl(get()) }
        single<UltraFeatureToggle> { UltraFeatureToggleImpl() }
        single<UltraUserDelegate> { UltraUserDelegateImpl(get(), get()) }
    }
}