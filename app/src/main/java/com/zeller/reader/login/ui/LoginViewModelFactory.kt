package com.zeller.reader.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeller.reader.login.data.LoginRepository
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != LoginViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return LoginViewModel(
            loginRepository
        ) as T
    }

}