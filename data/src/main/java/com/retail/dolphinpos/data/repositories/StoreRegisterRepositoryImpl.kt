package com.retail.dolphinpos.data.repositories

import com.retail.dolphinpos.data.dao.UserDao
import com.retail.dolphinpos.data.mapper.UserMapper
import com.retail.dolphinpos.data.service.ApiService
import com.retail.dolphinpos.domain.model.auth.logout.LogoutResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.GetStoreRegistersResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.UpdateStoreRegisterResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.request.UpdateStoreRegisterRequest
import com.retail.dolphinpos.domain.model.auth.users.LogoUrl
import com.retail.dolphinpos.domain.model.auth.users.Store
import com.retail.dolphinpos.domain.model.auth.users.User
import com.retail.dolphinpos.domain.repositories.StoreRegistersRepository

class StoreRegisterRepositoryImpl(
    private val api: ApiService, private val userDao: UserDao
) : StoreRegistersRepository {

    override suspend fun getStoreRegisters(storeId: Int): GetStoreRegistersResponse {
        return try {
            api.getStoreRegisters(storeId)
        } catch (e: Exception) {
            throw e
        }
    }

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

    override suspend fun insertUserIntoLocalDB(
        user: User, password: String
    ) {
        try {
            userDao.insertUser(
                UserMapper.toUserEntity(
                    user = user, password = password
                )
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertStoreIntoLocalDB(
        store: Store, userId: Int
    ) {
        try {
            userDao.insertStoreDetails(
                UserMapper.toStoreEntity(
                    userID = userId, store = store
                )
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertStoreLogoUrlIntoLocalDB(
        logoUrl: LogoUrl, userId: Int, storeID: Int
    ) {
        try {
            userDao.insertStoreLogoUrlDetails(
                UserMapper.toStoreLogoUrlEntity(
                    userID = userId,
                    storeID = storeID,
                    logoUrl = logoUrl
                )
            )
        } catch (e: Exception) {
            throw e
        }
    }
}