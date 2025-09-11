package com.retail.dolphinpos.data.repositories

import com.retail.dolphinpos.data.dao.UserDao
import com.retail.dolphinpos.data.mapper.UserMapper
import com.retail.dolphinpos.data.service.ApiService
import com.retail.dolphinpos.domain.models.login.request.LoginRequest
import com.retail.dolphinpos.domain.models.login.response.LoginResponse
import com.retail.dolphinpos.domain.models.login.response.User
import com.retail.dolphinpos.domain.repositories.LoginRepository

class LoginRepositoryImpl(
    private val api: ApiService,
    private val userDao: UserDao,
) : LoginRepository {

    override suspend fun login(request: LoginRequest): LoginResponse {
        return try {
            api.login(request)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertUserDataIntoLocalDB(user: User, password: String): Boolean {
        try {
            val rowId = userDao.insertUser(UserMapper.userToUserEntity(user, password))
            return rowId != -1L
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getUser(): User? {
        return try {
            val userEntity = userDao.getUser()
            userEntity?.let { UserMapper.userEntityToUser(it) }
        } catch (e: Exception) {
            throw e
        }
    }
}