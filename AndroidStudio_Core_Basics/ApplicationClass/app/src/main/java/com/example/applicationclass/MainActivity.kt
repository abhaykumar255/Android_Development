package com.example.applicationclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val person = Person("Abhay", 20)

        val applicationClass = application as ApplicationClass

        applicationClass.person = person

        val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }
}