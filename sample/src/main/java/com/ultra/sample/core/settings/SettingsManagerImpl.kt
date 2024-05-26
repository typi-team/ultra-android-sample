package com.ultra.sample.core.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class SettingsManagerImpl(
    context: Context,
) : SettingsManager {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private var _applicationId: String by preferences.propertyDelegate()
    override var applicationId: String?
        get() = _applicationId.takeIf { it.isNotEmpty() }
        set(value) {
            _applicationId = value.orEmpty()
        }

    private var _sid: String by preferences.propertyDelegate()
    override var sid: String?
        get() = _sid.takeIf { it.isNotEmpty() }
        set(value) {
            _sid = value.orEmpty()
        }

    override var isDarkTheme: Boolean by preferences.propertyDelegate(true)
    override var nickname: String by preferences.propertyDelegate()
    override var userId: String by preferences.propertyDelegate()
    override var phone: String by preferences.propertyDelegate()
    override var firstName: String by preferences.propertyDelegate()
    override var lastName: String by preferences.propertyDelegate()

    override fun clear() {
        sid = null
        isDarkTheme = false
        phone = ""
        firstName = ""
        lastName = ""
        applicationId = ""
    }

    private inline fun <reified T : Any> SharedPreferences.propertyDelegate(
        defaultValue: T? = null,
    ): ReadWriteProperty<Any?, T> = PrefDelegate(T::class, this, defaultValue)

    private inner class PrefDelegate<T : Any>(
        val clazz: KClass<T>,
        val prefs: SharedPreferences,
        val defaultValue: T? = null,
    ) : ReadWriteProperty<Any?, T> {

        private fun KProperty<*>.getKeyName(): String =
            this.name
                .asSequence()
                .joinToString(
                    separator = "",
                    prefix = PREF_KEY
                ) { if (it in 'A'..'Z') "_$it" else "$it" }
                .uppercase()

        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            val key = property.getKeyName()
            with(prefs) {
                return when (clazz) {
                    Int::class -> getInt(key, defaultValue as? Int ?: 0) as T
                    Long::class -> getLong(key, defaultValue as? Long ?: 0L) as T
                    Float::class -> getFloat(key, defaultValue as? Float ?: 0f) as T
                    Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
                    String::class -> getString(key, defaultValue as? String ?: "") as T
                    Set::class -> getStringSet(key, defaultValue as? Set<String> ?: emptySet()) as T
                    List::class -> getStringSet(
                        key,
                        ((defaultValue as? List<String>)?.toSet() ?: emptySet())
                    )!!.toList() as T

                    else -> throw TypeCastException("Not yet implemented for class: $clazz")
                }
            }
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            val key = property.getKeyName()
            prefs.edit(commit = true) {
                when (value) {
                    is Int -> putInt(key, value)
                    is Long -> putLong(key, value)
                    is Float -> putFloat(key, value)
                    is Boolean -> putBoolean(key, value)
                    is String -> putString(key, value)
                    is Set<*> -> putStringSet(key, value.mapNotNull { it as? String }.toSet())
                    is List<*> -> putStringSet(key, value.mapNotNull { it as? String }.toSet())
                    else -> throw TypeCastException("Not yet implemented for class: $clazz")
                }
            }
        }
    }

    companion object {

        private const val PREF_NAME = "sample_app"
        private const val PREF_KEY = "PREF_KEY_"
    }
}