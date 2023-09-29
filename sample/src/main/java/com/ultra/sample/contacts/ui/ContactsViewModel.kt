package com.ultra.sample.contacts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typi.ultra.integration.cache.UltraCacheProvider
import com.typi.ultra.integration.contact.UltraContact
import com.ultra.sample.R
import com.ultra.sample.contacts.domain.SyncContactsUseCase
import com.ultra.sample.contacts.model.Contact
import com.ultra.sample.contacts.model.ContactInfo
import com.ultra.sample.contacts.ui.model.ContactsEffect
import com.ultra.sample.contacts.ui.model.ContactsState
import com.ultra.sample.contacts.ui.model.GroupContact
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class ContactsViewModel(
    private val isCreateChat: Boolean,
    private val syncContactsUseCase: SyncContactsUseCase,
    private val cacheProvider: UltraCacheProvider,
) : ViewModel() {

    private val _state: MutableStateFlow<ContactsState> = MutableStateFlow(ContactsState.Loading)
    val state: StateFlow<ContactsState> = _state.asStateFlow()

    private val _effect: Channel<ContactsEffect> = Channel(Channel.BUFFERED)
    val effect: Flow<ContactsEffect> = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            flow {
                val groupedContacts = syncContactsUseCase(Unit)
                    .groupBy { it.contactInfo.firstName.first() }
                    .map { entry -> entry.toModel() }
                emit(groupedContacts)
            }
                .catch { Timber.e(it, "Couldn't sync contacts") }
                .collectLatest { items ->
                    _state.value = ContactsState.Display(items)
                }
        }
    }

    fun onContactPicked(contact: Contact) {
        viewModelScope.launch {
            try {
                if (isCreateChat) {
                    val effect = when (contact) {
                        is Contact.BankClient -> {
                            ContactsEffect.ShowChatDetail(contact.userId, contact.name)
                        }
                        is Contact.NotClient -> {
                            ContactsEffect.CloseScreenWithMessage(R.string.choose_bank_client)
                        }
                    }
                    _effect.send(effect)
                } else {
                    cacheProvider.saveContact(contact.contactInfo.toContact())
                    _effect.send(ContactsEffect.CloseScreen)
                }
            } catch (e: Exception) {
                Timber.e(e, "Couldn't pick contact")
                _effect.trySend(ContactsEffect.ShowMessage(R.string.something_went_wrong))
            }
        }
    }

    private fun Map.Entry<Char, List<Contact>>.toModel(): GroupContact {
        return GroupContact(
            initial = key,
            contacts = value
        )
    }

    private fun ContactInfo.toContact(): UltraContact.SendMessage =
        UltraContact.SendMessage(
            phone = phone,
            firstName = firstName,
            lastName = lastName,
        )
}