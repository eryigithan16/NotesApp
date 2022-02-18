package com.yigithan.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.yigithan.notesapp.database.NotesDatabase
import com.yigithan.notesapp.entities.Notes
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteFragment : BaseFragment() {

    var currentDate:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDate = sdf.format(Date())

        tvDateTime.text = currentDate

        imgDone.setOnClickListener {
            //save note
            saveNote()
        }

        imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun saveNote(){
        if (etNoteTitle.text.isNullOrEmpty()){
            Toast.makeText(context,"Note Title is Required", Toast.LENGTH_SHORT).show()
        }

        if (etNoteSubtitle.text.isNullOrEmpty()){
            Toast.makeText(context,"Note Sub Title is Required", Toast.LENGTH_SHORT).show()
        }

        if (etNoteDesc.text.isNullOrEmpty()){
            Toast.makeText(context,"Note Description is Required", Toast.LENGTH_SHORT).show()
        }

        launch {
            var notes = Notes()
            notes.title = etNoteTitle.text.toString()
            notes.subTitle = etNoteSubtitle.text.toString()
            notes.noteText = etNoteDesc.text.toString()
            notes.dateTime = currentDate

            context?.let {
                NotesDatabase.getDatabase(it).noteDao().insertNote(notes)
                etNoteSubtitle.setText("")
                etNoteTitle.setText("")
                etNoteDesc.setText("")
            }
        }
    }


}