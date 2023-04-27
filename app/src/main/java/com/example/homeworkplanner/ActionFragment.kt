package com.example.homeworkplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.homeworkplanner.databinding.FragmentActionBinding
import com.example.homeworkplanner.databinding.FragmentMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ActionFragment : Fragment() {
    lateinit var dbRef : DatabaseReference
    private var _binding: FragmentActionBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbRef = Firebase.database.reference //in view model or every fragment
        _binding = FragmentActionBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.addButton.setOnClickListener{
            val action = ActionFragmentDirections.actionActionFragmentToChooseFragment()
            rootView.findNavController().navigate(action)
        }
        binding.viewButton.setOnClickListener {
            val action = ActionFragmentDirections.actionActionFragmentToAllWorkFragment()
            rootView.findNavController().navigate(action)
        }
        binding.returnButton.setOnClickListener {
            rootView.findNavController().navigateUp()
        }
        return rootView
    }
}