package com.ultra.sample.ultra

import com.typi.ultra.integration.user.UltraUserDelegate
import com.ultra.sample.contacts.data.ContactRepository
import com.ultra.sample.core.network.AvatarInterceptor
import okhttp3.Interceptor

class UltraUserDelegateImpl(
    interceptor: AvatarInterceptor,
    private val contactRepository: ContactRepository,
) : UltraUserDelegate {

    override val avatarInterceptor: Interceptor = interceptor

    override suspend fun getAvatarUrl(identifier: String): String? {
        return runCatching {
            "https://ultra-dev.typi.team/mock/v1/profile/get-avatar?id=$identifier"
        }.getOrElse { null }
    }

    override suspend fun getUserName(identifier: String): String? {
        return runCatching {
            val user = contactRepository.getUser(identifier)
            user.fullName
        }.getOrElse { null }
    }
}