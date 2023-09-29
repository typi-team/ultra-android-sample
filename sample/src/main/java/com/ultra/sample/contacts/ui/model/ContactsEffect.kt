package com.ultra.sample.contacts.ui.model

import androidx.annotation.StringRes

sealed interface ContactsEffect {

    object CloseScreen : ContactsEffect

    @JvmInline
    value class CloseScreenWithMessage(@StringRes val messageRes: Int) : ContactsEffect

    @JvmInline
    value class ShowMessage(@StringRes val messageRes: Int) : ContactsEffect

    data class ShowChatDetail(
        val userId: String,
        val name: String,
    ) : ContactsEffect
}