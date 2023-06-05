package com.example.homeworkplanner

import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class PlanningViewModel : ViewModel() {
    var list = mutableListOf<Assignment>(

    )
    private var _index = 0 //change back once test case is removed
    var index: Int = 0
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

        list.add(Assignment(type, name, date, desc, subject, points, time, false, _index))
        _index++

    }

    fun removeAssignment(index:Int) {
        list.removeAt(index)
        updateIndex()
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
    fun updateIndex() {
        var n = 0
        for (work in list) {
            work.index = n
            n++
        }
        _index = list.size
    }
}