package com.example.kuka0.intranet.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kuka0.intranet.Database.Adapter
import com.example.kuka0.intranet.Interactors.ReadItemsInteractor
import com.example.kuka0.intranet.Presenters.CourseListPresenter
import com.example.kuka0.intranet.R
import com.example.kuka0.intranet.Views.CourseListView
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentListActivity : AppCompatActivity(),CourseListView {
    override fun setAdapter(list: ArrayList<Any>) {
        val adapter = Adapter(list, this)
        student_list__recycler_view.adapter = adapter
    }

    lateinit var presenter : CourseListPresenter
    lateinit var currentStudentUid:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        presenter = CourseListPresenter(this,ReadItemsInteractor())

    }
    private fun readIntent(id:String){
        var intent = intent
        if(intent.hasExtra("id")){
            currentStudentUid = intent.getStringExtra("id")
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.readStudents(currentStudentUid)
    }

}
