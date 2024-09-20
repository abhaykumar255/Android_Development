package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import org.junit.Assert.*
import org.junit.Before

class GetNotesTest{

    private lateinit var getNotes: GetNotes

    @Before
    fun setUp(){
        getNotes = GetNotes(NoteRepositoryImpl)
    }
}