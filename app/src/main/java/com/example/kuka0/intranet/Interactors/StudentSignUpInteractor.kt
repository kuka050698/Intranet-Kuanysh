package com.example.kuka0.intranet.Interactors

import android.util.Log
import com.example.kuka0.intranet.Database.Student
import com.example.kuka0.intranet.Database.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class StudentSignUpInteractor:StudentSignUpInteractorView {
    private var currentAuthorizingUserUID = "currentUserAuthId"
    private var mDb = FirebaseDatabase.getInstance().reference
    override fun saveSignedUpStudent(student: Student) {
        Log.d(TAG,"new student signed up and saving")
        mDb.child("Students").child(currentAuthorizingUserUID).setValue(student)
    }

    override fun logout() {
        mAuth.signOut()
    }

    private  var mAuth = FirebaseAuth.getInstance()

    private var TAG = "New Student"
    override fun register(user: Users, listener: StudentSignUpInteractorView.onFinishedListener) {
        listener.showMessage("Vot temaa")
        mAuth.createUserWithEmailAndPassword(user.name,user.surname)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        currentAuthorizingUserUID = it.result.user.uid
                        listener.onSuccess()
                        var uid :String?=null
                        uid = mAuth.currentUser?.uid
                        Log.d(TAG,uid)

                    }
                    else {
                        listener.onFailed(it.exception!!)
                        Log.d(TAG,"failed")
                    }
                }




    }







}