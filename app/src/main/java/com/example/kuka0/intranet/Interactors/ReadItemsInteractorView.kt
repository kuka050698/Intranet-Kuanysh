package com.example.kuka0.intranet.Interactors

interface ReadItemsInteractorView {
    interface onFinishedListener {
        fun onSuccess (list : ArrayList<Any>)
        fun onFinish(list:ArrayList<Any>)
    }

    fun readCourses(listener : ReadItemsInteractorView.onFinishedListener)
//    fun readCoursesByTeacher(uid: String, listener: ReadItemsInteractorView.onFinishedListener)
    fun readStudents(id:String,listener: onFinishedListener)
    fun marksByCourse(id:String,listener: onFinishedListener)
}