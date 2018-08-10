package com.example.kuka0.intranet.Interactors

import com.example.kuka0.intranet.Database.Teacher
import com.example.kuka0.intranet.Database.Users

interface TeacherSignUpInteractorView {
    interface onFinishedListener{
        fun onFailed(exception: Exception)
        fun onSuccess()
        fun showMessage(message: String)
    }
    fun register(user: Users, listener: onFinishedListener)
    fun logout()
    fun saveSignedUpTeacher(teacher: Teacher)

}