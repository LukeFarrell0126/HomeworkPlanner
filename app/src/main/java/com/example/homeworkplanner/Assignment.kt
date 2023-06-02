package com.example.homeworkplanner

data class Assignment(val type: String, val name: String, val date: String, val desc: String, val subject: String,
                      val points: Int, val time: Double, var completed: Boolean, var index: Int) {
}