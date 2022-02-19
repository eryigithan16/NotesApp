package com.yigithan.notesapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yigithan.notesapp.Model.Notes
import com.yigithan.notesapp.R
import com.yigithan.notesapp.databinding.ItemRvNotesBinding
import com.yigithan.notesapp.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    fun searched(searchedNotes : ArrayList<Notes>){
        notesList = searchedNotes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemRvNotesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }


    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.tvTitle.text = data.title
        holder.binding.tvDateTime.text = data.dateTime
        holder.binding.tvDesc.text = data.noteText

        when(data.priority){
            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCreateNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = notesList.size


    class NotesViewHolder(val binding: ItemRvNotesBinding) : RecyclerView.ViewHolder(binding.root)


}