package com.retail.dolphinpos.data.repositories

import com.retail.dolphinpos.data.dao.UserDao
import com.retail.dolphinpos.data.mapper.UserMapper
import com.retail.dolphinpos.data.service.ApiService
import com.retail.dolphinpos.domain.model.auth.login.response.Locations
import com.retail.dolphinpos.domain.model.auth.login.response.Registers
import com.retail.dolphinpos.domain.model.auth.logout.LogoutResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.UpdateStoreRegisterData
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.UpdateStoreRegisterResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.request.UpdateStoreRegisterRequest
import com.retail.dolphinpos.domain.repositories.auth.StoreRegistersRepository

class StoreRegisterRepositoryImpl(
    private val api: ApiService,
    private val userDao: UserDao
) : StoreRegistersRepository {

    override suspend fun updateStoreRegister(updateStoreRegisterRequest: UpdateStoreRegisterRequest): UpdateStoreRegisterResponse {
        return try {
            api.updateStoreRegister(updateStoreRegisterRequest)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun logout(): LogoutResponse {
        return try {
            api.logout()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getLocations(storeID: Int): List<Locations> {
        val locationEntities = userDao.getLocationsByStoreId(storeID)
        return UserMapper.toLocationsAgainstStoreID(locationEntities)
    }

    override suspend fun getRegistersByLocationID(locationID: Int): List<Registers> {
        val registerEntities = userDao.getRegistersByLocationId(locationID)
        return UserMapper.toRegistersAgainstLocationID(locationID, registerEntities)
    }

    override suspend fun insertRegisterStatusDetailsIntoLocalDB(updateStoreRegisterData: UpdateStoreRegisterData) {
        try {
            userDao.insertRegisterStatusDetails(
                UserMapper.toRegisterStatusEntity(
                    updateStoreRegisterData
                )
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getRegisterStatus(): UpdateStoreRegisterData {
        val registerStatusDetailEntities = userDao.getRegisterStatusDetail()
        return UserMapper.toRegisterStatus(registerStatusDetailEntities)
    }
}