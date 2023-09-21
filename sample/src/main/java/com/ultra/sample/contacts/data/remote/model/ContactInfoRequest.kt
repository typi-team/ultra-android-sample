package com.ultra.sample.contacts.data.remote.model

import com.google.gson.annotations.SerializedName

data class ContactInfoRequest(
    @SerializedName("Phone")
    val phone: String,
    @SerializedName("Firstname")
    val firstName: String,
    @SerializedName("Lastname")
    val lastName: String?,
)