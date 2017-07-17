package com.example.stefano.happypark.callbacks

import com.example.stefano.happypark.gson.Places

/**
 * Created by stefano on 16/06/17.
 */
interface OnPlacesCallback {

    fun onComplete(totalPlaces: ArrayList<String>)
    fun onError(error:String?)
}