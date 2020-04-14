package com.zeller.reader.login

import com.zeller.reader.utils.pref

object AccountManager {
    var authId by pref(-1)
    var username by pref("")
    var passwd by pref("")
    var token by pref("")

    fun isLoggedIn(): Boolean = token.isNotEmpty()

    fun login(userName: String, password: String) {
        username = userName
        passwd = password
        token = (userName + password)
    }

    fun logOut() {

        username = ""
        passwd = ""
        token = ""
    }
}



