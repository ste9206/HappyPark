package com.example.stefano.happypark.activities.SignUpActivity

import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.Uri
import com.example.stefano.happypark.callbacks.OnSignInCallBack
import com.example.stefano.happypark.callbacks.OnSignUpCallback

/**
 * Created by stefano on 13/06/17.
 */
interface SignUpContract {

    interface View{
        fun validateFields()
        fun showErrorMessage(e:String)
        fun  displayError(s: String)
        fun openPhotoDialog()
        fun openCamera(dialog: DialogInterface?)
        fun openDataPicker()
        fun openGallery(dialog: DialogInterface?)
        fun dismissDialog(dialog: DialogInterface?)
        fun  showImagePicture(path: String?)
        fun requestPermissions()
        fun openSignUpActivity()
        fun onRegistrationSuccessful()
        fun openProgressDialogSignUp()
        fun stopProgressDialog()
    }

    interface Presenter{
        fun setView(view:SignUpContract.View?)
        fun onDestroy()
        fun onBackPressed()
        fun onRegisterClicked()
        fun showError(s: String)
        fun signUp(nome: String, cognome: String, data: String, telefono: String, mail: String, password: String)
        fun onLoadPicture()
        fun  selectMethod(dialog: DialogInterface?, which: Int)
        fun  loadPicture(mMediaUri: Uri?, bitmap: Bitmap?, context: Context)
        fun onPhotoClicked()
        fun onDataClicked()
        fun onDialogComplete()
    }

    interface FirebaseRepository{
        fun  signUpWithMailAndPassword(mail: String, password: String, callback:OnSignUpCallback)

    }

    interface StorageRepository{
        fun  savePicture(path: String?, uid: String?, callback: OnSignUpCallback)

    }

    interface DatabaseRepository{
        fun  saveUserExtraInformations(uid: String?, name: String?, surname: String?, data: String?, telefono: String?, url:Uri?, mail:String?,callback: OnSignUpCallback)

    }
}