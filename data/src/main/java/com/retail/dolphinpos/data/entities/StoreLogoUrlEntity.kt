package com.retail.dolphinpos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store_logo_url",)

data class StoreLogoUrlEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val storeID: Int,
    val fileURL: String?,
    val originalName: String?
)