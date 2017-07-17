package com.example.stefano.happypark.MainActivity

import com.example.stefano.happypark.callbacks.OnPictureCallback
import com.example.stefano.happypark.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import javax.inject.Inject

/**
 * Created by stefano on 10/06/17.
 */
class DataReferenceService @Inject constructor(private var auth: FirebaseDatabase, private var fAuth: FirebaseAuth) : MainContract.DatabaseRepository,ValueEventListener {


    var mail:String? = null
    var callback:OnPictureCallback? = null

    override fun getImageProfile(mail: String?, callback: OnPictureCallback) {
        this.mail = mail
        this.callback = callback
       fAuth.currentUser.let {
        val query = auth.reference.child("user")
        query.addValueEventListener(this)
       }
    }
    override fun onCancelled(p0: DatabaseError?) {

    }

    override fun onDataChange(data: DataSnapshot?) {

        data?.children?.forEach {
            child ->run {
            val user = child.getValue(User::class.java)
            if (user.getEmail().equals(mail)) callback?.onPictureLoaded(user.getPhotos(),mail)

        }
       }
    }
}







