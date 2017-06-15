package com.example.stefano.happypark.activities.SignUpActivity

import android.app.Activity
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import com.bumptech.glide.Glide
import com.example.stefano.happypark.Manifest

import com.example.stefano.happypark.R
import com.example.stefano.happypark.activities.SignInActivity.SignInActivity
import com.example.stefano.happypark.application.HappyParkApp
import com.example.stefano.happypark.callbacks.DatePickerCallback
import com.example.stefano.happypark.utils.AlertMessage
import com.example.stefano.happypark.utils.FileCreator
import com.example.stefano.happypark.utils.Validator
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.io.IOException
import javax.inject.Inject

class SignUpActivity : AppCompatActivity(),SignUpContract.View,DatePickerCallback {

    @Inject
    lateinit var presenter:SignUpPresenter

    var dialog: ProgressDialog? = null
    private var photoUri: String? = null
    private var mMediaUri:Uri? = null
    private val REQUEST_TAKE_PHOTO = 0
    private val REQUEST_PICK_PHOTO = 1
    private val PERMISSION_CODE = 9
    private val MEDIA_TYPE_IMAGE = 4

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        HappyParkApp.appComponent.inject(this)

        registrati.setOnClickListener { presenter.onRegisterClicked() }
        profileImage.setOnClickListener { presenter.onPhotoClicked() }
        data.setOnClickListener { presenter.onDataClicked() }
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            9 -> when(grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> presenter.onLoadPicture()
                else -> showErrorMessage("You could not upload a photo if you don't grant permissions")
            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO)
            //dalla fotocamera
            {
                if (data != null)mMediaUri = data.data

                try {
                  val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, mMediaUri)
                    presenter.loadPicture(mMediaUri, bitmap, this)
                }
                catch (e: IOException) {
                  showErrorMessage(e.message!!)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onBackPressed() {
       presenter.onBackPressed()
    }
    override fun displayError(s: String) {
        AlertMessage.newAlertErrorMessage(s,this).show()
    }

    override fun requestPermissions() {
        ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE),PERMISSION_CODE)
    }

    override fun validateFields() {
        val nome = nome.text.toString().trim().takeUnless { nome.text.isNullOrEmpty() || !Validator.validateNameSurname(nome.text.toString().trim()) } ?: return presenter.showError("name")
        val cognome = cognome.text.toString().trim().takeUnless { cognome.text.isNullOrEmpty()|| !Validator.validateNameSurname(cognome.text.toString().trim())  } ?: return presenter.showError("surname")
        val data = data.text.toString().trim().takeUnless { data.text.isNullOrEmpty() } ?: return presenter.showError("data")
        val telefono = telefono.text.toString().trim().takeUnless { telefono.text.isNullOrEmpty() || !Validator.validateMobilePhone(telefono.text.toString().trim())  } ?: return presenter.showError("telefono")
        val mail = email.text.toString().trim().takeUnless { email.text.isNullOrEmpty()|| !Validator.validateEmailPattern(email.text.toString().trim())  } ?: return presenter.showError("email")
        val password = password.text.toString().trim().takeUnless { password.text.isNullOrEmpty() || !Validator.validatePasswordPattern(password.text.toString().trim()) } ?: return presenter.showError("password")

        presenter.signUp(nome,cognome,data,telefono,mail,password)
    }

    override fun dismissDialog(dialog: DialogInterface?) {
        dialog?.dismiss()
    }

    override fun showImagePicture(path: String?) {
        this.photoUri = path
        Glide.with(this).load(path).asBitmap().into(profileImage)
    }

    override fun openCamera(dialog: DialogInterface?) {
        mMediaUri = FileCreator.getOutputMediaFileUri(MEDIA_TYPE_IMAGE,this)
        val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri)
        startActivityForResult(photoIntent, REQUEST_TAKE_PHOTO)
        dismissDialog(dialog)
    }

    override fun openGallery(dialog: DialogInterface?) {
        val pickPhoto = Intent(Intent.ACTION_GET_CONTENT)
        pickPhoto.type = "image/*"
        startActivityForResult(pickPhoto, REQUEST_PICK_PHOTO)
        dismissDialog(dialog)
    }
    override fun showErrorMessage(e: String) {
      AlertMessage.newAlertErrorMessage(e,this).show()
    }

    override fun openPhotoDialog() {
        AlertMessage.makePhotoDialog("Please add a photo",this,DialogInterface.OnClickListener { dialog, which -> presenter.selectMethod(dialog,which)  }).show()
    }

    override fun onDataSet(date: String) {
        data.text = date
    }

    override fun openDataPicker() {
        val picker = DatePickerFragment()
        picker.show(supportFragmentManager, "datePicker")
    }

    override fun openSignUpActivity() {
        val intent = Intent(this,SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onRegistrationSuccessful() {
      AlertMessage.newAlertMessageListener("Success","Your registration is successful, now you could log in",this,DialogInterface.OnClickListener { dialog, which -> presenter.onDialogComplete() }).show()
    }

    override fun openProgressDialogSignUp() {
        dialog = ProgressDialog.show(this,"SignUp","SignUpInProgress",true)
    }

    override fun stopProgressDialog() {
        dialog?.dismiss()
    }




}
