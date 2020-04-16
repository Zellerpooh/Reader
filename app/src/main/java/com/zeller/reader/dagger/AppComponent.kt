package com.zeller.reader.dagger

import android.content.Context
import com.zeller.reader.dagger.inoreader.InoReaderDataModule
import com.zeller.reader.login.ui.LoginActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, InoReaderDataModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Context): AppComponent

//        fun applicationModule(module: ApplicationModule): Factory
    }

    fun inject(loginActivity: LoginActivity)

}