package com.retail.dolphinpos.data.repositories

import com.retail.dolphinpos.data.dao.VerifyPinDao
import com.retail.dolphinpos.data.mapper.VerifyPinMapper
import com.retail.dolphinpos.data.service.ApiService
import com.retail.dolphinpos.domain.model.auth.pin.request.VerifyPinRequest
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinLogoUrl
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinResponse
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinStore
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinUser
import com.retail.dolphinpos.domain.repositories.VerifyPinRepository

class VerifyPinRepositoryImpl(
    private val api: ApiService, private val verifyPinDao: VerifyPinDao
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
    ): Boolean {
        try {
            val rowId = verifyPinDao.insertVerifyPinUser(
                VerifyPinMapper.toVerifyPinUserEntity(
                    verifyPinUser = verifyPinUser, password = password
                )
            )
            return rowId != -1L
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertStoreDataIntoLocalDB(
        verifyPinStore: VerifyPinStore, userId: Int
    ): Boolean {
        try {
            val rowId = verifyPinDao.insertVerifyPinStoreDetails(
                VerifyPinMapper.toVerifyPinStoreEntity(
                    userID = userId, verifyPinStore = verifyPinStore
                )
            )
            return rowId != -1L
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertStoreLogoUrlDataIntoLocalDB(
        verifyPinLogoUrl: VerifyPinLogoUrl, userId: Int, storeID: Int
    ): Boolean {
        try {
            val rowId = verifyPinDao.insertVerifyPinStoreLogoUrlDetails(
                VerifyPinMapper.toVerifyPinStoreLogoUrlEntity(
                    userID = userId,
                    storeID = storeID,
                    verifyPinLogoUrl = verifyPinLogoUrl
                )
            )
            return rowId != -1L
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getCompleteUserData(userId: Int): VerifyPinUser? {
        val userEntity = verifyPinDao.getVerifyPinUserEntity(userId)

        // Get storeId from userEntity first
        val storeId = userEntity?.storeId
        if (storeId == null) return null

        val storeEntity = verifyPinDao.getVerifyPinStoreEntity(storeId)
        val storeLogoUrlEntity = verifyPinDao.getVerifyPinLogoUrlEntity(storeId)

        return if (storeEntity != null && storeLogoUrlEntity != null) {
            VerifyPinMapper.toVerifyPinData(
                userEntity,
                storeEntity,
                storeLogoUrlEntity
            )
        } else {
            null
        }
    }


}