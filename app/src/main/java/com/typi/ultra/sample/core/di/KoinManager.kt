package com.typi.ultra.sample.core.di

import android.content.Context
import com.typi.ultra.sample.AppDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinManager {

    fun init(context: Context, appDependencies: AppDependencies) {
        startKoin {
            androidContext(context)
            modules(KoinModules.create(appDependencies))
        }
    }
}