package com.typi.ultra.sample.push

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

class MessagingService : FirebaseMessagingService(),
    KoinComponent {

    private val pushManager: PushManager by inject()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        pushManager.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Timber.d("onMessageReceived: ${message.data}")
        pushManager.onMessageReceived(
            title = message.notification?.title,
            description = message.notification?.body,
            data = message.data,
        )
    }
}