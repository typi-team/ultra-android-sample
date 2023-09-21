package com.ultra.sample.contacts.data.remote.model

import com.google.gson.annotations.SerializedName

data class CreateContactRequest(
    @SerializedName("mine")
    val mine: ContactInfoRequest,
    @SerializedName("contact")
    val contact: ContactInfoRequest,
)