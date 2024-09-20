package com.example.mqttandroid

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // check if internet connection is available
//        if (!isConnected()){
//            Log.d(this.javaClass.name,"Internet Connection NOT available")
//            Toast.makeText(applicationContext,"Internet Connection NOT available",Toast.LENGTH_SHORT).show()
//        }
    }

//    private fun isConnected(): Boolean {
//
//        var result = false
//        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
//            if (capabilities !=null){
//                result = when{capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
//                    else -> false
//                }
//            }
//        }
//        else{
//            val activeNetwork = cm.activeNetworkInfo
//            if(activeNetwork != null){
//                // connect to the network
//                result = when(activeNetwork.type){
//                        ConnectivityManager.TYPE_WIFI,
//                        ConnectivityManager.TYPE_MOBILE,
//                        ConnectivityManager.TYPE_VPN -> true
//                    else -> true
//                }
//            }
//        }
//        return result
//    }
}