package com.retail.dolphinpos.domain.repositories

import com.retail.dolphinpos.domain.model.auth.logout.LogoutResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.GetStoreRegistersResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.UpdateStoreRegisterResponse
import com.retail.dolphinpos.domain.model.auth.select_registers.request.UpdateStoreRegisterRequest
import com.retail.dolphinpos.domain.model.auth.users.LogoUrl
import com.retail.dolphinpos.domain.model.auth.users.Store
import com.retail.dolphinpos.domain.model.auth.users.User

interface StoreRegistersRepository {
    suspend fun getStoreRegisters(storeId: Int): GetStoreRegistersResponse

    suspend fun updateStoreRegister(updateStoreRegisterRequest: UpdateStoreRegisterRequest): UpdateStoreRegisterResponse

    suspend fun logout(): LogoutResponse

    suspend fun insertUsersDataIntoLocalDB(
        user: User,
        password: String,
        userId: Int,
        storeID: Int
    ) {
        insertUserIntoLocalDB(user, password)
        user.store?.let { insertStoreIntoLocalDB(it, userId) }
        user.store?.let { insertStoreLogoUrlIntoLocalDB(it.logoUrl, userId, storeID) }
    }

    suspend fun insertUserIntoLocalDB(user: User, password: String)
    suspend fun insertStoreIntoLocalDB(store: Store, userId: Int)
    suspend fun insertStoreLogoUrlIntoLocalDB(
        logoUrl: LogoUrl,
        userId: Int,
        storeID: Int
    )

}