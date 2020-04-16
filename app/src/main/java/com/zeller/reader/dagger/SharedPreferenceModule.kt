package com.zeller.reader.dagger

import android.content.Context
import android.content.SharedPreferences
import com.zeller.reader.login.data.LoginLocalDataSource
import com.zeller.reader.login.data.api.InoreaderService
import dagger.Module
import dagger.Provides

@Module
open class SharedPreferenceModule() {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.applicationContext.getSharedPreferences(
            LoginLocalDataSource.INO_READER_PREF,
            Context.MODE_PRIVATE
        )
    }

}