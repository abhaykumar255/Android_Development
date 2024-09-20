package com.example.dataclasspassing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var textView = findViewById<TextView>(R.id.textview)

        // retrieving the dataclass object from intent
        val person = intent.extras?.get("person") as Person

        textView.text = "Name: ${person?.name}\nAge: ${person?.age}"
    }
}