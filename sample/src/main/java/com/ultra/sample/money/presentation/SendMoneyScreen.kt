package com.ultra.sample.money.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ultra.sample.money.presentation.composables.SendMoneyContent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

object SendMoneyScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: SendMoneyViewModel = koinViewModel()
        val focusRequester = remember { FocusRequester() }

        LaunchedEffect(Unit) {
            viewModel.effect
                .onEach { effect ->
                    when (effect) {
                        is SendMoneyEffect.CloseScreen -> navigator.pop()
                        is SendMoneyEffect.FocusRequest -> focusRequester.requestFocus()
                    }
                }
                .collect()
        }

        val state by viewModel.state.collectAsState()
        SendMoneyContent(
            state = state,
            focusRequester = focusRequester,
            onInputChange = viewModel::onInputChange,
            onButtonClicked = viewModel::onButtonClicked,
        )
    }
}