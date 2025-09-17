package com.retail.dolphinpos.data.repositories

import com.retail.dolphinpos.data.service.ApiService
import com.retail.dolphinpos.domain.model.auth.login.request.LoginRequest
import com.retail.dolphinpos.domain.model.auth.login.response.LoginResponse
import com.retail.dolphinpos.domain.repositories.LoginRepository

class LoginRepositoryImpl(
    private val api: ApiService
) : LoginRepository {

    override suspend fun login(request: LoginRequest): LoginResponse {
        return try {
            api.login(request)
        } catch (e: Exception) {
            throw e
        }
    }

}