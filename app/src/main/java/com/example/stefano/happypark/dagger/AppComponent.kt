package com.example.stefano.happypark.dagger

import com.example.stefano.happypark.MainActivity.MainActivity
import com.example.stefano.happypark.activities.RecoveryPasswordActivity.RecoveryPasswordActivity
import com.example.stefano.happypark.activities.SignInActivity.SignInActivity
import com.example.stefano.happypark.activities.SignUpActivity.SignUpActivity
import com.example.stefano.happypark.fragments.MapFragment.MapFragment
import dagger.Component

/**
 * Created by stefano on 08/06/17.
 */

@Component (dependencies = arrayOf(FirebaseComponent::class),modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(activity: MainActivity)
    fun  inject(activity: SignInActivity)
    fun  inject(recoveryPasswordActivity: RecoveryPasswordActivity)
    fun  inject(signUpActivity: SignUpActivity) {}
    fun  inject(mapFragment: MapFragment) {}
}