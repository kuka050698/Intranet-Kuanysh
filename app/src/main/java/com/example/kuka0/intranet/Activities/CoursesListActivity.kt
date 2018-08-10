package com.example.kuka0.intranet.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.kuka0.intranet.Database.Adapter
import com.example.kuka0.intranet.Interactors.ReadItemsInteractor
import com.example.kuka0.intranet.Presenters.CourseListPresenter
import com.example.kuka0.intranet.R
import com.example.kuka0.intranet.R.id.courses_list_recycler_view
import com.example.kuka0.intranet.Views.CourseListView
import kotlinx.android.synthetic.main.activity_courses_list.*

class CoursesListActivity : AppCompatActivity(), CourseListView {

    lateinit var presenter : CourseListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses_list)
        presenter = CourseListPresenter(this, ReadItemsInteractor())
        courses_list_recycler_view.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.readCourses ()
    }

    override fun setAdapter(list: ArrayList<Any>) {
        val adapter = Adapter(list, this)
        courses_list_recycler_view.adapter = adapter
    }
    private fun StudentList(id:String){
        var intent = Intent(this,StudentListActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)

    }
}
