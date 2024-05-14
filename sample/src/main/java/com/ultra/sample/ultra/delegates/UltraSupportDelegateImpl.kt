package com.ultra.sample.ultra.delegates

import com.typi.ultra.integration.support.UltraSupportDelegate
import com.typi.ultra.integration.support.model.UltraSupportChats

class UltraSupportDelegateImpl : UltraSupportDelegate {

    override suspend fun getSupportChats(): UltraSupportChats {
        return UltraSupportChats(
            isAssistantEnabled = true,
            supportChats = listOf(),
            personalManagers = listOf(),
        )
    }
}