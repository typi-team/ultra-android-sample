package com.typi.ultra.sample.contacts.data

import com.typi.ultra.sample.contacts.data.model.SyncContactRequest
import com.typi.ultra.sample.contacts.data.model.SyncContactResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ContactRemoteApi {

    @POST("contacts/import")
    suspend fun sync(@Body request: SyncContactRequest): SyncContactResponse
}