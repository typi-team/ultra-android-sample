package com.ultra.sample.ultra.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.typi.ultra.integration.navigation.UltraNavigator
import com.ultra.sample.navigation.BaseScreen
import org.koin.compose.koinInject

class UserDetailScreen(
    private val phoneNumber: String,
) : BaseScreen() {

    @Composable
    override fun ScreenContent() {
        val navigator = LocalNavigator.currentOrThrow
        val ultraNavigator: UltraNavigator = koinInject()

        val phoneNumberValue by remember { mutableStateOf(phoneNumber) }
        val onBackClicked: () -> Unit = remember { { navigator.pop() } }

        ultraNavigator.UserDetailScreen(
            phoneNumber = phoneNumberValue,
            onBackClicked = onBackClicked,
        )
    }
}