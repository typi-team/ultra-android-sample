package com.ultra.sample.auth.data.remote

import com.ultra.sample.auth.data.model.LoginRequest
import com.ultra.sample.auth.data.model.LoginResponse

class AuthDevDataSource(
    private val remoteApi: AuthRemoteApi,
) : AuthRemoteDataSource {

    override suspend fun login(nickname: String, phone: String, firstname: String, lastname: String?): LoginResponse {
        return remoteApi.login(
            request = LoginRequest(
                nickname = nickname,
                phone = phone,
                firstName = firstname,
                lastName = lastname
            )
        )
    }
}