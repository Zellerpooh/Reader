package com.zeller.reader.login

import android.content.SharedPreferences
import androidx.core.content.edit

class AuthTokenLocalDataSource(val prefs: SharedPreferences) {

    private var _authToken: String? = prefs.getString(KEY_ACCESS_TOKEN, null)

    var authToken: String? = _authToken
        set(value) {
            prefs.edit { putString(KEY_ACCESS_TOKEN, value) }
            field = value
        }

    fun clearData() {
        prefs.edit {
            KEY_ACCESS_TOKEN to null
            authToken = null
        }
    }

    companion object {
        const val INO_READER_AUTH_PREF = "INO_READER_AUTH_PREF"

        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"

        @Volatile
        private var INSTANCE: AuthTokenLocalDataSource? = null

        fun getInstance(prefs: SharedPreferences): AuthTokenLocalDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AuthTokenLocalDataSource(prefs).also {
                    INSTANCE = it
                }
            }
        }

    }

}