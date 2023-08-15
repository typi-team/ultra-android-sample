package com.ultra.sample.contacts.ui.model

import com.ultra.sample.contacts.model.ContactDetail

data class GroupContact(
    val initial: Char,
    val contacts: List<ContactDetail>,
)