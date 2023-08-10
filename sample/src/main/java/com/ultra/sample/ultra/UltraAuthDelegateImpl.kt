package com.ultra.sample.ultra

import com.typi.ultra.auth.api.UltraAuthDelegate
import com.ultra.sample.auth.domain.usecase.RefreshTokenUseCase

class UltraAuthDelegateImpl(
    private val refreshTokenUseCase: RefreshTokenUseCase,
) : UltraAuthDelegate {

    override suspend fun getSid(): String {
        return refreshTokenUseCase(Unit)
    }
}