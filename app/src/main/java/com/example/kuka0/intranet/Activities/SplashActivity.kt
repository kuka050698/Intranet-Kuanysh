package com.example.kuka0.intranet.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kuka0.intranet.Common_APP.Common_funs
import com.example.kuka0.intranet.Common_APP.Common_funs.Companion.roleOfUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SplashActivity : AppCompatActivity() {
    private var common = Common_funs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(Common_funs.mAuth.currentUser!=null){
            manageRoles()
            //Thread.sleep(2000)
        }
        else if(Common_funs.mAuth.currentUser==null){
            roleOfUser = "ANON"
            startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
            finish()
        }
    }
    private fun manageRoles() {
        val currentUserId = Common_funs.mAuth.currentUser!!.uid
        var appContext = this
        var studentItemListener: ValueEventListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Common_funs.log(databaseError.toString())
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (student in dataSnapshot.children) {
//                    log(student.value.toString())
//                    log(student.key.toString())
                    if (student.key.toString() == currentUserId) {
                        roleOfUser = "STUDENT"
                        startActivity(Intent(appContext, StudentMainActivity::class.java))
                        return
//                        log("tadam")
                    }
                }
            }
        }
        Common_funs.mDB.child("STUDENTS").addListenerForSingleValueEvent(studentItemListener)

        var teacherItemListener: ValueEventListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Common_funs.log(databaseError.toString())
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (teacher in dataSnapshot.children) {
//                    log(student.value.toString())
//                    log(student.key.toString())
                    if (teacher.key.toString() == currentUserId) {
                        roleOfUser = "TEACHER"
                        startActivity(Intent(appContext,TeacherMainActivity::class.java))
                        finish()
                        return
//                        log("tadam")
                    }
                }
            }
        }
        Common_funs.mDB.child("TEACHERS").addListenerForSingleValueEvent(teacherItemListener)

        var adminItemListener: ValueEventListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Common_funs.log(databaseError.toString())
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (admin in dataSnapshot.children) {
//                    log(student.value.toString())
//                    log(student.key.toString())
                    if (admin.key.toString() == currentUserId) {
                        roleOfUser = "ADMIN"
                        startActivity(Intent(appContext,AdminMainActivity::class.java))
                        finish()
                        return
//                        log("tadam")
                    }
                }
            }
        }
        Common_funs.mDB.child("ADMINS").addListenerForSingleValueEvent(adminItemListener)
    }
}
