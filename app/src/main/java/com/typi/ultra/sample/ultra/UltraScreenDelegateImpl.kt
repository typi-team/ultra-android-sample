package com.typi.ultra.sample.ultra

import androidx.compose.runtime.Composable
import com.typi.ultra.UltraScreenDelegate
import com.typi.ultra.sample.contacts.ui.composable.ContactsRoute
import com.typi.ultra.sample.money.SendMoneyBottomSheetDialog
import com.typi.ultra.sample.theme.AppTheme
import com.typi.ultra.transaction.model.Transaction
import com.typi.ultra.user.model.SyncedContact

class UltraScreenDelegateImpl : UltraScreenDelegate {

    @Composable
    override fun SendMoneyDialog(
        onMoneySent: (Transaction) -> Unit,
    ) {
        AppTheme {
            SendMoneyBottomSheetDialog(
                onMoneySent = onMoneySent
            )
        }
    }

    @Composable
    override fun ContactsScreen(
        title: String,
        onBackClicked: () -> Unit,
        onContactClicked: (SyncedContact) -> Unit,
    ) {
        ContactsRoute(
            title = title,
            onBackClicked = onBackClicked,
            onContactClicked = onContactClicked
        )
    }
}