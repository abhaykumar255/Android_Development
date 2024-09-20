package com.example.kotlin_mqtt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.kotlin_mqtt.manager.MQTTConnectionParams
import com.example.kotlin_mqtt.manager.MQTTmanager
import com.example.kotlin_mqtt.protocols.UIUpdaterInterface
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UIUpdaterInterface {
    private lateinit var ipAddressField : EditText
    private lateinit var messageField : EditText
    private lateinit var topicField : EditText
    private lateinit var sendBtn : Button
    private lateinit var connectBtn : Button
    private lateinit var statusLabl : TextView
    private lateinit var messageHistoryView : EditText

    var mqttManager : MQTTmanager ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ipAddressField = findViewById(R.id.ipAddressField)
        messageField = findViewById(R.id.messageField)
        topicField = findViewById(R.id.topicField)
        sendBtn = findViewById(R.id.sendBtn)
        connectBtn = findViewById(R.id.connectBtn)
        statusLabl = findViewById(R.id.statusLabl)
        messageField = findViewById(R.id.messageHistoryView)


    }

    // Interface Method
    override fun resetUIWithConnection(status: Boolean) {
        // manages state of all the buttons
        ipAddressField.isEnabled = !status
        messageField.isEnabled = status
        sendBtn.isEnabled = status
        topicField.isEnabled = !status
        connectBtn.isEnabled = !status

        if (status)
            updateStatusViewWith("Connected")
        else
            updateStatusViewWith("Disconnected")
    }

    override fun updateStatusViewWith(status: String) {
        statusLabl.text = status
    }

    override fun update(message: String) {
        var text = messageHistoryView.text.toString()
        var newText = """
            $text
            $message
            """
        messageHistoryView.setText(newText)
        messageHistoryView.setSelection(messageHistoryView.text.length)
    }

    fun connect(view: View){
        if(!(ipAddressField.text.isNullOrEmpty() && topicField.text.isNullOrEmpty())){
            var host = "tcp://"+ ipAddressField.text.toString() + ":1883"
            var topic = topicField.text.toString()
            var connectionsParams = MQTTConnectionParams("MQTTSample",host,topic,"","")
            mqttManager = MQTTmanager(connectionsParams,applicationContext,this)
            mqttManager?.connect()
        }
        else
            updateStatusViewWith("Please enter all valid details")
    }

    fun sendMessage(view: View){
        mqttManager?.publish(messageField.text.toString())
        messageField.setText("")
    }

}