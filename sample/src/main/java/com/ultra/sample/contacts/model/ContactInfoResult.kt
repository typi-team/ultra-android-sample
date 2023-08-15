package com.ultra.sample.contacts.model

data class ContactInfoResult(
    val userId: String,
    val phone: String,
    val firstName: String,
    val lastName: String?,
)