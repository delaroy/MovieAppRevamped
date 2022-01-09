package com.delaroystudios.movieapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.delaroystudios.movieapp.R
import com.delaroystudios.movieapp.databinding.ActivityMainBinding
import com.delaroystudios.movieapp.util.contentView
import dagger.hilt.android.AndroidEntryPoint
import android.view.Menu

import android.view.MenuInflater
import android.view.MenuItem
import androidx.viewbinding.BuildConfig


@AndroidEntryPoint
class MainActivity: AppCompatActivity(), NavController.OnDestinationChangedListener{
    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            val navHostFragment = supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment_content_main
            ) as NavHostFragment
            navController = navHostFragment.navController

            navController.addOnDestinationChangedListener(
                this@MainActivity
            )
        }


    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {

            else -> {
            }
        }
        return false
    }

    fun changeTitle(title: String){
        setTitle(title)
    }
}