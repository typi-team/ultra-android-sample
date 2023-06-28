package com.typi.ultra.sample.contacts.ui.model

import com.typi.ultra.user.model.SyncedContact

data class GroupContact(
    val initial: Char,
    val contacts: List<SyncedContact>,
)