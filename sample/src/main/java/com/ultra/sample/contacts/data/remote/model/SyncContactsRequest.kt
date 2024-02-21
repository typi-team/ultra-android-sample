package com.ultra.sample.contacts.data.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SyncContactsRequest(
    @SerializedName("contacts")
    val contacts: List<ContactInfoRequest>,
)