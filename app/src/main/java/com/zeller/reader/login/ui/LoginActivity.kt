package com.zeller.reader.login.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zeller.reader.R
import com.zeller.reader.ReaderApp
import com.zeller.reader.databinding.ActivityLoginBinding
import org.jetbrains.anko.toast
import javax.inject.Inject

class LoginActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory

    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as ReaderApp).appComponent.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.signInButton.setOnClickListener { doLogin() }

        binding.lifecycleOwner = this

        binding.username.setText("zellerpooh@gmail.com")
        binding.password.setText("Wei1ai.neymar")

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LoginViewModel::class.java)

        viewModel.user.observe(this, Observer {
            toast(it.toString())
        })

    }

    fun doLogin() {
        toast("startLogin")
        println("startLogin")
        viewModel.login(
            binding.username.text.toString().trim(),
            binding.password.text.toString().trim()
        )
    }

}