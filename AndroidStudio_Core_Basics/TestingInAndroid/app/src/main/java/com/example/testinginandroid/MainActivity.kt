package com.example.testinginandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.testinginandroid.QuoteManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tagType = findViewById<TextView>(R.id.tagType)
        //tagType.setPadding(50,0,50,0)
    }
}