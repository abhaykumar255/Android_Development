package com.example.dataclassobject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // retrieve the currentUser property of application class
        val currentUser = (application as MyApp).currentUser

        val textView = findViewById<TextView>(R.id.textview)
        textView.text = currentUser?.name ?: "No user found"
    }
}