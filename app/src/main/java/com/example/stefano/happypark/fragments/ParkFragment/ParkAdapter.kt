package com.example.stefano.happypark.fragments.ParkFragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.stefano.happypark.R
import com.example.stefano.happypark.models.Location
import com.example.stefano.happypark.utils.MapViewItemView

/**
 * Created by stefano on 15/06/17.
 */
class ParkAdapter(private var context: Context, private var locations: ArrayList<Location>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val STATIC = 0
    private val DYNAMIC = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(viewType){
            STATIC -> return onCreateStaticHolder(parent,viewType)
            else -> return onCreateDynamicHolder(parent,viewType)
        }
    }

    private fun  onCreateDynamicHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = MapViewItemView(context,null)
        v.mapViewOnCreate(null)
        val customAdapter = YesParkAdapter(v)
        return customAdapter
    }

    private fun  onCreateStaticHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_nopark, parent, false)
        val customAdapter = NoParkAdapter(v)
        return customAdapter
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(getItemViewType(position)){
            STATIC -> onBindStaticHolder(holder)
            else -> onBindDynamicHolder(holder,position)
        }
    }

    private fun  onBindStaticHolder(holder: RecyclerView.ViewHolder) {
      val staticHolder = holder as NoParkAdapter

        Glide.with(context).load(R.drawable.notinsert).asBitmap().into(staticHolder.notFoundImage)
    }

    private fun  onBindDynamicHolder(holder: RecyclerView.ViewHolder, position: Int) {
      val dinamicHolder = holder as YesParkAdapter
        val location = locations.get(position)
        dinamicHolder.putMarkers(location.latitude,location.longitude,location.type!!)

    }

    override fun getItemCount(): Int {
        when{
            locations.size == 0 -> return 1
            else -> return locations.size
        }

    }

    override fun getItemViewType(position: Int): Int {
       when{
           locations.size == 0 -> return STATIC
           else -> return DYNAMIC
       }
    }

    private inner class NoParkAdapter(itemView: View) : RecyclerView.ViewHolder(itemView){
        var notFoundImage:ImageView ? = null

        init {
            notFoundImage = itemView.findViewById(R.id.notFound) as? ImageView
        }
    }

    private inner class YesParkAdapter(mMapItemView: MapViewItemView) : RecyclerView.ViewHolder(mMapItemView)
    {
        var itemMapView:MapViewItemView ? = null
        var tipologia:TextView? = null
        var data:TextView? = null
        var state:TextView? = null
        var segnalation:TextView? = null
      init {
          itemMapView = mMapItemView
          tipologia = mMapItemView.findViewById(R.id.type) as? TextView
          data = mMapItemView.findViewById(R.id.addedAt) as? TextView
          state = mMapItemView.findViewById(R.id.state) as? TextView
          segnalation = mMapItemView.findViewById(R.id.signal) as? TextView
      }

     fun putMarkers(latitude:Double,longitude:Double,type:String){
         itemMapView.let { mapViewItemView -> mapViewItemView?.putMarkers(latitude,longitude,type) }
     }

     fun itemViewOnCreate(savedInstanceState: Bundle?){
         itemMapView.let { mapViewItemView -> mapViewItemView?.mapViewOnCreate(savedInstanceState) }
        }

     fun itemViewOnResume(){
         itemMapView.let { mapViewItemView -> mapViewItemView?.mapViewOnResume() }
     }

     fun itemViewOnDestroy(){
         itemMapView.let { mapViewItemView -> mapViewItemView?.mapViewOnDestroy() }
     }

     fun itemViewOnPause(){
         itemMapView.let { mapViewItemView -> mapViewItemView?.mapViewOnPause() }
     }

     fun itemViewOnLowMemory(){
         itemMapView.let { mapViewItemView -> mapViewItemView?.mapViewOnLowMemory() }
     }

     fun itemViewOnSaveInstanceState(outState:Bundle?){
         itemMapView.let { mapViewItemView ->mapViewItemView?.mapViewOnSaveInstanceState(outState!!) }
     }

    }
}