package com.example.mqttandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ConnectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_connect,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_prefill).setOnClickListener{
            // setting the default values
            view.findViewById<EditText>(R.id.edittext_server_uri).setText(MQTT_SERVER_URI)
            view.findViewById<EditText>(R.id.edittext_client_id).setText(MQTT_CLIENT_ID)
            view.findViewById<EditText>(R.id.edittext_username).setText(MQTT_USERNAME)
            view.findViewById<EditText>(R.id.edittext_password).setText(MQTT_PWD)
        }

        view.findViewById<Button>(R.id.button_clean).setOnClickListener{
            // clear the text
            view.findViewById<EditText>(R.id.edittext_server_uri).setText("")
            view.findViewById<EditText>(R.id.edittext_client_id).setText("")
            view.findViewById<EditText>(R.id.edittext_username).setText("")
            view.findViewById<EditText>(R.id.edittext_password).setText("")
        }

        view.findViewById<Button>(R.id.button_connect).setOnClickListener{
            // performing data retrieval

            val serverURIFromEdittext = view.findViewById<EditText>(R.id.edittext_server_uri).toString()
            val clientIDFromEdittext = view.findViewById<EditText>(R.id.edittext_client_id).toString()
            val usernameFromEdittext = view.findViewById<EditText>(R.id.edittext_username).toString()
            val pwdFromEdittext = view.findViewById<EditText>(R.id.edittext_password).toString()

            val mqttCredentialsBundle = bundleOf(MQTT_SERVER_URI to serverURIFromEdittext,
            MQTT_CLIENT_ID to clientIDFromEdittext,
            MQTT_USERNAME to usernameFromEdittext,
            MQTT_PWD to pwdFromEdittext)


            findNavController().navigate(R.id.action_ConnectFragment_to_ClientFragment,mqttCredentialsBundle)
//            Toast.makeText(context,"Clicked on Button",Toast.LENGTH_SHORT).show()
        }
    }
}