package com.ultra.sample.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject

abstract class BaseScreen(
    val shouldShowBottomBar: Boolean = false,
) : Screen {

    @Composable
    abstract fun ScreenContent()

    @Composable
    override fun Content() {
        val navigationHandler: NavigationHandler = koinInject()
        navigationHandler.setScreen(this)
        ScreenContent()
    }
}