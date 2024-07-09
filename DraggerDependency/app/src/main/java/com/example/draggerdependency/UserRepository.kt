package com.example.draggerdependency

import android.content.ContentValues.TAG
import android.util.Log
import javax.inject.Inject

class UserRepository @Inject constructor() {
    fun saveUser(email : String, pass : String){
        Log.d(TAG,"User saved in db")
    }
}