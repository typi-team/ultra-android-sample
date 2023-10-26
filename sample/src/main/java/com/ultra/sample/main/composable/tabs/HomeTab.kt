package com.ultra.sample.main.composable.tabs

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.ultra.sample.R
import com.ultra.sample.auth.presentation.createLoginActivityIntent
import com.ultra.sample.core.utils.startAndClose
import com.ultra.sample.home.HomeEffect
import com.ultra.sample.home.HomeViewModel
import com.ultra.sample.home.composables.HomeScreen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

object HomeTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.home)
            val icon = rememberVectorPainter(image = Icons.Default.Home)
            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
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
        HomeScreen(
            state = state,
            onLogoutClicked = viewModel::onLogoutClicked,
            onLogoutDismiss = viewModel::onLogoutDismiss,
            onLogoutConfirm = viewModel::onLogoutConfirm
        )
    }
}