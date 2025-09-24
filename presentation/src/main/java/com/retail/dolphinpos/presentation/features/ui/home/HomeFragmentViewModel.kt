package com.retail.dolphinpos.presentation.features.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.retail.dolphinpos.domain.model.home.Cart
import com.retail.dolphinpos.domain.model.home.Product
import com.retail.dolphinpos.domain.model.home.ProductCategory
import com.retail.dolphinpos.presentation.R
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor() : ViewModel() {

    private val _cartList = MutableLiveData<List<Cart>>()
    val cartList: LiveData<List<Cart>> get() = _cartList

    private val _categories = MutableLiveData<List<ProductCategory>>()
    val categories: LiveData<List<ProductCategory>> = _categories

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    init {
        loadCart()
        loadCategories()
    }

    fun loadCart() {
        val cartList = listOf(
            Cart(1, "Product A", 2, "$15.66", R.drawable.dummy_pdt_image),
            Cart(2, "Product B", 1, "$25.10", R.drawable.dummy_pdt_image_two),
            Cart(3, "Product C", 4, "$9.99", R.drawable.dummy_pdt_image_three),
            Cart(4, "Product D", 3, "$12.50", R.drawable.dummy_pdt_image),
            Cart(5, "Product E", 2, "$18.75", R.drawable.dummy_pdt_image_two),
            Cart(6, "Product F", 6, "$14.75", R.drawable.dummy_pdt_image_three),
            Cart(7, "Product G", 2, "$15.66", R.drawable.dummy_pdt_image),
            Cart(8, "Product H", 1, "$25.10", R.drawable.dummy_pdt_image_two),
            Cart(9, "Product J", 4, "$9.99", R.drawable.dummy_pdt_image_three),
            Cart(10, "Product K", 3, "$12.50", R.drawable.dummy_pdt_image),
            Cart(11, "Product L", 2, "$18.75", R.drawable.dummy_pdt_image_two),
            Cart(12, "Product M", 6, "$14.75", R.drawable.dummy_pdt_image_three),
            Cart(13, "Product N", 3, "$12.50", R.drawable.dummy_pdt_image),
            Cart(14, "Product O", 2, "$18.75", R.drawable.dummy_pdt_image_two),
            Cart(15, "Product P", 6, "$14.75", R.drawable.dummy_pdt_image_three),
        )
        _cartList.value = cartList
    }

    private fun loadCategories() {
        val fruits = mutableListOf(
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(2, "Banana", 80.0, R.drawable.dummy_pdt_img_two),
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(2, "Banana", 80.0, R.drawable.dummy_pdt_img_two),
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(2, "Banana", 80.0, R.drawable.dummy_pdt_img_two),
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(2, "Banana", 80.0, R.drawable.dummy_pdt_img_two),
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(2, "Banana", 80.0, R.drawable.dummy_pdt_img_two),
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(2, "Banana", 80.0, R.drawable.dummy_pdt_img_two),
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(2, "Banana", 80.0, R.drawable.dummy_pdt_img_two),
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(2, "Banana", 80.0, R.drawable.dummy_pdt_img_two),
            Product(1, "Apple", 120.0, R.drawable.dummy_pdt_img),
            Product(2, "Banana", 80.0, R.drawable.dummy_pdt_img_two),
        )
        val categoryList = mutableListOf(
            ProductCategory(1, "Frozen Items", fruits),
            ProductCategory(1, "Toys", emptyList()),
            ProductCategory(1, "Fruits", emptyList()),
            ProductCategory(1, "Personal", emptyList()),
            ProductCategory(1, "Drinks", emptyList()),
            ProductCategory(1, "Vegetables", emptyList()),
            ProductCategory(1, "Cleaning", emptyList()),
            ProductCategory(1, "Meat", emptyList()),
            ProductCategory(1, "chocolates", emptyList()),
            ProductCategory(1, "Stationary", emptyList()),
            ProductCategory(1, "Medicines", emptyList()),
            ProductCategory(1, "Pet", emptyList()),
            ProductCategory(1, "Kitchen", emptyList()),
            ProductCategory(1, "Decor", emptyList())
        )

        _categories.value = categoryList
        _products.value = categoryList[0].products
    }

    fun selectCategory(category: ProductCategory) {
        _products.value = category.products
    }
}