package com.example.clientsocketapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.IOException
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

open class MainActivity : AppCompatActivity() {

    private var client : Socket? = null
    private var printwriter: PrintWriter?= null
    private var textField : EditText? = null
    private var button: Button? = null
    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textField = findViewById(R.id.editText1)
        button = findViewById(R.id.button1)


        button!!.setOnClickListener{
            message = textField!!.text.toString()
            Thread(ClientThread(message!!)).start()
            Toast.makeText(this,"Clicked Button ",Toast.LENGTH_LONG).show()
        }
    }

    inner class ClientThread(private val message: String) : Runnable{
        override fun run() {
            try {
                Log.d("ThreadRunnerClass","Inside the client class")
                // the IP and port should be correct to have a connection established
                // Creates a stream socket and connects it to the specified port number on the named host
                client = Socket("192.168.43.114", 4444)
                printwriter = PrintWriter(client!!.getOutputStream(),true)
                printwriter!!.write(message)

                printwriter!!.flush()
                printwriter!!.close()
                // closing the connection
                client!!.close()
            }
            catch (e : IOException){
                e.printStackTrace()
            }
            // updating the UI
            //runOnUiThread(Runnable { textField!!.setText("") })
        }
    }
}