package com.ultra.sample.reception

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ultra.sample.R
import com.ultra.sample.auth.presentation.LoginActivity
import com.ultra.sample.navigation.BaseScreen
import com.ultra.sample.reception.composables.ReceptionContent
import com.ultra.sample.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

object ReceptionScreen : BaseScreen() {

    @Composable
    override fun ScreenContent() {
        val context = LocalContext.current
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: ReceptionViewModel = koinViewModel()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.change_reception))
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { navigator.pop() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    },
                    backgroundColor = AppTheme.colors.background.general,
                )
            },
            backgroundColor = AppTheme.colors.background.general,
        ) { paddingValues ->
            ReceptionContent(
                onChangeClicked = {
                    viewModel.onChangeClicked(it) {
                        onReceptionChanged(context)
                    }
                },
                modifier = Modifier.padding(paddingValues),
            )
        }
    }

    private fun onReceptionChanged(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
            .apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
        context.startActivity(intent)
    }
}