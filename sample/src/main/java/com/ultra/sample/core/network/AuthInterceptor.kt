package com.ultra.sample.core.network

import com.ultra.sample.core.settings.SettingsManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val settingsManager: SettingsManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (settingsManager.sid == null) return chain.proceed(chain.request())
        val request = chain.request().newBuilder()
            .addHeader(HEADER_SID, settingsManager.sid!!)
            .build()
        return chain.proceed(request)
    }

    companion object {

        private const val HEADER_SID = "SID"
    }
}