package com.example.stefano.happypark.MainActivity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.stefano.happypark.R
import com.example.stefano.happypark.activities.SignInActivity.SignInActivity
import com.example.stefano.happypark.application.HappyParkApp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import com.example.stefano.happypark.fragments.MapFragment.MapFragment
import kotlinx.android.synthetic.main.activity_sign_up.view.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,MainContract.View {

    @Inject
    lateinit var presenter:MainPresenter

    var view:View? = null
    var profile:ImageView? = null
    var email:TextView? = null
    var logo: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        HappyParkApp.appComponent.inject(this)

        loadDrawerSettings()
        loadNavigationViewSettings()
        loadFirstFragment()

    }

    private fun loadFirstFragment() {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.contentMain, MapFragment()).commit()
    }

    private fun loadNavigationViewSettings() {

        view = navigationView.getHeaderView(0) as? View
        profile = view?.findViewById(R.id.imgPro) as? ImageView
        email = view?.findViewById(R.id.emailAddress) as? TextView
        logo = view?.findViewById(R.id.happyParkLogo) as? ImageView
    }

    private fun loadDrawerSettings()
    {
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {

          if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when(id){
            R.id.mappa -> presenter.onDrawerMenuMapClicked()
            R.id.login -> presenter.onDrawerLoginClicked()
            R.id.logOut -> presenter.onLogOut()
        }


        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.loadAllData()
    }
    override fun setUserHasBeenSignIn(mail: String?, img: String?) {

        logo?.visibility = View.GONE
        profile?.visibility = View.VISIBLE
        email?.visibility = View.VISIBLE
        navigationView.menu.clear()
        navigationView.inflateMenu(R.menu.activity_main_drawer)

        Glide.with(this).load(img).asBitmap().into(profile)
    }

    override fun setDisableCollapse() {

    }


    override fun setUserHasNotBeenSignIn() {

        logo?.visibility = View.VISIBLE
        profile?.visibility = View.GONE
        email?.visibility = View.GONE
        navigationView.menu.clear()
        navigationView.inflateMenu(R.menu.activity_main_drawer_login)

        Glide.with(this).load(R.drawable.happyparktrans1).into(logo)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun loadMapFragment() {

    }

    override fun loadSignInActivity() {
      val intent = Intent(this,SignInActivity::class.java)
      startActivity(intent)
      finish()
    }


}
