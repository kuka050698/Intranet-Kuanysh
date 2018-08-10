package com.example.kuka0.intranet.Interactors

import com.example.kuka0.intranet.Database.Users

interface LoginInteractorView {
    interface onFinishedListener{
        fun onFailed(exception:Exception)
        fun onSuccess()
        fun showMessage(message:String)
    }
    fun login(user: Users,listener:onFinishedListener )
    fun logout()
}