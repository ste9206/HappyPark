package com.example.stefano.happypark.fragments.ParkFragment

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
class ParkRepository @Inject constructor(private var fAuth:FirebaseAuth, private var dAuth:FirebaseDatabase): ValueEventListener,ParkContract.Repository {

    private var callback:OnLocationCallback? = null

    override fun downloadListOfPark(callback: OnLocationCallback) {
      this.callback = callback
        fAuth.currentUser.let {
            dAuth.reference.let {
              val query = dAuth.reference.child("location")

              query.addValueEventListener(this)

            }
        }
    }

    override fun onCancelled(dataError: DatabaseError?) {
      callback?.onLocationError(dataError?.message)
    }

    override fun onDataChange(snapshot: DataSnapshot?) {
        snapshot?.children?.forEach { data -> run { convertData(data) } }
    }

    private fun  convertData(data: DataSnapshot?) {

        val location = data?.getValue(Location::class.java)
        callback?.onLocationLoaded(location!!)

    }
}