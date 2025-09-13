package com.retail.dolphinpos.domain.repositories

import com.retail.dolphinpos.domain.models.auth.select_register.GetStoreRegistersResponse

interface StoreRegistersRepository {
    suspend fun getStoreRegisters(storeId: Int): GetStoreRegistersResponse

}