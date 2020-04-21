package com.zeller.reader.login.data

import android.util.Log
import com.zeller.reader.data.Result
import com.zeller.reader.login.AuthTokenLocalDataSource
import com.zeller.reader.login.data.api.InoreaderService
import com.zeller.reader.login.data.model.UserInfoResponse
import com.zeller.reader.utils.safeApiCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    private val tokenLocalDataSource: AuthTokenLocalDataSource,
    val service: InoreaderService
) {

    suspend fun login(email: String, password: String) = safeApiCall(
        call = { requestLogin(email, password) },
        errorMessage = "Error logging in"
    )

    private suspend fun requestLogin(email: String, password: String): Result<UserInfoResponse> {
        Log.d(TAG, "requestLogin")
        try {
            val response = service.clientLogin(email, password).execute()
            Log.d(TAG, "clientLogin finish")
            if (response.isSuccessful) {
                Log.d(TAG, "response isSuccessful")
                val body = response.body()
                if (body != null) {
                    val token = body
                    val realToken = token.substring(token.indexOf("=") + 1)
                    Log.d(TAG, realToken)
                    tokenLocalDataSource.authToken = realToken
                    return requestUser()
                }
            }
            Log.d(TAG, "clientLogin failed with ${response.code()} ${response.message()}")
            return Result.Error(IOException("Access token retrieval failed ${response.code()} ${response.message()}"))
        } catch (e: Exception) {
            Log.d(TAG, "catch exception${e.printStackTrace()}")
            return Result.Error(IOException("exception:${e.printStackTrace()}"))
        }
    }

    private suspend fun requestUser(): Result<UserInfoResponse> {
        Log.d(TAG, "requestUser")
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

    companion object {
        const val TAG = "LoginRemoteDataSource"
    }
}