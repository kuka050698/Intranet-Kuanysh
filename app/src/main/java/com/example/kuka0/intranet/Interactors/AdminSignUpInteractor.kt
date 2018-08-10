package com.example.kuka0.intranet.Interactors

import android.util.Log
import com.example.kuka0.intranet.Database.Admin
import com.example.kuka0.intranet.Database.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AdminSignUpInteractor:AdminSignUpInteractorView { private var TAG = "New Admin"
    private var currentAuthorizingUserUID = "currentUserAuthId"
    private var mDb = FirebaseDatabase.getInstance().reference
    private var mAuth = FirebaseAuth.getInstance()

    override fun register(user: Users, listener: AdminSignUpInteractorView.onFinishedListener) {
        mAuth.createUserWithEmailAndPassword(user.name,user.surname)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        currentAuthorizingUserUID = it.result.user.uid
                        listener.onSuccess()
                        Log.d(TAG,"Signed Up")

                    }
                    else {
                        listener.onFailed(it.exception!!)
                        Log.d(TAG,"failed")
                    }
                }

    }

    override fun logout() {
        mAuth.signOut()
    }

    override fun saveSignedUpAdmini(admin: Admin) {
        Log.d(TAG,"new teacher signed up and saving")
        mDb.child("ADMINS").setValue(admin)
    }
}