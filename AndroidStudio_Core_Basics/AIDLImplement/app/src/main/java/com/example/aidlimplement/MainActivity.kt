package com.example.aidlimplement

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Process
import android.os.RemoteException
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        var intent : Intent = Intent(this,RemoteService::class.java)
        bindService(intent,connection,Context.BIND_AUTO_CREATE)
    }

    private var connection : ServiceConnection =  object  : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, iBinder: IBinder?) {

            var service : IRemoteService = IRemoteService.Stub.asInterface(iBinder)
            Log.d("ConnectionService","Connection is setup ")
            Toast.makeText(this@MainActivity,"Activity Process: "+ Process.myPid() + ",Service process : " + getRemotePid(service),Toast.LENGTH_SHORT).show()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("DisconnectedService", "Service has been unexpectedly disconnected")
            var service : IRemoteService? = null
        }
    }

    private fun getRemotePid(service: IRemoteService) : Any? {

        var result = -1
        try {
            result = service.pid
        }
        catch (e:RemoteException){
            e.printStackTrace()
        }
        return result
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
}