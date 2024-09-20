package com.example.socketio

import android.os.Bundle
import android.telephony.SmsManager.FinancialSmsCallback
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.IO.Options
import com.github.nkzawa.socketio.client.Socket
//import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {

    private var mSocket : Socket? = null

    //private var progressBar: ProgressBar = findViewById(R.id.progressBar)
    //private var button : Button = findViewById(R.id.button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.button)
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        var text = findViewById<TextView>(R.id.text)

        // Socket Instance
        val app : SocketInstance = application as SocketInstance
        mSocket = app.getmSocket()
        // connecting socket
        mSocket?.connect()

        val option = Options()
        option.reconnection = true // reconnection
        option.forceNew = true
        if(mSocket?.connected() == true){
            Toast.makeText(this,"Socket is Connected",Toast.LENGTH_SHORT).show()
        }

//        while (!mSocket!!.connected()){
//            progressBar.visibility = VISIBLE
//        }
        progressBar.visibility = GONE
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()

        val jsonObject1 = JSONObject()
        try {
            jsonObject1.put(
                "token",
                "TOKEN"
            )
        }
        catch (e : JSONException){
            e.printStackTrace()
        }
        // for receiving instance real data
        mSocket?.on("PATIENTS_POOL_FOR_DOCTOR"){ args ->
            val data = args[0] as JSONArray
            // data is in JSON format
            runOnUiThread {
                Log.d("testrunning", "test success")
                for (i in 0 until data.length()) {
                   text.text =data[i].toString() + "\n\n\n"
                }
            }
        }
        button.setOnClickListener {
            try{
                // for ending data to the server emit use
                mSocket?.emit("patientsPoolForDoctor",jsonObject1)
            }
            catch (e:URISyntaxException){
                e.printStackTrace()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mSocket?.disconnect()
    }
}