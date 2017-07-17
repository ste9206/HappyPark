package com.example.stefano.happypark.fragments.AddParkFragment


import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stefano.happypark.R
import com.example.stefano.happypark.fragments.AddWithMapFragment.AddWithMapFragment
import com.example.stefano.happypark.fragments.AddWithTextFragment.AddWithTextFragment

/**
 * Created by stefano on 15/06/17.
 */
class AddParkFragment: Fragment() {

    var tabLayout:TabLayout? = null
    var viewPager:ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_add_park,container,false)

        tabLayout = activity.findViewById(R.id.tabs) as TabLayout
        tabLayout?.visibility = View.VISIBLE
        viewPager = rootView?.findViewById(R.id.viewpager) as ViewPager

        val addWithMapFragment = AddWithMapFragment()
        val addWithTextFragment = AddWithTextFragment()

        viewPager?.adapter = object : FragmentPagerAdapter(childFragmentManager){

            override fun getItem(position: Int): Fragment {

                when(position){
                   0 -> return addWithTextFragment
                   else -> return addWithMapFragment
               }
            }

            override fun getPageTitle(position: Int): CharSequence {

                when(position){
                    0 -> return "Address"
                    else -> return "Map"
                }
            }

            override fun getCount(): Int {
                return 2
            }
        }
        tabLayout?.setupWithViewPager(viewPager)

        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}