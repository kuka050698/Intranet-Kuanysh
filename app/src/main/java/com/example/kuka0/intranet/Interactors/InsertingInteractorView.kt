package com.example.kuka0.intranet.Interactors

import com.example.kuka0.intranet.Database.Course

interface InsertingInteractorView {
    interface onFinishedListener{
        fun onFailed(exception:Exception)
        fun onSuccess()
        fun showMessage(message:String)
    }
    fun add(course: Course, listener:onFinishedListener )
}