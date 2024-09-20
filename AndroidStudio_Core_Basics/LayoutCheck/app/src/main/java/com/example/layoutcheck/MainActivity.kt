package com.example.layoutcheck

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.TestLooperManager
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import org.w3c.dom.Text

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.layoutFrame, MainFragment())
                .commitNow()
        }
        val smheader : TextView = findViewById(R.id.sm_header)
        smheader.text = "Abhay Rajput" +
                "Abhay Rajput" +
                "Abhay Rajput" +
                "Abhay Rajput" +
                "Abhay Rajput" +
                "Abhay Rajput" +
                "Abhay Rajput" +
                "Abhay Rajput " +
                "Abhay Rajput" +
                "Abhay Rajput "+
                "Abhay $ " +
                "Rajput"

        val sm_subHeading1 = findViewById<TextView>(R.id.sm_subHeading1)
        sm_subHeading1.text = "Testing" +
                "Testing" +
                "Testing Testing" +
                "Testing"

        val sm_discount_price : TextView = findViewById(R.id.sm_discount_price)
        sm_discount_price.text = "$180"

        val sm_price : TextView = findViewById(R.id.sm_price)
        sm_price.text = "$200"

        val sm_subHeading2 : TextView = findViewById(R.id.sm_subHeading2)
        sm_subHeading2.text = " Heading Test" +
                "Heading Test" +
                "Heading Test   Heading Test" +
                "      Heading Test" +
                "    " + " Heading Test" +
                "Heading Test" +
                "Heading Test   Heading Test" +
                "      Heading Test" +
                "    " + " Heading Test" +
                "Heading Test" +
                "Heading Test   Heading Test" +
                "      Heading Test" +
                "    "

        val sm_tnc : TextView = findViewById(R.id.sm_tnc)
        sm_tnc.text = " Header testing" +
                "Header testingHeader testingHeader testingHeader testingHeader testing" +
                "Header testing" +
                "Header testing" +
                "Header testing Header testing" +
                "Header testing"

    }
}