package com.ultra.sample.contacts.model

data class User(
    val userId: String,
    val nickname: String,
    val phone: String,
    val firstname: String,
    val lastname: String?,
) {

    val fullName: String
        get() {
            if (firstname.isEmpty()) {
                return nickname.ifEmpty { phone }
            }
            return if (lastname?.isNotEmpty() == true) {
                "$firstname $lastname"
            } else {
                firstname
            }
        }
}
