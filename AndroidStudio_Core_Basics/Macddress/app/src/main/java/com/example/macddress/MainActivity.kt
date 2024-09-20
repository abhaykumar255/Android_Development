package com.example.macddress

import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var macb = findViewById<Button>(R.id.macb)
        var mact = findViewById<TextView>(R.id.mact)

        macb.setOnClickListener {
            var wifiManager : WifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager;
            var wifiInfo = wifiManager.connectionInfo;
            var macAddress = wifiInfo.macAddress
            mact.setText(macAddress)
        }
    }
}