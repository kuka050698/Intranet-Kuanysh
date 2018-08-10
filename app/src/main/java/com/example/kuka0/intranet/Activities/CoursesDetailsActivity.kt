package com.example.kuka0.intranet.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kuka0.intranet.R
import com.example.kuka0.intranet.Views.CourseDetailsView

class CoursesDetailsActivity : AppCompatActivity(),CourseDetailsView {
    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

    }

    override fun goToStudentDetails(uuid: String) {
        var intent = Intent(this,StudentCourseListDetailsActitivity::class.java)
    }

    override fun onSuccess() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses_details)

    }
}
