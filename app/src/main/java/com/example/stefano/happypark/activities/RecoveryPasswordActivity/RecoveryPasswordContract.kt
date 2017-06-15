package com.example.stefano.happypark.activities.RecoveryPasswordActivity

import com.example.stefano.happypark.callbacks.OnTaskCallback

/**
 * Created by stefano on 13/06/17.
 */
interface RecoveryPasswordContract {

    interface View{
        fun recoverySuccessful()
        fun  recoveryError(error: String?)
        fun openSignInActivity()
        fun checkField()
        fun showErrorMessage(message:String)

    }

    interface Presenter{
        fun setView(view:RecoveryPasswordContract.View?)
        fun onButtonClicked()
        fun onDestroy()
        fun onSuccessfulDialogClicked()
        fun onBackPressedClicked()
        fun onFieldVerified(mail:String)
        fun showError()

    }

    interface Repository{
        fun  resetPassword(mail: String, callback:OnTaskCallback)

    }
}