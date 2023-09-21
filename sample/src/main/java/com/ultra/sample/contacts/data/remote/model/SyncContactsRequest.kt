package com.ultra.sample.contacts.data.remote.model

import com.google.gson.annotations.SerializedName

data class SyncContactsRequest(
    @SerializedName("contacts")
    val contacts: List<ContactInfoRequest>,
)