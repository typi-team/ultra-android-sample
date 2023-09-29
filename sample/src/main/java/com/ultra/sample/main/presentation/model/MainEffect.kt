package com.ultra.sample.main.presentation.model

import com.typi.ultra.call.model.CallModel

sealed class MainEffect {

    data class ShowScreen(val route: String) : MainEffect()
    data class ShowScreenWithPopup(
        val toRoute: String,
        val fromRoute: String,
    ) : MainEffect()

    object Logout : MainEffect()
    data class StartCallActivity(val callModel: CallModel) : MainEffect()
}