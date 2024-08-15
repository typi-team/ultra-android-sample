package com.ultra.sample.reception

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ultra.sample.reception.domain.ChangeReceptionUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class ReceptionViewModel(
    private val changeReceptionUseCase: ChangeReceptionUseCase,
) : ViewModel() {

    fun onChangeClicked(
        receptionNumber: String,
        onFinished: () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val params = ChangeReceptionUseCase.Param(receptionNumber)
                changeReceptionUseCase(params)
            } catch (throwable: Throwable) {
                Timber.e(throwable, "Couldn't change reception")
            } finally {
                onFinished()
            }
        }
    }
}