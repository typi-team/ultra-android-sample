package com.ultra.sample.splash.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ultra.sample.R
import com.ultra.sample.auth.presentation.createLoginActivityIntent
import com.ultra.sample.core.utils.launchWhenStarted
import com.ultra.sample.core.utils.startAndClose
import com.ultra.sample.main.createMainActivityIntent
import com.ultra.sample.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.actions.launchWhenStarted(this) { action ->
            when (action) {
                is SplashAction.ShowAuthScreen -> createLoginActivityIntent().startAndClose(this)
                is SplashAction.ShowMainScreen -> createMainActivityIntent().startAndClose(this)
            }
        }

        setContent {
            AppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}