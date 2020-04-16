package com.zeller.reader

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.zeller.reader.dagger.AppComponent
import com.zeller.reader.dagger.ApplicationModule
import com.zeller.reader.dagger.DaggerAppComponent
import com.zeller.reader.dagger.SharedPreferenceModule
import com.zeller.reader.login.data.LoginLocalDataSource

class ReaderApp : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(this)

    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(base)
        super.attachBaseContext(base)
    }

}

