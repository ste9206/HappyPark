package com.example.stefano.happypark.MainActivity

import com.example.stefano.happypark.callbacks.OnPictureCallback
import com.example.stefano.happypark.models.AuthUser

/**
 * Created by stefano on 08/06/17.
 */
interface MainContract {

    interface View{
        fun  setUserHasBeenSignIn(mail: String?, img: String?)
        fun setUserHasNotBeenSignIn()
        fun setDisableCollapse()
        fun loadMapFragment()
        fun loadSignInActivity()

    }

    interface Presenter{

        fun setView(view:MainContract.View)
        fun onDestroy()
        fun loadAllData()
        fun onDrawerMenuMapClicked()
        fun onDrawerLoginClicked()
        fun onLogOut()
    }

    interface Repository{
        fun getCurrentUser():AuthUser?
        fun logOut()

    }

    interface DatabaseRepository{
        fun getImageProfile(mail: String?, callback: OnPictureCallback)
    }
}