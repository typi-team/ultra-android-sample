package com.ultra.sample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ultra.sample.contacts.data.local.UserDao
import com.ultra.sample.contacts.data.local.model.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1
)
abstract class SampleDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        fun getDatabase(context: Context): SampleDatabase {
            return Room.databaseBuilder(context, SampleDatabase::class.java, "sample_database")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}