package com.retail.dolphinpos.domain.model.auth.pin.response

data class VerifyPinUser(
    val alreadyClockedIn: Boolean,
    val createdAt: String,
    val deletedAt: Any,
    val email: String,
    val password: String = "",
    val id: Int,
    val managerId: Int,
    val name: String,
    val permissions: Any,
    val phoneNo: String,
    val pin: String,
    val roleId: Int,
    val roleTitle: String,
    val status: String,
    val store: VerifyPinStore,
    val storeId: Int,
    val updatedAt: String,
    val username: String
)