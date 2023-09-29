package com.ultra.sample.money.di

import com.ultra.sample.money.data.MoneyRepository
import com.ultra.sample.money.data.MoneyRepositoryImpl
import com.ultra.sample.money.presentation.SendMoneyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MoneyModule {

    fun create() = module {
        single<MoneyRepository> { MoneyRepositoryImpl(get()) }

        viewModel { SendMoneyViewModel(get()) }
    }
}