package com.typi.ultra.sample.main.di

import com.typi.ultra.sample.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModule {

    fun create() = module {
        viewModel { MainViewModel(get()) }
    }
}