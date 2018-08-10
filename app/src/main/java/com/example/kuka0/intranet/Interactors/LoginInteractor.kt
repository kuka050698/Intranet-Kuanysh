package com.example.kuka0.intranet.Interactors

import com.example.kuka0.intranet.Database.Users
import com.google.firebase.auth.FirebaseAuth

class LoginInteractor:LoginInteractorView {
    override fun logout() {
        mAuth.signOut()
    }

    private var mAuth = FirebaseAuth.getInstance()
    override fun login(user: Users, listener: LoginInteractorView.onFinishedListener) {
        mAuth.signInWithEmailAndPassword(user.name,user.surname)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        listener.onSuccess()
                    }
                    else{
                        listener.onFailed(it.exception!!)
                    }
                }

    }
}