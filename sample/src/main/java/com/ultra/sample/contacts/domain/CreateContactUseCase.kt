package com.ultra.sample.contacts.domain

import com.typi.ultra.user.model.UltraContact
import com.ultra.sample.contacts.data.ContactRepository
import com.ultra.sample.contacts.model.ContactInfo
import com.ultra.sample.core.base.UseCase
import com.ultra.sample.core.settings.SettingsManager

class CreateContactUseCase(
    private val settingsManager: SettingsManager,
    private val contactRepository: ContactRepository,
) : UseCase<CreateContactUseCase.Param, UltraContact>() {

    override suspend fun execute(parameters: Param): UltraContact {
        val currentContact = ContactInfo(
            phone = settingsManager.phone,
            firstName = settingsManager.firstName,
            lastName = settingsManager.lastName,
        )
        return contactRepository.create(
            currentContact = currentContact,
            contactWillCreate = parameters.contactInfo
        )
    }

    data class Param(
        val contactInfo: ContactInfo,
    )
}