package com.ultra.sample.main.presentation.composable

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ultra.sample.main.presentation.model.Tab
import com.ultra.sample.theme.AppTheme

@Composable
internal fun AppBottomBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = AppTheme.colors.background.general
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val items = listOf(
            Tab.Products,
            Tab.Payments,
            Tab.Expenses,
            Tab.Chats,
        )
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(screen.iconResId),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.titleResId),
                        style = AppTheme.typography.bottomItem
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                selectedContentColor = AppTheme.colors.icon.selected,
                unselectedContentColor = AppTheme.colors.icon.disabled,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}