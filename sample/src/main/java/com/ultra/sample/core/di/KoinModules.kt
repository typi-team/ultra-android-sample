package com.ultra.sample.core.di

import com.ultra.sample.AppDependencies
import com.ultra.sample.auth.di.AuthModule
import com.ultra.sample.contacts.di.ContactModule
import com.ultra.sample.core.network.NetworkModule
import com.ultra.sample.database.DatabaseModule
import com.ultra.sample.home.di.HomeModule
import com.ultra.sample.money.di.MoneyModule
import com.ultra.sample.navigation.di.NavigationModule
import com.ultra.sample.push.di.PushModule
import com.ultra.sample.splash.di.SplashModule
import com.ultra.sample.ultra.di.UltraModule
import org.koin.core.module.Module

object KoinModules {

    fun create(appDependencies: AppDependencies): List<Module> = listOf(
        ApplicationModule.create(),
        DatabaseModule.create(),
        NetworkModule.create(),
        SplashModule.create(),
        AuthModule.create(),
        PushModule.create(),
        UltraModule.create(appDependencies),
        ContactModule.create(),
        MoneyModule.create(),
        HomeModule.create(),
        NavigationModule.create(),
    )
}