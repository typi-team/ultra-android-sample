package com.ultra.sample.settings.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ultra.sample.R
import com.ultra.sample.core.ui.alert.ConfirmAlertDialog
import com.ultra.sample.settings.SettingsState
import com.ultra.sample.theme.AppTheme

@Composable
fun SettingsScreen(
    state: SettingsState,
    onLogoutClicked: () -> Unit,
    onLogoutDismiss: () -> Unit,
    onLogoutConfirm: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.settings),
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.settings),
                color = AppTheme.colors.text.title
            )
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