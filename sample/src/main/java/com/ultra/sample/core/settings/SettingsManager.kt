package com.ultra.sample.core.settings

interface SettingsManager {

    var sid: String?
    var isDarkTheme: Boolean
    var phone: String
    var firstName: String
    var lastName: String

    fun clear()
}