package com.example.tasktrove.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.example.tasktrove.R
import com.example.tasktrove.databinding.FragmentAddpopUpBinding
import com.google.android.material.textfield.TextInputEditText


open class AddpopUpFragment : DialogFragment(){
private lateinit var binding:FragmentAddpopUpBinding
private lateinit var listener: popupnext

fun setListener(listener:popupnext){
    this.listener=listener
}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddpopUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerEvents()
    }

    private fun registerEvents() {
        binding.todoNextBtn.setOnClickListener{
            val todotasks=binding.todoEt.toString()
            if(todotasks.isNotEmpty()){
           listener.savetask(todotasks,binding.todoEt)
            }
            else{
                Toast.makeText(context,"Type some tasks to complete",Toast.LENGTH_SHORT).show()
            }
        }
        binding.todoClose.setOnClickListener{
            dismiss()
        }
    }
interface popupnext{
    fun savetask(todo:String,todoEt: TextInputEditText)
}
}