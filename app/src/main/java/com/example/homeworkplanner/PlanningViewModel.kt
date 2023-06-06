package com.example.homeworkplanner

import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class PlanningViewModel : ViewModel() {
    var list = mutableListOf<Assignment>()
    var dbRef: DatabaseReference = Firebase.database.reference

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

    private var _updated = false //change back once test case is removed
    var updated: Boolean = false
        get() = _updated


    fun addAssignment(
        type: String, name: String, date: String, desc: String, subject: String,
        points: Int, time: Double
    ) {

        list.add(Assignment(type, name, date, desc, subject, points, time, false, _index))
        _index++
        updateDatabase()
    }

    fun addAssignment2(
        type: String, name: String, date: String, desc: String, subject: String,
        points: Int, time: Double
    ) {
        list.add(Assignment(type, name, date, desc, subject, points, time, false, _index))
        _index++
    }

    fun removeAssignment(index: Int) {
        list.removeAt(index)
        updateIndex()
        updateDatabase()
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

    fun updateDatabase() {
        dbRef.child("assignments").removeValue()
        for (work in list) {
            dbRef.child("assignments").push().setValue(work)
        }
    }

    fun resumeData() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (updated == false){
                val allDBEntries = dataSnapshot.children
                var assignmentsAdded = 0
                for (allAssignmentEntries in allDBEntries) {
                    for (singleAssignmentEntry in allAssignmentEntries.children) {
                        assignmentsAdded++
                        addAssignment2(
                            singleAssignmentEntry.child("type").getValue().toString(),
                            singleAssignmentEntry.child("name").getValue().toString(),
                            singleAssignmentEntry.child("date").getValue().toString(),
                            singleAssignmentEntry.child("desc").getValue().toString(),
                            singleAssignmentEntry.child("subject").getValue().toString(),
                            singleAssignmentEntry.child("points").getValue().toString().toInt(),
                            singleAssignmentEntry.child("time").getValue().toString().toDouble(),
                        )
                    }
                }
                _index = assignmentsAdded
            }
                _updated = true

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("MainActivity", "Failed to read value.", error.toException())
            }
        })
    }
}