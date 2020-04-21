package com.zeller.reader.dagger.inoreader

import android.content.SharedPreferences
import com.google.gson.Gson
import com.zeller.reader.BuildConfig
import com.zeller.reader.login.AuthTokenLocalDataSource
import com.zeller.reader.login.data.LoginLocalDataSource
import com.zeller.reader.login.data.LoginRemoteDataSource
import com.zeller.reader.login.data.LoginRepository
import com.zeller.reader.login.data.api.ClientAuthInterceptor
import com.zeller.reader.login.data.api.InoreaderService
import com.zeller.reader.network.StringConverterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class InoReaderDataModule {

    @Provides
    fun provideLoginRepository(
        localSource: LoginLocalDataSource,
        remoteSource: LoginRemoteDataSource
    ): LoginRepository =
        LoginRepository.getInstance(localSource, remoteSource)

    @Provides
    fun provideTokenDataSource(sharedPreferences: SharedPreferences): AuthTokenLocalDataSource =
        AuthTokenLocalDataSource.getInstance(sharedPreferences)

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    fun provideInoReaderService(
        interceptor: HttpLoggingInterceptor,
        tokenHolder: AuthTokenLocalDataSource, gson: Gson
    ): InoreaderService {
        return Retrofit.Builder()
            .baseUrl(InoreaderService.ENDPOINT)
            .callFactory(
                OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(
                    ClientAuthInterceptor(
                        tokenHolder,
                        InoreaderService.INO_APP_ID,
                        InoreaderService.INO_APP_KEY
                    )
                ).build()
            )
            .addConverterFactory(StringConverterFactory())
//            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(InoreaderService::class.java)
    }

}