package com.retail.dolphinpos.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "store_logo_url",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StoreEntity::class,
            parentColumns = ["id"],
            childColumns = ["storeID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StoreLogoUrlEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val storeID: Int,
    val fileURL: String,
    val originalName: String
)
