package com.example.homeworkplanner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkplanner.databinding.ListItemLayoutBinding

class AssignmentAdapter(val assignmentList: List<Assignment>): RecyclerView.Adapter<AssignmentViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val binding = ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssignmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        val currentSnap = assignmentList[position]
        holder.bindAssignment(currentSnap)
    }

    override fun getItemCount(): Int {
        return assignmentList.size
    }
}