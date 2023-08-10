package com.ultra.sample.contacts.data.model

import com.google.gson.annotations.SerializedName

data class SyncContact(
    @SerializedName("Phone")
    val phone: String,
    @SerializedName("Firstname")
    val firstName: String,
    @SerializedName("Lastname")
    val lastName: String?,
)