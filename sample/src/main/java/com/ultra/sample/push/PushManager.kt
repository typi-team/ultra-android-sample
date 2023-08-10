package com.ultra.sample.push

interface PushManager {

    fun createNotificationChannel()

    fun onNewToken(token: String)

    suspend fun updateToken()

    fun onMessageReceived(
        title: String?,
        description: String?,
        data: Map<String, String>,
    )
}