package com.example.homeworkplanner

import android.view.View
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
//    init{
//        binding.root.setOnClickListener {
//            binding.root.findNavController()
//                .navigate(R.id.action_mainFragment_to_prankSnapFragment)
//        }
//    } navigate to the details fragment

}