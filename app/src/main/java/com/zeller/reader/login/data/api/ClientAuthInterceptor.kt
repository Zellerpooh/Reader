package com.zeller.reader.login.data.api

import com.zeller.reader.login.AuthTokenLocalDataSource
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class ClientAuthInterceptor(
    private val authTokenLocalDataSource: AuthTokenLocalDataSource,
    private val appId: String,
    private val appKey: String
) : Interceptor {
    override fun intercept(chain: Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if (!authTokenLocalDataSource.authToken.isNullOrEmpty()) {
            requestBuilder.addHeader(
                "Authorization",
                "GoogleLogin auth=${authTokenLocalDataSource.authToken}"
            )
        }
        requestBuilder.addHeader("AppId", appId)
        requestBuilder.addHeader("appKey", appKey)
        return chain.proceed(requestBuilder.build())
    }

}
