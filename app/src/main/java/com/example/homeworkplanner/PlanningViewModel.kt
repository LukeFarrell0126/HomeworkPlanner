package com.example.homeworkplanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class PlanningViewModel : ViewModel() {
    var list = mutableListOf<Assignment>(
        Assignment(
            "Essay", "Research", "March 1st", "final paper", "English", 100,
            2.0, false, 0
        )
    )
    private var _index = 1 //change back once test case is removed
    var index: Int = 1
        get() = _index

    val name: String
        get() = list.get(index ?: 0).name

    private val _isCompleted = MutableLiveData(false)
    val isCompleted: LiveData<Boolean>
        get() = _isCompleted //observe this property


    val numOfAssignments: Int
        get() = list.size

    fun addAssignment(
        type: String, name: String, date: String, desc: String, subject: String,
        points: Int, time: Double
    ) {
        list.add(Assignment(type, name, date, desc, subject, points, time, false, index))
        index++
    }

    fun removeAssignment(index:Int) {
        list.removeAt(index)
//        updateIndex()
    }


    fun totalTime(): Double {
        var total = 0.0
        for (work in list) {
            total += (work.time)
        }
        return total
    }

    fun completeAssignment(index: Int) {
        list.get(index ?: 0).completed = true
        updateIndex()
    }
    fun updateIndex() { //update iu
        var n = 0
        for (work in list) {
            work.index = n
            n++
        }
        index = list.size
    }
}