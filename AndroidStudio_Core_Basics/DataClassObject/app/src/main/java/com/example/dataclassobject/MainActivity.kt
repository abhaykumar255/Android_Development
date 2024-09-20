package com.example.dataclassobject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // created the User object
        user = User("Abhay Kumar", 23 , "abhay@gmail.com")

        // setting the current user property of Application class
        (application as MyApp).currentUser = user

        val intent  =Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }
}