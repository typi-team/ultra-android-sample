package com.ultra.sample.ultra.presentation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.typi.ultra.integration.navigation.UltraNavigator
import org.koin.compose.koinInject

class UserDetailScreen(
    private val phoneNumber: String,
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val ultraNavigator: UltraNavigator = koinInject()

        ultraNavigator.UserDetailScreen(
            phoneNumber = phoneNumber,
            onBackClicked = navigator::pop,
        )
    }
}