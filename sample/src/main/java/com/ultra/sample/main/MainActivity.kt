package com.ultra.sample.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.ultra.sample.core.utils.createIntent
import com.ultra.sample.main.composable.tabs.ChatsTab
import com.ultra.sample.main.composable.tabs.HomeTab
import com.ultra.sample.push.PushManager
import com.ultra.sample.theme.AppTheme
import org.koin.android.ext.android.inject
import timber.log.Timber

fun Context.createMainActivityIntent(): Intent =
    createIntent<MainActivity>()
        .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

class MainActivity : ComponentActivity() {

    private val pushManager: PushManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    private fun Content() {
        AppTheme {
            LaunchedEffect(Unit) {
                runCatching {
                    pushManager.updateToken()
                }.onFailure {
                    Timber.d(it, "Couldn't update token")
                }
            }

            TabNavigator(HomeTab) {
                Scaffold(
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = AppTheme.colors.background.general
                        ) {
                            TabNavigationItem(HomeTab)
                            TabNavigationItem(ChatsTab)
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        CurrentTab()
                    }
                }
            }
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        BottomNavigationItem(
            selected = tabNavigator.current.key == tab.key,
            selectedContentColor = AppTheme.colors.icon.selected,
            unselectedContentColor = AppTheme.colors.icon.disabled,
            onClick = { tabNavigator.current = tab },
            icon = {
                Icon(
                    painter = tab.options.icon!!,
                    contentDescription = tab.options.title
                )
            },
            label = {
                Text(
                    text = tab.options.title,
                    style = AppTheme.typography.bottomItem
                )
            },
        )
    }
}