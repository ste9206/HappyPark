package com.example.stefano.happypark.dagger

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides

/**
 * Created by stefano on 08/06/17.
 */
@Module
class FirebaseModule {

    @Provides
    fun provideAuth():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideDatabase():FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }

    @Provides
    fun provideStorage():FirebaseStorage{
        return FirebaseStorage.getInstance()
    }
}