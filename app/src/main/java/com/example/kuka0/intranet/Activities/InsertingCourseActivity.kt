package com.example.kuka0.intranet.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.kuka0.intranet.Common_APP.Common_funs.Companion.mAuth
import com.example.kuka0.intranet.Interactors.InsertingCourseInteractor
import com.example.kuka0.intranet.Presenters.InseringCoursePresenter
import com.example.kuka0.intranet.R
import com.example.kuka0.intranet.Views.InsertingCourseView
import kotlinx.android.synthetic.main.activity_inserting_course.*

class InsertingCourseActivity : AppCompatActivity(),InsertingCourseView {
    override fun goToCourses() {
        startActivity(Intent(this@InsertingCourseActivity,CoursesListActivity::class.java))
    }

    private lateinit var insertingCoursePresenter:InseringCoursePresenter
    override fun onSuccess() {
        var currentTeacheruid = mAuth.currentUser!!.uid
        var intent = Intent(this,TeacherMainActivity::class.java)
        intent.putExtra("currentteacher",currentTeacheruid)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun onFailed(exception: Exception) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inserting_course)
        insertingCoursePresenter = InseringCoursePresenter(this,InsertingCourseInteractor())
        insert_Course_Button.setOnClickListener{
            insertingCoursePresenter.insert(course_Full_Name.text.toString(),course_description.text.toString(),
                    course_year.text.toString(),course_credit_number.text.toString())
            Log.d("Course","is ADDED")
            onSuccess()
            startActivity(Intent(this, TeacherMainActivity::class.java))
        }
    }
}
