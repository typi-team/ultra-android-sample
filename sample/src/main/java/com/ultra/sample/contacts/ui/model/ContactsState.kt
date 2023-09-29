package com.ultra.sample.contacts.ui.model

sealed class ContactsState {

    object Loading : ContactsState()
    data class Display(val items: List<GroupContact>) : ContactsState()
}