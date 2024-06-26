package com.ultra.sample.ultra.delegates

import android.content.Context
import com.typi.ultra.integration.user.UltraUserDelegate
import com.typi.ultra.integration.user.UltraUserInfo
import com.typi.ultra.integration.user.UltraUserType
import com.ultra.sample.R
import com.ultra.sample.contacts.data.ContactRepository
import com.ultra.sample.core.network.AuthInterceptor
import okhttp3.Interceptor
import timber.log.Timber

class UltraUserDelegateImpl(
    private val context: Context,
    authInterceptor: AuthInterceptor,
    private val contactRepository: ContactRepository,
) : UltraUserDelegate {

    override val avatarInterceptor: Interceptor = authInterceptor

    override suspend fun getUserInfo(identifier: String, type: UltraUserType): UltraUserInfo? {
        Timber.d("getUserInfo $identifier $type")
        return runCatching {
            val user = contactRepository.getUser(identifier)
            UltraUserInfo(
                name = user.fullName,
                avatarUrl = "https://ultra-dev.typi.team/mock/v1/profile/get-avatar?phone=${user.phone}"
            )
        }.getOrElse { null }
    }

    override suspend fun getDisclaimerText(identifier: String): String =
        context.getString(R.string.chat_chat_detail_disclaimer_title)

    override fun onDisclaimerAcceptClicked() = Unit

    override fun onDisclaimerCancelClicked() = Unit
}