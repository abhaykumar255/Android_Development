package com.example.aidldemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.view.View
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText1 = findViewById(R.id.et1)
        editText2 = findViewById(R.id.et2)
        textview1 = findViewById(R.id.tv1)
        button1 = findViewById(R.id.btnCalculate)

        var intent : Intent = Intent(this,MyService::class.java)

        bindService(intent,mConnection, BIND_AUTO_CREATE)

        button1.setOnClickListener(View.OnClickListener {

        })
    }

    private var mConnection : ServiceConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iMyCalculator = IMyCalculator.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }

    }

    fun sample(view : View){
        var value1 :Int = editText1.text.toString().toInt()
        var value2 : Int = editText2.text.toString().toInt()
        try {
            result = iMyCalculator.addition(value1,value2)
            textview1.setText(result)
        }
        catch (e:RemoteException){
            e.printStackTrace()
        }

    }
    companion object{
        lateinit var editText1 : EditText
        lateinit var editText2 : EditText
        lateinit var button1: Button
        lateinit var textview1: EditText
        var result : Int = 0
        lateinit var iMyCalculator : IMyCalculator
    }
}