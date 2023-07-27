package com.example.todoappproject.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.todoappproject.R
import com.example.todoappproject.common.click

import com.example.todoappproject.data.Database
import com.example.todoappproject.databinding.FragmentDetailBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class DetailFragment : Fragment(R.layout.fragment_detail) {

private lateinit var binding: FragmentDetailBinding
    private val arg:DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        with(binding) {
           txtTitle.text = arg.toDo.title
            txtDesc.text = arg.toDo.desc
            txtDate.text = arg.toDo.date


        }
        clickDeleteButton()
    }
        private fun clickDeleteButton(){
            binding.Delete.click {
                AlertDialog.Builder(requireContext())

                    .setTitle("Delete Note")
                    .setMessage("Do you want to delete this note?")
                    .setPositiveButton("Yes"){_,_ ->

                            Database.removeCompleted(arg.toDo)
                        Database.removeToDo(arg.toDo)
                            Toast.makeText(requireContext(),"deleted successfully",Toast.LENGTH_SHORT).show()


                    }
                    .setNegativeButton("No",null)
                    .show()
            }
        }
}










