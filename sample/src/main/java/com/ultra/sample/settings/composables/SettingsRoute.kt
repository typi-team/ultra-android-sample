package com.ultra.sample.settings.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ultra.sample.settings.SettingsEffect
import com.ultra.sample.settings.SettingsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsRoute(
    onLoggedOut: () -> Unit
) {
    val viewModel: SettingsViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.effect
            .onEach { effect ->
                when (effect) {
                    SettingsEffect.Logout -> onLoggedOut()
                }
            }
            .collect()
    }

    val state by viewModel.state.collectAsState()
    SettingsScreen(
        state = state,
        onLogoutClicked = viewModel::onLogoutClicked,
        onLogoutDismiss = viewModel::onLogoutDismiss,
        onLogoutConfirm = viewModel::onLogoutConfirm
    )
}