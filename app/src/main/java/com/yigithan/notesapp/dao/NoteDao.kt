package com.yigithan.notesapp.dao

import androidx.room.*
import com.yigithan.notesapp.entities.Notes


@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY noteId DESC")
    suspend fun getAllNotes() : List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Notes)

    @Delete
    suspend fun deleteNote(note: Notes)

}