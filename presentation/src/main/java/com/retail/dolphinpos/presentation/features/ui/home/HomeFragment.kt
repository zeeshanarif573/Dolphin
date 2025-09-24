package com.retail.dolphinpos.presentation.features.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.retail.dolphinpos.presentation.databinding.FragmentHomeBinding
import com.retail.dolphinpos.presentation.features.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var cartAdapter: CartAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCartList()
        setCategoryAndProducts()
    }

    private fun setCartList() {
        cartAdapter = CartAdapter(mutableListOf()) { cart ->
            Toast.makeText(requireContext(), "${cart.productName} clicked", Toast.LENGTH_SHORT)
                .show()
        }

        binding.cartView.adapter = cartAdapter
        binding.cartView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.cartList.observe(viewLifecycleOwner) { list ->
            cartAdapter.updateData(list)
        }
    }

    private fun setCategoryAndProducts() {
        categoryAdapter = CategoryAdapter(requireContext(), mutableListOf()) { category ->
            viewModel.selectCategory(category)   // update products
        }

        productAdapter = ProductAdapter(mutableListOf())

        binding.categoriesView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
        }

        binding.productsView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3, RecyclerView.VERTICAL, false)
            adapter = productAdapter
        }

        viewModel.categories.observe(viewLifecycleOwner) { list ->
            categoryAdapter.updateData(list)
        }

        // observe products
        viewModel.products.observe(viewLifecycleOwner) { list ->
            productAdapter.updateProducts(list)
        }
    }
}