package com.example.todoappproject.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.todoappproject.MainActivity
import com.example.todoappproject.R

import com.example.todoappproject.common.showDatePicker
import com.example.todoappproject.common.showTimePicker
import com.example.todoappproject.data.Database
import com.example.todoappproject.data.ToDo
import com.example.todoappproject.databinding.AddAlertDialogDesignBinding
import com.example.todoappproject.databinding.FragmentToDoBinding
import com.example.todoappproject.ui.Adapter.ToDoAdapter
import java.util.Calendar


class ToDoFragment : Fragment(R.layout.fragment_to_do) {
    private lateinit var binding: FragmentToDoBinding


    private val toDoAdapter = ToDoAdapter(::onItemClick,::onToDoChecked)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentToDoBinding.inflate(inflater,container,false)
        with(binding){
            (activity as? MainActivity)?.binding?.floatingActionButton?.setOnClickListener {
                showAddDialog()

            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
   setData(Database.getToDo())


        }

    }
    
    private fun onToDoChecked(toDo: ToDo, isChecked: Boolean) {
        if (isChecked) {
            Database.moveToCompleted(toDo)

        } else {

            Database.moveToToDo(toDo)
        }
        setData(Database.getToDo()) // ToDoFragment'taki RecyclerView'ı güncelle
    }


    private fun setData(list: List<ToDo>) {
        binding.rvTodo.adapter = toDoAdapter
        toDoAdapter.updateList(list)
    }
    private fun onItemClick(toDo: ToDo) {

        val action= ToDoFragmentDirections.actionToDoFragmentToDetailFragment(toDo)
        findNavController().navigate(action)
    }

    private fun showAddDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val binding = AddAlertDialogDesignBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        val dialog = builder.create()
        val saveTypeList = listOf("ToDo", "Completed")

        var selectedSaveType = ""
        var selectedDate = ""
        val saveTypeAdapter = ArrayAdapter(
            requireContext(),

            androidx.transition.R.layout.support_simple_spinner_dropdown_item,
            saveTypeList
        )
        with(binding) {
            val calendar = Calendar.getInstance()
            actSaveType.setAdapter(saveTypeAdapter)

            actSaveType.setOnItemClickListener { _, _, position, _ ->
                selectedSaveType = saveTypeList[position]
            }
            switchDate.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    requireContext().showDatePicker(calendar) { year, month, day ->
                        requireContext().showTimePicker(calendar) { hour, minute ->
                            selectedDate = "$day.$month.$year - $hour:$minute"
                            tvDate.text = "$day.$month.$year - $hour:$minute"
                            tvDate.visibility = View.VISIBLE
                        }
                    }
                }
            }

            btnAdd.setOnClickListener {

                val title = etTitle.text.toString()
                val desc = etDesc.text.toString()
                if (title.isNotEmpty() && desc.isNotEmpty() && selectedSaveType.isNotEmpty()) {
                    if (selectedSaveType == saveTypeList[0]) {
                        Database.addToDo(
                            title,
                            desc,
                            selectedDate,
                            isChecked = false,
                            selectedSaveType
                        )
                        setData(Database.getToDo())
                        dialog.dismiss()
                    } else if (selectedSaveType == saveTypeList[1]) {
                        Database.addCompleted(
                            title,
                            desc,
                            selectedDate,
                            isChecked = true,
                            selectedSaveType
                        )
                        setData(Database.getCompleted())
                        dialog.dismiss()

                    }

                }
            }
                btnCancel.setOnClickListener {

                    dialog.dismiss()
                }

            dialog.show()

        }
    }
}



