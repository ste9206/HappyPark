package com.example.stefano.happypark.activities.RecoveryPasswordActivity

import com.example.stefano.happypark.callbacks.OnTaskCallback
import javax.inject.Inject

/**
 * Created by stefano on 13/06/17.
 */
class RecoveryPasswordPresenter @Inject constructor(var repository: RecoveryPasswordRepository): RecoveryPasswordContract.Presenter, OnTaskCallback {


    private var contractView:RecoveryPasswordContract.View? = null


    override fun setView(view: RecoveryPasswordContract.View?) {
      this.contractView = view
    }

    override fun onButtonClicked() {
       contractView?.checkField()
    }

    override fun onTaskSuccessful() {
      contractView?.recoverySuccessful()
    }

    override fun onTaskError(error: String?) {
        contractView?.recoveryError(error)
    }
    override fun onDestroy() {
        contractView = null
    }

    override fun onSuccessfulDialogClicked() {
      contractView?.openSignInActivity()
    }

    override fun onBackPressedClicked() {
      contractView?.openSignInActivity()
    }

    override fun onFieldVerified(mail:String) {
      repository.resetPassword(mail,this)
    }
    override fun showError() {
        contractView?.showErrorMessage("E-mail is in wrong format")
    }
}