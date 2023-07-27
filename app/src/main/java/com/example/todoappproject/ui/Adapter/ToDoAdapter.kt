package com.example.todoappproject.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.todoappproject.data.ToDo
import com.example.todoappproject.databinding.ItemTodoBinding


class ToDoAdapter(private val onItemClick: (ToDo) -> Unit={},
                  private val onToDoChecked: (ToDo,Boolean) -> Unit,




): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    private val todoList = mutableListOf<ToDo>()

    inner class ToDoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(toDo: ToDo) {
            with(binding) {
                tvTitle.text = toDo.title
                tvDate.text = toDo.date
checkBox.isChecked=toDo.isChecked
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    onToDoChecked(toDo,isChecked)
                }

                root.setOnClickListener {
                    onItemClick(toDo)

                }

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ToDoViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false),

        )
    override fun getItemCount() = todoList.size
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {

        holder.bind(todoList[position])
    }




    fun updateList(list: List<ToDo>) {
        todoList.clear()
        todoList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }

}