package com.retail.dolphinpos.domain.model.auth.users

data class User(
    val alreadyClockedIn: Boolean?,
    val createdAt: String?,
    val deletedAt: String?,
    val email: String?,
    val password: String = "",
    val id: Int,
    val managerId: Int?,
    val name: String?,
    val permissions: String?,
    val phoneNo: String?,
    val pin: String?,
    val roleId: Int?,
    val roleTitle: String?,
    val status: String?,
    val store: Store?,
    val storeId: Int,
    val updatedAt: String?,
    val username: String?
)