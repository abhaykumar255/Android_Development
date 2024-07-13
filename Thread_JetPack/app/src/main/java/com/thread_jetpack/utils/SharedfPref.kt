package com.thread_jetpack.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.thread_jetpack.component.SHARED_DATA_KEY
import com.thread_jetpack.component.SharedPrefConstants.BIO
import com.thread_jetpack.component.SharedPrefConstants.EMAIL
import com.thread_jetpack.component.SharedPrefConstants.IMAGE
import com.thread_jetpack.component.SharedPrefConstants.NAME
import com.thread_jetpack.component.SharedPrefConstants.USER_NAME

object SharedfPref {

    fun storeData(
        email: String,
        name: String,
        bio: String,
        userName: String,
        image: String,
        context: Context
    ) {
        val sharedPreferences = context.getSharedPreferences(SHARED_DATA_KEY, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(NAME, name)
        editor.putString(EMAIL, email)
        editor.putString(BIO, bio)
        editor.putString(USER_NAME, userName)
        editor.putString(IMAGE, image)
        editor.apply()
    }

    fun getPrefData(key: String, context: Context): String {
        val sharedPreferences = context.getSharedPreferences(SHARED_DATA_KEY, MODE_PRIVATE)
        return sharedPreferences.getString(key, "")!!
    }
}