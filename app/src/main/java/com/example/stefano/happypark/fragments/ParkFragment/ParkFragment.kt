package com.example.stefano.happypark.fragments.ParkFragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stefano.happypark.R
import com.example.stefano.happypark.application.HappyParkApp
import com.example.stefano.happypark.fragments.AddParkFragment.AddParkFragment
import com.example.stefano.happypark.models.Location
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_park.*
import javax.inject.Inject

/**
 * Created by stefano on 15/06/17.
 */
class ParkFragment: Fragment(), ParkContract.View {

    @Inject
    lateinit var presenter: ParkPresenter

    var locations = arrayListOf<Location>()
    var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val rootView = inflater?.inflate(R.layout.fragment_park,container,false)

        HappyParkApp.appComponent.inject(this)

        recyclerView = rootView?.findViewById(R.id.recyclerView) as? RecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        val adapter = ParkAdapter(activity,locations)

        rootView?.findViewById(R.id.addPark)?.setOnClickListener { presenter.onFloatingButtonClicked() }

        recyclerView?.adapter = adapter

       return rootView
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun openNewAddParkFragment() {
       val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.contentMain,AddParkFragment()).commit()
    }

    override fun addLocation(location: Location) {
       locations.add(location)
    }
}