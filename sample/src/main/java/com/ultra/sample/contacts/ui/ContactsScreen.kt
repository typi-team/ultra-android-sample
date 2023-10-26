package com.ultra.sample.contacts.ui

import android.Manifest
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ultra.sample.R
import com.ultra.sample.contacts.ui.composable.ContactsUI
import com.ultra.sample.contacts.ui.model.ContactsEffect
import com.ultra.sample.theme.AppTheme
import com.ultra.sample.ultra.presentation.ChatDetailScreen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class ContactsScreen(
    private val isCreateChat: Boolean = true,
) : Screen {

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    override fun Content() {
        AppTheme {
            val navigator = LocalNavigator.currentOrThrow
            val context = LocalContext.current
            val contactsPermissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)
            if (contactsPermissionState.status.isGranted) {
                val viewModel: ContactsViewModel = koinViewModel(parameters = { parametersOf(isCreateChat) })

                LaunchedEffect(Unit) {
                    viewModel.effect
                        .onEach { effect ->
                            when (effect) {
                                is ContactsEffect.CloseScreen -> navigator.pop()
                                is ContactsEffect.CloseScreenWithMessage -> {
                                    Toast.makeText(context, effect.messageRes, Toast.LENGTH_SHORT).show()
                                    navigator.pop()
                                }
                                is ContactsEffect.ShowMessage -> {
                                    Toast.makeText(context, effect.messageRes, Toast.LENGTH_SHORT).show()
                                }
                                is ContactsEffect.ShowChatDetail -> {
                                    navigator.pop()
                                    navigator.push(ChatDetailScreen(userId = effect.userId, userName = effect.name))
                                }
                            }
                        }
                        .collect()
                }

                val state by viewModel.state.collectAsState()
                val title =
                    if (isCreateChat) stringResource(R.string.new_chat) else stringResource(R.string.choose_contact)
                ContactsUI(
                    state = state,
                    title = title,
                    onBackClicked = navigator::pop,
                    onContactClicked = viewModel::onContactPicked
                )
            } else {
                LaunchedEffect(Unit) {
                    contactsPermissionState.launchPermissionRequest()
                }
            }
        }
    }
}