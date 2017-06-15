package com.example.stefano.happypark.activities.SignUpActivity

import android.net.Uri
import com.example.stefano.happypark.callbacks.OnSignUpCallback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import javax.inject.Inject


/**
 * Created by stefano on 13/06/17.
 */
class StorageRepository @Inject constructor(private val auth: FirebaseStorage, private var authent:FirebaseAuth) :SignUpContract.StorageRepository{

    override fun savePicture(path: String?, uid: String?, callback: OnSignUpCallback) {
        val user = authent.currentUser

        user.let {
            val storage: StorageReference = auth.getReferenceFromUrl("gs://happypark-170613.appspot.com")
            val uriPath = Uri.parse(path)
            storage.child(uid!!).child("ImgProfile").putFile(uriPath)
                    .addOnSuccessListener { task -> callback.onUploadComplete(uid, task.downloadUrl) }
                    .addOnFailureListener { exception -> callback.onUploadError(exception.message) }
        }
    }
}