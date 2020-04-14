package com.zeller.reader.navigator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zeller.reader.login.AccountManager

class RouteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navigator = Navigator()
        navigator.showMain(this)
    }

}