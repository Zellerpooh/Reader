package com.zeller.reader.login.data

import com.zeller.reader.utils.safeApiCall
import java.io.IOException
import com.zeller.reader.data.Result
import com.zeller.reader.login.AuthTokenLocalDataSource
import com.zeller.reader.login.data.api.InoreaderService
import com.zeller.reader.login.data.model.UserInfoResponse

class LoginRemoteDataSource(
    private val tokenLocalDataSource: AuthTokenLocalDataSource,
    val service: InoreaderService
) {

    suspend fun login(email: String, password: String) = safeApiCall(
        call = { requestLogin(email, password) },
        errorMessage = "Error logging in"
    )

    private suspend fun requestLogin(email: String, password: String): Result<UserInfoResponse> {
        val response = service.clientLogin(email, password)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val token = body.accessToken
                //TODO 解析Token
                tokenLocalDataSource.authToken = token
                return requestUser()
            }
        }
        return Result.Error(IOException("Access token retrieval failed ${response.code()} ${response.message()}"))
    }

    private suspend fun requestUser(): Result<UserInfoResponse> {
        val response = service.getUserInfo()
        if (response.isSuccessful) {
            val user = response.body()
            if (user != null) {
                return Result.Success(user)
            }
        }
        return Result.Error(IOException("Failed to get user info ${response.code()} ${response.message()}"))
    }

    fun logout() {
        tokenLocalDataSource.authToken = null
    }
}