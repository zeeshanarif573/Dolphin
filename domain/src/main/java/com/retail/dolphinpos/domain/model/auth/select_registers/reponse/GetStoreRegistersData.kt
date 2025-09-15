package com.retail.dolphinpos.domain.model.auth.select_registers.reponse

data class GetStoreRegistersData(
    val createdAt: String,
    val id: Int,
    val name: String,
    val storeId: Int,
    val updatedAt: String
)