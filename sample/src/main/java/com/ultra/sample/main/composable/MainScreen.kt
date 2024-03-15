package com.ultra.sample.main.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.typi.ultra.integration.message.UltraMessageProvider
import com.ultra.sample.main.composable.tabs.ChatsTab
import com.ultra.sample.main.composable.tabs.HomeTab
import com.ultra.sample.navigation.NavigationHandler
import com.ultra.sample.theme.AppTheme
import org.koin.compose.koinInject

object MainScreen : Screen {

    @Composable
    override fun Content() {
        val navigationHandler: NavigationHandler = koinInject()
        val messageProvider: UltraMessageProvider = koinInject()

        val shouldShowBottomBar by navigationHandler.shouldShowBottomBar.collectAsState()
        val unreadCount by messageProvider.unreadMessagesCountFlow.collectAsState(0)

        TabNavigator(HomeTab) {
            Scaffold(
                bottomBar = {
                    if (shouldShowBottomBar) {
                        BottomNavigation(
                            backgroundColor = AppTheme.colors.background.general
                        ) {
                            TabNavigationItem(HomeTab)
                            TabNavigationItem(ChatsTab, unreadCount)
                        }
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

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab, unreadCount: Int = 0) {
        val tabNavigator = LocalTabNavigator.current

        BottomNavigationItem(
            selected = tabNavigator.current.key == tab.key,
            selectedContentColor = AppTheme.colors.icon.selected,
            unselectedContentColor = AppTheme.colors.icon.disabled,
            onClick = { tabNavigator.current = tab },
            icon = { TabIcon(tab = tab, unreadCount = unreadCount) },
            label = {
                Text(
                    text = tab.options.title,
                    style = AppTheme.typography.bottomItem
                )
            },
        )
    }

    @Composable
    private fun TabIcon(tab: Tab, unreadCount: Int) {
        BadgedBox(
            badge = {
                if (unreadCount > 0) {
                    Badge(
                        backgroundColor = Color.Red,
                        contentColor = AppTheme.colors.text.button,
                    ) {
                        Text(text = unreadCount.toString())
                    }
                }
            }
        ) {
            Icon(
                painter = tab.options.icon!!,
                contentDescription = tab.options.title
            )
        }
    }
}