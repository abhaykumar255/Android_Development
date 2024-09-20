package com.example.aidldemo

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return binder.asBinder()
    }

    private var binder : IMyCalculator.Stub = object : IMyCalculator.Stub(){
        override fun addition(x: Int, y: Int): Int {
            return x+y
        }
    }
}