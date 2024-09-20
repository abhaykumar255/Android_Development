package com.example.mqtt_learning


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*


class MainActivity : AppCompatActivity() {

    // create mqtt client
    private lateinit var mqttClient : MqttAndroidClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // connect mqtt broker
        fun connect(context : Context) {
            val serverURI = "tcp://broker.emqx.io:1883"
            mqttClient = MqttAndroidClient(context,serverURI,"kotlin_client")
            mqttClient.setCallback(object : MqttCallback{
                override fun connectionLost(cause: Throwable?) {
                    Log.d(TAG, "Connection lost ${cause.toString()}")
                }

                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    Log.d(TAG, "Receive message: ${message.toString()} from topic: $topic")
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {

                }
            })

            val options = MqttConnectOptions()

            try {
                mqttClient.connect(options,null,object :IMqttActionListener{
                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                        Log.d(TAG, "Connection success")
                    }

                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                        Log.d(TAG, "Connection failure")
                    }
                })
            }
            catch (e: MqttException){
                e.printStackTrace()
            }
        }

        fun subscribe(topic : String , qos : Int = 1){
            try {
                mqttClient.subscribe(topic,qos,null, object : IMqttActionListener{
                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                        Log.d(TAG, "Subscribed to $topic")
                    }

                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                        Log.d(TAG, "Failed to Subscribed to $topic")
                    }
                })
            } catch (e: MqttException){
                e.printStackTrace()
            }
        }



        fun unsubscribe(topic: String, cbUnsubscribe : IMqttActionListener = defaultcbUnscuscribe){
            try {
                mqttClient.unsubscribe(topic,null,cbUnsubscribe)
            }
            catch (e:MqttException){
                e.printStackTrace()
            }
        }

        fun publish(topic: String, msg : String, qos: Int = 1,retained : Boolean){

            val message = MqttMessage()
            message.payload = msg.toByteArray()
            message.qos = qos
            message.isRetained = retained

            try {
                mqttClient.publish(topic, message,null, object : IMqttActionListener{
                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                        Log.d(TAG, "$msg published to $topic")
                    }

                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                        Log.d(TAG, " Failed to $msg published to $topic")
                    }
                })
            }
            catch (e:MqttException){
                e.printStackTrace()
            }
        }

        fun disconnect(){
            try {
                mqttClient.disconnect(null, object : IMqttActionListener{
                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                        Log.d(TAG, "Disconnected")
                    }

                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                        Log.d(TAG, " Failed Disconnected")
                    }
                })
            }
            catch (e:MqttException){
                e.printStackTrace()
            }
        }
    }

    private val defaultcbUnscuscribe = object : IMqttActionListener {
        override fun onSuccess(asyncActionToken: IMqttToken?) {
            Log.d(TAG, "Subscribed to topic")
        }

        override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
            Log.d(TAG, "Failed to Subscribed to topic")
        }

    }

    companion object {
        const val TAG = "AndroidMqttClient"
    }


}