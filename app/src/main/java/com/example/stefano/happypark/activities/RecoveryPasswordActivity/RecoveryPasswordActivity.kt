package com.example.stefano.happypark.activities.RecoveryPasswordActivity

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.stefano.happypark.R
import com.example.stefano.happypark.activities.SignInActivity.SignInActivity
import com.example.stefano.happypark.application.HappyParkApp
import com.example.stefano.happypark.utils.AlertMessage
import com.example.stefano.happypark.utils.Validator
import kotlinx.android.synthetic.main.activity_recovery_password.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import javax.inject.Inject

class RecoveryPasswordActivity : AppCompatActivity(), RecoveryPasswordContract.View,DialogInterface.OnClickListener {

    @Inject
    lateinit var presenter:RecoveryPasswordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery_password)

        HappyParkApp.appComponent.inject(this)

        sendMail.setOnClickListener { presenter.onButtonClicked() }

    }

    override fun recoverySuccessful() {
      AlertMessage.newAlertMessageListener("Success","Now you receive a mail at your address",this,this).show()
    }

    override fun recoveryError(error: String?) {
       AlertMessage.newAlertErrorMessage(error,this).show()
    }


    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onBackPressed() {
        presenter.onBackPressedClicked()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        presenter.onSuccessfulDialogClicked()
    }

    override fun checkField() {

        val mail = usernameRecovery.text.toString().trim().takeUnless { usernameRecovery.text.isNullOrEmpty() || !Validator.validateEmailPattern(usernameRecovery.text.toString().trim()) } ?: return presenter.showError()

        presenter.onFieldVerified(mail)
    }

    override fun showErrorMessage(message: String) {
        AlertMessage.newAlertErrorMessage(message,this).show()
    }

    override fun openSignInActivity() {
      val intent = Intent(this,SignInActivity::class.java)
      startActivity(intent)
      finish()
    }
}
