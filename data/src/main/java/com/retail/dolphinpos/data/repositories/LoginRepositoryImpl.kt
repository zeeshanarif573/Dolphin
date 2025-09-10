package com.retail.dolphinpos.data.repositories

import com.retail.dolphinpos.data.dao.UserDao
import com.retail.dolphinpos.data.entities.UserEntity
import com.retail.dolphinpos.data.entities.toEntity
import com.retail.dolphinpos.data.service.ApiService
import com.retail.dolphinpos.domain.models.login.request.LoginRequest
import com.retail.dolphinpos.domain.models.login.response.LoginResponse
import com.retail.dolphinpos.domain.models.login.response.User
import com.retail.dolphinpos.domain.repositories.LoginRepository

class LoginRepositoryImpl(
    private val api: ApiService,
    private val userDao: UserDao,
) : LoginRepository {
    override suspend fun login(
        request: LoginRequest
    ): LoginResponse {
        return try {
            api.login(request)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertLoginDataIntoLocalDB(user: User) {
        userDao.insertUser(user.toEntity())
    }

}