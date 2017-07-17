package com.example.stefano.happypark.dagger

import com.example.stefano.happypark.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by stefano on 16/06/17.
 */
@Module
class RetrofitModule {

    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }
}