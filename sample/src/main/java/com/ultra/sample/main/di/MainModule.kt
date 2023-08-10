package com.ultra.sample.main.di

import com.ultra.sample.main.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModule {

    fun create() = module {
        viewModel { MainViewModel(get()) }
    }
}