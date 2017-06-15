package com.example.stefano.happypark.utils

import android.content.Context
import com.google.android.gms.maps.CameraUpdate
import java.util.*
import com.google.android.gms.maps.CameraUpdateFactory
import android.location.Geocoder
import com.google.android.gms.maps.model.*
import java.io.IOException


/**
 * Created by stefano on 15/06/17.
 */
object MapsUtility {

    fun cameraZoom(locale: Locale,context: Context,country:String):CameraUpdate? {

        val geocoder = Geocoder(context, locale)

        try {
            val addresses = ArrayList(geocoder.getFromLocationName(country, 1))
            val address = addresses[0]

            val camPos = CameraPosition.Builder()
                    .target(LatLng(address.latitude, address.longitude))
                    .zoom(5f)
                    .build()
            val camUpd3 = CameraUpdateFactory.newCameraPosition(camPos)
            return camUpd3

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    fun markerOptionCreator(latitude:Double, longitude:Double,colorMarker:Float,type:String):MarkerOptions{
        return MarkerOptions()
                .position(LatLng(latitude,longitude))
                .title(type)
                .icon(BitmapDescriptorFactory.defaultMarker(colorMarker))
    }

 }