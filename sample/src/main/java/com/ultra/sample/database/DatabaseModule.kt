package com.ultra.sample.database

import org.koin.dsl.module

object DatabaseModule {

    fun create() = module {
        single { SampleDatabase.getDatabase(get()) }
    }
}