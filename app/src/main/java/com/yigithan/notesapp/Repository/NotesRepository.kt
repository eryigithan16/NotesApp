package com.yigithan.notesapp.Repository

import androidx.lifecycle.LiveData
import com.yigithan.notesapp.dao.NoteDao
import com.yigithan.notesapp.Model.Notes

class NotesRepository(val dao: NoteDao) {

    fun getAllNotes() : LiveData<List<Notes>> {
        return dao.getAllNotes()
    }

    fun getHighNotes() : LiveData<List<Notes>> {
        return dao.getHighNotes()
    }

    fun getMediumNotes() : LiveData<List<Notes>> {
        return dao.getMediumNotes()
    }

    fun getLowNotes() : LiveData<List<Notes>> {
        return dao.getLowNotes()
    }

    fun insertNotes(notes: Notes){
        dao.insertNote(notes)
    }

    fun deleteNotes(id:Int){
        dao.deleteNote(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNote(notes)
    }

    fun getNote(id: Int) : Notes{
        return dao.getNote(id)
    }
}