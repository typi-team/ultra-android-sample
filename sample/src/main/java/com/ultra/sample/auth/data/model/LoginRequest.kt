package com.ultra.sample.auth.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("firstname")
    val firstName: String,
    @SerializedName("lastname")
    val lastName: String?,
)