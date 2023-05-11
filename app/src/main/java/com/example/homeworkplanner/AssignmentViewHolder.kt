package com.example.homeworkplanner

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkplanner.databinding.ListItemLayoutBinding

class AssignmentViewHolder(val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentAssignment: Assignment

    fun bindAssignment(assignment: Assignment){
    currentAssignment = assignment
        binding.imageView.setVisibility(View.INVISIBLE)
        binding.dueDateText.text= currentAssignment.date
        binding.typeText.text = currentAssignment.type
    }
    init {
        binding.root.setOnClickListener {
            binding.root.findNavController()
                .navigate(R.id.action_allWorkFragment_to_assignmentDetailsFragment)
            binding.imageView.setVisibility(View.VISIBLE)
            //remove it from recylcer view when returning
            // only nagiate when assignment isn't complete
        }
    }

}