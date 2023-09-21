package com.ultra.sample.core.ui.alert

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ultra.sample.theme.AppTheme

@Composable
fun ConfirmAlertDialog(
    state: ConfirmAlertDialogState,
    onConfirmClicked: () -> Unit,
    onDismissClicked: () -> Unit,
) {
    val context = LocalContext.current.applicationContext

    AlertDialog(
        title = {
            Text(
                text = context.getString(state.title),
                color = AppTheme.colors.text.title
            )
        },
        text = {
            Column {
                Text(
                    text = context.getString(state.description),
                    color = AppTheme.colors.text.content
                )
            }
        },
        onDismissRequest = onDismissClicked,
        backgroundColor = AppTheme.colors.background.surface,
        confirmButton = {
            TextButton(onClick = onConfirmClicked) {
                Text(
                    text = context.getString(state.confirmButtonText),
                    color = AppTheme.colors.button.enabled
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissClicked) {
                Text(
                    text = context.getString(state.dismissButtonText),
                    color = AppTheme.colors.button.enabled
                )
            }
        }
    )
}