package com.zeller.reader.login.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.zeller.reader.R
import com.zeller.reader.ReaderApp
import com.zeller.reader.databinding.ActivityLoginBinding
import org.jetbrains.anko.toast
import javax.inject.Inject

class LoginActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var viewModel: LoginViewModel

    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as ReaderApp).appComponent.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.signInButton.setOnClickListener { doLogin() }

        binding.lifecycleOwner = this

        viewModel.user.observe(this, Observer {
            toast(it.toString())
        })

    }

    fun doLogin() {
        viewModel.login(binding.username.toString().trim(), binding.password.toString().trim())
    }

}