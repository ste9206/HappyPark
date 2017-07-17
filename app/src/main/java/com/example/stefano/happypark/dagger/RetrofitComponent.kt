package com.example.stefano.happypark.dagger

import dagger.Component
import retrofit2.Retrofit

/**
 * Created by stefano on 16/06/17.
 */
@Component(modules = arrayOf(RetrofitModule::class))
interface RetrofitComponent {
    fun retrofit():Retrofit
}