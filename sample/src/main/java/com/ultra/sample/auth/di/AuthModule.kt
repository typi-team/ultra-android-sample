package com.ultra.sample.auth.di

import com.ultra.sample.BuildConfig
import com.ultra.sample.auth.data.AuthRepository
import com.ultra.sample.auth.data.AuthRepositoryImpl
import com.ultra.sample.auth.data.remote.AuthDevDataSource
import com.ultra.sample.auth.data.remote.AuthRemoteApi
import com.ultra.sample.auth.data.remote.AuthRemoteDataSource
import com.ultra.sample.auth.data.remote.AuthTestDataSource
import com.ultra.sample.auth.domain.manager.SessionManager
import com.ultra.sample.auth.domain.manager.SessionManagerImpl
import com.ultra.sample.auth.domain.usecase.LoginUseCase
import com.ultra.sample.auth.domain.usecase.RefreshTokenUseCase
import com.ultra.sample.auth.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object AuthModule {

    fun create() = module {
        single<SessionManager> { SessionManagerImpl(get()) }
        single<AuthRepository> { AuthRepositoryImpl(get()) }
        single<AuthRemoteDataSource> {
            if (BuildConfig.FLAVOR == TEST_FLAVOR) {
                AuthTestDataSource()
            } else {
                AuthDevDataSource(get())
            }

        }
        single { provideAuthRemoteApi(get()) }

        single { LoginUseCase(get(), get(), get(), get()) }
        single { RefreshTokenUseCase(get(), get(), get()) }

        viewModel { LoginViewModel(get()) }
    }

    private fun provideAuthRemoteApi(retrofit: Retrofit): AuthRemoteApi {
        return retrofit.create(AuthRemoteApi::class.java)
    }

    private const val TEST_FLAVOR = "customerTest"
}