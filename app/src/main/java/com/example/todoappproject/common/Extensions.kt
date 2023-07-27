package com.example.todoappproject.common

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import java.util.Calendar
fun Context.showDatePicker(calendar: Calendar,onDateSelected:(Int,Int,Int)->Unit)
{DatePickerDialog(this,{
    _,year,month,day->
    onDateSelected(year,month,day)
},
calendar.get(Calendar.YEAR),
calendar.get(Calendar.MONTH),
calendar.get(Calendar.DAY_OF_MONTH)).show()
}

fun Context.showTimePicker(calendar:Calendar,onTimeSelected:(Int,Int)->Unit){
            TimePickerDialog(
                this,

                {
                        _,hour,minute->
                    onTimeSelected(hour,minute)

                },


        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        true
    ).show()
}
fun CheckBox.setOnCheckedChangeListener(listener: (isChecked: Boolean) -> Unit) {
    setOnCheckedChangeListener { _, isChecked ->
        listener(isChecked)

    }

}

fun View.click(function: () ->Unit){
    this.setOnClickListener {
        function()
    }
}
