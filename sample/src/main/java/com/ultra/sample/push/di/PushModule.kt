package com.ultra.sample.push.di

import com.ultra.sample.push.PushManager
import com.ultra.sample.push.PushManagerImpl
import org.koin.dsl.module

object PushModule {

    fun create() = module {
        single<PushManager> { PushManagerImpl(get(), get(), get(), get()) }
    }
}