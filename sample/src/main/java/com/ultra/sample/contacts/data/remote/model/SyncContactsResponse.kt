package com.ultra.sample.contacts.data.remote.model

import com.google.gson.annotations.SerializedName

data class SyncContactsResponse(
    @SerializedName("contacts")
    val contacts: List<UserResponse>,
)