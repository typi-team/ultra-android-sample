package com.typi.ultra.sample.contacts.data.model

import com.google.gson.annotations.SerializedName
import com.typi.ultra.sample.contacts.data.model.SyncContact

data class SyncContactRequest(
    @SerializedName("contacts")
    val contacts: List<SyncContact>,
)