package com.ultra.sample.splash.di

import com.ultra.sample.splash.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SplashModule {

    fun create() = module {
        viewModel { SplashViewModel(get()) }
    }
}