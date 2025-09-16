package com.retail.dolphinpos.data.entities.verify_pin_response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class VerifyPinUserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val alreadyClockedIn: Boolean,
    val createdAt: String,
    val deletedAt: Any,
    val email: String,
    val password: String = "",
    val managerId: Int,
    val name: String,
    val permissions: Any,
    val phoneNo: String,
    val pin: String,
    val roleId: Int,
    val roleTitle: String,
    val status: String,
    val storeId: Int,
    val updatedAt: String,
    val username: String
)