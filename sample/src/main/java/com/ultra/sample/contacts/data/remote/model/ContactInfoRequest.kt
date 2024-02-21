package com.ultra.sample.contacts.data.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ContactInfoRequest(
    @SerializedName("Phone")
    val phone: String,
    @SerializedName("Firstname")
    val firstName: String,
    @SerializedName("Lastname")
    val lastName: String?,
)