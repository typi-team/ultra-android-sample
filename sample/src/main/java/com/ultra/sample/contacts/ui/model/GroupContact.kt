package com.ultra.sample.contacts.ui.model

import com.ultra.sample.contacts.model.Contact

data class GroupContact(
    val initial: Char,
    val contacts: List<Contact>,
)