package com.ultra.sample.contacts.domain

import com.ultra.sample.contacts.data.ContactRepository
import com.ultra.sample.contacts.data.store.ContactsDataStore
import com.ultra.sample.contacts.model.Contact
import com.ultra.sample.core.base.UseCase

class SyncContactsUseCase(
    private val contactsDataStore: ContactsDataStore,
    private val contactRepository: ContactRepository,
) : UseCase<Unit, List<Contact>>() {

    override suspend fun execute(parameters: Unit): List<Contact> {
        val contacts = contactsDataStore.getContacts()
        val users = contactRepository.sync(contacts)
        return contacts.map { model ->
            val user = users.firstOrNull { it.phone == model.phone }
            if (user != null) {
                Contact.BankClient(
                    contactInfo = model,
                    name = user.nickname,
                    userId = user.userId
                )
            } else {
                Contact.NotClient(contactInfo = model)
            }
        }
    }
}