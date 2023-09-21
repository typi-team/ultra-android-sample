package com.ultra.sample.contacts.data.local

import com.ultra.sample.contacts.data.local.model.UserEntity
import com.ultra.sample.contacts.model.User

class UserLocalDataSourceImpl(
    private val userDao: UserDao
) : UserLocalDataSource {

    override suspend fun saveUsers(users: List<User>) {
        userDao.saveUsers(users.map { it.toLocal() })
    }

    override suspend fun saveUser(user: User) {
        userDao.saveUser(user.toLocal())
    }

    override suspend fun getUsers(): List<User> {
        return userDao.getUsers().map { it.toModel() }
    }

    override suspend fun getUserById(userId: String): User? {
        return userDao.getUserById(userId)?.toModel()
    }

    private fun User.toLocal(): UserEntity = UserEntity(
        userId = userId,
        nickname = nickname,
        phone = phone,
        firstname = firstname,
        lastname = lastname
    )

    private fun UserEntity.toModel(): User = User(
        userId = userId,
        nickname = nickname,
        phone = phone,
        firstname = firstname,
        lastname = lastname
    )
}