package com.retail.dolphinpos.domain.repositories.auth

import com.retail.dolphinpos.domain.model.auth.login.response.Locations
import com.retail.dolphinpos.domain.model.auth.login.response.Registers
import com.retail.dolphinpos.domain.model.auth.logout.LogoutResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.GetStoreRegistersResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.UpdateStoreRegisterResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.request.UpdateStoreRegisterRequest

interface StoreRegistersRepository {
    suspend fun getStoreRegisters(storeId: Int): GetStoreRegistersResponse

    suspend fun updateStoreRegister(updateStoreRegisterRequest: UpdateStoreRegisterRequest): UpdateStoreRegisterResponse

    suspend fun logout(): LogoutResponse

    suspend fun getLocations(storeID: Int): List<Locations>

    suspend fun getRegistersByLocationID(locationID: Int): List<Registers>

}