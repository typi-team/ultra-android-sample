package com.typi.ultra.sample.auth.data

import com.typi.ultra.sample.auth.data.model.LoginRequest
import com.typi.ultra.sample.auth.data.model.LoginResponse

interface AuthRepository {

    suspend fun login(
        phone: String,
        firstname: String,
        lastname: String?,
    ): LoginResponse
}

class AuthRepositoryImpl(
    private val remoteApi: AuthRemoteApi,
) : AuthRepository {

    override suspend fun login(phone: String, firstname: String, lastname: String?): LoginResponse {
        return remoteApi.login(
            request = LoginRequest(
                phone = phone,
                firstName = firstname,
                lastName = lastname
            )
        )
    }
}