package com.typi.ultra.sample.contacts.ui

import androidx.lifecycle.viewModelScope
import com.typi.ultra.sample.core.base.BaseViewModel
import com.typi.ultra.user.model.SyncedContact
import com.typi.ultra.sample.contacts.domain.SyncContactsUseCase
import com.typi.ultra.sample.contacts.ui.model.GroupContact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class ContactsViewModel(
    private val syncContactsUseCase: SyncContactsUseCase,
) : BaseViewModel<ContactsEvent, ContactsState, ContactsEffect>() {

    private val contacts: Flow<List<GroupContact>> =
        flow {
            val groupedContacts = syncContactsUseCase(Unit)
                .groupBy { it.contactModel.firstName.first() }
                .map { entry -> entry.toModel() }
            emit(groupedContacts)
        }
            .flowOn(Dispatchers.IO)
            .catch { Timber.e(it, "Couldn't sync contacts") }

    override fun setInitialState(): ContactsState = ContactsState.Loading

    override fun handleEvents(event: ContactsEvent) {}

    init {
        viewModelScope.launch {
            contacts.collectLatest { items ->
                setState { ContactsState.Display(items) }
            }
        }
    }

    private fun Map.Entry<Char, List<SyncedContact>>.toModel(): GroupContact {
        return GroupContact(
            initial = key,
            contacts = value
        )
    }
}