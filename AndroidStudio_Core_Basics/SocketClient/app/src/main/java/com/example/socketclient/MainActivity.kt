package com.example.socketclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etIP = findViewById(R.id.etIP)
        etPort = findViewById(R.id.etPort)
        btnConnect = findViewById(R.id.btnConnect)
        btnSend = findViewById(R.id.btnSend)
        tvMessages = findViewById(R.id.tvMessages)
        etMessage = findViewById(R.id.etMessage)

        etIP.setText("10.0.2.16")
        etPort.setText("8080")

        btnConnect.setOnClickListener {
            //tvMessages.text = ""
            SERVER_IP = etIP.text.toString()
            SERVER_PORT = etPort.text.toString().toInt()
            Thread1 = Thread(Thread1())
            Thread1.start()
        }
        btnSend.setOnClickListener(View.OnClickListener {
            var message = etMessage.text.toString()
                //.trim {  it <= ' '  }
            if (message.isEmpty()){
                Log.d("SendingClickOnSend","Clicked on Send Button")
                Thread(Thread3(message)).start()
            }
        })
    }

    internal inner class Thread1 : Runnable{
        override fun run() {
            var socket : Socket
            try {
                socket = Socket(SERVER_IP, SERVER_PORT)
                output = PrintWriter(socket.getOutputStream())
                input = BufferedReader(InputStreamReader(socket.getInputStream()))
                runOnUiThread{
                    tvMessages.text = "Connected \n"
                }
                Thread(Thread2()).start()
            }
            catch (e : IOException){
                e.printStackTrace()
            }
        }
    }

    inner class Thread2 : Runnable {
        override fun run() {
            while (true){
                try {
                    val message = input.readLine()
                    if(message != null){
                        Log.d("InsideThread2", "Inside the Thread 2 and and running")
                        runOnUiThread{ tvMessages.append("Server : $message\n") }
                    }
                    else{
                        Thread1 = Thread(Thread1())
                        Thread1.start()
                        return
                    }
                }
                catch (e:IOException){
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
                tvMessages.append("Client : $message\n")
                etMessage.setText("")
            }
        }
    }

    companion object{
        lateinit var etIP : EditText
        lateinit var etPort : EditText
        lateinit var btnConnect : Button
        lateinit var btnSend : Button
        lateinit var tvMessages : TextView
        lateinit var etMessage : EditText

        lateinit var SERVER_IP : String
        var SERVER_PORT = 0

        lateinit var Thread1 : Thread

        lateinit var output : PrintWriter
        lateinit var input : BufferedReader
    }
}