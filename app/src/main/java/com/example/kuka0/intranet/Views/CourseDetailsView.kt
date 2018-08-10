package com.example.kuka0.intranet.Views

import java.security.MessageDigest

interface CourseDetailsView {
    fun showMessage(message:String)
    fun goToStudentDetails(uuid: String)
    fun onSuccess()
}