package com.callor.login

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.callor.login.databinding.ActivityMainBinding
import com.callor.login.ui.login.LoginFragment

class MainActivity : AppCompatActivity(), LoginFragment.ViewBottomNav {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        /**
         * AppBarConfiguration 에 menu 와 연동을 하게 되면
         * AppBar 의 title 과 menu 를 서로 연동시키는 효과를 낼수 있다
         */
        val menuSets = setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.navigation_login
        )
        val appBarConfiguration = AppBarConfiguration(menuSets)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun viewBottonNav(status: Boolean) {
        binding.navView.visibility = if (status) View.VISIBLE else View.GONE
    }
}