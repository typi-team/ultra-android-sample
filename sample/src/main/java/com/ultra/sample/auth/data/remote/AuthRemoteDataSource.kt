package com.ultra.sample.auth.data.remote

import com.ultra.sample.auth.data.model.LoginResponse

interface AuthRemoteDataSource {

    suspend fun login(nickname: String, phone: String, firstname: String, lastname: String?): LoginResponse
}