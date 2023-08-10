package com.ultra.sample.money.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import com.ultra.sample.money.SendMoneyEffect
import com.ultra.sample.money.SendMoneyViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

@Composable
fun SendMoneyRoute(
    onSendClicked: () -> Unit,
) {
    val viewModel: SendMoneyViewModel = koinViewModel()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        viewModel.effect
            .onEach { effect ->
                when (effect) {
                    is SendMoneyEffect.CloseScreen -> onSendClicked()
                    is SendMoneyEffect.FocusRequest -> focusRequester.requestFocus()
                }
            }
            .collect()
    }

    val state by viewModel.state.collectAsState()
    SendMoneyScreen(
        state = state,
        focusRequester = focusRequester,
        onInputChange = viewModel::onInputChange,
        onButtonClicked = viewModel::onButtonClicked,
    )
}