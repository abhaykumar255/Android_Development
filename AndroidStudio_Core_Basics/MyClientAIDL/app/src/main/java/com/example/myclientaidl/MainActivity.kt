package com.example.myclientaidl

import android.annotation.SuppressLint

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext =this

        textViewDisplayResult = findViewById(R.id.display_result)
        editTextFirstValue = findViewById(R.id.edit_first_value)
        editTextSecondValue = findViewById(R.id.edit_second_value)
        buttonAdd = findViewById(R.id.addition)
        buttonSubtract = findViewById(R.id.subtract)
        buttonDivide = findViewById(R.id.division)
        buttonClearData = findViewById(R.id.clear_data)
        buttonMultiply = findViewById(R.id.multiply)

        buttonAdd.setOnClickListener(this)
        buttonSubtract.setOnClickListener(this)
        buttonMultiply.setOnClickListener(this)
        buttonDivide.setOnClickListener(this)
        buttonClearData.setOnClickListener(this)

        bindAIDLService()
    }

    private fun bindAIDLService(){
        try {
            val aidlServiceIntent = Intent("com.example.myserviceaidl")
            //aidlServiceIntent.setPackage("com.example.myserviceaidl")
           bindService(convertImplicitIntentToExplicitIntent(aidlServiceIntent, mContext),serviceConnectionObject, BIND_AUTO_CREATE)
           //bindService(aidlServiceIntent,serviceConnectionObject, BIND_AUTO_CREATE)
        }
        catch (e:Exception){
            Toast.makeText(this, "Service App may not be present", Toast.LENGTH_SHORT).show()
            Log.e("AIDL_ERROR", "EXCEPTION CAUGHT: $e")
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        var intent : Intent = Intent(this,IMyAidlInterface::calculateData::class.java)
    }

    private var serviceConnectionObject: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            // creating object
            aidlObject = IMyAidlInterface.Stub.asInterface(iBinder)
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            unbindService(this)
        }
    }
    private fun convertImplicitIntentToExplicitIntent(implicitIntent: Intent, context: Context): Intent? {
        val packageManager = context.packageManager
        val resolveInfoList = packageManager.queryIntentServices(implicitIntent, 0)
        if ( resolveInfoList == null ||resolveInfoList.size != 1) {
            return null
        }
        val serviceInfo = resolveInfoList[0]
        val component = ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name)
        val explicitIntent = Intent(implicitIntent)
        explicitIntent.component = component
        return explicitIntent
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.addition -> verifyAndCalculate(1)
            R.id.subtract -> verifyAndCalculate(2)
            R.id.multiply -> verifyAndCalculate(3)
            R.id.division -> verifyAndCalculate(4)
            R.id.clear_data -> {
                editTextSecondValue.text = null
                editTextFirstValue.text = null
                textViewDisplayResult.text = null
            }
            else -> {
                Toast.makeText(this, "Default case ", Toast.LENGTH_SHORT).show()
                Log.i("Error", "Default case")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun verifyAndCalculate(operationType: Int) = if (isAnyValueMissing){
        Toast.makeText(this, "Please enter both the value", Toast.LENGTH_SHORT).show()
    }
    else{
        val result : Int
        firstValue = editTextFirstValue.text.toString().toInt()
        secondValue = editTextSecondValue.text.toString().toInt()
        try {
            result = aidlObject!!.calculateData(firstValue, secondValue,operationType)
            textViewDisplayResult.text = "$result"
        }
        catch (e:RemoteException){
            e.printStackTrace()
        }
    }
    private val isAnyValueMissing: Boolean
        get() = (editTextFirstValue.text.toString()
            .isEmpty() && editTextSecondValue.text.toString().isEmpty()) || (editTextFirstValue.text.toString().isEmpty()) || (editTextSecondValue.text.toString().isEmpty())

    companion object{
        lateinit var textViewDisplayResult: TextView
        lateinit var editTextFirstValue: EditText
        lateinit var editTextSecondValue: EditText
        var firstValue : Int = 0
        var secondValue : Int = 0
        lateinit var buttonAdd: Button
        lateinit var buttonSubtract: Button
        lateinit var buttonMultiply: Button
        lateinit var buttonDivide: Button
        lateinit var buttonClearData: Button
        var aidlObject: IMyAidlInterface? = null
        lateinit var mContext : Context
    }
}