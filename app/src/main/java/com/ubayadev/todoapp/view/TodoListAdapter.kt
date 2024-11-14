package com.ubayadev.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubayadev.todoapp.databinding.TodoItemLayoutBinding
import com.ubayadev.todoapp.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>,
                      val adapterOnClick : (Todo) -> Unit)
    : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),
    TodoCheckedChangeListener, TodoEditClickListener {
    class TodoViewHolder(var binding: TodoItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int)
    {
        // initialize attribute binding
        holder.binding.todo = todoList[position]

        // initialize listener binding
        holder.binding.listener = this
        holder.binding.editlistener = this
        holder.binding.checkTask.isChecked = false
    }

    override fun getItemCount(): Int { return todoList.size }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckedChange(cb: CompoundButton, isChecked: Boolean,
                                 objTodo: Todo) {
        if(cb.isPressed) {
            adapterOnClick(objTodo)
        }
    }

    override fun onEditClick(v: View) {
        val action = TodoListFragmentDirections.actionEditTodo(
            v.tag.toString().toInt()
        )
        Navigation.findNavController(v).navigate(action)
    }
}
