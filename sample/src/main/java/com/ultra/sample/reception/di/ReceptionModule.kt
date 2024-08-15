package com.ultra.sample.reception.di

import com.ultra.sample.reception.ReceptionViewModel
import com.ultra.sample.reception.domain.ChangeReceptionUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ReceptionModule {

    fun create() = module {
        single { ChangeReceptionUseCase(get(), get()) }

        viewModel { ReceptionViewModel(get()) }
    }
}