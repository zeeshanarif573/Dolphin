package com.retail.dolphinpos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_table")
data class AuthEntity(
    @PrimaryKey(autoGenerate = false)
    val userId: Int, // same as user.id
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: String
)