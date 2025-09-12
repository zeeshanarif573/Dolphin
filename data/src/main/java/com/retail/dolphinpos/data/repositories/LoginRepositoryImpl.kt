package com.retail.dolphinpos.data.repositories

import com.retail.dolphinpos.data.dao.UserDao
import com.retail.dolphinpos.data.mapper.UserMapper
import com.retail.dolphinpos.data.service.ApiService
import com.retail.dolphinpos.domain.models.auth.login.request.LoginRequest
import com.retail.dolphinpos.domain.models.auth.login.response.LoginData
import com.retail.dolphinpos.domain.models.auth.login.response.LoginResponse
import com.retail.dolphinpos.domain.models.auth.login.response.User
import com.retail.dolphinpos.domain.repositories.LoginRepository

class LoginRepositoryImpl(
    private val api: ApiService, private val userDao: UserDao
) : LoginRepository {

    override suspend fun login(request: LoginRequest): LoginResponse {
        return try {
            api.login(request)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertAuthDataIntoLocalDB(loginData: LoginData): Boolean {
        try {
            val rowId =
                userDao.insertUserAuthDetails(UserMapper.toAuthEntity(loginData = loginData))
            return rowId != -1L
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertUserDataIntoLocalDB(user: User, password: String): Boolean {
        try {
            val rowId = userDao.insertUser(UserMapper.toUserEntity(user, password))
            return rowId != -1L
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertStoreDataIntoLocalDB(loginData: LoginData): Boolean {
        try {
            val rowId =
                userDao.insertUserStoreDetails(
                    UserMapper.toStoreEntity(
                        userID = loginData.user.id,
                        store = loginData.user.store
                    )
                )
            return rowId != -1L
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertStoreLogoUrlDataIntoLocalDB(loginData: LoginData): Boolean {
        try {
            val rowId = userDao.insertStoreLogoUrlDetails(
                UserMapper.toStoreLogoUrlEntity(
                    userID = loginData.user.id,
                    storeID = loginData.user.store.id,
                    storeLogoUrl = loginData.user.store.logoUrl!!
                )
            )
            return rowId != -1L
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertLoginDataIntoLocalDB(loginData: LoginData, password: String) {
        try {
            val userEntity = UserMapper.toUserEntity(loginData.user, password)
            val authEntity = UserMapper.toAuthEntity(loginData)
            val storeEntity = UserMapper.toStoreEntity(loginData.user.id, loginData.user.store)
            val storeLogoUrlEntity = UserMapper.toStoreLogoUrlEntity(
                loginData.user.id,
                loginData.user.store.id,
                loginData.user.store.logoUrl!!
            )
            
            userDao.insertCompleteUserData(userEntity, authEntity, storeEntity, storeLogoUrlEntity)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getCompleteUserData(userId: Int): LoginData? {
        val userEntity = userDao.getUserEntity(userId)
        val authEntity = userDao.getAuthEntity(userId)
        
        // Get storeId from userEntity first
        val storeId = userEntity?.storeId
        if (storeId == null) return null
        
        val storeEntity = userDao.getStoreEntity(storeId)
        val storeLogoUrlEntity = userDao.getStoreLogoUrlEntity(storeId)

        return if (userEntity != null && authEntity != null && storeEntity != null && storeLogoUrlEntity != null) {
            UserMapper.userCompleteDataToLoginData(
                userEntity,
                authEntity,
                storeEntity,
                storeLogoUrlEntity
            )
        } else {
            null
        }
    }

}