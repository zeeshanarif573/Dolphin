package com.retail.dolphinpos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    val roleTitle: String?,
    val status: String?,
    val password: String?,
    val username: String?,
    val storeId: Int?,
    val managerId: Int?
)

