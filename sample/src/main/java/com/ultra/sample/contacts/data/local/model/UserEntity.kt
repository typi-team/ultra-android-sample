package com.ultra.sample.contacts.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo("user_id")
    val userId: String,
    @ColumnInfo("nickname")
    val nickname: String,
    @ColumnInfo("phone")
    val phone: String,
    @ColumnInfo("firstname")
    val firstname: String,
    @ColumnInfo("lastname")
    val lastname: String?
)
