package com.ultra.sample.contacts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typi.ultra.cache.api.UltraCacheProvider
import com.typi.ultra.user.model.SyncedContact
import com.ultra.sample.contacts.domain.SyncContactsUseCase
import com.ultra.sample.contacts.ui.model.GroupContact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class ContactsViewModel(
    private val syncContactsUseCase: SyncContactsUseCase,
    private val cacheProvider: UltraCacheProvider,
) : ViewModel() {

    private val _state: MutableStateFlow<ContactsState> = MutableStateFlow(ContactsState.Loading)
    val state: StateFlow<ContactsState> = _state.asStateFlow()

    private val _effect: Channel<ContactsEffect> = Channel(Channel.BUFFERED)
    val effect: Flow<ContactsEffect> = _effect.receiveAsFlow()

    private val contacts: Flow<List<GroupContact>> =
        flow {
            val groupedContacts = syncContactsUseCase(Unit)
                .groupBy { it.contactModel.firstName.first() }
                .map { entry -> entry.toModel() }
            emit(groupedContacts)
        }
            .flowOn(Dispatchers.IO)
            .catch { Timber.e(it, "Couldn't sync contacts") }

    init {
        viewModelScope.launch {
            contacts.collectLatest { items ->
                _state.value = ContactsState.Display(items)
            }
        }
    }

    fun onContactPicked(contact: SyncedContact) {
        viewModelScope.launch {
            cacheProvider.saveContact(contact)
            _effect.send(ContactsEffect.CloseScreen)
        }
    }

    private fun Map.Entry<Char, List<SyncedContact>>.toModel(): GroupContact {
        return GroupContact(
            initial = key,
            contacts = value
        )
    }
}

sealed class ContactsState {
    object Loading : ContactsState()
    data class Display(val items: List<GroupContact>) : ContactsState()
}

sealed class ContactsEffect {
    object CloseScreen : ContactsEffect()
}