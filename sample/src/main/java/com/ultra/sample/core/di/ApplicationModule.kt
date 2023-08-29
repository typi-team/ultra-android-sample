package com.ultra.sample.core.di

import com.ultra.sample.core.cache.CacheManager
import com.ultra.sample.core.cache.CacheManagerImpl
import com.ultra.sample.core.settings.SettingsManager
import com.ultra.sample.core.settings.SettingsManagerImpl
import org.koin.dsl.module

object ApplicationModule {

    fun create() = module {
        single<SettingsManager> { SettingsManagerImpl(get()) }
        single<CacheManager> { CacheManagerImpl() }
    }
}