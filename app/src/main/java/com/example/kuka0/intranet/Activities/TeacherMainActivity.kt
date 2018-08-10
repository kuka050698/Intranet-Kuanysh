package com.example.kuka0.intranet.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.kuka0.intranet.Common_APP.Common_funs.Companion.mAuth
import com.example.kuka0.intranet.Database.Adapter
import com.example.kuka0.intranet.Interactors.CourseDetailsInteractor
import com.example.kuka0.intranet.Interactors.ReadItemsInteractor
import com.example.kuka0.intranet.Presenters.CourseDetailsPresenter
import com.example.kuka0.intranet.Presenters.CourseListPresenter
import com.example.kuka0.intranet.R
import com.example.kuka0.intranet.Views.CourseDetailsView
import com.example.kuka0.intranet.Views.CourseListView
import kotlinx.android.synthetic.main.activity_courses_list.*
import kotlinx.android.synthetic.main.activity_teacher_main.*

class TeacherMainActivity : AppCompatActivity(), CourseListView,CourseDetailsView {
    override fun setAdapter(list: ArrayList<Any>) {
        val adapter = Adapter(list, this)
        teacher_main_recycler_view.adapter = adapter
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun goToStudentDetails(uuid: String) {

    }

    override fun onSuccess() {

    }

    lateinit var presenter:CourseListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        var currentTeacherId = mAuth.currentUser?.uid
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_main)
        presenter = CourseListPresenter(this,ReadItemsInteractor())
        teacher_main_recycler_view.layoutManager = LinearLayoutManager(this)
        add_course_teacher_main.setOnClickListener{
            startActivity(Intent(this,InsertingCourseActivity::class.java))
        }
        intent_uid.text = currentTeacherId
    }

    override fun onResume() {
        super.onResume()
        var teacher_id:String?=null
        presenter = CourseListPresenter(this,ReadItemsInteractor())
        presenter.readCourses ()
        var intent = intent
        if(intent.hasExtra("teacher_id")){
            teacher_id = intent.getStringExtra("teacher_id")
        }
    }

}
