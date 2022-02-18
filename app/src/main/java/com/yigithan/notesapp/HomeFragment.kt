package com.yigithan.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yigithan.notesapp.adapter.NotesAdapter
import com.yigithan.notesapp.database.NotesDatabase
import com.yigithan.notesapp.databinding.FragmentHomeBinding
import com.yigithan.notesapp.entities.Notes
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    var arrNotes = ArrayList<Notes>()
    var notesAdapter: NotesAdapter = NotesAdapter()

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesRecyclerView.setHasFixedSize(true)
        notesRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                var notes = NotesDatabase.getDatabase(it).noteDao().getAllNotes()
                //notesRecyclerView.adapter = NotesAdapter(notes)
                notesAdapter!!.setData(notes)
                arrNotes = notes as ArrayList<Notes>
                notesRecyclerView.adapter = notesAdapter
            }
        }

        imageAddNoteMain.setOnClickListener {
            /*val action = HomeFragmentDirections.actionHomeFragmentToCreateNoteFragment()
            Navigation.findNavController(it).navigate(action)*/
            findNavController().navigate(R.id.action_homeFragment_to_createNoteFragment)
        }
    }

}