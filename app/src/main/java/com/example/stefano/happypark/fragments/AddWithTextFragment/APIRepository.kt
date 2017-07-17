package com.example.stefano.happypark.fragments.AddWithTextFragment

import com.example.stefano.happypark.callbacks.OnPlacesCallback
import com.example.stefano.happypark.gson.Places
import com.example.stefano.happypark.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.Retrofit
import java.util.ArrayList
import java.util.stream.Collector
import java.util.stream.Collectors
import javax.inject.Inject

/**
 * Created by stefano on 16/06/17.
 */
class APIRepository @Inject constructor(private val retrofit: Retrofit):AddWithTextContract.APIRepository
{

    override fun getAllPlaces(input: String,callback:OnPlacesCallback) {

        var totalPlaces = arrayListOf<String>()

        val service = retrofit.create<APIService>(APIService::class.java)
        service.getPlaces(Constants.APIKEY, Constants.TYPES, Constants.LANGUAGE, Constants.COMPONENTS, input)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (object :Subscriber<Places>{
                    override fun onSubscribe(s: Subscription?) {
                    }

                    override fun onComplete() {
                      callback.onComplete(totalPlaces)
                    }

                    override fun onError(t: Throwable?) {
                      callback.onError(t?.message)
                    }

                    override fun onNext(t: Places?) {
                       addPlacesToList(t,totalPlaces)
                    }

                })
    }

    private fun  addPlacesToList(places: Places?, totalPlaces: ArrayList<String>) {
        places?.getPredictions()?.forEach { s-> totalPlaces.add(s.description)  }
    }

}