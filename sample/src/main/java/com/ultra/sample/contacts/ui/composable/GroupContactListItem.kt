package com.ultra.sample.contacts.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ultra.sample.contacts.model.Contact
import com.ultra.sample.contacts.model.ContactInfo
import com.ultra.sample.contacts.ui.model.GroupContact
import com.ultra.sample.theme.AppTheme

@Composable
fun GroupContactListItem(
    item: GroupContact,
    onContactClicked: (Contact) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = item.initial
                .uppercaseChar()
                .toString(),
            style = AppTheme.typography.heading,
            color = AppTheme.colors.text.title,
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(16.dp),
            backgroundColor = AppTheme.colors.background.surface,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                val items = item.contacts
                items.forEachIndexed { index, item ->
                    ContactListItem(
                        item = item,
                        onContactClicked = onContactClicked
                    )
                    if (index < items.size - 1) {
                        Divider(
                            modifier = Modifier.padding(horizontal = 32.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun GroupContactListItemPreview() {
    AppTheme {
        GroupContactListItem(
            item = GroupContact(
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
            onContactClicked = {}
        )
    }
}