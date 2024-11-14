package com.ubayadev.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.ubayadev.todoapp.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckedChange(cb:CompoundButton, isChecked:Boolean, obj:Todo)
}

interface TodoEditClickListener {
    fun onEditClick(v:View)
}

interface RadioClickListener {
    fun onRadioClick(v:View, obj:Todo)
}