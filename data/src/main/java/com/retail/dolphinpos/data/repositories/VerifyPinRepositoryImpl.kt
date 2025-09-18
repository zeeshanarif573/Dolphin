package com.retail.dolphinpos.data.repositories

import com.retail.dolphinpos.data.dao.UserDao
import com.retail.dolphinpos.data.mapper.UserMapper
import com.retail.dolphinpos.domain.model.auth.users.User
import com.retail.dolphinpos.domain.repositories.VerifyPinRepository

class VerifyPinRepositoryImpl(
   private val userDao: UserDao
) : VerifyPinRepository {

    override suspend fun getCompleteUserData(pin: String): User? {
        val userEntity = userDao.getUserEntity(pin)

        // Get storeId from userEntity first
        val storeId = userEntity?.storeId
        if (storeId == null) return null

        val storeEntity = userDao.getStoreEntity(storeId)
        val storeLogoUrlEntity = userDao.getLogoUrlEntity(storeId)

        return if (storeEntity != null && storeLogoUrlEntity != null) {
            UserMapper.toUsers(
                userEntity,
                storeEntity,
                storeLogoUrlEntity
            )
        } else {
            null
        }
    }

}