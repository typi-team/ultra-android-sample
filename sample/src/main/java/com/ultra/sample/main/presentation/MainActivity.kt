package com.ultra.sample.main.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.typi.ultra.call.model.CallModel
import com.typi.ultra.call.presentation.ongoing.CallActivity.Companion.createCallActivityIntent
import com.typi.ultra.call.presentation.ongoing.model.CallInputParams
import com.typi.ultra.integration.navigation.UltraNavigator
import com.ultra.sample.auth.presentation.createLoginActivityIntent
import com.ultra.sample.contacts.ui.composable.ContactsRoute
import com.ultra.sample.core.utils.createIntent
import com.ultra.sample.core.utils.startAndClose
import com.ultra.sample.main.presentation.composable.AppBottomBar
import com.ultra.sample.main.presentation.composable.TabScreen
import com.ultra.sample.main.presentation.model.MainEffect
import com.ultra.sample.main.presentation.model.MainState
import com.ultra.sample.main.presentation.model.Tab
import com.ultra.sample.money.presentation.composables.SendMoneyRoute
import com.ultra.sample.settings.composables.SettingsRoute
import com.ultra.sample.theme.AppTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

fun Context.createMainActivityIntent(): Intent =
    createIntent<MainActivity>()
        .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val ultraNavigator: UltraNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainRoute(viewModel)
        }
    }

    @Composable
    internal fun MainRoute(
        viewModel: MainViewModel,
    ) {
        AppTheme {
            val navController = rememberNavController()

            LaunchedEffect(Unit) {
                viewModel.effect
                    .onEach { effect ->
                        when (effect) {
                            is MainEffect.ShowScreen -> navController.navigate(effect.route)
                            is MainEffect.ShowScreenWithPopup -> {
                                navController.navigateWithPopUp(effect.toRoute, effect.fromRoute)
                            }
                            MainEffect.Logout -> createLoginActivityIntent().startAndClose(this@MainActivity)
                            is MainEffect.StartCallActivity -> {
                                val params = CallInputParams.ConnectToRoom(effect.callModel)
                                startActivity(createCallActivityIntent(params))
                            }
                        }
                    }
                    .collect()
            }

            val state by viewModel.state.collectAsState()
            MainScreen(
                navController = navController,
                state = state,
                onChangeBottomBarVisibility = viewModel::onBottomBarStateChanged,
                onChatClicked = viewModel::onChatClicked,
                onContactsClicked = viewModel::onContactsClicked,
                onSendContactClicked = viewModel::onSendContactClicked,
                onSendMoneyClicked = viewModel::onSendMoneyClicked,
                onUserDetailClicked = viewModel::onUserDetailClicked,
                onVideoPlayerClicked = viewModel::onVideoPlayerClicked,
                onLoggedOut = viewModel::onLoggedOut,
                onCallClicked = viewModel::onCallClicked,
                onChatDetailShowed = viewModel::onChatDetailShowed,
            )
        }
    }

    @Composable
    private fun MainScreen(
        navController: NavHostController,
        state: MainState,
        onChangeBottomBarVisibility: (Boolean) -> Unit,
        onChatClicked: (String) -> Unit,
        onContactsClicked: () -> Unit,
        onSendContactClicked: () -> Unit,
        onSendMoneyClicked: () -> Unit,
        onUserDetailClicked: (String) -> Unit,
        onVideoPlayerClicked: (String) -> Unit,
        onLoggedOut: () -> Unit,
        onCallClicked: (CallModel) -> Unit,
        onChatDetailShowed: (String, String) -> Unit,
    ) {
        Scaffold(
            bottomBar = {
                if (state.isBottomBarVisible) {
                    AppBottomBar(navController)
                }
            }
        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = Tab.Products.route
            ) {
                composable(route = Tab.Products.route) {
                    onChangeBottomBarVisibility(true)
                    TabScreen(tab = Tab.Products)
                }
                composable(route = Tab.Payments.route) {
                    onChangeBottomBarVisibility(true)
                    TabScreen(tab = Tab.Payments)
                }
                composable(route = Tab.Expenses.route) {
                    onChangeBottomBarVisibility(true)
                    TabScreen(tab = Tab.Expenses)
                }
                composable(route = Tab.Settings.route) {
                    onChangeBottomBarVisibility(true)
                    SettingsRoute(
                        onLoggedOut = onLoggedOut
                    )
                }
                composable(route = Tab.Chats.route) {
                    onChangeBottomBarVisibility(true)
                    ultraNavigator.ChatsScreen(
                        onChatClicked = onChatClicked,
                        onContactsClicked = onContactsClicked,
                        isToolbarVisible = true,
                    )
//                    ChatsScaffold(onContactsClicked = onContactsClicked) {
//                        ultraNavigator.ChatsScreen(
//                            onChatClicked = onChatClicked,
//                            onContactsClicked = {},
//                            isToolbarVisible = false,
//                        )
//                    }
                }
                composable(
                    route = "chat_detail?chat_id={chat_id}&user_id={user_id}&name={name}",
                    arguments = listOf(
                        navArgument("chat_id") {
                            nullable = true
                            type = NavType.StringType
                            defaultValue = null
                        },
                        navArgument("user_id") {
                            nullable = true
                            type = NavType.StringType
                            defaultValue = null
                        },
                        navArgument("name") {
                            nullable = true
                            type = NavType.StringType
                            defaultValue = null
                        }
                    )
                ) { backStackEntry ->
                    onChangeBottomBarVisibility(false)
                    val chatId = backStackEntry.arguments?.getString("chat_id")
                    val userId = backStackEntry.arguments?.getString("user_id")
                    val name = backStackEntry.arguments?.getString("name")
                    ultraNavigator.ChatDetailScreen(
                        chatId = chatId,
                        userId = userId,
                        name = name,
                        onBackClicked = navController::navigateUp,
                        onSendContactClicked = onSendContactClicked,
                        onSendMoneyClicked = onSendMoneyClicked,
                        onUserDetailClicked = onUserDetailClicked,
                        onVideoPlayerClicked = onVideoPlayerClicked,
                        onCallClicked = onCallClicked,
                    )
                }
                composable(
                    route = "contacts/{is_create_chat}",
                    arguments = listOf(navArgument("is_create_chat") { type = NavType.BoolType })
                ) { backStackEntry ->
                    onChangeBottomBarVisibility(false)
                    val isCreateChat = backStackEntry.arguments?.getBoolean("is_create_chat") ?: false
                    ContactsRoute(
                        isCreateChat = isCreateChat,
                        onBackClicked = navController::navigateUp,
                        onChatDetailShowed = onChatDetailShowed,
                    )
                }
                composable(route = "send_money") {
                    onChangeBottomBarVisibility(false)
                    SendMoneyRoute(
                        onSendClicked = navController::navigateUp
                    )
                }
                composable(route = "user_detail/{phone_number}") { backStackEntry ->
                    onChangeBottomBarVisibility(false)
                    val phoneNumber = checkNotNull(
                        backStackEntry.arguments?.getString("phone_number")
                    ) { "Phone number required" }
                    ultraNavigator.UserDetailScreen(
                        phoneNumber = phoneNumber,
                        onBackClicked = navController::navigateUp
                    )
                }
                composable(route = "video_player/{message_id}") { backStackEntry ->
                    onChangeBottomBarVisibility(false)
                    val messageId = checkNotNull(
                        backStackEntry.arguments?.getString("message_id")
                    ) { "Message id required" }
                    ultraNavigator.VideoPlayerScreen(messageId = messageId)
                }
            }
        }
    }

    private fun NavHostController.navigateWithPopUp(
        toRoute: String,
        fromRoute: String,
    ) {
        this.navigate(toRoute) {
            popUpTo(fromRoute) {
                inclusive = false
            }
        }
    }
}