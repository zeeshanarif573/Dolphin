package com.retail.dolphinpos.domain.repositories

import androidx.room.Transaction
import com.retail.dolphinpos.domain.models.login.request.LoginRequest
import com.retail.dolphinpos.domain.models.login.response.LoginData
import com.retail.dolphinpos.domain.models.login.response.LoginResponse
import com.retail.dolphinpos.domain.models.login.response.Store
import com.retail.dolphinpos.domain.models.login.response.User


interface LoginRepository {
    suspend fun login(request: LoginRequest): LoginResponse

    suspend fun insertLoginDataIntoLocalDB(loginData: LoginData, password: String) {
        insertUserDataIntoLocalDB(loginData.user, password)
        insertAuthDataIntoLocalDB(loginData)
        insertStoreDataIntoLocalDB(loginData)
        insertStoreLogoUrlDataIntoLocalDB(loginData)
    }

    suspend fun insertAuthDataIntoLocalDB(loginData: LoginData): Boolean
    suspend fun insertUserDataIntoLocalDB(user: User, password: String): Boolean
    suspend fun insertStoreDataIntoLocalDB(loginData: LoginData): Boolean
    suspend fun insertStoreLogoUrlDataIntoLocalDB(loginData: LoginData): Boolean

    suspend fun getCompleteUserData(userId: Int): LoginData?

}