package com.example.stefano.happypark.utils

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.stefano.happypark.R
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory

/**
 * Created by stefano on 15/06/17.
 */

class MapViewItemView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    protected var mMapView: MapView? = null

    init {
        setupView()
    }

    private fun setupView() {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.card_park, this)
        mMapView = view.findViewById(R.id.mapCardView) as MapView

    }

    fun mapViewOnCreate(savedInstanceState: Bundle?) {
        mMapView.let { mapView -> mapView?.onCreate(savedInstanceState) }

    }

    fun putMarkers(latitude: Double, longitude: Double, type: String) {
        mMapView?.getMapAsync { googleMap ->
            googleMap.clear()

            when(type){
                "free" -> googleMap.addMarker(MapsUtility.addMarker(latitude,longitude,BitmapDescriptorFactory.HUE_GREEN))
                "reserved" -> googleMap.addMarker(MapsUtility.addMarker(latitude,longitude,BitmapDescriptorFactory.HUE_YELLOW))
                "pay" -> googleMap.addMarker(MapsUtility.addMarker(latitude,longitude,BitmapDescriptorFactory.HUE_CYAN))
            }
            googleMap.moveCamera(MapsUtility.cardCamera(latitude,longitude))
        }
    }

    fun mapViewOnResume() {
        mMapView.let { mapView -> mapView?.onResume() }
    }

    fun mapViewOnPause() {
        mMapView.let { mapView -> mapView?.onPause() }
    }

    fun mapViewOnDestroy() {
        mMapView.let { mapView -> mapView?.onDestroy() }
    }

    fun mapViewOnLowMemory() {
        mMapView.let { mapView -> mapView?.onLowMemory() }
    }

    fun mapViewOnSaveInstanceState(outState: Bundle) {
        mMapView.let { mapView -> mapView?.onSaveInstanceState(outState) }
    }
}
