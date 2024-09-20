package com.example.intentpassing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val messageTextView = findViewById<TextView>(R.id.messageTextview)
        val extras = intent.extras
        val message = extras?.getString("message")

        messageTextView.text = message
    }
}