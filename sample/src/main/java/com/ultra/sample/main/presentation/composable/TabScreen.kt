package com.ultra.sample.main.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ultra.sample.main.presentation.model.Tab
import com.ultra.sample.theme.AppTheme

@Composable
internal fun TabScreen(
    tab: Tab,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(tab.titleResId),
                        style = AppTheme.typography.heading,
                        color = AppTheme.colors.text.title,
                    )
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
                text = stringResource(tab.titleResId),
                color = AppTheme.colors.text.title
            )
        }
    }
}