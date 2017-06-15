package com.example.stefano.happypark.activities.SignInActivity

import com.example.stefano.happypark.callbacks.OnSignInCallBack
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/**
 * Created by stefano on 12/06/17.
 */
class SignInRepository @Inject constructor(var auth: FirebaseAuth) :SignInContract.Repository {

    override fun signIn(username: String, password: String,callback:OnSignInCallBack) {
       auth.signInWithEmailAndPassword(username,password).addOnCompleteListener { task -> verifySuccessfulTask(task,callback) }
    }

    private fun  verifySuccessfulTask(task: Task<AuthResult>, callback: OnSignInCallBack) {

        if (task.isSuccessful)
         callback.onLoginSuccessful()
        else
         callback.onLoginFailed(task.exception?.message)
    }


}