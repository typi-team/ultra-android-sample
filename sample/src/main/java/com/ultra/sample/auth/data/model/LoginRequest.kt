package com.ultra.sample.auth.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginRequest(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("firstname")
    val firstName: String,
    @SerializedName("lastname")
    val lastName: String?,
    @SerializedName("reception")
    val reception: Int?,
    @SerializedName("reception_service")
    val receptionService: Int?,
    @SerializedName("device_id")
    val deviceId: String,
)