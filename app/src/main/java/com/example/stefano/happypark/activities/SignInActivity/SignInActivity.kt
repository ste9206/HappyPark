package com.example.stefano.happypark.activities.SignInActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.stefano.happypark.MainActivity.MainActivity

import com.example.stefano.happypark.R
import com.example.stefano.happypark.activities.RecoveryPasswordActivity.RecoveryPasswordActivity
import com.example.stefano.happypark.activities.SignUpActivity.SignUpActivity
import com.example.stefano.happypark.application.HappyParkApp
import com.example.stefano.happypark.utils.AlertMessage
import com.example.stefano.happypark.utils.Validator
import kotlinx.android.synthetic.main.activity_sign_in.*
import javax.inject.Inject

class SignInActivity : AppCompatActivity(), SignInContract.View {

    @Inject
    lateinit var presenter:SignInPresenter


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        HappyParkApp.appComponent.inject(this)

        setOnClickListeners()


    }

    private fun setOnClickListeners() {
        signIn.setOnClickListener { presenter.onSignInClicked() }
        lostSignIn.setOnClickListener { presenter.lostPasswordClicked() }
        signUp.setOnClickListener{ presenter.onSignUpClicked()}
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
    override fun checkFields() {

      val username = username.text.toString().trim().takeUnless { username.text.isNullOrEmpty() || !Validator.validateEmailPattern(username.text.toString().trim()) } ?: return presenter.showError("username")
      val password = password.text.toString().trim().takeUnless { password.text.isNullOrEmpty() } ?: return presenter.showError("password")

     presenter.signInWithUsernameAndPassword(username, password)

    }

    override fun openLostPasswordActivity() {
       val intent = Intent(this,RecoveryPasswordActivity::class.java)
       startActivity(intent)
        finish()
    }

    override fun openSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showErrorMessage(s: String) {

        when(s){
            "username" ->  AlertMessage.newAlertErrorMessage("Username is required",this).show()
            "password" ->  AlertMessage.newAlertErrorMessage("Password is required",this).show()
        }
    }

    override fun loginSuccessful() {
     val intent = Intent(this,MainActivity::class.java)
     startActivity(intent)
     finish()

    }

    override fun loginFailed(error: String?) {
     AlertMessage.newAlertErrorMessage(error,this).show()
    }



    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
