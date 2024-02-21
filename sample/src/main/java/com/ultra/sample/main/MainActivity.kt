package com.ultra.sample.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import com.ultra.sample.core.ui.LocaleAwareComponentActivity
import com.ultra.sample.core.utils.createIntent
import com.ultra.sample.main.composable.MainScreen
import com.ultra.sample.push.PushManager
import com.ultra.sample.theme.AppTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

fun Context.createMainActivityIntent(): Intent =
    createIntent<MainActivity>()
        .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

class MainActivity : LocaleAwareComponentActivity() {

    private val pushManager: PushManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPush()

        setContent {
            AppTheme {
                Navigator(MainScreen)
            }
        }
    }

    private fun setupPush() {
        lifecycleScope.launch {
            runCatching {
                pushManager.updateToken()
            }.onFailure {
                Timber.d(it, "Couldn't update token")
            }
        }
    }
}