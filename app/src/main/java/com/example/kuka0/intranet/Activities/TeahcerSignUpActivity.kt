package com.example.kuka0.intranet.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.kuka0.intranet.Interactors.TeacherSignUpInteractor
import com.example.kuka0.intranet.Presenters.TeacherSignUpPresenter
import com.example.kuka0.intranet.R
import com.example.kuka0.intranet.Views.TeacherSignUpView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_student_sign_up.*
import kotlinx.android.synthetic.main.activity_teahcer_sign_up.*

class TeahcerSignUpActivity : AppCompatActivity(),TeacherSignUpView {




    override fun onSuccess() {
        var intent = Intent(this, TeacherMainActivity::class.java)
        intent.putExtra("username", FirebaseAuth.getInstance().currentUser?.email)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    private var mAuth = FirebaseAuth.getInstance()
    lateinit var  registrationPresenter : TeacherSignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teahcer_sign_up)
        mAuth = FirebaseAuth.getInstance()
        registrationPresenter = TeacherSignUpPresenter(this, TeacherSignUpInteractor())

        TeacherSignUpBtn.setOnClickListener{
            registrationPresenter.signUpTeacher(registerUsernameTeacher.text.toString(), registerPasswordTeacher.text.toString(),registerAge?.text.toString(), register_Degree_Teacher.text.toString())
        }
    }
}
