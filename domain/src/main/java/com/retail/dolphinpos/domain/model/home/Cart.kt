package com.retail.dolphinpos.domain.model.home

data class Cart(
    val productId: Int,
    val productName: String,
    val quantity: Int = 0,
    val price: String,
    val image: Int
)