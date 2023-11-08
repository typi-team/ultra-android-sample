package com.ultra.sample.main.composable.view_pager

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.typi.ultra.integration.navigation.UltraNavigator
import com.ultra.sample.R
import com.ultra.sample.contacts.ui.ContactsScreen
import com.ultra.sample.theme.AppTheme
import com.ultra.sample.ultra.presentation.ChatDetailScreen
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalPagerApi::class)
object ViewPagerScreen : Screen {

    @StringRes
    private val titles: List<Int> = listOf(R.string.chats, R.string.notifications)

    @Composable
    override fun Content() {
        val pagerState = rememberPagerState()
        Column(modifier = Modifier.fillMaxSize()) {
            TabLayout(pagerState)
            HorizontalPager(
                state = pagerState,
                count = titles.size,
            ) { page ->
                when (titles[page]) {
                    R.string.chats -> ChatsScreen()
                    R.string.notifications -> EmptyScreen()
                }
            }
        }
    }

    @Composable
    private fun TabLayout(pagerState: PagerState) {
        val coroutineScope = rememberCoroutineScope()
        TabRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            selectedTabIndex = pagerState.currentPage,
            divider = { Spacer(modifier = Modifier.height(5.dp)) },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = 5.dp,
                    color = AppTheme.colors.primary
                )
            },
            backgroundColor = AppTheme.colors.background.surface,
            contentColor = AppTheme.colors.primary,
        ) {
            titles.forEachIndexed { index, titleRes ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(stringResource(titleRes)) }
                )
            }
        }
    }

    @Composable
    private fun ChatsScreen() {
        val navigator = LocalNavigator.currentOrThrow
        val ultraNavigator: UltraNavigator = koinInject()

        Box(modifier = Modifier.fillMaxSize()) {
            ultraNavigator.ChatsScreen(
                onChatClicked = { chatId ->
                    navigator.push(ChatDetailScreen(chatId = chatId))
                },
                onContactsClicked = {},
                isToolbarVisible = false,
            )
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                onClick = { navigator.push(ContactsScreen()) }
            ) {
                Text(
                    text = "Create new chat",
                    color = AppTheme.colors.text.button
                )
            }
        }
    }

    @Composable
    private fun EmptyScreen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background.general)
        )
    }
}