package com.example.stefano.happypark.application

import android.app.Application
import com.example.stefano.happypark.dagger.*

/**
 * Created by stefano on 08/06/17.
 */
class HappyParkApp: Application() {


    companion object{

      lateinit var appComponent: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        createAppComponent()
    }

    fun createAppComponent(): AppComponent
    {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .firebaseComponent(createFirebaseComponent()).build()

        return appComponent!!

    }

    fun createFirebaseComponent():FirebaseComponent
    {
        return DaggerFirebaseComponent
                .builder()
                .firebaseModule(FirebaseModule())
                .build()
    }
}