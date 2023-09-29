package com.ultra.sample.money.presentation

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typi.ultra.transaction.model.Money
import com.ultra.sample.money.data.MoneyRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SendMoneyViewModel(
    private val moneyRepository: MoneyRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<SendMoneyState> = MutableStateFlow(SendMoneyState())
    val state: StateFlow<SendMoneyState> = _state.asStateFlow()

    private val _effect: Channel<SendMoneyEffect> = Channel(Channel.BUFFERED)
    val effect: Flow<SendMoneyEffect> = _effect.receiveAsFlow()

    init {
        _effect.trySend(SendMoneyEffect.FocusRequest)
    }

    fun onInputChange(input: String) {
        if (input.isDigitsOnly() && input.length < 13) {
            setState { copy(inputAmount = input) }
        }
    }

    fun onButtonClicked() {
        viewModelScope.launch {
            runCatching {
                setState { copy(isLoading = true) }
                val amount = state.value.inputAmount.toLong()
                val money = Money(units = amount)
                moneyRepository.sendMoney(money)
                _effect.send(SendMoneyEffect.CloseScreen)
            }
        }
    }

    private fun setState(reducer: SendMoneyState.() -> SendMoneyState) {
        val newState = state.value.reducer()
        _state.value = newState
    }
}

data class SendMoneyState(
    val inputAmount: String = "",
    val isLoading: Boolean = false,
) {

    val isButtonEnabled: Boolean
        get() = inputAmount.isNotEmpty() && !isLoading
}

sealed class SendMoneyEffect {
    object CloseScreen : SendMoneyEffect()
    object FocusRequest : SendMoneyEffect()
}