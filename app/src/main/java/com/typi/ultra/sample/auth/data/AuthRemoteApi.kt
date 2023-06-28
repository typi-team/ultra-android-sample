package com.typi.ultra.sample.auth.data

import com.typi.ultra.sample.auth.data.model.LoginRequest
import com.typi.ultra.sample.auth.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRemoteApi {

    @POST("auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}