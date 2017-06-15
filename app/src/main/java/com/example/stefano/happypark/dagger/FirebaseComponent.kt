package com.example.stefano.happypark.dagger

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Component

/**
 * Created by stefano on 09/06/17.
 */
@Component(modules = arrayOf(FirebaseModule::class))
interface FirebaseComponent {

    fun auth():FirebaseAuth
    fun data():FirebaseDatabase
    fun storage():FirebaseStorage
}