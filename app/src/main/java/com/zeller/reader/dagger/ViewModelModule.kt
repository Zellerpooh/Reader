package com.zeller.reader.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeller.reader.login.ui.LoginViewModel
import com.zeller.reader.login.ui.LoginViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ClassKey(LoginViewModel::class)
    abstract fun bindMainViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: LoginViewModelFactory): ViewModelProvider.Factory
}