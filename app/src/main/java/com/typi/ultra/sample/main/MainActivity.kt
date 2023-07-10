package com.typi.ultra.sample.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.typi.ultra.UltraScreenStarter
import com.typi.ultra.UltraService
import com.typi.ultra.sample.R
import com.typi.ultra.sample.core.utils.createIntent
import com.typi.ultra.sample.core.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

fun Context.createMainActivityIntent(): Intent =
    createIntent<MainActivity>()
        .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_root) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view).setupWithNavController(navController)

        if (savedInstanceState == null) {
            handleDeeplink(intent)
            handlePushNotification(intent)
        }

        viewModel.actions
            .onEach { action ->
                when (action) {
                    is MainAction.StartServices -> {
                        startService(Intent(this, UltraService::class.java))
                    }
                }
            }
            .launchWhenStarted(this)

        viewModel.onCreate()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleDeeplink(intent)
        handlePushNotification(intent)
    }

    private fun handlePushNotification(intent: Intent?) {
        val pushType = intent?.getStringExtra(PUSH_TYPE) ?: return
        val userId = intent.getStringExtra(USER_ID).orEmpty()

        when (pushType) {
            MESSAGE_TYPE -> UltraScreenStarter.startChatDetailActivity(
                context = this,
                userId = userId
            )
            else -> Timber.d("Handled not supported type of push notification: $pushType")
        }
    }

    private fun handleDeeplink(intent: Intent?) {
        if (intent?.action != Intent.ACTION_VIEW) return
        if (intent.flags and Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY != 0) return

        val uri = intent.dataString?.toUri()
        if (uri != null) {
            val pathSegments = uri.path?.toUri()?.pathSegments ?: emptyList()

            val segment = pathSegments.firstOrNull()
            if (segment == "chat") {
                val userId = uri.getQueryParameter("id")
                if (userId != null) {
                    UltraScreenStarter.startChatDetailActivity(context = this, userId = userId)
                }
            }
        }
    }

    companion object {

        private const val PUSH_TYPE = "push_type"
        private const val USER_ID = "sender_id"
        private const val MESSAGE_TYPE = "message"
    }
}