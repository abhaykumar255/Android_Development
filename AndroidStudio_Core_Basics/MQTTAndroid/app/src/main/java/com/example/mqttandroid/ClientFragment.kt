package com.example.mqttandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.eclipse.paho.client.mqttv3.*


class ClientFragment : Fragment() {
    private lateinit var mqttClient : MQTTClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_client,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serverURI = arguments?.getString(MQTT_SERVER_URI_KEY)
        val clientId = arguments?.getString(MQTT_CLIENT_ID_KEY)
        val username = arguments?.getString(MQTT_USERNAME_KEY)
        val pwd = arguments?.getString(MQTT_PWD_KEY)

        if(serverURI != null &&
                clientId != null &&
                username != null &&
                pwd != null){

            // open mqtt broker connection
            mqttClient = MQTTClient(context,serverURI,clientId)

            // connect with mqtt
            mqttClient.connect(username,pwd,object :IMqttActionListener{
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d(this.javaClass.name,"Connection Successful")
                    Toast.makeText(context,"MQTT Connection Successful",Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d(this.javaClass.name,"Connection failure : ${exception.toString()}")
                    Toast.makeText(context,"MQTT Connection failure : ${exception.toString()}",Toast.LENGTH_SHORT).show()

                    // come back to connect fragment
                    findNavController().navigate(R.id.action_ClientFragment_to_ConnectFragment)
                }
            },
            object : MqttCallback{
                override fun connectionLost(cause: Throwable?) {
                    Log.d(this.javaClass.name,"Connection Lost : ${cause.toString()}")
                }
                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    val msg = "Received message : ${message.toString()} from topic : $topic"
                    Log.d(this.javaClass.name,msg)
                    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                }
                override fun deliveryComplete(token: IMqttDeliveryToken?) {
                    Log.d(this.javaClass.name,"Delivery Complete")
                }
            })
        }
        else{
            // arguments not valid, come back to connect fragment
            findNavController().navigate(R.id.action_ClientFragment_to_ConnectFragment)
        }

        view.findViewById<Button>(R.id.button_prefill_client).setOnClickListener{
            // setting text empty
            view.findViewById<EditText>(R.id.edittext_pubtopic).setText(MQTT_TEST_TOPIC)
            view.findViewById<EditText>(R.id.edittext_subtopic).setText(MQTT_TEST_TOPIC)
            view.findViewById<EditText>(R.id.edittext_pubmsg).setText(MQTT_TEST_MSG)
        }

        view.findViewById<Button>(R.id.button_clean_client).setOnClickListener{
            // clearing texts
            view.findViewById<EditText>(R.id.edittext_pubtopic).setText("")
            view.findViewById<EditText>(R.id.edittext_subtopic).setText("")
            view.findViewById<EditText>(R.id.edittext_pubmsg).setText("")
        }

        view.findViewById<Button>(R.id.button_disconnect).setOnClickListener {
            if (mqttClient.isConnected()) {
                // Disconnect from MQTT Broker
                mqttClient.disconnect(object : IMqttActionListener {
                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                        Log.d(this.javaClass.name, "Disconnected")

                        Toast.makeText(context, "MQTT Disconnection success", Toast.LENGTH_SHORT).show()

                        // Disconnection success, come back to Connect Fragment
                        findNavController().navigate(R.id.action_ClientFragment_to_ConnectFragment)
                    }

                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                        Log.d(this.javaClass.name, "Failed to disconnect")
                    }
                })
            } else {
                Log.d(this.javaClass.name, "Impossible to disconnect, no server connected")
            }
        }

        view.findViewById<Button>(R.id.button_publish).setOnClickListener{

            val topic = view.findViewById<EditText>(R.id.edittext_pubtopic).text.toString()
            val msg = view.findViewById<EditText>(R.id.edittext_pubmsg).text.toString()

            if(mqttClient.isConnected()){
                mqttClient.publish(
                    topic,
                    msg,
                    1,
                    false,
                    object : IMqttActionListener{
                        override fun onSuccess(asyncActionToken: IMqttToken?) {
                            val message = "Publish message :$msg to topic : $topic "
                            Log.d(this.javaClass.name,message)
                            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailure(
                            asyncActionToken: IMqttToken?,
                            exception: Throwable?,
                        ) {
                            Log.d(this.javaClass.name,"failed to Publish message to topic")
                        }
                    }
                )
            }
            else{
                Log.d(this.javaClass.name,"Impossible to Publish, no server connect")
            }
        }

        view.findViewById<Button>(R.id.button_subscribe).setOnClickListener{
            val topic = view.findViewById<EditText>(R.id.edittext_subtopic).text.toString()

            if(mqttClient.isConnected()){
                mqttClient.subscribe(
                    topic,
                    1,
                    object : IMqttActionListener{
                        override fun onSuccess(asyncActionToken: IMqttToken?) {
                            val msg = "Subscribed to : $topic"
                            Log.d(this.javaClass.name,msg)
                            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailure(
                            asyncActionToken: IMqttToken?,
                            exception: Throwable?,
                        ) {
                            Log.d(this.javaClass.name,"Failed to Subscribe : $topic")
                        }
                    }
                )
            }
            else{
                Log.d(this.javaClass.name,"Impossible to subscribe, no server connect")
            }
        }

        view.findViewById<Button>(R.id.button_unsubscribe).setOnClickListener {
            val topic = view.findViewById<EditText>(R.id.edittext_subtopic).text.toString()

            if(mqttClient.isConnected()){
                mqttClient.unsubscribe(
                    topic,
                    object : IMqttActionListener{
                        override fun onSuccess(asyncActionToken: IMqttToken?) {
                            val msg = "Unsubscribe to : $topic"
                            Log.d(this.javaClass.name,msg)
                            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailure(
                            asyncActionToken: IMqttToken?,
                            exception: Throwable?,
                        ) {
                            Log.d(this.javaClass.name,"Failed to Unsubscribe : $topic")
                        }
                    }
                )
            }
        }


    }
}