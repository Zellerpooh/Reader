package com.zeller.reader.login.data

import android.util.Log
import com.zeller.reader.login.data.model.UserInfoResponse
import com.zeller.reader.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(
    private val localDataSource: LoginLocalDataSource,
    private val remoteDataSource: LoginRemoteDataSource
) {

    var user: UserInfoResponse? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = localDataSource.user
    }

    fun logOut() {
        user = null

        localDataSource.logout()
        remoteDataSource.logout()
    }

    suspend fun login(email: String, password: String): Result<UserInfoResponse> {
        Log.d(TAG, "email:${email},password${password}")

        val result = withContext(Dispatchers.IO) { remoteDataSource.login(email, password) }

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }
        return result
    }

    private fun setLoggedInUser(userInfoResponse: UserInfoResponse) {
        user = userInfoResponse
        localDataSource.user = user
    }

    companion object {

        private const val TAG = "LoginRepository"

        @Volatile
        private var INSTANCE: LoginRepository? = null

        fun getInstance(
            loginLocalDataSource: LoginLocalDataSource,
            remoteDataSource: LoginRemoteDataSource
        ): LoginRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LoginRepository(
                    loginLocalDataSource,
                    remoteDataSource
                ).also {
                    INSTANCE = it
                }
            }
        }
    }
}