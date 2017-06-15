package com.example.stefano.happypark.activities.SignUpActivity

import android.net.Uri
import com.example.stefano.happypark.callbacks.OnSignUpCallback
import com.example.stefano.happypark.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class DatabaseRepository @Inject constructor(val auth: FirebaseDatabase, val authent:FirebaseAuth) : SignUpContract.DatabaseRepository {

    override fun saveUserExtraInformations(uid: String?, name: String?, surname: String?, data: String?, telefono: String?, url: Uri?, mail:String?, callback: OnSignUpCallback) {

     authent.currentUser.let {

     val reference = auth.reference
     val user = User(uid,telefono,url.toString(),data,mail,surname,name)

     reference.child("user").child(uid).setValue(user).addOnCompleteListener { task -> checkTask(task,callback)  }
     }
    }

     private fun  checkTask(task: Task<Void>, callback: OnSignUpCallback) {
         when{
             task.isSuccessful -> run {
                 callback.onInformationUserSuccessful()
                 authent.signOut()
             }
             else ->callback.onInformationUserError(task.exception?.message)
         }

    }
}