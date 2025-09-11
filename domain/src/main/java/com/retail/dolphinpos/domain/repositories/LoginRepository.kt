package com.retail.dolphinpos.domain.repositories

import com.retail.dolphinpos.domain.models.login.request.LoginRequest
import com.retail.dolphinpos.domain.models.login.response.LoginResponse
import com.retail.dolphinpos.domain.models.login.response.User


interface LoginRepository {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun insertUserDataIntoLocalDB(user: User, password: String): Boolean
    suspend fun getUser(): User?
}