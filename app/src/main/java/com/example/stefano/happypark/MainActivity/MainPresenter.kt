package com.example.stefano.happypark.MainActivity

import com.example.stefano.happypark.callbacks.OnPictureCallback
import javax.inject.Inject

/**
 * Created by stefano on 08/06/17.
 */
class MainPresenter @Inject constructor(var firebaseService: FirebaseService,var databaseService: DataReferenceService) :MainContract.Presenter,OnPictureCallback {

    var contractView:MainContract.View? = null


    override fun setView(view: MainContract.View) {
      this.contractView = view
      }

    override fun onDestroy() {
        contractView = null
    }

    override fun loadAllData() {

      val authUser = firebaseService.getCurrentUser()

       when(authUser){
           null -> contractView?.setUserHasNotBeenSignIn()
           else -> databaseService.getImageProfile(authUser.getMail(),this)
       }

    }
    override fun onPictureLoaded(picture: String?, mail:String?) {
        contractView?.setUserHasBeenSignIn(mail,picture)
    }

    override fun onDrawerMenuMapClicked() {

    }

    override fun onDrawerLoginClicked() {
      contractView?.loadSignInActivity()
    }

    override fun onLogOut() {
       firebaseService.logOut()
       contractView?.setUserHasNotBeenSignIn()
    }

}