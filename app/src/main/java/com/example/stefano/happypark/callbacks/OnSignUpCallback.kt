package com.example.stefano.happypark.callbacks

import android.net.Uri

/**
 * Created by stefano on 14/06/17.
 */
interface OnSignUpCallback {

    fun onCreateNewUserSuccessful(uid:String?)
    fun onCreateNewUserError(e:String?)
    fun onUploadComplete(uid: String?, downloadUrl: Uri?)
    fun onUploadError(e:String?)
    fun onInformationUserSuccessful()
    fun onInformationUserError(e:String?)

}