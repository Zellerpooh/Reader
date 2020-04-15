package com.zeller.reader.login.data.api

import com.zeller.reader.login.data.model.AccessToken
import com.zeller.reader.login.data.model.UserInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface InoreaderService {


    @GET("reader/api/0/user-info")
    fun getUserInfo():Response<List<UserInfoResponse>>

    @POST("accounts/ClientLogin")
    fun clientLogin(@Query("Email") email: String, @Query("Passwd") password: String): Response<AccessToken>

    companion object {
        const val ENDPOINT = "https://www.inoreader.com/"
    }
}