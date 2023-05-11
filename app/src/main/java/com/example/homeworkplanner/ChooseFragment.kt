package com.example.homeworkplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.homeworkplanner.databinding.FragmentChooseBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ChooseFragment : Fragment() {
    lateinit var dbRef: DatabaseReference
    private var _binding: FragmentChooseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlanningViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbRef = Firebase.database.reference //in view model or every fragment
        _binding = FragmentChooseBinding.inflate(inflater, container, false)
        val rootView = binding.root

        binding.returnButton2.setOnClickListener {
            rootView.findNavController().navigateUp()
        }
        binding.essayImage.setOnClickListener{
            viewModel.workType = "Essay"
        }
        binding.projectImage.setOnClickListener{
            viewModel.workType = "Project"
        }
        binding.homeworkImage.setOnClickListener{
            viewModel.workType = "Homework"
        }
        return rootView
    }

}