package com.example.stefano.happypark.fragments.ParkFragment

import com.example.stefano.happypark.callbacks.OnLocationCallback
import com.example.stefano.happypark.models.Location
import javax.inject.Inject

/**
 * Created by stefano on 15/06/17.
 */
class ParkPresenter @Inject constructor(private var repository: ParkRepository): OnLocationCallback, ParkContract.Presenter {

    private var contractView:ParkContract.View? = null

    override fun setView(view: ParkContract.View?) {
      this.contractView = view
    }

    override fun onFloatingButtonClicked() {
        contractView?.openNewAddParkFragment()
    }

    override fun loadAllParksInserted() {
       repository.downloadListOfPark(this)
    }

    override fun onLocationError(e: String?) {

    }

    override fun onLocationLoaded(location: Location) {
        contractView?.addLocation(location)

    }



}