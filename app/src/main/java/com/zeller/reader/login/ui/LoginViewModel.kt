package com.zeller.reader.login.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zeller.reader.data.Result
import com.zeller.reader.login.data.LoginRepository
import com.zeller.reader.login.data.model.UserInfoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var user = MutableLiveData<UserInfoResponse?>()

    fun login(email: String, password: String) {
        uiScope.launch {
            val result = loginRepository.login(email, password)
            if (result is Result.Success) {
                user.value = result.data
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}