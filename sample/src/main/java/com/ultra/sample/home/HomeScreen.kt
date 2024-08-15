package com.ultra.sample.home

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ultra.sample.auth.presentation.createLoginActivityIntent
import com.ultra.sample.core.ui.LocaleAwareComponentActivity
import com.ultra.sample.core.utils.startAndClose
import com.ultra.sample.home.composables.HomeContent
import com.ultra.sample.navigation.BaseScreen
import com.ultra.sample.reception.ReceptionScreen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

object HomeScreen : BaseScreen(shouldShowBottomBar = true) {

    @Composable
    override fun ScreenContent() {
        val activity = LocalContext.current as LocaleAwareComponentActivity
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: HomeViewModel = koinViewModel()

        LaunchedEffect(Unit) {
            viewModel.effect
                .onEach { effect ->
                    when (effect) {
                        is HomeEffect.ChangeLanguage -> {
                            activity.updateLocale(effect.locale)
                        }
                        HomeEffect.NavigateToReception -> {
                            navigator.push(ReceptionScreen)
                        }
                        HomeEffect.Logout -> {
                            activity.createLoginActivityIntent()
                                .startAndClose(activity)
                        }
                        is HomeEffect.SendLogFile -> {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_STREAM, effect.uri)
                                type = "*/*"
                            }
                            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            activity.startActivity(shareIntent, null)
                        }
                    }
                }
                .collect()
        }

        val state by viewModel.state.collectAsState()
        HomeContent(
            state = state,
            onLanguageClicked = viewModel::onLanguageClicked,
            onChangeReceptionClicked = viewModel::onChangeReceptionClicked,
            onLogoutClicked = viewModel::onLogoutClicked,
            onLogoutDismiss = viewModel::onLogoutDismiss,
            onLogoutConfirm = viewModel::onLogoutConfirm,
            onSendLogFileClicked = viewModel::onSendLogFileClicked,
        )
    }

}