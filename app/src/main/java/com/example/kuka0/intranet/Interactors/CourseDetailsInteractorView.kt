package com.example.kuka0.intranet.Interactors

import com.example.kuka0.intranet.Database.Student

interface CourseDetailsInteractorView {
    interface onFinishedListener{
        fun onSuccess()
        fun onFailed(exception: Exception)
        fun showMessage(message:String)
        fun onFinish(list:ArrayList<Any>)
    }
    fun fetchStudentList(listener: onFinishedListener)
    fun fetchCourseList(teacher_id:String,listener: onFinishedListener)

}