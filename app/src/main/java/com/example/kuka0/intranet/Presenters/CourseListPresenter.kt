package com.example.kuka0.intranet.Presenters

import com.example.kuka0.intranet.Interactors.ReadItemsInteractorView
import com.example.kuka0.intranet.Views.CourseListView

class CourseListPresenter(private val view : CourseListView,
                          private val interactor : ReadItemsInteractorView)
    :ReadItemsInteractorView.onFinishedListener{
    override fun onFinish(list: ArrayList<Any>) {

    }

    override fun onSuccess(list: ArrayList<Any>) {
        view.setAdapter (list)
    }

    fun readCourses() {
        interactor.readCourses(this)
    }
    fun readStudents(id:String){
        interactor.readStudents(id,this)
    }


}