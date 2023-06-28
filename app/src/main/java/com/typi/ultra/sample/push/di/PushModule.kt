package com.typi.ultra.sample.push.di

import com.typi.ultra.sample.AppDependencies
import com.typi.ultra.sample.push.PushManager
import com.typi.ultra.sample.push.PushManagerImpl
import org.koin.dsl.module

object PushModule {

    fun create(appDependencies: AppDependencies) = module {
        single { appDependencies.pushProvider }

        single<PushManager> { PushManagerImpl(get(), get(), get()) }
    }
}