package com.ultra.sample.home.di

import com.ultra.sample.auth.domain.usecase.LogoutUseCase
import com.ultra.sample.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomeModule {

    fun create() = module {
        single { LogoutUseCase(get(), get(), get()) }

        viewModel { HomeViewModel(get(), get(), get()) }
    }
}