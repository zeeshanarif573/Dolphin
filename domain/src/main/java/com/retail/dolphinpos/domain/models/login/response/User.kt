package com.retail.dolphinpos.domain.models.login.response

data class User(
    val id: Int,
    val managerId: Int,
    val name: String,
    val password: String,
    val roleTitle: String,
    val status: String,
    val store: Store,
    val storeId: Int,
    val username: String
)