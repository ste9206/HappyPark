package com.example.stefano.happypark.activities.SignInActivity

import android.widget.Toast
import com.example.stefano.happypark.MainActivity.MainContract
import com.example.stefano.happypark.callbacks.OnSignInCallBack
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/**
 * Created by stefano on 11/06/17.
 */
class SignInPresenter @Inject constructor(var repository:SignInRepository): SignInContract.Presenter, OnSignInCallBack {

    override fun showError(s: String) {
      contractView?.showErrorMessage(s)
    }


    override fun signInWithUsernameAndPassword(username: String, password: String) {

        repository.signIn(username,password,this)
    }


    var contractView: SignInContract.View? = null

    override fun onSignInClicked() {
        contractView?.checkFields()
    }

    override fun lostPasswordClicked() {
      contractView?.openLostPasswordActivity()
    }

    override fun onSignUpClicked() {
        contractView?.openSignUpActivity()
    }

    override fun setView(view: SignInContract.View?) {
        this.contractView = view
    }

    override fun onDestroy() {
        contractView = null
    }

    override fun onLoginSuccessful() {
        contractView?.loginSuccessful()
    }

    override fun onLoginFailed(error:String?) {
       contractView?.loginFailed(error)
    }

}