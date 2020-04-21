package com.zeller.reader

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.zeller.reader.dagger.AppComponent
import com.zeller.reader.dagger.ApplicationModule
import com.zeller.reader.dagger.DaggerAppComponent

class ReaderApp : Application() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(base)
        super.attachBaseContext(base)
    }

}

