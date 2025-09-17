package com.retail.dolphinpos.domain.repositories

import com.retail.dolphinpos.domain.model.auth.pin.request.VerifyPinRequest
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinLogoUrl
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinResponse
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinStore
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinUser

interface VerifyPinRepository {
    suspend fun verifyPin(request: VerifyPinRequest): VerifyPinResponse

    suspend fun insertDataAfterVerifyPinIntoLocalDB(
        verifyPinUser: VerifyPinUser,
        password: String,
        userId: Int,
        storeID: Int
    ) {
        insertUserDataIntoLocalDB(verifyPinUser, password)
        insertStoreDataIntoLocalDB(verifyPinUser.store, userId)
        insertStoreLogoUrlDataIntoLocalDB(verifyPinUser.store.logoUrl, userId, storeID)
    }

    suspend fun insertUserDataIntoLocalDB(verifyPinUser: VerifyPinUser, password: String)
    suspend fun insertStoreDataIntoLocalDB(verifyPinStore: VerifyPinStore, userId: Int)
    suspend fun insertStoreLogoUrlDataIntoLocalDB(
        verifyPinLogoUrl: VerifyPinLogoUrl,
        userId: Int,
        storeID: Int
    )

    suspend fun getCompleteUserData(userId: Int): VerifyPinUser?

}