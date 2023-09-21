package com.ultra.sample.contacts.data

import com.typi.ultra.user.model.UltraContact
import com.ultra.sample.contacts.data.local.UserLocalDataSource
import com.ultra.sample.contacts.data.remote.ContactRemoteDataSource
import com.ultra.sample.contacts.model.ContactInfo
import com.ultra.sample.contacts.model.User

interface ContactRepository {

    suspend fun sync(contacts: List<ContactInfo>): List<User>

    suspend fun create(currentContact: ContactInfo, contactWillCreate: ContactInfo): UltraContact

    suspend fun getUserById(userId: String): User
}

class ContactRepositoryImpl(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: ContactRemoteDataSource
) : ContactRepository {

    override suspend fun sync(contacts: List<ContactInfo>): List<User> {
        val users = remoteDataSource.sync(contacts)
        localDataSource.saveUsers(users)
        return localDataSource.getUsers()
    }

    override suspend fun create(currentContact: ContactInfo, contactWillCreate: ContactInfo): UltraContact {
        return remoteDataSource.create(currentContact, contactWillCreate)
    }

    override suspend fun getUserById(userId: String): User {
        var user = localDataSource.getUserById(userId)
        if (user != null) return user
        user = remoteDataSource.getUserById(userId)
        localDataSource.saveUser(user)
        return user
    }
}