package com.ultra.sample.auth.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ultra.sample.auth.presentation.composeable.LoginScreen
import com.ultra.sample.core.utils.createIntent
import com.ultra.sample.core.utils.startAndClose
import com.ultra.sample.main.createMainActivityIntent
import com.ultra.sample.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

fun Context.createLoginActivityIntent(): Intent =
    createIntent<LoginActivity>()
        .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val viewState by viewModel.viewState.collectAsState()

                LoginScreen(
                    viewState = viewState,
                    onAuthButtonClicked = {
                        viewModel.onAuthClicked(
                            onAuthSuccess = {
                                createMainActivityIntent().startAndClose(this)
                            }
                        )
                    },
                    onLoginButtonClicked = { nickname, phone, firstname, lastname ->
                        viewModel.onLoginClicked(
                            nickname = nickname,
                            phone = phone,
                            firstname = firstname,
                            lastname = lastname,
                            onLoginSuccess = {
                                createMainActivityIntent().startAndClose(this)
                            }
                        )
                    }
                )
            }
        }
    }
}