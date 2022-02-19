package com.yigithan.notesapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yigithan.notesapp.Model.Notes


@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY noteId DESC")
    fun getAllNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority = 3")
    fun getHighNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority = 2")
    fun getMediumNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority = 1")
    fun getLowNotes() : LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Notes)

    @Query("DELETE FROM notes WHERE noteId=:noteId")
    fun deleteNote(noteId: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note: Notes)

    @Query("SELECT * FROM notes WHERE noteId=:noteId")
    fun getNote(noteId: Int) : Notes

}