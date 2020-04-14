package com.zeller.reader.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zeller.reader.R
import com.zeller.reader.databinding.ActivityLoginBinding

class LoginActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.signInButton.setOnClickListener { doLogin() }
    }

    fun doLogin() {
        AccountManager.login(
            binding.username.text.toString().trim(),
            binding.password.text.toString().trim()
        )
    }

}