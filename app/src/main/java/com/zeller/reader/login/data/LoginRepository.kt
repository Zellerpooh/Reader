package com.zeller.reader.login.data

import com.zeller.reader.login.data.model.UserInfoResponse
import com.zeller.reader.data.Result

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
        val result = remoteDataSource.login(email, password)

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