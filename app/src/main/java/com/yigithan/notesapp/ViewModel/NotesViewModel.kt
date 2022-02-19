package com.yigithan.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.yigithan.notesapp.Repository.NotesRepository
import com.yigithan.notesapp.database.NotesDatabase
import com.yigithan.notesapp.Model.Notes

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository : NotesRepository

    init {
        val dao = NotesDatabase.getDatabase(application).noteDao()
        repository=NotesRepository(dao)
    }

    fun addNotes(notes:Notes){
        repository.insertNotes(notes)
    }

    fun getNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun getHighNotes() : LiveData<List<Notes>> {
        return repository.getHighNotes()
    }

    fun getMediumNotes() : LiveData<List<Notes>> {
        return repository.getMediumNotes()
    }

    fun getLowNotes() : LiveData<List<Notes>> {
        return repository.getLowNotes()
    }


    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }

    fun getNote(id: Int) : Notes{
        return repository.getNote(id)
    }

}