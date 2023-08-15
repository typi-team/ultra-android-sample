package com.ultra.sample.contacts.data

import com.ultra.sample.contacts.data.model.CreateRequest
import com.ultra.sample.contacts.data.model.CreateResponse
import com.ultra.sample.contacts.data.model.SyncContactRequest
import com.ultra.sample.contacts.data.model.SyncContactResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ContactRemoteApi {

    @POST("contacts/import")
    suspend fun sync(@Body request: SyncContactRequest): SyncContactResponse

    @POST("createChat")
    suspend fun create(@Body request: CreateRequest): CreateResponse
}