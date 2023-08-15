package com.ultra.sample.contacts.data.model

import com.google.gson.annotations.SerializedName

data class SyncContactResponse(
    @SerializedName("contacts")
    val contacts: List<ContactRequest>,
)