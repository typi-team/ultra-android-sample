package com.ultra.sample.home

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.ultra.sample.auth.presentation.createLoginActivityIntent
import com.ultra.sample.core.utils.startAndClose
import com.ultra.sample.home.composables.HomeContent
import com.ultra.sample.navigation.BaseScreen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

object HomeScreen : BaseScreen(shouldShowBottomBar = true) {

    @Composable
    override fun ScreenContent() {
        val activity = LocalContext.current as Activity
        val viewModel: HomeViewModel = koinViewModel()

        LaunchedEffect(Unit) {
            viewModel.effect
                .onEach { effect ->
                    when (effect) {
                        HomeEffect.Logout -> {
                            activity.createLoginActivityIntent()
                                .startAndClose(activity)
                        }
                    }
                }
                .collect()
        }

        val state by viewModel.state.collectAsState()
        HomeContent(
            state = state,
            onLogoutClicked = viewModel::onLogoutClicked,
            onLogoutDismiss = viewModel::onLogoutDismiss,
            onLogoutConfirm = viewModel::onLogoutConfirm
        )
    }

}