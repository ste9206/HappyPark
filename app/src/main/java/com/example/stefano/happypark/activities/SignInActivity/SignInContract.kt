package com.example.stefano.happypark.activities.SignInActivity

import com.example.stefano.happypark.callbacks.OnSignInCallBack

/**
 * Created by stefano on 10/06/17.
 */
interface SignInContract {

    interface Presenter{
       fun setView(view:SignInContract.View?)
        fun onSignInClicked()
        fun lostPasswordClicked()
        fun onSignUpClicked()
        fun onDestroy()
        fun  signInWithUsernameAndPassword(username: String, password: String)
        fun  showError(s: String)
    }

    interface View{
        fun checkFields()
        fun openLostPasswordActivity()
        fun openSignUpActivity()
        fun  showErrorMessage(s: String)
        fun loginSuccessful()
        fun loginFailed(error:String?)

    }

    interface Repository{
        fun  signIn(username: String, password: String, callBack: OnSignInCallBack)

    }
}