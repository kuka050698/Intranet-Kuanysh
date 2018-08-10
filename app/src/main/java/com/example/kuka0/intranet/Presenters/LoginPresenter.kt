package com.example.kuka0.intranet.Presenters

import android.text.TextUtils
import android.util.Log
import com.example.kuka0.intranet.Common_APP.Common_funs.Companion.log
import com.example.kuka0.intranet.Database.Users
import com.example.kuka0.intranet.Interactors.LoginInteractorView
import com.example.kuka0.intranet.Views.LoginView

class LoginPresenter(private val view:LoginView,private val loginInteractor:LoginInteractorView):LoginInteractorView.onFinishedListener{
    override fun onFailed(exception: Exception) {
        log(exception.toString())
    }

    override fun onSuccess() {
        view.onSuccess()
    }

    override fun showMessage(message: String) {
        view.showMessage(message)
    }
    fun sign_in(username:String,password:String){
        if(validate(username,password)){
            loginInteractor.login(Users(username,password),this)
//            newAdmin = Admin(username)
        }
        else{
            view.showMessage("Kuka Kuma")
        }
    }
    private fun validate(username: String,password: String):Boolean{
        if(TextUtils.isEmpty(username)){
            Log.d("main","$username $password")
            view.showMessage("Enter the name")
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
}