package com.retail.dolphinpos.presentation.features.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor() : ViewModel() {

    private val _menus = MutableLiveData<List<String>>()
    val menus: LiveData<List<String>> get() = _menus

    init {
        loadMenus()
    }

    fun loadMenus() {
        val menusList = listOf(
            "Home",
            "Products",
            "Orders",
            "Inventory",
            "Reports",
            "Setup",
            "Cash Drawer",
            "Quick Access"
        )
        _menus.value = menusList
    }
}