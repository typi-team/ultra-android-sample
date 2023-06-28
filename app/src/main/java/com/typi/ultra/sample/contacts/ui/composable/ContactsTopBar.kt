package com.typi.ultra.sample.contacts.ui.composable

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.typi.ultra.R
import com.typi.ultra.sample.theme.AppTheme

@Composable
fun ContactsTopBar(
    title: String,
    onBackClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = AppTheme.colors.heading,
                style = AppTheme.typography.heading
            )
        },
        actions = {
            IconButton(
                onClick = { onBackClicked() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ultra_ic_close_accent),
                    contentDescription = null,
                    tint = AppTheme.colors.accent
                )
            }
        },
        backgroundColor = AppTheme.colors.primary
    )
}

@Preview
@Composable
private fun ContactsTopBarPreview() {
    ContactsTopBar(
        title = "",
        onBackClicked = {}
    )
}