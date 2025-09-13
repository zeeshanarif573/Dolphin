package com.retail.dolphinpos.domain.models.auth.select_register

data class GetStoreRegistersData(
    val createdAt: String,
    val id: Int,
    val name: String,
    val storeId: Int,
    val updatedAt: String
)