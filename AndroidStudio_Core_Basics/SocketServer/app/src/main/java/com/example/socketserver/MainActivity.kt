package com.example.socketserver

import android.annotation.SuppressLint
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.UnknownHostException
import java.nio.ByteBuffer
import java.nio.ByteOrder

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvIP = findViewById(R.id.tvIP)
        tvConnectionStatus = findViewById(R.id.tvConnectionStatus)
        tvPort = findViewById(R.id.tvPort)
        tvMessages = findViewById(R.id.tvMessages)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)
        try {
            Server_IP = localIpAddress
        } catch (e: UnknownHostException) {
            e.printStackTrace()
        }
        Thread1 = Thread(Thread1())
        Thread1.start()

        btnSend.setOnClickListener(View.OnClickListener {
            message = etMessage.text.toString()
                //.trim{ it <= ' ' }
            if (message.isEmpty()){
                Thread(Thread3(message)).start()
            }
        })
    }

    @get:Throws(UnknownHostException::class)
    private val localIpAddress : String
        private get() {
            val wifiManager = (applicationContext.getSystemService(WIFI_SERVICE) as WifiManager)
            val wifiInfo = wifiManager.connectionInfo
            val ipInt = wifiInfo.ipAddress
            return InetAddress.getByAddress(
                ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array()
            ).hostAddress
    }

    internal inner class Thread1 : Runnable {
        override fun run() {
            val socket : Socket
            try {
                serverSocket = ServerSocket(SERVER_PORT)
                runOnUiThread{
                    tvMessages.text = "Not Connected"
                    tvIP.text = "IP : $Server_IP"
                    tvPort.text = "Port : ${SERVER_PORT.toString()}"
                }
                try {
                    socket = serverSocket.accept()
                    output = PrintWriter(socket.getOutputStream())
                    input = BufferedReader(InputStreamReader(socket.getInputStream()))
                    runOnUiThread{ tvMessages.text="Connected \n"}
                    Thread(Thread2()).start()
                }
                catch (e: IOException){
                    e.printStackTrace()
                }
            }
            catch (e: IOException){
                e.printStackTrace()
            }
        }
    }

    private inner class Thread2 : Runnable {
        override fun run() {
            while (true){
                try {
                    val message = input.readLine()
                    if (message != null){
                        runOnUiThread{ tvMessages.append("Client : $message \n")}
                    }
                    else{
                        Thread1 = Thread(Thread1())
                        Thread1.start()
                        return
                    }
                }
                catch (e: IOException){
                    e.printStackTrace()
                }
            }
        }
    }
    internal inner class Thread3(private var message: String) : Runnable {
        override fun run() {
            output.write(message)
            output.flush()
            runOnUiThread{
                tvMessages.append("Server : $message\n")
                etMessage.setText("")
            }
        }
    }

    companion object{

        lateinit var serverSocket : ServerSocket
        lateinit var Thread1 : Thread
        lateinit var tvIP : TextView
        lateinit var tvConnectionStatus : TextView
        lateinit var tvPort : TextView
        lateinit var tvMessages : TextView
        lateinit var etMessage : EditText
        lateinit var btnSend : Button
        lateinit var message : String

        lateinit var output : PrintWriter
        lateinit var input : BufferedReader

        var Server_IP : String = ""
        const val SERVER_PORT = 8080
    }
}