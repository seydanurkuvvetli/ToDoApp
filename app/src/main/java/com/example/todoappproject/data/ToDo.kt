package com.example.todoappproject.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToDo(val id: Int?,
                val title: String,
                val desc: String?,
                val date: String,
                var isChecked:Boolean,
                var selecteSaveType:String):Parcelable
