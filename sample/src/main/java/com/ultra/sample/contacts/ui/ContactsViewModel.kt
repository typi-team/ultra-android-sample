package com.ultra.sample.contacts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typi.ultra.cache.api.UltraCacheProvider
import com.typi.ultra.user.model.UltraContact
import com.ultra.sample.contacts.domain.CreateContactUseCase
import com.ultra.sample.contacts.domain.SyncContactsUseCase
import com.ultra.sample.contacts.model.ContactDetail
import com.ultra.sample.contacts.model.ContactInfo
import com.ultra.sample.contacts.ui.model.GroupContact
import com.ultra.sample.core.cache.CacheManager
import com.ultra.sample.core.cache.KEY_CONTACT
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
    private val syncContactsUseCase: SyncContactsUseCase,
    private val createContactUseCase: CreateContactUseCase,
    private val cacheManager: CacheManager,
    private val cacheProvider: UltraCacheProvider,
) : ViewModel() {

    private val _state: MutableStateFlow<ContactsState> = MutableStateFlow(ContactsState.Loading)
    val state: StateFlow<ContactsState> = _state.asStateFlow()

    private val _effect: Channel<ContactsEffect> = Channel(Channel.BUFFERED)
    val effect: Flow<ContactsEffect> = _effect.receiveAsFlow()

    var isCreateChat: Boolean = false

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

    fun onContactPicked(contact: ContactDetail) {
        if (isCreateChat && contact.isClient.not()) return
        viewModelScope.launch {
            try {
                val contactResult = if (isCreateChat) {
                    createContactUseCase(CreateContactUseCase.Param(contact.contactInfo))
                } else {
                    contact.contactInfo.toUltraContact()
                }
                cacheManager.save(KEY_CONTACT, contactResult)
                cacheProvider.saveContact(contactResult)
                _effect.send(ContactsEffect.CloseScreen)
            } catch (e: Exception) {
                Timber.e(e, "Couldn't create chat")
            }
        }
    }

    private fun Map.Entry<Char, List<ContactDetail>>.toModel(): GroupContact {
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

private fun ContactInfo.toUltraContact() = UltraContact(
    userId = "",
    identifier = phone,
    firstName = firstName,
    lastName = lastName
)