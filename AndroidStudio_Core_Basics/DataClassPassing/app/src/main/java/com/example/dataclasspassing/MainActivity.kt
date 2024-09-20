package com.example.dataclasspassing

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var person: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button: Button = findViewById(R.id.button)


        button.setOnClickListener {
            person = Person("Abhay Kumar", 25)

            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("person",person as Serializable)
            startActivity(intent)
        }
    }

}

