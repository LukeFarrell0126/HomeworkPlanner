package com.example.homeworkplanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlanningViewModel: ViewModel() {
    var list = mutableListOf(
       Assignment("Essay", "Research", "March 1st", "final paper", "English", 100,
           2.0, false, 0)
    )
    private var _index = 1
    var index: Int =1 //loop index
        get() = _index

    val name: String
        get() = list.get(index ?: 0).name

    val date: String
        get() = list.get(index ?: 0).date

    val desc: String
        get() = list.get(index ?: 0).desc

    val subject: String
        get() = list.get(index ?: 0).subject

    val points: Int
        get() = list.get(index ?: 0).points

    val time: Double
        get() = list.get(index ?: 0).time

    private val _isCompleted = MutableLiveData(false)
    val isCompleted: LiveData<Boolean>
        get() = _isCompleted //observe this property

    private var _workType = ""
    var workType: String = ""
        get() = list.get(index?: 0).type //observe this property

    val numOfAssignments: Int
        get() = list.size

    fun addAssignment(type: String, name: String, date: String, desc: String, subject: String,
                      points: Int, time: Double){
        list.add(Assignment(type,name,date,desc,subject,points,time, false, index))
        index++
    }
    fun removeAssignments(){
        for(work in list) {
            if (work.completed)
                list.remove(work)
        }
    }
    fun checkLate(){
        //call in viewholder to set the status text view
        //get current date to set late, due today, not yet due
    }
    fun totalTime(): Double{
        var total = 0.0
        for(work in list){
            total+=(work.time.toDouble())
        }
        return total
    }
    fun completeAssignment(){
        _isCompleted.value=true
    }

}