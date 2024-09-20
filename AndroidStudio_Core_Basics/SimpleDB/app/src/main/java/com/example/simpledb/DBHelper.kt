package com.example.simpledb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):SQLiteOpenHelper(context, DB_Name,null, DB_Version) {
    override fun onCreate(db: SQLiteDatabase?) {
        // this method for creating the table into the database
        val query = "CREATE TABLE $DB_Table( $ID_Col INTEGER PRIMARY KEY, $NAME_Col TEXT , $AGE_Col INTEGER)"
        // now executing query
        db?.execSQL(query)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // check table if already exists
        db?.execSQL("DROP TABLE IF EXISTS $DB_Table")
        onCreate(db)
    }
    // now creating a function for adding the data
    fun addName(name :String , age :Int){
        // now creating a Content values variable
        val value = ContentValues()
        // now inserting data in the form of key-value form
        value.put(NAME_Col,name)
        value.put(AGE_Col,age)
        // now we r creating a writable variable for inserting data in our database
        val db = this.writableDatabase
        // inserting data into database
        db.insert(DB_Table,null,value)

        // now closing
        db.close()
    }

    // now get the data from the database
    fun getName():Cursor?{
        // creating only for the readable database
        val db = this.readableDatabase

        return db.rawQuery("SELECT *FROM $DB_Table ",null)
    }

    companion object{
        private const val DB_Name ="Geeks_Record"
        const val DB_Version = 1
        const val DB_Table = "Emp_Record"
        const val ID_Col = "id"
        const val NAME_Col = "name"
        const val AGE_Col = "age"
    }
}