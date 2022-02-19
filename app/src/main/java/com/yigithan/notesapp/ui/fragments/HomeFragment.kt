package com.yigithan.notesapp.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.yigithan.notesapp.Model.Notes
import com.yigithan.notesapp.R
import com.yigithan.notesapp.ViewModel.NotesViewModel
import com.yigithan.notesapp.databinding.FragmentHomeBinding
import com.yigithan.notesapp.ui.adapter.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    var oldNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        viewModel.getNotes().observe(viewLifecycleOwner,{ notesList ->
            oldNotes = notesList as ArrayList<Notes>
            binding.notesRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
            adapter = NotesAdapter(requireContext(),notesList)
            binding.notesRecyclerView.adapter = adapter

        })

        binding.imageAddNoteMain.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        binding.filterBtn.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner,{ notesList ->
                oldNotes = notesList as ArrayList<Notes>
                binding.notesRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
                adapter = NotesAdapter(requireContext(),notesList)
                binding.notesRecyclerView.adapter = adapter

            })
        }

        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner,{ notesList ->
                oldNotes = notesList as ArrayList<Notes>
                binding.notesRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
                adapter = NotesAdapter(requireContext(),notesList)
                binding.notesRecyclerView.adapter = adapter

            })
        }

        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner,{ notesList ->
                oldNotes = notesList as ArrayList<Notes>
                binding.notesRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
                adapter = NotesAdapter(requireContext(),notesList)
                binding.notesRecyclerView.adapter = adapter

            })
        }

        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner,{ notesList ->
                oldNotes = notesList as ArrayList<Notes>
                binding.notesRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
                adapter = NotesAdapter(requireContext(),notesList)
                binding.notesRecyclerView.adapter = adapter

            })
        }

        binding.inputSearch.doAfterTextChanged {
            searchingNotes(it)
        }

        return binding.root
    }

    private fun searchingNotes(text : Editable?) {

        val searchedNotes = arrayListOf<Notes>()

        for (i in oldNotes){
            if (i.title.contains(text!!) || i.subTitle.contains(text!!)){
                searchedNotes.add(i)
            }
        }

        adapter.searched(searchedNotes)
    }

}