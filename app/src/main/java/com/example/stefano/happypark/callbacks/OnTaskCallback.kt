package com.example.stefano.happypark.callbacks

/**
 * Created by stefano on 13/06/17.
 */
interface OnTaskCallback {

    fun onTaskSuccessful()
    fun onTaskError(error:String?)
}