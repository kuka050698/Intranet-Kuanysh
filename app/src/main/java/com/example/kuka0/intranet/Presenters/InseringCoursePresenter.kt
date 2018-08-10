package com.example.kuka0.intranet.Presenters

import android.text.TextUtils
import com.example.kuka0.intranet.Common_APP.Common_funs
import com.example.kuka0.intranet.Database.Course
import com.example.kuka0.intranet.Interactors.InsertingInteractorView
import com.example.kuka0.intranet.Views.InsertingCourseView

class InseringCoursePresenter(private val view:InsertingCourseView,private val insertingCourseInteractor:InsertingInteractorView):InsertingInteractorView.onFinishedListener {

    override fun onFailed(exception: Exception) {
        view.onFailed(exception)
    }

    override fun onSuccess() {
        view.onSuccess()
    }

    override fun showMessage(message: String) {
        view.showMessage(message)
    }

    fun insert(course_name:String,course_description:String,course_year:String,course_credits:String){
        if(validate(course_name,course_description,course_year,course_credits)){
            insertingCourseInteractor.add(Course(course_name,course_description,course_year,course_credits),this)
            view.goToCourses()
        }
    }

    private fun validate(course_name: String,course_description: String,course_year:String,course_credits:String):Boolean{
        if(TextUtils.isEmpty(course_name)){
            view.showMessage("Enter the course name")
            return false
        }
        if(TextUtils.isEmpty(course_year)){
            view.showMessage("Enter the course year")
            return false
        }
        if(TextUtils.isEmpty(course_credits) ){
            view.showMessage("Enter the course credits")
            return false
        }
        if(TextUtils.isEmpty(course_description)){
            view.showMessage("Enter the course description")
            return false
        }
        return true
    }
}