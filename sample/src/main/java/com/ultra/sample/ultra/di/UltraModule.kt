package com.ultra.sample.ultra.di

import com.typi.ultra.integration.auth.UltraAuthDelegate
import com.typi.ultra.integration.localise.UltraLocaliseDelegate
import com.typi.ultra.integration.recorder.UltraErrorRecorder
import com.typi.ultra.integration.settings.UltraSettingsDelegate
import com.typi.ultra.integration.theme.UltraThemeDelegate
import com.typi.ultra.integration.toggle.UltraFeatureToggle
import com.typi.ultra.integration.user.UltraUserDelegate
import com.ultra.sample.AppDependencies
import com.ultra.sample.ultra.delegates.UltraAuthDelegateImpl
import com.ultra.sample.ultra.delegates.UltraErrorRecorderImpl
import com.ultra.sample.ultra.delegates.UltraFeatureToggleImpl
import com.ultra.sample.ultra.delegates.UltraLocaliseDelegateImpl
import com.ultra.sample.ultra.delegates.UltraSettingsDelegateImpl
import com.ultra.sample.ultra.delegates.UltraThemeDelegateImpl
import com.ultra.sample.ultra.delegates.UltraUserDelegateImpl
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
        single<UltraErrorRecorder> { UltraErrorRecorderImpl() }
        single<UltraLocaliseDelegate> { UltraLocaliseDelegateImpl() }
    }
}