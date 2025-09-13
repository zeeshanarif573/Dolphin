package com.retail.dolphinpos.data.service

import com.retail.dolphinpos.domain.models.auth.login.request.LoginRequest
import com.retail.dolphinpos.domain.models.auth.login.response.LoginResponse
import com.retail.dolphinpos.domain.models.auth.select_register.GetStoreRegistersResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("store-registers")
    suspend fun getStoreRegisters(@Query("storeId") storeId: Int): GetStoreRegistersResponse

}