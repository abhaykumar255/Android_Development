package com.example.sampledatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val personname = findViewById<EditText>(R.id.personname)
        val contact = findViewById<EditText>(R.id.contact)
        val age = findViewById<EditText>(R.id.age)
        val insertdata = findViewById<Button>(R.id.insertdata)
        val updatedata = findViewById<Button>(R.id.updatedata)
        val deletedata = findViewById<Button>(R.id.deletedata)
        val viewdata = findViewById<Button>(R.id.viewdata)



    }
}