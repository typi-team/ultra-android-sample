package com.typi.ultra.sample.core.di

import com.typi.ultra.sample.AppDependencies
import com.typi.ultra.sample.auth.di.AuthModule
import com.typi.ultra.sample.contacts.di.ContactModule
import com.typi.ultra.sample.core.network.NetworkModule
import com.typi.ultra.sample.main.di.MainModule
import com.typi.ultra.sample.push.di.PushModule
import com.ultra.sample.splash.di.SplashModule
import com.typi.ultra.sample.ultra.di.UltraModule
import org.koin.core.module.Module

object KoinModules {

    fun create(appDependencies: AppDependencies): List<Module> = listOf(
        ApplicationModule.create(),
        NetworkModule.create(),
        SplashModule.create(),
        AuthModule.create(appDependencies),
        PushModule.create(appDependencies),
        MainModule.create(),
        UltraModule.create(),
        ContactModule.create(),
    )
}