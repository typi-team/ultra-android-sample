package com.ultra.sample.contacts.data.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SyncContactsResponse(
    @SerializedName("contacts")
    val contacts: List<UserResponse>,
)