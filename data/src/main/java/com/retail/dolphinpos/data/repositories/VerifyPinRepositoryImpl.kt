package com.retail.dolphinpos.data.repositories

import com.retail.dolphinpos.data.dao.UserDao
import com.retail.dolphinpos.data.mapper.UserMapper
import com.retail.dolphinpos.data.service.ApiService
import com.retail.dolphinpos.domain.model.auth.pin.request.VerifyPinRequest
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinLogoUrl
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinResponse
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinStore
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinUser
import com.retail.dolphinpos.domain.repositories.VerifyPinRepository

class VerifyPinRepositoryImpl(
    private val api: ApiService, private val userDao: UserDao
) : VerifyPinRepository {

    override suspend fun verifyPin(request: VerifyPinRequest): VerifyPinResponse {
        return try {
            api.verifyPin(request)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertUserDataIntoLocalDB(
        verifyPinUser: VerifyPinUser, password: String
    ) {
        try {
            userDao.insertUser(
                UserMapper.toUserEntity(
                    verifyPinUser = verifyPinUser, password = password
                )
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertStoreDataIntoLocalDB(
        verifyPinStore: VerifyPinStore, userId: Int
    ) {
        try {
            userDao.insertStoreDetails(
                UserMapper.toStoreEntity(
                    userID = userId, verifyPinStore = verifyPinStore
                )
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertStoreLogoUrlDataIntoLocalDB(
        verifyPinLogoUrl: VerifyPinLogoUrl, userId: Int, storeID: Int
    ) {
        try {
            userDao.insertStoreLogoUrlDetails(
                UserMapper.toStoreLogoUrlEntity(
                    userID = userId,
                    storeID = storeID,
                    verifyPinLogoUrl = verifyPinLogoUrl
                )
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getCompleteUserData(userId: Int): VerifyPinUser? {
        val userEntity = userDao.getUserEntity(userId)

        // Get storeId from userEntity first
        val storeId = userEntity?.storeId
        if (storeId == null) return null

        val storeEntity = userDao.getStoreEntity(storeId)
        val storeLogoUrlEntity = userDao.getLogoUrlEntity(storeId)

        return if (storeEntity != null && storeLogoUrlEntity != null) {
            UserMapper.toVerifyPinUser(
                userEntity,
                storeEntity,
                storeLogoUrlEntity
            )
        } else {
            null
        }
    }

}