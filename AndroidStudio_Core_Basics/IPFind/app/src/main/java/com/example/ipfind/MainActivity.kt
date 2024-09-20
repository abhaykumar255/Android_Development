package com.example.ipfind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*

class MainActivity : AppCompatActivity(){
    companion object{
        var scanButton : Button? = null
        var resultTextView : TextView?= null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanButton = findViewById(R.id.button)
        resultTextView = findViewById(R.id.textview)

        scanButton?.setOnClickListener {
            scanNetwork();
        }
    }
    private fun scanNetwork(){
        val sb = java.lang.StringBuilder()
        Thread(object : Runnable{
            override fun run() {
                try {
                    Log.d("Scan Network","Running scan network")
                    val localIpAddress : String? = getlocalIpAddress
                    val ipParts =
                        localIpAddress!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()

                    for (i in 1..254){
                        val ipAddress : String = ipParts[0] + "." + ipParts[1] + "." + ipParts[2] + "." + i

                        val inetAddress : InetAddress = InetAddress.getByName(ipAddress)
                        if (inetAddress.isReachable(1000)){
                            sb.append("""$ipAddress""".trimIndent())
                        }
                    }
                    runOnUiThread(Runnable { resultTextView!!.text = sb.toString() })

                }
                catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }).start()
    }

    private val getlocalIpAddress: String?
        private get() {
            try {
                Log.d("Local IPAddress","Running get local ip address")
                val en: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
                while (en.hasMoreElements()) {
                    val intf: NetworkInterface = en.nextElement()
                    val enumIpAddr: Enumeration<InetAddress> = intf.getInetAddresses()
                    while (enumIpAddr.hasMoreElements()) {
                        val inetAddress: InetAddress = enumIpAddr.nextElement()
                        if (!inetAddress.isLinkLocalAddress() && !inetAddress.isLoopbackAddress() && inetAddress is Inet4Address) {
                            return inetAddress.getHostAddress()
                        }
                    }
                }
            } catch (ex: SocketException) {
                ex.printStackTrace()
            }
            return null
        }

}