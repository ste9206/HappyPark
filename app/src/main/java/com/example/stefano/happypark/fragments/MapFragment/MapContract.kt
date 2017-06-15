package com.example.stefano.happypark.fragments.MapFragment

import android.location.Geocoder
import com.example.stefano.happypark.callbacks.OnLocationCallback
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by stefano on 14/06/17.
 */
interface MapContract {

    interface View{
        fun showErrorMessage(e: String)
        fun  addMarker(markerOptionCreator: MarkerOptions)
    }

    interface Presenter{
        fun  setView(view:MapContract.View)
        fun  loadMapMarkers()

    }

    interface Repository{
        fun loadMarkers(callback:OnLocationCallback)


    }
}