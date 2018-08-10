package com.example.kuka0.intranet.Views

interface InsertingCourseView {
    fun onSuccess()
    fun showMessage(message: String)
    fun onFailed(exception: Exception)
    fun goToCourses()

}