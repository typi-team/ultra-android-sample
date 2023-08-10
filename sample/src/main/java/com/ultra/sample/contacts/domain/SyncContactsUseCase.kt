package com.ultra.sample.contacts.domain

import com.typi.ultra.user.model.SyncedContact
import com.ultra.sample.contacts.data.ContactRepository
import com.ultra.sample.contacts.store.ContactsDataStore
import com.ultra.sample.core.base.UseCase

class SyncContactsUseCase(
    private val contactsDataStore: ContactsDataStore,
    private val contactRepository: ContactRepository,
) : UseCase<Unit, List<SyncedContact>>() {

    override suspend fun execute(parameters: Unit): List<SyncedContact> {
        val contacts = contactsDataStore.getContacts()
        val users = contactRepository.sync(contacts)
        return contacts.map { model ->
            SyncedContact(
                contactModel = model,
                isClient = users.contains(model)
            )
        }
    }
}