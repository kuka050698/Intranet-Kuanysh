package com.example.kuka0.intranet.Common_APP

import android.app.Application
import android.util.Log
import com.example.kuka0.intranet.Database.Course
import com.example.kuka0.intranet.Database.Teacher
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Common_funs():Application() {
    companion object {
         var mDB = FirebaseDatabase.getInstance().reference
         var mAuth = FirebaseAuth.getInstance()
         var roleOfUser = "ANONYM"

        fun log(message: String) {
            Log.d("accepted", message)
        }
    }
    override fun onCreate() {
        super.onCreate()
        log("App is loading")
        mAuth = FirebaseAuth.getInstance()
        mDB = FirebaseDatabase.getInstance().reference

        //if(firebaseAuth.currentUser != null)
    }

//    private fun getRoleOfUser(): String {
//        val currentUserId = mAuth.currentUser!!.uid
//
//        var role = "anon"
//
//
//        var studentItemListener: ValueEventListener = object : ValueEventListener {
//            override fun onCancelled(databaseError: DatabaseError) {
//                log(databaseError.toString())
//            }
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                for (student in dataSnapshot.children) {
////                    log(student.value.toString())
////                    log(student.key.toString())
//                    if (student.key.toString() == currentUserId) {
//                        role = "STUDENT"
////                        log("tadam")
//                    }
//                }
//            }
//        }
//        mDB.child("STUDENTS").addListenerForSingleValueEvent(studentItemListener)
//
//        var teacherItemListener: ValueEventListener = object : ValueEventListener {
//            override fun onCancelled(databaseError: DatabaseError) {
//                log(databaseError.toString())
//            }
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                for (teacher in dataSnapshot.children) {
////                    log(student.value.toString())
////                    log(student.key.toString())
//                    if (teacher.key.toString() == currentUserId) {
//                        role = "TEACHER"
////                        log("tadam")
//                    }
//                }
//            }
//        }
//        mDB.child("TEACHERS").addListenerForSingleValueEvent(teacherItemListener)
//
//        var adminItemListener: ValueEventListener = object : ValueEventListener {
//            override fun onCancelled(databaseError: DatabaseError) {
//                log(databaseError.toString())
//            }
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                for (admin in dataSnapshot.children) {
////                    log(student.value.toString())
////                    log(student.key.toString())
//                    if (admin.key.toString() == currentUserId) {
//                        role = "ADMIN"
////                        log("tadam")
//                    }
//                }
//            }
//        }
//        mDB.child("ADMINS").addListenerForSingleValueEvent(adminItemListener)
//        return role
//    }

}