package com.example.stefano.happypark.MainActivity

import com.example.stefano.happypark.models.AuthUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

/**
 * Created by stefano on 09/06/17.
 */
class FirebaseService @Inject constructor(private var auth: FirebaseAuth) : MainContract.Repository {

    override fun getCurrentUser(): AuthUser? {

        val user: FirebaseUser? = auth.currentUser

        user?.let {
            val authUser: AuthUser = AuthUser(user.email, user.displayName)
            return authUser
        } ?: run {
            return null
        }
    }
    override fun logOut() {
      auth.signOut()
    }


}