package com.example.todoappproject.data


object Database {
    private val toDoList = mutableListOf<ToDo>()
   private val completedList= mutableListOf<ToDo>()

    fun getToDo()= toDoList
 fun getCompleted()= completedList

    fun addToDo(title: String, desc: String, date: String,isChecked:Boolean,saveType:String) {

            toDoList.add(
                ToDo(
                    id = (toDoList.lastOrNull()?.id ?: 0) + 1,
                    title = title,
                    desc = desc,
                    date = date,
                    isChecked = isChecked,
                    selecteSaveType =saveType

                )
            )

        }
    fun moveToCompleted(toDo: ToDo) {
        toDoList.remove(toDo)
        toDo.isChecked = true
        completedList.add(toDo)
    }

    fun moveToToDo(toDo: ToDo) {
        completedList.remove(toDo)
        toDo.isChecked = false
        toDoList.add(toDo)
    }
    fun addCompleted(title: String, desc: String, date: String,isChecked:Boolean,saveType:String) {

        completedList.add(
            ToDo(
                id = (completedList.lastOrNull()?.id ?: 0) + 1,
                title = title,
                desc = desc,
                date = date,
                isChecked = isChecked ,
                        selecteSaveType =saveType
            )
        )

    }


fun removeToDo(toDo:ToDo) = toDoList.remove(toDo)
    fun removeCompleted(toDo:ToDo)= completedList.remove(toDo)

}
