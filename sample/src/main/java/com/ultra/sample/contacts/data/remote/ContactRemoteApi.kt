package com.ultra.sample.contacts.data.remote

import com.ultra.sample.contacts.data.remote.model.SyncContactsRequest
import com.ultra.sample.contacts.data.remote.model.SyncContactsResponse
import com.ultra.sample.contacts.data.remote.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ContactRemoteApi {

    @POST("contacts/import")
    suspend fun sync(@Body request: SyncContactsRequest): SyncContactsResponse

    @GET("user")
    suspend fun getUserById(@Query("id") userId: String): UserResponse
}