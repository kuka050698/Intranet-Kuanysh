package com.example.kuka0.intranet.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.kuka0.intranet.Database.Adapter
import com.example.kuka0.intranet.Interactors.ReadItemsInteractor
import com.example.kuka0.intranet.Presenters.CourseListPresenter
import com.example.kuka0.intranet.R
import com.example.kuka0.intranet.Views.CourseListView
import kotlinx.android.synthetic.main.activity_teachers_course_list.*

class TeachersCourseListActivity : AppCompatActivity(), CourseListView {

    lateinit var presenter : CourseListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teachers_course_list)
        presenter = CourseListPresenter(this, ReadItemsInteractor())
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        presenter = CourseListPresenter(this, ReadItemsInteractor())
//        presenter.readCoursesByTeacher()
    }

    override fun setAdapter(list: ArrayList<Any>) {
        val adapter = Adapter(list, this)
        recyclerView.adapter = adapter
    }
}
