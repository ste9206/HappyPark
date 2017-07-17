package com.example.stefano.happypark.fragments.AddWithTextFragment


import com.example.stefano.happypark.gson.Places
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by stefano on 16/06/17.
 */
interface APIService {

    @GET("json")
    fun getPlaces(@Query("key") API: String,
                  @Query("types") address: String,
                  @Query("language") language: String,
                  @Query("components") component: String,
                  @Query("input") input: String): Observable<Places>
}