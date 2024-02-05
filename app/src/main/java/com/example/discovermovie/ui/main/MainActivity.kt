package com.example.discovermovie.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.discovermovie.R
import com.example.discovermovie.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)



        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.homeFragment -> {
                    binding.topTitle.text = "Discover Movies"
                }

                R.id.aboutAppFragment -> {
                    binding.topTitle.text = "About App"
                }
            }
        }


        NavigationUI.setupWithNavController(bottomNavigationView, navController)


//        val quotesApi = RetrofitHelper.getInstance().create(MyApi::class.java)
//        // launching a new coroutine
//        GlobalScope.launch {
//            val result = quotesApi.getQuotes()
//            if (result != null)
//            // Checking the results
//                Log.d("ayush: ", result.body().toString())
//        }

    }


    }

