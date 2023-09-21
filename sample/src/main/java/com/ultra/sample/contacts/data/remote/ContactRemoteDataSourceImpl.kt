package com.ultra.sample.contacts.data.remote

import com.typi.ultra.user.model.UltraContact
import com.ultra.sample.contacts.data.remote.model.ContactInfoRequest
import com.ultra.sample.contacts.data.remote.model.CreateContactRequest
import com.ultra.sample.contacts.data.remote.model.CreateContactResponseItem
import com.ultra.sample.contacts.data.remote.model.SyncContactsRequest
import com.ultra.sample.contacts.data.remote.model.UserResponse
import com.ultra.sample.contacts.model.ContactInfo
import com.ultra.sample.contacts.model.User

class ContactRemoteDataSourceImpl(
    private val remoteApi: ContactRemoteApi
) : ContactRemoteDataSource {

    override suspend fun sync(contacts: List<ContactInfo>): List<User> {
        val request = SyncContactsRequest(contacts = contacts.map { it.toRequest() })
        return remoteApi.sync(request)
            .let { response ->
                response.contacts.map { it.toUser() }
            }
    }

    override suspend fun create(currentContact: ContactInfo, contactWillCreate: ContactInfo): UltraContact {
        val request = CreateContactRequest(
            mine = currentContact.toRequest(),
            contact = contactWillCreate.toRequest()
        )
        return remoteApi.create(request)
            .contact
            .toUltraContact()
    }

    override suspend fun getUserById(userId: String): User {
        return remoteApi.getUserById(userId).toUser()
    }

    private fun ContactInfo.toRequest(): ContactInfoRequest = ContactInfoRequest(
        phone = phone,
        firstName = firstName,
        lastName = lastName
    )

    private fun UserResponse.toUser(): User = User(
        userId = id ?: userId, // TODO: bug in mock service
        nickname = nickname,
        phone = phone,
        firstname = firstName,
        lastname = lastName
    )

    private fun CreateContactResponseItem.toUltraContact(): UltraContact = UltraContact(
        userId = chatUserId,
        identifier = userId,
        firstName = nickname,
        lastName = null
    )
}