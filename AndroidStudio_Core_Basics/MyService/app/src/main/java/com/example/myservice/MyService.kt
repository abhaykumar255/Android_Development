package com.example.myservice

import SepratePackage.aidlInterface
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.widget.Toast

class MyService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        //return stubObject.asBinder()
    }

    private val stubObject:  = object : aidlInterface.Stub() {
        @Throws(RemoteException::class)
        override fun calculateData(
            firstValue: Int,
            secondValue: Int,
            operationToPerform: Int
        ): Int {
            return performCalculation(firstValue, secondValue, operationToPerform)
        }
    }

    private fun performCalculation(firstValue: Int, secondValue: Int, operation: Int): Int {
        return when (operation) {
            1 -> firstValue + secondValue
            2 -> firstValue - secondValue
            3 -> firstValue * secondValue
            4 -> firstValue / secondValue
            else -> {
                Toast.makeText(this, "Invalid operation", Toast.LENGTH_SHORT).show()
                Log.e("Error", "Invalid Operation")
                0
            }
        }
    }
}