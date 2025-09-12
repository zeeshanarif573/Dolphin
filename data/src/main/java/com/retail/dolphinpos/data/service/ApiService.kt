package com.retail.dolphinpos.data.service

import com.retail.dolphinpos.domain.models.auth.login.request.LoginRequest
import com.retail.dolphinpos.domain.models.auth.login.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

}