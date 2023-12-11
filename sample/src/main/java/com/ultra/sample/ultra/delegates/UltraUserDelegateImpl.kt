package com.ultra.sample.ultra.delegates

import com.typi.ultra.integration.user.UltraUserDelegate
import com.typi.ultra.integration.user.UserInfo
import com.ultra.sample.contacts.data.ContactRepository
import com.ultra.sample.core.network.AuthInterceptor
import okhttp3.Interceptor

class UltraUserDelegateImpl(
    authInterceptor: AuthInterceptor,
    private val contactRepository: ContactRepository,
) : UltraUserDelegate {

    override val avatarInterceptor: Interceptor = authInterceptor

    override suspend fun getUserInfo(identifier: String): UserInfo? {
        return runCatching {
            val user = contactRepository.getUser(identifier)
            UserInfo(
                name = user.fullName,
                avatar = "https://ultra-dev.typi.team/mock/v1/profile/get-avatar?phone=${user.phone}"
            )
        }.getOrElse { null }
    }
}