package com.example.kuka0.intranet.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.kuka0.intranet.Interactors.AdminSignUpInteractor
import com.example.kuka0.intranet.Presenters.AdminSignUpPresenter
import com.example.kuka0.intranet.R
import com.example.kuka0.intranet.Views.AdminSignIUpView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_admin_sign_up.*

class AdminSignUpActivity : AppCompatActivity(),AdminSignIUpView {

    override fun onSuccess() {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("username", FirebaseAuth.getInstance().currentUser?.email)
        startActivity(intent)

    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    private var mAuth = FirebaseAuth.getInstance()
    lateinit var  registrationPresenter : AdminSignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_sign_up)
        mAuth = FirebaseAuth.getInstance()
        registrationPresenter = AdminSignUpPresenter(this, AdminSignUpInteractor())

        AdminSignUpBtn.setOnClickListener{
            registrationPresenter.signUpAdmin(registerUsernameAdmin.text.toString(),registerPasswordAdmin.text.toString(),
                    register_verifying_code_Admin.text.toString())

        }
    }

}
