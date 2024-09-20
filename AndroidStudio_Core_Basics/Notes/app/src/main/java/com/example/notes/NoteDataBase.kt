package com.example.notes

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
public abstract class NoteDataBase : RoomDatabase() {

    abstract fun getNodeDao() : NoteDao

    // now make database singleton to prevent the multiple instance of database opening at same time
    @Volatile
    private var INSTANCE: NoteDataBase? = null

    fun getDatabase(context: Context): NoteDataBase {
        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteDataBase::class.java,
                "note_database"
            ).build()
            INSTANCE = instance
            // return instance
            instance
        }
    }

    companion object {
        fun getDatabase(application: Application): Any {
            TODO("Not yet implemented")
        }
    }

}