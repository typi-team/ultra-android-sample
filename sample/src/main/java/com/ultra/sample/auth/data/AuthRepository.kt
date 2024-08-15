package com.ultra.sample.auth.data

import com.ultra.sample.auth.data.model.LoginRequest
import com.ultra.sample.auth.data.model.LoginResponse
import com.ultra.sample.auth.data.remote.AuthRemoteApi

interface AuthRepository {

    suspend fun login(
        nickname: String,
        phone: String,
        firstname: String,
        lastname: String?,
        receptionNumber: String?,
        deviceId: String,
    ): LoginResponse
}

class AuthRepositoryImpl(
    private val remoteApi: AuthRemoteApi,
) : AuthRepository {

    override suspend fun login(
        nickname: String,
        phone: String,
        firstname: String,
        lastname: String?,
        receptionNumber: String?,
        deviceId: String,
    ): LoginResponse {
        return remoteApi.login(
            request = LoginRequest(
                nickname = nickname,
                phone = phone,
                firstName = firstname,
                lastName = lastname,
                reception = receptionNumber?.toIntOrNull(),
                receptionService = receptionNumber?.toIntOrNull(),
                deviceId = deviceId,
            )
        )
    }
}