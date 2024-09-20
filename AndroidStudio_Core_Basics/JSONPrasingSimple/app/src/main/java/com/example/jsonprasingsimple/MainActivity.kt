package com.example.jsonprasingsimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonStr = listData
        try {
            val userlist = ArrayList<HashMap<String,String?>>()

            // declare the listview
            val lv = findViewById<ListView>(R.id.user_list)

            // initialize json object and extracting the information
            val jObj = JSONObject(jsonStr)
            val jsonArray = jObj.getJSONArray("users")
            for(i in 0 until jsonArray.length()){
                val user = HashMap<String,String?>()
                val obj = jsonArray.getJSONObject(i)
                user["name"] = obj.getString("name")
                user["designation"]  = obj.getString("designation")
                user["location"] = obj.getString("location")
                userlist.add(user)
            }

            // listadapter to broadcast the information to list element
            val adapter:ListAdapter = SimpleAdapter(
                this,userlist,R.layout.list_row,
                arrayOf("name","designation","location"), intArrayOf(
                    R.id.name,
                    R.id.designation,
                    R.id.location
                )
            )
            lv.adapter=adapter
        }
        catch(ex: JSONException) {
            Log.e("JSONParser","unexpected JSON exception",ex)
        }

    }

    private val listData : String
    get() = ("{ \"users\" :[" +
            "{\"name\":\"Ace\",\"designation\":\"Engineer\",\"location\":\"New York\"}" +
            ",{\"name\":\"Tom\",\"designation\":\"Director\",\"location\":\"Chicago\"}" +
            ",{\"name\":\"Tim\",\"designation\":\"Charted Accountant\",\"location\":\"Sunnyvale\"}] }")
}