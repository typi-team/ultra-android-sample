package com.ultra.sample.contacts.di

import com.ultra.sample.contacts.data.ContactRemoteApi
import com.ultra.sample.contacts.data.ContactRepository
import com.ultra.sample.contacts.data.ContactRepositoryImpl
import com.ultra.sample.contacts.data.store.ContactsDataStore
import com.ultra.sample.contacts.data.store.ContactsDataStoreImpl
import com.ultra.sample.contacts.domain.CreateContactUseCase
import com.ultra.sample.contacts.domain.SyncContactsUseCase
import com.ultra.sample.contacts.ui.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object ContactModule {

    fun create() = module {
        single<ContactsDataStore> { ContactsDataStoreImpl(get()) }
        single<ContactRepository> { ContactRepositoryImpl(get()) }
        single { provideContactRemoteApi(get()) }

        single { SyncContactsUseCase(get(), get()) }
        single { CreateContactUseCase(get(), get()) }

        viewModel { ContactsViewModel(get(), get(), get(), get()) }
    }

    private fun provideContactRemoteApi(retrofit: Retrofit): ContactRemoteApi {
        return retrofit.create(ContactRemoteApi::class.java)
    }
}