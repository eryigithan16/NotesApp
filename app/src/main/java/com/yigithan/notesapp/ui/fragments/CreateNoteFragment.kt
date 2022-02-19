package com.yigithan.notesapp.ui.fragments
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yigithan.notesapp.R
import com.yigithan.notesapp.ViewModel.NotesViewModel
import com.yigithan.notesapp.databinding.FragmentCreateNoteBinding
import com.yigithan.notesapp.Model.Notes
import com.yigithan.notesapp.ui.adapter.NotesAdapter
import kotlinx.android.synthetic.main.dialog_delete.*
import java.util.*

class CreateNoteFragment : Fragment() {

    lateinit var binding : FragmentCreateNoteBinding
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()
    val d = Date()
    val notesDate : CharSequence = DateFormat.format("MMMM d, yyyy",d.time)
    val argNotes by navArgs<CreateNoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater,container,false)

        binding.pGreen.setImageResource(R.drawable.ic_tick)

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_tick)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.colorSubtitle.setBackgroundResource(R.color.colorGreen)
        }

        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_tick)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
            binding.colorSubtitle.setBackgroundResource(R.color.colorYellow)
        }

        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_tick)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.colorSubtitle.setBackgroundResource(R.color.colorRed)
        }

        binding.tvDateTime.text = notesDate
        var data2 = argNotes.data

        if (data2 != null) {
            showNotes(data2)
            binding.imgDelete.visibility = View.VISIBLE
        }
        binding.imgDone.setOnClickListener {
            if (data2 != null) { updateNotes(it) }
            else{ createNotes(it) }
        }

        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.imgDelete.setOnClickListener {
            val bottomSheet = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            bottomSheet.dialogYes.setOnClickListener {
                viewModel.deleteNotes(argNotes.data?.noteId!!)
                bottomSheet.dismiss()
                findNavController().popBackStack()
            }

            bottomSheet.dialogNo.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.etNoteTitle.text.toString()
        val subtitle = binding.etNoteSubtitle.text.toString()
        val notes = binding.etNoteDesc.text.toString()
        val data = Notes(
            argNotes.data?.noteId,
            title = title,
            subTitle = subtitle,
            noteText = notes,
            priority = priority,
            dateTime = notesDate.toString()
        )
        viewModel.updateNotes(data)
        Toast.makeText(context,"Note Updated Succesfully",Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun createNotes(it: View?) {
        val title = binding.etNoteTitle.text.toString()
        val subtitle = binding.etNoteSubtitle.text.toString()
        val notes = binding.etNoteDesc.text.toString()
        val data = Notes(
            null,
            title = title,
            subTitle = subtitle,
            noteText = notes,
            priority = priority,
            dateTime = notesDate.toString()
        )
        viewModel.addNotes(data)
        Toast.makeText(context,"Note Created Succesfully",Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun showNotes(showData:Notes){
        binding.etNoteTitle.setText(showData.title)
        binding.etNoteSubtitle.setText(showData.subTitle)
        binding.etNoteDesc.setText(showData.noteText)
        priority = showData.priority
        binding.tvDateTime.text = showData.dateTime

        when(priority){
            "1" -> {
                binding.pGreen.setImageResource(R.drawable.ic_tick)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
                binding.colorSubtitle.setBackgroundResource(R.color.colorGreen)
            }
            "2" -> {
                binding.pYellow.setImageResource(R.drawable.ic_tick)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
                binding.colorSubtitle.setBackgroundResource(R.color.colorYellow)
            }
            "3" -> {
                binding.pRed.setImageResource(R.drawable.ic_tick)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
                binding.colorSubtitle.setBackgroundResource(R.color.colorRed)
            }
        }
    }

}