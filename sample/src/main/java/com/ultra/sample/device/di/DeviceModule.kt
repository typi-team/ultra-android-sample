package com.ultra.sample.device.di

import com.ultra.sample.device.data.DeviceRepository
import com.ultra.sample.device.data.DeviceRepositoryImpl
import com.ultra.sample.device.data.remote.DeviceRemoteApi
import com.ultra.sample.device.data.remote.DeviceRemoteDataSource
import com.ultra.sample.device.data.remote.DeviceRemoteDataSourceImpl
import com.ultra.sample.device.domain.UpdateDeviceUseCase
import com.ultra.sample.device.manager.DeviceManager
import com.ultra.sample.device.manager.DeviceManagerImpl
import org.koin.dsl.module
import retrofit2.Retrofit

object DeviceModule {

    fun create() = module {
        single { provideDeviceRemoteApi(get()) }

        single<DeviceRemoteDataSource> { DeviceRemoteDataSourceImpl(get()) }
        single<DeviceRepository> { DeviceRepositoryImpl(get()) }
        single<DeviceManager> { DeviceManagerImpl(get()) }

        single { UpdateDeviceUseCase(get(), get()) }
    }

    private fun provideDeviceRemoteApi(retrofit: Retrofit): DeviceRemoteApi {
        return retrofit.create(DeviceRemoteApi::class.java)
    }
}