package com.ultra.sample.money.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ultra.sample.R
import com.ultra.sample.navigation.BaseScreen
import com.ultra.sample.theme.AppTheme

class MoneyInfoScreen(
    private val transactionId: String,
) : BaseScreen() {

    @Composable
    override fun ScreenContent() {
        val navigator = LocalNavigator.currentOrThrow

        MoneyInfoContent(
            transactionId = transactionId,
            onBackClicked = navigator::pop,
        )
    }
}

@Composable
fun MoneyInfoContent(
    transactionId: String,
    onBackClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Информация о переводе",
                        color = AppTheme.colors.text.title,
                        style = AppTheme.typography.heading
                    )
                },
                actions = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            painter = painterResource(R.drawable.ic_close_accent),
                            contentDescription = null,
                            tint = AppTheme.colors.icon.selected
                        )
                    }
                },
                backgroundColor = AppTheme.colors.background.general
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(AppTheme.colors.background.general),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = transactionId,
                color = AppTheme.colors.text.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}