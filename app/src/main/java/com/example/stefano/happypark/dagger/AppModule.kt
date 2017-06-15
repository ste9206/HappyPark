package com.example.stefano.happypark.dagger

import android.content.Context
import com.example.stefano.happypark.application.HappyParkApp
import dagger.Module
import dagger.Provides

/**
 * Created by stefano on 08/06/17.
 */
@Module
class AppModule(application: HappyParkApp) {
    var application : HappyParkApp = application

    @Provides
    fun provideContext(): Context = application

}