package com.ultra.sample.auth.data

import com.ultra.sample.auth.data.model.LoginResponse
import com.ultra.sample.auth.data.remote.AuthRemoteDataSource

interface AuthRepository {

    suspend fun login(
        nickname: String,
        phone: String,
        firstname: String,
        lastname: String?,
    ): LoginResponse
}

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override suspend fun login(nickname: String, phone: String, firstname: String, lastname: String?): LoginResponse {
        return remoteDataSource.login(
            nickname = nickname,
            phone = phone,
            firstname = firstname,
            lastname = lastname
        )
    }
}