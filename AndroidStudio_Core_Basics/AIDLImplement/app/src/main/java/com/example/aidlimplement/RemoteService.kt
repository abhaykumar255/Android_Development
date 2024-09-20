package com.example.aidlimplement

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import android.os.RemoteException
import org.jetbrains.annotations.Nullable

class RemoteService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    // binder created to implement service
    override fun onBind(intent: Intent?): IBinder? {
        return binder.asBinder()
    }

    // stub created object
    private val binder : IRemoteService.Stub = object : IRemoteService.Stub() {
        @Nullable
        @Throws(RemoteException::class)
        override fun getPID(): Int {
            return Process.myPid()
        }
    }
}