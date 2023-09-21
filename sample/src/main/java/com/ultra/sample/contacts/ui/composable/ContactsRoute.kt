package com.ultra.sample.contacts.ui.composable

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ultra.sample.R
import com.ultra.sample.contacts.ui.ContactsEffect
import com.ultra.sample.contacts.ui.ContactsViewModel
import com.ultra.sample.theme.AppTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactsRoute(
    isCreateChat: Boolean,
    onBackClicked: () -> Unit,
    onContactClicked: () -> Unit,
) {
    AppTheme {
        val contactsPermissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)
        if (contactsPermissionState.status.isGranted) {
            val viewModel: ContactsViewModel = koinViewModel()
            viewModel.isCreateChat = isCreateChat

            LaunchedEffect(Unit) {
                viewModel.effect
                    .onEach { effect ->
                        when (effect) {
                            is ContactsEffect.CloseScreen -> onContactClicked()
                        }
                    }
                    .collect()
            }

            val state by viewModel.state.collectAsState()
            val title = if (isCreateChat) stringResource(R.string.new_chat) else stringResource(R.string.choose_contact)
            ContactsScreen(
                state = state,
                title = title,
                onBackClicked = onBackClicked,
                onContactClicked = viewModel::onContactPicked
            )
        } else {
            LaunchedEffect(Unit) {
                contactsPermissionState.launchPermissionRequest()
            }
        }
    }
}