package com.ultra.sample.main.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ultra.sample.main.composable.tabs.TabsScreen
import com.ultra.sample.main.composable.view_pager.ViewPagerScreen

object MainScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navigator.push(ViewPagerScreen) }
            ) {
                Text(text = "View Pager Case")
            }
            Button(
                onClick = { navigator.push(TabsScreen) }
            ) {
                Text(text = "Bottom Navigation Case")
            }
        }
    }
}