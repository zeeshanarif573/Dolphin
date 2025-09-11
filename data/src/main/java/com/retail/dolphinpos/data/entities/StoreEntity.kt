package com.retail.dolphinpos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store")
data class StoreEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val location: String,
    val multiCashier: Boolean,
    val policy: String,
    val advertisementImg: String?,
    val isAdvertisement: Boolean?,
    val allowCustomDiscount: Boolean
)
