package com.retail.dolphinpos.domain.repositories.auth

import com.retail.dolphinpos.domain.model.auth.login.response.Locations
import com.retail.dolphinpos.domain.model.auth.login.response.Registers
import com.retail.dolphinpos.domain.model.auth.logout.LogoutResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.UpdateStoreRegisterData
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.UpdateStoreRegisterResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.request.UpdateStoreRegisterRequest

interface StoreRegistersRepository {
    suspend fun updateStoreRegister(updateStoreRegisterRequest: UpdateStoreRegisterRequest): UpdateStoreRegisterResponse

    suspend fun logout(): LogoutResponse

    suspend fun getLocations(storeID: Int): List<Locations>

    suspend fun getRegistersByLocationID(locationID: Int): List<Registers>

    suspend fun insertRegisterStatusDetailsIntoLocalDB(updateStoreRegisterData: UpdateStoreRegisterData)

    suspend fun getRegisterStatus(): UpdateStoreRegisterData

}