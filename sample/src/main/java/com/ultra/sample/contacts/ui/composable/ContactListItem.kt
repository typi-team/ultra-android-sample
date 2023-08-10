package com.ultra.sample.contacts.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.typi.ultra.user.model.ContactModel
import com.typi.ultra.user.model.SyncedContact
import com.ultra.sample.R
import com.ultra.sample.core.ui.ImagePlaceholder
import com.ultra.sample.theme.AppTheme

@Composable
internal fun ContactListItem(
    item: SyncedContact,
    onContactClicked: (SyncedContact) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable { onContactClicked(item) }
            .padding(vertical = 6.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImagePlaceholder(
            modifier = Modifier.size(34.dp),
            initials = item.contactModel.placeholder,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
                .weight(1f)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = item.contactModel.fullName,
                style = AppTheme.typography.title,
                color = AppTheme.colors.text.title,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = item.contactModel.phone,
                style = AppTheme.typography.body,
                color = AppTheme.colors.text.subtitle,
            )
        }
        if (item.isClient) {
            Image(
                modifier = Modifier
                    .padding(start = 4.dp, end = 12.dp)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_push_notification),
                contentDescription = null,
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null,
            tint = AppTheme.colors.icon.selected
        )
    }
}

@Preview
@Composable
private fun ContactListItemPreview() {
    AppTheme {
        ContactListItem(
            item = SyncedContact(
                contactModel = ContactModel(
                    phone = "+7 701 255 7303",
                    firstName = "Аслан",
                    lastName = ""
                ),
                isClient = true
            ),
            onContactClicked = {}
        )
    }
}