package com.zeller.reader.dagger

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule
//    (val context: Context, val name: String)
{

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

//    @Provides
//    fun provideSharedPreferences(): SharedPreferences {
//        return context.applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
//    }

}