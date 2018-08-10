package com.example.kuka0.intranet.Database

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kuka0.intranet.R
import kotlinx.android.synthetic.main.course_item.view.*

class Adapter(private val items : ArrayList<Any>,
              private val context : Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return CourseListViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.course_item, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val course = items.get(p1) as Course
        p0.itemView.tvName.text = course.course_name
        p0.itemView.tvCredits.text = course.course_credits
        p0.itemView.tvYear.text = course.course_year
    }

    class CourseListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}