package com.ultra.sample.ultra.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.typi.ultra.integration.navigation.UltraNavigator
import com.ultra.sample.R
import com.ultra.sample.contacts.ui.ContactsScreen
import com.ultra.sample.navigation.BaseScreen
import com.ultra.sample.theme.AppTheme
import org.koin.compose.koinInject

object ChatsScreen : BaseScreen(shouldShowBottomBar = true) {

    @Composable
    override fun ScreenContent() {
        val navigator = LocalNavigator.currentOrThrow
        val ultraNavigator: UltraNavigator = koinInject()

        ultraNavigator.ChatsScreen(
            onChatClicked = { chatId ->
                navigator.push(ChatDetailScreen(chatId = chatId))
            },
            onContactsClicked = {
                navigator.push(ContactsScreen())
            },
            isToolbarVisible = true,
            emptyContent = {
                EmptyState(
                    navigator = navigator
                )
            }
        )
    }

    @Composable
    private fun EmptyState(
        navigator: Navigator
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true)
                    .padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(R.drawable.img_chats_empty),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.chats_empty_title),
                    modifier = Modifier.padding(top = 8.dp),
                    color = AppTheme.colors.text.title,
                    style = AppTheme.typography.title,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.chats_empty_subtitle),
                    modifier = Modifier.padding(top = 8.dp),
                    color = AppTheme.colors.text.subtitle,
                    style = AppTheme.typography.body,
                    textAlign = TextAlign.Center
                )
            }
            Button(
                onClick = { navigator.push(ContactsScreen()) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colors.button.enabled,
                    disabledContainerColor = AppTheme.colors.button.disabled,
                )
            ) {
                Text(
                    text = stringResource(R.string.start),
                    color = AppTheme.colors.text.button
                )
            }
        }
    }
}