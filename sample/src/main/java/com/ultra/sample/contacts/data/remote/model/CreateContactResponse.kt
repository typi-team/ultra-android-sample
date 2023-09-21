package com.ultra.sample.contacts.data.remote.model

import com.google.gson.annotations.SerializedName

data class CreateContactResponse(
    @SerializedName("mine")
    val mine: CreateContactResponseItem,
    @SerializedName("contact")
    val contact: CreateContactResponseItem,
)

data class CreateContactResponseItem(
    @SerializedName("UserId")
    val chatUserId: String,
    @SerializedName("Phone")
    val userId: String,
    @SerializedName("Firstname")
    val nickname: String,
)