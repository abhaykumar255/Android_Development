package com.example.intentpassing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendButton = findViewById<Button>(R.id.sendButton)
        val messageEditText = findViewById<EditText>(R.id.messageEditText)

        sendButton.setOnClickListener {
            val message = messageEditText.text.toString()
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("message",message)
            startActivity(intent)
        }
    }
}