package com.ultra.sample.contacts.data.remote

import com.ultra.sample.contacts.data.remote.model.ContactInfoRequest
import com.ultra.sample.contacts.data.remote.model.SyncContactsRequest
import com.ultra.sample.contacts.data.remote.model.UserResponse
import com.ultra.sample.contacts.model.ContactInfo
import com.ultra.sample.contacts.model.User

interface ContactRemoteDataSource {

    suspend fun sync(contacts: List<ContactInfo>): List<User>

    suspend fun getUserById(id: String): User
}

class ContactRemoteDataSourceImpl(
    private val remoteApi: ContactRemoteApi,
) : ContactRemoteDataSource {

    override suspend fun sync(contacts: List<ContactInfo>): List<User> {
        val request = SyncContactsRequest(contacts = contacts.map { it.toRequest() })
        return remoteApi.sync(request)
            .let { response ->
                response.contacts.map { it.toUser() }
            }
    }

    override suspend fun getUserById(id: String): User {
        return remoteApi.getUserById(id).toUser()
    }

    private fun ContactInfo.toRequest(): ContactInfoRequest = ContactInfoRequest(
        phone = phone,
        firstName = firstName,
        lastName = lastName
    )

    private fun UserResponse.toUser(): User = User(
        userId = userId,
        nickname = nickname,
        phone = phone,
        firstname = firstName,
        lastname = lastName
    )
}