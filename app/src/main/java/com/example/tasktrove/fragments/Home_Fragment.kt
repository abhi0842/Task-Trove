package com.example.tasktrove.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.tasktrove.databinding.FragmentHomeBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Home_Fragment : Fragment(), AddpopUpFragment.popupnext {
      private lateinit var auth:FirebaseAuth
      private lateinit var databaseRef:DatabaseReference
      private lateinit var navControl: NavController
      private lateinit var binding:FragmentHomeBinding
      private lateinit var popupfragment:AddpopUpFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(view)
        registerEvents()
    }

    private fun registerEvents() {
        binding.addbutton.setOnClickListener{
            popupfragment= AddpopUpFragment()
            popupfragment.setListener(this)
            popupfragment.show(childFragmentManager,
            "AddpopUpFragment"
            )
        }
    }

    private fun init(view:View) {
    navControl = Navigation.findNavController(view)
        auth=FirebaseAuth.getInstance()
        databaseRef=FirebaseDatabase.getInstance().reference.child("Task").child(auth.currentUser?.uid.toString())
    }

    override fun savetask(todo: String, todoEt: TextInputEditText) {
databaseRef.push().setValue(todo).addOnCompleteListener{
    if(it.isSuccessful){
        Toast.makeText(context,"Task Added Successfully",Toast.LENGTH_SHORT).show()
        todoEt.text=null
    }
    else{
        Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT).show()
    }
    popupfragment.dismiss()
}
    }

}