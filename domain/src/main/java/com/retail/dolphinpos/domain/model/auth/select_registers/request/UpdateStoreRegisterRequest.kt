package com.retail.dolphinpos.domain.model.auth.select_registers.request

data class UpdateStoreRegisterRequest(
    val storeId : Int,
    val storeRegisterId : Int
)
