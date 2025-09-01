package com.retail.dolphinpos.presentation.features.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigationGraph()
    }

    private fun setNavigationGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.auth_nav_graph)
        navGraph.setStartDestination(R.id.splashFragment)
        navController.graph = navGraph
    }
}