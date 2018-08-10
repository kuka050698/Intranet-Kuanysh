package com.example.kuka0.intranet.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kuka0.intranet.Presenters.CourseListPresenter
import com.example.kuka0.intranet.R

class MyMarksActivity : AppCompatActivity() {
    lateinit var presenter:CourseListPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_marks)
    }
}
