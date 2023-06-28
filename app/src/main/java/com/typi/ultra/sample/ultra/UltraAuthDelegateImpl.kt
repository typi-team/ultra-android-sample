package com.typi.ultra.sample.ultra

import com.typi.ultra.auth.api.UltraAuthDelegate
import com.typi.ultra.sample.auth.domain.usecase.RefreshTokenUseCase

class UltraAuthDelegateImpl(
    private val refreshTokenUseCase: RefreshTokenUseCase,
) : UltraAuthDelegate {

    override suspend fun getSid(): String {
        return refreshTokenUseCase(Unit)
    }
}