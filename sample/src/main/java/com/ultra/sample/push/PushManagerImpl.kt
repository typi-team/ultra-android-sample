package com.ultra.sample.push

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.typi.ultra.integration.push.UltraPushProvider
import com.ultra.sample.R
import com.ultra.sample.auth.domain.manager.SessionManager
import com.ultra.sample.main.presentation.MainActivity
import java.security.SecureRandom
import kotlinx.coroutines.tasks.await

internal class PushManagerImpl(
    private val context: Context,
    private val pushProvider: UltraPushProvider,
    private val sessionManager: SessionManager,
) : PushManager {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private val random = SecureRandom()

    override fun createNotificationChannel() {
        pushProvider.createNotificationChannel()
    }

    override fun onNewToken(token: String) {
        if (!sessionManager.isAuthorized.value) return

        pushProvider.onNewToken(token)
    }

    override suspend fun updateToken() {
        val token = FirebaseMessaging.getInstance()
            .token
            .await()
        onNewToken(token)
    }

    override fun onMessageReceived(
        title: String?,
        description: String?,
        data: Map<String, String>,
    ) {
        val isChatMessage = pushProvider.isChatPush(data)
        if (isChatMessage) {
            pushProvider.showNotification(
                data = data,
                intent = Intent(context, MainActivity::class.java)
                    .putExtra(PUSH_TYPE, data[PUSH_TYPE])
                    .putExtra(USER_ID, data[USER_ID].orEmpty())
            )
            return
        }

        showNotification(
            title = title.orEmpty(),
            description = description.orEmpty(),
            intent = Intent(context, MainActivity::class.java),
        )
    }

    private fun showNotification(title: String, description: String, intent: Intent) {
        val notification = createNotification(
            title = title,
            description = description,
            pendingIntent = PendingIntent.getActivity(
                context,
                random.nextInt(),
                intent,
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PendingIntent.FLAG_IMMUTABLE
                } else {
                    PendingIntent.FLAG_UPDATE_CURRENT
                }
            )
        )
        notificationManager.notify(random.nextInt(), notification)
    }

    private fun createNotification(
        title: String,
        description: String,
        pendingIntent: PendingIntent,
    ): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_push_notification)
            .setColor(ContextCompat.getColor(context, R.color.ic_launcher_background))
            .setContentTitle(title)
            .setContentText(description)
            .setContentIntent(pendingIntent)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(description)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setGroup(GROUP)
            .setWhen(System.currentTimeMillis())
            .setShowWhen(true)
            .setAutoCancel(true)
            .build()
    }

    companion object {

        private const val CHANNEL_ID = "sample_channel"
        private const val GROUP = "sample_group"

        private const val PUSH_TYPE = "push_type"
        private const val USER_ID = "sender_id"
    }
}