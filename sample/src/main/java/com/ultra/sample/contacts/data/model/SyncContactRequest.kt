package com.ultra.sample.contacts.data.model

import com.google.gson.annotations.SerializedName

data class SyncContactRequest(
    @SerializedName("contacts")
    val contacts: List<SyncContact>,
)