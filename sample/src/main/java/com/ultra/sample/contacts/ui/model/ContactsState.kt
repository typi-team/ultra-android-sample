package com.ultra.sample.contacts.ui.model

sealed class ContactsState {

    data object Loading : ContactsState()
    data class Display(val items: List<GroupContact>) : ContactsState()
}