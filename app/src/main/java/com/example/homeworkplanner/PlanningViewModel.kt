package com.example.homeworkplanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlanningViewModel: ViewModel() {
    var list = mutableListOf(
       Assignment("Essay", "Research", "March 1st", "final paper", "English", 100, "2 hours")
    )

    private var _index = 0
    var index: Int =0 //loop index
        get() = _index

    val name: String
        get() = list.get(index ?: 0).type

    val date: String
        get() = list.get(index ?: 0).date

    val desc: String
        get() = list.get(index ?: 0).desc

    val subject: String
        get() = list.get(index ?: 0).subject

    val points: Int
        get() = list.get(index ?: 0).points

    val time: String
        get() = list.get(index ?: 0).time

    private val _isCompleted = MutableLiveData(false)
    val isCompleted: LiveData<Boolean>
        get() = _isCompleted //observe this property

    private var _workType = ""
    var workType: String = ""
        get() = _workType //observe this property

    val numOfAssignments: Int
        get() = list.size

    fun addAssignment(type: String, name: String, date: String, desc: String, subject: String,
                      points: Int, time: String){
        list.add(Assignment(type,name,date,desc,subject,points,time))
    }

}