package com.yigithan.notesapp.dao

import androidx.room.*
import com.yigithan.notesapp.entities.Notes


@Dao
interface NoteDao {

    @get:Query("SELECT * FROM notes ORDER BY id DESC")
    val allNotes: List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Notes)

    @Delete
    fun deleteNote(note: Notes)

}