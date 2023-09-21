package com.ultra.sample.ultra

import com.typi.ultra.user.UltraUserDelegate
import com.ultra.sample.contacts.data.ContactRepository
import com.ultra.sample.core.network.AvatarInterceptor
import okhttp3.Interceptor

class UltraUserDelegateImpl(
    interceptor: AvatarInterceptor,
    private val contactRepository: ContactRepository
) : UltraUserDelegate {

    override val avatarInterceptor: Interceptor = interceptor

    override suspend fun getAvatarUrl(identifier: String): String? {
        return runCatching {
            val user = contactRepository.getUserById(identifier)
            "http://ultra-dev.typi.team:8086/v1/profile/get-avatar?phone=${user.phone}"
        }.getOrElse { null }
    }

    override suspend fun getUserName(identifier: String): String? {
        return runCatching {
            val user = contactRepository.getUserById(identifier)
            user.fullName
        }.getOrElse { null }
    }
}