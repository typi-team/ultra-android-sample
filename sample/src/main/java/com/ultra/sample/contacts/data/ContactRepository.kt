package com.ultra.sample.contacts.data

import com.ultra.sample.contacts.data.model.ContactRequest
import com.ultra.sample.contacts.data.model.ContactResponse
import com.ultra.sample.contacts.data.model.CreateRequest
import com.ultra.sample.contacts.data.model.SyncContactRequest
import com.ultra.sample.contacts.model.ContactInfo
import com.ultra.sample.contacts.model.ContactInfoResult

interface ContactRepository {

    suspend fun sync(contacts: List<ContactInfo>): List<ContactInfo>
    suspend fun create(currentContact: ContactInfo, contactWillCreate: ContactInfo): ContactInfoResult
}

class ContactRepositoryImpl(
    private val remoteApi: ContactRemoteApi,
) : ContactRepository {

    override suspend fun sync(contacts: List<ContactInfo>): List<ContactInfo> {
        val request = SyncContactRequest(contacts = contacts.map { it.toSyncRequest() })
        return remoteApi.sync(request)
            .let { response ->
                response.contacts.map { it.toContactInfo() }
            }
    }

    override suspend fun create(currentContact: ContactInfo, contactWillCreate: ContactInfo): ContactInfoResult {
        val request = CreateRequest(
            currentContact = currentContact.toCreateRequest(),
            contactWillCreate = contactWillCreate.toCreateRequest()
        )
        return remoteApi.create(request)
            .contact
            .toContactInfoResult()
    }

    private fun ContactInfo.toSyncRequest(): ContactRequest =
        ContactRequest(
            phone = phone,
            firstName = firstName,
            lastName = lastName
        )

    private fun ContactInfo.toCreateRequest(): CreateRequest.Contact =
        CreateRequest.Contact(
            phone = phone,
            firstName = firstName,
            lastName = lastName
        )

    private fun ContactRequest.toContactInfo(): ContactInfo =
        ContactInfo(
            phone = phone,
            firstName = firstName,
            lastName = lastName.orEmpty()
        )

    private fun ContactResponse.toContactInfoResult(): ContactInfoResult =
        ContactInfoResult(
            userId = userId,
            phone = phone,
            firstName = firstName,
            lastName = lastName.orEmpty()
        )
}