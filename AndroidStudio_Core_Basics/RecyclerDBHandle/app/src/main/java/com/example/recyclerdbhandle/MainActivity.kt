package com.example.recyclerdbhandle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var edName : EditText
    private lateinit var edAge : EditText
    private lateinit var btnAdd : Button
    private lateinit var btnView : Button
    private lateinit var btnUpdate : Button
    private lateinit var db : DBHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter : CustomAdapter ? = null
    private var std : ItemViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
        db = DBHelper(this)
        btnAdd.setOnClickListener{addDetails()}
        btnView.setOnClickListener{getDetails()}
        btnUpdate.setOnClickListener{updateDetails()}
        adapter?.setOnClickItem {
            Toast.makeText(this,it.name,Toast.LENGTH_SHORT).show()
            edName.setText(it.name)
            edAge.setText(it.age.toString())
            std = it
        }
        adapter?.setOnClickDelete {
            deleteDetails(it.id)
        }
    }

    private fun updateDetails() {
        val name = edName.text.toString()
        val age = edAge.text.toString().toInt()
        // record check
        if((name == std?.name) && (age == std?.age)){
            Toast.makeText(this,"Record not changed",Toast.LENGTH_SHORT).show()
            return
        }
        if (std == null) return
        val std = ItemViewModel(id = std!!.id,name=name, age = age)
        val status = db.updateDetails(std)
        if(status > -1){
            clearEditText()
            getDetails()
        } else{
            Toast.makeText(this,"Update failed",Toast.LENGTH_SHORT).show()
        }
    }
    private fun deleteDetails(id:Int){
        if (id==null) return
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Sure to delete detail ??")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){ dialog, _ ->
            db.deleteDetailsById(id)
            getDetails()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){
                dialog, _ ->dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()

    }

    private fun getDetails() {
        val stdList = db.getName()
        Log.e("Done","${stdList.size}")
        adapter?.addItems(stdList)
    }

    private fun addDetails(){
        val name = edName.text.toString()
        val age = edAge.text.toString().toInt()

        if(name.isEmpty()){
            Toast.makeText(this,"Enter the details",Toast.LENGTH_SHORT).show()
        }
        else{
            val std = ItemViewModel(name = name, age = age)
            val success = db.addName(std)
            if(success > -1){
                Toast.makeText(this,"Data saved",Toast.LENGTH_SHORT).show()
                clearEditText()
            }
            else{
                Toast.makeText(this,"Record not saved",Toast.LENGTH_SHORT).show()
            }
            getDetails()
        }
    }
    private fun clearEditText(){
        edName.setText("")
        edAge.setText("")
        edName.requestFocus()
    }
    private fun initRecyclerView(){
        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter = CustomAdapter()
        recyclerView.adapter=adapter
    }
    private fun initView(){
        edName = findViewById(R.id.entername)
        edAge = findViewById(R.id.enterage)
        btnAdd = findViewById(R.id.addname)
        btnView = findViewById(R.id.printname)
        btnUpdate = findViewById(R.id.updatename)
        recyclerView = findViewById(R.id.recyclerView)
    }

}

