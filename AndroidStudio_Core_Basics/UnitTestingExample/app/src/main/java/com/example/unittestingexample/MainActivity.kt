package com.example.unittestingexample

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val label  = findViewById<TextView>(R.id.textview)

        label.setText("Leaks are bad")
        if(sBackground == null)
            sBackground = getDrawable(R.drawable.ic_launcher_background)
        label.setBackgroundDrawable(sBackground)

        setContentView(label)
    }

    companion object {
        private var sBackground: Drawable? = null
    }
}