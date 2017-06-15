package com.example.stefano.happypark.fragments.MapFragment

import android.location.Geocoder
import com.example.stefano.happypark.callbacks.OnLocationCallback
import com.example.stefano.happypark.models.Location
import com.example.stefano.happypark.utils.MapsUtility
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import javax.inject.Inject

/**
 * Created by stefano on 14/06/17.
 */
class MapPresenter @Inject constructor(private var repository: MapRepository) :OnLocationCallback,MapContract.Presenter {

    private var contractView:MapContract.View? = null


    override fun onLocationLoaded(location: Location)
    {
      when{
      location.type.equals("free") -> contractView?.addMarker(MapsUtility.markerOptionCreator(location.latitude,location.longitude,BitmapDescriptorFactory.HUE_GREEN,"Free"))
      location.type.equals("reserved")-> contractView?.addMarker(MapsUtility.markerOptionCreator(location.latitude,location.longitude,BitmapDescriptorFactory.HUE_YELLOW,"Reserved for: "+location.reservation))
      location.type.equals("pay") ->  contractView?.addMarker(MapsUtility.markerOptionCreator(location.latitude,location.longitude,BitmapDescriptorFactory.HUE_CYAN,"Price: "+location.hourCost+" €/h"))
      }
    }

    override fun onLocationError(e: String?) {

    }
    override fun loadMapMarkers() {

        repository.loadMarkers(this)
    }



    override fun setView(view: MapContract.View) {
       this.contractView = view
    }


}