package com.example.stefano.happypark.callbacks

/**
 * Created by stefano on 12/06/17.
 */
interface OnSignInCallBack {
    fun onLoginSuccessful()
    fun onLoginFailed(error: String?)
}