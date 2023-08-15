package com.ultra.sample.contacts.data.model

import com.google.gson.annotations.SerializedName

class CreateResponse(
    @SerializedName("contact")
    val contact: ContactResponse,
)