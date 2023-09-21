package com.ultra.sample.main.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ultra.sample.R
import com.ultra.sample.theme.AppTheme

@Composable
fun ChatsScaffold(
    onContactsClicked: (String) -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current.applicationContext

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.chats),
                        style = AppTheme.typography.heading,
                        color = AppTheme.colors.text.title,
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            val title = context.getString(R.string.new_chat)
                            onContactsClicked(title)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = null,
                            tint = AppTheme.colors.button.enabled
                        )
                    }
                },
                backgroundColor = AppTheme.colors.background.general
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            content()
        }
    }
}