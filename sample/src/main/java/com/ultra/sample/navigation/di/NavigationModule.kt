package com.ultra.sample.navigation.di

import com.ultra.sample.navigation.NavigationHandler
import com.ultra.sample.navigation.NavigationHandlerImpl
import org.koin.core.module.Module
import org.koin.dsl.module

object NavigationModule {

    fun create(): Module = module {
        single<NavigationHandler> { NavigationHandlerImpl() }
    }
}