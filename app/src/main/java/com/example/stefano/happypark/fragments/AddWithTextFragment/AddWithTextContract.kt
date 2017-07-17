package com.example.stefano.happypark.fragments.AddWithTextFragment

import com.example.stefano.happypark.callbacks.OnPlacesCallback

/**
 * Created by stefano on 16/06/17.
 */
interface AddWithTextContract {

    interface View{
        fun  updatePlaces(totalPlaces: ArrayList<String>)

    }

    interface Presenter{
        fun setView(view:AddWithTextContract.View?)
        fun onNextButtonClicked()
        fun onTipologyClicked()
        fun  onTextChanged(character: String)
    }

    interface APIRepository{

        fun getAllPlaces(input:String, callback:OnPlacesCallback)
    }

}