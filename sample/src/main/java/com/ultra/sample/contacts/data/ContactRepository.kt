package com.ultra.sample.contacts.data

import com.typi.ultra.user.model.ContactModel
import com.ultra.sample.contacts.data.model.SyncContact
import com.ultra.sample.contacts.data.model.SyncContactRequest

interface ContactRepository {

    suspend fun sync(contacts: List<ContactModel>): List<ContactModel>
}

class ContactRepositoryImpl(
    private val remoteApi: ContactRemoteApi,
) : ContactRepository {

    override suspend fun sync(contacts: List<ContactModel>): List<ContactModel> {
        val request = SyncContactRequest(contacts = contacts.map { it.toRemote() })
        return remoteApi.sync(request)
            .let { response ->
                response.contacts.map { it.toDomain() }
            }
    }

    private fun ContactModel.toRemote(): SyncContact =
        SyncContact(
            phone = phone,
            firstName = firstName,
            lastName = lastName
        )

    private fun SyncContact.toDomain(): ContactModel =
        ContactModel(
            phone = phone,
            firstName = firstName,
            lastName = lastName.orEmpty()
        )
}