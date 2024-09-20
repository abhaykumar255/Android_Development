package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class NoteViewModel(application: Application) : AndroidViewModel(application ) {

    // Live data fetch
    val allNotes : LiveData<List<Note>>

    init {
        val dataBase = NoteDataBase.getDatabase(application).getNodeDao()
    }
}