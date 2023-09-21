package com.ultra.sample.contacts.data.local

import com.ultra.sample.contacts.model.User

interface UserLocalDataSource {

    suspend fun saveUsers(users: List<User>)

    suspend fun saveUser(user: User)

    suspend fun getUsers(): List<User>

    suspend fun getUserById(userId: String): User?
}