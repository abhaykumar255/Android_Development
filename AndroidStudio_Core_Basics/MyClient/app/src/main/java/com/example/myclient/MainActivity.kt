package com.example.myclient

import SepratePackage.aidlInterface
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity(), View.OnClickListener {
    private var textViewDisplayResult: TextView? = null
    private var editTextFirstValue: EditText? = null
    private var editTextSecondValue: EditText? = null
    var firstValue = 0
    var secondValue = 0
    var buttonAdd: Button? = null
    var buttonSubtract: Button? = null
    var buttonMultiply: Button? = null
    var buttonDivide: Button? = null
    var buttonClearData: Button? = null
    private var aidlObject: aidlInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        //bind service to the aidl
        bindAIDLService()
    }

    private fun bindAIDLService() {
        try {
            val aidlServiceIntent = Intent("connect_to_aidl_service")
            bindService(aidlServiceIntent, serviceConnectionObject, BIND_AUTO_CREATE)
        } catch (e: Exception) {
            Toast.makeText(this, "Service App may not be present", Toast.LENGTH_SHORT).show();
            Log.e("AIDL_ERROR", "EXCEPTION CAUGHT: $e")
            finish()
        }
    }

    var serviceConnectionObject: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            // creating object
            aidlObject = aidlInterface.Stub.asInterface(iBinder)
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            unbindService(this)
        }
    }

        @Override
//        protected void onStart() {
//            super.onStart();
//            var intent : Intent = Intent(this,aidlInterface::calculateData::class.java)
//        }
//        public Intent implicitIntentToExplicitIntent(Intent implicitIntent, Context context) {
//            PackageManager packageManager = context.getPackageManager();
//            List<ResolveInfo> resolveInfoList = packageManager.queryIntentServices( implicitIntent, 0);
//            Log.d("Mytag",resolveInfoList.toString()+resolveInfoList.size());
//            if (resolveInfoList == null || resolveInfoList.size() != 1) {
//                return null;
//            }
//            ResolveInfo serviceInfo = resolveInfoList.get( 0 );
//            ComponentName component = new ComponentName( serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name );
//            Intent explicitIntent = new Intent( implicitIntent );
//            explicitIntent.setComponent( component );
//            return explicitIntent;
//        }
    override fun onClick(view: View) {
        when (view.id) {
            R.id.addition -> verifyAndCalculate(1)
            R.id.subtract -> verifyAndCalculate(2)
            R.id.multiply -> verifyAndCalculate(3)
            R.id.division -> verifyAndCalculate(4)
            R.id.clear_data -> {
                editTextSecondValue.setText(null)
                editTextFirstValue.setText(null)
                textViewDisplayResult.setText(null)
            }
            else -> {
                Toast.makeText(this, "Default case ", Toast.LENGTH_SHORT).show()
                Log.i("Error", "Default case")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun verifyAndCalculate(operationType: Int) {
        if (isAnyValueMissing) {
            Toast.makeText(this, "Please enter both the value", Toast.LENGTH_SHORT).show()
        } else {
            val result: Int
            firstValue = editTextFirstValue!!.text.toString().toInt()
            secondValue = editTextSecondValue!!.text.toString().toInt()
            try {
                result = aidlObject!!.calculateData(firstValue, secondValue, operationType)
                // getting reply from the service app
                textViewDisplayResult!!.text = "" + result
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    private val isAnyValueMissing: Boolean
        private get() = editTextFirstValue!!.text.toString()
            .isEmpty() && editTextSecondValue!!.text.toString().isEmpty()

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnectionObject)
    }
}