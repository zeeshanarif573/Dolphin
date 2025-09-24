package com.retail.dolphinpos.domain.model.home

data class ProductCategory(
    val id: Int,
    val name: String,
    val products: List<Product>
)