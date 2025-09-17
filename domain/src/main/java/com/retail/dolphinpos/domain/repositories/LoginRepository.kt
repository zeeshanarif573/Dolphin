package com.retail.dolphinpos.domain.repositories

import com.retail.dolphinpos.domain.model.auth.login.request.LoginRequest
import com.retail.dolphinpos.domain.model.auth.login.response.LoginResponse

interface LoginRepository {
    suspend fun login(request: LoginRequest): LoginResponse

}