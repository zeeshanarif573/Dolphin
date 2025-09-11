package com.retail.dolphinpos.domain.models.login.response

data class User(
    val id: Int = 0,
    val managerId: Int? = 0,
    val name: String? = "",
    val password: String? = "",
    val roleTitle: String? = "",
    val status: String? = "",
    val store: Store?,
    val storeId: Int? = 0,
    val username: String? = ""
)