package com.zeller.reader.login.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.annotations.SerializedName
import com.zeller.reader.login.data.model.UserInfoResponse
import com.zeller.reader.utils.pref
import javax.inject.Inject

class LoginLocalDataSource @Inject constructor(private val prefs: SharedPreferences) {

    var user: UserInfoResponse?
        get() {
            val userId = prefs.getString(KEY_USER_ID, null)
            val userName = prefs.getString(KEY_USER_NAME, null)
            val userProfileId = prefs.getString(KEY_USER_PROFILE_ID, null)
            val userEmail = prefs.getString(KEY_USER_EMAIL, null)
            val isBloggerUser = prefs.getBoolean(KEY_USER_IS_BLOGGER, false)
            val signupTimeSec = prefs.getLong(KEY_SIGN_UP_TIME_SEC, 0L)
            val isMultiLoginEnabled = prefs.getBoolean(KEY_IS_MULTI_LOGIN_ENABLED, false)
            if (userId == null || userName == null) {
                return null
            }
            return UserInfoResponse(
                userId,
                userName,
                userProfileId,
                userEmail,
                isBloggerUser,
                signupTimeSec,
                isMultiLoginEnabled
            )
        }
        set(value) {
            if (value != null) {
                prefs.edit {
                    putString(KEY_USER_ID, value.userId)
                    putString(KEY_USER_NAME, value.userName)
                    putString(KEY_USER_EMAIL, value.userEmail)
                    putString(KEY_USER_PROFILE_ID, value.userProfileId)
                    putBoolean(KEY_USER_IS_BLOGGER, value.isBloggerUser)
                    putLong(KEY_SIGN_UP_TIME_SEC, value.signupTimeSec)
                    putBoolean(KEY_IS_MULTI_LOGIN_ENABLED, value.isMultiLoginEnabled)
                }
            }
        }

    fun logout() {
        prefs.edit {
            putString(KEY_USER_ID, null)
            putString(KEY_USER_NAME, null)
            putString(KEY_USER_EMAIL, null)
            putString(KEY_USER_PROFILE_ID, null)
            putBoolean(KEY_USER_IS_BLOGGER, false)
            putLong(KEY_SIGN_UP_TIME_SEC, 0L)
            putBoolean(KEY_IS_MULTI_LOGIN_ENABLED, false)
        }
    }

    companion object {
        const val INO_READER_PREF = "INO_READER_PREF"

        private const val KEY_USER_ID = "KEY_USER_ID"
        private const val KEY_USER_NAME = "KEY_USER_NAME"
        private const val KEY_USER_PROFILE_ID = "KEY_USER_PROFILE_ID"
        private const val KEY_USER_EMAIL = "KEY_USER_EMAIL"
        private const val KEY_USER_IS_BLOGGER = "KEY_USER_IS_BLOGGER"
        private const val KEY_SIGN_UP_TIME_SEC = "KEY_SIGN_UP_TIME_SEC"
        private const val KEY_IS_MULTI_LOGIN_ENABLED = "KEY_IS_MULTI_LOGIN_ENABLED"
    }
}