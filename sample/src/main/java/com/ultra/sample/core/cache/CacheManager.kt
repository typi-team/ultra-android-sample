package com.ultra.sample.core.cache

import kotlinx.coroutines.flow.Flow

interface CacheManager {

    suspend fun save(key: String, value: Any?)

    suspend fun listen(key: String): Flow<Any?>
}

const val KEY_CONTACT = "KEY_CONTACT"