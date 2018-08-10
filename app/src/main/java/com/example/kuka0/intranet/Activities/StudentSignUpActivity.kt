package com.example.kuka0.intranet.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.kuka0.intranet.Database.Student
import com.example.kuka0.intranet.Database.Users
import com.example.kuka0.intranet.Interactors.StudentSignUpInteractor
import com.example.kuka0.intranet.Presenters.StudentSignUpPresenter
import com.example.kuka0.intranet.R
import com.example.kuka0.intranet.Views.StudentSignUpView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_student_sign_up.*

class StudentSignUpActivity() : AppCompatActivity(),StudentSignUpView {


    var user: Users? = null
    var student = Student()
    override fun showMessage(message: String) {
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }



    override fun onSuccess() {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("username", FirebaseAuth.getInstance().currentUser?.email)
        startActivity(intent)
    }

    private var TAG = "User"
//    private var username = "kuanish.yessenzhanov@thorntonacademy.org"
//    private var password = "Qwerty123456"
    private lateinit var mAuth:FirebaseAuth
    lateinit var  registrationPresenter : StudentSignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_sign_up)
            mAuth = FirebaseAuth.getInstance()
        registrationPresenter = StudentSignUpPresenter(this, StudentSignUpInteractor())
        StudentSignUpBtn.setOnClickListener {
            registrationPresenter.signUp(registerUsername.text.toString(), registerPassword.text.toString(),registerAge.text.toString())
            //0finish()
        }
    }

}
