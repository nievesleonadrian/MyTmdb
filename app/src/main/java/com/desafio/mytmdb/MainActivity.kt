package com.desafio.mytmdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.desafio.mytmdb.common.MyTmdbApplication
import com.desafio.mytmdb.databinding.ActivityMoviesBinding
import com.desafio.mytmdb.ui.di.DaggerMoviesComponent

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private var binding: ActivityMoviesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies)
        navController = Navigation.findNavController(this, R.id.movies_nav_fragment)
        setupInjection()
        setUpNavigation()
        destinationManager()
    }

    private fun setupInjection() {
        DaggerMoviesComponent.builder()
            .applicationComponent(MyTmdbApplication.getApplicationComponent())
            .build()
            .inject(this)
    }

    private fun setUpNavigation() {
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.movies_nav_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun destinationManager() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.apply {
                setDisplayShowHomeEnabled(true)
            }
            when (destination.id) {
                else -> supportActionBar?.show()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}