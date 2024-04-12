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
import com.typi.ultra.integration.push.UltraPush
import com.typi.ultra.integration.push.UltraPushNotification
import com.typi.ultra.integration.push.UltraPushProvider
import com.ultra.sample.R
import com.ultra.sample.core.settings.SettingsManager
import com.ultra.sample.device.domain.UpdateDeviceUseCase
import com.ultra.sample.main.MainActivity
import java.security.SecureRandom
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber

internal class PushManagerImpl(
    private val context: Context,
    private val pushProvider: UltraPushProvider,
    private val settingsManager: SettingsManager,
    private val updateDeviceUseCase: UpdateDeviceUseCase,
) : PushManager, CoroutineScope {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private val random = SecureRandom()

    override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()

    override fun createNotificationChannel() {
        pushProvider.createNotificationChannel()
    }

    override fun onNewToken(token: String) {
        if (!settingsManager.isAuthorized) return

        pushProvider.onNewToken(token)
        launch {
            try {
                val params = UpdateDeviceUseCase.Param(
                    pushToken = token
                )
                updateDeviceUseCase(params)
            } catch (throwable: Throwable) {
                Timber.e(throwable, "Couldn't update device")
            }
        }
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
        val ultraPush = pushProvider.parseUltraPush(data)
        if (ultraPush.isSdkPush) {
            showUltraNotification(ultraPush)
        } else {
            showNotification(
                title = title.orEmpty(),
                description = description.orEmpty(),
                intent = Intent(context, MainActivity::class.java),
            )
        }
    }

    private fun showUltraNotification(ultraPush: UltraPush) {
        when (ultraPush) {
            is UltraPush.Message -> {
                val pushNotification = UltraPushNotification.Message(
                    ultraPush = ultraPush,
                    intent = Intent(context, MainActivity::class.java)
                        .putExtra("CHAT_ID", ultraPush.chatId),
                )
                pushProvider.showNotification(pushNotification)
            }
            is UltraPush.IncomingCall -> {
                val pushNotification = UltraPushNotification.IncomingCall(
                    ultraPush = ultraPush
                )
                pushProvider.showNotification(pushNotification)
            }
            else -> Unit
        }
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
    }
}