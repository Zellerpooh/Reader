package com.zeller.reader.navigator

import android.content.Context
import com.zeller.reader.login.AccountManager
import com.zeller.reader.login.LoginActivity
import com.zeller.reader.ui.activities.MainActivity

class Navigator() {

    private fun showLogin(context: Context) =
        context.startActivity(LoginActivity.callingIntent(context))

    fun showMain(context: Context) {
        when (AccountManager.isLoggedIn()) {
            true -> showHomePage(context)
            false -> showLogin(context)
        }
    }

    private fun showHomePage(context: Context) =
        context.startActivity(MainActivity.callingIntent(context))

}