package com.example.memoryleak

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        Intent(this,SecondActivity::class.java).also {
            startActivity(it)
        }
    }
    companion object{

        lateinit var context: Context
        // this would not be cleared when activity is cleared
        // placced context in static field
    }
}