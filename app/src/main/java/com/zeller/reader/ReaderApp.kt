package com.zeller.reader

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import androidx.multidex.MultiDex

private lateinit var INSTANCE: Application

class ReaderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(base)
        super.attachBaseContext(base)
    }
}

object AppContext: ContextWrapper(INSTANCE)