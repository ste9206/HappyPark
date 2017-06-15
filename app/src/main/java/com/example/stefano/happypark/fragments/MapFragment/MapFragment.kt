package com.example.stefano.happypark.fragments.MapFragment

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Geocoder
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stefano.happypark.R
import com.example.stefano.happypark.application.HappyParkApp
import com.example.stefano.happypark.utils.AlertMessage
import com.example.stefano.happypark.utils.MapsUtility
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import java.util.*
import javax.inject.Inject


/**
 * Created by stefano on 14/06/17.
 */
class MapFragment : Fragment() , MapContract.View{

    var mapView:MapView? = null
    var gMaps:GoogleMap? = null
    val PERMISSION_CODE = 9

    @Inject
    lateinit var presenter:MapPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater?.inflate(R.layout.fragment_map,container,false)

        HappyParkApp.appComponent.inject(this)

        loadMapView(rootView,savedInstanceState)
        requestPermissions()

        return rootView
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(activity,arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.READ_EXTERNAL_STORAGE),PERMISSION_CODE)
    }

    private fun loadMapView(rootView:View?,savedInstanceState: Bundle?) {
        mapView = rootView?.findViewById(R.id.mapView) as? MapView
        mapView?.onCreate(savedInstanceState)
        mapView?.onResume()

        try { MapsInitializer.initialize(activity.applicationContext) }
        catch (e: Exception) { e.printStackTrace() }

        mapView?.getMapAsync { googleMap -> getMaps(googleMap) }
    }

    private fun  getMaps(googleMap: GoogleMap?) {

        this.gMaps = googleMap
        gMaps?.isMyLocationEnabled = true
        gMaps?.uiSettings?.isMyLocationButtonEnabled = true
        gMaps?.uiSettings?.isZoomControlsEnabled = true
        gMaps?.animateCamera(MapsUtility.cameraZoom(Locale.ITALY,activity,"Italia"))

        presenter.loadMapMarkers()
        gMaps?.setOnInfoWindowClickListener{   }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            9 -> when(grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> print("ok")
                else -> showErrorMessage("You could not upload a photo if you don't grant permissions")
            }
        }

    }

    override fun showErrorMessage(e: String) {
        AlertMessage.newAlertErrorMessage(e,activity).show()
    }

    override fun addMarker(markerOptionCreator: MarkerOptions) {
       gMaps?.addMarker(markerOptionCreator)
    }



    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        mapView.let { mapView -> mapView?.onResume() }
    }

    override fun onPause() {
        super.onPause()
        mapView.let { mapView -> mapView?.onPause() }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.let { mapView -> mapView?.onLowMemory() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.let { mapView -> mapView?.onDestroy() }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView.let { mapView -> mapView?.onSaveInstanceState(outState) }
    }





//
//
//                     mgoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
//                     {
//                         public void onInfoWindowClick(Marker marker)
//                         {
//
//                             /**QUA BISOGNA VEDERE SE SEGNALARE O NO **/
//
//                             if(marker.getTitle().equals("Prezzo:")) //parcheggio paynz
//                               createNewPromptView(marker);
//                             else
//                             {
//                               Location location = mapLocation.get(marker);
//                               createNewFragment(IdLocation.get(location),userid);
//                             }
//                         }
//                     });
//                    }
//                    });
//
//
//
//
//
//
//
//            return v;
//        }



}