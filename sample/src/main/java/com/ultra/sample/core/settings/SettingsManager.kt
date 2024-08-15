package com.ultra.sample.core.settings

interface SettingsManager {

    val isAuthorized: Boolean
        get() = !sid.isNullOrEmpty()

    var applicationId: String?
    var sid: String?
    var isDarkTheme: Boolean
    var nickname: String
    var userId: String
    var phone: String
    var firstName: String
    var lastName: String
    var receptionNumber: String

    fun clear()
}