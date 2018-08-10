package com.example.kuka0.intranet.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.kuka0.intranet.Interactors.LoginInteractor
import com.example.kuka0.intranet.Presenters.LoginPresenter
import com.example.kuka0.intranet.R
import com.example.kuka0.intranet.R.id.*
import com.example.kuka0.intranet.Views.LoginView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),LoginView {
    override fun onSuccess() {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("username", FirebaseAuth.getInstance().currentUser?.email)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()

    }
    private var mAuth = FirebaseAuth.getInstance()
    lateinit var loginPresenter:LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginPresenter = LoginPresenter(this, LoginInteractor())
        login_button_action.setOnClickListener{
            loginPresenter.sign_in(loginUsernameUser.text.toString(),loginPasswordUser.text.toString())

        }

        StudentSignUp.setOnClickListener{
            startActivity(Intent(this@LoginActivity,StudentSignUpActivity::class.java))
        }
        TeacherSignUp.setOnClickListener{
            startActivity(Intent(this@LoginActivity,TeahcerSignUpActivity::class.java))
        }
        AdminSignUp.setOnClickListener{
            startActivity(Intent(this@LoginActivity,AdminSignUpActivity::class.java))
        }


    }
}
