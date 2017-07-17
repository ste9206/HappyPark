package com.example.stefano.happypark.fragments.AddWithTextFragment

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import com.example.stefano.happypark.callbacks.OnAutoCompleteCallback

/**
 * Created by stefano on 16/06/17.
 */
class PlacesAdapter(context: Context?, resource: Int, private var resultList: List<String>?, private val listener: OnAutoCompleteCallback) : ArrayAdapter<String>(context, resource), Filterable {

    override fun getCount(): Int {
        resultList.let { return resultList!!.size } ?:run { return 0 }
    }

    fun setResultList(resultList: List<String>?) {
        this.resultList = resultList
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): String? {
        return resultList?.get(position)
    }

    override fun getFilter(): Filter {
        val filter = object : Filter() {
            override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
                val filterResults = Filter.FilterResults()

                constraint.let{
                    listener.onAutoComplete(constraint.toString())

                    filterResults.values = resultList
                    filterResults.count = resultList!!.size
                }

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: Filter.FilterResults?) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }

        return filter
    }
}