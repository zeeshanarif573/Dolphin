package com.retail.dolphinpos.presentation.features.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: BottomNavAdapter
    private val viewModel: HomeActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigationGraph()
        setMenusAdapter()
    }

    private fun setMenusAdapter() {
        adapter = BottomNavAdapter(emptyList()) { menu ->
            Toast.makeText(this, "Clicked: $menu", Toast.LENGTH_SHORT).show()
        }
        binding.bottomNavBar.adapter = adapter
        binding.bottomNavBar.layoutManager =
            GridLayoutManager(this, viewModel.menus.value!!.size, RecyclerView.VERTICAL, false)

        viewModel.menus.observe(this) { menusList ->
            adapter.updateMenus(menusList)
        }
    }

    private fun setNavigationGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.home_nav_graph)
        navGraph.setStartDestination(R.id.homeFragment)
        navController.graph = navGraph
    }
}