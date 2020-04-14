package com.zeller.reader.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zeller.reader.R
import com.zeller.reader.R.layout
import com.zeller.reader.databinding.ActivityLoginBinding
import com.zeller.reader.databinding.ActivityMainBinding
import com.zeller.reader.login.AccountManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        fun callingIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.tvLogout.setOnClickListener { AccountManager.logOut() }
    }

}
