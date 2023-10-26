package com.ultra.sample.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface NavigationHandler {

    val shouldShowBottomBar: StateFlow<Boolean>

    fun setScreen(screen: BaseScreen)
}

internal class NavigationHandlerImpl : NavigationHandler {

    private val _shouldShowBottomBar = MutableStateFlow(true)
    override val shouldShowBottomBar: StateFlow<Boolean> = _shouldShowBottomBar.asStateFlow()

    override fun setScreen(screen: BaseScreen) {
        _shouldShowBottomBar.value = screen.shouldShowBottomBar
    }
}