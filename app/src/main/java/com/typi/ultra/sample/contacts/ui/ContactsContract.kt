package com.typi.ultra.sample.contacts.ui

import com.typi.ultra.sample.core.base.ViewEvent
import com.typi.ultra.sample.core.base.ViewSideEffect
import com.typi.ultra.sample.core.base.ViewState
import com.typi.ultra.sample.contacts.ui.model.GroupContact

sealed class ContactsState : ViewState {
    object Loading : ContactsState()
    data class Display(val items: List<GroupContact>) : ContactsState()
}

sealed class ContactsEvent : ViewEvent

sealed class ContactsEffect : ViewSideEffect