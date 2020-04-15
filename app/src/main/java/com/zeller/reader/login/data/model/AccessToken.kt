package com.zeller.reader.login.data.model

import com.google.gson.annotations.SerializedName

data class AccessToken(@SerializedName("access_token") val accessToken: String) {

    fun getToken(): String {
        return "TfKGWxLdLKBqBXsMYiMYCo72hTgi32_i"
    }

    companion object {
        const val result = "SID=null\n" +
                "LSID=null\n" +
                "Auth=TfKGWxLdLKBqBXsMYiMYCo72hTgi32_i\n"

        const val google_auth="GoogleLogin auth=TfKGWxLdLKBqBXsMYiMYCo72hTgi32_i"
    }
}
