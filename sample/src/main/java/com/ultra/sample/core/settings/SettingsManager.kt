package com.ultra.sample.core.settings

interface SettingsManager {

    val isAuthorized: Boolean
        get() = !sid.isNullOrEmpty()

    var sid: String?
    var isDarkTheme: Boolean
    var nickname: String
    var userId: String
    var phone: String
    var firstName: String
    var lastName: String

    fun clear()
}