package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from note_table")
    fun getAllNotes():LiveData<List<Note>>
}

// will create database - to access the data from one place
// Singleton database

// Insert and delete working in background thread, otherwise it shows the error or slowing the application
// running on single thread, it is done by the IO

// livedata can we observed from anywhere
// show the live changes
