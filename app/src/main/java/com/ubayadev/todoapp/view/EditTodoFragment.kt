package com.ubayadev.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubayadev.todoapp.R
import com.ubayadev.todoapp.databinding.FragmentEditTodoBinding
import com.ubayadev.todoapp.model.Todo
import com.ubayadev.todoapp.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment(), RadioClickListener, TodoEditClickListener {
    private lateinit var binding: FragmentEditTodoBinding
    private lateinit var viewModel:DetailTodoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)
            .get(DetailTodoViewModel::class.java)

        binding.txtJudulTodo.text = "Edit Todo"
        binding.btnAdd.text = "Save"
        binding.radiolistener = this
        binding.savelistener = this

        val uuid = EditTodoFragmentArgs
                   .fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            binding.todo= it
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditTodoBinding.inflate(inflater,
                  container, false)
        return binding.root
    }

    override fun onRadioClick(v: View, obj: Todo) {
        obj.priority = v.tag.toString().toInt()
    }

    override fun onEditClick(v: View) {
        viewModel.update(binding.todo!!)
        Toast.makeText(v.context, "Todo updated",
            Toast.LENGTH_SHORT).show()
    }
}