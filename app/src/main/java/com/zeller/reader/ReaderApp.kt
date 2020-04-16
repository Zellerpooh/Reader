package com.zeller.reader

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.zeller.reader.dagger.AppComponent
import com.zeller.reader.dagger.ApplicationModule
import com.zeller.reader.dagger.DaggerAppComponent

class ReaderApp : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory()
//            .applicationModule(ApplicationModule(this, "INO_READER_PREFS"))
            .create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(base)
        super.attachBaseContext(base)
    }

}

