package com.example.broadcastreceiver2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeChangedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var isAirplaneModeActive = intent?.getBooleanExtra("state",false) ?: return
        if(isAirplaneModeActive){
            Toast.makeText(context,"Airplane mode Enabled",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context,"Airplane mode disabled",Toast.LENGTH_LONG).show()
        }
    }
}