package com.ultra.sample.contacts.data.remote

import com.typi.ultra.user.model.UltraContact
import com.ultra.sample.contacts.model.ContactInfo
import com.ultra.sample.contacts.model.User

interface ContactRemoteDataSource {

    suspend fun sync(contacts: List<ContactInfo>): List<User>

    suspend fun create(currentContact: ContactInfo, contactWillCreate: ContactInfo): UltraContact

    suspend fun getUserById(userId: String): User
}