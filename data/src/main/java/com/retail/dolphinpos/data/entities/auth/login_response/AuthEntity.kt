package com.retail.dolphinpos.data.entities.auth.login_response

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "auth_table",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
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