package com.example.homeworkplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.homeworkplanner.databinding.FragmentAssignmentDetailsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AssignmentDetailsFragment : Fragment() {
    private var _binding: FragmentAssignmentDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    private val viewModel: PlanningViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbRef = Firebase.database.reference //in view model or every fragment
        _binding = FragmentAssignmentDetailsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        binding.completeButton.setOnClickListener {
            rootView.findNavController().navigateUp()
        }
        binding.returnButton5.setOnClickListener {
            rootView.findNavController().navigateUp()
        }
        return rootView
    }

}