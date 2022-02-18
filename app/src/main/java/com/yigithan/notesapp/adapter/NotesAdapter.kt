package com.yigithan.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.yigithan.notesapp.R
import com.yigithan.notesapp.entities.Notes
import kotlinx.android.synthetic.main.item_rv_notes.view.*

class NotesAdapter() :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    var listener: AdapterView.OnItemClickListener? = null
    var arrList = ArrayList<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes,parent,false)
        )
    }

    fun setData(arrNotesList: List<Notes>){
        arrList = arrNotesList as ArrayList<Notes>
    }

    fun setOnClickListener(listener1: AdapterView.OnItemClickListener){
        listener = listener1
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        holder.itemView.tvTitle.text = arrList[position].title
        holder.itemView.tvDesc.text = arrList[position].noteText
        holder.itemView.tvDateTime.text = arrList[position].dateTime
    }

    override fun getItemCount(): Int {
        return arrList.size
    }


    class NotesViewHolder(view:View) : RecyclerView.ViewHolder(view){

    }
}