package com.ultra.sample.settings.di

import com.ultra.sample.auth.domain.usecase.LogoutUseCase
import com.ultra.sample.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SettingsModule {

    fun create() = module {
        single { LogoutUseCase(get(), get(), get()) }

        viewModel { SettingsViewModel(get()) }
    }
}