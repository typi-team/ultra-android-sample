package com.ultra.sample.contacts.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ultra.sample.contacts.model.Contact
import com.ultra.sample.contacts.model.ContactInfo
import com.ultra.sample.contacts.ui.model.ContactsState
import com.ultra.sample.contacts.ui.model.GroupContact
import com.ultra.sample.core.ui.LoadingView
import com.ultra.sample.theme.AppTheme

@Composable
internal fun ContactsUI(
    state: ContactsState,
    title: String,
    onBackClicked: () -> Unit,
    onContactClicked: (Contact) -> Unit,
) {
    Scaffold(
        topBar = {
            ContactsTopBar(
                title = title,
                onBackClicked = onBackClicked
            )
        },
        content = {
            when (state) {
                is ContactsState.Loading -> LoadingView()
                is ContactsState.Display -> ContactsContent(
                    modifier = Modifier.padding(it),
                    items = state.items,
                    onContactClicked = onContactClicked
                )
            }
        }
    )
}

@Composable
internal fun ContactsContent(
    modifier: Modifier,
    items: List<GroupContact>,
    onContactClicked: (Contact) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background.general),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        items(items) { item ->
            GroupContactListItem(
                item = item,
                onContactClicked = onContactClicked
            )
        }
    }
}

@Preview
@Composable
private fun ContactsScreenPreview() {
    AppTheme {
        ContactsUI(
            state = ContactsState.Display(
                items = listOf(
                    GroupContact(
                        initial = 'A',
                        contacts = listOf(
                            Contact.BankClient(
                                contactInfo = ContactInfo(
                                    phone = "+7 701 255 7303",
                                    firstName = "Аслан",
                                    lastName = ""
                                ),
                                userId = "",
                                name = "",
                            ),
                            Contact.NotClient(
                                contactInfo = ContactInfo(
                                    phone = "+7 701 255 7303",
                                    firstName = "Ануар",
                                    lastName = "Аманбеков"
                                ),
                            ),
                        )
                    ),
                    GroupContact(
                        initial = 'Б',
                        contacts = listOf(
                            Contact.NotClient(
                                contactInfo = ContactInfo(
                                    phone = "+7 701 255 7303",
                                    firstName = "Бека",
                                    lastName = "Коллега"
                                ),
                            ),
                        )
                    ),
                )
            ),
            title = "Новый чат",
            onBackClicked = { },
            onContactClicked = { }
        )
    }
}