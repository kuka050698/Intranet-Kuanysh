package com.example.kuka0.intranet.Interactors

import android.util.Log
import com.example.kuka0.intranet.Common_APP.Common_funs.Companion.log
import com.example.kuka0.intranet.Common_APP.Common_funs.Companion.mAuth
import com.example.kuka0.intranet.Common_APP.Common_funs.Companion.mDB
import com.example.kuka0.intranet.Database.Course
import com.example.kuka0.intranet.Database.Marks
import com.example.kuka0.intranet.Database.Student
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ReadItemsInteractor : ReadItemsInteractorView {
    var currentTeacherId = mAuth.currentUser?.uid
    override fun readCourses(listener: ReadItemsInteractorView.onFinishedListener) {
        val courses = ArrayList<Any>()
        val listener: ValueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {
                val items = p0.children.iterator()
                while (items.hasNext()) {
                    val index = items.next()
                    val map = index.value as HashMap<String, String>
                    val course = Course(map.get("name")!!, map.get("description")!!,
                            map.get("year")!!, map.get("credits")!!, map.get("teacher_id")!!)
                    courses.add(course)
                }
                listener.onSuccess(courses)
            }
        }
        mDB.child("Courses").child(currentTeacherId!!).addListenerForSingleValueEvent(listener)

    }

    override fun readStudents(id: String, listener: ReadItemsInteractorView.onFinishedListener) {
        var students: ArrayList<Any> = ArrayList()
        val itemListener: ValueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {
                val items = p0.children.iterator()
                if (!items.hasNext()) listener.onFinish(students)
                while (items.hasNext()) {
                    val index = items.next()
                    val student_id = index.key
                    Log.d("studentId", student_id)
                    val studentItemListener: ValueEventListener = object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {}
                        override fun onDataChange(p0: DataSnapshot) {
                            val map = p0.getValue() as HashMap<String, String>
                            val student = Student(map.get("name")!!, map.get("age")!!, map.get("year")!!)
                            student.uid = p0.key!!
                            students.add(student)
                            listener.onFinish(students)
                        }
                    }
                    mDB.child("Students").child(student_id!!).addListenerForSingleValueEvent(studentItemListener)
                }
            }
        }
        mDB.child("Courses_students").child(id).orderByKey().addListenerForSingleValueEvent(itemListener)
    }
    override fun marksByCourse(id:String,listener: ReadItemsInteractorView.onFinishedListener){
        var marks : ArrayList<Any> = ArrayList()
        val itemListener : ValueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {
                val items = p0.children.iterator()
                if (!items.hasNext()) listener.onFinish(marks)
                while (items.hasNext()) {
                    val index = items.next()
                    var markId = index.key
                    val studentItemListener: ValueEventListener = object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {}
                        override fun onDataChange(p0: DataSnapshot) {
                            val map = p0.getValue() as HashMap<String, String>
                            val mark = Marks()
                            mark.mark_value = map[("value")]!!
                            mark.student_id = map[("student_id")]!!
                            mark.course_id = map[("course_id")]!!
                            mark.uid = map[("uuid")]!!
                            val listener2: ValueEventListener = object : ValueEventListener {
                                override fun onCancelled(p0: DatabaseError) {
                                    log(p0.toString())
                                }

                                override fun onDataChange(p0: DataSnapshot) {
                                    val map_student = p0?.value as HashMap<String, String>
                                    mark.full_name = map_student[("name")]!!
                                }
                            }
                            mDB.child("Students").child(mark.student_id).addListenerForSingleValueEvent(listener2)
                        }
                    }
                    mDB.child("Marks").child(markId!!).addListenerForSingleValueEvent(studentItemListener)
                }
            }
        }
        mDB.child("Courses").child(id).orderByKey().addListenerForSingleValueEvent(itemListener)
    }

}

