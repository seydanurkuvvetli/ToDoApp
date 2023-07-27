package com.example.todoappproject.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.example.todoappproject.R

import com.example.todoappproject.data.Database
import com.example.todoappproject.data.ToDo

import com.example.todoappproject.databinding.FragmentCompletedBinding
import com.example.todoappproject.ui.Adapter.ToDoAdapter


class CompletedFragment : Fragment(R.layout.fragment_completed) {
    private val toDoAdapter = ToDoAdapter(::onItemClick,::onToDoChecked)
    private lateinit var binding: FragmentCompletedBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCompletedBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            setData(Database.getCompleted())
        }
    }
    private fun onItemClick(toDo: ToDo) {

        val action= CompletedFragmentDirections.actionCompletedFragmentToDetailFragment(toDo)
        findNavController().navigate(action)
    }
    private fun setData(list:List<ToDo>){
        binding.rvCompleted.adapter=toDoAdapter
        toDoAdapter.updateList(list)

    }
    private fun onToDoChecked(toDo:ToDo,isChecked:Boolean){
        if (!isChecked) {
            Database.moveToToDo(toDo)
            // ToDo tamamlandı, CompletedFragment'a taşı

        } else {
            // ToDo tamamlandığındaki işlemi geri al, CompletedFragment'tan kaldır
         Database.moveToCompleted(toDo)
        }
        setData(Database.getCompleted()) // ToDoFragment'taki RecyclerView'ı güncelle
    }

}