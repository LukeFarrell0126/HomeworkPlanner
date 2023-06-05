package com.example.homeworkplanner

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkplanner.databinding.ListItemLayoutBinding

class AssignmentViewHolder(val binding: ListItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentAssignment: Assignment

    fun bindAssignment(assignment: Assignment) {
        currentAssignment = assignment
        binding.dueDateText.text = currentAssignment.date
        binding.typeText.text = currentAssignment.type
        binding.nameText.text = currentAssignment.name
    }
    init {
        binding.root.setOnClickListener {
            val action = AllWorkFragmentDirections.actionAllWorkFragmentToAssignmentDetailsFragment(currentAssignment.index)
            binding.root.findNavController().navigate(action)
        }
    }

}