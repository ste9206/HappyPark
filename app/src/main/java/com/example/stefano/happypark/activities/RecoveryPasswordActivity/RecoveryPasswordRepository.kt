package com.example.stefano.happypark.activities.RecoveryPasswordActivity

import com.example.stefano.happypark.callbacks.OnTaskCallback
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/**
 * Created by stefano on 13/06/17.
 */
class RecoveryPasswordRepository @Inject constructor(private var auth: FirebaseAuth): RecoveryPasswordContract.Repository {


    override fun resetPassword(mail: String,callback:OnTaskCallback) {
     auth.sendPasswordResetEmail(mail).addOnCompleteListener { task -> checkTask(task,callback) }

    }

    private fun  checkTask(task: Task<Void>,callback: OnTaskCallback) {
        if (task.isSuccessful)
         callback.onTaskSuccessful()
        else
         callback.onTaskError(task.exception?.message)
    }

}