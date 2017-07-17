package com.example.stefano.happypark.fragments.AddWithTextFragment

import android.util.Log
import com.example.stefano.happypark.callbacks.OnPlacesCallback
import com.example.stefano.happypark.gson.Places
import javax.inject.Inject

/**
 * Created by stefano on 16/06/17.
 */
class AddWithTextPresenter @Inject constructor(private var apiRepo:APIRepository): AddWithTextContract.Presenter, OnPlacesCallback {


    private var contractView:AddWithTextContract.View? = null

    override fun setView(view: AddWithTextContract.View?) {
       this.contractView = view
    }

    override fun onNextButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTipologyClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTextChanged(character: String) {
      apiRepo.getAllPlaces(character,this)
    }

    override fun onComplete(totalPlaces: ArrayList<String>) {
        contractView?.updatePlaces(totalPlaces)
    }

    override fun onError(error: String?) {
        Log.e("AutocompleteError",error)
    }


}