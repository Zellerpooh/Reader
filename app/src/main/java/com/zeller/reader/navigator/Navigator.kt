package com.zeller.reader.navigator

import android.content.Context
import com.zeller.reader.login.ui.LoginActivity
import com.zeller.reader.ui.activities.MainActivity

class Navigator() {

    private fun showLogin(context: Context) =
        context.startActivity(LoginActivity.callingIntent(context))

    fun showMain(context: Context) {
        showLogin(context)
    }


}