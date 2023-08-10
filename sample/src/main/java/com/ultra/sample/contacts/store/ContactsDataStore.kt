package com.ultra.sample.contacts.store

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER
import android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER
import android.provider.ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME
import android.provider.ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME
import android.provider.ContactsContract.CommonDataKinds.StructuredName.LOOKUP_KEY
import android.provider.ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME
import android.provider.ContactsContract.Data.DISPLAY_NAME
import com.typi.ultra.user.model.ContactModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.provider.ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY as PHONE_LOOKUP_KEY

interface ContactsDataStore {

    suspend fun getContacts(): List<ContactModel>
}

class ContactsDataStoreImpl(
    private val context: Context,
) : ContactsDataStore {

    @SuppressLint("Range")
    override suspend fun getContacts(): List<ContactModel> = withContext(Dispatchers.IO) {
        val contactsMap = mutableMapOf<String, MutableList<ContactModel>>()

        val projectionNames = arrayOf(
            LOOKUP_KEY,
            NUMBER,
            NORMALIZED_NUMBER,
            GIVEN_NAME,
            FAMILY_NAME,
            DISPLAY_NAME,
            MIDDLE_NAME
        )

        context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projectionNames,
            null,
            null,
            null
        )?.use { cursor ->
            if (!cursor.moveToFirst()) return@withContext emptyList()
            val phoneSet = hashSetOf<String>()
            do {
                val rawNumber = cursor
                    .getString(cursor.getColumnIndex(NUMBER))
                    .orEmpty()
                val rawNormalizedNumber = cursor
                    .getString(cursor.getColumnIndex(NORMALIZED_NUMBER))
                    .orEmpty()

                val number = rawNumber.takeIf { rawNormalizedNumber.isEmpty() } ?: rawNormalizedNumber

                if (number.isEmpty()) continue
                if (phoneSet.contains(number)) continue

                val lookupKey = cursor.getString(cursor.getColumnIndex(PHONE_LOOKUP_KEY))
                val firstName = cursor.getString(cursor.getColumnIndex(GIVEN_NAME))
                var lastName = cursor.getString(cursor.getColumnIndex(FAMILY_NAME))
                val middleName = cursor.getString(cursor.getColumnIndex(MIDDLE_NAME))
                val displayName = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME))

                var contact = contactsMap[lookupKey]
                if (contact == null) {
                    contact = mutableListOf()
                    contactsMap[lookupKey] = contact
                }

                var contactFirstName = displayName
                val isNotValidNameString = contactFirstName.isEmpty() ||
                        contactFirstName.count { it in '0'..'9' } > 3
                if (isNotValidNameString) {
                    if (contactFirstName.isEmpty() && !firstName.isNullOrEmpty()) {
                        contactFirstName = firstName.trim()
                    }

                    if (!middleName.isNullOrEmpty()) {
                        if (contactFirstName.isNotEmpty()) {
                            contactFirstName += " ${middleName.trim()}"
                        } else {
                            contactFirstName = middleName.trim()
                        }
                    }
                } else {
                    val spaceIndex = displayName.lastIndexOf(' ')
                    if (spaceIndex != -1) {
                        contactFirstName = displayName.substring(0, spaceIndex).trim()
                        lastName = displayName.substring(spaceIndex + 1).trim()
                    }
                }

                val phoneContact = ContactModel(
                    phone = number,
                    firstName = contactFirstName,
                    lastName = lastName.orEmpty()
                )
                contact.add(phoneContact)

                phoneSet.add(number)
            } while (cursor.moveToNext())
        }

        return@withContext contactsMap.values.flatten()
    }
}