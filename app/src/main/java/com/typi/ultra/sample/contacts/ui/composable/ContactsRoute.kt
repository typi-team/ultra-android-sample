package com.typi.ultra.sample.contacts.ui.composable

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.typi.ultra.sample.contacts.ui.ContactsViewModel
import com.typi.ultra.sample.theme.AppTheme
import com.typi.ultra.user.model.SyncedContact
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactsRoute(
    viewModel: ContactsViewModel = koinViewModel(),
    title: String,
    onBackClicked: () -> Unit,
    onContactClicked: (SyncedContact) -> Unit,
) {
    AppTheme {
        val contactsPermissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)
        if (contactsPermissionState.status.isGranted) {
            val state by viewModel.viewState
            ContactsScreen(
                state = state,
                title = title,
                onBackClicked = onBackClicked,
                onContactClicked = onContactClicked
            )
        } else {
            LaunchedEffect(Unit) {
                contactsPermissionState.launchPermissionRequest()
            }
        }
    }
}