package com.retail.dolphinpos.data.repositories

import com.retail.dolphinpos.data.service.ApiService
import com.retail.dolphinpos.domain.models.auth.login.request.LoginRequest
import com.retail.dolphinpos.domain.models.auth.login.response.LoginResponse
import com.retail.dolphinpos.domain.models.auth.select_register.GetStoreRegistersData
import com.retail.dolphinpos.domain.models.auth.select_register.GetStoreRegistersResponse
import com.retail.dolphinpos.domain.repositories.LoginRepository
import com.retail.dolphinpos.domain.repositories.StoreRegistersRepository

class StoreRegisterRepositoryImpl(
    private val api: ApiService
) : StoreRegistersRepository {
    override suspend fun getStoreRegisters(storeId: Int): GetStoreRegistersResponse {
        return try {
            api.getStoreRegisters(storeId)
        } catch (e: Exception) {
            throw e
        }
    }

}