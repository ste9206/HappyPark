package com.example.stefano.happypark.activities.SignUpActivity

import com.example.stefano.happypark.callbacks.OnSignUpCallback
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/**
 * Created by stefano on 13/06/17.
 */
class FirebaseRepository @Inject constructor(private val auth: FirebaseAuth) : SignUpContract.FirebaseRepository {

    override fun signUpWithMailAndPassword(mail: String, password: String, callback: OnSignUpCallback) {
        auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener { task -> checkTask(task,callback)  }
    }

    private fun checkTask(task: Task<AuthResult>, callback: OnSignUpCallback) {
        if(task.isSuccessful)
        {
            val uid = auth.currentUser?.uid
            callback.onCreateNewUserSuccessful(uid)
        }
        else callback.onCreateNewUserError(task.exception?.message)
    }

}