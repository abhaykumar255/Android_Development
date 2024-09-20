package com.example.dataclassobject

import android.app.Application


// using class to store object and retrieve to the target activity
class MyApp : Application() {

    var currentUser : User? = null
}