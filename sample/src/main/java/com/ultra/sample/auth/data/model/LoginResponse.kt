package com.ultra.sample.auth.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("sid")
    val sid: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("firstname")
    val firstName: String,
    @SerializedName("lastname")
    val lastName: String?,
)