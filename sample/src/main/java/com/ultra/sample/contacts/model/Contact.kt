package com.ultra.sample.contacts.model

sealed class Contact(
    open val contactInfo: ContactInfo,
) {

    data class NotClient(override val contactInfo: ContactInfo) : Contact(contactInfo)

    data class BankClient(
        override val contactInfo: ContactInfo,
        val name: String,
        val userId: String,
    ) : Contact(contactInfo)
}