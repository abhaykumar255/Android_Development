package com.example.bmiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtWeight = findViewById<EditText>(R.id.edtWeight)
        val edtHeightft = findViewById<EditText>(R.id.edtHeightft)
        val edtHeightIn = findViewById<EditText>(R.id.edtHeightIn)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)



        btnCalculate.setOnClickListener{
            var weight : Double = edtWeight.text.toString().toDouble()
            var heightft : Double = edtHeightft.text.toString().toDouble()
            var heightin : Double = edtHeightIn.text.toString().toDouble()

            var tltHtIn : Double = heightft*12 + heightin
            var ttlHeightM : Double = (tltHtIn*2.53)/100

            var bmi :Double = weight/(ttlHeightM*ttlHeightM)

            if(bmi>25){
                txtResult.text="You are overweight"

            }
            else if(bmi<18){
                txtResult.text="You are underweight"

            }
            else{
                txtResult.text="Normal"

            }

            edtWeight.text.clear()
            edtHeightft.text.clear()
            edtHeightIn.text.clear()
        }


    }
}