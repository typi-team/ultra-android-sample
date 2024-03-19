package com.ultra.sample.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ultra.sample.R
import com.ultra.sample.core.ui.alert.ConfirmAlertDialog
import com.ultra.sample.home.HomeState
import com.ultra.sample.theme.AppTheme

@Composable
fun HomeContent(
    state: HomeState,
    onLanguageClicked: () -> Unit,
    onLogoutClicked: () -> Unit,
    onLogoutDismiss: () -> Unit,
    onLogoutConfirm: () -> Unit,
    onSendLogFileClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.home),
                        style = AppTheme.typography.heading,
                        color = AppTheme.colors.text.title,
                    )
                },
                actions = {
                    IconButton(onClick = onLogoutClicked) {
                        Icon(
                            painter = painterResource(R.drawable.ic_logout),
                            contentDescription = null,
                            tint = AppTheme.colors.button.enabled
                        )
                    }
                },
                backgroundColor = AppTheme.colors.background.general
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            TextButton(onClick = onLanguageClicked) {
                Text(text = state.language.code)
            }
            Text(
                text = state.fullName,
                color = AppTheme.colors.text.title
            )
            Text(
                text = state.phoneNumber,
                color = AppTheme.colors.text.subtitle
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onSendLogFileClicked) {
                Text(text = stringResource(id = R.string.send_log_file))
            }
        }
    }

    if (state.logoutDialogState != null) {
        ConfirmAlertDialog(
            state = state.logoutDialogState,
            onConfirmClicked = onLogoutConfirm,
            onDismissClicked = onLogoutDismiss
        )
    }
}