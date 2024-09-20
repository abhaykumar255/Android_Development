package com.example.applicationclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val applicationClass = application as ApplicationClass

        val person = applicationClass.person

        if (person != null){

            val nameTextView = findViewById<TextView>(R.id.nameTextView)
            val ageTextView = findViewById<TextView>(R.id.ageTextView)

            nameTextView.text = person.name
            ageTextView.text = person.age.toString()
        }
    }
}