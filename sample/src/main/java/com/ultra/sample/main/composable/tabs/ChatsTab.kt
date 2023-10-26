package com.ultra.sample.main.composable.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.ultra.sample.R
import com.ultra.sample.ultra.presentation.ChatsScreen

object ChatsTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.chats)
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(R.drawable.ic_tab_chats))
            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(ChatsScreen)
    }
}