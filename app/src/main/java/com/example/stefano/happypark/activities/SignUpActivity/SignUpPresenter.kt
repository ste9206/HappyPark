package com.example.stefano.happypark.activities.SignUpActivity

import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.Uri
import com.example.stefano.happypark.callbacks.OnFinishedCallback
import com.example.stefano.happypark.callbacks.OnSignUpCallback
import com.example.stefano.happypark.utils.BitmapCompressor
import javax.inject.Inject

/**
 * Created by stefano on 13/06/17.
 */
class SignUpPresenter @Inject constructor(val databaseRepository: DatabaseRepository, val storageRepository: StorageRepository, val firebaseRepository:FirebaseRepository):SignUpContract.Presenter, OnFinishedCallback, OnSignUpCallback {

    var contractView:SignUpContract.View? = null
    var path: String? = null
    var name: String? = null
    var surname: String? = null
    var data: String? = null
    var telefono: String? = null
    var mail:String? = null

    override fun onCreateNewUserSuccessful(uid:String?) {
        storageRepository.savePicture(path,uid,this)
        contractView?.stopProgressDialog()
    }

    override fun onCreateNewUserError(e: String?) {
      contractView?.displayError(e!!)
      contractView?.stopProgressDialog()
    }

    override fun onDataClicked() {
        contractView?.openDataPicker()
    }

    override fun onPhotoClicked() {

        contractView?.requestPermissions()
    }

    override fun setView(view: SignUpContract.View?) {
        this.contractView = view
    }

    override fun showError(s: String) {

        var error: String? = null

        when(s){
            "name" -> error="You need to insert a valid name"
            "surname" -> error = "You need to insert a valid surname"
            "data" -> error = "You need to insert a valid data"
            "telefono" -> error = "You need to insert a valid mobile phone"
            "email" -> error = "You need to insert a valid mail"
            "password" -> error = "You need to insert a valid password"
        }
        contractView?.displayError(error!!)
    }

    override fun loadPicture(mMediaUri: Uri?, bitmap: Bitmap?, context: Context) {
      BitmapCompressor.compressBitmapInBackground(bitmap!!,context,this)
    }

    override fun onFinish(path: String?) {
      this.path = path
      contractView?.showImagePicture(path)
    }



    override fun selectMethod(dialog: DialogInterface?, which: Int) {
        when(which){
            0 -> contractView?.openCamera(dialog)
            1 -> contractView?.openGallery(dialog)
            2 -> contractView?.dismissDialog(dialog)
        }
    }

    override fun onLoadPicture() {
        contractView?.openPhotoDialog()
    }


    override fun onRegisterClicked() {
        contractView?.validateFields()
    }

    override fun onDestroy() {
        contractView = null
    }

    override fun onBackPressed() {
        contractView?.openSignUpActivity()
    }

    override fun signUp(nome: String, cognome: String, data: String, telefono: String, mail: String, password: String) {
      contractView?.openProgressDialogSignUp()
      firebaseRepository.signUpWithMailAndPassword(mail, password,this)
        this.name = nome
        this.surname = cognome
        this.data = data
        this.telefono = telefono
        this.mail = mail
    }

    override fun onUploadComplete(uid: String?, downloadUrl: Uri?) {
        databaseRepository.saveUserExtraInformations(uid,name,surname,data,telefono,downloadUrl,mail,this)
    }

    override fun onUploadError(e: String?) {
      contractView?.displayError(e!!)
       contractView?.stopProgressDialog()
    }

    override fun onInformationUserSuccessful() {
       contractView?.onRegistrationSuccessful()
    }

    override fun onInformationUserError(e: String?) {
       contractView?.displayError(e!!)
        contractView?.stopProgressDialog()
    }
    override fun onDialogComplete() {
        contractView?.openSignUpActivity()
    }

}