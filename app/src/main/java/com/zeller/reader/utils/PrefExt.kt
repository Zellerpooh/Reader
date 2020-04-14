package com.zeller.reader.utils

import com.zeller.reader.AppContext
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.pref(default: T) = Preference(AppContext, "", default, R::class.jvmName)