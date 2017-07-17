package com.example.stefano.happypark.fragments.ParkFragment

import com.example.stefano.happypark.callbacks.OnLocationCallback
import com.example.stefano.happypark.models.Location

/**
 * Created by stefano on 15/06/17.
 */
interface ParkContract {

    interface View{
        fun openNewAddParkFragment()
        fun  addLocation(location: Location)
    }

    interface Presenter{
        fun setView(view:ParkContract.View?)
        fun onFloatingButtonClicked()
        fun loadAllParksInserted()

    }

    interface Repository{
        fun downloadListOfPark(callback: OnLocationCallback)

    }
}