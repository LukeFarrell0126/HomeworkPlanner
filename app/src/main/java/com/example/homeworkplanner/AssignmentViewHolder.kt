package com.example.homeworkplanner

import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkplanner.databinding.ListItemLayoutBinding

class AssignmentViewHolder(val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentAssignment: Assignment

    fun bindAssignment(assignment: Assignment){
    currentAssignment = assignment
        // fill out fields in list item layout
    }

}