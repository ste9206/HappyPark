package com.example.stefano.happypark.fragments.MapFragment

import com.example.stefano.happypark.callbacks.OnLocationCallback
import com.example.stefano.happypark.models.Location
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

/**
 * Created by stefano on 15/06/17.
 */
class MapRepository @Inject constructor(private var fAuth:FirebaseAuth, private var dAuth:FirebaseDatabase): MapContract.Repository, ValueEventListener{

    private var callback:OnLocationCallback? = null
    override fun loadMarkers(callback:OnLocationCallback) {

        this.callback = callback
        val reference = dAuth.reference
        val query = reference.child("location")

        query.addValueEventListener(this)

    }

    override fun onCancelled(p0: DatabaseError?) {
     callback?.onLocationError(p0.let { p0?.message })
    }

    override fun onDataChange(snapshot: DataSnapshot?) {
       snapshot?.children?.forEach { data ->
           val location = snapshot.getValue(Location::class.java)
           callback?.onLocationLoaded(location)
       }
    }


}