package com.ultra.sample.contacts.data.model

import com.google.gson.annotations.SerializedName

class CreateRequest(
    @SerializedName("mine")
    val currentContact: Contact,
    @SerializedName("contact")
    val contactWillCreate: Contact,
) {

    data class Contact(
        @SerializedName("phone")
        val phone: String,
        @SerializedName("firstname")
        val firstName: String,
        @SerializedName("lastname")
        val lastName: String?,
    )
}