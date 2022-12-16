package com.example.myappbancodados.rickandmorty.ui.initial

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.myappbancodados.R
import com.example.myappbancodados.databinding.ActivityInitialBinding

class InitialRmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitialBinding

    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setupActionBarWithNavController(navController)
//        binding.bottomNavigation.setupWithNavController(navController)

//        setNavControllerDestination()
        setBottomNavigation()


    }

    private fun setBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.id_character -> {
                    navController.navigate(R.id.characterListFragment)
                    true
                }

                R.id.id_episode -> {
                    navController.navigate(R.id.episodesListFragment)
                    true
                }

                R.id.id_location -> {
                    navController.navigate(R.id.locationsListFragment)
                    true
                }

                else -> false
            }
        }
    }

    private fun setNavControllerDestination() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> hideBottomNav()
                R.id.registerFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }
}