package com.example.kuka0.intranet.Presenters

import android.text.TextUtils
import android.util.Log
import com.example.kuka0.intranet.Database.Admin
import com.example.kuka0.intranet.Database.Users
import com.example.kuka0.intranet.Interactors.AdminSignUpInteractorView
import com.example.kuka0.intranet.Views.AdminSignIUpView
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class AdminSignUpPresenter(private val view: AdminSignIUpView, private val adminSignUpInteractor: AdminSignUpInteractorView): AdminSignUpInteractorView.onFinishedListener {
    override fun onFailed(exception: Exception) {
        when (exception) {
            is FirebaseAuthInvalidCredentialsException -> view.showMessage("Incorrect username or password")
            is FirebaseAuthUserCollisionException -> view.showMessage("Such user is already exist!")
            else -> view.showMessage(exception.message.toString())
        }
    }

    private var newAdmin = Admin()





    override fun onSuccess() {
        adminSignUpInteractor.saveSignedUpAdmini(newAdmin)
        view.showMessage("Admin is added to database")
        view.onSuccess()
    }

    override fun showMessage(message: String) {
        view.showMessage(message)
    }
    fun signUpAdmin(username:String,password:String,admin_code:String) {
        if(validate(username,password,admin_code)){
            adminSignUpInteractor.register(Users(username,password),this)
            newAdmin = Admin(username,admin_code)
        }
        else{
            view.showMessage("Kuka Kuma")
        }
    }
    private fun validate(username: String,password: String,admin_code:String):Boolean{
        if(TextUtils.isEmpty(username)){
            Log.d("main","$username $password")
            view.showMessage("Enter the name")
            return false
        }
        if(TextUtils.isEmpty(admin_code)){
            Log.d("main","$username $password")
            view.showMessage("Enter the age")
            return false
        }
        if(TextUtils.isEmpty(password) ){
            Log.d("main","$username $password")
            view.showMessage("Enter the password")
            return false
        }
        if(!standards(password)){
            return false
        }
        return true
    }
    private fun standards(password: String):Boolean{
        var uppercase=0
        var number=0
        for(it in password){
            if(it in 'A'..'Z'){
                uppercase++
            }
            else if(it in '0'..'9'){
                number++
            }
        }
        if(password.length<8){
            Log.d("password standards","$password")
            view.showMessage("Password length must be at least 8")
            return false
        }
        if(uppercase==0){
            view.showMessage("Password should contain at least 1 uppercase letter")
            return false
        }
        if(number==0){
            view.showMessage("Password should contain at least 1 number")
            return false
        }
        return true

    }
    private fun performSavingMode(admin: Admin){
        adminSignUpInteractor.saveSignedUpAdmini(admin)
    }

}