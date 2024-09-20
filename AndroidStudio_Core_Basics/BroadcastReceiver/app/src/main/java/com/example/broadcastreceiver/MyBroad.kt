package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

// static broadcast receiver
class MyBroad : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action){
            Intent.ACTION_BOOT_COMPLETED ->{
                Toast.makeText(context,"Boot Completed",Toast.LENGTH_LONG).show()
            }
            Intent.ACTION_TIME_TICK ->{
                Toast.makeText(context,"Time Changed",Toast.LENGTH_LONG).show()
            }
            Intent.ACTION_ALL_APPS ->{
                Toast.makeText(context,"Clicked App",Toast.LENGTH_LONG).show()
            }
        }
    }
}