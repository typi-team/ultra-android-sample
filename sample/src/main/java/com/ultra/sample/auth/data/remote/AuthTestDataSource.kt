package com.ultra.sample.auth.data.remote

import com.ultra.sample.BuildConfig
import com.ultra.sample.auth.data.model.LoginResponse

// TODO: for testing in customer environment
class AuthTestDataSource : AuthRemoteDataSource {

    override suspend fun login(nickname: String, phone: String, firstname: String, lastname: String?): LoginResponse {
        return LoginResponse(
            sid = BuildConfig.TEST_SID,
            nickname = nickname,
            phone = phone,
            firstName = firstname,
            lastName = lastname
        )
    }
}