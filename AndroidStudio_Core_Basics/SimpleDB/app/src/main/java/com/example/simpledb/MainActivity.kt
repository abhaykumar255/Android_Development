package com.example.simpledb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import java.util.jar.Attributes.Name


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val entername = findViewById<EditText>(R.id.entername)
        val enterage = findViewById<EditText>(R.id.enterage)
        val addname = findViewById<Button>(R.id.addname)
        val printname = findViewById<Button>(R.id.printname)
        //val scrollertext = findViewById<LinearLayout>(R.id.scrollertext)
        //scrollertext
        val tName = findViewById<TextView>(R.id.Name)
        //tName.movementMethod = ScrollingMovementMethod()
        val tAge = findViewById<TextView>(R.id.Age)
        //tAge.movementMethod = ScrollingMovementMethod()

        addname.setOnClickListener {
            // created a new dbhelper class
            val db = DBHelper(this, null)

            val name = entername.text.toString()
            val age = enterage.text.toString().toInt()

            if(name==""){
                Toast.makeText(this,"Enter the name",Toast.LENGTH_SHORT).show()
            }else {

                // calling method to add name to our data
                // this method is written in our DBHelperclass
                db.addName(name, age)

                Toast.makeText(this, " $name added to our database", Toast.LENGTH_LONG).show()

                entername.text.clear()
                enterage.text.clear()
            }
        }

        printname.setOnClickListener {
            val db = DBHelper(this, null)

            //  created method to get all the names from the database
            val cursor = db.getName()

            // moving the cursor to the first position
            // appending the value to the text View
            cursor!!.moveToLast()
            tName.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_Col)) + "\n")
            tAge.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_Col)) + "\n")
//            cursor!!.moveToFirst()
//            tName.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_Col)) + "\n")
//            tAge.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_Col)) + "\n")
//
//            // moving cursor to next position and appending value
//            while (cursor.moveToNext()){
//                tName.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_Col)) + "\n")
//                tAge.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_Col)) + "\n")
//            }
            Toast.makeText(this, "Data is showing", Toast.LENGTH_SHORT).show()
//            // closing cursor
            cursor.close()

        }

        val db = DBHelper(this, null)
        val cursorm1 = db.getName()
        cursorm1!!.moveToFirst()
        tName.append(cursorm1.getString(cursorm1.getColumnIndex(DBHelper.NAME_Col)) + "\n")
        tAge.append(cursorm1.getString(cursorm1.getColumnIndex(DBHelper.AGE_Col)) + "\n")
//
        while (cursorm1.moveToNext()) {
            tName.append(cursorm1.getString(cursorm1.getColumnIndex(DBHelper.NAME_Col)) + "\n")
            tAge.append(cursorm1.getString(cursorm1.getColumnIndex(DBHelper.AGE_Col)) + "\n")
//        }
            cursorm1.close()

        }
    }
}