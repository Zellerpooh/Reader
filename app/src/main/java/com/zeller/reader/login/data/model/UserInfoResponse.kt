package com.zeller.reader.login.data.model

import com.google.gson.annotations.SerializedName

class UserInfoResponse(
    @SerializedName("userId") val userId: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("userProfileId") val userProfileId: String?,
    @SerializedName("userEmail") val userEmail: String?,
    @SerializedName("isBloggerUser") val isBloggerUser: Boolean,
    @SerializedName("signupTimeSec") val signupTimeSec: Long,
    @SerializedName("isMultiLoginEnabled") val isMultiLoginEnabled: Boolean
)