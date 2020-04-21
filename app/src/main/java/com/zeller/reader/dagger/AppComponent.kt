package com.zeller.reader.dagger

import android.app.Application
import android.content.Context
import com.zeller.reader.dagger.inoreader.InoReaderDataModule
import com.zeller.reader.login.ui.LoginActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, InoReaderDataModule::class, SharedPreferenceModule::class]
)
interface AppComponent {

    fun inject(application: Application)

    fun inject(loginActivity: LoginActivity)

}