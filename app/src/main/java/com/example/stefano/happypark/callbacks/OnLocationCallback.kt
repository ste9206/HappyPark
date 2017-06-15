package com.example.stefano.happypark.callbacks

import com.example.stefano.happypark.models.Location


/**
 * Created by stefano on 15/06/17.
 */
interface OnLocationCallback {

    fun onLocationError(e:String?)
    fun onLocationLoaded(location: Location)
}