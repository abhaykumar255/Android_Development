package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // recyclerView requires layout manager and adapter
        // now will create adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        // after creating the adapter now bind it
        var item = fetchData()
        var adapter:NewsListAdapter = NewsListAdapter(item)

        // now attaching adapter
        recyclerView.adapter=adapter
    }
    // sample for the ArrayList
    private fun fetchData(): ArrayList<String>{
        var list = ArrayList<String>()
        for(i in 0 until 100 ){
            list.add("Item $i")
        }
        return list
    }
}


//need for things
//        1 A list of data objects to work with
//        2 an xml file to view items
//        3 adapter to bind the data to the views
//        4 viewHolder to populate the ui view