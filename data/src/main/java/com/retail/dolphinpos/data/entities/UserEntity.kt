package com.retail.dolphinpos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val alreadyClockedIn: Boolean?,
    val createdAt: String?,
    val deletedAt: String?,
    val email: String?,
    val password: String = "",
    val managerId: Int?,
    val name: String?,
    val permissions: String?,
    val phoneNo: String?,
    val pin: String?,
    val roleId: Int?,
    val roleTitle: String?,
    val status: String?,
    val storeId: Int,
    val updatedAt: String?,
    val username: String?
)