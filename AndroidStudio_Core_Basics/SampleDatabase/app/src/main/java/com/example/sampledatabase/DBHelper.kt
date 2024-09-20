package com.example.sampledatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_Name,null, DB_VERSION) {
    companion object{
        private val DB_Name = "Database"
        private val DB_VERSION = 1
        private val Table_Name = "Personal_Details"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $Table_Name(Name TEXT PRIMARY KEY, Contact INTEGER, Age INTEGER)"
        // for execute this query we use (db: SQLiteDatabase?) db , to let the query execute
        // shows error due to nunnable
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // whenever we upgrade the database , we have to drop the database
        val dropTable = "DROP TABLE IF EXISTS $Table_Name"
        db?.execSQL(dropTable)
        // will create the table
        onCreate(db)

        // till now we have created the database and table now,  we will create the new file for handling the
    }

    // Now fetching the data from the table
    fun getAllinfo():List<model>{
        val tasklist = ArrayList<model>()
        // will write the database in open form means wriitable form
        val p0 = writableDatabase
        val selectuery  = "SELECT *FROM $Table_Name"
        // data holding this cursor
        val cursor = p0.rawQuery(selectuery,null)
    }
}