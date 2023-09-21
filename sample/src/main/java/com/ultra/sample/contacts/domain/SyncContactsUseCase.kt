package com.ultra.sample.contacts.domain

import com.ultra.sample.contacts.data.ContactRepository
import com.ultra.sample.contacts.data.store.ContactsDataStore
import com.ultra.sample.contacts.model.ContactDetail
import com.ultra.sample.core.base.UseCase

class SyncContactsUseCase(
    private val contactsDataStore: ContactsDataStore,
    private val contactRepository: ContactRepository,
) : UseCase<Unit, List<ContactDetail>>() {

    override suspend fun execute(parameters: Unit): List<ContactDetail> {
        val contacts = contactsDataStore.getContacts()
        val users = contactRepository.sync(contacts)
        val usersPhones = users.map { it.phone }
        return contacts.map { model ->
            ContactDetail(
                contactInfo = model,
                isClient = usersPhones.contains(model.phone)
            )
        }
    }
}