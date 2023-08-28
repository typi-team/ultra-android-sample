package com.ultra.sample.contacts.model

data class ContactInfo(
    val phone: String,
    val firstName: String,
    val lastName: String,
) {

    val fullName: String
        get() {
            return when {
                firstName.isEmpty() -> phone
                lastName.isNotEmpty() -> "$firstName $lastName"
                else -> firstName
            }
        }

    val placeholder: String
        get() {
            return when {
                firstName.isEmpty() -> "+"
                lastName.isNotEmpty() -> "${firstName.first()}${lastName.first()}"
                else -> firstName.first().toString()
            }
        }

    val avatarUrl: String
        get() = "http://ultra-dev.typi.team:8086/v1/profile/get-avatar?phone=$phone"
}