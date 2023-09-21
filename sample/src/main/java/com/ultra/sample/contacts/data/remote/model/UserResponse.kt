package com.ultra.sample.contacts.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("ID")
    val id: String?,
    @SerializedName("UserID")
    val userId: String,
    @SerializedName("Nickname")
    val nickname: String,
    @SerializedName("Phone")
    val phone: String,
    @SerializedName("Firstname")
    val firstName: String,
    @SerializedName("Lastname")
    val lastName: String?,
)
