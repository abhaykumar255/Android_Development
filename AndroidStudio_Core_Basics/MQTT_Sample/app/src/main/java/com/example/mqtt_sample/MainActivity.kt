package com.example.mqtt_sample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*


class MainActivity : AppCompatActivity() {
    private var client: MqttAndroidClient? = null
    private var subText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subText = findViewById(R.id.subText)
        val clientID: String = MqttClient.generateClientId()
        client = MqttAndroidClient(applicationContext, "tcp://192.168.43.61:1883", clientID)

        try {
            val token: IMqttToken = client!!.connect()
            token.actionCallback = object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Toast.makeText(this@MainActivity, "connected!!", Toast.LENGTH_LONG).show()
                    setSubscription()
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Toast.makeText(this@MainActivity, "connection failed", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: MqttException) {
            e.printStackTrace()
        }

        client!!.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                Log.d(this.javaClass.name, "Connection Lost : ${cause.toString()}")
                Toast.makeText(
                    this@MainActivity,
                    "Connection Lost : ${cause.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                if (message != null) {
                    subText!!.text = String(message.payload)
                    //subText.text = String(message.payload)
                }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                Log.d(this.javaClass.name, "Delivery Complete")
            }
        })

    }

    fun published(v: View) {

        val topic = "event"
        val message = "the payload"

        try {
            client?.publish(topic, message.toByteArray(), 0, false)
            Toast.makeText(this, "Published Message", Toast.LENGTH_SHORT).show()
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun connect(v: View) {
        try {
            val token: IMqttToken = client!!.connect()
            token.actionCallback = object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Toast.makeText(this@MainActivity, "Connected", Toast.LENGTH_LONG).show()
                    setSubscription()
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Toast.makeText(this@MainActivity, "Not Connected", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun disconnect(v: View) {
        try {
            val token: IMqttToken = client!!.disconnect()
            token.actionCallback = object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Toast.makeText(this@MainActivity, "Disconnected", Toast.LENGTH_LONG).show()
                    setSubscription()
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Toast.makeText(this@MainActivity, "Not able to disconnect", Toast.LENGTH_LONG)
                        .show()
                }
            }
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    private fun setSubscription() {
        try {
            client?.subscribe("event", 0)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

}


