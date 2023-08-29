package com.ultra.sample.core.cache

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow

class CacheManagerImpl : CacheManager {

    private val _cache = mutableMapOf<String, Any?>()
    private val cache: Channel<Map<String, Any?>> = Channel(Channel.BUFFERED)

    override suspend fun save(key: String, value: Any?) {
        _cache[key] = value
        cache.send(_cache)
    }

    override suspend fun listen(key: String): Flow<Any?> {
        save(key, null)
        return cache.receiveAsFlow()
            .mapNotNull { map ->
                map.entries
                    .firstOrNull { entry -> entry.key == key }
                    ?.value
            }
    }
}