package com.example.stefano.happypark.fragments.AddWithTextFragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import com.example.stefano.happypark.R
import com.example.stefano.happypark.application.HappyParkApp
import com.example.stefano.happypark.callbacks.OnAutoCompleteCallback
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by stefano on 15/06/17.
 */
class AddWithTextFragment : Fragment(), AddWithTextContract.View,OnAutoCompleteCallback {

    @Inject
    lateinit var presenter:AddWithTextPresenter

    var autoComplete: List<String>? = ArrayList()
    var adapter:PlacesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_add_with_text,container,false)

        HappyParkApp.appComponent.inject(this)

        val insertText = rootView?.findViewById(R.id.autoText) as? AutoCompleteTextView


        adapter = PlacesAdapter(activity, R.layout.list_item, autoComplete, this)
        insertText?.setAdapter(adapter)



        return rootView
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun updatePlaces(totalPlaces: ArrayList<String>) {
        adapter?.setResultList(totalPlaces)
    }


    override fun onAutoComplete(character: String) {
       presenter.onTextChanged(character)
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

}