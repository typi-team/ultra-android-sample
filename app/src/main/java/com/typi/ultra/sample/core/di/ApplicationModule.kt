package com.typi.ultra.sample.core.di

import com.typi.ultra.sample.core.settings.SettingsManager
import com.typi.ultra.sample.core.settings.SettingsManagerImpl
import org.koin.dsl.module

object ApplicationModule {

    fun create() = module {
        single<SettingsManager> { SettingsManagerImpl(get()) }
    }
}