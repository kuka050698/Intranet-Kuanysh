package com.example.kuka0.intranet.Interactors

import com.example.kuka0.intranet.Common_APP.Common_funs.Companion.log
import com.example.kuka0.intranet.Common_APP.Common_funs.Companion.mAuth
import com.example.kuka0.intranet.Common_APP.Common_funs.Companion.mDB
import com.example.kuka0.intranet.Database.Course
import com.example.kuka0.intranet.Database.Student
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CourseDetailsInteractor:CourseDetailsInteractorView {
    override fun fetchCourseList(teacher_id: String, listener: CourseDetailsInteractorView.onFinishedListener) {
        var courses: ArrayList<Any> = ArrayList()
        var currentTeacherId = mAuth.currentUser?.uid
        if (currentTeacherId == teacher_id) {
            mDB.child("Courses").child(teacher_id).orderByKey().addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    log(p0.toString())
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val map = p0.getValue() as HashMap<String, String>
                    val course = Course(map["course_name"]!!, map["course_description"]!!, map["course_year"]!!,
                            map["course_credits"]!!, map["teacher_id"]!!)
                    course.uid = p0.key!!
                    courses.add(course)
                }
            })

        }

    }

    override fun fetchStudentList(listener:CourseDetailsInteractorView.onFinishedListener ) {
        var students : ArrayList<Any> = ArrayList()
        var itemListener : ValueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {
                val items = p0.children.iterator()
                while (items.hasNext()) {
                    val index = items.next()
                    val map = index.value as HashMap<String, String>
                    val student = Student(map["name"]!!, map.get("age")!!)
                    student.uid = index.key!!
                    students.add(student)
                }
                listener.onFinish(students)
            }
        }
        mDB.child("Students").orderByKey().addListenerForSingleValueEvent(itemListener)

    }
}

