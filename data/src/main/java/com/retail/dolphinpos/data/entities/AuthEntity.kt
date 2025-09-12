package com.retail.dolphinpos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "auth_table",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ]
)
data class AuthEntity(
    @PrimaryKey(autoGenerate = false)
    val userId: Int,
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: String
)