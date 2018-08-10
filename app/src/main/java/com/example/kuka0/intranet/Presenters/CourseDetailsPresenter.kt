package com.example.kuka0.intranet.Presenters

import android.widget.Toast
import com.example.kuka0.intranet.Common_APP.Common_funs.Companion.log
import com.example.kuka0.intranet.Database.Student
import com.example.kuka0.intranet.Interactors.CourseDetailsInteractorView
import com.example.kuka0.intranet.Views.CourseDetailsView

class CourseDetailsPresenter(private val view:CourseDetailsView,private val interactor:CourseDetailsInteractorView):CourseDetailsInteractorView.onFinishedListener {
    override fun onSuccess() {
        view.onSuccess()
    }

    override fun onFailed(exception: Exception) {
        log(exception.toString())
    }

    override fun showMessage(message: String) {
        view.showMessage(message)
    }

    override fun onFinish(list: ArrayList<Any>) {

    }
    fun getStudentCourseList(){
        interactor.fetchStudentList(this)
    }
    fun getCourseList(teacher_id:String){
        interactor.fetchCourseList(teacher_id,this)
    }
    fun onItemClick(obj: Any) {
        view.goToStudentDetails((obj as Student).uid)
    }

}