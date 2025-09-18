package com.retail.dolphinpos.domain.model.auth.login.response

data class LoginUser(
    val id: Int = 0,
    val managerId: Int? = 0,
    val name: String? = "",
    val password: String? = "",
    val roleTitle: String? = "",
    val status: String? = "",
    val store: LoginStoreData,
    val storeId: Int = 0,
    val username: String? = ""
)