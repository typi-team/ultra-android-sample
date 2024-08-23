package com.ultra.sample.ultra.delegates

import com.typi.ultra.integration.support.UltraSupportDelegate
import com.typi.ultra.integration.support.model.AssistantInfo
import com.typi.ultra.integration.support.model.PersonalManagerInfo
import com.typi.ultra.integration.support.model.SupportChatInfo
import com.typi.ultra.integration.support.model.UltraSupportChats

class UltraSupportDelegateImpl : UltraSupportDelegate {

    override suspend fun getSupportChats(): UltraSupportChats {
        return UltraSupportChats(
            supportChats = listOf(
                SupportChatInfo(
                    reception = 50,
                    receptionService = 50,
                    name = "FF Global 50/50",
                    avatarUrl = "https://i.ibb.co.com/7JWCLz9/Icon-app.png",
                ),
                SupportChatInfo(
                    reception = 50,
                    receptionService = 35,
                    name = "FF Global 50/35",
                    avatarUrl = "https://i.ibb.co.com/7JWCLz9/Icon-app.png",
                ),
                SupportChatInfo(
                    reception = 36,
                    receptionService = 36,
                    name = "FF Казахстан 36/36",
                    avatarUrl = "https://i.ibb.co.com/7JWCLz9/Icon-app.png",
                ),
            ),
            personalManagers = listOf(
                PersonalManagerInfo(
                    userId = "6639e50cc84b2404d400791b",
                    nickname = "Talgat",
                )
            ),
            assistant = AssistantInfo(
                name = "AI Ассистент",
                avatarUrl = "https://i.ibb.co.com/7JWCLz9/Icon-app.png",
            ),
        )
    }
}