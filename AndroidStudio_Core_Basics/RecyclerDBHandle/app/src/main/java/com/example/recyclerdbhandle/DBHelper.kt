package com.example.recyclerdbhandle

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_Name,null, DB_Version){

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABle $DB_Table($ID INTEGER PRIMARY KEY, $NAME TEXT, $AGE INTEGER)"
        db?.execSQL(query)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $DB_Table")
        onCreate(db)
    }

    fun addName(std : ItemViewModel) : Long{
        val value = ContentValues()
        value.put(ID,std.id)
        value.put(NAME,std.name)
        value.put(AGE,std.age)
        val db = this.writableDatabase
        val success = db.insert(DB_Table,null,value)
        db.close()
        return success
    }
    fun getName() : ArrayList<ItemViewModel>{
        val stdList : ArrayList<ItemViewModel> = ArrayList()
        val db = this.readableDatabase
        val selectQuery = "SELECT *FROM $DB_Table"
        val cursor : Cursor ? = db.rawQuery(selectQuery,null)
        //return ArrayList()
        var id : Int
        var name : String
        var age : Int
        if(cursor!!.moveToFirst()){
            do {
                var idIndex = cursor.getColumnIndex("id")
                id = if (idIndex == -1) 0 else cursor.getInt(idIndex)
                var nameIndex = cursor.getColumnIndex("name")
                name = cursor.getString(nameIndex)
                var ageIndex = cursor.getColumnIndex("age")
                age = cursor.getInt(ageIndex)

                val std = ItemViewModel(id=id,name=name,age=age)
                stdList.add(std)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return stdList
    }
    fun deleteDetailsById(id:Int): Int{
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(ID,id)
        val success = db.delete(DB_Table,"id = $id",null)
        db.close()
        return success
    }
    fun updateDetails(std:ItemViewModel):Int{
        val db =this.writableDatabase
        val content = ContentValues()
        content.put(ID,std.id)
        content.put(NAME,std.name)
        content.put(AGE,std.age)
        val success = db.update(DB_Table,content,"id="+std.id,null)
        db.close()
        return success
    }
    companion object{
        const val DB_Name = "Records"
        const val DB_Version = 1
        const val DB_Table = "Emp_Record"
        const val NAME = "name"
        const val AGE = "age"
        const val ID = "id"
    }

}
