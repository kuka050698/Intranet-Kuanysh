package com.example.kuka0.intranet.Presenters

import android.text.TextUtils
import android.util.Log
import com.example.kuka0.intranet.Database.Student
import com.example.kuka0.intranet.Database.Users
import com.example.kuka0.intranet.Interactors.StudentSignUpInteractorView
import com.example.kuka0.intranet.Views.StudentSignUpView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StudentSignUpPresenter(private val view: StudentSignUpView, private val studentSignUpInteractor: StudentSignUpInteractorView) : StudentSignUpInteractorView.onFinishedListener {
    private var mDB = FirebaseDatabase.getInstance().reference
    private var mAuth = FirebaseAuth.getInstance()
    override fun onFailed(exception: Exception) {
        when (exception) {
            is FirebaseAuthInvalidCredentialsException -> view.showMessage("Incorrect username or password")
            is FirebaseAuthUserCollisionException -> view.showMessage("Such user is already exist!")
            else -> view.showMessage(exception.message.toString())
        }
    }

    private var newStudent = Student()
    override fun showMessage(message: String) {
        view.showMessage(message)
    }


    override fun onSuccess() {
        studentSignUpInteractor.saveSignedUpStudent(newStudent)
        view.showMessage("Database is added")
        view.onSuccess()
    }


    fun signUp(username: String, password: String, age: String) {
        if (validate(username, password, age)) {
            studentSignUpInteractor.register(Users(username, password), this)
            newStudent = Student(username, age)
        } else {
            view.showMessage("Kuka Kuma")
        }
    }

    private fun validate(username: String, password: String, age: String): Boolean {
        if (TextUtils.isEmpty(username)) {
            Log.d("main", "$username $password")
            view.showMessage("Enter the name")
            return false
        }
        if (TextUtils.isEmpty(age)) {
            Log.d("main", "$username $password")
            view.showMessage("Enter the age")
            return false
        }
        if (TextUtils.isEmpty(password)) {
            Log.d("main", "$username $password")
            view.showMessage("Enter the password")
            return false
        }
        if (!standards(password)) {
            return false
        }
        return true
    }

    private fun standards(password: String): Boolean {
        var uppercase = 0
        var number = 0
        for (it in password) {
            if (it in 'A'..'Z') {
                uppercase++
            } else if (it in '0'..'9') {
                number++
            }
        }
        if (password.length < 8) {
            Log.d("password standards", "$password")
            view.showMessage("Password length must be at least 8")
            return false
        }
        if (uppercase == 0) {
            view.showMessage("Password should contain at least 1 uppercase letter")
            return false
        }
        if (number == 0) {
            view.showMessage("Password should contain at least 1 number")
            return false
        }
        return true

    }

    private fun getRole(): String {
        val currentUserId = mAuth.currentUser!!.uid

        var role = "anon"


        var studentItemListener: ValueEventListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (student in dataSnapshot.children) {
//                    log(student.value.toString())
//                    log(student.key.toString())
                    if (student.key.toString() == currentUserId) {
                        role = "STUDENT"
//                        log("tadam")
                    }
                }
            }
        }
        mDB.child("STUDENTS").addListenerForSingleValueEvent(studentItemListener)
        return role

    }
}